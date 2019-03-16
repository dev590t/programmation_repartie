il y a une explication sur wait free snapshot
    https://en.wikipedia.org/wiki/Shared_snapshot_objects#Multi-Writer_Multi-Reader_implementation_by_Afek_et_al.


ça c'est du code wait free snapshot
#+BEGIN_SRC 
    public class WFSnapshot
    implements Snapshot{
    private SRSW_atomic[] value;
    public void update(int i,int v) {
        int[] snapshot = this.scan();
        int label = this.value[i].read();
        this.value.write(i,
                         newEntry(label+1,v,snapshot);
                         }}
    public int[] scan(){
        int[] copy = new int[N];
        boolean moved = new boolean[N];
        Collect:
        while(true){
            // first collect
            for (int j=0;j<N;j++)
                copy[j]=this.value[j].read();
            // second collect
            for (int j=0;j<N;j++)
                if (copy[j]!=this.value[j].read()) { // thread moved
                    if (this.moved[j]) { // second move
                        return getScan(this.value[j].read())
                            } else { // first move
                        this.moved[j] = true;
                        continue Collect;
                    }
                }
            return copy;
        }
    }
}
#+END_SRC

    Each thread has a singlewriter, n-reader atomic register that it alone writes and all the others read. The
    register has three fields:
    - A label field, extracted by a getLabel method, that is incremented each
        time the thread updates its value,
        - A value field, extracted by a getValue method, representing that register’s
        most recently written value, and
        - A scan field, extracted by getScan, representing that thread’s most recent
        scan.
        A register value is assembled by calling
        newEntry(label, value, scan);

The scan method creates a temporary n-element Boolean array moved, initialized to false, which records which threads have been observed to move in the
    course of the scan. Each thread performs a first collect, and then starts a second. During the second collect, it tests whether the register’s label has changed.
    If no thread’s label has changed, then the collect is clean, and the scan returns
    the result of the collect. If any thread’s label has changed, the scanning thread
    tests the moved array to detect whether this is the second time this thread has
    moved. If so, it returns that threads scan, and otherwise updates moved and
    resumes the outer loop.

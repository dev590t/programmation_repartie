import java.util.concurrent.atomic.AtomicStampedReference;

class Elem <T> {
	public T a_table;
	public T[] snap;
	public Elem(T a_table){
		this.a_table=a_table;
		snap=null;
	}
	public Elem(T a_table, T[] snap){
		this.a_table=a_table;
		this.snap=snap;
	}
}
class WaitFreeSnap<T> implements Snapshot<T> {
	private AtomicStampedReference <Elem<T>>[] a_table;
	public WaitFreeSnap(int capacity, T init){
		a_table= new AtomicStampedReference[capacity];
		T[] initsnap= (T[])new Object[capacity];
		for (int i=0;i<capacity;i++) {
			initsnap[i]=init;		
		}
		for (int i=0;i<capacity;i++) {Elem e=new Elem(init,initsnap);
			a_table[i]= new AtomicStampedReference <Elem<T>> (e,0);
			
		}
	}
	
	public void update(T w) {
		int me=ThreadID.get();
		int st = a_table[me].getStamp();
		T[] lscan=scan();
		Elem<T> v=new Elem<T>( w, lscan);
		a_table[me].set(v,st+1);
	}
	  public T[] scan() {
		  int N = a_table.length;
	        int[] copy = new int[N];
	        boolean[] moved = new boolean[N];
	        Collect:
	        while(true){
	            // first collect
	            for (int j=0;j<N;j++)
	                copy[j]=((Object) this.a_table[j]).reference;
	            // second collect
	            for (int j=0;j<N;j++)
	                if (copy[j]!=this.a_table[j].reference()) { // thread moved
	                    if (this.moved[j]) { // second move
	                        return getScan(((Object) this.a_table[j]).reference())
	                            } else { // first move
	                        this.moved[j] = true;
	                        continue Collect;
	                    }
	                }
	            return copy;
	        }
	    }
	  
	  private boolean equals(Elem[] oldCopy, Elem[] newCopy) {
		// TODO Auto-generated method stub
		  for (int i = 0 ; i< oldCopy.length; i ++) {
			  if (oldCopy[i] != newCopy[i]) return false;
		  }
		  return true;
	}
		}
	  private Elem[] collect() {
	      Elem[] copy =
	          new Elem[n];
	      for (int j = 0; j < n; j++)
	          copy[j] = this.register[j].reference();
	      return copy;
	  }


}

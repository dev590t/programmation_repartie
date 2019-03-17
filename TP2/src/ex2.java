
class SimpleSnap<T> implements Snapshot<T> {
    private AtomicStampedReference<T>[] a_table;
    public SimpleSnap(int capacity, T  integer){
        a_table= (AtomicStampedReference<T>[]) new Object[capacity];
        for (int i=0;i<capacity;i++)a_table[i]= new AtomicStampedReference<>(0, integer);
    }
    public void update(T v) {
        int me=ThreadID.get();
        a_table[me]= new AtomicStampedReference<>(0, v);
        //ne fait pas partie de l’implémentation
        try{ MyThread.sleep(1);} catch(InterruptedException e){};
        MyThread.yield();
    }
    private AtomicStampedReference<T>[] collect() {
        AtomicStampedReference<T>[] copy= (AtomicStampedReference<T>[]) new Object[a_table.length];
        for(int j=0;j<a_table.length;j++){ copy[j]=a_table[j];
            //ne fait pas partie de l’implémentation
            try{ MyThread.sleep(3);} catch(InterruptedException e){};
            MyThread.yield();
        }
        return copy;
    }
    public T[] scan(){
        AtomicStampedReference<T>[] result;
        Collect:
        while(true) {
        result=collect();
        AtomicStampedReference<T>[] result2=collect();
        for(int i = 0; i < result.length; i++) {
        	if(result[i] != result2[i]) continue Collect;
        }
        
        T[] r = (T[]) new Object[result.length];        
        for(int i = 0; i < result.length; i++) {
        	r[i] = result[i].getReference();
        }
        return r;
        }
        
    }
}
class ThreadID {
    static volatile int nextID=0;
    private static class ThreadLocalID extends ThreadLocal<Integer>{
        @Override
        protected synchronized Integer initialValue(){
            return nextID ++;
        }
    }
    private static final ThreadLocal<Integer> threadID =new ThreadLocal<Integer>(){
            @Override
            protected synchronized Integer initialValue(){
                    return nextID ++;
                }
        };
    public static int get(){
        return threadID.get();
    }
    public static void set (int index){
        threadID.set(index);
    }
}
class MyThread extends Thread{
    public SimpleSnap<Integer> partage;
    public int nb;
    public MyThread( SimpleSnap<Integer> partage, int nb){
        this.partage=partage;
        this.nb=nb;
    }
    public void run(){
        if (ThreadID.get()!=0){
            partage.update(new Integer(1));
            partage.update(new Integer(2));
            partage.update(new Integer(3));
        }
        else {
            Object [] O=new Object[nb];
            O=partage.scan();
            System.out.print("scan de "+ThreadID.get() + ": ");
            for(int i=0;i<nb;i++){
                
                    System.out.print((Integer)O[i]+" ");
            }
            System.out.println();
        }
    }
}


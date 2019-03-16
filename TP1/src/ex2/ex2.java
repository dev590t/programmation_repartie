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

class TPb {
    public static void main(String[] args) {
        MonObjet o = new MonObjet(0);
        Thread TH[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            TH[i] = new MyThreadA("nom=" + i);
        }
        try {
            for (int i = 0; i < 10; i++) {
                TH[i].start();
            }
            for (int i = 0; i < 10; i++) {
                TH[i].join();
            }
        } catch (InterruptedException e) {
        }
    }
}
class Travail {
    public void faire() {
        ThreadID tID = new ThreadID();
        System.out.println("la thread " + tID.get() + " travaille : Thread id "
                           + (Thread.currentThread()).getId());
    }
}
class MyThreadA extends Thread {
    public MyThreadA(String name) {
        super(name);
    }
    @Override
    public void run() {
        ThreadID tID = new ThreadID();
        System.out.println("la thread " + tID.get() + " débute: Thread id"
                            + (Thread.currentThread()).getId());
        (new Travail()).faire();
        System.out.println("la thread " + tID.get() + " termine : Thread id "
                           + (Thread.currentThread()).getId());
    }
}

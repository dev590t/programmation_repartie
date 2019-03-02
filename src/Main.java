class MonObjet {
    ThreadLocal<Integer> last;//nb ecriture de chaque thread
     int value;//valeur commune
     int valuebis;//valeur commune
    public MonObjet(int init) {
        value = init;
        last = new ThreadLocal<Integer>() {
                protected Integer initialValue() {
                    return 0;
                }
            };
    }
    public int read() {
        return value;
    }
    public void add() {
        last.set(last.get() + 1);
        value ++;
        valuebis ++;
    }
}
class MyThread2 extends Thread {
    public MonObjet o;
    public int nbwrite;
    public MyThread2(MonObjet o, int nbwrite) {
        this.o = o;
        this.nbwrite = nbwrite;
    }
    public void run() {
        for (int i = 0; i < nbwrite; i++) {
            o.add();
            this.yield();
        }
        System.out.println("la thread "+this.getName()+
                           " value=" + o.value + ", " + "valuebis=" + o.valuebis + " et "
                           + " last=" + o.last.get());
    }
}
class TP {
    public static void main(String[] args) {
        MonObjet o = new MonObjet(0);
        MyThread2 W;
        MyThread2 R;
        W = new MyThread2(o, 1000);
        R = new MyThread2(o, 5000);
        W.start();
        R.start();
        try {
            R.join();
            W.join();
        } catch (InterruptedException e) {
        }
        System.out.println("value=" + o.value + ", " + "valuebis=" + o.valuebis + " et "
                           + " last=" + o.last.get());
    }
}

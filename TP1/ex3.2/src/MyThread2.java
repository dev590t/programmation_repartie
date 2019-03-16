class MyThread2 extends Thread {
    public Partaged_object o;
    public int nbwrite;
    int id;
    public MyThread2(Partaged_object o, int nbwrite, int id) {
        this.o = o;
        this.nbwrite = nbwrite;
        this.id = id;
    }
    public void run() {
        for (int i = 0; i < nbwrite; i++) {
            o.request_add_by(id);
            this.yield();
        }
        System.out.println("la thread "+this.getName()+
                           " value=" + o.value + ", " + "valuebis=" + o.valuebis + " et "
                           + " nb_write=" + o.nb_write.get());
    }
}

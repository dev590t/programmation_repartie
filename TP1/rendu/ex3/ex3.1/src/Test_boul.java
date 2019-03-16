public class Test_boul {
	
    public static void main(String[] args) {
        Partaged_object o = new Boulangerie_locked_object(0);
        MyThread2 W;
        MyThread2 R;
        W = new MyThread2(o, 10000,0);
        R = new MyThread2(o, 50000,1);
        W.start();
        R.start();
        try {
            R.join();
            W.join();
        } catch (InterruptedException e) {
        }
        System.out.println("value=" + o.value + ", " + "valuebis=" + o.valuebis + " et "
                           + " nb_write=" + o.nb_write.get());
    }
}

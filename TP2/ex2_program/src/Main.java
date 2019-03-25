class Main {
    public static void main(String[] args) {
        int nb=15;
        SimpleSnap<Integer> partage= new SimpleSnap<Integer>(nb,new Integer(0));
        MyThread R[]=new MyThread[nb];
        for (int i=0;i<nb;i++) R[i]= new MyThread(partage,nb);
        try{
            for (int i=0;i<nb;i++){R[i].start();if (i!=0)R[i].join();}
            R[0].join();
        } catch(InterruptedException e){};
    }
}
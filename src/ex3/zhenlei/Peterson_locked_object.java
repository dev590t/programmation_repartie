class Peterson_locked_object extends Partaged_object  {

    // thread-local index, 0 or 1
    private static boolean[] flag = new boolean[2] ;
    private static int victim;
    public Peterson_locked_object(int init) {
        super(init);
    }
            
    public void lock(int i ) {
        int j=1- i;

        flag[i] = true; // I'm interested
        victim = i; // you go first
        while (flag[j] && victim == i) {}; // wait

    }
    public void unlock(int i ) {
        flag[i] = false; // I'm not interested

    }
}

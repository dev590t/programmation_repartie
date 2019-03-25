
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
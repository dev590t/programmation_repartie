/* objet accès concurrent par thread */
abstract class Partaged_object {
/* pour tester nb acces de objet par chaque thread 
on see flot execution is correct
*/
    ThreadLocal<Integer> nb_write;//nb ecriture de chaque thread

/* pour tester coherence de value avec manipulation concurrent par few thread 
on look output is correct for input
*/
     int value;//valeur commune
     int valuebis;//valeur commune
    public Partaged_object(int init) {
        value = init;
        nb_write = new ThreadLocal<Integer>() {
                protected Integer initialValue() {
                    return 0;
                }
            };
    }
    public int read() {
        return value;
    }
    public void request_add_by(int i) {        
        nb_write.set(nb_write.get() + 1);
        this.lock(i);
        value ++;
        valuebis ++;
        this.unlock(i);
    }
    abstract protected void unlock(int i );
    abstract protected void lock(int i );
}

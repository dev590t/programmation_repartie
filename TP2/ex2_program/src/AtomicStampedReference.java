
public class AtomicStampedReference<T> {
	T reference;
	int stamp1;
	
    public int getstamp(AtomicStampedReference<T> elt){
        return elt.stamp1;
    }
    public T getReference(){
        return this.reference;
    }
    public AtomicStampedReference(int stamp,T reference){
        this.stamp1 = stamp1;
        this.reference = reference;
    }

}

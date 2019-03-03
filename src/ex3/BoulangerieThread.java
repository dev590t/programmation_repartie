
public class BoulangerieThread implements Runnable{

	Boulangerie b;
	
	public BoulangerieThread(Boulangerie b) {
		this.b=b;
	}
	@Override
	public void run() {
		b.lock();
		b.unlock();
		
	}

}

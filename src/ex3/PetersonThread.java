
public class PetersonThread implements Runnable {
	Peterson p;
	
	public PetersonThread(Peterson p) {
		this.p=p;
	}
	@Override
	public void run() {
		p.lock();
		p.unlock();
		
	}

}

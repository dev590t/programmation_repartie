public class Boulangerie_locked_object extends Partaged_object {


	volatile private boolean flag[];
	volatile private int noms[];
	public Boulangerie_locked_object(int init) {
		super(init);
		flag = new boolean[2];
		noms = new int[2];
		
	}

	private int maxNoms() {
		int max = noms[0];
		for(int i=1;i<noms.length;i++) {
			if(max < noms[i]) max = noms[i];
		}
		return max;
	}
	
	private boolean otherThreadCritic() {
		for(int i=0;i<flag.length;i++) {
			if(i != read() && flag[i] && noms[i] < i) return true;
		}
		return false;
	}
	
	@Override
	protected void lock(int i) {
		// TODO Auto-generated method stub
		flag[i] = true;
		noms[i] = maxNoms() + 1;
		while(otherThreadCritic()) {
			System.out.println("Thread numéro "+ i + " attend.");
		}
		
		System.out.println("Thread numéro "+ i + " en section critique.");
		
	}

	@Override
	protected void unlock(int i) {
		flag[i] = false;
		
	}

}

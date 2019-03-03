public class Boulangerie implements Lock{

	private boolean flag[];
	private int noms[];
	
	int nom;
	
	public Boulangerie(int nbThread) {
		flag = new boolean[nbThread];
		noms = new int[nbThread];
		
		for(int i=0; i<nbThread; i++) {
			flag[i] = false;
			noms[i] = 0;
		}
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
			if(i !=nom && flag[i] && noms[i] < nom) return true;
		}
		return false;
	}
	@Override
	public void lock() {
		flag[nom] = true;
		noms[nom] = maxNoms() + 1;
		while(otherThreadCritic()) {
			System.out.println("Thread numéro "+ nom + " attend.");
		}
		
		
	}

	@Override
	public void unlock() {
		flag[nom] = false;
		
	}

}








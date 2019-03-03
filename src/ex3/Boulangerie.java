public class Boulangerie implements Lock{

	private boolean flag[];
	private int noms[];
	MyInt myint;
	int nom;
	
	public Boulangerie(int nbThread, int nom, MyInt myint) {
		this.nom = nom;
		this.myint = myint;
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
		
		System.out.println("Thread numéro "+ nom + " en section critique.");
		myint.add();
	}

	@Override
	public void unlock() {
		flag[nom] = false;
		
	}
	
	public static void main(String[] args) {
		MyInt myint = new MyInt(0);
		Boulangerie b1 = new Boulangerie(2,0,myint);
		Boulangerie b2 = new Boulangerie(2,1,myint);

		BoulangerieThread bt1 = new BoulangerieThread(b1);
		BoulangerieThread bt2 = new BoulangerieThread(b2);

		bt1.run();
		bt2.run();
		
		// Doit afficher 2
		System.out.println("MyInt: "+myint.getVal());
		
   }
	
	

}

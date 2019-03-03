public class Peterson implements Lock{
	int nom;
	
	private boolean[] flag = {false, false};
	private volatile int turn;
	private MyInt myint;
	
	public Peterson(int nom, MyInt myint) {
		this.nom = nom;
		this.myint = myint;
	}
	
	
	public int otherThread() {
		if(nom == 1) return 0;
		return 1;
	}
	
	@Override
	public void lock(){
        flag[nom] = true;
        turn = otherThread();
        
        while(flag[otherThread()] && turn == otherThread()){
        	System.out.println("Thread number "+ nom + " is waiting.");
        };
        
        System.out.println("Thread number "+ nom + " is in the critical section");
        myint.add();
    }
	
	@Override
	public void unlock(){
       flag[nom] = false;
    }
	
	public static void main(String[] args) {
		MyInt myint = new MyInt(0);
		Peterson p1 = new Peterson(0, myint);
		Peterson p2 = new Peterson(1, myint);
		PetersonThread pt1 = new PetersonThread(p1);
		PetersonThread pt2 = new PetersonThread(p2);

		pt1.run();
		pt2.run();
		// Doit afficher 2
		System.out.println("MyInt: "+myint.getVal());
		
   }
 }

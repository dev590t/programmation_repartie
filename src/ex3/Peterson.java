public class Peterson implements Lock{
	int nom;
	
	private boolean[] flag = {false, false};
	private volatile int turn;
	
	public Peterson(int nom) {
		this.nom = nom;
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
    }
	
	@Override
	public void unlock(){
       flag[nom] = false;
    }
	
	public static void main(String[] args) {
	   Peterson p1 = new Peterson(0);
	   Peterson p2 = new Peterson(1);
   }
 }
 
 
 
 
 

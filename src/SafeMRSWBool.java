public class SafeMRSWBool {

	private int id;
	private SafeSMRSWBool[] r;
	
	public SafeMRSWBool(int n, int id) {
		r = new SafeSMRSWBool[n];
		this.id=id;
	}
	
	public void write(boolean x) {
		for(int i=0; i< r.length; i++)
			r[i].write(x);
	}
	
	public boolean read() {
		return r[id].read();
	}
}

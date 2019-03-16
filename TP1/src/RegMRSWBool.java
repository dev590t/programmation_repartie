public class RegMRSWBool {
	private boolean old;
	private SafeMRSWBool val;
	
	public RegMRSWBool(boolean old, SafeMRSWBool val) {
		this.old = old;
		this.val = val;
	}
	
	public void write(boolean x) {
		if(old != x) {
			val.write(x);
			old = x;
		}
	}
	
	public boolean read() {
		return val.read();
	}
}

public class RegMRSWInt {
	private RegMRSWBool[] b;
	
	public RegMRSWInt(int m) {
		b = new RegMRSWBool[m];
	}
	
	public void write(int x) {
		b[x].write(true);
		for(int i=x-1; i>0; i--)
			b[i].write(false);
	}
	
	public int read() {
		for(int i=0; i<b.length; i++)
			if(b[i].read()) return i;
		return -1;
	}
}
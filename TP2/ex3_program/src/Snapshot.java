
interface Snapshot<S >{
	 public void update(S v);
	 public S[] scan(); 
}
package F21AS;

public class Log extends Singleton{
	
	//private static Lock datalock = null;
	
	private static int nextID;
	
	public Log(){
		super();
		if(Singleton.checkReference(this)) {
			nextID=1;
			//datalock=new ReentrantLock();
		}
	}
	
	public void showMessage(String message) {
		if(!Singleton.checkReference(this))
			((Log)Singleton.getReference()).showMessage(message);
		else {
			System.out.println(message);
		}
	}
	
	public int getNextID() {
		if(!Singleton.checkReference(this))
			return ((Log)Singleton.getReference()).getNextID();
		else {
			int result=nextID;
			nextID += 1;
			return result;
		}
	}
}

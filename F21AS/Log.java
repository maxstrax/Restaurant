package F21AS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Log extends Singleton{
	
	private static Lock datalock = null;
	
	private static int nextID;
	
	public Log(){
		super();
		if(!Singleton.checkReference(this)) {
			nextID=0;
			datalock=new ReentrantLock();
		}
	}
	
	public void showMessage(String message) {
		if(!Singleton.checkReference(this))
			((Log)Singleton.getReference()).showMessage(message);
		else {
			if(datalock!=null)
				datalock.lock();
			System.out.println(message);
			if(datalock!=null)
				datalock.unlock();
		}
	}
	
	public int getNextID() {
		if(!Singleton.checkReference(this))
			return ((Log)Singleton.getReference()).getNextID();
		else {
			if(datalock!=null)
				datalock.lock();
			int result=nextID++;
			if(datalock!=null)
				datalock.unlock();
			return result;
		}
	}
}

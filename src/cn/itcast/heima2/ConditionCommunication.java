package cn.itcast.heima2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

	static class Business{
		
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		private boolean isSub =true;
		
		public void sub(int i ) {
			lock.lock();
			try {
				while(!isSub){
					try {
					condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for(int j=0;j<10;j++){
					System.out.println("sub i="+i+",loop="+j);
				}
				isSub=false;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
		
		public void main(int i ) {
			lock.lock();
			try {
				while(isSub){
					try {
					condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for(int j=0;j<100;j++){
					System.out.println("main i="+i+",loop="+j);
				}
				isSub=true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
		
	}
	

	/**
	 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次，请写出程序。 
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.main(i);
				}

			}
		}).start();

	}

}





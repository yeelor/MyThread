package com.j2cms;

/**
 *3个子线程，１个来１０下，如此循环50次，请写出程序。 
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionCommunication {

	static class Business{
		
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		private int isSub =1;
		
		public void sub1(int i ) {
			lock.lock();
			try {
				while(isSub!=1){
					try {
					condition1.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for(int j=0;j<10;j++){
					System.out.println("sub1 i="+i+",loop="+j);
				}
				isSub=2;
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}
		
		public void sub2(int i ) {
			lock.lock();
			try {
				while(isSub!=2){
					try {
					condition2.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for(int j=0;j<10;j++){
					System.out.println("sub2 i="+i+",loop="+j);
				}
				isSub=3;
				condition3.signal();
			} finally {
				lock.unlock();
			}
		}
		
		
		public void sub3(int i ) {
			lock.lock();
			try {
				while(isSub!=3){
					try {
					condition3.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for(int j=0;j<10;j++){
					System.out.println("sub3 i="+i+",loop="+j);
				}
				isSub=1;
				condition1.signal();
			} finally {
				lock.unlock();
			}
		}
		
	}
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 50; i++) {
					business.sub1(i);
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub2(i);
				}

			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub3(i);
				}

			}
		}).start();

	}

}





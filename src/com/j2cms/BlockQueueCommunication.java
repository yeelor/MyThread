package com.j2cms;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueCommunication {

static class Business{


	BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
	BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
	
	//匿名构造方法，运行时机在构造方法之前。每个对象都会调用一次.
	{
		try {
			queue2.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public  void sub(int i ) {
	
		try {
			queue1.put(1);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		for(int j=0;j<10;j++){
			System.out.println("sub i="+i+",loop="+j);
		}
		try {
			queue2.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
	public  void main(int i )  {
		try {
			queue2.put(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int j=0;j<100;j++){
			System.out.println("main i="+i+",loop="+j);
		}
		try {
			queue1.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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





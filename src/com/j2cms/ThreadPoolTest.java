package com.j2cms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 09_传智播客_张孝祥_java5线程并发库的应用
 *
 */
public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		// ExecutorService threadPool = Executors.newCachedThreadPool();
		// ExecutorService threadPool = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 10; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {

				@Override
				public void run() {

					for (int j = 0; j < 10; j++) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("thread " + task + "loop " + j);
					}
				}
			});
		}

		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("bombing!");

			}
		}, 6, 2, TimeUnit.SECONDS);
		
		
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {
			
			@Override
			public void run() {
			System.out.println("boooo!");
				
			}
		}, 10, TimeUnit.SECONDS);
		
	}

}

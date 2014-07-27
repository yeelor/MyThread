package com.j2cms;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 更复杂的定时器，开源的quartz，有demo
 * @author aleak
 *
 */
public class TraditionalTimerTest {

	static int count = 0;
	
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {

				System.out.println("bombing");
			}
		}, 10000, 3000);

		
		class MyTimerTask extends TimerTask{
			@Override
			public void run() {
				count = (count+1)%2;
				System.out.println("bombing");
				new Timer().schedule(new MyTimerTask(), 2000+2000*count);
				
			}
			
		}
		new Timer().schedule(new MyTimerTask(), 2000);
		
		
		while (true) {
			System.out.println(new Date().getSeconds());
			Thread.sleep(1000);
		}

	}

}

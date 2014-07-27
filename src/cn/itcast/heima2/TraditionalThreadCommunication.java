package cn.itcast.heima2;
public class TraditionalThreadCommunication {


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

//
//class Business {
//	private boolean bShouldSub = true;
//
//	public synchronized void sub(int i) {
//		while (!bShouldSub) {
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		for (int j = 1; j <= 10; j++) {
//			System.out.println("sub thread sequence of " + j + ",loop of " + i);
//		}
//		bShouldSub = false;
//		this.notify();
//	}
//
//	public synchronized void main(int i) {
//		while (bShouldSub) {
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		for (int j = 1; j <= 100; j++) {
//			System.out.println("main thread sequence of " + j + ",loop of " + i);
//		}
//		bShouldSub = true;
//		this.notify();
//	}
//}

class Business{
	private boolean isSub =true;
	
	public synchronized void sub(int i ) {
		while(!isSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=0;j<10;j++){
			System.out.println("sub i="+i+",loop="+j);
		}
		isSub=false;
		this.notify();
	}
	
	public synchronized void main(int i )  {
		while(isSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=0;j<100;j++){
			System.out.println("main i="+i+",loop="+j);
		}
		isSub=true;
		this.notify();
	}
	
}



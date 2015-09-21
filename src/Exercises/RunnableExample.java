package Exercises;

public class RunnableExample {

	public RunnableExample() {
		
	}
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new RunnableThread(), "thread1");
		Thread thread2 = new Thread(new RunnableThread(), "thread2");
		//@SuppressWarnings("unused")
		//RunnableThread thread3 = new RunnableThread("thread3");
		thread1.start();
		try {
			thread1.join();
		} catch(InterruptedException e) {
			
		}
		thread2.start();
		/*try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			
		}*/
		//System.out.println(Thread.currentThread());
	}

}

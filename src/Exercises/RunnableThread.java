package Exercises;

import java.util.Scanner;

public class RunnableThread implements Runnable {
	
	Thread runner;

	public RunnableThread() {
	}
	
	public void open() {
		Scanner s = new Scanner(System.in);
		String command;
		while(!(command = s.nextLine()).equals("QUIT")) {
			System.out.println(command + " - understood.");
		}
		System.out.println("You elected to QUIT " + Thread.currentThread().getName());
	}
	
	public RunnableThread(String threadname) {
		runner = new Thread(this, threadname);
		System.out.println(runner.getName());
		runner.start();
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread());
		open();
	}

}

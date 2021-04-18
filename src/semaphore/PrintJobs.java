package semaphore;

import java.util.concurrent.Semaphore;

//Multiple permits , only 1 printer, 1printer prints 1job at a time

public class PrintJobs implements Runnable {

	Semaphore semaphore = new Semaphore(5);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PrintJobs printJobs = new PrintJobs();
		Thread[] t = new Thread[10];
		for (int i = 0; i < 10; i++) {
			t[i] = new Thread(printJobs);
			t[i].start();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		print();

	}

	public void print() {

		try {
			semaphore.acquire();

			System.out.println("Acquired printer by " + Thread.currentThread().getName());

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Released printer " + Thread.currentThread().getName());
		semaphore.release();

	}

}

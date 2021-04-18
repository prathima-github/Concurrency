package semaphore;

import java.util.concurrent.Semaphore;
// Binary Semaphore ie 1 permit, 1 printer , 1printer prints 1job at a time
public class PrintJob implements Runnable {

	public PrintJob() {

		semaphore = new Semaphore(1);

	}

	private final Semaphore semaphore;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PrintJob printer = new PrintJob();
		for (int i = 0; i < 10; i++) {

			Thread t = new Thread(printer);
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		print();

	}

	public void print() {
		boolean status = semaphore.tryAcquire();
		if (status) {
			System.out.println("Acquired printer - printing job  " + Thread.currentThread().getName());
			try {
				Thread.sleep(3000);

				System.out
						.println("Printing done for task " + Thread.currentThread().getName() + " - releasing printer");
				semaphore.release();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("task " + Thread.currentThread().getName() + " Waiting to acquire printer");
		}

	}

}

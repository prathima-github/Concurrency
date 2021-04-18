package semaphore;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Multiple Permits, Multiple Printers, 1Printer prints 1job at a time
public class MultiplePrinterJobs implements Runnable {

	Semaphore semaphore;
	Boolean[] printersAvailable = new Boolean[5];
	Lock printerLock;

	MultiplePrinterJobs() {
		Arrays.fill(printersAvailable, true);
		semaphore = new Semaphore(5);
		printerLock = new ReentrantLock();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiplePrinterJobs mpj = new MultiplePrinterJobs();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(mpj);
			t.start();
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
			int printerNo = getPrinter();

			if (printerNo != -1) {

				System.out.println("Printing by printer" + printerNo + ".." + Thread.currentThread().getName());
				Thread.sleep(3000);
				semaphore.release();
				System.out.println("Releasing printer" + printerNo + ".." + Thread.currentThread().getName());
				releasePrinter(printerNo);

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getPrinter() {
		printerLock.lock();
		for (int i = 0; i < printersAvailable.length; i++) {
			if (printersAvailable[i]) {
				printersAvailable[i] = false;
				printerLock.unlock();
				return i;
			}
		}
		printerLock.unlock();
		return -1;
	}

	public void releasePrinter(int i) {

		printersAvailable[i] = true;

	}

}

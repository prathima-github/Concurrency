package blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdConsBQ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Integer> bloQueue = new LinkedBlockingQueue<>(2);
		Thread ProdThread = new Thread(() -> {
			int value = 0;
			try {
				while (true) {
					bloQueue.put(value);
					System.out.println("Producer added - " + value);
					value++;

					Thread.sleep(2000);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		});
		Thread ConsThread = new Thread(() -> {
			try {
				while (true) {
					int value = bloQueue.take();
					System.out.println("Consumer took - " + value);
					Thread.sleep(1000);
					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		ProdThread.start();
		ConsThread.start();

		try {
			ProdThread.join();
			ConsThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

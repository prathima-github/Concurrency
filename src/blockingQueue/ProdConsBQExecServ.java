package blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdConsBQExecServ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Integer> bloQueue = new LinkedBlockingQueue<>(2);
		ExecutorService eServ = Executors.newFixedThreadPool(2);
		Runnable ProdTask = () -> {
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

		};
		Runnable ConsTask = () -> {
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

		};
		eServ.execute(ProdTask);
		eServ.execute(ConsTask);
		eServ.shutdown();
		

	}

}

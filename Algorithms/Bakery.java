public class Bakery extends Thread {

	public int thread_id;
	public static final int countToThis = 10;
	public static final int numberOfThreads = 3;
	public static volatile int count = 0;
	private static volatile boolean[] choosing = new boolean[numberOfThreads];
	private static volatile int[] ticket = new int[numberOfThreads];

	public Bakery(int id) {
		thread_id = id;
	}

	public void run() {
		int scale = 2;
		for (int i = 0; i < countToThis; i++) {
			lock(thread_id);
			// Start of critical section.
			count = count + 1;
			System.out.println("I am " + thread_id + " and count is: " + count);

			// Wait, in order to cause a race condition among the threads.
			try {
				sleep((int) (Math.random() * scale));
			} catch (InterruptedException e) {
				/* nothing */ }
			// End of critical section.
			unlock(thread_id);
		}
	}

	public void lock(int id) {
		choosing[id] = true;

		ticket[id] = findMax() + 1;
		choosing[id] = false;

		for (int j = 0; j < numberOfThreads; j++) {
			if (j == id)
				continue;
			while (choosing[j]) {
				/* nothing */ }
			while (ticket[j] != 0 && (ticket[id] > ticket[j] || (ticket[id] == ticket[j] && id > j))) {
				/* nothing */ }

		}
	}

	private void unlock(int id) {

		ticket[id] = 0;
	}

	private int findMax() {

		int m = ticket[0];

		for (int i = 1; i < ticket.length; i++) {
			if (ticket[i] > m)
				m = ticket[i];
		}
		return m;
	}

	public static void main(String[] args) {

		for (int i = 0; i < numberOfThreads; i++) {
			choosing[i] = false;
			ticket[i] = 0;
		}

		Bakery[] threads = new Bakery[numberOfThreads]; // Array of threads.

		// Initialize the threads.
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Bakery(i);
			threads[i].start();
		}
		// Wait all threads to finish.
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\nCount is: " + count);
		System.out.println("\nExpected was: " + (countToThis * numberOfThreads));
	}
}
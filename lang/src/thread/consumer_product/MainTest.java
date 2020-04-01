package thread.consumer_product;

/**
 * main test for producer and consumer problem
 *
 * @author missb
 * @create 2020--03--19 12:06 AM
 */

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();

        Thread consumer1 = new Thread(new Consumer(storage));
        consumer1.setName("consumer1");

        Thread consumer2 = new Thread(new Consumer(storage));
        consumer2.setName("consumer2");

        Thread product1 = new Thread(new Producer(storage));
        product1.setName("producer1");

        Thread product2 = new Thread(new Producer(storage));
        product2.setName("producer1");

        product1.start();
        product2.start();

        Thread.sleep(1000);

        consumer1.start();
        consumer2.start();
    }
}

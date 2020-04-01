package thread.consumer_product;

/**
 * consumer class
 *
 * @author missb
 * @create 2020--03--19 12:03 AM
 */

public class Consumer implements Runnable{
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int i = 0;
        int loopTime = 10;
        while( i < loopTime){
            ++i;
            storage.pop();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

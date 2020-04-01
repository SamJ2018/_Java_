package thread.consumer_product;

import java.util.Random;

/**
 * 生产者
 *
 * @author missb
 * @create 2020--03--18 11:57 PM
 */

public class Producer implements Runnable {
    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int i = 0;
        Random r = new Random();
        int loopTime = 10;
        while(i < loopTime){
            i++;
            Product product = new Product(i ,"phone" + r.nextInt(100));
            storage.push(product);
        }
    }
}

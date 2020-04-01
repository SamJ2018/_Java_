package thread.consumer_product;

/**
 * 仓库类
 *
 * @author missb
 * @create 2020--03--18 11:42 PM
 */

public class Storage {
    private Product[] products = new Product[10];
    private int top = 0;

    /**
     * 生产者往仓库放产品
     */
    public synchronized void push(Product product) {
        while (top == products.length) {
            try {
                System.out.println("producer wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //把产品放入仓库
        products[top++] = product;
        System.out.println(Thread.currentThread().getName() + " 生产了产品 " + product);
        System.out.println("producer notifyAll");
        notifyAll();

    }

    /**
     * 消费者从仓库取产品
     */
    public synchronized Product pop() {
        while (top == 0) {
            try {
                System.out.println("consumer wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //从仓库取产品
        Product p = new Product(products[--top].getId(), products[top].getName());
        products[top] = null;
        System.out.println(Thread.currentThread().getName() + "消费了产品 " + p);
        System.out.println("consumer notifyAll");
        notifyAll();
        return p;
    }
}

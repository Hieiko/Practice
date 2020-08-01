
import java.util.ArrayList;
import java.util.List;

public class demo4 {
    public static void main(String[] args) {
        ProductConsumer pc = new ProductConsumer();
        new Thread() {
            public void run() {
                try {
                    pc.produce();
                    ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            public void run() {
                try {
                    pc.consume();
                    ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

//简单的消费者模式
class ProductConsumer{
    private List<Integer> list =new ArrayList<>();
    public synchronized void produce() throws InterruptedException {
        while (true){
            if (list.isEmpty()){
                for (int i=0;i<10;i++){
                    list.add(i);
                    System.out.println("----生成了一个产品");
                    Thread.sleep(200);
                }
            }else {
                //通知等待进程
                notifyAll();
                wait();
            }
        }
    }
    public synchronized void consume() throws InterruptedException {
        while (true){
            if (!list.isEmpty()){
                for (int i=0;i<10;i++){
                    list.remove(0);
                    System.out.println("===消费了一个产品"+i);
                    Thread.sleep(100);
                }
            }else {
                notifyAll();
                wait();
            }
        }
    }
}


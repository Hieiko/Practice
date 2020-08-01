import java.util.Scanner;

public class demo1 {
    public static void main(String[] args) {
        A a = new A("a的线程");
        B b = new B();
        Thread t = new Thread(b,"b的线程");
        a.start();//启动 变为可运行的状态
        t.start();

        System.out.println("main getName"+Thread.currentThread().getName());
        System.out.println("main getPriority"+Thread.currentThread().getPriority());
        System.out.println("mian getId"+Thread.currentThread().getId());
        System.out.println("main getState"+Thread.currentThread().getState());
    }

    public static void a(){
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入");
        String s=sc.nextLine();
        System.out.println("您输入的是"+s);
        sc.close();
    }

    public static void b(){
        System.out.println("这是b方法");
    }

    public static class A extends Thread{
        //重写父类的构造方法
        public A(String name){
            super(name);
        }
        public void run(){
            a();
            System.out.println("a getName"+Thread.currentThread().getName());
            System.out.println("a getPriority"+Thread.currentThread().getPriority());
            System.out.println("a getId"+Thread.currentThread().getId());
            System.out.println("a getState"+Thread.currentThread().getState());
        }
    }
    //实现Runnable接口
    public static  class B implements Runnable{
        public  void run(){
            System.out.println("b() 休眠10秒");
            try{
                Thread.sleep(10*1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            b();
            System.out.println("b getName"+Thread.currentThread().getName());
            System.out.println("b getPriority"+Thread.currentThread().getPriority());
            System.out.println("b getId"+Thread.currentThread().getId());
            System.out.println("b getState"+Thread.currentThread().getState());
        }
    }
}

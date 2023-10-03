import java.util.concurrent.*;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/3 17:00
 */
public class Test implements Callable<String> {


    public Test() {
        System.out.println("当前线程："+Thread.currentThread().getName()+"正在构造");
    }

    @Override
    public String call() throws Exception {
        System.out.println("计算中");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("计算线程"+Thread.currentThread().getName());
        return "与服务器连接成功";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future = executorService.submit(new Test());
        Future<String> future2 = executorService.submit(new Test());


        System.out.println(future.get());
        System.out.println(future2.get());
    }
}

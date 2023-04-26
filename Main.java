import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int corePoolSize = 3;
        int maxPoolSize = 5;
        long keepAliveTime = 10;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        ExecutorService executorService = new ScalableThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);

        for (int i = 0; i < 12; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello world " + finalI);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}

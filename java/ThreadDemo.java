import java.util.concurrent.*;

public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        // 1. 继承Thread类
        class MyThread extends Thread {
            public void run() {
                System.out.println("继承Thread类创建的线程");
            }
        }
        new MyThread().start();

        // 2. 实现Runnable接口
        new Thread(() -> {
            System.out.println("实现Runnable接口创建的线程");
        }).start();

        // 3. 实现Callable接口
        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.println("实现Callable接口创建的线程");
            return "Callable返回值";
        });
        new Thread(task).start();
        System.out.println("Callable结果: " + task.get());

        // 4. 线程池详细实现
        System.out.println("\n=== 线程池实现 ===");
        
        // 4.1 固定大小线程池
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        fixedPool.execute(() -> System.out.println("固定线程池任务1"));
        fixedPool.execute(() -> System.out.println("固定线程池任务2"));
        
        // 4.2 缓存线程池
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        cachedPool.submit(() -> System.out.println("缓存线程池任务"));
        
        // 4.3 定时线程池
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);
        scheduledPool.schedule(() -> System.out.println("延迟1秒执行"), 1, TimeUnit.SECONDS);
        
        // 4.4 自定义线程池
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(
            2, // 核心线程数
            4, // 最大线程数
            60, // 空闲时间
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10) // 任务队列
        );
        customPool.execute(() -> System.out.println("自定义线程池任务"));
        
        // 关闭线程池
        fixedPool.shutdown();
        cachedPool.shutdown();
        scheduledPool.shutdown();
        customPool.shutdown();
    }
}

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
        
        // 4.4 自定义线程池(带策略)
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(
            2, // 核心线程数
            4, // 最大线程数
            60, // 空闲时间
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2), // 小容量队列
            new ThreadFactory() { // 自定义线程工厂
                private int count = 0;
                public Thread newThread(Runnable r) {
                    return new Thread(r, "CustomThread-" + count++);
                }
            },
            new RejectedExecutionHandler() { // 拒绝策略
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    System.out.println("任务被拒绝: " + r);
                }
            }
        );
        
        // 监控线程池状态
        ScheduledExecutorService monitor = Executors.newSingleThreadScheduledExecutor();
        monitor.scheduleAtFixedRate(() -> {
            System.out.println(
                String.format("线程池状态: 活跃线程=%d, 核心线程=%d, 最大线程=%d, 队列大小=%d",
                customPool.getActiveCount(),
                customPool.getCorePoolSize(),
                customPool.getMaximumPoolSize(),
                customPool.getQueue().size())
            );
        }, 0, 1, TimeUnit.SECONDS);
        
        // 提交超过线程池处理能力的任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            try {
                customPool.execute(() -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("执行任务: " + taskId + " 线程: " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                System.out.println("提交任务异常: " + taskId);
            }
        }
        
        // 关闭线程池
        Thread.sleep(5000);
        monitor.shutdown();
        fixedPool.shutdown();
        cachedPool.shutdown();
        scheduledPool.shutdown();
        customPool.shutdown();
    }
}

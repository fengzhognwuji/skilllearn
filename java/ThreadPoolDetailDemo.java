import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDetailDemo {
    public static void main(String[] args) {
        // 1. 线程池7大核心参数
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
            2,     // 核心线程数 (corePoolSize)
            5,     // 最大线程数 (maximumPoolSize)
            30,    // 空闲线程存活时间 (keepAliveTime)
            TimeUnit.SECONDS, // 时间单位
            new ArrayBlockingQueue<>(10), // 工作队列 (workQueue)
            new CustomThreadFactory(),    // 线程工厂 (threadFactory)
            new ThreadPoolExecutor.AbortPolicy() // 拒绝策略 (handler)
        );

        // 2. 线程池工作流程演示
        System.out.println("=== 线程池工作流程演示 ===");
        for (int i = 1; i <= 15; i++) {
            final int taskId = i;
            try {
                pool.execute(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 执行任务-" + taskId);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("任务-" + taskId + " 被拒绝: " + e.getMessage());
            }
        }

        // 3. 监控线程池状态
        monitorThreadPool(pool);
        
        // 4. 优雅关闭
        pool.shutdown();
    }

    // 自定义线程工厂
    static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger counter = new AtomicInteger(1);
        
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("CustomPool-Thread-" + counter.getAndIncrement());
            t.setDaemon(false);
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    // 线程池监控方法
    private static void monitorThreadPool(ThreadPoolExecutor pool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("\n=== 线程池状态 ===");
            System.out.println("核心线程数: " + pool.getCorePoolSize());
            System.out.println("活跃线程数: " + pool.getActiveCount());
            System.out.println("最大线程数: " + pool.getMaximumPoolSize());
            System.out.println("队列任务数: " + pool.getQueue().size());
            System.out.println("已完成任务: " + pool.getCompletedTaskCount());
        }, 0, 1, TimeUnit.SECONDS);
    }
}

import java.util.concurrent.locks.*;

public class LockDemo {
    private static int count = 0;
    private static final Object syncLock = new Object();
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        // 1. synchronized关键字
        synchronized(syncLock) {
            count++;
            System.out.println("synchronized修改count: " + count);
        }

        // 2. ReentrantLock
        reentrantLock.lock();
        try {
            count++;
            System.out.println("ReentrantLock修改count: " + count);
        } finally {
            reentrantLock.unlock();
        }

        // 3. ReadWriteLock
        rwLock.writeLock().lock();
        try {
            count++;
            System.out.println("ReadWriteLock修改count: " + count);
        } finally {
            rwLock.writeLock().unlock();
        }

        // 4. StampedLock
        long stamp = stampedLock.writeLock();
        try {
            count++;
            System.out.println("StampedLock修改count: " + count);
        } finally {
            stampedLock.unlockWrite(stamp);
        }

        // 多线程竞争演示
        Thread t1 = new Thread(LockDemo::incrementWithLock);
        Thread t2 = new Thread(LockDemo::incrementWithLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("最终count值: " + count);
    }

    private static void incrementWithLock() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}

//1 Synchronized Method : Here, the synchronized keyword is used on the methods themselves to lock the entire object. When a thread enters a synchronized method, it acquires the lock on the object and no other threads can enter any other synchronized method on the same object until the lock is released.
class Foo {
    private boolean oneDone;
    private boolean twoDone;
    
    public Foo() {
        oneDone = false;
        twoDone = false;
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        oneDone = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!oneDone) {
            wait();
        }
        printSecond.run();
        twoDone = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!twoDone) {
            wait();
        }
        printThird.run();
    }
}

//2. Synchronized on Object : In this method, a separate Object is used as a lock instead of the class itself. When a thread enters a synchronized block with the lock object, it acquires the lock and no other threads can enter any other synchronized blocks with the same lock object until the lock is released.
// For sychronize on object, using this to sync is enough( though it's OK to sync on another object)
class Foo {
    private Object lock;
    private boolean oneDone;
    private boolean twoDone;
    
    public Foo() {
        lock = new Object();
        oneDone = false;
        twoDone = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            oneDone = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!oneDone) {
                lock.wait();
            }
            printSecond.run();
            twoDone = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!twoDone) {
                lock.wait();
            }
            printThird.run();
        }
    }
}

//3 Synchronized on Two Objects : Here, two separate objects are used as locks to lock two different sections of the code. This can be useful in some scenarios where a single lock is not sufficient.
//Synchronized on Two Objects (not needed for this question, just put it here in case someone wants to use one object to protect one variable):
class Foo {
    private Object lock1;
    private Object lock2;
    private boolean oneDone;
    private boolean twoDone;
    
    public Foo() {
        lock1 = new Object();
        lock2 = new Object();
        oneDone = false;
        twoDone = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock1) {
            printFirst.run();
            oneDone = true;
            lock1.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock1) {
            synchronized (lock2) {
                while (!oneDone) {
                    lock1.wait();
                }
                printSecond.run();
                twoDone = true;
                lock2.notifyAll();
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock2) {
            while (!twoDone) {
                lock2.wait();
            }
            printThird.run();
        }
    }
}

//4 Semaphore : A Semaphore is a synchronization mechanism that uses a counter to control access to a shared resource. The acquire() method decrements the counter, and the release() method increments it. When the counter is 0, the acquire() method blocks until the counter is greater than 0.
class Foo {
    private Semaphore s2;
    private Semaphore s3;
    
    public Foo() {
        s2 = new Semaphore(0);
        s3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        s2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        s2.acquire();
        printSecond.run();
        s3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        s3.acquire();
        printThird.run();
    }
}

//5 Condition Variable : A Condition Variable is a synchronization mechanism that provides a way to wait for a certain condition to be true before proceeding. Here, the await() method releases the lock and blocks the thread until another thread calls the signal() method. The Condition class is used in conjunction with a Lock.
class Foo {
    private Lock lock;
    private boolean oneDone;
    private boolean twoDone;
    private Condition one;
    private Condition two;
    
    public Foo() {
        lock = new ReentrantLock();
        one = lock.newCondition();
        two = lock.newCondition();
        oneDone = false;
        twoDone = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            printFirst.run();
            oneDone = true;
            one.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (!oneDone) {
                one.await();
            }
            printSecond.run();
            twoDone = true;
            two.signal();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (!twoDone) {
                two.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }
    }
}


//all boolean here should be volatile (except in the synchronized), otherwise it might go into deadlock. No guarantee three threads will be run in the same CPU, thus diff CPU cache might maintain different values.
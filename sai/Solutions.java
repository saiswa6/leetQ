/*1114. Print in Order

Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}

Example 1:
Input: nums = [1,2,3]
Output: "firstsecondthird"
Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.

Example 2:
Input: nums = [1,3,2]
Output: "firstsecondthird"
Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
*/
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
//3rd Solution - Semaphore
class Foo {

    Semaphore one = new Semaphore(1);
    Semaphore two = new Semaphore(0);
    Semaphore three = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        one.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        two.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        one.release();
    }
}

////4th Condition Variable : A Condition Variable is a synchronization mechanism that provides a way to wait for a certain condition to be true before proceeding. Here, the await() method releases the lock and blocks the thread until another thread calls the signal() method. The Condition class is used in conjunction with a Lock.
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
//5th Solution
//rl1 and rl2 are effectively act as ReentrantLock with exception with the only difference that CountDownLatch can be "unlocked" from any thread by using countDown() method.
//ReentrantLock can't be used because its unlock() throws an exception when unlocking from the non-owner thread.\//CountDownLatch seems a good fit for this, from java doc " A synchronization aid that allows one or more threads to wait until, a set of operations being performed in other threads completes. "

class Foo {

    private CountDownLatch rl1 = new CountDownLatch(1);
    private CountDownLatch rl2 = new CountDownLatch(1);
    
    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        rl1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        rl1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        rl2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        rl2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*
1188. Design Bounded Blocking Queue
Implement a thread-safe bounded blocking queue that has the following methods:
BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
int size() Returns the number of elements currently in the queue.
*/

//Solution 1: Using synchronized
class BoundedBlockingQueue {
    Queue<Integer> boundedQueue;
    int capacity;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.boundedQueue = new ArrayDeque<>(capacity);
        
    }
    
    public synchronized void enqueue(int element) throws InterruptedException {
        while(boundedQueue.size() == capacity){
            wait();
        }

        boundedQueue.offer(element);
        notifyAll();
    }
    
    public synchronized int dequeue() throws InterruptedException {
        while (boundedQueue.isEmpty()) {
            wait();
        }
        int value = boundedQueue.remove();
        notifyAll();
        return value;
    }
    
    public int size() {
        return boundedQueue.size();
    }
}

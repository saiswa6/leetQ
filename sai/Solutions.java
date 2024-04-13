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
*BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
*void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
*int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
*int size() Returns the number of elements currently in the queue.
*/


// Solution 1 : Synchronized Block, here Queue is intialized with Array , not ArrayDeque(Very Important)
class BoundedBlockingQueue {

    private int queue[];
    private volatile int size; // declare size as volatile as it is accessed by many threads
    private int capacity;
    private int readPointer;
    private int writePointer;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        size = 0;
        readPointer = 0;
        writePointer = 0;
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (this) {
            while (size == capacity) {
                wait();
            }

            queue[writePointer++] = element;
            size++;
            writePointer = writePointer % capacity;
            notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized (this) {
            while (size == 0) {
                wait();
            }

            int value = queue[readPointer++];// readPointer is also ++, not -- as it is a Queue.
            size--;
            readPointer = readPointer % capacity;
            notifyAll();
            return value;
        }
    }

    public int size() {
        return size;
    }
}

//Solution 2: Using synchronized
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

//Solution 3: Reentrant Lock
  class BoundedBlockingQueue {
    Queue<Integer> boundedQueue;
    int capacity;
    ReentrantLock lock;
    Condition isFull;
    Condition isEmpty;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.boundedQueue = new ArrayDeque<>(capacity);
        this.lock = new ReentrantLock();
        this.isEmpty = lock.newCondition();
        this.isFull = lock.newCondition();
    }

    public void enqueue(int element) throws InterruptedException {
        try {
            lock.lock();
            while (boundedQueue.size() == capacity) {
                isFull.await();
            }

            boundedQueue.offer(element);
            isEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        try {
            lock.lock();
            while (boundedQueue.isEmpty()) {
                isEmpty.await();
            }
            int value = boundedQueue.remove();
            isFull.signalAll();
            return value;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return boundedQueue.size();
    }
}

// Slution 4 : Imp with Semaphore
/*
Semaphores: Two instances of the Semaphore class are used, s1 and s2, each serving a distinct purpose. s1 governs the ability to insert an item into the queue, and begins with permits equal to the capacity. s2 reflects the number of items in the queue available to be dequeued and starts with no permits. This is a classic application of the "producer-consumer" problem's solution, where one semaphore is used to signal "empty slots" and another semaphore is used to signal "available items".

When enqueue(element: int) is called, the following steps are performed:
---------------------------------------------------------------------------
self.s1.acquire(): Attempt to acquire a permit from s1, which represents a free slot in the queue. If there are no free slots, this call will block until another thread calls dequeue and releases a permit on s1.
self.q.append(element): Once a permit has been acquired (meaning there is space in the queue), the element is safely enqueued into the queue.
self.s2.release(): Releasing a permit on s2 to signal that an element has been enqueued and is now available for dequeuing.

For dequeue(), the steps are the mirror image:
----------------------------------------------
self.s2.acquire(): This acquires a permit from s2, which signifies that there is at least one element in the queue to be dequeued. If the queue is empty, this call will block until a thread calls enqueue and releases a permit on s2.
ans = self.q.popleft(): Removes the oldest (front) element from the queue safely because it's been ensured that the queue is not empty.
self.s1.release(): Releasing a permit on s1 to signal that an element has been dequeued and there is now a free slot in the queue.
*/
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

// This class represents a thread-safe bounded blocking queue with a fixed capacity.
public class BoundedBlockingQueue {
    // Semaphore to control the number of elements that can be added (based on capacity).
    private final Semaphore enqueueSemaphore;
    // Semaphore to control the number of elements that can be removed (starts at 0).
    private final Semaphore dequeueSemaphore;
    // The queue to store elements.
    private final Deque<Integer> queue;

    // Constructor initializes the semaphores and queue with specified capacity.
    public BoundedBlockingQueue(int capacity) {
        enqueueSemaphore = new Semaphore(capacity);
        dequeueSemaphore = new Semaphore(0);
        queue = new ArrayDeque<>();
    }

    // Enqueues an element into the queue if there's available capacity.
    public void enqueue(int element) throws InterruptedException {
        // Acquire a permit from enqueueSemaphore discarding it, if available capacity is 0 waits.
        enqueueSemaphore.acquire();
        synchronized (this) {
            // Adds the element to the end of the queue.
            queue.offer(element);
        }
        // Release a permit to dequeueSemaphore, increasing the number of available elements to dequeue.
        dequeueSemaphore.release();
    }

    // Dequeues an element from the front of the queue.
    public int dequeue() throws InterruptedException {
        // Acquire a permit from dequeueSemaphore, waiting if necessary until an element is available.
        dequeueSemaphore.acquire();
        int element;
        synchronized (this) {
            // Remove and return the front element of the queue.
            element = queue.poll();
        }
        // Release a permit to enqueueSemaphore, increasing the available capacity.
        enqueueSemaphore.release();
        return element;
    }

    // Returns the current number of elements in the queue.
    public int size() {
        synchronized (this) {
            // The size of the queue is returned.
            return queue.size();
        }
    }
}

//Semaphore other variant
class BoundedBlockingQueue {
    private final int[] queue;
    private volatile int size = 0;
    private int wp = 0, rp = 0;
    Semaphore enqSem, deqSem, lockSem;
    
    public BoundedBlockingQueue(int capacity) {
        this.queue = new int[capacity];
        enqSem = new Semaphore(capacity);
        deqSem = new Semaphore(0);
        lockSem = new Semaphore(1);
    }
    
    public void enqueue(int element) throws InterruptedException {
        enqSem.acquire();
        lockSem.acquire();
        
        queue[wp++] = element;
        size++;
        wp %= queue.length;
        
        lockSem.release();
        deqSem.release();
    }
    
    public int dequeue() throws InterruptedException {
        deqSem.acquire();
        lockSem.acquire();
        
        int res = queue[rp++];
        size--;
        rp %= queue.length;
        
        lockSem.release();
        enqSem.release();
        return res;
    }
    
    public int size() {
        return size;
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*
Question
1116. Print Zero Even Odd
You have a function printNumber that can be called with an integer parameter and prints it to the console.

Implement the ZeroEvenOdd class:
ZeroEvenOdd(int n) Initializes the object with the number n that represents the numbers that should be printed.
void zero(printNumber) Calls printNumber to output one zero.
void even(printNumber) Calls printNumber to output one even number.
void odd(printNumber) Calls printNumber to output one odd number.

Example 1:
Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously.
One of them calls zero(), the other calls even(), and the last one calls odd().
"0102" is the correct output.
*/

//Solution 1 : Synchronized

class ZeroEvenOdd {
    private int n;
    // private Semaphore
    private volatile int status = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <= n; i++) {
            while (status != 0) {      //Put this while block for status inside for loop to maintain context of i = 0, 1, 2, 3,....
                wait();
            }
            printNumber.accept(0);
            status = (i % 2) + 1;
            notifyAll();
        }

    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {

        for (int i = 2; i <= n; i = i + 2) {
            while (status != 1) {
                wait();
            }
            printNumber.accept(i);
            status = 0;
            notifyAll();
        }

    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <= n; i = i + 2) {
            while (status != 2) {
                wait();
            }
            printNumber.accept(i);
            status = 0;
            notifyAll();
        }
    }
}

//Solution 2 : Reentrant Lock
class ZeroEvenOdd {
    private int n;
    private volatile int status = 0;
    private ReentrantLock lock;
    private Condition zero;
    private Condition odd;
    private Condition even;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.lock = new ReentrantLock();
        this.zero = lock.newCondition();
        this.odd = lock.newCondition();
        this.even = lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        try {

            lock.lock();
            for (int i = 1; i <= n; i++) {
                while (status != 0) { // Put this while block for status inside for loop to maintain context of i = 0,
                                      // 1, 2, 3,....
                    zero.await();     // Becareful while calling await
                }
                printNumber.accept(0);
                status = (i % 2) + 1;     // 1 is even and 2 is odd.
                if (status == 1) {
                    even.signalAll();   // signal to respective even or odd.
                } else {
                    odd.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 2; i <= n; i = i + 2) {
                while (status != 1) {
                    even.await();     // even await
                }
                printNumber.accept(i);
                status = 0;
                zero.signalAll();   // signal to zero.
            }
        } finally {
            lock.unlock();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 1; i <= n; i = i + 2) {
                while (status != 2) {
                    odd.await();
                }
                printNumber.accept(i);
                status = 0;
                zero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}

//Solution 3 : Semaphore 
class ZeroEvenOdd {
    private int n;
    private volatile int status = 0;
    private Semaphore zero;
    private Semaphore odd;
    private Semaphore even;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.zero = new Semaphore(1);  // Be careful Semaphore of zero is 1. each even or odd is called, zero will be released.
        this.odd = new Semaphore(0); // first release(), then acquire() 
        this.even = new Semaphore(0); // first release(), then acquire() 
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <= n; i++) {
            try {
                zero.acquire();
                printNumber.accept(0);
                status = (i % 2) + 1; // 1 is even and 2 is odd.

            } finally {
                if (status == 1) {
                    even.release(); 
                } else {
                    odd.release();
                }
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        for (int i = 2; i <= n; i = i + 2) {

            try {
                even.acquire();
                printNumber.accept(i);
            } finally {
                zero.release();
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i = i + 2) {
            try {
                odd.acquire();
                printNumber.accept(i);
            } finally {
                zero.release();
            }
        }

    }
}

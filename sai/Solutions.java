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
/*
Why use the notifyAll() in enqueue() and dequeue() methods instead of the notify() method. 
Solution : 
Consider a situation with two producer threads and one consumer thread all working with a queue of size one. It's possible that when an item is added to the queue by one of the producer threads, 
the other two threads are blocked waiting on the condition variable. If the producer thread after adding an item invokes notify() it is possible that the other producer thread is chosen by the system 
to resume execution. The woken-up producer thread would find the queue full and go back to waiting on the condition variable, causing a deadlock. Invoking notifyAll() assures that the consumer thread 
also gets a chance to wake up and resume execution.
*/


// Solution 1 : Synchronized Block, here Queue is intialized with Array , not ArrayDeque(Very Important)
class BoundedBlockingQueue {

    private int queue[];
    private volatile int size; // declare size as volatile as it is accessed by many threads
    private int capacity;
    private int readPointer;//headPointer of the Queue
    private int writePointer; //tailPointer of the Queue

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*Question : 
1195. Fizz Buzz Multithreaded

You have the four functions:
- printFizz that prints the word "fizz" to the console,
- printBuzz that prints the word "buzz" to the console,
- printFizzBuzz that prints the word "fizzbuzz" to the console, and
- printNumber that prints a given integer to the console.
You are given an instance of the class FizzBuzz that has four functions: fizz, buzz, fizzbuzz and number. The same instance of FizzBuzz will be passed to four different threads:

"fizzbuzz" if i is divisible by 3 and 5,
"fizz" if i is divisible by 3 and not 5,
"buzz" if i is divisible by 5 and not 3, or
i if i is not divisible by 3 or 5.
  */
// SOlution 1 : Synchronized
class FizzBuzz {
    private int n;
    private int number = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {

        while (number <= n) {   //This condition is very Important. Use while for checking number <= n. inside check for number identiy.
            if (number % 3 == 0 && number % 5 != 0) {
                printFizz.run();
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (number <= n) {
            if (number % 3 != 0 && number % 5 == 0) {
                printBuzz.run();
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (number <= n) {
            if (number % 3 == 0 && number % 5 == 0) {
                printFizzBuzz.run();
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (number <= n) {
            if (number % 3 != 0 && number % 5 != 0) {
                printNumber.accept(number);
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }
}
// Solution 2 : Reeentrant Lock
 //Don't use this next condition(int mumber) - this causing Deadlock because numberCondition signalAll() is called before await().

class FizzBuzz {
    private int n;
    private int number = 1;
    private ReentrantLock lock;
    private Condition FizzCondition;
    private Condition BuzzCondition;
    private Condition FizzBuzzCondition;
    private Condition NumberCondition;

    public FizzBuzz(int n) {
        this.n = n;
        lock = new ReentrantLock();
        FizzBuzzCondition = lock.newCondition();
        BuzzCondition = lock.newCondition();
        FizzCondition = lock.newCondition();
        NumberCondition = lock.newCondition();
        
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        try {
            lock.lock();
            while (number <= n) {
                if (number % 3 == 0 && number % 5 != 0) {
                    printFizz.run();
                    number++;
                    BuzzCondition.signal();
                    FizzBuzzCondition.signal();
                    NumberCondition.signal();
                } else {
                    FizzCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        try {
            lock.lock();
            while (number <= n) {
                if (number % 3 != 0 && number % 5 == 0) {
                    printBuzz.run();
                    number++;
                    FizzCondition.signal();
                    FizzBuzzCondition.signal();
                    NumberCondition.signal();
                } else {
                    BuzzCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        try {
            lock.lock();

            while (number <= n) {
                if (number % 3 == 0 && number % 5 == 0) {
                    printFizzBuzz.run();
                    number++;
                    FizzCondition.signal();
                    BuzzCondition.signal();
                    NumberCondition.signal();
                } else {
                    FizzBuzzCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            while (number <= n) {
                if (number % 3 != 0 && number % 5 != 0) {
                    printNumber.accept(number);
                    number++;
                    FizzCondition.signal();
                    FizzBuzzCondition.signal();
                    BuzzCondition.signal();
                } else {
                    NumberCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /*private Condition nextCondition(int number) {   -- Don't use this next condition(int mumber) - this causing Deadlock because numberCondition
        if (number % 3 == 0 && number % 5 == 0) {     //  signalAll() is called before await().
            return FizzBuzzCondition;
        } else if (number % 3 == 0 && number % 5 != 0) {
            return FizzCondition;
        } else if (number % 3 != 0 && number % 5 == 0) {
            return BuzzCondition;
        } else {
            return NumberCondition;
        }
    }*/
}
//SOlution 3 : Semaphore Solution 

class FizzBuzz {
    private int n;
    private int number = 1;
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);
    private Semaphore num = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        while (number <= n) {
            if (number % 3 == 0 && number % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                number++;
                if (number <= n) {
                    releaseLock(number);
                }
            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

        while (number <= n) {
            if (number % 3 != 0 && number % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                number++;
                if (number <= n) {
                    releaseLock(number);
                }
            }
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

        while (number <= n) {
            if (number % 3 == 0 && number % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number++;
                if (number <= n) {
                    releaseLock(number);
                }
            }
        }

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

        while (number <= n) {
            if (number % 3 != 0 && number % 5 != 0) {
                num.acquire();
                printNumber.accept(number);
                number++;
                if (number <= n) {
                    releaseLock(number);
                }
            }
        }

    }

    public void releaseLock(int n) {
        if (n % 3 == 0 && n % 5 != 0) {
            fizz.release();
        } else if (n % 5 == 0 && n % 3 != 0) {
            buzz.release();
        } else if (n % 15 == 0) {
            fizzbuzz.release();
        } else {
            num.release();
        }
    }
}
// SOlution 4 : Cyclic Barrier
/*
In this solution we use a CyclicBarrier to hold each iteration of the while loop until each thread has an opportunity to execute. Once all four threads have executed, the Barrier is tripped, the counter increased, and all threads advance to the next iteration.

*/
class FizzBuzz {
    private int n;
    private CyclicBarrier cyclicBarrier;
    private int i;

    public FizzBuzz(int n) {
        this.n = n;
        cyclicBarrier = new CyclicBarrier(4, () -> i++); //Barrier tripped when all four threads call await(), which increases the counter
        i = 1;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while(i <= n){
            if(i % 3 == 0 && i % 5 != 0)
                printFizz.run();
            
            try{
                cyclicBarrier.await();
            }catch(BrokenBarrierException e){}
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(i <= n){
            if(i % 3 != 0 && i % 5 == 0)
                printBuzz.run();
            
            try{
                cyclicBarrier.await();
            }catch(BrokenBarrierException e){}
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(i <= n){
            if(i % 3 == 0 && i % 5 == 0)
                printFizzBuzz.run();
            
            try{
                cyclicBarrier.await();
            }catch(BrokenBarrierException e){}
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while(i <= n){
            if(i % 3 != 0 && i % 5 != 0)
                printNumber.accept(i);
            
            try{
                cyclicBarrier.await();
            }catch(BrokenBarrierException e){}
        }
    }
}


//Solution 1
/*
1) new ReentrantLock(true) creates a fair ReentrantLock, which guarantees that the lock will be granted to the longest-waiting thread when the lock becomes available.
2) Fair locking can be useful to prevent thread starvation, where some threads may be waiting indefinitely for a lock while other threads continually acquire and release the lock.
3) However, fair locking may introduce additional overhead compared to non-fair locking, as it requires more complex bookkeeping to maintain the order of waiting threads.

Step 1: Acquire the lock
Step 2a: Check if the road has a green light already (determined by currentRoadId)
Step 2b: If condition is false, turn light green and set currentRoadId to roadId
Step 3: Allow car to cross
Step 4: Release the lock
*/
class TrafficLight {
    private ReentrantLock lock;
    private int currentRoadId;

    public TrafficLight() {
        lock = new ReentrantLock(true);
        currentRoadId = 1; 
    }
    
    public void carArrived(
        int carId,           // ID of the car
        int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
        int direction,       // Direction of the car
        Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
        Runnable crossCar    // Use crossCar.run() to make car cross the intersection 
    ) {
        lock.lock();
        try{
            if (currentRoadId != roadId) { // If currentRoad not equal to previous road, turn the Green. For previous road, already green turned on.
                turnGreen.run(); // Road A -> 1 AND Road B -> 2
                currentRoadId = roadId;
            }
            crossCar.run();
        } finally {
            lock.unlock();
        }
        
    }
}
//=============================================================================================================================
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Question LRU Cache 
/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:
LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
*/
//Solution 1: DOubly Linked List and HashMap
class NormalLRUCache {
    int capacity;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    public NormalLRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }

        ListNode node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode oldListNode = map.get(key);
            remove(oldListNode);
        }

        ListNode node = new ListNode(key, value);
        map.put(key, node);
        add(node);

        if(map.size() > capacity) {
            ListNode ListNodeToBeDeleted = head.next;
            remove(ListNodeToBeDeleted);
            map.remove(ListNodeToBeDeleted.key);
        }
    }

    public void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

class ListNode{
    int key;
    int value;
    ListNode prev;
    ListNode next;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
//Solution 2: Synchronized
// SOlution 3: Reentrant Lock
// SOlution ReentrantReadWriteLock
// Solution 4 : ConcurrentHashMap is used for thread-safe put and get operations.
//ConcurrentLinkedDeque is used to maintain the order of nodes in the doubly linked list.
// Solution : LinkedHashMap LinkedHashMap, which is a hash map that maintains insertion order. It essentially implements the linked list for us in the same data structure as the hash map, with the add and remove methods built into the hash map operations.

/*LinkedHashMap<Integer, Integer> dic;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new LinkedHashMap<>(5, 0.75f, true) { // access order is true
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Question : File Search
//SOlution :
// FileSearchRunner
package org.concurrency.Questions.FileSearch.Imp1;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSearchRunner implements Runnable{
    private String fileToBeSearched;
    private BlockingQueue<File> directories;
    private AtomicBoolean isFileFound;

    public FileSearchRunner(String fileToBeSearched, BlockingQueue<File> directories, AtomicBoolean isFileFound) {
        this.fileToBeSearched = fileToBeSearched;
        this.directories = directories;
        this.isFileFound = isFileFound;
    }

    @Override
    public void run() {
        while(!directories.isEmpty() && !isFileFound.get()) {
            try{
                File file =directories.take();
                performRecursiveSearch(file, fileToBeSearched);
            } catch (InterruptedException ie) {
                System.out.println("Interrupt signal received");
                return;
            }
        }
    }

    public void performRecursiveSearch(File file, String fileToBeSearched) {
        File [] files = file.listFiles();

        if(files == null || files.length == 0) {
            return;
        }

        for(File eachFile : files) {
            if(isFileFound.get()) {
                return;
            }

            if(eachFile.isDirectory()) {
                performRecursiveSearch(eachFile, fileToBeSearched);
            } else {
                if(eachFile.getName().equals(fileToBeSearched)) {
                    System.out.println(" File found at path " + eachFile.getAbsolutePath());
                    isFileFound.set(true);
                    return;
                }
            }
        }
    }
}
// File Search Main
package org.concurrency.Questions.FileSearch.Imp1;


import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSearchMain {
    public static void main(String[] args) {
        File directory = new File("C:\\SaiPrograms\\");
        String fileNameToBeSearched = "SpringSecurityProjectTips";

        BlockingQueue<File> directories = new LinkedBlockingDeque<>();

        AtomicBoolean isFileFound = new AtomicBoolean(false);

        File[] allFilesInGivenDirectory = directory.listFiles();

        for(File file : allFilesInGivenDirectory) {
            if(file.isDirectory()) {
                directories.add(file);
            } else if(file.getName().equals(fileNameToBeSearched)){
                isFileFound.set(true);
                System.out.println("File found in path : " + file.getAbsolutePath());
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(12);

        for(int i=0;i < 12; i++) {
            executorService.submit(new FileSearchRunner(fileNameToBeSearched, directories, isFileFound));
        }


        executorService.shutdown();
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Question : Matrix Multiplication
//SOlution 1 : without multithreading
package org.concurrency.Questions.MatrixMultiplication.Imp1;

public class MatrixMultiplication {

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("Invalid dimensions for matrix multiplication");
        }

        int m = matrixA.length;  // rows in first matrix
        int n = matrixA[0].length; // columns in first matrix or rows in second matrix
        int p = matrixB[0].length; // columns in second matrix

        int[][] result = new int[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int[][] result = multiplyMatrices(matrixA, matrixB);

        // Print the result matrix
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Solution 2 : with multithreading 
package org.concurrency.Questions.MatrixMultiplication.Imp2;

public class MatrixMultiplication {
    private static final int NUM_THREADS = 4;

    static class MatrixMultiplier implements Runnable {
        private final int[][] matrixA;
        private final int[][] matrixB;
        private final int[][] result;
        private final int start;
        private final int end;

        public MatrixMultiplier(int[][] matrixA, int[][] matrixB, int[][] result, int start, int end) {
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.result = result;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int n = matrixA[0].length;
            int p = matrixB[0].length;

            for (int i = start; i < end; i++) {
                int row = i / p;
                int col = i % p;

                for (int j = 0; j < n; j++) {
                    result[row][col] += matrixA[row][j] * matrixB[j][col];
                }
            }
        }
    }

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("Invalid dimensions for matrix multiplication");
        }

        int m = matrixA.length;
        int n = matrixA[0].length;
        int p = matrixB[0].length;

        int[][] result = new int[m][p];

        Thread[] threads = new Thread[NUM_THREADS];
        int cellsPerThread = (m * p) / NUM_THREADS; // divide computation per thread

        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * cellsPerThread;  // 0 * 2   (3*3 = 9 elements== 9 / 4 = 2)
            int end = (i == NUM_THREADS - 1) ? m * p : (i + 1) * cellsPerThread;  // 1*2 =2
            threads[i] = new Thread(new MatrixMultiplier(matrixA, matrixB, result, start, end));
            threads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int[][] result = multiplyMatrices(matrixA, matrixB);

        // Print the result matrix
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
    30 24 18
    84 69 54
    138 114 90
     */
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Question : You have to transfer large files
//Solution :
/*
- Compression is required to reduce the amount of data to be transferred. Compress the file to use minimal network bandwidth.
- Deduplication may be required so that we don't need to send the same chunk multiple times. (data deduplication is a technique for eliminating duplicate copies of repeating data.
  Successful implementation of the technique can improve storage utilization.  It can also be applied to network data transfers to reduce the number of bytes that must be sent.)

- Whenever we have to transfer large files, the best way would be to send one big file in chunks.
 We can have a program which creates chunks of the original file, and we send the chunks.
  And at the receiver's end we will have some program which will basically collate the chunks into one single file.
  By sending the file by chunks, if any file chunk transfer fails, we will have to only transfer the failed chunk again, and not the entire file.
  Also we can spawn multiple threads to transfer multiple file parts in parallel.

  Or we can use a ThreadPool with a message queue, all the file transfer jobs will be added to queue by the program which generates the parts from original file.

- using a checksum to ensure data integrity while transferring the files
- including a chunk_id (or part number) in each chunk being transferred to keep track of received chunks at the receiver end
- Have an asynchronous setup to transfer chunks of the file.
- On the receiving end, devise a merge algorithm to stitch all chunks.
- in case of failures, receiver can request for a specific chunk_id. In case a chunk fails, we perform a retry strategy - depending on the HttpStatusCode from receiver's end.
*/
// LargeFileTransferAsyncWithChunkId

public class LargeFileTransferAsyncWithChunkId {
    private static final String DEST_IP = "10.10.12.14";
    private static final int PORT = 1234;
    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB
    private static int THREAD_POOL_SIZE = 10; //Number Of Threads

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try(Socket socket = new Socket(DEST_IP,PORT);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream("/path/to/source/largefile")){

            byte buffer[] = new byte[CHUNK_SIZE];
            long chunkId = 0;

            while(fis.available() > 0) {
                int bytesRead = fis.read(buffer);
                if(bytesRead == -1 ) {
                    break;
                }

                byte [] chunk = new byte[bytesRead];
                System.arraycopy(buffer, 0, chunk, 0, bytesRead);
                /*
                The line System.arraycopy(buffer, 0, chunk, 0, bytesRead); is used to copy data from one array (buffer) to another array (chunk) with the
                specified length (bytesRead). Here's what each parameter represents:

                buffer: The source array from which data will be copied.
                0: The starting position in the source array (buffer) from where the copying will begin.
                chunk: The destination array where the copied data will be placed.
                0: The starting position in the destination array (chunk) where the copied data will be placed.
                bytesRead: The number of bytes to be copied from the source array (buffer) to the destination array (chunk).
                                 */

                // Add chunkId to the beginning of the chunk
                byte[] chunkWithId = addChunkId(chunkId, chunk);

                // Submit chunk transfer task to thread pool
                executorService.submit(() -> {
                    try {
                        bos.write(chunkWithId);
                        bos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                chunkId++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static byte[] addChunkId(long chunkId, byte[] chunk) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeLong(chunkId);  // Write chunkId as long
            dos.write(chunk);       // Write chunk data
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
// LargeFileReceiverAsyncWithChunkId
package org.concurrency.Questions.LargeFileTransfer.Imp1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LargeFileReceiverAsyncWithChunkId {

    private static final int PORT = 12345;
    private static final int CHUNK_SIZE = 1024 * 1024; // 1 MB
    private static final int THREAD_POOL_SIZE = 10; // Number of threads

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
             BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
             FileOutputStream fos = new FileOutputStream("/path/to/destination/largefile")) {

            byte[] buffer = new byte[CHUNK_SIZE];
            long expectedChunkId = 0;

            while (true) {
                int bytesRead = bis.read(buffer, 0, buffer.length);
                if (bytesRead == -1) {
                    break;
                }

                // Extract chunkId and data from the received chunk
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                DataInputStream dis = new DataInputStream(bais);

                long receivedChunkId = dis.readLong();
                byte[] chunkData = new byte[bytesRead - Long.BYTES];
                dis.readFully(chunkData);

                if (receivedChunkId != expectedChunkId) {
                    System.err.println("Unexpected chunkId: " + receivedChunkId);
                    continue;
                }

                // Submit chunk assembly task to thread pool
                executorService.submit(() -> {
                    try {
                        fos.write(chunkData);
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                expectedChunkId++;
            }

            // Shutdown the executor service
            executorService.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*Question : Given a blackbox sendEmail() API, implement two APIs to book calendar and send notification to all the invites(email ids) at the same of when the event would occur
based on it's time of event and isRecurring flag. If isRecurring == true, repeat the send notification everyday at the same time else only send it once at the scheduled time.
addEvent(time, list of emails, message, isRecurring)
Eg.
["15:00:00", {"rty@dd.com", "wer@gg.com", "rwq@qq.com"}, "message1", true]
["15:00:01", {"rty@dd.com", "wer@gg.com"}, "message2", false]
sendEvent()  
*/


public class CalendarService {
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2); // careful here, don't use ExecutorService, use ScheduledExecutorService


    public void addEvent(String time, Set<String> emails, String message, boolean isRecurring) {
        LocalTime eventTime = LocalTime.parse(time);

        if(isRecurring) {
            scheduledExecutorService.scheduleAtFixedRate(() -> { sendEvent(emails, message);
                },
                    getInitialDelay(eventTime),24*60*60*1000, TimeUnit.MILLISECONDS); /// Be careful here, initial delay and delay shall be of same UNIT. For 24 hours, convert to milliseconds
        } else {
            scheduledExecutorService.schedule(()-> {
                sendEvent(emails, message);
            }, getInitialDelay(eventTime), TimeUnit.MILLISECONDS);
        }
    }

    public long getInitialDelay(LocalTime eventTime){
        return Duration.between(LocalTime.now(), eventTime).toMillis();
    }

    public void sendEvent(Set<String> emails, String message) {
        for(String email : emails) {
            sendEmail(email, message);
        }
    }

    // Implement the sendEmail() method using the blackbox API
    public void sendEmail(String email, String message) {
        System.out.println("Sending email to " + email + " with message : " + message);
    }

    public static void main(String[] args) {
        CalendarService calendarService = new CalendarService();
        // Add events
        calendarService.addEvent("15:01:00", Set.of("uvsaiswaroop@gmail.com","ganesh.shah@gmail.com"),"Hello", true);

        calendarService.addEvent("15:01:00", Set.of("uvsaiswaroop@gmail.com","ganesh.shah@gmail.com"),"Hi, join to attend Rubrik Webinar", false);

        try{
            Thread.sleep(10000); // Sleep for 24 hours
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            calendarService.scheduledExecutorService.shutdown();
        }

    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : Write Read Seek
/*
write(byte[] data, int len) writes the given data to the block-based storage.
read(byte[] data, int len) reads the specified number of bytes from the block-based storage into the provided buffer.
seek(int location) moves the current position to the specified byte in the block-based storage.

// Following APIs are provided by underneath block based Interface to you:
//  int bSeek(size_t blockNum);
//  int bWrite(void *blockPtr);
//  int bRead(void *blockPtr);
//  int bBlockSize();
//  Example input:
//   seek(12)  : seeks to x byte
//   write(data1, 15)
//   write(data2, 16)
//   seek(17)
//   read(data3, 2) : read 2 bytes from <seek> into data3

*/
//Solution
//============================
/*
 block = 8 bytes
 consider 64 blocks = 512 bytes

Take current Block and current Position
private final int BLOCK_SIZE = 8;
private final byte[] blocks = new byte[512];
 */
public class ByteIO {

    private final BlockIO blockIO;
    private int currentBlock;  // consider
    private int currentPosition;

    //private final int BLOCK_SIZE = 8;
    //private final byte[] blocks = new byte[512];

    public ByteIO(BlockIO blockIO) {
        this.blockIO = blockIO;
        this.currentBlock = 0;
        this.currentPosition = 0;
    }

    public int write(byte[] data, int len) {
        int totalWritten = 0;

        while (totalWritten < len) {
            int remainingInBlock = blockIO.bBlockSize() - currentPosition;
            int bytesToWrite = Math.min(remainingInBlock, len - totalWritten);

            byte[] blockData = blockIO.bRead(currentBlock);
            /* Replacement for blockIO.bRead(currentBlock) :
                    byte[] blockData = new byte[BLOCK_SIZE];
                    System.arraycopy(blocks, BLOCK_SIZE * currentBlock, blockData, 0, BLOCK_SIZE);
             */
            System.arraycopy(data, totalWritten, blockData, currentPosition, bytesToWrite);
            blockIO.bWrite(currentBlock, blockData);
            /*  Replacement for blockIO.bWrite(currentBlock, blockData) :
                    System.arraycopy(blockData, 0, blocks, BLOCK_SIZE * currentBlock, BLOCK_SIZE);
             */

            totalWritten += bytesToWrite;
            currentPosition += bytesToWrite;

            if (currentPosition == blockIO.bBlockSize()) { // BLOCK_SIZE
                currentBlock++;
                currentPosition = 0;
            }
        }

        return totalWritten;
    }

    public int read(byte[] data, int len) {
        int totalRead = 0;

        while (totalRead < len) {
            int remainingInBlock = blockIO.bBlockSize() - currentPosition;
            int bytesToRead = Math.min(remainingInBlock, len - totalRead);

            byte[] blockData = blockIO.bRead(currentBlock);
            /* Replacement for blockIO.bRead(currentBlock) :
                    byte[] blockData = new byte[BLOCK_SIZE];
                    System.arraycopy(blocks, BLOCK_SIZE * currentBlock, blockData, 0, BLOCK_SIZE);
             */
            System.arraycopy(blockData, currentPosition, data, totalRead, bytesToRead);

            totalRead += bytesToRead;
            currentPosition += bytesToRead;

            if (currentPosition == blockIO.bBlockSize()) {
                currentBlock++;
                currentPosition = 0;
            }
        }

        return totalRead;
    }

    public int seek(int location) {
        if (location < 0) {
            return -1;
        }

        currentBlock = location / blockIO.bBlockSize();
        currentPosition = location % blockIO.bBlockSize();

        return 0;
    }

    public static void main(String[] args) {
        BlockIO blockIO = new SimpleBlockIO();
        ByteIO byteIO = new ByteIO(blockIO);

        byte[] data1 = "Hello".getBytes();
        byte[] data2 = "World".getBytes();
        byte[] data3 = new byte[2];

        byteIO.seek(12);
        byteIO.write(data1, 5);
        byteIO.write(data2, 5);
        byteIO.seek(17);
        byteIO.read(data3, 2);

        System.out.println(new String(data3));  // Output: Wo
    }
}

class SimpleBlockIO implements BlockIO {
    private final int BLOCK_SIZE = 8;
    private final byte[] blocks = new byte[512];

    @Override
    public int bSeek(int blockNum) {
        if (blockNum < 0 || blockNum >= blocks.length / BLOCK_SIZE) {
            return -1;
        }
        return 0;
    }

    @Override
    public int bWrite(int blockNum, byte[] blockData) {
        System.arraycopy(blockData, 0, blocks, BLOCK_SIZE * blockNum, BLOCK_SIZE);
        return 0;
    }

    @Override
    public byte[] bRead(int blockNum) {
        byte[] blockData = new byte[BLOCK_SIZE];
        System.arraycopy(blocks, BLOCK_SIZE * blockNum, blockData, 0, BLOCK_SIZE);
        return blockData;
    }

    @Override
    public int bBlockSize() {
        return BLOCK_SIZE;
    }
}

interface BlockIO {
    int bSeek(int blockNum);
    int bWrite(int blockNum, byte[] blockData);
    byte[] bRead(int blockNum);
    int bBlockSize();
}
// Solution 2
// synchronized blocks are used to synchronize access to the shared resources (currentBlock and currentPosition), ensuring thread-safe access to these variables 
//and preventing race conditions.

// SOlution 3
// Reentrant Lock

//Solution 4
/*ReentrantReadWriteLock:
- ReentrantReadWriteLock to synchronize access to the shared resources (currentBlock and currentPosition).
- The read method uses a read lock, allowing multiple threads to read simultaneously but preventing writing while reading is in progress.
- The write method uses a write lock, ensuring that no other threads can read or write while a write operation is in progress.
  */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Question : implement thread-safe stack using a linkedlist. Push and pop should be O(1). This problem is pretty similar to Design Bounded Blocking Queue.
//Solution 1 : Implement Normally
public class StackUsingLinkedList {
  static class ListNode{
    int value;
    ListNode next;
    public ListNode(int value) {
        this.value = value;
    }

   }
    private ListNode top;
    private volatile int size = 0;

    public synchronized void push(int item){
        ListNode newNode = new ListNode(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public synchronized int pop(){
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is emoty");
        }

        int item = top.value;
        top = top.next;
        size--;
        return item;
    }

    public synchronized int peek(){
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is emoty");
        }

        int item = top.value;
        return item;
    }


    public synchronized  boolean isEmpty(){
        return top == null;
    }

    public synchronized int size(){
        return size;
    }

    public static void main(String[] args) {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
// SOlution 2 : By synchronizing these methods, we ensure that multiple threads can safely access and modify the stack without causing race conditions
// SOlution 3 : Use Reentrant Lock
// SOlution 4 : Use ReentrantReadWriteLock

// Solution 5 : Use Atomic Reference
/*
we can further enhance the performance of the thread-safe stack by using a non-blocking algorithm.
One such approach is to use the compare-and-swap (CAS) operation provided by java.util.concurrent.atomic.AtomicReference to atomically update the top reference without explicit locking.

 We use AtomicReference<Node<T>> named top to represent the top node of the stack.
- The push() method uses a do-while loop with compareAndSet() to atomically update the top reference.
- The pop() method also uses a do-while loop with compareAndSet() to atomically remove the top element from the stack.
- The peek() method simply retrieves the top element without removing it.
- The isEmpty() and size() methods do not require atomic operations as they only read the top reference without modifying it.
- This lock-free implementation leverages the atomic compareAndSet() operation to achieve thread safety without the need for explicit locking, thereby potentially improving performance in highly concurrent scenarios.
*/

public class StackUsingLLAtomic<T> {

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item){
            this.item = item;
        }
    }


    private final AtomicReference<Node<T>> top = new AtomicReference<>();

    public void push(T item) {
        Node<T> newNode = new Node<T>(item);
        Node<T> currentTop;

        do{
            currentTop = top.get();
            newNode.next = currentTop;
        } while (!top.compareAndSet(currentTop,newNode));


    }

    public T pop(){
        Node<T> currentTop;
        Node<T> newTop;

        do{
            currentTop = top.get();
            if(currentTop == null) {
                throw  new NoSuchElementException("Stack is Empty");
            }
            newTop = currentTop.next;
        }while (!top.compareAndSet(currentTop, newTop));

        return currentTop.item;
    }

    public T peek() {
        Node<T> currentTop = top.get();
        if (currentTop == null) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        return currentTop.item;
    }

    public boolean isEmpty() {
        return top.get() == null;
    }

    public int size() {
        int size = 0;
        Node<T> current = top.get();
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }


    public static void main(String[] args) {
        StackUsingLLAtomic<Integer> stack = new StackUsingLLAtomic<>();

        // Push elements onto the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Pop elements from the stack
        System.out.println(stack.pop());  // Output: 3
        System.out.println(stack.pop());  // Output: 2
        System.out.println(stack.pop());  // Output: 1
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : Implement a thread-safe data structure which can keep track of number of incoming requests grouped by IP Address over a time window. Add support for grouping by other attributes such as BrowserAgent.

//Solution 1 :  keep methods as synchronized
//Solution 2 : Use reentrant lock
//Solution 2 : Use reentrantreadwrite lock
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeBrowser {
    private HashMap<String, Integer> ipMap;
    private HashMap<String,Integer> browserAgentMap;

    private ReentrantReadWriteLock ipLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock browserAgentLock = new ReentrantReadWriteLock();

    public ThreadSafeBrowser(){
        ipMap = new HashMap<>();
        browserAgentMap = new HashMap<>();
    }


    public void incrementRequestCountByIp(String ipAddress) {
        ipLock.writeLock().lock();
        try{
            ipMap.put(ipAddress, ipMap.getOrDefault(ipAddress, 0) + 1);
        } finally {
            ipLock.writeLock().unlock();
        }
    }

    public void incrementRequestCountByBrowserAgent(String browserAgent) {
        browserAgentLock.writeLock().lock();
        try{
            browserAgentMap.put(browserAgent, browserAgentMap.getOrDefault(browserAgent, 0) + 1);
        } finally {
            browserAgentLock.writeLock().unlock();
        }
    }

    public int getRequestCountByIp(String ipAddress) {
        ipLock.readLock().lock();
        try {
            {
                return ipMap.getOrDefault(ipAddress,0);
            }
        } finally {
            ipLock.readLock().unlock();
        }
    }

    public int getRequestCountByBrowserAgent(String browserAgent) {
        browserAgentLock.readLock().lock();
        try {
            return browserAgentMap.getOrDefault(browserAgent, 0);
        } finally {
            browserAgentLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ThreadSafeBrowser counter = new ThreadSafeBrowser();

        // Simulate incoming requests
        for (int i = 0; i < 10; i++) {
            counter.incrementRequestCountByIp("192.168.1.1");
            counter.incrementRequestCountByBrowserAgent("Chrome");
        }

        for (int i = 0; i < 5; i++) {
            counter.incrementRequestCountByIp("192.168.1.2");
            counter.incrementRequestCountByBrowserAgent("Firefox");
        }

        // Print request counts
        System.out.println("Requests from IP Address 192.168.1.1: " + counter.getRequestCountByIp("192.168.1.1"));
        System.out.println("Requests from IP Address 192.168.1.2: " + counter.getRequestCountByIp("192.168.1.2"));
        System.out.println("Requests from Browser Agent Chrome: " + counter.getRequestCountByBrowserAgent("Chrome"));
        System.out.println("Requests from Browser Agent Firefox: " + counter.getRequestCountByBrowserAgent("Firefox"));
    }
}
// Solutin 4 :
//  custom ConcurrentHashMap

public class RequestCounterCustomMap {

    private static final int DEFAULT_CAPACITY = 16;
    private final Entry[] ipEntries;
    private final Entry[] browserAgentEntries;
    private final ReentrantReadWriteLock[] ipLocks;
    private final ReentrantReadWriteLock[] browserAgentLocks;

    public RequestCounterCustomMap() {
        this.ipEntries = new Entry[DEFAULT_CAPACITY];
        this.browserAgentEntries = new Entry[DEFAULT_CAPACITY];
        this.ipLocks = new ReentrantReadWriteLock[DEFAULT_CAPACITY];
        this.browserAgentLocks = new ReentrantReadWriteLock[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            this.ipLocks[i] = new ReentrantReadWriteLock();
            this.browserAgentLocks[i] = new ReentrantReadWriteLock();
        }
    }

    public void incrementRequestCountByIp(String ipAddress) {
        int hash = hash(ipAddress.hashCode());
        int index = hash % ipEntries.length;
        ipLocks[index].writeLock().lock();
        try {
            Entry current = ipEntries[index];
            while (current != null && !current.key.equals(ipAddress)) {
                current = current.next;
            }
            if (current != null) {
                current.count++;
            } else {
                addEntry(ipAddress, index, ipEntries);
            }
        } finally {
            ipLocks[index].writeLock().unlock();
        }
    }


    public int getRequestCountByIp(String ipAddress) {
        int hash = hash(ipAddress.hashCode());
        int index = hash % ipEntries.length;
        ipLocks[index].readLock().lock();
        try {
            Entry current = ipEntries[index];
            while (current != null && !current.key.equals(ipAddress)) {
                current = current.next;
            }
            return current != null ? current.count : 0;
        } finally {
            ipLocks[index].readLock().unlock();
        }
    }

    private int hash(int hashCode) {
        return hashCode & 0x7fffffff; // Ensure positive hash value
    }

    private static class Entry {
        String key;
        int count;
        Entry next;

        Entry(String key) {
            this.key = key;
            this.count = 1;
        }
    }

    public static void main(String[] args) {
        RequestCounterCustomMap counter = new RequestCounterCustomMap();

        // Simulate incoming requests
        for (int i = 0; i < 10; i++) {
            counter.incrementRequestCountByIp("192.168.1.1");
            counter.incrementRequestCountByBrowserAgent("Chrome");
        }

        for (int i = 0; i < 5; i++) {
            counter.incrementRequestCountByIp("192.168.1.2");
            counter.incrementRequestCountByBrowserAgent("Firefox");
        }

        // Print request counts
        System.out.println("Requests from IP Address 192.168.1.1: " + counter.getRequestCountByIp("192.168.1.1"));
        System.out.println("Requests from IP Address 192.168.1.2: " + counter.getRequestCountByIp("192.168.1.2"));
        System.out.println("Requests from Browser Agent Chrome: " + counter.getRequestCountByBrowserAgent("Chrome"));
        System.out.println("Requests from Browser Agent Firefox: " + counter.getRequestCountByBrowserAgent("Firefox"));
    }
}
// Solution 5 : concurrent hashma

public class RequestCounterConcurrentMap {
    private final ConcurrentHashMap<String, Integer> ipRequestCounts = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> browserAgentRequestCounts = new ConcurrentHashMap<>();

    public void incrementRequestCountByIp(String ipAddress) {
        ipRequestCounts.compute(ipAddress, (k, v) -> (v == null) ? 1 : v + 1);
    }

    public void incrementRequestCountByBrowserAgent(String browserAgent) {
        browserAgentRequestCounts.compute(browserAgent, (k, v) -> (v == null) ? 1 : v + 1);
    }

    public int getRequestCountByIp(String ipAddress) {
        return ipRequestCounts.getOrDefault(ipAddress, 0);
    }

    public int getRequestCountByBrowserAgent(String browserAgent) {
        return browserAgentRequestCounts.getOrDefault(browserAgent, 0);
    }

    public static void main(String[] args) {
        RequestCounterConcurrentMap counter = new RequestCounterConcurrentMap();

        // Simulate incoming requests
        for (int i = 0; i < 10; i++) {
            counter.incrementRequestCountByIp("192.168.1.1");
            counter.incrementRequestCountByBrowserAgent("Chrome");
        }

        for (int i = 0; i < 5; i++) {
            counter.incrementRequestCountByIp("192.168.1.2");
            counter.incrementRequestCountByBrowserAgent("Firefox");
        }

        // Print request counts
        System.out.println("Requests from IP Address 192.168.1.1: " + counter.getRequestCountByIp("192.168.1.1"));
        System.out.println("Requests from IP Address 192.168.1.2: " + counter.getRequestCountByIp("192.168.1.2"));
        System.out.println("Requests from Browser Agent Chrome: " + counter.getRequestCountByBrowserAgent("Chrome"));
        System.out.println("Requests from Browser Agent Firefox: " + counter.getRequestCountByBrowserAgent("Firefox"));
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Question : Implement a threadsafe queue
/*
- enqueue() method adds an item to the end of the queue.
- dequeue() method removes and returns the item at the front of the queue.
- isEmpty() method checks if the queue is empty.
  */

public class ImplementQueueUsingLL<T> {
    private Node<T> head;
    private Node<T> tail;
    private final ReentrantLock lock = new ReentrantLock();

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);

        lock.lock();
        try {
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() {
        lock.lock();
        try {
            if (head == null) {
                throw new NoSuchElementException("Queue is empty");
            }
            T item = head.data;
            head = head.next;

            if (head == null) {
                tail = null;
            }
            return item;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return head == null;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ImplementQueueUsingLL<Integer> queue = new ImplementQueueUsingLL<>();

        // Enqueue some items
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
        }

        // Dequeue and print items
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }

        // Try to dequeue from an empty queue
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty");
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : how to write a concurrent thread safe List data structure with constant time operations.
//Solution : The add, remove, contains, size, and get operations have a time complexity of O(n) in the worst-case scenario because we need to traverse the list to perform these operations.
public class ConcurrentList<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private final ReentrantLock lock = new ReentrantLock();
    private Node<T> head;
    private Node<T> tail;

    public ConcurrentList() {
        head = new Node<>(null); // dummy head
        tail = new Node<>(null); // dummy tail
        head.next = tail;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        lock.lock();
        try {
            newNode.next = tail;
            Node<T> prev = head;
            while (prev.next != tail) {
                prev = prev.next;
            }
            prev.next = newNode;
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(T data) {
        lock.lock();
        try {
            Node<T> prev = head;
            Node<T> current = head.next;
            while (current != tail) {
                if (current.data.equals(data)) {
                    prev.next = current.next;
                    return true;
                }
                prev = current;
                current = current.next;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(T data) {
        lock.lock();
        try {
            Node<T> current = head.next;
            while (current != tail) {
                if (current.data.equals(data)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            int count = 0;
            Node<T> current = head.next;
            while (current != tail) {
                count++;
                current = current.next;
            }
            return count;
        } finally {
            lock.unlock();
        }
    }

    public T get(int index) {
        lock.lock();
        try {
            if (index < 0) {
                return null;
            }
            Node<T> current = head.next;
            for (int i = 0; i < index; i++) {
                if (current == tail) {
                    return null;
                }
                current = current.next;
            }
            return current.data;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConcurrentList<String> list = new ConcurrentList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("Contains B: " + list.contains("B"));  // true

        list.remove("B");

        System.out.println("Contains B after removal: " + list.contains("B"));  // false
        System.out.println("Size: " + list.size());  // 2
        System.out.println("Element at index 1: " + list.get(1));  // C
    }
}
//Solution 2 : One approach is to use a combination of a hash table and a linked list to achieve the desired complexity.
/*
 - ReentrantLock is used to provide thread-safe access to the shared data structure.
 - HashMap is used to maintain a mapping of the object's identity hash code to its corresponding node in the linked list.
 - Constant-time operations are achieved by maintaining the head and tail sentinel nodes and using the hash map for quick access to the nodes.

Note: This implementation uses System.identityHashCode(data) to generate the hash code of the data objects.
This is used to uniquely identify objects and is used as a key in the hash map.*/
public class ConcurrentList<T> {

    private final Map<Integer, Node<T>> indexMap;
    private final ReentrantLock lock = new ReentrantLock();
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    public ConcurrentList() {
        this.indexMap = new HashMap<>();
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
    }

    public void add(T data) {
        lock.lock();
        try {
            Node<T> newNode = new Node<>(data);
            tail.prev.next = newNode;
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev = newNode;
            indexMap.put(System.identityHashCode(data), newNode);
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(T data) {
        lock.lock();
        try {
            Node<T> node = indexMap.get(System.identityHashCode(data));
            if (node != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                indexMap.remove(System.identityHashCode(data));
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(T data) {
        lock.lock();
        try {
            return indexMap.containsKey(System.identityHashCode(data));
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return indexMap.size();
        } finally {
            lock.unlock();
        }
    }

    public T get(int index) {
        lock.lock();
        try {
            Node<T> current = head.next;
            for (int i = 0; i < index; i++) {
                if (current == tail) {
                    return null;
                }
                current = current.next;
            }
            return current.data;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConcurrentList<String> list = new ConcurrentList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("Contains B: " + list.contains("B"));  // true

        list.remove("B");

        System.out.println("Contains B after removal: " + list.contains("B"));  // false
        System.out.println("Size: " + list.size());  // 2
        System.out.println("Element at index 1: " + list.get(1));  // C
    }
}
//Solution 3 : 
/*
 We use a ConcurrentHashMap to store the elements of the list and a ConcurrentLinkedQueue to maintain the order of the elements.
- The add, remove, contains, and size methods are constant time operations, as they operate directly on the ConcurrentHashMap and ConcurrentLinkedQueue.
- The get method has a time complexity of O(n) in the worst-case scenario because we use stream().skip(index).findFirst() to get the element at a given index.
   */

public class ConcurrentList<T> {

    private final ConcurrentHashMap<T, Boolean> map;
    private final ConcurrentLinkedQueue<T> queue;

    public ConcurrentList() {
        this.map = new ConcurrentHashMap<>();
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public void add(T data) {
        if (map.putIfAbsent(data, Boolean.TRUE) == null) {
            queue.offer(data);
        }
    }

    public boolean remove(T data) {
        if (map.remove(data) != null) {
            queue.remove(data);
            return true;
        }
        return false;
    }

    public boolean contains(T data) {
        return map.containsKey(data);
    }

    public int size() {
        return map.size();
    }

    public T get(int index) {
        if (index < 0 || index >= map.size()) {
            return null;
        }
        return queue.stream().skip(index).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        ConcurrentList<String> list = new ConcurrentList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("Contains B: " + list.contains("B"));  // true

        list.remove("B");

        System.out.println("Contains B after removal: " + list.contains("B"));  // false
        System.out.println("Size: " + list.size());  // 2
        System.out.println("Element at index 1: " + list.get(1));  // C
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : 1226. The Dining Philosophers
// Solution : 
// Philospher
package org.concurrency.Questions.DiningPhilosphers.Imp1Baeldung;

public class Philosopher implements Runnable {

    private Object leftFork ;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try{
            while (true) {
                doAction(System.nanoTime() + "thinking");
                synchronized (leftFork) {
                    doAction(System.nanoTime() + " Picked up left Fork");
                    synchronized (rightFork) {
                        doAction(System.nanoTime() + "Picked up right Fork - eating");
                        doAction(System.nanoTime() + "Picked down right Fork ");
                    }
                    doAction(System.nanoTime() + " Picked down left Fork. Back to thinking.");
                }
            }

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((int)(Math.random()*100));

    }
}
//DiningPhilosphers
package org.concurrency.Questions.DiningPhilosphers.Imp1Baeldung;

import java.util.ArrayList;

public class DiningPhilosphers {
    public static void main(String[] args) {
        Philosopher [] philosophers = new Philosopher[5];

        Object[] forks = new Object[philosophers.length];
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < philosophers.length; i++) {
            forks[i] = new Object();
        }

        for(int i = 0; i < philosophers.length ; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i+1) % forks.length];

            //philosophers[i] = new Philosopher(leftFork, rightFork);
            // The last philosopher picks up the right fork first
            if(i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads.add(t);
            t.start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(Thread thread : threads) {
            thread.interrupt();
        }
    }
}
//Leetcode Solution
// Efficient - To avoid deadlock, ask 1 philospher to right fork first and other 4 philosphers to take left fork.
// May be have chance to acquire to 2 people eat a time.

class DiningPhilosophers {
    Object forks[] = new Object[5];

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new Object();
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
            Runnable pickLeftFork,
            Runnable pickRightFork,
            Runnable eat,
            Runnable putLeftFork,
            Runnable putRightFork) throws InterruptedException {
        Object left = forks[philosopher];
        Object right = forks[(philosopher + 1) % 5];
        if(philosopher == 1) {
             synchronized (right) {
            synchronized (left) {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }
        } else {
             synchronized (left) {
            synchronized (right) {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }
        }

    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : Unisex Bathroom problem
// A bathroom is being designed for the use of both males and females in an office but requires the following constraints to be maintained: There cannot be men and women in the bathroom at the same time.
//There should never be more than three employees in the bathroom simultaneously. The solution should avoid deadlocks. For now, though, don’t worry about starvation.
// Solution : 
package org.concurrency.Questions.UniSexBathroom.Imp1;

import java.util.concurrent.Semaphore;

public class UniSexBathroom1 {
    private String WOMEN = "women";
    private String MEN = "men";
    private String NONE = "none";

    String inUseBy = NONE;
    private int employeesInBathroom = 0;

    Semaphore maxEmployees = new Semaphore(3);
    public void useBathroom(String name) {
        System.out.println(name + " using Bathromm, Current employees in bathroom : " + employeesInBathroom);
        try{
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " done using Bathromm");
    }

    public void maleUseBathroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals(WOMEN)) {
                this.wait();
            }
            //maxEmployees.acquire();
            employeesInBathroom++;
            inUseBy = MEN;
        }
        maxEmployees.acquire();

        useBathroom(name);
        maxEmployees.release();

        synchronized (this) {
            employeesInBathroom--;

            if(employeesInBathroom == 0){
                inUseBy = NONE;
            }
            this.notifyAll();
        }
    }

    public void femaleUseBathroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals(MEN)) {
                this.wait();
            }
            maxEmployees.acquire();
            employeesInBathroom++;
            inUseBy = WOMEN;
        }

        useBathroom(name);
        maxEmployees.release();

        synchronized (this) {
            employeesInBathroom--;

            if(employeesInBathroom == 0){
                inUseBy = NONE;
            }
            this.notifyAll();
        }
    }

    public static void main( String args[] ) throws InterruptedException {
        final UniSexBathroom1 unisexBathroom = new UniSexBathroom1();
        Thread female1 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.femaleUseBathroom("Lisa");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male1 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("John");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male2 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Bob");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male3 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Anil");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male4 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Wentao");
                } catch (InterruptedException ie) {
                }
            }
        });
        female1.start();
        male1.start();
        male2.start();
        male3.start();
        male4.start();
        female1.join();
        male1.join();
        male2.join();
        male3.join();
        male4.join();
    }
}
// Question : Design Semaphore
// Solution 1 :
/*
acquire(): Decrements the semaphore's permit count. If the permit count is zero, the calling thread will block until a permit is available.
release(): Increments the semaphore's permit count and wakes up a waiting thread if any.
availablePermits(): Returns the current number of permits available.
 */

public class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        if (permits < 0) {
            throw new IllegalArgumentException("Number of permits cannot be negative");
        }
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits <= 0) {
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notify();
    }

    public synchronized int availablePermits() {
        return permits;
    }
}
// Main
package org.concurrency.Questions.DesignSemaphore.Imp1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);

// Create three threads that will try to acquire permits.
        Thread thread1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 1 acquired a permit.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 2 acquired a permit.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 3 acquired a permit.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 4 acquired a permit.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

// Start the threads.
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

// Wait for all threads to finish.
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}

// Solution 2 : Using Reentrant Lock
public class SemaphoreUsingLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int permits;

    public SemaphoreUsingLock(int permits) {
        if (permits < 0) {
            throw new IllegalArgumentException("Number of permits cannot be negative");
        }
        this.permits = permits;
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                condition.await();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            permits++;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public int availablePermits() {
        lock.lock();
        try {
            return permits;
        } finally {
            lock.unlock();
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : Implement or Design Custom Lock or Mutex (Mutual Exclusion)
/*
In Java, a mutex (short for "mutual exclusion") is often implemented using a ReentrantLock from the java.util.concurrent.locks package. 
A ReentrantLock is a synchronization primitive that works similarly to a synchronized block but offers more flexibility and control.
The criticalSection() method represents a critical section of code that you want to be accessed by only one thread at a time.
The ReentrantLock is used to acquire and release the lock around the critical section.

in the context of synchronization in Java, a ReentrantLock serves the same purpose as a mutex. Both are used to achieve mutual exclusion, ensuring that only one thread can execute a critical section of code at a time.

Here are a few distinctions and characteristics of ReentrantLock:
1. Reentrancy: ReentrantLock is reentrant, meaning a thread can acquire the lock multiple times without blocking itself, as long as it releases the lock the same number of times.
2. Condition Support: ReentrantLock provides additional features like support for condition variables, which can be used to wait for a certain condition to be met.
3. Interruptible Locking: The lock acquisition methods can be interrupted by other threads.
4. Fairness: ReentrantLock can be configured to provide a fair lock, ensuring that the longest-waiting thread gets the lock when it becomes available.
*/
// Solution 1 : Using Synchronized
public class CustomLock {

    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = callingThread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;

            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }

    public synchronized boolean tryLock() {
        if (!isLocked) {
            isLocked = true;
            lockedCount++;
            lockedBy = Thread.currentThread();
            return true;
        }
        return false;
    }

    public synchronized boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long endTime = System.currentTimeMillis() + unit.toMillis(time);
        long remainingTime = time;

        while (!tryLock()) {
            wait(remainingTime);

            remainingTime = endTime - System.currentTimeMillis();
            if (remainingTime <= 0) {
                return false;
            }
        }
        return true;
    }

    public synchronized void lockInterruptibly() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        lock();
    }

    public synchronized Condition newCondition() {
        return null;
        //return new ConditionObject();
    }

    public synchronized void lockWhen(Predicate<Void> predicate) throws InterruptedException {
        lock();
        try {
            while (!predicate.test(null)) {
                wait();
            }
        } finally {
            unlock();
        }
    }

    public synchronized void signalAll() {
        notifyAll();
    }
}
// Solution 2 : Using Reentrant Lock

public class CustomLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isLocked = false;

    public void lock() {
        lock.lock();
        try {
            while (isLocked) {
                condition.await();
            }
            isLocked = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void unlock() {
        lock.lock();
        try {
            isLocked = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void lockWhen(Predicate<Void> predicate) throws InterruptedException {
        lock();
        try {
            while (!predicate.test(null)) {
                condition.await();
            }
        } finally {
            unlock();
        }
    }

    public static void main(String[] args) {
        CustomLock customLock = new CustomLock();

        // Example usage
        try {
            customLock.lock();
            System.out.println("Lock acquired");

            // Do some work

        } finally {
            customLock.unlock();
            System.out.println("Lock released");
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : Design SequenceGenerator
// Solution :
public class SequenceGenerator {
    private final AtomicInteger counter = new AtomicInteger(0);
  
    public int getNext() {
        return counter.incrementAndGet();
    }

    public static void main(String[] args) {
        SequenceGenerator generator = new SequenceGenerator();

        // Generate some sequence numbers
        for (int i = 0; i < 10; i++) {
            System.out.println("Next sequence number: " + generator.getNext());
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Question : Singleton Design Pattern
/*
Singleton DP :
Ensures that Maximum only 1 object of a class is created EVER.
When ?
1. Shared Resource : A class whose all objects have to share a common resource Ex : Database object - Database database = new Database(url,username, password);
2. Creation of object is expensive (takes lot of time and memory)
3. Stateless (No attributes) - No difference between all objects
4. One object to be present across application (config file)

1. Normal method
 */
public class Database1 {
    private static Database1 Database1Instance = null; // needs to static attribute
    private Database1() {

    }
    public static Database1 getDatabase1Instance(){ // static method can only access static attributes
        if (Database1Instance == null) {
            Database1Instance =  new Database1();
        }
        return Database1Instance;
    }
}
/*
method 2 :
When 2 different classes trying to call Database.getInstance() at same time from different threads at this line : Database1Instance =  new Database2();

Solution : lazy loading with synchronized method
cons : synchronized is actually needed when the object is created first time. Later,
It hits performance
 */
public class Database2 {
    private static Database2 Database1Instance = null;
    private Database2() {

    }
    public synchronized static Database2 getDatabase1Instance(){ // add synchronized
        if (Database1Instance == null) {
            Database1Instance =  new Database2();
        }
        return Database1Instance;
    }
}
/*
method 3 :
When 2 different classes trying to call Database.getInstance() at same time from different threads at this line : Database1Instance =  new Database2();

Solution : eager loading
object created at start of the application

cons :
1. will slow down startup of application
2. Memory cost even if object is never needed
3. parameters in constructor created at runtime, then can't pass them at startup.
 */

public class Database3 {
    private static Database3 Database1Instance = new Database3();
    private Database3() {

    }
    public  static Database3 getDatabase1Instance(){ // remove synchronized
        return Database1Instance;
    }
}
/*
method 4 :
When 2 different classes trying to call Database.getInstance() at same time from different threads at this line : Database1Instance =  new Database2();

Solution : lazy loading with synchronized block

cons :
1. it is same as using synchronized method.
 */


public class Database4 {
    private static Database4 Database1Instance = null;
    private Database4() {

    }
    public static Database4 getDatabase1Instance(){ // add synchronized block
        synchronized (Database4.class) {            // careful here
            if(Database1Instance == null) {
                Database1Instance = new Database4();
            }
        }
        return Database1Instance;
    }
}
/*
method 5 :
When 2 different classes trying to call Database.getInstance() at same time from different threads at this line : Database1Instance =  new Database2();

Solution : Double check locking (lazy loading with synchronized block)

pros : only one check for first time object is created
 */
public class Database5 {
    private static Database5 Database1Instance = null;
    private Database5() {

    }
    public static Database5 getDatabase1Instance(){
        // careful here
        if(Database1Instance == null) {
            synchronized (Database5.class){      // add synchronized block inside if null check
                if(Database1Instance == null) {
                    Database1Instance = new Database5();
                }
            }
        }

        return Database1Instance;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*Question : Imagine at the end of a political conference, republicans and democrats are trying to leave the venue and ordering Uber rides at the same time.
However, to make sure no fight breaks out in an Uber ride, the software developers at Uber come up with an algorithm whereby either an Uber ride can have all democrats or republicans or two Democrats and two Republicans. All other combinations can result in a fist-fight.
Your task as the Uber developer is to model the ride requestors as threads. Once an acceptable combination of riders is possible, threads are allowed to proceed to ride. Each thread invokes the method seated() when selected by the system for the next ride. When all the threads are
seated, any one of the four threads can invoke the method drive() to inform the driver to start the ride.*/

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberSeatingProblem {

    private int republicans = 0;
    private int democrats = 0;

    private Semaphore demsWaiting  = new Semaphore(0);
    private Semaphore repubsWaiting = new Semaphore(0);

    CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
    ReentrantLock lock = new ReentrantLock();

    public void drive() {
        System.out.println("Uber ride is on way... with ride leader " + Thread.currentThread().getName());
    }

    public void seatDemocrat() throws InterruptedException, BrokenBarrierException {
        boolean rideLeader = false;
        lock.lock();

        democrats++;

        if(democrats == 4) {
            //Seat all democrats in Ubder ride
            demsWaiting.release(3);
            democrats = democrats - 4;
            rideLeader = true;
        } else if(democrats == 2 && republicans >= 2) {
            // Seat 2 democrats and 2 republicans
            demsWaiting.release(1);
            repubsWaiting.release(2);
            democrats = democrats - 2;
            republicans = republicans - 2;
            rideLeader = true;

        } else {
            lock.unlock();
            demsWaiting.acquire();
        }

        seated();
        cyclicBarrier.await();

        if(rideLeader) {
            drive();
            lock.unlock();
        }
    }

    void seated() { // will call once they seat in car
        System.out.println( Thread.currentThread().getName() + " seated");
    }

    public void seatRepulicam() throws InterruptedException, BrokenBarrierException {
        boolean rideLeader = false;
        lock.lock();

        republicans++;

        if(republicans == 4) {
            //Seat all republicans in Ubder ride
            repubsWaiting.release(3);
            republicans = republicans - 4;
            rideLeader = true;
        } else if(republicans == 2 && democrats >= 2) {
            // Seat 2 democrats and 2 republicans
            repubsWaiting.release(1);
            demsWaiting.release(2);
            democrats = democrats - 2;
            republicans = republicans - 2;
            rideLeader = true;

        } else {
            lock.unlock();
            repubsWaiting.acquire();
        }

        seated();
        cyclicBarrier.await();

        if(rideLeader) {
            drive();
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UberSeatingProblem uberSeatingProblem = new UberSeatingProblem();
        Set<Thread> allThreads = new HashSet<>();


        for(int i=0; i < 10;i++) {
            Thread t =  new Thread(() -> {
                try {
                    uberSeatingProblem.seatDemocrat();
                } catch (InterruptedException | BrokenBarrierException e) {
                    System.out.println("problem occured");
                    throw new RuntimeException(e);
                }
            });
            t.setName("Democrat " + (i+1));
            allThreads.add(t);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(int i=0; i < 14;i++) {
            Thread t =  new Thread(() -> {
                try {
                    uberSeatingProblem.seatRepulicam();
                } catch (InterruptedException | BrokenBarrierException e) {
                    System.out.println("problem occured");
                    throw new RuntimeException(e);
                }
            });
            t.setName("Republican " + (i+1));
            allThreads.add(t);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(Thread thread : allThreads) {
            thread.start();
        }


        for(Thread thread : allThreads) {
            thread.join();
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Question : Design Rate limiter with Token Bucket Filter
//Solution : 
/*
The key to the problem is to find a way to track the number of available tokens when a consumer requests for a token. Note the rate at which the tokens are being generated is constant.
*/
import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilter {
    private int MAX_TOKENS ;

    private int possibleTokens = 0;
    private long lastRequestTime = System.currentTimeMillis();

    public TokenBucketFilter(int maxTokens) {
        this.MAX_TOKENS = maxTokens;
    }

    public synchronized void getToken() throws InterruptedException {
        possibleTokens += (System.currentTimeMillis() - lastRequestTime) /1000;

        if(possibleTokens> MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        if(possibleTokens == 0) {
            Thread.sleep(1000);
        } else {
            possibleTokens--;
        }

        lastRequestTime = System.currentTimeMillis();
        System.out.println("Granting " + Thread.currentThread().getName() + " token at "+ System.currentTimeMillis());
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(5);

        Set<Thread> threadSet = new HashSet<>();

        Thread.sleep(6000);

        for(int i = 0 ; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    tokenBucketFilter.getToken();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setName("Thread_"+(i + 1));
            threadSet.add(thread);
        }

        for(Thread thread : threadSet) {
            thread.start();
        }

        for(Thread thread : threadSet) {
            thread.join();
        }
    }
}


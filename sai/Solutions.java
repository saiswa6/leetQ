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

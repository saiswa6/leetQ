//Barrier may be the easiest way to solve it.

/*
Number of Phases:
CyclicBarrier: It is designed to synchronize a fixed number of threads at a barrier point. Once the specified number of threads arrive at the barrier, they are allowed to proceed together.
Phaser: It can synchronize a dynamic number of threads into multiple phases. You can add or remove threads dynamically during the execution, and threads can synchronize at different phases.

Dynamic Thread Management:
CyclicBarrier: The number of participating threads is fixed when the barrier is created. If you want to add or remove threads, you need to recreate the CyclicBarrier.
Phaser: You can dynamically register and deregister threads using methods like register() and arriveAndDeregister(). This makes it more flexible for scenarios where the number of participating threads may change over time.

Phasing:
CyclicBarrier: It primarily focuses on a single synchronization point. Once threads pass the barrier, they are free to proceed independently until the next synchronization point.
Phaser: It supports multiple phases. Threads can synchronize at various points within the same Phaser object, allowing for more complex coordination patterns.


CyclicBarrier also works. CyclicBarrier is a simpler version of Phaser.
*/

class H2O {

    Semaphore hydrogenSemaphore ;
    Semaphore oxygenSemaphore ;
    private Phaser phaser;

    public H2O() {
        hydrogenSemaphore = new Semaphore(2);
        oxygenSemaphore = new Semaphore(1);
        phaser = new Phaser(3);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();  
        releaseHydrogen.run();
        phaser.arriveAndAwaitAdvance();  
        hydrogenSemaphore.release();

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
		releaseOxygen.run();
        phaser.arriveAndAwaitAdvance();
        oxygenSemaphore.release();
    }
}


// Phaser is more complicated version of locking mechanism: allows you to have functionality of cyclic barrier and semaphore at the same time. 
import java.util.concurrent.*;

class H2O {
    
    private final CyclicBarrier barrier = new CyclicBarrier(3);
    private final Semaphore hSem = new Semaphore(2);
    private final Semaphore oSem = new Semaphore(1);

    public H2O() {
        
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		try {
            hSem.acquire();
            
             // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            barrier.await();
        } catch(Exception ignore) {
            
        } finally {
            hSem.release();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        try {
            oSem.acquire();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            barrier.await();
        } catch(Exception ignore) {
            
        } finally {
            oSem.release();
        }
    }
}
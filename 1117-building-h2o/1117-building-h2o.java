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
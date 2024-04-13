class H2O {

    Semaphore hydrogenSemaphore;
    Semaphore oxygenSemaphore;
    private CyclicBarrier barrier;

    public H2O() {
        hydrogenSemaphore = new Semaphore(2);
        oxygenSemaphore = new Semaphore(1);
        barrier = new CyclicBarrier(3);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        try {
            hydrogenSemaphore.acquire();
            releaseHydrogen.run();
            barrier.await();
        } catch (Exception e) {

        } finally {
            hydrogenSemaphore.release();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        try {
            oxygenSemaphore.acquire();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            barrier.await();
        } catch (Exception e) {

        } finally {
            oxygenSemaphore.release();
        }

    }
}
class H2O {
    private int hydrogenCount;
    private int oxygenCount;

    public H2O() {
        hydrogenCount = 0;
        oxygenCount = 0;
    }

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        while (hydrogenCount == 2 && oxygenCount == 0) {
            wait();
        }
        
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hydrogenCount++;

        if (hydrogenCount == 2 && oxygenCount == 1) {
            hydrogenCount = 0;
            oxygenCount = 0;
        }

        notifyAll();
    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
        while (oxygenCount == 1 && hydrogenCount != 2) {
            wait();
        }
        
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        oxygenCount++;

        if (hydrogenCount == 2 && oxygenCount == 1) {
            hydrogenCount = 0;
            oxygenCount = 0;
        }

        notifyAll();
    }
}

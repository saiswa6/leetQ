class H2O {
    private int hydrogenCount ;
    private int oxygenCount ;

    public H2O() {
        hydrogenCount = 0;
        oxygenCount = 0;
        
    }

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        if(hydrogenCount == 2 && oxygenCount == 1 ) {
            hydrogenCount = 0;
            oxygenCount = 0;
        }
		if(hydrogenCount < 2) {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hydrogenCount++;
          notifyAll();
        } else {
           wait();
        }
    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
        if(hydrogenCount == 2 && oxygenCount == 1 ) {
            hydrogenCount = 0;
            oxygenCount = 0;
        }
        if(oxygenCount < 1) {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
		releaseOxygen.run();
        oxygenCount++;
        notifyAll();
        } else {
            wait();
        }
    }
}
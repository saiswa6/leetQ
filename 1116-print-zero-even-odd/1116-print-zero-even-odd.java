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
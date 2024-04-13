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
                    zero.await();
                }
                printNumber.accept(0);
                status = (i % 2) + 1;
                if (status == 1) {
                    even.signalAll();
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
                    even.await();
                }
                printNumber.accept(i);
                status = 0;
                zero.signalAll();
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
class ZeroEvenOdd {
    private int n;
    private Integer sequence;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
        sequence = 0;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n ; i++){
            while(sequence != 0){
                wait();
            }
            printNumber.accept(0);
            sequence = i % 2 == 1 ? 1 : 2 ;
            notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n ; i+=2) {
            while(sequence != 1){
                wait();
            }
            printNumber.accept(i);
            sequence = 0;
            notifyAll();
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n ; i+=2) {
            while(sequence != 2){
                wait();
            }
            printNumber.accept(i);
            sequence = 0;
            notifyAll();
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////
// Using Synchrozied block
 class ZeroEvenOdd {
        private int n;
        // private volatile int currState; no need to use volatile if using sync block
        private int currState;
        private final int ZERO, EVEN, ODD;

        public ZeroEvenOdd(int n) {
            this.n = n;
            this.ZERO = 0;
            this.EVEN = 2;
            this.ODD = 1;
            this.currState = ZERO;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                synchronized (this) {
                    while (currState != ZERO)
                        wait();

                    printNumber.accept(0);
                    if ((i & 1) == 1) {
                        currState = ODD;
                    } else {
                        currState = EVEN;
                    }

                    notifyAll();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                synchronized (this) {
                    while (currState != EVEN)
                        wait();

                    printNumber.accept(i);
                    currState = ZERO;
                    notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                synchronized (this) {
                    while (currState != ODD)
                        wait();

                    printNumber.accept(i);
                    currState = ZERO;
                    notifyAll();
                }
            }
        }
    }

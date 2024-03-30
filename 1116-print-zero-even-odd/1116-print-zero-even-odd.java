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
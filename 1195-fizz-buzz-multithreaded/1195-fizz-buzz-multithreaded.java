class FizzBuzz {
    private int n;
    // AtomicInteger number;
    private int counter = 1;

    public FizzBuzz(int n) {
        this.n = n;
        // number = new AtomicInteger(1);
        counter = 1;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        // int currentNumber = number.get();
        while (counter <= n) {
            if (counter % 3 == 0 && counter % 5 != 0) {
                printFizz.run();
                counter++;
                notifyAll();
            } else {
                wait();
            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        // int currentNumber = number.get();
        while (counter <= n) {
            if (counter % 3 != 0 && counter % 5 == 0) {
                printBuzz.run();
                counter++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        // int currentNumber = number.get();
        while (counter <= n) {
            if (counter % 3 == 0 && counter % 5 == 0) {
                printFizzBuzz.run();
                counter++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        // int currentNumber = number.get();
        while (counter <= n) {
            if (counter % 3 != 0 && counter % 5 != 0) {
                printNumber.accept(counter);
                counter++;
                notifyAll();
            } else {
                wait();
            }
        }
    }
}
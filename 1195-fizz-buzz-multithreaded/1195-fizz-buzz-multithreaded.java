class FizzBuzz {
    private int n;
    private int number = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {

        while (number <= n) {
            if (number % 3 == 0 && number % 5 != 0) {
                printFizz.run();
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (number <= n) {
            if (number % 3 != 0 && number % 5 == 0) {
                printBuzz.run();
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (number <= n) {
            if (number % 3 == 0 && number % 5 == 0) {
                printFizzBuzz.run();
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (number <= n) {
            if (number % 3 != 0 && number % 5 != 0) {
                printNumber.accept(number);
                number++;
                notifyAll();
            } else {
                wait();
            }
        }
    }
}
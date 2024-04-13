
class FizzBuzz {
    private int n;
    private int number = 1;
    private ReentrantLock lock;
    private Condition FizzCondition;
    private Condition BuzzCondition;
    private Condition FizzBuzzCondition;
    private Condition NumberCondition;

    public FizzBuzz(int n) {
        this.n = n;
        lock = new ReentrantLock();
        FizzBuzzCondition = lock.newCondition();
        BuzzCondition = lock.newCondition();
        FizzCondition = lock.newCondition();
        NumberCondition = lock.newCondition();
        
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        try {
            lock.lock();
            while (number <= n) {
                if (number % 3 == 0 && number % 5 != 0) {
                    printFizz.run();
                    number++;
                    BuzzCondition.signal();
                    FizzBuzzCondition.signal();
                    NumberCondition.signal();
                } else {
                    FizzCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        try {
            lock.lock();
            while (number <= n) {
                if (number % 3 != 0 && number % 5 == 0) {
                    printBuzz.run();
                    number++;
                    FizzCondition.signal();
                    FizzBuzzCondition.signal();
                    NumberCondition.signal();
                } else {
                    BuzzCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        try {
            lock.lock();

            while (number <= n) {
                if (number % 3 == 0 && number % 5 == 0) {
                    printFizzBuzz.run();
                    number++;
                    FizzCondition.signal();
                    BuzzCondition.signal();
                    NumberCondition.signal();
                } else {
                    FizzBuzzCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            while (number <= n) {
                if (number % 3 != 0 && number % 5 != 0) {
                    printNumber.accept(number);
                    number++;
                    FizzCondition.signal();
                    FizzBuzzCondition.signal();
                    BuzzCondition.signal();
                } else {
                    NumberCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /*private Condition nextCondition(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return FizzBuzzCondition;
        } else if (number % 3 == 0 && number % 5 != 0) {
            return FizzCondition;
        } else if (number % 3 != 0 && number % 5 == 0) {
            return BuzzCondition;
        } else {
            return NumberCondition;
        }
    }*/
}
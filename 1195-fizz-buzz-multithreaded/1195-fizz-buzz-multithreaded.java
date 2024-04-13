
class FizzBuzz {
    private int n;
    private int number = 1;
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);
    private Semaphore num = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {


            while (number <= n) {
                if (number % 3 == 0 && number % 5 != 0) {
                    fizz.acquire();
                    printFizz.run();
                    number++;
                    releaseLock(number);
                }
            }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {


            while (number <= n) {
                if (number % 3 != 0 && number % 5 == 0) {
                    buzz.acquire();
                    printBuzz.run();
                    number++;
                    releaseLock(number);
                }
            }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {


            while (number <= n) {
                if (number % 3 == 0 && number % 5 == 0) {
                    fizzbuzz.acquire();
                    printFizzBuzz.run();
                    number++;
                    releaseLock(number);
                }
            }

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

            while (number <= n) {
                if (number % 3 != 0 && number % 5 != 0) {
                    num.acquire();
                    printNumber.accept(number);
                    number++;
                    releaseLock(number);
                }
            }

    }

    public void releaseLock(int n) {
        if (n % 3 == 0 && n % 5 != 0) {
            fizz.release();
        } else if (n % 5 == 0 && n % 3 != 0) {
            buzz.release();
        } else if (n % 15 == 0) {
            fizzbuzz.release();
        } else {
            num.release();
        }
    }
}
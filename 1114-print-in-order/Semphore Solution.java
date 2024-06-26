//My Easy Semaphore Solution
class Foo {

    Semaphore one = new Semaphore(1);
    Semaphore two = new Semaphore(0);
    Semaphore three = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        one.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        two.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        one.release();
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Simple semaphore based solution
class Foo {

    private Semaphore[] semaphore;
    
    public Foo() {
        int numOfThreads = 3;
        semaphore = new Semaphore[numOfThreads];
        
        try {
            for (int i = 0 ; i< numOfThreads; ++i) {
                // Binary semaphore, since there is only one resource i.e., "Print N".
                semaphore[i] = new Semaphore(1, true);
                
                // Don't let any thread print.
                semaphore[i].acquire();
            }

            // Let a thread to print "first".
            semaphore[0].release();
        } catch (InterruptedException ie) {
            // NO-OP.
        }
    }

    public void first(Runnable printFirst) throws InterruptedException {
        semaphore[0].acquire();
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        
        semaphore[1].release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphore[1].acquire();
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        
        semaphore[2].release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphore[2].acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        
        semaphore[0].release();
    }
}

class Foo {
    ReentrantLock lock ;
    Condition firstDone ;
    Condition secondDone;
    private boolean isFirstDone;
    private boolean isSecondDone;

    public Foo() {
        lock = new ReentrantLock();
        firstDone = lock.newCondition();
        secondDone = lock.newCondition();
        isFirstDone = false;
        isSecondDone = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        try{
            lock.lock();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        isFirstDone = true;
        firstDone.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        try{
            lock.lock();
            while(!isFirstDone) {
                firstDone.await();
            }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        isSecondDone = true;
        secondDone.signalAll();
        } finally{
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        try{
            lock.lock();
            while(!isSecondDone) {
                secondDone.await();
            }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        secondDone.signalAll();
        } finally{
            lock.unlock();
        }
    }
}
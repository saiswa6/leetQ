class FooBar {
    private int n;
    private boolean isFoo;

    public FooBar(int n) {
        this.n = n;
        isFoo = true;
    }

    public synchronized void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if (!isFoo) {
                wait();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            isFoo = false;
            notifyAll();
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if (isFoo) {
                wait();
            }

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            isFoo = true;
            notifyAll();
        }
    }
}
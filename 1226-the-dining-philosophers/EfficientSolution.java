// Efficient - To avoid deadlock, ask 1 philospher to right fork first and other 4 philosphers to take left fork.
// May be have chance to acquire to 2 people eat a time.

class DiningPhilosophers {
    Object forks[] = new Object[5];

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new Object();
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
            Runnable pickLeftFork,
            Runnable pickRightFork,
            Runnable eat,
            Runnable putLeftFork,
            Runnable putRightFork) throws InterruptedException {
        Object left = forks[philosopher];
        Object right = forks[(philosopher + 1) % 5];
        if(philosopher == 1) {
             synchronized (right) {
            synchronized (left) {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }
        } else {
             synchronized (left) {
            synchronized (right) {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }
        }

    }
}

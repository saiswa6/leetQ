class BoundedBlockingQueue {
    Queue<Integer> boundedQueue;
    int capacity;
    ReentrantLock lock;
    Condition isFull;
    Condition isEmpty;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.boundedQueue = new ArrayDeque<>(capacity);
        this.lock = new ReentrantLock();
        this.isEmpty = lock.newCondition();
        this.isFull = lock.newCondition();
    }
    
    public void enqueue(int element) throws InterruptedException {
        try{
            lock.lock();
        while(boundedQueue.size() == capacity){
            isFull.await();
        }

        boundedQueue.offer(element);
        isEmpty.signalAll();
        } finally{
            lock.unlock();
        }
    }
    
    public int dequeue() throws InterruptedException {
        try{

            lock.lock();
        while (boundedQueue.isEmpty()) {
            isEmpty.await();;
        }
        int value = boundedQueue.remove();
        isFull.signalAll();
        return value;
        } finally{
            lock.unlock();
        }
    }
    
    public int size() {
        return boundedQueue.size();
    }
}
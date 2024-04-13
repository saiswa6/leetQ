class BoundedBlockingQueue {
    Queue<Integer> boundedQueue;
    int capacity;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.boundedQueue = new ArrayDeque<>(capacity);
        
    }
    
    public synchronized void enqueue(int element) throws InterruptedException {
        while(boundedQueue.size() == capacity){
            wait();
        }

        boundedQueue.offer(element);
        notifyAll();
    }
    
    public synchronized int dequeue() throws InterruptedException {
        while (boundedQueue.isEmpty()) {
            wait();
        }
        int value = boundedQueue.remove();
        notifyAll();
        return value;
    }
    
    public int size() {
        return boundedQueue.size();
    }
}
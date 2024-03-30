class BoundedBlockingQueue {

    Queue<Integer> buffer;
    //private int sizeOfBuffer; 
    private int capacityOfBuffer; 

    public BoundedBlockingQueue(int capacity) {
        buffer = new ArrayDeque<>(capacity);
        //sizeOfBuffer = 0;
        capacityOfBuffer = capacity;
    }
    
    public synchronized void enqueue(int element) throws InterruptedException {
        while(buffer.size() == capacityOfBuffer) {
            wait();
        }

        buffer.add(element);
        notifyAll();
        
    }
    
    public synchronized int dequeue() throws InterruptedException {
        while(buffer.size() == 0) {
            wait();
        }
        int value = buffer.poll();
        notifyAll();
        return value;
    }
    
    public int size() {
        return buffer.size();
    }
}
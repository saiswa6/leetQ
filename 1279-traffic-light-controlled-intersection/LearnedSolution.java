
/*
In Java, the ReentrantLock class provides a way to create locks that can be acquired multiple times by the same thread, similar to the intrinsic lock provided by the synchronized keyword. The ReentrantLock constructor new ReentrantLock(true) creates a fair lock.

ReentrantLock(boolean fair)
fair: If true, the lock will guarantee a fair ordering among the threads that are waiting to acquire the lock. If false, the lock does not guarantee any particular order.

Summary:
1) new ReentrantLock(true) creates a fair ReentrantLock, which guarantees that the lock will be granted to the longest-waiting thread when the lock becomes available.
2) Fair locking can be useful to prevent thread starvation, where some threads may be waiting indefinitely for a lock while other threads continually acquire and release the lock.
3) However, fair locking may introduce additional overhead compared to non-fair locking, as it requires more complex bookkeeping to maintain the order of waiting threads.


Step 1: Acquire the lock
Step 2a: Check if the road has a green light already (determined by currentRoadId)
Step 2b: If condition is false, turn light green and set currentRoadId to roadId
Step 3: Allow car to cross
Step 4: Release the lock
*/


class TrafficLight {

    private ReentrantLock lock;
    private int currentRoadId;

    public TrafficLight() {
        lock = new ReentrantLock(true);
        currentRoadId = 1;
        
    }
    
    public void carArrived(
        int carId,           // ID of the car
        int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
        int direction,       // Direction of the car
        Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
        Runnable crossCar    // Use crossCar.run() to make car cross the intersection 
    ) {
        lock.lock();
        try{
            if (currentRoadId != roadId) { // If currentRoad not equal to previous road, turn the Green. For previous road, already green turned on.
                turnGreen.run(); // Road A -> 1 AND Road B -> 2
                currentRoadId = roadId;
            }
            crossCar.run();
        } finally {
            lock.unlock();
        }
        
    }
}
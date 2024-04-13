class TrafficLight {

    Lock lock;

    //we will have only one flag to represent the light, since the light cannot be green at same time
    //on both paths. So if RoadA is green, it means RoadB is Red.
    //if roadA is red(false), Road B is Green, car can go!
    public boolean isRoadAGreen;
    
    public TrafficLight() {

    //we will use a reentrant lock with true set, to ensure thread fairness
    //So that no request ends up waiting indefinitely and everything gets processed sequentially
        lock = new ReentrantLock(true);
        //initially RoadA is green
        isRoadAGreen = true;
    }
    
    public void carArrived(
        int carId,           // ID of the car
        int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
        int direction,       // Direction of the car
        Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
        Runnable crossCar    // Use crossCar.run() to make car cross the intersection 
    ) {

        //first capture the lock
        lock.lock();

        //do the rest of the application logic in a try and finally block, so that in case 
        //the thread is interrupted, the lock is safely released, thereby ensuring other
        //threads can capture it.

        try {
            //we will turn the light to green if
            //1) RoadA and the light is Red there(i.e that flag is false)
            //2) RoadB and the light is Red there(i.e our flag is true there)
            if((!isRoadAGreen && roadId==1) || (isRoadAGreen && roadId==2)) {
                //we turn the light to green! Ensure to reflect it on the flag also
                isRoadAGreen = !isRoadAGreen;

                turnGreen.run();

            }

            // we are already green, let the car cross
            crossCar.run();

        }

        finally {
            lock.unlock();
        }
        
    }
}

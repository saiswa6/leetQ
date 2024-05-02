/*
Given the current ping call ti , we are asked to count the number of previous calls that fall in the range of [ti−3000, ti].

Approach 1: Iteration over Sliding Window
- The idea is that we can use a container such as array or list to keep track of all the incoming ping calls. 
  At each occasion of ping(t) call, first we append the call to the container, and then starting from the current call, we iterate backwards to count the calls that fall into the time range of [t-3000, t].

- On the other hand, once the ping calls become outdated, i.e. out of the scope of [t-3000, t], we do not need to keep them any longer in the container, since they will not contribute to the solution later.
  As a result, one optimization that we could do is that rather than keeping all the historical ping calls in the container, we could remove the outdated calls on the go, which can avoid the overflow of the 
  container and reduce the memory consumption to the least.
  In summary, our container will function like a sliding window over the ever-growing sequence of ping calls.

- Based on the above description, the list data structure seems to be more fit as the container for our tasks, than the array.
  Because the list is more adapted for the following two operations:
  Appending: we will append each incoming call to the tail of the sliding window.
  Popping: we need to pop out all the outdated calls from the head of the sliding window.

*/

class RecentCounter {
    LinkedList<Integer> slideWindow;

    public RecentCounter() {
        this.slideWindow = new LinkedList<Integer>();
    }

    public int ping(int t) {
        // step 1). append the current call
        this.slideWindow.addLast(t);

        // step 2). invalidate the outdated pings
        while (this.slideWindow.getFirst() < t - 3000)
            this.slideWindow.removeFirst();

        return this.slideWindow.size();
    }
}
/*
Complexity Analysis
 - condition : "It is guaranteed that every call to ping uses a strictly larger value of t than before."
   Based on the above condition, the maximal number of elements in our sliding window would be 3000, which is also the maximal time difference between the head and the tail elements.

Time Complexity: O(1)
- The main time complexity of our ping() function lies in the loop, which in the worst case would run 3000 iterations to pop out all outdated elements, and in the best case a single iteration.
- Therefore, for a single invocation of ping() function, its time complexity is O(3000)=O(1)
- If we assume that there is a ping call at each timestamp, then the cost of ping() is further amortized, where at each invocation, we would only need to pop out a single element, 
  once the sliding window reaches its upper bound.

Space Complexity: O(1)
- As we estimated before, the maximal size of our sliding window is 3000, which is a constant.
*/


/*
Discussion
- Since the elements in our sliding window are strictly ordered, due to the condition of the problem, one might argue that it might be more efficient to use binary search to locate the most recent outdated calls 
  and then starting from that point truncate all the previous calls.
- In terms of search, binary search is seemingly more efficient than our linear search. When the elements are held in the array data structure, it is true that binary search is more efficient.

- However, it is not the case for the linked list, since there is no way to locate an element in the middle of a linked list instantly, which is a critical condition for binary search algorithm.
- As a result, in order to apply binary search, we might have to opt for the Array data structure.On the other hand, once we use the array as the container, we might have to keep all the historical elements, which in the long run is not space-efficient neither time-efficient later.
  Or we have to find a way to efficiently remove the elements from array without frequently reallocating memory.
- To conclude, it is doable to have a binary search solution.
  Yet, it would complicate the design, and at the end the final solution is not necessarily more efficient than the above simple LinkedList-based sliding window.
*/

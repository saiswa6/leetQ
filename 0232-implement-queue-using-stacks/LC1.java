/*
Queue is FIFO (first in - first out) data structure, in which the elements are inserted from one side - rear and removed from the other - front.
To satisfy FIFO property of a queue we need to keep two stacks. They serve to reverse arrival order of the elements and one of them store the queue elements in their final order.


Approach #1 (Two Stacks) Push - O(n) per operation, Pop - O(1) per operation.
Push
A queue is FIFO (first-in-first-out) but a stack is LIFO (last-in-first-out). This means the newest element must be pushed to the bottom of the stack. To do so we first transfer all s1 elements to auxiliary stack s2. Then the newly arrived element is pushed on top of s2 and all its elements are popped and pushed to s1.
*/
private int front;

public void push(int x) {
    if (s1.empty())
        front = x;
    while (!s1.isEmpty())
        s2.push(s1.pop());
    s2.push(x);
    while (!s2.isEmpty())
        s1.push(s2.pop());
}

/*
Complexity Analysis**
Time complexity : O(n)
Each element, with the exception of the newly arrived, is pushed and popped twice. The last inserted element is popped and pushed once. Therefore this gives 4n+24 n + 24n+2 operations where nnn is the queue size. The push and pop operations have O(1) time complexity.

Space complexity : O(n)
We need additional memory to store the queue elements


Pop
======
The algorithm pops an element from the stack s1, because s1 stores always on its top the first inserted element in the queue.
The front element of the queue is kept as front.
*/
public int pop() {
    int res = s1.pop();
    if (!s1.empty())
        front = s1.peek();
    return res;
}

/*
Complexity Analysis
Time complexity : O(1)O(1)O(1).
Space complexity : O(1)O(1)O(1).


Empty
=====
Stack s1 contains all stack elements, so the algorithm checks s1 size to return if the queue is empty.
*/
// Return whether the queue is empty.
public boolean empty() {
    return s1.isEmpty();
}

/*
Complexity Analysis
Time complexity : O(1)O(1)O(1).
Space complexity : O(1)O(1)O(1).


Peek
=====
The front element is kept in constant memory and is modified when we push or pop an element.
*/
// Get the front element.
public int peek() {
  return front;
}
/*
Complexity Analysis
Time complexity : O(1)O(1)O(1). The front element has been calculated in advance and only returned in peek operation.
Space complexity : O(1)O(1)O(1).

*/




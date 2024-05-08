/*
Approach #2 (Two Stacks) Push - O(1) per operation, Pop - Amortized O(1) per operation.
Push
The newly arrived element is always added on top of stack s1 and the first element is kept as front queue element

Complexity Analysis
Time complexity : O(1). Аppending an element to a stack is an O(1) operation.
Space complexity : O(n). We need additional memory to store the queue elements

Pop
We have to remove element in front of the queue. This is the first inserted element in the stack s1 and it is positioned at the bottom of the stack because of stack's LIFO (last in - first out) policy. To remove the bottom element from s1, we have to pop all elements from s1 and to push them on to an additional stack s2, which helps us to store the elements of s1 in reversed order. This way the bottom element of s1 will be positioned on top of s2 and we can simply pop it from stack s2. Once s2 is empty, the algorithm transfer data from s1 to s2 again.

Complexity Analysis
Time complexity: Amortized O(1) Worst-case O(n). In the worst case scenario when stack s2 is empty, the algorithm pops nnn elements from stack s1 and pushes nnn elements to s2, where nnn is the queue size. This gives 2n2n2n operations, which is O(n). But when stack s2 is not empty the algorithm has O(1)O(1)O(1) time complexity. So what does it mean by Amortized O(1)? Please see the next section on Amortized Analysis for more information.

Space complexity : O(1)

Empty
Both stacks s1 and s2 contain all stack elements, so the algorithm checks s1 and s2 size to return if the queue is empty.
Complexity Analysis
Time complexity : O(1)
Space complexity : O(1)

Peek
The front element is kept in constant memory and is modified when we push an element. When s2 is not empty, front element is positioned on the top of s2

Complexity Analysis
Time complexity : O(1). The front element was either previously calculated or returned as a top element of stack s2. Therefore complexity is O(1)
Space complexity : O(1)
*/

class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();

    }

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        if (s1.isEmpty() && s2.isEmpty()) {
            return -1;
        }
        if (s2.empty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.peek());
                s1.pop();
            }
        }
        int val = s2.peek();
        s2.pop();
        return val;
    }

    public int peek() {
        if (s1.isEmpty() && s2.isEmpty()) {
            return -1;
        }
        if (s2.empty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.peek());
                s1.pop();
            }
        }
        int val = s2.peek();
        return val;
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */



/*
Amortized Analysis
Amortized analysis gives the average performance (over time) of each operation in the worst case. The basic idea is that a worst case operation can alter the state in such a way that the worst case cannot occur again for a long time, thus amortizing its cost.

Consider this example where we start with an empty queue with the following sequence of operations applied:
push1,push2,…,pushn,pop1,pop2…,popn

The worst case time complexity of a single pop operation is O(n). Since we have nnn pop operations, using the worst-case per operation analysis gives us a total of O(n2) ime.

However, in a sequence of operations the worst case does not occur often in each operation - some operations may be cheap, some may be expensive. Therefore, a traditional worst-case per operation analysis can give overly pessimistic bound. For example, in a dynamic array only some inserts take a linear time, though others - a constant time.

In the example above, the number of times pop operation can be called is limited by the number of push operations before it. Although a single pop operation could be expensive, it is expensive only once per n times (queue size), when s2 is empty and there is a need for data transfer between s1 and s2. Hence the total time complexity of the sequence is : n (for push operations) + 2*n (for first pop operation) + n - 1 ( for pop operations) which is O(2∗n).This gives O(2n/2n) = O(1) average time per operation.

*/


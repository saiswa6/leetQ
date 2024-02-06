/*
there is another solution for this problem, because all the elements in the lists are positive, first go through list a and negate all the element in it, then traverse through list b, check the first negative element in the list, this element is the intersection between two lists, then traverse through list a again to convert a back to positive list
*/


/*
2nd Approach
There is one more solution that follows O(1) space complexity.
We can loop through linkedlist A to reach the last node. We then make a link between the last node and the first element in linkedlist A.
We then apply Floyd's cycle detection algorithm on linkedlist B. And whether the cycle is detected or not, we restore the linkedlist A back to original state before returning the value

  */
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	if(headA == null || headB == null) {return null;}
	ListNode temp = headA;
	while(temp.next != null) { 
		temp = temp.next;
	}
	temp.next = headA; 

	ListNode hare = headB;
	ListNode tortoise = headB;

	while(hare != null && hare.next != null) {
		tortoise = tortoise.next;
		hare = hare.next.next;
		if(hare == tortoise) {
			break;
		}
	}

	if(hare == null || hare.next == null) {
		temp.next = null;
		return null;
	}

	tortoise = headB;

	while(tortoise != hare) {
		tortoise = tortoise.next;
		hare = hare.next;
	}

	temp.next = null;
	return tortoise;
}

//3rd Approach

/*I think solution 3 is beautiful but will really confuse interviewer and hard to prove during such a short time in interview. Moreover interviewer will ask you to prove all the corner cases will work. If you cannot explain it well they might think you copy the code from somewhere without understanding it and that will be a red flag. It doesn't worth the risk. More naive way is just to count the difference of length and use that to loop through the longer node again. Easy to understand for the interviewer and the code itself covers all the corner cases:*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        int la = 0;
        int lb = 0;
        while(a != null) {
            a = a.next;
            la++;
        }
        while(b != null) {
            b = b.next;
            lb++;
        }
        if(la > lb) {
            return helper(headA, headB, la - lb);
        } else {
            return helper(headB, headA, lb - la);
        }
        
    }
    
    public ListNode helper(ListNode headA, ListNode headB, int diff) {
        while(diff > 0) {
            headA = headA.next;
            diff--;
        }
        
        while(headA != null && headB != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
}

// Alternative Approach
public class Solution
{
    public ListNode GetIntersectionNode(ListNode headA, ListNode headB)
    {
        int sizeA = GetSize(headA);
        int sizeB = GetSize(headB);

        for (int i = 0; i < Math.Abs(sizeA - sizeB); i++)
        {
            if (sizeA > sizeB)
            {
                headA = headA.next;
            }
            else
            {
                headB = headB.next;
            }
        }

        while (headA != null && headB != null)
        {
            if (headA == headB)
            {
                return headA;
            }

            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    private int GetSize(ListNode head)
    {
        ListNode ptr = head;
        int count = 0;

        while (ptr != null)
        {
            count++;
            ptr = ptr.next;
        }

        return count;
    }
}

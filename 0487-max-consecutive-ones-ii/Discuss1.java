//Simpler, one pointer: track two sets of ones, current set and previous set, resetting previous to 0 if 2 consecutive 0s are encountered. Add 1 if at least one 0 is encountered:

public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0;
    int curr = 0, prev = 0, seenZero = 0;
    
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 0) {
            seenZero = 1;
            prev = curr;
            curr = 0;
        } else {
            curr++;
        }
        
        max = Math.max(max, curr + prev + seenZero);
    }
    
    return max;
}

//Really simple and intuitive O(n) dp solution: uses two additional variables each keeping track of current best streak with/without using the flip.
public int findMaxConsecutiveOnes(int[] nums) {
    int result = 0;
    int countFlipped = 0;
    int countUnflipped = 0;
    int i = 0;
    while (i < nums.length) {
        if (nums[i] == 1) {
            countFlipped++;
            countUnflipped++;
        } else {
            countFlipped = countUnflipped + 1;
            countUnflipped = 0;
        }
        result = Math.max(result, Math.max(countFlipped, countUnflipped));
        i++;
    }
    return result;
}

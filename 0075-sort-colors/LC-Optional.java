// Not appropriate as per solution----------
int zero = 0;
    int one = 0;
    int two = 0;
    
    for(int i:nums) {
        switch(i) {
            case 0:
                zero++;
                break;
            case 1:
                one++;
                break;
            case 2:
                two++;
                break;
        }
    }
    
    for(int i=0;i<zero;i++) {
        nums[i] = 0;
    }
    
    for(int i=zero;i<zero+one;i++) {
        nums[i] = 1;
    }
    
    for(int i=zero+one;i<zero+one+two;i++) {
        nums[i] = 2;
    }
}

(or)

public void sortColors(int[] nums) {
    // 2-pass
    int count0 = 0, count1 = 0, count2 = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 0) {count0++;}
        if (nums[i] == 1) {count1++;}
        if (nums[i] == 2) {count2++;}
    }
    for(int i = 0; i < nums.length; i++) {
        if (i < count0) {nums[i] = 0;}
        else if (i < count0 + count1) {nums[i] = 1;}
        else {nums[i] = 2;}
    }
}


// 2nd solution -----------
Arrays.sort(nums);

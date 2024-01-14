class Solution {
    public void duplicateZeros(int[] arr) {
        int lastIndex = arr.length - 1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            if(arr[start] == 0 && start == end) {
                arr[lastIndex--] = 0;
                end--;
            } else if(arr[start] == 0) {
                end--;
            }
            start++;
        }

        int preEndIndex = end;
        
        while(lastIndex >= 0 && preEndIndex >= 0) {
            if(arr[preEndIndex] != 0) {
                arr[lastIndex--] = arr[preEndIndex--];
            } else {
                arr[lastIndex--] = 0;
                if(lastIndex >= 0) {
                    arr[lastIndex--] = 0;
                }
                preEndIndex--;
            }
        }



/*
        int length = arr.length;
        int checkLength = length;
        int index = 0;
        for( ; index < length; index++) {
            if(checkLength == 0 || checkLength == -1) {
                break;
            }
            if(arr[index] == 0) {
                //index = index + 2;
                checkLength = checkLength - 2;
            } else {
                //index = index + 1;
                checkLength = checkLength - 1;
            }
        }

        int indexPointer = index - 1;
        int lastPointer = length - 1;
        while ( lastPointer >= 0 && indexPointer >= 0) {
            if(arr[indexPointer] == 0) {
                if(lastPointer > 0) {
                    arr[lastPointer--] = arr[indexPointer];
                }
                if(lastPointer > 0) {
                    arr[lastPointer--] = arr[indexPointer];
                }
                indexPointer--;
            } else {
                arr[lastPointer--] = arr[indexPointer--];
            }
        }
        return;*/
    }
}
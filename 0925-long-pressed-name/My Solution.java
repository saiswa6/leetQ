// Two Pointer SOlution

class Solution {
    public boolean isLongPressedName(String name, String typed) {
        char nameArray []= name.toCharArray();
        char typedArray []= typed.toCharArray();
        int nameLength = name.length();
        int typedLength = typed.length();

        int namePointer = 0;
        int typedPointer = 0;

        while(namePointer < nameLength || typedPointer < typedLength) {
            if(namePointer >= nameLength || typedPointer >= typedLength) { // if any one of the arrays left finally, then not aligned properly, SO false.
                return false;
            }
            int nameChar = nameArray[namePointer];
            int nameCount = 0;
            while(namePointer < nameLength && nameChar == nameArray[namePointer]) { // iterate to count the no of times character repeats to compare below
                namePointer++;
                nameCount++;
            }

            int typedChar = typedArray[typedPointer];
            int typedCount = 0;
            while(typedPointer < typedLength && typedChar == typedArray[typedPointer]) { // iterate to count the no of times character repeats to compare below
                typedPointer++;
                typedCount++;
            }
            if(nameChar != typedChar || nameCount > typedCount) { // If char is not same or count of name > count of typed, return false
                return false;
            }
        }

        return true; // if everything satisfied, return true
    }
}

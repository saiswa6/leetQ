class Solution {
    public boolean isLongPressedName(String name, String typed) {
        char nameArray []= name.toCharArray();
        char typedArray []= typed.toCharArray();
        int nameLength = name.length();
        int typedLength = typed.length();

        int namePointer = 0;
        int typedPointer = 0;

        while(namePointer < nameLength || typedPointer < typedLength) {
            if(namePointer >= nameLength || typedPointer >= typedLength) {
                return false;
            }
            int nameChar = nameArray[namePointer];
            int nameCount = 0;
            while(namePointer < nameLength && nameChar == nameArray[namePointer]) {
                namePointer++;
                nameCount++;
            }

            int typedChar = typedArray[typedPointer];
            int typedCount = 0;
            while(typedPointer < typedLength && typedChar == typedArray[typedPointer]) {
                typedPointer++;
                typedCount++;
            }
            if(nameChar != typedChar || nameCount > typedCount) {
                return false;
            }
        }

        return true;
    }
}
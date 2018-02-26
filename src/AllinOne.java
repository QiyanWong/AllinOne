public class AllinOne {


    public class Solution {
        public int search(Dictionary dict, int target) {
            // Write your solution here
            if(dict == null) return -1;

            int right = findSize(dict, 1);
            int left = 0;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(dict.get(mid) == null){
                    right = mid - 1;
                }
                else if(dict.get(mid) == target){
                    return mid;
                }
                else if(dict.get(mid) > target){
                    right = mid;
                }
                else left = mid;
            }

            return -1;
        }
        private int findSize(Dictionary dict, int size){
            if(dict.get(size - 1) == null) return size;
            else return findSize(dict, size * 2);
        }
    }

    public static void main(String[] args) {

    }
}

import java.util.*;

public class AllinOne {
  int globalmin = Integer.MIN_VALUE;
    public int search(Dictionary dict, int target) {
        // Write your solution here
        if(dict == null) return -1;

        int right = findSize(dict, 1) - 1;
        if(right == 0) return -1;
        int left = 0;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(dict.get(mid) == null){
                right = mid - 1;
            }
            else if(dict.get(mid) == target){
                return mid;
            }
            else if(dict.get(mid) > target){
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return -1;
    }
    private int findSize(Dictionary dict, int size){
        if(dict.get(size - 1) == null) return size;
        size = size * 2;
        return findSize(dict, size);
    }


    public ListNode reorder(ListNode head) {
        // Write your solution here.
        if(head == null || head.next == null) return head;
        ListNode middle =  middle(head);

        ListNode tail = head;
        while(tail.next != middle){
            tail = tail.next;
        }
        tail.next = null;
        ListNode two = reverse(middle);
        return pair(head,two);

    }
    private ListNode middle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode prev = null;
        return helper(head,prev);
    }

    private ListNode helper(ListNode head, ListNode prev){
        if(head == null) return prev;
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
        return helper(head,prev);
    }

    private ListNode pair(ListNode one, ListNode two){
        if(one == null) return two;
        if(two == null) return one;
        if(one == null && two == null) return null;

        while(two != null){
            if(one.next == null){
                one.next = two;
                break;
            }
            ListNode next = one.next;
            ListNode twonext = two.next;
            one.next = two;
            two.next = next;
            two = twonext;
            one = one.next;

        }
        return one;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k > nums.length || k < 0){
            return false;
        }
        HashMap<Integer,Integer> table = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(table.containsKey(nums[i])){
                if(Math.abs(i - table.get(nums[i])) <= k ){
                    return true;
                }
            }
            else table.put(i,nums[i]);

        }
        return false;

    }

    public String[] topKFrequent(String[] combo, int k) {
        //step1
        if(combo == null || combo.length == 0 || k > combo.length){
            return combo;
        }
        String[] results = new String[k];
        Map<String,Integer> map = new HashMap<String,Integer>();
        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(k,new Comparator<Map.Entry<String,Integer>>(){
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        //step1: iterate over and count the freq
        for(String word : combo){
            if(map.isEmpty() || !map.containsKey(word)){
                map.put(word, 1);
            }
            else{
                map.put(word, map.get(word) + 1);
            }
        }
        //step2: sort by minHeap
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(minHeap.size() < k){
                minHeap.offer(entry);
            }
            else if(entry.getValue() > minHeap.peek().getValue()){
                minHeap.poll();
                minHeap.offer(entry);
            }
        }

        //get results
        for(int i = k -1; i >= 0; i--){
            results[i] = minHeap.remove().getKey();
        }
        return results;
    }

//    public int kthSmallest(int[][] matrix, int k) {
//        // Write your solution here.
//        if(matrix == null || k <= 0) return -1;
//        int rows = matrix.length;
//        int columns = matrix[0].length;
//        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
//            @Override
//            public int compare(Cell a, Cell b){
//                if(a.value == b.value) return 0;
//                return a.value < b.value ? -1 : 1;
//            }
//        });
//
//        boolean[][] visited = new boolean[rows][columns];
//        minHeap.offer(new Cell(0,0,matrix[0][0]));
//        visited[0][0] = true;
//
//        for(int i = 0; i < k - 1; i++){
//            //expand
//            Cell next = minHeap.poll();
//            //generate
//            if(next.row + 1 < rows && !visited[next.row + 1][next.column]){
//                minHeap.offer(new Cell(next.row + 1,next.column,matrix[next.row + 1][next.column]));
//                minHeap.size();
//                visited[next.row + 1][next.column] = true;
//            }
//            if(next.column + 1 < columns && !visited[next.row][next.column + 1]){
//                minHeap.offer(new Cell(next.row,next.column + 1,matrix[next.row][next.column + 1]));
//                minHeap.size();
//                visited[next.row][next.column + 1] = true;
//            }
//
//        }
//        return minHeap.peek().value;
//
//    }
//    class Cell{
//        int row;
//        int column;
//        int value;
//        Cell(int row, int colunm, int value){
//            this.row = row;
//            this.column = column;
//            this.value = value;
//        }
//    }

    public List<String> permutations(String set) {
        // Write your solution here.
        if(set == null){
            return null;
        }
        if(set.length() == 0) {
            return new ArrayList<String>();
        }
        char[] array = set.toCharArray();
        List<String> results = new ArrayList<String>();
        DFS(array,0,results);
        return results;
    }
    private void DFS(char[] array, int position, List<String> results){
        if(position == array.length - 1){
            results.add(new String(array));
        }
        Set<String> dedup = new HashSet<>();
        for(int i = position;i < array.length; i++){
            swap(array,position,i);
            if(dedup.isEmpty()){
                dedup.add(new String(array));
            }
            else if(dedup.contains(new String(array))){
                continue;
            }
            dedup.add(new String(array));
            DFS(array,position + 1, results);
            swap(array,position,i);
        }
    }
    private void swap(char[] array, int a, int b){
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public int largest(int[][] matrix) {
        // Write your solution here
        int globalmax = 0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] up = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(i == 0){
                    up[i][j] = matrix[i][j];
                }
                else if(j == 0){
                    up[i][j] = matrix[i][j];
                }
                else if(matrix[i][j] == 1){
                    up[i][j] = matrix[i - 1][j - 1] + 1;
                }
            }
        }
        int[][] down = new int[rows][columns];
        for(int i = rows - 1; i >= 0; i--){
            for(int j = columns - 1; j >= 0; j--){
                if(i == rows - 1){
                    down[i][j] = matrix[i][j];
                }
                else if(j == columns - 1){
                    down[i][j] = matrix[i][j];
                }
                else if(matrix[i][j] == 1){
                    down[i][j] = matrix[i + 1][j + 1] + 1;
                }
            }
        }

        int[][] left = new int[rows][columns];
        for(int j = 0; j < columns; j++){
            for(int i = rows - 1; i >= 0; i--){
                if(i == rows - 1){
                    left[i][j] = matrix[i][j];
                }
                else if(j == 0){
                    left[i][j] = matrix[i][j];
                }
                else if(matrix[i][j] == 1){
                    left[i][j] = matrix[i + 1][j - 1] + 1;
                }
            }
        }
        int[][] right = new int[rows][columns];
        for(int j = columns - 1; j >= 0; j--){
            for(int i = 0; i < rows; i++){
                if(i == 0){
                    right[i][j] = matrix[i][j];
                }
                else if(j == columns - 1){
                    right[i][j] = matrix[i][j];
                }
                else if(matrix[i][j] == 1){
                    right[i][j] = matrix[i -1][j + 1] + 1;
                }
            }
        }
        printMatrix(up,rows,columns);
        System.out.println();
        printMatrix(down,rows,columns);
        System.out.println();
        printMatrix(left,rows,columns);
        System.out.println();
        printMatrix(right,rows,columns);
        System.out.println();


        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                globalmax = Math.max(globalmax,min(up[i][j],down[i][j],left[i][j],right[i][j]));
            }
        }
        return globalmax;
    }

    private int min(int a, int b, int c, int d){
        int min = a;
        min = Math.min(min,b);
        min = Math.min(min,c);
        min = Math.min(min,d);
        return min;
    }

    private void printMatrix(int[][] m, int rows, int columns){
        for(int i = 0; i < rows; i++){
            System.out.println();
            for(int j = 0; j < columns; j++){
                System.out.print(m[i][j] + ",");
            }
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        // base case:
        if(root == null){
            return null;
        }
        if(root == one){
            return root;
        }
        else if(root == two){
            return root;
        }
        if(root.left == null){
            return root.right;
        }
        else if(root.right == null){
            return root.left;
        }

        TreeNode leftchild = lowestCommonAncestor(root.left,one,two);
        TreeNode rightchild = lowestCommonAncestor(root.right,one,two);
        if(leftchild == null){
            return rightchild;
        }
        else if(rightchild == null){
            return leftchild;
        }
        else return root;
    }
    public TreeNode createTree(String args){
        TreeNode root;
        switch (args) {
            case "tree1": {
                root = new TreeNode(5);
                root.left = new TreeNode(9);
                root.right = new TreeNode(12);
                TreeNode cur = root;
                cur = cur.left;
                cur.left = new TreeNode(2);
                cur.right = new TreeNode(3);
                cur = root.right;
                cur.left = null;
                cur.right = new TreeNode(14);

            }
        }
        root = new TreeNode(1);
        return root;
    }
    public ListNode reverseInPairs(ListNode head) {
        // Write your solution here.
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        reverse(cur,cur.next);
        return head.next;
    }
    private void reverse(ListNode head, ListNode next){
        if(head == null || next == null){
            return;
        }
        ListNode newNext = next.next;
        next.next = head;
        head.next = newNext;
        head = head.next;
        reverse(head, head.next);
    }
    public boolean match(String input, String pattern) {
        // Write your solution here
        return match(input,pattern,0,0);
    }
    private boolean match(String input, String pattern, int i, int j){
        //base case:
        if(i == input.length() && j == pattern.length()){
            return true;
        }
        else if(i >= input.length() || j >= pattern.length()){
            return false;
        }
        if(input.charAt(i) == pattern.charAt(j)){
            return match(input,pattern,i + 1, j + 1);
        }
        else{
            int count = countDigit(pattern,j);
            if(count > 0){
                int digit = Integer.valueOf(pattern.substring(j,j + count));
                return match(input,pattern,i + digit,j + count);
            }
            else return false;
        }
    }
    private boolean isDigit(char i){
        if(i >= '0' && i <='9' ){
            return true;
        }
        else return false;
    }

    private int countDigit(String pattern, int i){
        int count = 0;
        while(i < pattern.length() && isDigit(pattern.charAt(i))){
            count++;
            i++;
        }
        return count;
    }

    public List<List<Integer>> nqueens(int n) {
        // Write your solution here.
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> otherQueues = new ArrayList<Integer>();
        permutate(n,otherQueues,results);
        return results;
    }
    private void permutate(int n, List<Integer> otherQueues, List<List<Integer>> results){
        //base case
        int size = otherQueues.size();
        if(size == n){
            results.add(otherQueues);
            System.out.println(results);
            return;
        }
        for(int i = 0; i < n; i++){
            //place this queue
            if(canPlace(otherQueues,size,i)){
                otherQueues.add(i);
                permutate(n,otherQueues,results);
                otherQueues.remove(size);
            }
        }
    }
    private boolean canPlace(List<Integer> otherQueues, int x, int y){
        if(otherQueues.size() == 0){
            return true;
        }
        for(int i = 0; i < otherQueues.size(); i++){
            if((x + y == otherQueues.get(i) + i)
                    ||(x - y == otherQueues.get(i) - i)
                    || (y == otherQueues.get(i))){
                return false;
            }
        }
        return true;
    }

    public List<Integer> spiral(int[][] matrix) {
        // Write your solution here
        List<Integer> results = new ArrayList<Integer>();
        int rows = matrix.length;
        if(rows == 0){
            return results;
        }
        int columns = matrix[0].length;
        int up = 0;
        int down = rows - 1;
        int left = 0;
        int right = columns - 1;

        spiralTraverse(matrix,up,down,left,right,results);
        return results;
    }
    private void spiralTraverse(int[][] matrix, int up,int down, int left, int right, List<Integer> results){
        //base case:
        //nothing left
        if(up < down || right < left){
            return;
        }
        else if(up == down){
            //one row left
            for(int i = left; i <= right; i++){
                results.add(matrix[up][up]);
            }
            return;
        }
        else if(left == right){
            //one colmuns left
            for(int i = up; i <= down; i++){
                results.add(matrix[i][left]);
            }
            return;
        }
        //top row:
        for(int i = left; i < right; i++){
            results.add(matrix[up][left]);
        }
        //right colmun
        for(int i = up; i < down; i++){
            results.add(matrix[i][right]);
        }
        //bottom row
        for(int i = right; i > left; i--){
            results.add(matrix[down][i]);
        }
        //left colmun
        for(int i = down; i > up; i--){
            results.add(matrix[i][left]);
        }
        spiralTraverse(matrix,up + 1, down - 1, left + 1, right - 1, results);
        return;
    }
    public int minCuts(String input){
        if(input == null || input.length() == 0){
            return 0;
        }
        char[] array = input.toCharArray();
        //M[i] represents minimum cuts for string with i size, so M[0] = 0, solution is M[array.length]
        int[] M = new int[array.length + 1];
        M[0] = 0;
        //aab|baab
        for(int i = 1; i <= array.length; i++){
            M[i] = i - 1;
            //j = 0 represent no cut
            //ababbbabb
            for(int j = 0; j <= i; j++){
                if(isValid(array,j,i - 1)){
                    if(j == 0){
                        M[i] = 0;
                    }
                    else M[i] = Math.min(M[i], M[j - 1] + 1);
                }
            }
        }
        for(int i : M){
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.print("   ");
        for(char i : array){
            System.out.print(i + ", ");
        }
        return M[array.length];
    }
    private boolean isValid(char[] array,int index, int end){
        int left = index;
        int right = end;
        while(left < right){
            if(array[left++] != array[right--]){
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<>();
        combine(coins,0,target,cur,results);
        return results;
    }
    private void combine(int[] coins, int index,int rest, List<Integer> cur, List<List<Integer>> results) {
        //base case
        int coin = coins[index];
        if (rest == 0) {
            for (int i = 0; i < coins.length - 1 - index;i++) {
                cur.add(0);
            }
            results.add(new ArrayList<Integer>(cur));
            return;
        }
        if (index == coins.length - 1) {
            if (rest % coin == 0) {
                cur.add(rest / coin);
                results.add(new ArrayList<Integer>(cur));
                return;
            }
            return;
        }
        int k = rest / coin;
        for (int i = 0; i <= k; i++) {
            cur.add(i);
            rest -= i * coin;
            combine(coins, index + 1, rest, cur, results);
            cur.remove(cur.size() - 1);
        }
    }
    public List<Integer> zigZag(TreeNode root) {
        // Write your solution here
        List<Integer> results = new ArrayList<>();
        if(root == null){
            return results;
        }
        Deque<TreeNode> cur = new LinkedList<>();
        int level = 1;
        int size = 1;
        cur.offerLast(root);
        while(!cur.isEmpty()){
            //expand
            int i = size;
            size = 0;
            if(level % 2 == 0){//even
                for(;i > 0; i--){
                    TreeNode curNode = cur.pollFirst();
                    results.add(curNode.value);
                    //generate
                    if(curNode.left != null){
                        cur.offerLast(curNode.left);
                        size++;
                    }
                    if(root.right != null){
                        cur.offerLast(curNode.right);
                        size++;
                    }
                }
                System.out.println(results.toString());
                level++;
            }
            else{//odd
                for(;i > 0; i--){
                    TreeNode curNode = cur.pollLast();
                    if(curNode.right != null){
                        cur.offerFirst(curNode.right);
                        size++;
                    }
                    if(curNode.left != null){
                        cur.offerFirst(curNode.left);
                        size++;
                    }
                    results.add(curNode.value);
                }
                System.out.println(results.toString());
                level++;
            }
        }
        return results;
    }

    public int[] largestAndSmallest(int[] array) {
        if(array == null || array.length <= 1){
            return array;
        }
        int i = 0;
        int j = array.length - 1;
        while(i <= j){
            if(array[i] < array[j]){
                swap(array,i,j);
            }
            i++;
            j--;
        }
        System.out.println("i: "+ i);
        System.out.println("j: "+ j);
        printArray(array);
        int[] results = new int[2];
        results[0] = array[0];
        results[1] = array[i];
        for(int k = 0; k < i; k++){
            results[0] = array[k] > results[0] ? array[k] : results[0];
        }
        for(int k = i; k < array.length; k++){
            results[1] = array[k] < results[1] ? array[k] : results[1];
        }
        return results;
    }
    private void swap(int[] array,int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    private void printArray(int[] array){
        for (int i = 0; i < array.length;i++)
        System.out.print(array[i] + ", ");
        System.out.println();

    }

    public int[] largestAndSecond(int[] array) {
        // Write your solution here
        int[] results = new int[2];
        int n = array.length;
        Element globalmax = new Element(0);
        Deque<Element> queue = new LinkedList<>();
        for(int i = 0; i <= n / 2; i++){
            if(n % 2 != 0 && i == n / 2){
                Element e = new Element(array[i]);
                queue.offerLast(e);
            }
            else if(array[i] > array[n - 1 - i]){
                Element e = new Element(array[i]);
                e.smaller.add(array[n - 1 - i]);
                queue.offerLast(e);
            }

            else{
                Element e = new Element(array[n - 1 - i]);
                e.smaller.add(array[i]);
                queue.offerLast(e);
            }
        }

        while(queue.size() > 1){
            Element e1 = queue.pollFirst();
            Element e2 = queue.pollFirst();
            if(e1.value > e2.value){
                e1.smaller.add(e2.value);
                queue.offerLast(e1);
            }
            else{
                e2.smaller.add(e1.value);
                queue.offerLast(e2);
            }
        }
        Element max = queue.pollFirst();
        System.out.println(max.smaller.toString());
        results[0] = max.value;
        results[1] = max.smaller.get(0);
        for(int i = 0; i < max.smaller.size();i++){
            results[1] = Math.max(results[1],max.smaller.get(i));
        }
        return results;
    }
    public void rotate(int[][] matrix) {
        // Write your solution here.
        int size = matrix.length;
        rotate(matrix,0,size);
        return;
    }
    private void rotate(int[][] matrix, int offset, int size){
        //base case:
        if(size <= 1){
            return;
        }

        for(int i = 0; i < size - 1; i++){
            int edge = size - 1;
            int temp = matrix[offset + i][offset + edge];
            //5
            matrix[offset + i][offset + edge] = matrix[offset][offset + i];
            //1
            matrix[offset][offset + i] = matrix[offset + edge - i][offset];
            //13
            matrix[offset + edge - i][offset] = matrix[offset + edge][offset + edge - i];
            //9
            matrix[offset + edge][offset + edge - i] = temp;
        }
        rotate(matrix,offset + 1,size - 2);
    }
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        // Write your solution here.
        if(one == null || two == null){
            return null;
        }
        TreeNodeP cur = one;
        Set<TreeNodeP> ancestors =new HashSet<>();
        while(cur != null){
            ancestors.add(cur);
            cur = cur.parent;
        }
        cur = two;
        while(cur != null){
            if(ancestors.contains(cur)){
                return cur;
            }
            cur = cur.parent;
        }
        return null;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        // Write your solution here.
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode node : nodes){
            set.add(node);
        }
        if(root == null) return null;
        return lca(root,set);
    }
    private TreeNode lca(TreeNode root, Set<TreeNode> set){
        //base case
        if(root == null){
            return null;
        }
        if(set.contains(root)){
            return root;
        }
        TreeNode left = lca(root.left,set);
        TreeNode right = lca(root.right,set);
        if(left != null && right != null) {
            return root;
        }
        else if(left == null){
            return right;
        }
        else return left;
    }
    public int kthSmallest(int[][] matrix, int k) {
        // Write your solution here
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k,new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if(c1.value == c2.value){
                    return 0;
                }
                else if(c1.value < c2.value){
                    return -1;
                }
                return 1;
            }});
        boolean[][] visit = new boolean[n][m];

        minHeap.offer(new Cell(0,0,matrix[0][0]));
        visit[0][0] = true;
        for(int i = 0; i < k - 1; i++){
            Cell cur = minHeap.poll();
            System.out.println("expand: " + cur.value);
            int row = cur.n;
            int column = cur.m;
            if(column + 1 <= m - 1 && !visit[row][column + 1]){
                minHeap.offer(new Cell(row,column + 1, matrix[row][column + 1]));
                visit[row][column + 1] = true;
                System.out.print("generate: " + matrix[row][column + 1] + "  ");
            }
            if(row + 1 <= n - 1 && !visit[row + 1][column]){
                minHeap.offer(new Cell(row + 1,column, matrix[row + 1][column]));
                visit[row + 1][column] = true;
                System.out.println("generate: " + matrix[row + 1][column]);
            }
        }
        return minHeap.peek().value;
    }
    public boolean isCompleted(TreeNode root) {
        if(root == null){
            return true;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        boolean flag = false;
        while(!queue.isEmpty()){
            for(int i = queue.size(); i > 0; i--){
                TreeNode cur = queue.pollFirst();
                if(cur.left == null){
                    flag = true;
                }
                else if(!flag){
                    queue.offerLast(root.left);
                }
                else return false;
                if(cur.right == null){
                    flag = true;
                }
                else if(!flag){
                    queue.offerLast(root.right);
                }
                else return false;
            }
        }
        return true;
    }
    public RandomListNode copy(RandomListNode head) {
        if(head == null){
            return null;
        }
        Map<RandomListNode,RandomListNode> nodes = new HashMap<>();
        RandomListNode newNode = new RandomListNode(head.value);
        nodes.put(head,newNode);
        if(head.random != null){
            RandomListNode newRandom = new RandomListNode(head.random.value);
            newNode.random = newRandom;
            nodes.put(head.random,newRandom);
        }
        newNode.next = next(head.next,nodes);
        return newNode;
    }
    private RandomListNode next(RandomListNode head, Map<RandomListNode,RandomListNode> nodes){
        if(head == null){
            return null;
        }
        RandomListNode newNode;
        if(nodes.containsKey(head)){
            newNode = nodes.get(head);
        }
        else{
            newNode = new RandomListNode(head.value);
            nodes.put(head,newNode);
        }
        if(head.random == null){
            newNode.random = null;
        }
        else if(nodes.containsKey(head.random)){
            newNode.random = nodes.get(head.random);
        }
        else{
            RandomListNode newRandom = new RandomListNode(head.random.value);
            newNode.random = newRandom;
            nodes.put(head.random,newRandom);
        }
        newNode.next = next(head.next,nodes);
        return newNode;
    }
    public List<GraphNode> copy(List<GraphNode> graph) {
        List<GraphNode> results = new ArrayList<GraphNode>();
        if(graph.size() == 0){
            return results;
        }
        Map<GraphNode,GraphNode> nodes = new HashMap<>();
        for(int i = 0; i < graph.size(); i++){
            results.add(copy(graph.get(i),nodes));
        }
        return results;
    }
    private GraphNode copy(GraphNode graph,Map<GraphNode,GraphNode> nodes){
        if(graph == null){
            return null;
        }
        GraphNode newNode;
        if(nodes.containsKey(graph)){
            newNode = nodes.get(graph);
        }
        else{
            newNode = new GraphNode(graph.key);
            nodes.put(graph,newNode);
        }
        for(int i = 0; i < graph.neighbors.size();i++){
            GraphNode neighbor = graph.neighbors.get(i);
            if(nodes.containsKey(neighbor)){
                newNode.neighbors.add(nodes.get(neighbor));

            }
            else{
                GraphNode newNehb = new GraphNode(neighbor.key);
                newNode.neighbors.add(newNehb);
                nodes.put(neighbor,newNehb);
            }
        }
        return newNode;
    }

    public TreeNode delete(TreeNode root, int key) {
        if(root == null){
            return root;
        }
        return deleteIt(root,key);
    }
    private TreeNode deleteIt(TreeNode root, int target){
        if(root == null){
            return null;
        }
        if(target > root.value){
            root.right = deleteIt(root.right, target);
            return root;
        }
        else if(target < root.value){
            root.left = deleteIt(root.left,target);
            return root;
        }
        else{
            //case 1: no child
            if(root.left == null && root.right == null){
                return null;
            }
            //case 2: one of them is null
            else if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            //case 3: children are not null
            else{
                //find smallest node in the right subtree
                TreeNode smallest = find(root.right);
                if(smallest.equals(root.right)){
                  smallest.left = root.left;
                  return smallest;
                }
                smallest.left = root.left;
                smallest.right = root.right;
                return smallest;
            }
        }
    }
    private TreeNode find(TreeNode root){
        if(root.left == null){
            return root;
        }
        return find(root.left);
    }
    public List<List<Integer>> layerByLayer(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if(root == null){
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--){
                TreeNode cur = queue.poll();
                level.add(cur.value);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            results.add(level);
        }
        return results;
    }
  public int closest(TreeNode root, int target) {
    int globalmin = root.value;
    search(root,target,globalmin);
    return globalmin;
  }
  private void search(TreeNode root, int target, int globalmin){
    if(root == null){
      return;
    }
    if(root.value == target){
      globalmin = target;
      return;
    }

    if(Math.abs(target - root.value) < Math.abs(target - globalmin)){
      globalmin = root.value;
    }
    if(root.value > target){
      search(root.left,target,globalmin);
      return;
    }
    else{
      search(root.right,target,globalmin);
      return;
    }
  }

  public int largestSmaller(TreeNode root, int target) {
    if(root == null || target == 1 ){
      return globalmin;
    }
    if(root.value < target){
      if(target - root.value < target - globalmin){
        globalmin = root.value;
      }
      return largestSmaller(root.right, target);
    }
    else return largestSmaller(root.left, target);
  }

  public int minCost(int[] cuts, int length) {
    int n = cuts.length;
    int[] newCuts = new int[n + 2];
    newCuts[0] = 0;
    for(int i = 1; i < n + 1; i++){
      newCuts[i] = cuts[i - 1];
    }
    newCuts[n + 1] = length;
    int[][] M = new int[n + 2][n + 2];
    for(int k = 1; k < n + 2; k++){
      int i = 0;
      int j = k;
      while(j < n + 2){
        if(k == 1){
          M[i][j] = 0;
        }
        else {
          int rest = newCuts[j] - newCuts[i];
          M[i][j] = Integer.MAX_VALUE;
          for(int m = j - 1; m > i;m--){
            M[i][j] = Math.min(M[i][m] + M[m][j] + rest, M[i][j]);

          }
          System.out.println(M[i][j]);
        }
        i++;
        j++;
      }
    }
    printMatrix(M,n+2,n+2);
    return M[0][n + 1];
  }
  public boolean exist(TreeNode root, int target) {
    // Write your solution here
    if(root == null){
      return false;
    }
    Set<Integer> sum = new HashSet<>();
    sum.add(0);
    return exist(root,target,0,sum);
  }
  private boolean exist(TreeNode root, int target, int prefixsum, Set<Integer> sum){
    prefixsum += root.value;
    if(sum.contains(prefixsum - target)){
      return true;
    }

    boolean needRemove = sum.add(prefixsum);
    if(root.left != null && exist(root.left,target,prefixsum,sum)){
      return true;
    }
    if(root.right != null && exist(root.right,target,prefixsum,sum)){
      return true;
    }
    System.out.println(sum.toString());
    if (needRemove){
      sum.remove(prefixsum);
    }
    return false;
  }
    public ListNode mergeSort(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;
        }
        return sort(head,tail);

    }
    private ListNode sort(ListNode head, ListNode tail){
        //base case:
        if(head == tail){
            return head;
        }
        //step 1: incomplete
        // left , mid, || right
        // 1 2 3  4 5 null
        //  h		m  h ?
        //   s			 f
        ListNode mid = middleOf(head,tail);
        ListNode mid2 = mid.next;
        mid.next = null;
        ListNode one = sort(head,mid);
        ListNode two = sort(mid2,tail);

        //step 2: merge
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = next(one,two);
        return dummyHead.next;
    }
    //find middle
    private ListNode middleOf(ListNode head, ListNode tail) {
        if(head == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != tail && fast.next.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    //compare method
    private ListNode next(ListNode one, ListNode two){
        if(one == null){
            return two;
        }
        else if(two == null){
            return one;
        }
        // return the node which is smaller
        if(one.value < two.value){
            one.next = next(one.next,two);
            return one;
        }
        else{
            two.next = next(one,two.next);
            return two;
        }
    }
    public int maxPathSum(TreeNode root) {
        // Write your solution here
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSum(root,0,max);
        return max[0];
    }
    private void maxPathSum(TreeNode root,int curtmax, int[] max){
        if(root == null){
            return;
        }
        if(root.value >= 0){
            curtmax += root.value;
        }
        else curtmax = root.value;
        max[0] = Math.max(curtmax,max[0]);
        maxPathSum(root.left,curtmax,max);
        maxPathSum(root.right,curtmax,max);
        return;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long result = count(l1,-1);
        result += count(l2,-1);
        return toList(result);
    }
    private ListNode toList(long n){
        String s = "" + n;
        ListNode head = new ListNode(s.charAt(s.length() - 1) -48);
        ListNode cur = head;
        for(int i = s.length() - 2; i >= 0; i-- ){
            cur.next = new ListNode(s.charAt(i) - 48);
            cur = cur.next;
        }
        return head;
    }
    private long count(ListNode head, int digits){
        if(head == null){
            return 0;
        }
        digits++;
        long result = count(head.next,digits);
        result += Math.pow(10,digits) * head.value;
        return result;
    }
        public void permutation(String input){
            if(input == null || input.length() <= 1){
                return;
            }
            StringBuilder sb = new StringBuilder();
            char[] array = input.toCharArray();
            permutate(array,0,sb);
        }
        private void permutate(char[] array, int position, StringBuilder sb){
            if(position == array.length){
                printString(sb);
                return;
            }
            //add space
            sb.append(array[position]);
            sb.append(" ");
            permutate(array,position + 1,sb);
            sb.deleteCharAt(sb.length() - 1);
            //do nothing
            permutate(array,position + 1,sb);
        }
        private void printString(StringBuilder sb){
            System.out.println(new String(sb));
        }
    // Java program to check if two binary tree are cousins
    class Node
    {
        int data;
        Node left, right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }



        private boolean sameParent(Node node, Node a, Node b) {
            // Base case
            if (node == null)
                return false;

            return ((node.left == a && node.right == b) ||
                    (node.left == b && node.right == a) ||
                    sameParent(node.left, a, b) ||
                    sameParent(node.right, a, b));
        }
        private int level(Node node, Node ptr, int lev) {
            // base cases
            if (node == null)
                return 0;

            if (node == ptr)
                return lev;

            int l = level(node.left, ptr, lev + 1);
            if (l != 0)
                return l;
            return level(node.right, ptr, lev + 1);
        }

        public boolean isCousin(Node node, Node a, Node b) {
            return ((level(node, a, 1) == level(node, b, 1)) &&
                    (!sameParent(node, a, b)));
        }
    public int minCuts(int n) {

        int M[] = new int[n + 1];
        M[0] = 0;
        M[1] = 1;
        M[2] = 2;
        M[3] = 3;
        for (int i = 4; i <= n; i++) {
            M[i] = i;
            for (int x = 1; x <= i; x++) {
                int temp = x * x;
                if (temp > i)
                    break;
                else M[i] = Math.min(M[i], 1 + M[i - temp]);
            }
        }

        int res = M[n];

        return res;
    }

    public static int cake(String s) {
        String flag = "";
        int substringIndex = 1;
        while(true) {
            flag = s;
            String substr = flag.substring(0,substringIndex);
            flag = flag.replaceAll(substr,"");
            if(flag.length() == 0){
                return s.length() / substr.length();
            }
            substringIndex++;

        }
    }
    public boolean formCircle(String input[]){
        List<String> result = new ArrayList<String>();
        result.add(input[0]);
        boolean used[] = new boolean[input.length];
        return formCircle(input,result,used,input[0].charAt(0));
    }
    private boolean formCircle(String input[], List<String> result,boolean used[],char firstChar){
        if(input.length == result.size()){
            String str = result.get(result.size()-1);
            if(firstChar == str.charAt(str.length()-1)){
                return true;
            }
            return false;
        }
        String str = result.get(result.size()-1);
        char lastChar = str.charAt(str.length()-1);
        for(int i=1; i < input.length; i++){
            if(used[i]){
                continue;

            }
            if(lastChar == input[i].charAt(0)){
                used[i] = true;
                result.add(input[i]);
                boolean r = formCircle(input,result,used,firstChar);
                if(r){
                    return true;
                }
                used[i] = false;
                result.remove(result.size()-1);
            }

        }

        return false;
    }

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int profit = 0;
        int max = 0;
        int globalmax = 0;
        for(int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            } else {
                if (profit >= max) {
                    globalmax = max + profit;
                    max = profit;
                } else if (max + profit >= globalmax) {
                    globalmax = max + profit;
                }
                profit = 0;
            }
            if (max + profit >= globalmax) {
                globalmax = max + profit;
            }
        }
        return globalmax;
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //origin
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseHelper(head,m,n,1);
        if(m > 1){
            return head;
        }
        //newHead
        else return newHead;
    }
    private ListNode reverseHelper(ListNode head, int m, int n, int index){
        //base case
        if(index == n || head.next == null){
            return head;
        }
        ListNode newHead = reverseHelper(head.next,m,n,index + 1);
        //reverse
        if(index > m && index < n){
            head.next.next = head;
            head.next = null;
            return newHead;
        }
        else if(index == m){
            head.next.next = head;
            head.next = null;
            ListNode.printList(newHead);
            return newHead;
        }
        else if(index + 1 == m){
            head.next.next = newHead.next;
            head.next = newHead;
            return newHead;

        }
        else return newHead;
    }
    private boolean isSequence(String a, String b){
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int diff = (int)A[0]  - (int)B[0];
        for(int i = 0; i < A.length; i++){
            if(A[i] == B[i]) return false;
            int shift = (int)A[i] - diff;
            if(shift >= 26 + (int)'a'){
                shift -= 26;
            }
            else if(shift <=  (int)'a') {
                shift += 26;
            }
            if(shift != (int)B[i]) return false;
        }
        return true;
    }

    public int[] rainbowSortII(int[] array) {
        // Write your solution here
        if(array.length <= 1) return array;
        int r = 0;
        int g = 0;
        int b = 0;
        int k = array.length - 1;
        while(b <= k){
            if(array[b] == 0){
                swap(array,r++,b++);
            }
            else if(array[b] == 2){
                b++;
            }
            else if(array[b] == 1){
                swap(array,g++,b++);
            }
            else{
                swap(array,k--,b);
            }
            printArray(array);
        }
        return array;
    }

    public int longest(String input) {
        // Write your solution here
        int longest = 0;
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        while(right < input.length()){
            char cur = input.charAt(right);
            if(set.add(cur)){
                right++;
            }
            else{
                longest = right - left;
                while(left < right && left < input.length()){
                    if(input.charAt(left) == cur){
                        set.remove(cur);
                        left++;
                        right++;
                        break;
                    }
                    left++;
                }
            }
        }
        return longest;
    }
    public String reverseWords(String input) {
        // reverse whole input
        if(input == null || input.length() == 0){
            return input;
        }
        reverse(input, 0, input.length());
        //reverse 'word' which is between two spaces
        int offset = 0;
        for(int end = 0; end < input.length(); end++){
            if(input.charAt(end) == ' '){
                reverse(input,offset,end - 1);
                offset = end + 1;
            }
        }
        reverse(input,offset,input.length() - 1);
        return input;
    }
    private String reverse(String s, int offset, int end){
        //reverse string s from offset to end, offset inclusive, end inclusive
        char[] array = s.toCharArray();
        while(offset < end){
            swap(array,offset++,end--);
        }
        return new String(array);
    }

    public String decompress(String input) {
        // Write your solution here.
        if(input == null || input.length() == 0){
            return input;
        }
        int n = input.length();
        char letter;
        int nums = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i+=2){
            letter = input.charAt(i);
            nums = input.charAt(i + 1) - 48;
            while(nums > 0){
                sb.append(letter);
                nums--;
            }
        }
        return new String(sb);
    }
    public List<Integer> allAnagrams(String s, String l) {
        List<Integer> results = new ArrayList<>();
        if(l.length() == 0){
            return results;
        }
        Map<Character, Integer> map =  new HashMap<>();
        char[] substr = s.toCharArray();
        init(map, substr,l);
        int left = 0;
        if(isAnagram(map)){
            results.add(left);
        }
        for(int right = s.length(); right < l.length(); right++){
            if(!map.isEmpty() && map.containsKey(l.charAt(right))){
                map.put(l.charAt(right),map.get(l.charAt(right)) - 1);
            }
            if(!map.isEmpty() && map.containsKey(l.charAt(left))){
                map.put(l.charAt(left), map.get(l.charAt(left)) + 1);
            }
            left++;
            if(isAnagram(map)){
                results.add(left);
            }
        }
        return results;
    }
    private boolean isAnagram(Map<Character, Integer> map){
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() != 0){
                return false;
            }
        }
        return true;
    }
    private void init(Map<Character, Integer> map, char[] l, String s){
        for(int i = 0; i < l.length; i++){
            if(map.isEmpty() || !map.containsKey(l[i])){
                map.put(l[i],1);
            }
            else {
                map.put(l[i], map.get(l[i]) + 1);
            }
        }
        for(int i = 0; i < l.length; i++){
            if(!map.isEmpty() && map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i)) - 1);
            }
        }
    }
     List AnagramsInAString(String s, String t) {

        List<Integer> list = new ArrayList<Integer>();
        int sSum = 0;
        int tSum = 0;

        for(int i=0;i<t.length();i++) {
            tSum += t.charAt(i);
            sSum+= s.charAt(i);
        }
        //System.out.println(tSum);.

        int fakesum =sSum;
        for(int i=t.length();i<s.length();i++) {
            if(fakesum == tSum) {
                list.add(i-t.length());
            }
            fakesum += s.charAt(i) - s.charAt(i-(t.length()));
        }

        return list;
    }

    public static void main(String[] args) {
        AllinOne test = new AllinOne();
        ListNode head = new ListNode(1);
        ListNode curnode = head;
        for(int i = 2; i <= 8; i++){
            curnode.next = new ListNode(i);
            curnode = curnode.next;
        }

        RandomListNode randomHead = new RandomListNode(5);
        RandomListNode random = new RandomListNode(3);
        RandomListNode node = randomHead;
        for(int i = 4; i >= 0; i--){
            node.next = new RandomListNode(i);
            node.random = random;
            node = node.next;

        }
        Map<Character,Integer> map1 = new HashMap<>();
        map1.put('c',1);
        Map<Character,Integer> map2 = new HashMap<>();
        map2.put('c',1);
        System.out.println(map1.hashCode() == map2.hashCode());



    }
}

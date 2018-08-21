import java.util.*;

public class myBST {
    public List<Integer> preorder(TreeNode root){
        if(root == null){
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        List<Integer> results = new ArrayList<>();
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            if(cur.right != null) {
                stack.offerLast(cur.right);
            }
            if(cur.left != null) {
                stack.offerLast(cur.left);
            }
            results.add(cur.value);
        }
    return results;
    }

    public List<Integer> inorder(TreeNode root){
        if(root == null){
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> results = new ArrayList<>();
        TreeNode helper = root;
        while(helper != null || !stack.isEmpty()){
            if( helper!= null){
                stack.offerLast(helper);
                helper = helper.left;
                continue;
            }
            else{
                helper = stack.pollLast();
                results.add(helper.value);
                helper = helper.right;
            }
        }
        return results;
    }

    public List<Integer> postorder(TreeNode root){
        if(root == null){
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> results = new ArrayList<>();
        TreeNode helper = root;
        while(helper != null || !stack.isEmpty()){
            if( helper!= null){
                stack.offerLast(helper);
                helper = helper.left;
                continue;
            }
            else{
                helper = stack.pollLast();
                results.add(helper.value);
                helper = helper.right;
            }
        }
        return results;
    }


    public List<List<Integer>> layerByLayer(TreeNode root) {
        // Write your solution here.
        if(root == null){
            return new ArrayList<List<Integer>>();
        }
        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
        int lvl = 0;
        Queue<TreeNode> queue= new LinkedList<TreeNode>();
        queue.offer(root);
        lvl = 1;
        while(!queue.isEmpty()){
            ArrayList<Integer> result = new ArrayList<Integer>();
            int  k = lvl;
            lvl = 0;
            for(int i = 0; i < k; i++){
                TreeNode cur = queue.poll();
                if(cur.left != null){
                    queue.offer(cur.left);
                    lvl++;
                }

                if(cur.right != null){
                    queue.offer(cur.right);
                    lvl++;
                }
                result.add(cur.value);
            }
            results.add(result);
        }
        return results;
    }

    public List<Integer> postOrder(TreeNode root) {
        // Write your solution here
        if(root == null) return new ArrayList<Integer>();
        List<Integer> results = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.offerLast(root);
        TreeNode prev = null;
        while(!stack.isEmpty()){
            TreeNode cur = stack.peekLast();
            if(prev == null || cur == prev.left || cur == prev.right){
                if(cur.left != null){
                    stack.offerLast(cur.left);
                }
                else if(cur.right != null){
                    stack.offerLast(cur.right);
                }
                else{
                    stack.pollLast();
                    results.add(cur.value);
                }
            }
            else if(prev == cur.right){
                stack.pollLast();
                results.add(cur.value);
            }
            else if(prev == cur.left){
                if(cur.right != null){
                    stack.offerLast(cur.right);
                }
                else results.add(cur.value);
            }
            prev = cur;
        }
        return results;
    }

     public static void main(String[] args){
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(8);
        TreeNode d = new TreeNode(6);
        TreeNode e = new TreeNode(10);
        TreeNode f = new TreeNode(12);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        e.right = f;
        myBST test = new myBST();
        System.out.println(test.preorder(a));
        System.out.println(test.layerByLayer(a));
        System.out.println(test.inorder(a));
        System.out.println(test.postOrder(a));
     }

}

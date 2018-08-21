import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int value;

    public TreeNode(int value){
        this.value = value;
    }

    @Override
    public String toString(){
        String results = "";
        List<List<Integer>> tree = new ArrayList<List<Integer>>();
        AllinOne a = new AllinOne();
        tree = a.layerByLayer(this);
        for(int i = 0; i < tree.size(); i++) {
            results += tree.get(i).toString();
            results += '\n';
        }
        return results;
    }
}

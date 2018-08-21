import java.util.ArrayList;
import java.util.List;

class GraphNode {
   public int key;
   public List<GraphNode> neighbors;
   public GraphNode(int key) {
     this.key = key;
     neighbors = new ArrayList<>();
   }
   @Override
  public String toString(){
     String results = new String("");
     return results += print(results);
   }
   private String print(String results){
     results += this.key + " ->";
     for(int i = 0; i < neighbors.size();i++){
       results += neighbors.get(i).key +",";
     }
     return results;
   }
}
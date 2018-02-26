import java.util.ArrayList;

public class Dictionary {
    ArrayList<Integer> array;
  public Integer get(int index){
      if(index >= array.size()) return null;
      else return array.get(index);
  }
}

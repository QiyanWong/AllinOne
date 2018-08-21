import java.util.ArrayList;

public class Dictionary {
    ArrayList<Integer> array;

    Dictionary(ArrayList<Integer> array){
        this.array = array;
    }

  public Integer get(int index){
      if(index >= array.size()) return null;
      else return array.get(index);
  }
}

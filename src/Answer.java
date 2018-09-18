import java.util.Arrays;
import java.util.Comparator;

public class Answer {
  public static String[] answer(String[] l) {
    if(l == null || l.length == 0) return l;
    MyComparator myComparator = new MyComparator();
    Arrays.sort(l,myComparator);
    return l;
  }
  static class MyComparator implements Comparator<String>{
    @Override
    public int compare(String a, String b){
      String[] s1 = split(a);
      String[] s2 = split(b);
      int i = 0;
      int j = 0;
      while(i < s1.length && j < s2.length && Integer.valueOf(s1[i]) == Integer.valueOf(s2[i])){

        i++;
        j++;
      }
      if(i < s1.length && j < s2.length){
        return Integer.valueOf(s1[i]) < Integer.valueOf(s2[i]) ? -1 : 1;
      }
      else return j < s2.length ? -1 : 1;
    }
  }
  private static String[] split(String a){
    int slow = 0;
    String[] res = new String[a.length()];
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < a.length(); i++){
      char c = a.charAt(i);
      if(c != '.'){
        sb.append(c);
      }
      else {
        res[slow++] = sb.toString();
        sb.delete(0,sb.length());
      }
    }
    if(sb.length() != 0){
      res[slow++] = sb.toString();
    }
    return Arrays.copyOf(res,slow);
  }

  public static void main(String[] args){
   String[] solution = answer(new String[]{"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"});

   for(String s : solution){
     System.out.println(s);
   }
    System.out.println();
  }
}
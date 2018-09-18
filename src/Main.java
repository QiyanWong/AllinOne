import java.util.*;
public class Main{
  public static int minmumGroup(int people, Map<Integer,Set<Integer>> map){
    if(map == null || map.isEmpty()) return 0;
    int[] visited = new int[people + 1];
    int result = 0;
    for(int count = 1; count <= people; count++){
      if(visited[count] == 0){
        result++;
        visited = dfs(map,visited,count);
      }
    }
    return result;
  }
  private static int[] dfs(Map<Integer,Set<Integer>> map, int[] visited, int count){
    Deque<Integer> stack = new ArrayDeque<>();
    stack.offerLast(count);
    visited[count] = 1;
    while(!stack.isEmpty()){
      int cur = stack.pollLast();
      Set<Integer> set = map.get(cur);
      if(set == null || set.isEmpty()) continue;
      for(int s : set){
        if(visited[s] == 0){
          visited[s] = 1;
          stack.offerLast(s);
        }
      }
    }
    return visited;
    /*
    10
1:0
2:5 3 0
3:8 4 0
4:9 0
5:9 0
6:3 0
7:0
8:7 9 0
9:0
10:9 7 0
     */
  }
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int people = sc.nextInt();
    Map<Integer,Set<Integer>> map = new HashMap<>();
    int count = 1;
    while(count <= people){
      Set<Integer> set = new HashSet<>();
      while(sc.hasNextInt()){
        int temp = sc.nextInt();
        if(temp == 0){
          break;
        }
        else{
          set.add(temp);
        }
      }
      map.put(count,new HashSet<Integer>(set));
      count++;
    }
    System.out.println(minmumGroup(people,map));
  }
}
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class Solution<K, V> {
  int size;
  int limit;
  ListNode<K,V> head;
  ListNode<K,V> tail;
  Map<K,ListNode<K,V>> map;
  public Solution(int limit) {
    this.limit = limit;
    size = 0;
    map = new HashMap<>();
    head = null;
    tail = null;
  }

  public void set(K key, V value) {
    ListNode<K,V> cur;
    //case1: exists, update its value and append head;
    if(map.containsKey(key)){
      cur = map.get(key);
      cur.value = value;
      add(remove(cur));
      map.put(key,cur);
    }
    //case2: doesn't exist, map put new KV pair and append head
    else {
      cur = new ListNode<K,V>(key, value);
      add(cur);
      map.put(key,cur);
      if(size > limit){
        map.remove(remove(tail).key);
      }
    }
  }

  public V get(K key) {
    ListNode<K,V> cur;
    //case1: exists, append head and return its value;
    if(map.containsKey(key)){
      cur = map.get(key);
      add(remove(cur));
      return cur.value;
    }
    return null;
  }
  private ListNode<K,V> remove(ListNode<K,V> node){
    if(size == 1){
      head = tail = null;
    }
    if(node.prev != null)
      node.prev.next = node.next;
    if(node.next != null)
      node.next.prev = node.prev;
    if(node == head){
      head = head.next;
    }
    if(node == tail){
      tail = tail.prev;
    }
    node.next = null;
    node.prev = null;
    size--;
    return node;
  }
  private void add(ListNode<K,V> node){
    if(size == 0){
      head = tail = node;
    }
    node.next = head;
    head.prev = node;
    head = node;
    size++;
  }

  class ListNode<K,V> {
    V value;
    K key;
    ListNode<K, V> prev;
    ListNode<K, V> next;

    ListNode(K key, V value) {
      this.value = value;
      this.key = key;
      prev = null;
      next = null;
    }
    @Override
    public String toString(){
      return "" + key + ", " + value;
    }
  }

  public static void main(String[] args){
    Solution<Integer,Integer> s = new Solution<>(3);
    System.out.println(s.get(1));
    s.set(1,1);
    System.out.println(s.get(1));
    s.set(2,2);
    System.out.println(s.get(2));
    s.set(3,3);
    System.out.println(s.get(3));
    s.set(4,4);
    s.set(5,5);
    System.out.println(s.get(1));


  }
}



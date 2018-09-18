import java.util.List;

class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value) {
      this.value = value;
      next = null;
    }
    @Override
   public String toString(){
      return new String(this.value + " ");
    }
    public static ListNode toLinkedList(String s){
      ListNode head  = new ListNode(s.charAt(0) - 48);
      ListNode cur = head;
      for(int i = 1; i < s.length(); i++){
        cur.next = new ListNode(s.charAt(i) - 48);
        cur = cur.next;
      }
      return head;
    }
    public static void printList(ListNode head){
      if(head == null) return;
      int count = 0;
      while(head != null && count < 10){
        System.out.print(head + "->");
        head = head.next;
        count++;
      }
      System.out.println("null");

    }
    public static ListNode buildList(int[] array){
      ListNode head = new ListNode(array[0]);
      ListNode cur = head;
      for(int i = 1; i < array.length; i++){
        cur.next = new ListNode(array[i]);
      }
      return head;
    }
  }

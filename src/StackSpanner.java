class StockSpanner {
  ListNode head;
  ListNode tail;
  int max;
  int size;
  public StockSpanner() {
    head = null;
  }

  public int next(int price) {
    if(head == null){
      head = new ListNode(price);
      return 1;
    }
    else if (price <= head.value){
      ListNode newHead = new ListNode(price);
      newHead.next = head;
      head = newHead;
      return 1;
    }
    else{
      ListNode cur = head;
      ListNode prev = head;
      ListNode newHead = new ListNode(price);
      int count = 1;
      while(cur != null && cur.value < price){
        count++;
        prev = cur;
        cur = cur.next;
      }
      prev.next = newHead;
      newHead.next = cur;
      return count;
    }
  }
  class ListNode{
    ListNode next;
    int value;
    ListNode(int value){
      this.value = value;
    }
  }
  public static void main (String[] args){
    StockSpanner s = new StockSpanner();
    System.out.println(s.next(29));
    System.out.println(s.next(91));
    System.out.println(s.next(62));

    System.out.println(s.next(76));
    System.out.println(s.next(51));



  }
}
class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;
    public RandomListNode(int value) {
     this.value = value;
    }
    @Override
    public String toString(){
      String results = new String("" );
      return results += next(this,results);
    }

    private String next(RandomListNode head, String results){
      if(head == null){
        return results + "null";
      }
      if(head.random == null) {
        return results += head.value + "->" + next(head.next, results);
      }
      else{
        return results += head.value + "(" + head.random.value + ")" + "-> " + next(head.next, results);
      }
    }

  }
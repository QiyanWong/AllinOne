public class myEntry {
   private final String key;
   private Integer value;
   public myEntry next;
    public myEntry(String key, Integer value){
        this.key = key;
        this.value = value;
        next = null;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }
}

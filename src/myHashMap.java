public class myHashMap{
    private final int defaultCapacity;
    private myEntry[] buckets;
    private int size;
    private final double loadFactor;

    public myHashMap(int defaultCapacity){
        this.defaultCapacity = defaultCapacity;
        this.buckets = new myEntry[defaultCapacity];
        this.loadFactor = 0.75;
        size = 0;
    }

    public int size(){
        return size;
    }

    public int hash(String key){
        if(key == null){
            return 0;
        }
        int hashNumber = key.hashCode() & 0x7fffffff;
        return hashNumber;
    }
    public Integer get(String key){
        int index = getIndex(key);
        myEntry cur = buckets[index];
        while(cur != null){
            if(cur.getKey().equals(key)){
                return cur.getValue();
            }
            cur = cur.next;
        }
        return null;
    }

    public Integer put(String key, Integer value){
            int index = getIndex(key);
            myEntry cur = buckets[index];
            while(cur.next!= null){
                if(cur.getKey().equals(key)){
                     int temp = cur.getValue();
                     cur.setValue(value);
                     return temp;
                }
                cur = cur.next;
            }
            myEntry newhead= new myEntry(key,value);
            newhead.next = buckets[index];
            buckets[index] = newhead;
            size++;
            //rehashing?
            return null;
    }

    private void rehash(){
       myEntry[] oldbuckets = buckets;
       buckets = new myEntry[oldbuckets.length * 2];
       for(myEntry entry : oldbuckets){
           while(entry != null) { // 要注意从old bucket拿过来的entry并不一定只有一个
               int i = getIndex(entry.getKey());
               myEntry next = entry.next;
               entry.next = buckets[i];
               buckets[i] = entry;
               entry = entry.next;
           }
       }


    }
    private boolean needRehash(){
        return size > loadFactor * buckets.length;
    }

    private int getIndex(String key){
        return hash(key) % buckets.length;
    }


}

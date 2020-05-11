package Design;
import java.util.*;

class Pair<V, U>{
    public V key;
    public U value;
    public Pair(V k, U v){
        this.key = k;
        this.value = v;
    }
}

class Bucket{
    public LinkedList<Pair<Integer, Integer>> bucket;
    public Bucket(){
        this.bucket = new LinkedList<Pair<Integer, Integer>>();
    }

    // 在bucket中查找key
    public int get(int search_key){
        for(Pair<Integer, Integer> pair:this.bucket){
            if(pair.key == search_key) return pair.value;
        }
        return -1;
    }

    //在bucket中update key-value pair
    public void update(int update_key, int update_value){
        boolean flag = false;
        for(Pair<Integer, Integer> pair:this.bucket){
            if(pair.key==update_key) {
                // 更新旧的
                pair.value = update_value;
                flag = true;
            }
        }
        if(!flag){
            //新添加一个
            this.bucket.add(new Pair<Integer, Integer>(update_key, update_value));

        }

    }

    // 在bucket中删除一个pair
    public void delete(Integer delete_key){
        for(Pair<Integer, Integer> pair:this.bucket){
            if(pair.key.equals(delete_key) ) {
                this.bucket.remove(pair);
                break;
            }
        }
        return;
    }
}



class MyHashMap {

    /** Initialize your data structure here. */
    private int key_space;
    private List<Bucket> hash_table;
    public MyHashMap() {
        this.key_space = 2069;
        this.hash_table = new ArrayList<Bucket>();
        for(int i=0; i<this.key_space; i++){
            this.hash_table.add(new Bucket());
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int key_hashed = key % this.key_space;
        this.hash_table.get(key_hashed).update(key, value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int key_hashed = key % this.key_space;
        return this.hash_table.get(key_hashed).get(key);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int key_hashed = key % this.key_space;
        this.hash_table.get(key_hashed).delete(key);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

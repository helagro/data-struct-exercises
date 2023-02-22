public class HashTable<K, V>{
    static final double MAX_LOAD_FACTOR = 0.7;
    static final double MIN_LOAD_FACTOR = 0.3;
    static final double GROWTH_FACTOR = 1.5;
    static final double SHRINK_FACTOR = 0.5;
    static final int MIN_CAPACITY = 10;

    private class KValue<K, V>{
        K key;
        V value;

        KValue(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(K:" + key + " V:" + value + ")";
        }
    }

    private KValue<K,V> [] array = new KValue[MIN_CAPACITY];



    public void put(K key, V value){
        int index = getIndex(key);

        while (! indexVacant(index, key)){
            index = nextIndex(index);
        }

        array[index] = new KValue<K,V>(key, value);
    }

    private boolean indexVacant(int index, K key){
        return 
            array[index] == null ||
            array[index].key == null ||
            array[index].key == key;
    }

    private int nextIndex(int index){
        return (index +1) % array.length;
    }

    private int getIndex(K key){
        return Math.abs(key.hashCode()) % array.length;
    }


    public V get(K key){
        int index = getIndex(key);
        KValue kValue;

        do{
            kValue = array[index];
            if(kValue == null) return null;

            index = nextIndex(index);
        } while(kValue.key != key);

        return (V) kValue.value;
    }

    public void delete(K key){
        int index = getIndex(key);
        KValue kValue;

        do{
            kValue = array[index];
            if(kValue == null) return;

            index = nextIndex(index);
        } while(kValue.key != key);

        kValue.key = null;
        kValue.value = null;
    }


    @Override
    public String toString() {
        String res = "[ ";

        for(KValue kValue : array){
            res += kValue + ", ";
        }

        res += "]";
        return res;
    }
}

class TestHashTable{
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("maidens", 0);
        hashTable.put("phones", 2);
        hashTable.put("money", 1000000000);

        System.out.println(hashTable);
        System.out.println(hashTable.get("phones"));

        hashTable.delete("money");
        System.out.println(hashTable);

        hashTable.put("weight", 58);

        hashTable.put("age", 20);
        System.out.println(hashTable);
        hashTable.put("age", 21);
        System.out.println(hashTable);

        hashTable.put("date", 22);
        hashTable.put("time", 12);

        System.out.println(hashTable);
    }
}

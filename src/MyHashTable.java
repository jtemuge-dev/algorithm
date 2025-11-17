import dataStructures.HashTable;

public class MyHashTable {

    private HashTable table = new HashTable(101);


//    public Object search(Object key) {
//        return table.search(key);
//    }

    public Object updateElement(Object Key, Object Element) {

        Object element = table.get(Key);

        if (element== null) {
            return null;
        }
        table.put(Key, Element);
        return element;
    }


    public Object updateKey(Object Key, Object newKey) {

        Object element = table.get(Key);

        if (element == null) {
            return null;
        }
        table.put(Key, null);
        table.put(newKey, element);
        return element;
    }



    public Object delete(Object Key) {

        Object element = table.get(Key);
        if (element == null) {
            return null;
        }
        table.put(Key, null);
        return element;
    }



    public HashTable getTable() {
        return table;
    }



    public static void main(String[] args) {

        MyHashTable myTable = new MyHashTable();

        myTable.table.put("A", 100);
        myTable.table.put("B", 200);
        myTable.table.put("C", 300);

        System.out.println("Value A = " + myTable.table.get("A"));
        System.out.println("Value B = " + myTable.table.get("B"));
        System.out.println("Value C = " + myTable.table.get("C"));

        Object oldValue = myTable.updateElement("B", 250);
        System.out.println("Old value at B: " + oldValue);
        System.out.println("New value at B: " + myTable.table.get("B"));

        Object moved = myTable.updateKey("C", "Z");
        System.out.println("Moved element: " + moved);
        System.out.println("Value at old key C: " + myTable.table.get("C"));
        System.out.println("Value at new key D: " + myTable.table.get("D"));

        Object deleted = myTable.delete("A");
        System.out.println("Deleted value: " + deleted);
        System.out.println("A: " + myTable.table.get("A"));

        System.out.println("Z = " + myTable.table.get("D"));
        System.out.println("B = " + myTable.table.get("B"));
    }

}

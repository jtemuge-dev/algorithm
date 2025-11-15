import dataStructures.HashTable;

public class MyHashTable {

    public HashTable table = new HashTable(101);

    public Object updateElement(Object theKey, Object theElement) {

        Object element = table.get(theKey);

        if (element== null) {
            return null;
        }
        table.put(theKey, theElement);
        return element;
    }



    public static void main(String[] args) {

        MyHashTable ht = new MyHashTable();

        ht.table.put("A", 10);
        ht.table.put("B", 20);
        ht.table.put("C", 30);

        System.out.println("Before update:");
        System.out.println("A: " + ht.table.get("A"));
        System.out.println("B: " + ht.table.get("B"));
        System.out.println("C: " + ht.table.get("C"));
        System.out.println();

        Object oldA = ht.updateElement("A", 100);
        System.out.println("Update A: old value = " + oldA);
        System.out.println("A now: " + ht.table.get("A"));
        System.out.println();

        Object oldX = ht.updateElement("X", 55);
        System.out.println("Update X: old value = " + oldX + " (should be null)");
        System.out.println();

        System.out.println("After update:");
        System.out.println("A: " + ht.table.get("A"));
        System.out.println("B: " + ht.table.get("B"));
        System.out.println("C: " + ht.table.get("C"));
    }

}

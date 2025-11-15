import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyHashTableTest {

    private MyHashTable myHashTable;

    @BeforeEach
    void setUp() {
        myHashTable = new MyHashTable();
    }

    @Test
    void testUpdateExistingKey() {
        myHashTable.updateElement("a", 1);
        myHashTable.table.put("a", 1);

        Object oldValue = myHashTable.updateElement("a", 99);

        assertEquals(1, oldValue);
        assertEquals(99, myHashTable.table.get("a"));
    }

    @Test
    void testUpdateNonExistingKey() {
        Object result = myHashTable.updateElement("notHere", "something");
        assertNull(result);
    }

    @Test
    void testValueActuallyReplaced() {

        myHashTable.table.put("x", "old");
        myHashTable.updateElement("x", "new");
        assertEquals("new", myHashTable.table.get("x"));
    }

    @Test
    void testOldValueIsReturned() {
        myHashTable.table.put("num", 10);

        Object returned = myHashTable.updateElement("num", 20);
        assertEquals(10, returned);
    }
}

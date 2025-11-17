import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MyHashTableTest {

    private MyHashTable table;

    @BeforeEach
    void setUp() {
        table = new MyHashTable();
        table.getTable().put("A", 10);
        table.getTable().put("B", 20);
        table.getTable().put("C", 30);
    }

    @Test
    void testUpdateElementSuccess() {
        Object old = table.updateElement("B", 200);

        assertEquals(20, old);
        assertEquals(200, table.getTable().get("B"));
    }

    @Test
    void testUpdateElementKeyNotFound() {
        Object result = table.updateElement("Z", 999);

        assertNull(result);
        assertNull(table.getTable().get("Z"));
    }

    @Test
    void testUpdateKeySuccess() {
        Object moved = table.updateKey("C", "Z");

        assertEquals(30, moved);
        assertNull(table.getTable().get("C"));
        assertEquals(30, table.getTable().get("Z"));
    }

    @Test
    void testUpdateKeyOldKeyDoesNotExist() {
        Object moved = table.updateKey("X", "Y");

        assertNull(moved);
        assertNull(table.getTable().get("Y"));
    }

    @Test
    void testDeleteSuccess() {
        Object deleted = table.delete("A");

        assertEquals(10, deleted);
        assertNull(table.getTable().get("A"));
    }

    @Test
    void testDeleteKeyNotFound() {
        Object deleted = table.delete("Z");

        assertNull(deleted);
    }
}

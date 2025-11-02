import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

public class MyStackTest {

    private MyStack myStack;

    @BeforeEach
    void setUp() {
        myStack = new MyStack();
    }

    @Test
    void testSplitStack_EvenSize() {

        myStack.stack.push(10);
        myStack.stack.push(20);
        myStack.stack.push(30);
        myStack.stack.push(40);

        myStack.splitStack();

        Stack<Integer> expectedFirst = new Stack<>();
        expectedFirst.push(10);
        expectedFirst.push(20);

        Stack<Integer> expectedSecond = new Stack<>();
        expectedSecond.push(30);
        expectedSecond.push(40);

        assertEquals(expectedFirst, myStack.firstHalf, "First half should contain first 2 elements");
        assertEquals(expectedSecond, myStack.secondHalf, "Second half should contain last 2 elements");
    }

    @Test
    void testSplitStack_OddSize() {

        myStack.stack.push(1);
        myStack.stack.push(2);
        myStack.stack.push(3);
        myStack.stack.push(4);
        myStack.stack.push(5);

        myStack.splitStack();

        Stack<Integer> expectedFirst = new Stack<>();
        expectedFirst.push(1);
        expectedFirst.push(2);
        expectedFirst.push(3);

        Stack<Integer> expectedSecond = new Stack<>();
        expectedSecond.push(4);
        expectedSecond.push(5);

        assertEquals(expectedFirst, myStack.firstHalf, "First half should have first 3 elements");
        assertEquals(expectedSecond, myStack.secondHalf, "Second half should have last 2 elements");
    }

    @Test
    void testCombineStack() {

        myStack.firstHalf.push(1);
        myStack.firstHalf.push(2);
        myStack.secondHalf.push(3);
        myStack.secondHalf.push(4);

        myStack.combineStack();

        Stack<Integer> expected = new Stack<>();
        expected.push(1);
        expected.push(2);
        expected.push(3);
        expected.push(4);

        Stack<Integer> actual = new Stack<>();
        for (int i = 0; i < myStack.firstHalf.size(); i++) {
            actual.push(myStack.firstHalf.get(i));
        }
        for (int i = 0; i < myStack.secondHalf.size(); i++) {
            actual.push(myStack.secondHalf.get(i));
        }

        assertEquals(expected, actual, "Combined stack should contain all elements in order");
    }

    @Test
    void testSize() {

        myStack.stack.push(5);
        myStack.stack.push(10);

        assertEquals(2, myStack.size(), "Size should return 2");
    }
}

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

public class ExpressionTest {

    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void testBuildFromPostfix() {
        Expression exp = new Expression();
        String[] postfix = {"a", "b", "c", "*", "+"};
        exp.buildFromPostfix(postfix);

        out.reset();
        exp.printInfix();
        String printed = out.toString().trim();

        assertEquals("Infix: (a+(b*c))", printed);
    }

    @Test
    void testBuildFromPrefix() {
        Expression exp = new Expression();
        String[] prefix = {"+", "a", "*", "b", "c"};
        exp.buildFromPrefix(prefix);

        out.reset();
        exp.printPostfix();
        String printed = out.toString().trim();

        assertEquals("Postfix: a b c * +", printed);
    }

    @Test
    void testBuildFromInfix() {
        Expression exp = new Expression();
        String[] infix = {"(", "a", "+", "(", "b", "*", "c", ")", ")"};
        exp.buildFromInfix(infix);

        out.reset();
        exp.printPrefix();
        String printed = out.toString().trim();

        assertEquals("Prefix: + a * b c", printed);
    }

    @Test
    void testEvaluateNumeric() {
        Expression exp = new Expression();
        String[] postfix = {"2", "3", "4", "+", "*"};
        exp.buildFromPostfix(postfix);

        double result = exp.evaluate();

        assertEquals(14.0, result);
    }

    @Test
    void testEvaluateVariables() {
        Expression exp = new Expression();
        String[] postfix = {"a", "b", "c", "*", "+"};
        exp.buildFromPostfix(postfix);

        String input = "2\n3\n4\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        double result = callEvaluatePrivate(exp, mockScanner);

        assertEquals(14.0, result);
    }

    private double callEvaluatePrivate(Expression exp, Scanner sc) {
        try {
            var method = Expression.class.getDeclaredMethod("evaluate",
                    Expression.Node.class, Scanner.class);
            method.setAccessible(true);
            return (double) method.invoke(exp, exp.getRoot(), sc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

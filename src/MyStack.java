import java.util.*;
import java.util.Stack;

public class MyStack  extends Stack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> firstHalf = new Stack<>();
    Stack<Integer> secondHalf = new Stack<>();
    Scanner sc = new Scanner(System.in);

    
    public int size(){
        return stack.size();
    }



    public void inputStack() {
        System.out.println("Enter the size of stack:");
        int size = sc.nextInt();
        System.out.println("Enter your elements:");

        for (int i = 0; i < size; i++) {
            int element = sc.nextInt();
            stack.push(element);
        }
    }



    public void printStack() {
        if (stack.isEmpty()){
            System.out.println("Stack is empty");
        }else{
            System.out.println("Stack elements: " +  stack);
        }
    }



    public void splitStack(){
        int half = (stack.size() + 1 ) / 2 ;

        for (int i = 0; i < stack.size(); i++ ) {
            int temp = stack.get(i);
            if ( i < half ) {
                firstHalf.push(temp);
            } else {
                secondHalf.push(temp);
            }
        }
        System.out.println("First Half Stack: " + firstHalf);
        System.out.println("Second Half Stack: " + secondHalf);
    }



    public void combineStack() {
        Stack<Integer> combinedStack = new Stack<>();

        for (int i = 0; i < firstHalf.size(); i++ ) {
            combinedStack.push(firstHalf.get(i));
        }
        for  (int i = 0; i < secondHalf.size(); i++ ) {
            combinedStack.push(secondHalf.get(i));
        }
        System.out.println("Combined Stack: " + combinedStack);
    }



    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.inputStack();
        s.printStack();
        s.splitStack();
        s.combineStack();
    }
}

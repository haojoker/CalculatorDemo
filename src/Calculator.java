import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private Stack<Operation> undoStack = new Stack<>();
    private Stack<Operation> redoStack = new Stack<>();

    /**
     * 加法
     * @param x
     * @param y
     * @return
     */
    public int add(int x, int y) {
        int result = x + y;
        undoStack.push(new Operation(x, y, '+', result));
        return result;
    }

    /**
     * 减法
     * @param x
     * @param y
     * @return
     */
    public int subtract(int x, int y) {
        int result = x - y;
        undoStack.push(new Operation(x, y, '-', result));
        return result;
    }

    /**
     * 乘法
     * @param x
     * @param y
     * @return
     */
    public int multiply(int x, int y) {
        int result = x * y;
        undoStack.push(new Operation(x, y, '*', result));
        return result;
    }

    /**
     * 除法
     * @param x
     * @param y
     * @return
     */
    public float divide(int x, int y) {
        float result = (float) x / y;
        undoStack.push(new Operation(x, y, '/', result));
        return result;
    }

    /**
     * undo操作
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Operation op = undoStack.pop();
            redoStack.push(op);
            System.out.printf("%d %c %d = %g \n", op.operandA, op.operator, op.operandB, op.result);
        }
    }

    /**
     * redo操作
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Operation op = redoStack.pop();
            undoStack.push(op);
            System.out.printf("%d %c %d = %g \n", op.operandA, op.operator, op.operandB, op.result);
        }
    }

    private static class Operation {
        private int operandA;
        private int operandB;
        private char operator;
        private double result;

        Operation(int operandA, int operandB, char operator, double result) {
            this.operandA = operandA;
            this.operandB = operandB;
            this.operator = operator;
            this.result = result;
        }
    }

    public static void main(String[] args) {
        System.out.println("计算器demo");
        Calculator calculator = new Calculator();
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        char operator = in.next().charAt(0);
        int b = in.nextInt();
        switch (operator){
            case "+" : calculator.add(a,b);break;
            case "-" : calculator.subtract(a,b);break;
            case "*" : calculator.multiply(a,b);break;
            case "/" : calculator.subtract(a,b);break;
            case "r" : calculator.redo();break;
            case "u" : calculator.undo();break;
            default:
                System.out.println("输入错误 ，请重新输入");
        }
    }
}
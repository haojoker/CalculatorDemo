import java.math.BigDecimal;
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
    public BigDecimal add(double x, double y) {
        BigDecimal result = new BigDecimal(x).add(new BigDecimal(y));
        undoStack.push(new Operation(x, y, '+', result));
        return result;
    }

    /**
     * 减法
     * @param x
     * @param y
     * @return
     */
    public BigDecimal subtract(double x, double y) {
        BigDecimal result = new BigDecimal(x).subtract(new BigDecimal(y));
        undoStack.push(new Operation(x, y, '-', result));
        return result;
    }

    /**
     * 乘法
     * @param x
     * @param y
     * @return
     */
    public BigDecimal multiply(double x, double y) {
        BigDecimal result = new BigDecimal(x).multiply(new BigDecimal(y));
        undoStack.push(new Operation(x, y, '*', result));
        return result;
    }

    /**
     * 除法
     * @param x
     * @param y
     * @return
     */
    public BigDecimal divide(double x, double y) {
        BigDecimal result = new BigDecimal(x).divide(new BigDecimal(y));
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
        private double operandA;
        private double operandB;
        private char operator;
        private BigDecimal result;

        Operation(double operandA, double operandB, char operator, BigDecimal result) {
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
        double a = in.nextDouble();
        char operator = in.next().charAt(0);
        double b = in.nextDouble();
        switch (operator){
            case "+" : calculator.add(a,b);break;
            case "-" : calculator.subtract(a,b);break;
            case "*" : calculator.multiply(a,b);break;
            case "/" : calculator.divide(a,b);break;
            case "r" : calculator.redo();break;
            case "u" : calculator.undo();break;
            default:
                System.out.println("输入错误 ，请重新输入");
        }
    }
}
package 动态数组;

import java.util.Arrays;

public class InfixCalculator {
    public static void main(String[] args) {
        String expression = "(10+20/2*3)/2+8";
        int result = evaluate(expression);
        System.out.println(result);
    }

    public static int evaluate(String expression) {
        expression = format(expression);
        String[] tokens = expression.split(" ");
        ArrayStack<Character> operatorStack = new ArrayStack<>();
        ArrayStack<Integer> numberStack = new ArrayStack<>();
        for (String token : tokens) {
            if (token.length() != 0) {
                switch (token) {
                    case "+", "-" -> {
                        // 如果是空 直接进
                        // 如果不是空栈顶是( 直接进
                        // 如果不是空栈顶是 + - * / 需要处理栈顶 直到进栈为止
                        while (!operatorStack.isEmpty() &&
                                (operatorStack.peek() == '+' ||
                                        operatorStack.peek() == '-' ||
                                        operatorStack.peek() == '/' ||
                                        operatorStack.peek() == '*')) {
                            process(numberStack, operatorStack);
                        }
                        operatorStack.push(token.charAt(0));
                    }
                    case "*", "/" -> {
                        // 如果是空 直接进
                        // 如果不是空 栈顶是(直接进
                        // 如果不是空 栈顶是+ - 直接进
                        // 如果不是空 栈顶是 * / 需要处理栈顶 直到能进栈为止
                        while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                            process(numberStack, operatorStack);
                        }
                        operatorStack.push(token.charAt(0));
                    }
                    case "(" -> operatorStack.push('(');
                    case ")" -> {
                        // 只要当前栈顶不是 ( 都处理掉
                        while (operatorStack.peek() != '(') {
                            process(numberStack, operatorStack);
                        }
                        operatorStack.pop();
                    }
                    default -> numberStack.push(Integer.valueOf(token));
                }
            }
        }
        // 遍历完毕表达式, 栈中还有数据没处理
        while (!operatorStack.isEmpty()) {
            process(numberStack, operatorStack);
        }
        // 对于数字格式不匹配的问题会返回一个错误的结果, 所以检查是否合法抛出异常
        if (numberStack.size() != 1) {
            throw new IllegalArgumentException("Illegal");
        }
        return numberStack.pop();
    }

    private static void process(ArrayStack<Integer> numberStack, ArrayStack<Character> operatorStack) {
        char op = operatorStack.pop();
        int num1 = numberStack.pop();
        int num2 = numberStack.pop();
        switch (op) {
            case '+' -> numberStack.push(num2 + num1);
            case '-' -> numberStack.push(num2 - num1);
            case '*' -> numberStack.push(num2 * num1);
            case '/' -> numberStack.push(num2 / num1);
        }
    }

    static String format(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '/' || c == '*' || c == '(' || c == ')' ) {
                sb.append(' ');
                sb.append(c);
                sb.append(' ');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

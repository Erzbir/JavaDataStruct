package com.erzbir.dataStruct.MutableArray;

public class SuffixCalculator {
    public static void main(String[] args) {
        String expression = "(10+20/2*3)/2+8";
        expression = InfixToSuffix.infixToSuffix(expression);
        System.out.println(evaluate(expression));
    }

    private static int evaluate(String expression) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            if (token.length() == 0) {
                continue;
            }
            if (InfixToSuffix.isOperator(token)) {
                process(stack, token);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Illegal");
        }
        return stack.pop();

    }

    private static void process(ArrayStack<Integer> stack, String token) {
        int num1 = stack.pop();
        int num2 = stack.pop();
        switch (token) {
            case "+" -> stack.push(num1 + num2);
            case "-" -> stack.push(num2 - num1);
            case "*" -> stack.push(num1 * num2);
            case "/" -> stack.push(num2 / num1);
        }
    }
}

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
        for (String token : tokens) {
            if (token.length() != 0) {

            }
        }
        System.out.println(expression);
        System.out.println(Arrays.toString(tokens));
        return -1;
    }
    private static String format(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '/' || c == '(' || c == ')') {
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

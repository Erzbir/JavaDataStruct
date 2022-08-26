package 动态数组;

public class InfixToSuffix {
    public static void main(String[] args) {
        String expression = "(10+20/2*3)/2+8";
        expression = infixToSuffix(expression);
        System.out.println(expression);
    }

    public static String infixToSuffix(String expression) {
        ArrayStack<String> opStack = new ArrayStack<>();
        ArrayList<String> suffixList = new ArrayList<>();
        expression = InfixCalculator.format(expression);
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            if (token.length() == 0) {
                continue;
            }
            if (isOperator(token)) {
                while (true) {
                    if (opStack.isEmpty() || opStack.peek().equals("(")) {
                        opStack.push(token);
                        break;
                    }
                    if (priority(token) > priority(opStack.peek())) {
                        opStack.push(token);
                        break;
                    }

                    suffixList.add(opStack.pop());
                }
            } else if (isNumber(token)) {
                suffixList.add(token);
            } else if (token.equals("(")) {
                opStack.push("(");
            } else if (token.equals(")")) {
                while (!opStack.peek().equals("(")) {
                    suffixList.add(opStack.pop());
                }
                opStack.pop();
            }
        }
        while (!opStack.isEmpty()) {
            suffixList.add(opStack.pop());
        }
        StringBuilder ret = new StringBuilder();
        for (String token : suffixList) {
            if (isNumber(token) || isOperator(token)) {
                ret.append(token).append(" ");
            }
        }
        return ret.toString();
    }

    private static int priority(String token) {
        if (token.equals("*") || token.equals("/")) {
            return 1;
        }
        if (token.equals("+") || token.equals("-")) {
            return 0;
        }
        throw new IllegalArgumentException("Illegal");
    }

    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static boolean isNumber(String token) {
        return token.matches("\\d+");
    }


}

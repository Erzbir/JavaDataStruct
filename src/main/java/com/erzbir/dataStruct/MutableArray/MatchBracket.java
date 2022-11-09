package com.erzbir.dataStruct.MutableArray;

public class MatchBracket {
    public static void main(String[] args) {
        System.out.println(matchBracket("[{}[]]"));
    }

    public static boolean matchBracket(String s) {
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char p = stack.peek();
                if (p - c == -1 || p - c == -2) {
                    stack.pop();
                } else {
                    stack.push(c);
                }

            }
        }
        return stack.isEmpty();
    }
}

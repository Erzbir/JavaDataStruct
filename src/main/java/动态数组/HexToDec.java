package 动态数组;

public class HexToDec {
    public static void main(String[] args) {
        System.out.println(hexToDec("9FBF1"));
    }

    public static int hexToDec(String hex) {
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < hex.length(); i++) {
            stack.push(hex.charAt(i));
        }
        int num = 0;
        int exponent = 0;
        while (!stack.isEmpty()) {
            char c = stack.pop();
            num += getNumber(c) * Math.pow(16, exponent++);
        }
        return num;
    }
    private static int getNumber(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        throw new IllegalArgumentException("Illegal");
    }
}

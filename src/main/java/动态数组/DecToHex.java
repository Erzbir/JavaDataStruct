package 动态数组;

public class DecToHex {
    public static void main(String[] args) {
        System.out.println(decToHex01(654321));
        System.out.println(decToHex02(654321));
    }

    public static String decToHex01(int num) {
        ArrayStack<Character> stack = new ArrayStack<>();
        StringBuilder hex = new StringBuilder();
        while (num != 0) {
            int mod = num % 16;
            if (mod < 10) {
                stack.push((char) ('0' + mod));
            } else {
                stack.push((char) ('A' + mod - 10));
            }
            num /= 16;
        }
        while (!stack.isEmpty()) {
            hex.append(stack.pop());
        }
        return hex.toString();
    }

    public static String decToHex02(int num) {
        String hex = "";
        while (num != 0) {
            int mod = num % 16;
            if (mod < 10) {
                hex = (char) ('0' + mod) + hex;
            } else {
                hex = (char) ('A' + mod - 10) + hex;
            }
            num /= 16;
        }
        return hex;
    }
}

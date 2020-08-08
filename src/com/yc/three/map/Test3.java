public class Test3 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("abcdedcba"));
    }
    private static boolean isPalindrome(String s){
        if (s.length()<=1){
            return true;
        }else if (s.charAt(0)!=s.charAt(s.length()-1)){
            return false;
        }else {
            String newString =s.substring(1,s.length()-1);
            return isPalindrome(newString);
        }
    }
}


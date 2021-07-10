import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        System.out.println(isInteger("123458"));;
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}

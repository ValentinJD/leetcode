
import java.util.*;

public class RomanToInt {

    public static Map<String, Integer> ROMANS_BY_INT = new HashMap<>();

    static {
        ROMANS_BY_INT.put("I", 1);
        ROMANS_BY_INT.put("V", 5);
        ROMANS_BY_INT.put("X", 10);
        ROMANS_BY_INT.put("L", 50);
        ROMANS_BY_INT.put("C", 100);
        ROMANS_BY_INT.put("D", 500);
        ROMANS_BY_INT.put("M", 1000);
        ROMANS_BY_INT.put("III", 3);
    }

    public static void main(String[] args) {
        String s = "I";
        System.out.println(romanToInt(s));
        String s2 = "II";
        System.out.println(romanToInt(s2));
        String s3 = "VIII";
        System.out.println(romanToInt(s3));
    }

    public static int romanToInt(String s) {
        List<String> strings = charArrayToArrayRoman(s.toCharArray());
        int sum = 0;
        for (String key : strings) {
            Integer integer = ROMANS_BY_INT.get(key);
            if (integer != null) {
                sum += integer;
            } else {
                char[] chars = key.toCharArray();
                Integer oneNumber = ROMANS_BY_INT.get(chars[0] + "");
                Integer twoNumber = ROMANS_BY_INT.get(chars[1] + "");
                sum += twoNumber - oneNumber;
            }
        }
        return sum;
    }

    public static List<String> charArrayToArrayRoman(char[] chars) {
        List<String> romanArray = new ArrayList<>();
        int oneIndex;
        int twoIndex;
        int threeIndex;
        for (int x = 0; x < chars.length; x++) {
            oneIndex = x;
            String one = chars[oneIndex] + "";
            twoIndex = x + 1;
            if (twoIndex <= chars.length - 1) {
                String two = chars[twoIndex] + "";
                if (compare(one, two) > 0) {
                    romanArray.add(one);
                    continue;
                }
                if (compare(one, two) < 0) {
                    romanArray.add(one + two);
                    x = twoIndex;
                    continue;
                }
                if (compare(one, two) == 0) {
                    threeIndex = twoIndex + 1;
                    if (threeIndex <= chars.length - 1) {
                        String three = chars[threeIndex] + "";
                        if (two.equals(three)) {
                            romanArray.add(one);
                            romanArray.add(two);
                            romanArray.add(three);
                            x = threeIndex;
                            continue;
                        }
                    } else {
                        romanArray.add(one);
                        romanArray.add(two);
                        x = twoIndex;
                        continue;
                    }
                }
            }
            romanArray.add(one);
        }
        return romanArray;
    }

    public static int compare(String a, String b) {
        return ROMANS_BY_INT.get(a) - ROMANS_BY_INT.get(b);
    }

}

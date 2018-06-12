package test;

/**
 * @author xuan
 * @create 2018-06-03 16:41
 **/
public class Test32 {

    public static void main(String[] args) {
        String scrStr = "java woaijava,i like jajavava i enjoy java ";
        String delStr = "java";
        String result = recursivedelStr(scrStr, delStr);
        System.out.println(result);
        System.out.println((scrStr.length() - result.length()) / delStr.length());
    }

    private static String recursivedelStr(String scrStr, String delStr) {
        String[] split = scrStr.split(delStr);
        if (split.length == 1) {
            return scrStr;
        }
        StringBuilder builder = new StringBuilder();
        for (String s : split) {
            builder.append(s);
        }
        return recursivedelStr(builder.toString(), delStr);
    }
}

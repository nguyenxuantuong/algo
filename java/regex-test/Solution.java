import java.util.regex.*;

public class Solution {
    public static void main(String[] args){
        String s = "Found value: This order was placed for QT3000! OK?";
        String p = "(.*?)(\\d+)(.*)";

        Pattern pat = Pattern.compile(p);
        Matcher mat = pat.matcher(s);

        if(mat.find()){
            System.out.println("found:" + mat.group(2)); //group: index 1: on ward
        } else {
            System.out.println("not found");
        }
    }
}
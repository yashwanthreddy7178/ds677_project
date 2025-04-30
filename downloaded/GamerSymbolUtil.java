package head.util;

public class GamerSymbolUtil{

    public static String convert(Boolean value) {
        String res;

        if (value == null) {
            res = "-";
        } else if (value){
            res = "X";
        } else {
            res = "0";
        }

        return res;
    }
}

package de.uni_hamburg.informatik.swt.se2.kino.services.parseService;

public class ParseService
{
    private ParseService(){}
    
    public static int parseToInt(String s)
    {
        s=s.trim();
        if(s.matches("-?(((\\d){1,3}(.\\d\\d\\d)*)|\\d*)")){
            s = s.replaceAll("\\.", "");
            boolean negFlag = s.startsWith("-");
            char[] array = s.toCharArray();
            int res = 0;
            int fac = 1;
            for(int i = array.length-1; i >= 0; i--){
                res += parseDigitToInt(array[i], fac);
                fac *= 10;
            }
            return((negFlag)? -res : res);
        }
        else
            return Integer.MIN_VALUE;
    }
    public static double parseToDouble(String s)
    {
        s=s.trim();
        if(s.matches("-?(((\\d){1,3}(.\\d\\d\\d)*)|\\d*),?\\d*")){
            boolean negFlag = s.startsWith("-");
            String pre;
            String post = "";
            if(s.contains(","))
            {
                pre = s.substring(0, s.indexOf(",")).replaceAll("\\.", "");
                post  = s.substring(s.indexOf(",")+1);
            }
            else
                pre = s;
            char[] preArray = pre.toCharArray();
            char[] postArray = post.toCharArray();
            double res = 0.0;
            double fac = 1.0;
            for(int i = preArray.length-1; i >= 0; i--){
                res += parseDigitToDouble(preArray[i], fac);
                fac *= 10;
            }
            fac = 1.0/10.0;
            for(int i = 0; i <= postArray.length-1; i++){
                res += parseDigitToDouble(postArray[i], fac);
                fac /= 10;
            }
            return((negFlag)? -res : res);
        }
        else
            return Double.MIN_VALUE;
    }
    
            private static double parseDigitToDouble(char c,double fac)
            {
            double d;
            switch (c){
            case '1': d = 1.0;
            break;
            case '2': d = 2.0;
            break;
            case '3': d = 3.0;
            break;
            case '4': d = 4.0;
            break;
            case '5': d = 5.0;
            break;
            case '6': d = 6.0;
            break;
            case '7': d = 7.0;
            break;
            case '8': d = 8.0;
            break;
            case '9': d = 9.0;
            break;
            default: d = 0.0;
            break;
            }
            return d*fac;
            }
            private static int parseDigitToInt(char c, int fac)
            {
            int d;
            switch (c){
            case '1': d = 1;
            break;
            case '2': d = 2;
            break;
            case '3': d = 3;
            break;
            case '4': d = 4;
            break;
            case '5': d = 5;
            break;
            case '6': d = 6;
            break;
            case '7': d = 7;
            break;
            case '8': d = 8;
            break;
            case '9': d = 9;
            break;
            default: d = 0;
            break;
            }
            return d*fac;
            }


}

package com.mohammed.mosa.eg.drug_info.utility;

public class Utility {
    public static final String NA = "N/A";
    public static final String DRUG = "drug";
    public static final int shortestLength = 4;

    public static String ToTitle(String text){
        StringBuilder builder = new StringBuilder();
        boolean isNextAreSpace = true;
        if(text != null){
            char[] chars = text.toCharArray();
            for(char c:chars){
                if(c == ' ' || c == '\n' || c == '\t'){
                    isNextAreSpace = true;
                }
                else if(isNextAreSpace){
                    c = Character.toUpperCase(c);
                    isNextAreSpace = false;
                }
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static boolean isNumber(String text){
        char[] chars = text.toCharArray();
        for(char c: chars){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static String fit(String txt){
        return txt.length() <= 30 ? txt: txt.substring(0, 30).trim();
    }


}


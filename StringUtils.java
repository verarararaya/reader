package com.app.utils;

/**
 *
 */
public class StringUtils {
    public static boolean validateUP(String text){
        if(text==null||"".equals(text)){
            return false;
        }else {
            return true;
        }
    }
    public static boolean validatePWD(String pwd,String rpwd){
//        if((pwd==null||"".equals(pwd))||(rpwd==null||"".equals(rpwd))){
//            return false;
//        }else {
//            if(pwd.equals(rpwd)){
//                return true;
//            }else {
//                return false;
//            }
//
//        }
        if(pwd.equals(rpwd)){
                return true;
            }else {
                return false;
            }
    }
}

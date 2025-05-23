package edu.cdtu.utils;

public class UserSaveTool
{
    //11已登录用户的用户名
    private static String currentLoginUsername = null;
    //11已登录用户的用户密码
    private static String currentLoginUserPass = null;

    //getter和setter方法
    public static String getcurrentLoginUsername(){return currentLoginUsername;}
    public static void setcurrentLoginUsername(String currentLoginUsername)
    {
        UserSaveTool.currentLoginUsername = currentLoginUsername;
    }
    public static String getCurrentLoginUserPass() {return currentLoginUserPass;}

    public static void setCurrentLoginUserPass(String currentLoginUserPass)
    {
        UserSaveTool.currentLoginUserPass = currentLoginUserPass;
    }
}

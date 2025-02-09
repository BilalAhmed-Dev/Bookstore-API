package com.backend.test.bookstore.utils;

public class MsgUtil {
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int LOGIN_USER_ERROR = -100;
    public static final int NOT_LOGGED_IN_ERROR = -101;

    public static final String SUCCESS_MSG = "Success!";

    public static final String ERROR_MSG = "Error!";
    public static final String LOGIN_USER_ERROR_MSG = "Username or Password Wrong! Try Again!";
    public static final String NOT_LOGGED_IN_ERROR_MSG = "Login out of Date! Please Login Again!";


    public static  final String SUCCESS_ADDBOOK = "Add Book Successfully!";

    public static  final String SUCCESS_DELETEBOOK = "Delete Book Successfully!";

    public static  final String SUCCESS_EDITBOOK = "Edit Book Successfully!";



    public static  final String ERROR_INVENTORY = "Out of Inventory!";
    public static  final String ERROR_CARTDECREASE = "Only One Left!";



    public static Msg makeMsg(int status, String msg){
        return new Msg(status, msg);
    }
}

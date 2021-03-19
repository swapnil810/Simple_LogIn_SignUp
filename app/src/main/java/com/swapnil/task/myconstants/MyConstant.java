package com.swapnil.task.myconstants;

/**
 * This class is used as .
 *
 * @author CanopusInfoSystems
 * @version 1.0
 * @since 19/3/21 :March : 2021 on 16 : 46.
 */
public class MyConstant {
    public static final String BASE_URL = "http://ec2-18-224-33-209.us-east-2.compute.amazonaws.com/api/v1/";
    public static final String NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String ROLE ="role" ;
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "password_confirmation";
    public static final String X_API_KEY = "Bearer";
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String API_TOKEN = "api_token";
    public static final String MY_TOKEN = "BearereyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9lYzItMTgtMjI0LTMzLTIwOS51cy1lYXN0LTIuY29tcHV0ZS5hbWF6b25hd3MuY29tXC9hcGlcL3YxXC91c2VyIiwiaWF0IjoxNjE2MTU2NDY2LCJleHAiOjE2MTYxNjAwNjYsIm5iZiI6MTYxNjE1NjQ2NiwianRpIjoiQWRTMG5tcXJTb05SbkNHQSIsInN1YiI6Mzg4OSwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.VmLns9zZpRx60AlIxRmmd4EAofU_T37HL8c5WAEh79w";
    public static final String FIRST_NAME = "first_name";
    public static final String USER_NAME = "username";


    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            //android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}

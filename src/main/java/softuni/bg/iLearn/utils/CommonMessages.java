package softuni.bg.iLearn.utils;

public class CommonMessages {

    public static final String EMAIL_CREATION_SUBJECT = "Welcome to iLearn!";
    public static final String EMAIL_SENDER = "alerts.ilearn@gmail.com";
    public static final String EMAIL_ADMIN = "admin@ilearn.com";
    public static final String EMAIL_PASSWORD = "admin";
    public static final String EMAIL_CREATION_BODY = """
            
                Welcome to iLearn!

                Dear %s,
                We are glad to confirm that your account has been successfully created and you may now login with the credentials you provided.
                	 
                Please note: If you have any issues logging in or have forgotten your password you can reset it here: www.ilearn.com/forgot-password.
                Don't forget to subscribe to our weekly newsletter and find our newest courses!
                
 
                The iLearn Customer Service Team
""";

    public static final String EMAIL_CONTACT_BODY = """
            
                From: %s
                Contact email: %s
                
                Message: %s
""";
    public static final String USER_DOESNT_EXIST = "User %s doesn't exist!";
}

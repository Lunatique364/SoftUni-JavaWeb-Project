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

    public static final String SUBSCRIPTION_EMAIL_BODY = """
            
                Hello Adventurer!

                We are pleased to show you this week's top new courses available at iLearn!
                
                %s
 
                The iLearn Customer Service Team
""";

    public static final String SUBSCRIPTION_EMAIL_SUBJECT = "Weekly Newsletter %s - iLearn";

    public static final String RESET_EMAIL_BODY = """
            
                Hello Adventurer!

                Your new password is:
                %s
 
                The iLearn Customer Service Team
""";

    public static final String RESET_EMAIL_SUBJECT = "Reset Password - iLearn";
    public static final String EMAIL_CONTACT_BODY = """
            
                From: %s
                Contact email: %s
                
                Message: %s
""";
    public static final String USER_DOESNT_EXIST = "User %s doesn't exist!";
    public static final String ADMIN_BAN = "Admin banned user %s  on %s";
    public static final String ADMIN_DELETION = "Admin deleted user %s  on %s";
}

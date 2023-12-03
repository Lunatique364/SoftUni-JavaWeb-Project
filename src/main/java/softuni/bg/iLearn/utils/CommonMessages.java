package softuni.bg.iLearn.utils;

public class CommonMessages {

    public static final String EMAIL_CREATION_SUBJECT = "Welcome to iLearn!";
    public static final String EMAIL_SENDER = "alerts.ilearn@gmail.com";
    public static final String EMAIL_ADMIN = "admin@ilearn.com";
    public static final String EMAIL_PASSWORD = "admin";
    public static final String EMAIL_CREATION_BODY = """
            <!DOCTYPE html>
            <html>
            <body>         \s
                <p>Welcome to iLearn!</p>
                <br>
                <p>Dear %s,</p>
                <p>	We are glad to confirm that your account has been successfully created and you may now login with the credentials you provided.</p>
                	 
                <p><b>Please note:</b> If you have any issues logging in or have forgotten your password you can reset it <a href="www.ilearn.com/forgot-password">here</a>.</p>
                <p> Don't forget to subscribe to our weekly newsletter and find our newest courses!</p>
                <br>
 
                <p>The iLearn Customer Service Team</p>
            </body>
            </html>
            """;
    public static final String USER_DOESNT_EXIST = "User %s doesn't exist!";
}

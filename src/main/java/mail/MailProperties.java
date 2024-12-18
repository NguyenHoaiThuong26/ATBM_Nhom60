package mail;

import java.io.IOException;
import java.util.Properties;

public class MailProperties {
    private  static Properties prop = new Properties();

    static {
        try {
            prop.load(MailProperties.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String host = prop.getProperty("mail.smtp.host");
    public static String port = prop.getProperty("mail.smtp.port");
    public static boolean auth = "true".equals(prop.getProperty("mail.smtp.auth"));
    public static boolean ssl = "true".equals(prop.getProperty("mail.smtp.starttls.enable"));
    public static String username = prop.getProperty("mail.user");
    public static String password = prop.getProperty("mail.pass");

    public static void main(String[] args) {
        System.out.println(host);
        System.out.println(port);
        System.out.println(auth);
        System.out.println(ssl);
        System.out.println(username);
        System.out.println(password);
    }
}

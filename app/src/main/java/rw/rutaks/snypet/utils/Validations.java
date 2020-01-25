package rw.rutaks.snypet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Class for handling view validations
 *  @author Sam Rutakayile
 *  @since version 1.0.0
 */
public class Validations {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /**
     * Method To Check if email passed is valid
     *
     * @param email email to check
     * @return boolean value stating if email is valid or not
     */
    public static boolean isValidEmail(String email){
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * Method To Check if password passed is valid
     *
     * @param password email to check
     * @return boolean value stating if password is valid or not
     */
    public static boolean isValidPassword(final String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}

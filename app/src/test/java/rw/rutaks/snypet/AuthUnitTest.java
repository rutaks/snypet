package rw.rutaks.snypet;

import org.junit.Test;

import rw.rutaks.snypet.utils.Validations;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AuthUnitTest {
    @Test
    public void isValidEmail() {
        boolean check = Validations.isValidEmail("rutaksam@gmail.com");
        assertEquals(true, check);
    }

    @Test
    public void isNotValidEmailAlt() {
        boolean check = Validations.isValidEmail("rutaksamgmail.com");
        assertEquals(false, check);
    }

    @Test
    public void isNotValidEmailDot() {
        boolean check = Validations.isValidEmail("rutaksam@gmailcom");
        assertEquals(false, check);
    }

    @Test
    public void isValidPassword() {
        boolean check = Validations.isValidPassword("Illmatic1234@");
        assertEquals(true, check);
    }

    @Test
    public void isNotValidPasswordNoNumber() {
        boolean check = Validations.isValidEmail("Illmaticffff");
        assertEquals(false, check);
    }

    @Test
    public void isNotValidPasswordNoUpperCase() {
        boolean check = Validations.isValidEmail("illmatic123");
        assertEquals(false, check);
    }

    @Test
    public void isNotValidPasswordshort() {
        boolean check = Validations.isValidEmail("illm");
        assertEquals(false, check);
    }
    @Test
    public void isNotValidPasswordNoSpecialCharacter() {
        boolean check = Validations.isValidEmail("illmuefdsafdsa12333");
        assertEquals(false, check);
    }

    @Test
    public void isNotValidPasswordEmpty() {
        boolean check = Validations.isValidEmail("");
        assertEquals(false, check);
    }
}
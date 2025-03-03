package ch.heigvd.amt.projectOne.business;

import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Crypto;
import lombok.Getter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Check data from the identification form
 */
@Getter
public class Identification {

    @EJB
    private UsersDaoLocal userDao;

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private Map<String, String> errors = new HashMap<String, String>();

    public Identification(UsersDaoLocal userDao){
        this.userDao = userDao;
    }

    /**
     * Return a user if all test passed
     * @param req  request
     * @return a user if all test passed
     */
    public User login(HttpServletRequest req) {

        User user = null;

        String email = getValue(req, EMAIL);
        String password = getValue(req, PASSWORD);

        try {
            valideEmail(email);
        } catch (Exception e){
            setError(EMAIL, e.getMessage());
        }

        try {
            valide(email, password);
        } catch (Exception e){
            setError(PASSWORD, e.getMessage());
        }

        if (errors.isEmpty()) {
            user = userDao.connect(email, Crypto.getCryptoHash(password));
        }

        return user;
    }

    private static String getValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter(fieldName);
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        }
        else {
            return value.trim();
        }
    }

    private void setError(String field, String message ) {
        errors.put(field, message );
    }

    /**
     * Check if the email exist or not
     * @param email      email
     * @param password   password
     * @throws Exception
     */
    private void valide(String email, String password) throws Exception {

        if (password != null && email != null ) {
            if(userDao.connect(email, Crypto.getCryptoHash(password)) == null) {
                throw new Exception("Hum, try again.");
            }
        }
        else {
            throw new Exception( "At least write something..." );
        }
    }

    /**
     * Check if the email has a correct format
     * @param email email
     * @throws Exception
     */
    private void valideEmail(String email) throws Exception {

        if (email != null ) {
            if(!userDao.exist(email)) {
                throw new Exception("This email doesn't exist.");
            }
        }
        else {
            throw new Exception( "At least write something..." );
        }
    }
}

package ch.heigvd.amt.projectOne.business;

import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Crypto;
import ch.heigvd.amt.projectOne.utils.DateFormat;
import lombok.Getter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Check data from the registration user form
 */
@Getter
public class RegistrationUser {

    @EJB
    private UsersDaoLocal userDao;

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String DATE = "date";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String COMFIRMATION = "confirmation";

    private Map<String, String> errors = new HashMap<String, String>();

    public RegistrationUser(UsersDaoLocal userDao){
        this.userDao = userDao;
    }

    public User registrationUser(HttpServletRequest req){

        String firstname = getValue(req, FIRSTNAME);
        String lastname = getValue(req, LASTNAME);
        String date = getValue(req, DATE);
        String email = getValue(req, EMAIL);
        String password = getValue( req, PASSWORD);
        String confirmation = getValue( req, COMFIRMATION);

        User user = null;

        try {
            validationEmail( email );
        }
        catch (Exception e) {
            setError(EMAIL, e.getMessage() );
        }

        try {
            validationPassword( password, confirmation );
        }
        catch (Exception e) {
            setError(PASSWORD, e.getMessage() );
            setError(COMFIRMATION, e.getMessage() );
        }

        try {
            validationDate(date);
        }
        catch (Exception e) {
            setError(DATE, e.getMessage() );
        }

        try {
            existEmail(email);
        }
        catch (Exception e) {
            setError(EMAIL, e.getMessage() );
        }

        if (errors.isEmpty() ) {
            user = new User(firstname, lastname, date, email, Crypto.getCryptoHash(password));
            long id = userDao.addUser(user);
            user = userDao.user(id);
        }

        return user;
    }

    private void setError(String field, String message ) {
        errors.put(field, message );
    }

    private static String getValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter(fieldName);
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value.trim();
        }
    }

    /**
     * Check if the password and the confirmation are the same
     * @param password      password
     * @param confirmation  repeted password
     * @throws Exception
     */
    private void validationPassword( String password, String confirmation ) throws Exception {
        if ( password != null && confirmation != null ) {
            if (!password.equals( confirmation ) ) {
                throw new Exception( "There is something different here." );
            }
        } else {
            throw new Exception( "You should write in both password field." );
        }
    }

    /**
     * Check if the date has the correct format and if it's not in the future
     * @param date  date
     * @throws Exception
     */
    private void validationDate( String date) throws Exception {
        if ( date != null ) {
            if (!DateFormat.correctFormatDate(date)) {
                throw new Exception( "It should be dd-mm-yyyy" );
            } else if (!DateFormat.futurDate(date)) {
                throw new Exception( "Nice to meet someone from the future" );
            }
        } else {
            throw new Exception( "You are not that old! You can lie you know..." );
        }
    }

    /**
     * Check the format of the email
     * @param email email
     * @throws Exception
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "You should use a valide Email" );
            }
        } else {
            throw new Exception( "At least write something..." );
        }
    }

    /**
     * Check if the email already exist or not
     * @param email email
     * @throws Exception
     */
    private void existEmail(String email ) throws Exception {
        if ( email != null ) {
            if (userDao.exist(email)){
                throw new Exception( "The email already exist!" );
            }
        } else {
            throw new Exception( "At least write something..." );
        }
    }
}

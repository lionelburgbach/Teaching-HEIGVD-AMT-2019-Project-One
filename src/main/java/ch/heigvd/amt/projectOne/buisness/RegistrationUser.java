package ch.heigvd.amt.projectOne.buisness;

import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Crypto;
import ch.heigvd.amt.projectOne.utils.DateFormat;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        catch ( Exception e ) {
            setError(EMAIL, e.getMessage() );
        }

        try {
            validationPassword( password, confirmation );
        }
        catch ( Exception e ) {
            setError(PASSWORD, e.getMessage() );
            setError(COMFIRMATION, e.getMessage() );
        }

        try {
            validationDate(date);
        }
        catch ( Exception e ) {
            setError(DATE, e.getMessage() );
        }

        if (errors.isEmpty() ) {
            user = new User(firstname, lastname, date, email, Crypto.getCryptoHash(password));
            long id = userDao.addUser(user);
            user = userDao.user(id);
        }

        return user;
    }

    private void setError(String champ, String message ) {
        errors.put( champ, message );
    }

    private static String getValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter(fieldName);
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value.trim();
        }
    }

    private void validationPassword( String password, String confirmation ) throws Exception {
        if ( password != null && confirmation != null ) {
            if ( !password.equals( confirmation ) ) {
                throw new Exception( "There is something different here." );
            }
        } else {
            throw new Exception( "You should write in both password field." );
        }
    }

    private void validationDate( String date) throws Exception {
        if ( date != null ) {
            if (!DateFormat.correctFormatDate(date)) {
                throw new Exception( "It should be dd-mm-yyyy" );
            }
        } else {
            throw new Exception( "You are not that old! You can lie you know..." );
        }
    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "You should use a valide Email" );
            }
        } else {
            throw new Exception( "At least write something..." );
        }
    }
}

package ch.heigvd.amt.projectOne.buisness;

import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Crypto;
import ch.heigvd.amt.projectOne.utils.DateFormat;
import lombok.Getter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Getter
public class UpdateUser {

    @EJB
    private UsersDaoLocal userDao;

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String DATE = "date";
    private static final String PASSWORD = "password";

    private Map<String, String> errors = new HashMap<String, String>();

    public UpdateUser(UsersDaoLocal userDao){
        this.userDao = userDao;
    }

    public boolean updateUser(HttpServletRequest req, String email, String password, long id){

        String firstname = getValue(req, FIRSTNAME);
        String lastname = getValue(req, LASTNAME);
        String date = getValue(req, DATE);
        String newPassword = getValue(req, PASSWORD);

        if (newPassword == null) {
            newPassword = password;
        }
        else{
            newPassword = Crypto.getCryptoHash(newPassword);
        }

        try {
            validationDate(date);
        }
        catch (Exception e) {
            setError(DATE, e.getMessage() );
        }

        if (errors.isEmpty() ) {

            User user = new User(id, firstname, lastname, date, email, newPassword);
            userDao.updateUser(user);
        }
        return (errors.size()==0);
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

    /**
     *
     * @param date that we want to check ( correct format, date is not in futur)
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
}

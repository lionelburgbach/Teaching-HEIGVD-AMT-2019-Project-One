package ch.heigvd.amt.projectOne.buisness;

import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.utils.DateFormat;
import lombok.Getter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Getter
public class RegistrationTrail {

    @EJB
    private TrailDaoLocal trailDao;

    private static final String NAME = "name";
    private static final String DISTANCE = "distance";
    private static final String UP_AND_DOWN = "upAndDown";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "date";

    private Map<String, String> errors = new HashMap<String, String>();

    public RegistrationTrail(TrailDaoLocal trailDao){
        this.trailDao = trailDao;
    }

    public boolean registrationTrail(HttpServletRequest req){

        boolean res = false;

        String name = getValue(req, NAME);
        double distance = Double.parseDouble(getValue(req,DISTANCE));
        double upAndDown  = Double.parseDouble(getValue(req, UP_AND_DOWN));
        String description = getValue(req, DESCRIPTION);
        String date = getValue( req, DATE);

        try {
            validationDistanceAndUpAndDown(distance, upAndDown);
        }
        catch ( Exception e ) {
            setError(DISTANCE, e.getMessage() );
            setError(UP_AND_DOWN, e.getMessage() );
        }

        try {
            validationDate(date);
        }
        catch ( Exception e ) {
            setError(DATE, e.getMessage() );
        }

        if (errors.isEmpty() ) {
            long id = -1;
            id = trailDao.addTrail(new Trail(name, distance, upAndDown, description, date));

            if (id >= 0) {
                res = true;
            }
        }

        return res;
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

    private void validationDate( String date) throws Exception {
        if ( date != null ) {
            if (!DateFormat.correctFormatDate(date)) {
                throw new Exception( "It should be dd-mm-yyyy" );
            }
            else if(!DateFormat.possibleDate(date)) {
                throw new Exception( "Time travel ? nice" );
            }
        }
        else {
            throw new Exception( "You are not that old! You can lie you know..." );
        }
    }

    private void validationDistanceAndUpAndDown( double upAndDown, double distance ) throws Exception {
        if (upAndDown < 0 || distance < 0 ) {
            throw new Exception( "You can run -2m, really ?" );
        }
    }
}

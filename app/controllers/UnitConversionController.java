package controllers;

import play.mvc.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class UnitConversionController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index(String units) {
        Pattern inputPattern = Pattern.compile("[(][A-z]+[/][A-z]+[)]");
        Matcher inputMatcher = inputPattern.matcher(units);
        boolean validInput = inputMatcher.matches();

        if(validInput){
            units = units.replaceAll("[(]|[)]","");

            String[] parsedUnits = units.split("/");


            return ok(units);
        }

        return ok(views.html.index.render());
    }

}

package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import play.libs.Json;
import play.mvc.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class UnitConversionController extends Controller {

    public final double pi = Math.PI;
    public final double minute = 60;
    public final double hour = 3600;
    public final double day = 86400;
    public final double degree = (pi/180);
    public final double arcminute = (pi/10800);
    public final double arcsecond = (pi/648000);
    public final double hectare = 10000;
    public final double litre = 0.001;
    public final double tonne = 1000;

    public final String[] variables = {
            "minute","min",
            "hour","h",
            "day","d",
            "degree","D",
            "arcminute","M",
            "arcsecond","S",
            "hectare","ha",
            "litre","L",
            "tonne","t"
    };


    public Result index(String units) {
        ObjectNode response = Json.newObject();

        if(units == null || units.isEmpty() || units.equals("")){
            response.put("error","units cannot be null or empty");
        }else{

            try{

                units = units
                        .replace('°','D')
                        .replace('\'','M')
                        .replace('“','S');

                Expression e = new ExpressionBuilder(units)
                        .variables(variables)
                        .build()
                        .setVariable(variables[0],minute)
                        .setVariable(variables[1],minute)
                        .setVariable(variables[2],hour)
                        .setVariable(variables[3],hour)
                        .setVariable(variables[4],day)
                        .setVariable(variables[5],day)
                        .setVariable(variables[6],degree)
                        .setVariable(variables[7],degree)
                        .setVariable(variables[8],arcminute)
                        .setVariable(variables[9],arcminute)
                        .setVariable(variables[10],arcsecond)
                        .setVariable(variables[11],arcsecond)
                        .setVariable(variables[12],hectare)
                        .setVariable(variables[13],hectare)
                        .setVariable(variables[14],litre)
                        .setVariable(variables[15],litre)
                        .setVariable(variables[16],tonne)
                        .setVariable(variables[17],tonne);


                String unitName = units
                        .replaceAll(regexGen(variables[0]),"s")
                        .replaceAll(regexGen(variables[1]),"s")
                        .replaceAll(regexGen(variables[2]),"s")
                        .replaceAll(regexGen(variables[3]),"s")
                        .replaceAll(regexGen(variables[4]),"s")
                        .replaceAll(regexGen(variables[5]),"s")
                        .replaceAll(regexGen(variables[6]),"rad")
                        .replaceAll(regexGen(variables[7]),"rad")
                        .replaceAll(regexGen(variables[8]),"rad")
                        .replaceAll(regexGen(variables[9]),"rad")
                        .replaceAll(regexGen(variables[10]),"rad")
                        .replaceAll(regexGen(variables[11]),"rad")
                        .replaceAll(regexGen(variables[12]),"m²")
                        .replaceAll(regexGen(variables[13]),"m²")
                        .replaceAll(regexGen(variables[14]),"m³")
                        .replaceAll(regexGen(variables[15]),"m³")
                        .replaceAll(regexGen(variables[16]),"kg")
                        .replaceAll(regexGen(variables[17]),"kg")
                        .replaceAll(" ","");

                double result = e.evaluate();
                BigDecimal bd = new BigDecimal(result);
                bd = bd.round(new MathContext(14));
                response.put("unit_name",unitName);
                response.put("multiplication_factor",bd);

            }catch(Exception e){
                response.put("error","error while parsing expression");
            }
        }



        return ok(response);
    }

    public String regexGen(String s){
        String retorno  = "(?<![A-z])("+s+")(?![A-z])" ;

        return retorno;
    }

}

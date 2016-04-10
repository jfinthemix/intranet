package proyecto_uoct.reservas.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.*;

public class Parse {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static Long newLong(String n) {
        if(n != null && !n.trim().equals(""))
            return new Long(n);
        else
            return null;
    }

    public static Date newDate(String f) {
        if(f != null && !f.trim().equals(""))
            try {
                return dateFormat.parse(f);
            } catch (ParseException ex) {
                return null;
            }
        else
            return null;
    }

    public static Date newTime(String h) {
        if(h != null && !h.trim().equals(""))
            try {
                return timeFormat.parse(h);
            } catch (ParseException ex) {
                return null;
            }
        else
            return null;
    }

    public static String quitaDosPuntos(String conP){
        try{
            String sinP = conP.substring(0, conP.indexOf(":")) +
                          conP.substring(conP.indexOf(":") + 1);
            return sinP;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}

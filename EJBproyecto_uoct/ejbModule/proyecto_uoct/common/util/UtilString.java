package proyecto_uoct.common.util;
import proyecto_uoct.common.util.UtilVenta;

public class UtilString extends proyecto_uoct.common.util.UtilVenta {
    public String validaStringParaBD(String cadena) {
        char us = 95;
        StringBuffer sb = new StringBuffer(cadena);
        for (int i = 0; i < cadena.length(); i++) {
            char a = cadena.charAt(i);
            int ascii = a;
            if (a < 65 || a > 122 || (a > 90 && a < 97)) {
                sb.setCharAt(i, us);
            }
        }
        return sb.toString();
    }


    public String formatoFecha(String fecha) {
        String mes = fecha.substring(3, 5);
        int mesi = (Integer.valueOf(mes)).intValue();
        switch (mesi) {
        case 1:
            mes = "Enero";
            break;
        case 2:
            mes = "Febrero";
            break;
        case 3:
            mes = "Marzo";
            break;
        case 4:
            mes = "Abril";
            break;
        case 5:
            mes = "Mayo";
            break;
        case 6:
            mes = "Junio";
            break;
        case 7:
            mes = "Julio";
            break;
        case 8:
            mes = "Agosto";
            break;
        case 9:
            mes = "Septiembre";
            break;
        case 10:
            mes = "Octubre";
            break;
        case 11:
            mes = "Noviembre";
            break;
        case 12:
            mes = "Diciembre";
            break;
        }
        fecha = fecha.substring(0, 2) + " de " + mes;
        return fecha;

    }


}

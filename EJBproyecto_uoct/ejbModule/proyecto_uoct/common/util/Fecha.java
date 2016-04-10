package proyecto_uoct.common.util;


import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Fecha {
    public Fecha() {
    }


    private static Fecha f = null;

    public static Fecha getInstance() {
        if (f == null) {
            f = new Fecha();
        }
        return f;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


    /**
     * getDiadeSemana
     * recibe como parámetro una fecha y un entero que representa un día de la
     * semana, de acuerdo a la siguiente tabla:
     * 1: Domingo
     * 2: Lunes
     * 3: Martes
     * 4: Miercoles
     * 5: jueves
     * 6: Viernes
     * 7: Sábado
     * el resultado de este metodo es una fecha correspondiente al día indicado
     * como parametro,  de la semana en que se encuentra la  fecha entregada
     * como parametro. es decir, si se entrega la fecha 21-04-09, que
     * corresponde a un dia martes, y  el parametro 2,  el resultado sera  la
     * fecha 20-04-09, es decir el dia lunes(parámetro 2) de esa semana.
     *
     * @param dia Date
     * @param elquesebusca int
     * @return Date
     */
    public java.util.Date getDiadeSemana(java.util.Date dia, int elquesebusca) {


        Calendar dia_C = Calendar.getInstance();
        dia_C.setTime(dia);
        int dia_de_la_semana = dia_C.get(Calendar.DAY_OF_WEEK);
        java.util.Date dia_D = new Date(dia_C.getTimeInMillis());
        switch (dia_de_la_semana) {
        case 1: { //es domingo

            switch (elquesebusca) {
            case 1: { // se busca un domingo
                break;
            }
            case 2: { //se busca un lunes
                dia_D = this.fechaMenos(dia_D, 6);
                break;
            }
            case 3: { //se busca un martes
                dia_D = this.fechaMenos(dia_D, 5);
                break;
            }
            case 4: { //se busca un miércoles
                dia_D = this.fechaMenos(dia_D, 4);
                break;
            }
            case 5: { //se busca un jueves
                dia_D = this.fechaMenos(dia_D, 3);
                break;
            }
            case 6: { //se busca un viernes
                dia_D = this.fechaMenos(dia_D, 2);
                break;
            }
            case 7: { //se busca un sábado
                dia_D = this.fechaMenos(dia_D, 1);
                break;
            }
            }

            return dia_D;

        }

        case 2: { //es lunes
            switch (elquesebusca) {
            case 1: { // se busca un domingo
                dia_D = this.fechaMas(dia_D, 6);
                break;
            }
            case 2: { //se busca un lunes
                break;
            }
            case 3: { //se busca un martes
                dia_D = this.fechaMas(dia_D, 1);
                break;
            }
            case 4: { //se busca un miércoles
                dia_D = this.fechaMas(dia_D, 2);
                break;
            }
            case 5: { //se busca un jueves
                dia_D = this.fechaMas(dia_D, 3);
                break;
            }
            case 6: { //se busca un viernes
                dia_D = this.fechaMas(dia_D, 4);
                break;
            }
            case 7: { //se busca un sábado
                dia_D = this.fechaMas(dia_D, 5);
                break;
            }
            } //fin switch
            return dia_D;
        }

        case 3: { //es martes
            switch (elquesebusca) {
            case 1: { // se busca un domingo
                dia_D = this.fechaMas(dia_D, 5);
                break;
            }
            case 2: { //se busca un lunes
                dia_D = this.fechaMenos(dia_D, 1);
                break;
            }
            case 3: { //se busca un martes
                break;
            }
            case 4: { //se busca un miércoles
                dia_D = this.fechaMas(dia_D, 1);
                break;
            }
            case 5: { //se busca un jueves
                dia_D = this.fechaMas(dia_D, 2);
                break;
            }
            case 6: { //se busca un viernes
                dia_D = this.fechaMas(dia_D, 3);
                break;

            }
            case 7: { //se busca un sábado
                dia_D = this.fechaMas(dia_D, 4);
                break;
            }

            } //fin switch

            return dia_D;
        }

        case 4: { //es miércoles
            switch (elquesebusca) {
            case 1: { // se busca un domingo
                dia_D = this.fechaMas(dia_D, 4);
                break;
            }
            case 2: { //se busca un lunes
                dia_D = this.fechaMenos(dia_D, 2);
                break;
            }
            case 3: { //se busca un martes
                dia_D = this.fechaMenos(dia_D, 1);
                break;
            }
            case 4: { //se busca un miércoles
                break;
            }
            case 5: { //se busca un jueves
                dia_D = this.fechaMas(dia_D, 1);
                break;
            }
            case 6: { //se busca un viernes
                dia_D = this.fechaMas(dia_D, 2);
                break;
            }
            case 7: { //se busca un sábado
                dia_D = this.fechaMas(dia_D, 3);
                break;
            }
            } //fin switch
            return dia_D;
        }

        case 5: { //es jueves
            switch (elquesebusca) {
            case 1: { // se busca un domingo
                dia_D = this.fechaMas(dia_D, 3);
                break;
            }
            case 2: { //se busca un lunes
                dia_D = this.fechaMenos(dia_D, 3);
                break;
            }
            case 3: { //se busca un martes
                dia_D = this.fechaMenos(dia_D, 2);
                break;
            }
            case 4: { //se busca un miércoles
                dia_D = this.fechaMenos(dia_D, 1);
                break;
            }
            case 5: { //se busca un jueves
                break;

            }
            case 6: { //se busca un viernes
                dia_D = this.fechaMas(dia_D, 1);
                break;
            }
            case 7: { //se busca un sábado
                dia_D = this.fechaMas(dia_D, 2);
                break;
            }
            } //fin switch
            return dia_D;
        }

        case 6: { //es viernes
            switch (elquesebusca) {
            case 1: { // se busca un domingo
                dia_D = this.fechaMas(dia_D, 2);
                break;
            }
            case 2: { //se busca un lunes
                dia_D = this.fechaMenos(dia_D, 4);
                break;
            }
            case 3: { //se busca un martes
                dia_D = this.fechaMenos(dia_D, 3);
                break;
            }
            case 4: { //se busca un miércoles
                dia_D = this.fechaMenos(dia_D, 2);
                break;
            }
            case 5: { //se busca un jueves
                dia_D = this.fechaMenos(dia_D, 1);
                break;
            }
            case 6: { //se busca un viernes
                break;
            }
            case 7: { //se busca un sábado
                dia_D = this.fechaMas(dia_D, 1);
                break;
            }
            } //fin switch
            return dia_D;
        }

        case 7: { //es sábado
            switch (elquesebusca) {
            case 1: { // se busca un domingo
                dia_D = this.fechaMas(dia_D, 1);
                break;
            }
            case 2: { //se busca un lunes
                dia_D = this.fechaMenos(dia_D, 5);
                break;
            }
            case 3: { //se busca un martes
                dia_D = this.fechaMenos(dia_D, 4);
                break;
            }
            case 4: { //se busca un miércoles
                dia_D = this.fechaMenos(dia_D, 3);
                break;
            }
            case 5: { //se busca un jueves
                dia_D = this.fechaMenos(dia_D, 2);
                break;
            }
            case 6: { //se busca un viernes
                dia_D = this.fechaMenos(dia_D, 1);
                break;
            }
            case 7: { //se busca un sábado
                break;

            }
            } //fin switch
            return dia_D;
        }

        }

        return dia_D;

    }


    public java.util.Date fechaMas(java.util.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    public java.util.Date fechaMenos(java.util.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new Date(cal.getTimeInMillis());
    }


}

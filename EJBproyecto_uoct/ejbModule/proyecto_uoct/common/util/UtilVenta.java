package proyecto_uoct.common.util;

import proyecto_uoct.dao.VentasDAO;

public class UtilVenta {
    public UtilVenta() {
    }

    private float iva = (float) 0.19;
    private float uf = this.getUF();

    private float getUF() {
        VentasDAO d = VentasDAO.getInstance();
        return d.getUF();
    }


    /**
     * precioEnPesos
     * Multiplica el valor recibido como parametro por el valor de la UF
     * registrado en el modulo de ventas. Asì, devuelve el precio en pesos, con
     * decimales.
     *
     * @param precio Integer
     * @return float
     */
    public float precioEnPesos(Integer precio) {
        float pesos = precio.floatValue() * uf;
        return pesos;
    }


    public float precioEnPesos(Float precio) {
        float pesos = precio.floatValue() * uf;
        return pesos;
    }

    public String formateaPrecioPesos(String precio){
        int f = precio.length();
        String resultado="";
        if(f>3){
                String buff=precio.substring(f-3);
                String buff2=precio.substring(0,f-3);
                precio=buff2;
                resultado=formateaPrecioPesos(precio)+"."+buff;
        }
        else{
        resultado = precio;
        }
        return resultado;
    }


    public String formateaPrecioPesos(float precio) {
        String pesos = null;
        Float pre = new Float(precio);
        pesos = pre.toString();
        pesos = pesos.replace('.', ',');
        String entero = pesos.substring(0, pesos.indexOf(","));
        String decimal=pesos.substring(pesos.indexOf(",")+1);
        String resultado="";
        int f=entero.length()-1;
        if(f+1>3){
            while(f+1>3){
                String buff=entero.substring(f-2);
                String buff2=entero.substring(0,f-3);
                entero=buff2;
                resultado=buff2+"."+buff;
                f=entero.length()-1;


            }
        pesos=resultado+","+decimal;
        }


        return pesos;
    }


}

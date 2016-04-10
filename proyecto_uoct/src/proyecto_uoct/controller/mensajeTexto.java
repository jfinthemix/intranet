package proyecto_uoct.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class mensajeTexto {
    public mensajeTexto() {
    }

    public void escribe(String mensaje) throws Exception{

           try{
               BufferedWriter bw= new BufferedWriter(new FileWriter("mensaje.txt"));
               bw.write(mensaje,0,mensaje.length());

           }catch(Exception e){
               e.printStackTrace();
               throw e;

           }

            /*try {
            File inputFile = new File("original.txt");
            File outputFile = new File("outagain.txt");
            //Creamos entradas y salidas por cónsola
            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            int c;
            //Mientras el valor del método read() del objeto fis sea != -1 --> ejecuta metodo
            //write del objeto fos
            // traduciendo: mientras no termine de leer  el fichero
            // inputfile, copialo ( y si no existe lo crea y si existe lo sobreescribe)
            // en el fichero outputfile

            while ((c = fis.read()) != -1) {
            //lee byte a byte de fis y lo vuelca en fos
               fos.write(c);
            }
            // en realidad trabaja entre la ram(FileInputStream y FileOutputStream) y el HD
            // (inputFile , outputFile)
            fis.close();
            fos.close();//importante , no dejarse abierto canales
        } catch (Exception e) {
          //la excepción provendria de no encontrar original.txt
          // originada en la linea FileInputStream fis = new FileInputStream(inputFile);
          // java exige recoger la excepcion al usar este canal ( try{..} catch{..} )
          // el fichero de salida no genera excepción , ya que se va a crear o sobreescribir
            System.err.println("FileStreamsTest: " + e);
        }
*/
        /*catch (IOException e) {
          // excepción más genérica de entrada / salida
            System.err.println("FileStreamsTest: " + e);
        }*/

    }

    public String leer(){
        try{
            String m=new String();
            FileInputStream file = new FileInputStream("../../WM_1/texto/mensaje.txt");
            boolean eof = false;
            int count = 0;
            while (!eof) {
                int input = file.read();
                if (input == -1){
                    eof = true;
                    break;
                }
                else{
                    count++;
                    System.out.print(input + " ");
                    m+=(char) input;
                }
            }
            file.close();
            return  m;
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}

package proyecto_uoct.common.util;

import java.io.File;
import org.apache.struts.upload.FormFile;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Archivo {
    public Archivo() {
    }

    private static Archivo archivo = null;

    public static Archivo getInstance() {
        if (archivo == null) {
            archivo = new Archivo();
        }
        return archivo;
    }

    public File formFileToFile(FormFile ff) throws Exception {

        try{
            File f = new File(ff.getFileName());
        DataOutputStream dostr = new DataOutputStream(new BufferedOutputStream(new
                FileOutputStream(f)));
        dostr.write(ff.getFileData());
        dostr.close();
        return f;
        }
        catch(Exception e){
            throw e;
        }

    }


    public File byteToFile(byte[] b) throws Exception {
        File f = new File("archivo.txt");
        try{
        DataOutputStream dostr = new DataOutputStream(new BufferedOutputStream(new
                FileOutputStream(f)));
        dostr.write(b);
        dostr.close();
        }
        catch(Exception e){
            throw e;
        }
        return f;
    }




}

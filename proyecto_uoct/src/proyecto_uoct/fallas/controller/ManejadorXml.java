package proyecto_uoct.fallas.controller;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ManejadorXml extends DefaultHandler{
    public void startDocument() throws SAXException {
        System.out.println("INICIO DEL DOCUMENTO");
    }

    public void endDocument() throws SAXException {
        System.out.println("FIN DEL DOCUMENTO");
    }


    public void startElement(String uri, String localName, String name,
                                Attributes attributes) throws SAXException {
        System.out.println("Procesando etiqueta "+localName);
        //System.out.println("Namespace uri: "+uri);   //System.out.println("Nombre con prefijo: "+name);
//        System.out.println("Nombre: "+localName);
        //System.out.println("Procesando "+attributes.getLength()+" atributos...");
        for(int i=0;i<attributes.getLength();i++){
            System.out.println("Nombre: "+attributes.getQName(i));
            System.out.println("Valor: "+attributes.getValue(i));
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String texto = String.valueOf(ch, start, length);
        System.out.println("Procesando texto dentro de una etiqueta... ");
        System.out.println("Texto:" + texto);
    }

    public void endElement(String uri, String localName, String name)
            throws SAXException {
        System.out.println("Fin de etiqueta: "+localName);
        //System.out.println("Namespace uri: "+uri);
//        System.out.println("Nombre: "+localName);
        //System.out.println("Nombre con prefijo: "+name);

    }

}

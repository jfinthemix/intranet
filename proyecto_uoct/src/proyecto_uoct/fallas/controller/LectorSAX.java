package proyecto_uoct.fallas.controller;
import java.util.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import proyecto_uoct.fallas.VO.FallaVO;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

public class LectorSAX  extends DefaultHandler{
    //ArrayList nodos;
    List nodos    = new LinkedList();;
    FallaVO nodo  = null;
    public List getNodos(){
        return this.nodos;
    }
    public LectorSAX(){

    }
    public LectorSAX(String url){
        nodos = new ArrayList();
        try{
            SAXParserFactory spf=SAXParserFactory.newInstance();  // creo la factoría sax
            SAXParser sp = spf.newSAXParser();                    // creo el parseador
            sp.parse(url, new LibraryXMLReader() );	            // al parseador le digo que parsee la url que quiero con el
        }catch(ParserConfigurationException e){                   // manipulador de documento (documentHandler) que quiero
            System.err.println("error de  parseo");             // (la url es una url o una path de tu ordenador se lo
        }catch(SAXException e2){                                  // traga igual )
            System.err.println("error de  sax : " + e2.getStackTrace());
        }catch (IOException e3) {
            // TODO Auto-generated catch block
            System.err.println("error de  io : " + e3.getMessage() );
        }
    }

    public void lee(String url){
        try{
            SAXParserFactory spf=SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            sp.parse(url, new LibraryXMLReader() );
        }catch(ParserConfigurationException e){
            System.err.println("error de  parseo");
        }catch(SAXException e2){
            System.err.println("error de  sax : " + e2.getStackTrace());
        }catch (IOException e3) {
            // TODO Auto-generated catch block
            System.err.println("error de  io : " + e3.getMessage() );
            }
        }

        private class LibraryXMLReader extends DefaultHandler {
            String contenido="";  // cadena para almacenar el contenido de un tag
            private FallaVO nodoActual= new FallaVO();
            private int cont = 0;
            public void startDocument() throws SAXException{
            //System.out.println("Estoy al principio del documento");
        }

        public void endDocument()throws SAXException{
            //System.out.println("Estoy al final del documento");
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            //System.out.println("he encontrado una nueva etiqueta :" + qName);
            if ("item".equals(qName)) {
                nodoActual = new FallaVO();
            }
        }
        /* Esta funcion es llamada cuando ve el contenido de un tag
         * (non-Javadoc)
         * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
         */
        public void characters(char buf[], int offset, int len) throws SAXException{
            contenido = new String(buf, offset, len);
        }

        public void endElement(String uri, String localName, String qName) {
            List lista_falla = new LinkedList();
            FallaVO nodo        = null;
            nodo = new FallaVO();
            if("DISPOSITIVO".equals(qName)){
                nodo.setDispositivoXml(contenido);
                //cont= cont+1;
                nodos.add(nodo);
            }
            if("COORD_X".equals(qName)) {
                nodo.setCoord_xXml(contenido);
                //cont= cont+1;
                nodos.add(nodo);
            }
            if("COORD_Y".equals(qName)) {
                nodo.setCoord_yXml(contenido);
                //cont= cont+1;
                nodos.add(nodo);
            }
            if("LOCALIZACION".equals(qName)) {
                nodo.setLocalXml(contenido);
                //cont= cont+1;
                nodos.add(nodo);
            }
            if("CODIGO".equals(qName)) {
                nodo.setCodigoXml(contenido);
                //cont= cont+1;
                nodos.add(nodo);
            }
            if("DESCRIPCION".equals(qName)) {
                nodo.setDescripcionXml(contenido);
                //cont= cont+1;
                nodos.add(nodo);
            }
            if("HORAS".equals(qName)) {
                nodo.setHorasXml(contenido);
                //cont= cont+1;
                nodos.add(nodo);
            }
            //System.out.println("he leido la  etiqueta : " + qName + " y su contenido es : " + contenido);
        }
    }
}

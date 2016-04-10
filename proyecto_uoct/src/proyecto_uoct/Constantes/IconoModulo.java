package proyecto_uoct.Constantes;

import java.util.*;

public class IconoModulo {
	
	
	public static String ObtieneIconoModulo(int id)
	{
		Hashtable<Integer,String> Modul = new Hashtable<Integer,String>();	
		Modul.put(1, "glyphicons-user");
		Modul.put(2, "glyphicons-circle-info");
		Modul.put(3, "glyphicons-book");
		Modul.put(4, "glyphicons-construction-cone");
		Modul.put(5, "glyphicons-pushpin");
		Modul.put(6, "glyphicons-certificate");
		Modul.put(7, "glyphicons-building");
		Modul.put(8, "glyphicons-conversation");
		Modul.put(9, "glyphicons-money");
		Modul.put(10, "glyphicons-list-alt");
		Modul.put(11, "glyphicons-cogwheel");
		Modul.put(12, "glyphicons-security-camera");
		Modul.put(13, "glyphicons-suitcase");
		Modul.put(14, "glyphicons-database-search");
		Modul.put(15, "glyphicons-database-search");
		Modul.put(16, "glyphicons-database-search");
		
		
		return Modul.get(id);
		
	}
	
	
	

}

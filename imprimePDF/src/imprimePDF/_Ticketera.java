package imprimePDF;

import java.io.IOException;


public class _Ticketera {

  public static void main (String args[]) {
    //Aca llenamos los articulos, sustituyelo por lo de tu eleccion
    String items = "12234567890122345678901223456789012234567890122345678\n"+
    			   "7   Articulo Tara tara   25.00        1\n"+
    "4   Super articulo   55.39";
    String store = "Picharras Ltd.";
    String venue = "Molas, Yuc.";
    String date = "01/enero/2012";
    String caissier = "Josue Camara";

    
    
    try {
		_Ticket ticket = new _Ticket(store, venue, "5", "99", caissier, date, items, "100.00", "16.00", "116.00", "150", "34", caissier, caissier, caissier, caissier, caissier, caissier, caissier, caissier, caissier, caissier, caissier, caissier, caissier);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
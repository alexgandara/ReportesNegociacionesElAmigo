package imprimePDF;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.DocumentException;




;



public class _printTicketeraQR {
	
	public static String[] Cabecera_Ticket  = new String[10];

	
	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_ticket, String _destino, String _ruta_tickets) throws DocumentException, IOException {
	 
	
		
		
		String reportePDF = _file_ticket;

		Cabecera_Ticket[0]="";
		Cabecera_Ticket[1]="";
		Cabecera_Ticket[2]="";
		Cabecera_Ticket[3]="";
		
		Cabecera_Ticket[4]="";
	       
		
		try {
			readParam("cabecera_ticket.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String linea01 = Cabecera_Ticket[1];
		String linea02 = Cabecera_Ticket[2];
		String linea03 = Cabecera_Ticket[3];
		String linea04 = Cabecera_Ticket[4];
		String linea05 = Cabecera_Ticket[5];
	//	System.out.println("Linea 01"+linea01);
	//	System.out.println("Linea 02"+linea02);
	//	System.out.println("Linea 03"+linea03);
	//	System.out.println("Linea 04"+linea04);
		
		//    String linea02 = "       GOMEZ MUNARES SANTA CRISTINA         ";
	    //    String linea03 = "     Telefono: 056 407825  -  952 366262    ";
	    //    String linea04 = " Calle Moquegua Nro.  Modelo Ica - Ica - Ica";

		
		
		
	//	  String linea01 = "       C O M E R C I A L   K R I S S        ";
	//    String linea02 = "       GOMEZ MUNARES SANTA CRISTINA         ";
    //    String linea03 = "     Telefono: 056 407825  -  952 366262    ";
    //    String linea04 = " Calle Moquegua Nro.  Modelo Ica - Ica - Ica";
            
        if (Cabecera.get_moneda().equals("USD")) {
        	Cabecera.set_moneda("Dolares");
        } else {
        	Cabecera.set_moneda("Soles");
        }
	    
        
        String items = "";
        
 	    for (int i=0; i<_lineas_de_la_factura; i++) {        
 	    	System.out.println(i+Detalle[i].get_descripcion());	
 	    	          //"12234567890122345678901223456789012234567890122345678\n"+
 	    	
 	    	if (Detalle[i].get_descripcion()!=  null) { 
 	    		String _descripcion01="";
 	    		String _descripcion02="";
 	    		
 	    		if (Detalle[i].get_descripcion().length()>21) {
 	    			int _tam_desc=Detalle[i].get_descripcion().length();
 	    			if (_tam_desc>42) {
 	    				_tam_desc=42;
 	    			}
 	    			
 	    			
 	    			_descripcion01=Formato.cadena21(Detalle[i].get_descripcion().substring(0, 21));
 	    			_descripcion02=Formato.cadena21(Detalle[i].get_descripcion().substring(21, _tam_desc))+"\n";
 	    			
 	    			_descripcion01.replace('ñ', 'n');
 	    			_descripcion02.replace('ñ', 'n');
 	    			
 	    			_descripcion01.replace('Ñ', 'N');
 	    			_descripcion02.replace('Ñ', 'N');

 	    			
 	    		} else {
 	    			_descripcion01=Formato.cadena21(Detalle[i].get_descripcion().trim());
 	    			
 	    		}
 	    			
 	         items = items+
 	        		_descripcion01+Formato.dinero0(Detalle[i].get_cantidad())+
 	        		" "+Formato.dinero_corto(Detalle[i].get_precio_unitario())+
 	        		Formato.dinero(Detalle[i].get_subtotal())+"\n"+_descripcion02;
 	    	}

 	    	 
// 	    	 Chunk _unidad_de_medida = new Chunk(Formato.cadena5(Detalle[i].get_unidad()));
// 	    	 Chunk _cantidad = new Chunk(Formato.dinero0(Detalle[i].get_cantidad()));
// 	    	 Chunk _precio = new Chunk(Formato.dinero_corto(Detalle[i].get_precio_unitario()));
// 	    	 Chunk _precio_con_igv = new Chunk(Formato.dinero_corto(Detalle[i].get_precio_unitario()*1.18));
// 	    	 Chunk _importe = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
// 	    	 Chunk _igv = new Chunk(Formato.dinero(Detalle[i].get_igv()));
// 	    	 Chunk _importe_sin_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
   
 	     }
 	     

 	     
        
 	     	String _ruta_ticket="";
			_TicketQR ticket = new _TicketQR(linea01, 
 	     			    linea02, 
 	     			    "5", 
 	     			    "99", 
 	     			    linea04,  
 	     			    linea03, 
 	     			    items,
 	     			    Formato.dinero(Cabecera.get_subtotal()),
 	     			    Formato.dinero(Cabecera.get_total_igv()),
 	     			    Formato.dinero(Cabecera.get_total()),
 	     				"150",
 	     				"34",
 	     				"RUC EMISOR     : "+Cabecera.get_ruc_emisor(),
 	     				"TIPO DOCUMENTO : "+Cabecera.get_tipo_doc_descripcion(),
 	     				"FOLIO          : "+Cabecera.get_serie()+"-"+Cabecera.get_folio(),
 	     				"RUC/DNI        : "+Cabecera.get_ruc_receptor(),
 	     				"RAZON SOCIAL   : "+Cabecera.get_razon_social_receptor(),
 	     				"DIRECCION      : "+Cabecera.get_direccion_receptor(),
 	     				"FECHA EMISION  : "+Cabecera.get_fecha(),
 	     				"MONEDA         : "+Cabecera.get_moneda(),
 	     				"% IGV          : 18 %",
 	     				"SON: "+Cabecera.get_total_letra()+" Soles.",
 	     				"HASH;"+Cabecera.get_codigo_hash(),
 	     				"Emisor Electronico mediante Resolucion de Intendencia No 155-2017/SUNAT. \n"+Cabecera.get_guia(),
 	     				_ruta_ticket);
 	     				
 	     				
 	     				


	        
    
	        

	        
	        

/*	  	       
	  	     String _contenido_qr = Cabecera.get_ruc_emisor()+"|"+Cabecera.get_tipo_documento()+"|"+   
						Cabecera.get_serie()+"-"+Cabecera.get_folio()+"|"+
						Cabecera.get_total_igv()+"|"+Cabecera.get_total()+"|"+Cabecera.get_fecha_qr()+"|"+
						Cabecera.get_tipo_doc_adquiriente()+"|"+
						Cabecera.get_ruc_receptor()+"|";

	  	    	BarcodeQRCode barcodeQRCode = new BarcodeQRCode(_contenido_qr, 82, 82, null);
	  	    		Image codeQrImage = barcodeQRCode.getImage();
	  	    		
	*/	  	     
		  	     
	  	    
	
	} 
	
	
	public static void readParam(String _file_parametros) throws Exception {
		
		InputStream is_param = new FileInputStream(_file_parametros);
		DataSource ds_param = (DataSource) new ByteArrayDataSource(is_param,"application/octet-stream");
		DataHandler dhandler_param = new DataHandler((javax.activation.DataSource) ds_param);
		
		Object content = dhandler_param.getContent();
		
		BufferedReader br = null;
		
		int x=1;
		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));
			
			while ((sCurrentLine = br.readLine()) != null) {
				Cabecera_Ticket[x]=sCurrentLine;
			//	System.out.println(x+"  "+sCurrentLine);
				
				
				x++;
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}




}

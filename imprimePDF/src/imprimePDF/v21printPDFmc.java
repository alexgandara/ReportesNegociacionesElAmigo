package imprimePDF;



import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;



public class v21printPDFmc {
	
	
	//private static PdfWriter writer;


	
	public static final String FONT = ".\\resources\\fonts\\Consolas.ttf";
	// private static String FILE = "c:/temp/FirstPdf.pdf";

	public static final String FONT_BOLD = ".\\resources\\fonts\\FrankfurtGothic-Bold.ttf";
	public static final String FONT_CALIBRI = ".\\resources\\fonts\\calibri.ttf";
	
	
	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_pdf) throws DocumentException, IOException {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 
		
		
		String reportePDF = _file_pdf;
		 // 
		String formato_factura = ".\\data\\20534984261\\10_formatos\\MediaCarta2.jpg"; // .gif and .jpg are ok too!
		
	
        
		 	//Document document = new Document();
		 	Document document = new Document(PageSize.A5.rotate(), 10, 10, 10, 10);
	        // step 2
	       
	        
	       // Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
          //  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
            
            PdfWriter writer =
    	            PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
            
	        // step 3
	        document.open();
	        
	        BaseFont bf_bold = BaseFont.createFont(FONT_BOLD,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font console_bold = new Font(bf_bold, 7);
	        
	        
	        
	        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font console = new Font(bf, 7);
	        
	
			Image img = Image.getInstance(formato_factura);
			img.scalePercent(23);
			img.setAbsolutePosition(0, 20); // horizontal , vertical
			document.add(img);
	       
	        // step 4
	     
	
	        
	         
	        // ruc  emisor
	        PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
	        writer.setCompressionLevel(0);
	        
	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(460, 377);                         // 36 788 Td
	        //canvas.setFontAndSize(BaseFont.createFont(), 11); // /F1 12 Tf
	        canvas.setFontAndSize(bf, 12);
	        canvas.showText(Cabecera.get_ruc_emisor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	        
	        // TIPO DOCUMENTO
	        // NOMBRE DEL DOCUMENTO
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(380, 358);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	        canvas.showText(Cabecera.get_tipo_doc_descripcion());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	  
	        
	        
	        
	        
	        // serie
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(445, 340);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	        canvas.showText(Cabecera.get_serie()+"-"+Cabecera.get_folio());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        // folio
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(440, 320);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	       // canvas.showText(Cabecera.get_folio());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	        // razon social del emisor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(170, 705);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	    //    canvas.showText(Cabecera.get_razon_social_emisor());	        // (Hello World)Tj
	   //     canvas.showText("LA GRAN EMPRESA SAC");	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        // direccion social del emisor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(170, 690);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	    //    canvas.showText(Cabecera.get_direccion_emisor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	        
	        // ruc del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(30, 280);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	        if (Cabecera.get_ruc_receptor().length()>8) {
	        	canvas.showText(Cabecera.get_Ruc_Dni()+"          "+Cabecera.get_ruc_receptor());	        // (Hello World)Tj
	        } else {
	        	canvas.showText(Cabecera.get_Ruc_Dni()+"          "+Cabecera.get_ruc_receptor());
	        }
	        
	   //     canvas.showText("RUC:           "+Cabecera.get_ruc_receptor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	        // RAZON SOCIAL  del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(30, 290);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	        if (Cabecera.get_ruc_receptor().length()>8) {
	        	canvas.showText("RAZON SOCIAL: "+Cabecera.get_razon_social_receptor());	        // (Hello World)Tj
	        } else {
	        	canvas.showText("NOMBRE:       "+Cabecera.get_razon_social_receptor());
	        }
	      //  canvas.showText("Razon Social:  "+Cabecera.get_razon_social_receptor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        // RAZON SOCIAL  del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(30, 260);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	        canvas.showText("DIRECCION:    "+Cabecera.get_direccion_receptor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        // RAZON SOCIAL  del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(30, 250);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	  //      canvas.showText(Cabecera.get_fecha_llegada()+"         "+Cabecera.get_fecha_salida()+"         "+Cabecera.get_habitacion()+"       "+Cabecera.get_personas()+"       "+Cabecera.get_ficha());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        
	        

	        // RAZON SOCIAL  del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(30, 260);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	  //      canvas.showText("PLACA:        "+Cabecera.get_placa());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        
	        
	        // fecha de emision del docto
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(420, 290);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	        canvas.showText("Fecha de Emision: "+Cabecera.get_fecha());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
   
	        
	        if (Cabecera.get_moneda().equals("PEN")) {
	        	Cabecera.set_moneda("SOLES");
	        }
	     

	        //moneda
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(420, 280);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	        canvas.showText("Moneda:           "+Cabecera.get_moneda());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        //IGV
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(420, 270);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	        canvas.showText("% IGV:            "+"18 %");	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        
	        
	       
	        
	        
	     // resumen hash
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(160, 67);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	        canvas.showText("Hash:  "+Cabecera.get_codigo_hash());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	  	    BarcodePDF417 pdf417 = new BarcodePDF417();
	  	    String text = Cabecera.get_sello();
	  	    
	  	       pdf417.setText(text);
	  	       Image barras = pdf417.getImage(); 
	  	       barras.setAbsolutePosition(115, 75); 
	  	       document.add(barras);
	  	      
	  	       
		        //special font sizes
		        BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		        Font arial = new Font(bf_arial, 7);
		        	      
		  	     Paragraph _linea00 = new Paragraph();
		  	     Chunk _espacio = new Chunk(" ",arial);
		  	     _linea00.add(_espacio);
		  	     
		  	     
	  	       
	  	       
	  	       
	  	       
	  	       
	  	       
	  	    // Paragraph _linea00 = new Paragraph();
	  	    // Chunk _espacio = new Chunk(" ");
	  	    // _linea00.add(_espacio);
	  	     
	  	     
	  	     for (int z = 1; z<=11; z++) {
	  	    	 document.add(_linea00);
	  	     }
	  	     
	  	   Paragraph _linea01 = new Paragraph(9);
	 // 	     Paragraph _linea01 = new Paragraph();
	  	    
	  //	 Cabecera.set_subtotal(0);
	  //	    Cabecera.set_total_igv(0);
	  //	    Cabecera.set_total(0);
	  	   
	  	     for (int i=0; i<_lineas_de_la_factura; i++) {
	  	    	 
	  	     
	  	     
	  	//    	Cabecera.set_subtotal(Cabecera.get_subtotal()+Detalle[i].get_subtotal_sin_igv());
	  	//    	 Cabecera.set_total_igv(Cabecera.get_total_igv()+Detalle[i].get_igv());
	  	//    	 Cabecera.set_total(Cabecera.get_subtotal()+Cabecera.get_total_igv());
	  	    	 
	  	    	if (Detalle[i].get_descripcion() != null) {
	  	    		
	  	    	
	  	    		
	  	     
	  	     Chunk _producto = new Chunk(Formato.padRight(Detalle[i].get_codigo(),4));
	  	     Chunk _descripcion = new Chunk(Formato.padRight(Formato.cadena80(Detalle[i].get_descripcion()),80));
	  	     Chunk _unidad_de_medida = new Chunk(Formato.cadena5(Detalle[i].get_unidad()));
	  	     Chunk _cantidad = new Chunk(Formato.dinero0(Detalle[i].get_cantidad()));
	  	     Chunk _precio = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()));
	  	     Chunk _precio_con_igv = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()*1.18));
	  	     Chunk _importe = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
	  	     Chunk _igv = new Chunk(Formato.dinero(Detalle[i].get_igv()));
	  	     Chunk _importe_sin_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
	  	     
	  	     _espacio.setFont(console);
	  	     //  _lineas_de_la_factura
	  	     
	  	     _linea01.add(_espacio);
	  	     
	  	     
	  	     _producto.setFont(console);
	  	     _descripcion.setFont(console);
	  	     _cantidad.setFont(console);
	  	     _unidad_de_medida.setFont(console);
	  	     
	  	     _precio.setFont(console);
	  	     _precio_con_igv.setFont(console);
	  	     _importe.setFont(console);
	  	     _importe_sin_igv.setFont(console);
	  	     _igv.setFont(console);
	  	     
	  	       
	  	     
	  	     
	  	     
	  	    
	    	 _linea01.add(_espacio);
	    	 if (Detalle[i].get_codigo()!=".") {
	    		 //_linea01.add(_producto);
	    	 } else {
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 _linea01.add(_espacio);
	    		 
	    	 }
	    		 
	    	 
	    	 
	    	 _linea01.add(_espacio);
	       	 _linea01.add(_descripcion);
	       	 
	       	if (Detalle[i].get_codigo()!=".") {
	      	  	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		  	   
		    	 _linea01.add(_cantidad);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		  	     
		  	//     _linea01.add(_unidad_de_medida);
		  	     
		  	     _linea01.add(_espacio);
		  	     _linea01.add(_espacio);
		  	     _linea01.add(_espacio);
		  	     
		  	     
		  	   
		  		
		  		   
		  		   
		  		   
		  		   
		  	   
		  	     
		  	     
		  	   if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
		    		  _linea01.add(_precio_con_igv);
			        }
			        
			        	else
			        {
			        		_linea01.add(_precio);
			        }
		  	   		_linea01.add(_espacio);
		  	     
		    	// _linea01.add(_igv);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
		    	 _linea01.add(_espacio);
			  	     
			     if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
			    	 	_linea01.add(_importe);
			        }
			        
			        	else
			        {
			        	 _linea01.add(_importe_sin_igv);
			        }
		  	    // _linea01.add(_importe_sin_igv);

	       		
	       		
	       	}
	       	 
	       	}
	       	
 	  	     
	  	    
	  	    
	  	     
	  	     
	  	     document.add(_linea01);
	  	     _linea01.removeAll(_linea01);
	  	     }
	  	     	

	  	     
	  	     
	  	     
		        // cantidad en letra
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(25, 141);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
		        canvas.showText("Cantidad en Letra:"+Cabecera.get_total_letra()+" Soles.");	        // (Hello World)Tj
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		       
		        
		     // TOTAL subtotal
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(440, 123);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		        if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
		        	
		        }
		        
		        	else
		        {
		        		canvas.showText("SUBTOTAL S/ "+Formato.dinero(Cabecera.get_subtotal()));			
		        }
		        
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		       
		        // TOTAL subtotal
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(440, 108);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		        if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
		        	
		        }
		        
		        	else
		        {
		        		canvas.showText("IGV      S/ "+Formato.dinero(Cabecera.get_total_igv()));	
		        }
		        
		        
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q  
		        
		        // TOTAL DE LA FACTURAS
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(440, 93);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		     // canvas.showText("Total:      "+Cabecera.get_total());	        // (Hello World)Tj
		        canvas.showText("TOTAL    S/ "+Formato.dinero(Cabecera.get_total()));
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		       
		        
		        

	  	       
	  	       
	  	       
	    
	        
	        // step 5
	        document.close();		
		
		
		
	}

	
	
	
	  

}

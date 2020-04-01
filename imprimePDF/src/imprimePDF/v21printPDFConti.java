package imprimePDF;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;



public class v21printPDFConti {
	
	
	//private static PdfWriter writer;


	

	public static final String FONT = ".\\resources\\fonts\\Consolas.ttf";
	public static final String FONT_AN = ".\\resources\\fonts\\arial-narrow.ttf";
	public static final String FONT_BOLD = ".\\resources\\fonts\\FrankfurtGothic-Bold.ttf";

	// private static String FILE = "c:/temp/FirstPdf.pdf";

	public static String[] Cabecera_Ticket  = new String[10];
	
	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_pdf, String _file_jpg) throws DocumentException, IOException {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 


		
		Cabecera_Ticket[0]="";
		Cabecera_Ticket[1]="";
		Cabecera_Ticket[2]="";
		Cabecera_Ticket[3]="";
		Cabecera_Ticket[4]="";
		Cabecera_Ticket[5]="";
		
	       
		String _archivo_ticket=Cabecera.get_serie()+"_cabecera_ticket.txt";
		
		
		
		
		try {
			readParam(_archivo_ticket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String linea01 = Cabecera_Ticket[1];
		String linea02 = Cabecera_Ticket[2];
		String linea03 = Cabecera_Ticket[3];
		String linea04 = Cabecera_Ticket[4];
		String linea05 = Cabecera_Ticket[5];

		

		String reportePDF = _file_pdf;
		// 
		String formato_factura = _file_jpg; // .gif and .jpg are ok too!



		Document document = new Document();
		// step 2


		// Document document = new Document();
		File af = new File(reportePDF);


		af.delete();


		if (!af.exists()) { 




			PdfWriter.getInstance(document, new FileOutputStream(reportePDF));


			PdfWriter writer =
					PdfWriter.getInstance(document, new FileOutputStream(reportePDF));

			// step 3
			document.open();

			BaseFont bf = BaseFont.createFont(FONT,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			BaseFont bf_web = BaseFont.createFont(FONT_AN,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			BaseFont bf_bold = BaseFont.createFont(FONT_BOLD,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

			Font console = new Font(bf, 7);
			Font console_web = new Font(bf_web, 8);
			console_web.setColor(0, 0, 255);





			Image img = Image.getInstance(formato_factura);
			
			
			img.scalePercent(23);
			img.setAbsolutePosition(0, 70); // horizontal , vertical
			document.add(img);

			// step 4




			// ruc  emisor
			PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
			writer.setCompressionLevel(0);


			// calcula donde poner la razon social para uqe quede centrado
			int _tam_razon_social = Cabecera.get_razon_social_emisor().trim().length();
			System.out.println("tamaño:"+_tam_razon_social);
			String _razon_social = "";
			int _sobra = 0;

			if (_tam_razon_social>40) {
				_razon_social=Cabecera.get_razon_social_emisor().trim().substring(0, 40);
			} else {
				_sobra=(int)(40-_tam_razon_social)/2;
				System.out.println("sobre:"+_sobra);
				_sobra=_sobra*8;
				System.out.println("sobre:"+_sobra);

				_razon_social=Cabecera.get_razon_social_emisor().trim();	
			}


			// NOMBRE DEL DOCUMENTO
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40+_sobra, 740);                         // 36 788 Td
			canvas.setFontAndSize(bf, 15); // /F1 12 Tf
			//	canvas.setColorFill(BaseColor.WHITE);
			//	 		canvas.showText(_razon_social);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			// calcula donde poner la razon social para uqe quede centrado
	//		int _tam_direccion = Cabecera.get_direccion_emisor().trim().length();
	//		String _direccion = "";
	//		_sobra = 0;

	//		System.out.println(Cabecera.get_direccion_emisor()+" "+_tam_direccion);	

	//		if (_tam_direccion>46) {
	//			_direccion=Cabecera.get_direccion_emisor().trim().substring(0, 40);
	//		} else {
	//			_sobra=(int)(46-_tam_direccion)/2;
	//			_sobra=_sobra*7;

	//			_direccion=Cabecera.get_direccion_emisor().trim();	
	//		}



			// NOMBRE DEL DOCUMENTO
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40+_sobra, 715);                         // 36 788 Td
			canvas.setFontAndSize(bf, 13); // /F1 12 Tf
			//	canvas.setColorFill(BaseColor.WHITE);
			//	 		canvas.showText(_direccion);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			

			
			// Linea 01
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(38, 738);                         // 36 788 Td
			canvas.setFontAndSize(bf, 13); // /F1 12 Tf
			//	canvas.setColorFill(BaseColor.WHITE);
			canvas.showText(linea01);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			
			// Linea 02
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(45, 726);                         // 36 788 Td
			canvas.setFontAndSize(bf, 12); // /F1 12 Tf
			//	canvas.setColorFill(BaseColor.WHITE);
			canvas.showText(linea02);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			// Linea 03
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(45, 714);                         // 36 788 Td
			canvas.setFontAndSize(bf, 12); // /F1 12 Tf
			//	canvas.setColorFill(BaseColor.WHITE);
			canvas.showText(linea03);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q
			
			
			// Linea 04
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(45, 702);                         // 36 788 Td
			canvas.setFontAndSize(bf, 12); // /F1 12 Tf
			//	canvas.setColorFill(BaseColor.WHITE);
			canvas.showText(linea04);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			
			// Linea 05
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(45, 690);                         // 36 788 Td
			canvas.setFontAndSize(bf, 12); // /F1 12 Tf
			//	canvas.setColorFill(BaseColor.WHITE);
			canvas.showText(linea05);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			
			
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(50, 780);                         // 36 788 Td
			//	canvas.setFontAndSize(BaseFont.createFont(), 11); // /F1 12 Tf
			canvas.setFontAndSize(bf, 13);
			if (Cabecera.get_tipo_doc().equals("03")) {
				canvas.showText("BOLETA DE VENTA EMITDA EN CONTINGENCIA");	        // (Hello World)Tj				
			} else {
				canvas.showText("FACTURA DE VENTA EMITDA EN CONTINGENCIA");	        // (Hello World)Tj
			}

			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(385, 780);                         // 36 788 Td
			//	canvas.setFontAndSize(BaseFont.createFont(), 11); // /F1 12 Tf
			canvas.setFontAndSize(bf, 12);
			canvas.showText("EMISOR ELECTRONICO OBLIGADO");	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			
			

			// NOMBRE DEL DOCUMENTO
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(392, 762);                         // 36 788 Td
			canvas.setFontAndSize(bf, 15); // /F1 12 Tf
			canvas.setColorFill(BaseColor.WHITE);
			canvas.showText(Cabecera.get_tipo_doc_descripcion());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q




			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(464, 731);                         // 36 788 Td
			//	canvas.setFontAndSize(BaseFont.createFont(), 11); // /F1 12 Tf
			canvas.setFontAndSize(bf, 11);
			canvas.showText(Cabecera.get_ruc_emisor());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			// 	200, 765






			// razon social del emisor
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(110, 705);                         // 36 788 Td
			canvas.setFontAndSize(bf, 14); // /F1 12 Tf
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			// serie
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(464, 714);                         // 36 788 Td
			canvas.setFontAndSize(bf, 11); // /F1 12 Tf
			canvas.showText(Cabecera.get_serie());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			// folio
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(464, 697);                       // 36 788 Td
			canvas.setFontAndSize(bf, 11); // /F1 12 Tf
			canvas.showText(Cabecera.get_folio());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			// ruc del receptor
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(100, 664);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Cabecera.get_ruc_receptor());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			// RAZON SOCIAL  del receptor
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(100, 653);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.padRight(Cabecera.get_razon_social_receptor(),50));	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			if (Cabecera.get_direccion_receptor().length()>65) {
				int _tam = Cabecera.get_direccion_receptor().length();
				String _dir01 =  Cabecera.get_direccion_receptor().substring(0, 65);
				String _dir02 =  Cabecera.get_direccion_receptor().substring(65, _tam);

				// direccion social del emisor
				canvas.saveState();                               // q
				canvas.beginText();                               // BT
				canvas.moveText(100, 641);                         // 36 788 Td
				canvas.setFontAndSize(bf, 9); // /F1 12 Tf
				canvas.showText(_dir01);	        // (Hello World)Tj
				canvas.endText();                                 // ET
				canvas.restoreState();                            // Q


				// direccion social del emisor
				canvas.saveState();                               // q
				canvas.beginText();                               // BT
				canvas.moveText(100, 632);                         // 36 788 Td
				canvas.setFontAndSize(bf, 9); // /F1 12 Tf
				canvas.showText(_dir02);	        // (Hello World)Tj
				canvas.endText();                                 // ET
				canvas.restoreState();                            // Q





			} else {
				// direccion social del emisor
				canvas.saveState();                               // q
				canvas.beginText();                               // BT
				canvas.moveText(100, 641);                         // 36 788 Td
				canvas.setFontAndSize(bf, 9); // /F1 12 Tf
				canvas.showText(Cabecera.get_direccion_receptor());	        // (Hello World)Tj
				canvas.endText();                                 // ET
				canvas.restoreState();                            // Q



			}

			
			
			
			
			if (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				canvas.saveState();                               // q
				canvas.beginText();                               // BT
				canvas.moveText(55, 621);                         // 36 788 Td
				canvas.setFontAndSize(bf, 9); // /F1 12 Tf
				canvas.showText("DOC. REL:"+Cabecera.get_doc_relacionado()+" MOTIVO:"+Cabecera.get_motivo_de_anulacion());	        // (Hello World)Tj
				canvas.endText();                                 // ET
				canvas.restoreState();                            // Q
			}


			if (Cabecera.get_descripcion_documento().equals("DebitNote")) {
				canvas.saveState();                               // q
				canvas.beginText();                               // BT
				canvas.moveText(55, 621);                         // 36 788 Td
				canvas.setFontAndSize(bf, 9); // /F1 12 Tf
				canvas.showText("DOC. REL:"+Cabecera.get_doc_relacionado()+" MOTIVO:"+Cabecera.get_motivo_de_anulacion());	        // (Hello World)Tj
				canvas.endText();                                 // ET
				canvas.restoreState();                            // Q
			}



			//GUIA
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(100, 611);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	canvas.showText(Cabecera.get_guia());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q




			// fecha de emision del docto
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 664);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Cabecera.get_fecha());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			//moneda
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 653);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			//canvas.showText("Moneda:           "+Cabecera.get_moneda());	        // (Hello World)Tj
			if (Cabecera.get_moneda().equals("PEN")) {
				canvas.showText("SOLES");	        // (Hello World)Tj
			} else {
				canvas.showText("DOLARES");	        // (Hello World)Tj
			}

			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q







			String _fecha = Cabecera.get_fecha_vencimiento();
			String _fecha_final = _fecha;

			//fecha vencimiento
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 641);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			if (!Cabecera.get_fecha().equals(_fecha_final)) {
				canvas.showText(_fecha_final);
			}
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q









			// descuento cabecera
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(495, 620);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_descuentos_cabecera()));	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q





			// descuento detalle
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(495, 608);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_descuentos_detalle()));	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q








			// TOTAL subtotal
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 220);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_total_gravado()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			// TOTAL inafecto
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 209);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_total_inafecto()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			// TOTAL exonerado
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 198);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_total_exonerado()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			// TOTAL gratuiss
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 187);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_total_gratuitas()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			// TOTAL descuentos
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 175);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_total_descuentos()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q







			// TOTAL anticipo
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 163);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_total_anticipos()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q  




			// TOTAL igv
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(500, 151);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText(Formato.dinero(Cabecera.get_total_igv()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q  





			// TOTAL DE LA FACTURAS
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(490, 132);                         // 36 788 Td
			canvas.setFontAndSize(bf, 11); // /F1 12 Tf
			canvas.setColorFill(BaseColor.WHITE);
			// canvas.showText("Total:      "+Cabecera.get_total());	        // (Hello World)Tj
			canvas.showText(Formato.dinero(Cabecera.get_total()));
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



			// descuento cabecera
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(377, 115);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText("Tipo de Operación:"+Cabecera.get_tipo_operacion());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q





			// para el facturador
			Cabecera.set_total_letra(denomina.main(Cabecera.get_total()));
			System.out.println("Importe con Letra _ _ _ _ _ _ : " + Cabecera.get_total_letra());




			// cantidad en letra
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40, 240);                         // 36 788 Td
			canvas.setFontAndSize(bf, 8); // /F1 12 Tf
			if (Cabecera.get_moneda().equals("PEN")) {
				canvas.showText("Son: "+Cabecera.get_total_letra()+" SOLES.");	        // (Hello World)Tj
			} else {
				canvas.showText("Son: "+Cabecera.get_total_letra()+" DOLARES.");	        // (Hello World)Tj
			}
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q




			// resumen hash
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(108, 117);                         // 36 788 Td
			canvas.setFontAndSize(bf, 7); // /F1 12 Tf
			canvas.showText("Hash: "+Cabecera.get_codigo_hash());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q

			//	  	    BarcodePDF417 pdf417 = new BarcodePDF417();
			//	  	    String text = Cabecera.get_sello();

			//	  	       pdf417.setText(text);
			//	  	       Image barras = pdf417.getImage(); 
			//	  	       barras.setAbsolutePosition(48, 160); 
			//	  	       barras.scalePercent(90, 120);
			//	  	       document.add(barras);




			String _contenido_qr = Cabecera.get_ruc_emisor()+"|"+Cabecera.get_tipo_documento()+"|"+   
					Cabecera.get_serie()+"-"+Cabecera.get_folio()+"|"+
					Cabecera.get_total_igv()+"|"+Cabecera.get_total()+"|"+Cabecera.get_fecha_qr()+"|"+
					Cabecera.get_tipo_doc_adquiriente()+"|"+Cabecera.get_ruc_receptor()+"|";

			BarcodeQRCode barcodeQRCode = new BarcodeQRCode(_contenido_qr, 90, 90, null);
			Image codeQrImage = barcodeQRCode.getImage();
			codeQrImage.setAbsolutePosition(131, 131);
			document.add(codeQrImage);








			Paragraph _linea00 = new Paragraph(8);
			Chunk _espacio = new Chunk(" ");
			_linea00.add(_espacio);


			//special font sizes
			BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font arial = new Font(bf_arial, 6);


		  	     
		  	     
		  	     for (int z = 1; z<=28; z++) {
		  	    	 document.add(_linea00);
		  	     }
		  	     
		  	     
		  	     Paragraph _linea01 = new Paragraph(7);
		
		  	     String _desc ="";

	        
	        
	        
	  	  	       
	 
	
		  	     int _linea_factura=0;
	  	   
	 

		  	     for (int i=0; i<_lineas_de_la_factura; i++) {
			 	  	    //	 System.out.println("esta en la linea "+i);
			 	  	     //	System.out.println(Detalle[i].get_descripcion());
			 	  	    	 if (Detalle[i].get_descripcion()!=null) { 	 
			 	  	    		 if (!Detalle[i].get_descripcion().trim().equals("")) {
			 	  	     

			 	  	    		 if (Detalle[i].get_codigo()==null) {
			 	  	    			 Detalle[i].set_codigo(".");
			 	  	    		 }
			 	  	    		 
			 	  	    	

				  	    		 Chunk _producto = new Chunk(Formato.padRight(Detalle[i].get_codigo(),15));
				  	    		 Chunk _descripcion = new Chunk(Formato.padRight(Formato.cadena59(Detalle[i].get_descripcion()),59));
				  	  

				  	    		
				  	    		 
				  	       	     
				  	    		 if (Detalle[i].get_unidad()==null) {
				  	    			 Detalle[i].set_unidad("KG");
				  	    		 }
				  	     
				  	    		 if (Detalle[i].get_unidad().equals("KGM")) {
				  	    			 Detalle[i].set_unidad("KG");
				  	    		 }
				  	     
				  	    		 if (Detalle[i].get_unidad().equals("MTR")) {
				  	    			 Detalle[i].set_unidad("MTS");
				  	    		 }
				  	     
				  	    		 if (Detalle[i].get_unidad().equals("NIU")) {
				  	    			 Detalle[i].set_unidad("UNI");
				  	    		 }
				  	     
				  	    		 if (Detalle[i].get_unidad().equals("BX")) {
				  	    			 Detalle[i].set_unidad("CJ");
				  	    		 }
				  	    
				  	    		 if (Detalle[i].get_unidad().equals("PF")) {
				  	    			 Detalle[i].set_unidad("PAL");
				  	    		 }
				  	    
				  	    		Detalle[i].set_total_linea(Detalle[i].get_subtotal()); //-Detalle[i].get_descuento());
				  	     
				  	    		 Chunk _unidad_de_medida = new Chunk(Formato.cadena5(Detalle[i].get_unidad()));
				  	    		 Chunk _cantidad = new Chunk(Formato.cant(Detalle[i].get_cantidad()));
				  	    		 Chunk _precio = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()));
				  	    		 Chunk _precio_con_igv = new Chunk(Formato.dinero(Detalle[i].get_precio_unitario()+(Detalle[i].get_igv()/Detalle[i].get_cantidad())));
				  	    		 Chunk _importe = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
				  	    		 Chunk _igv = new Chunk(Formato.dinero(Detalle[i].get_igv()));
				  	    		 Chunk _importe_con_igv = new Chunk(Formato.dinero( (Detalle[i].get_cantidad()*Detalle[i].get_precio_unitario()) + Detalle[i].get_igv()));
				  	    		 Chunk _descuento = new Chunk(Formato.dinero(Detalle[i].get_descuento()));
				  	    	//	 Chunk _linea = new Chunk(Formato.dinero2(Detalle[i].get_num_linea()));
				  	    		 Chunk _total_linea = new Chunk(Formato.dinero( (Detalle[i].get_cantidad()*Detalle[i].get_precio_unitario()) + Detalle[i].get_igv()-Detalle[i].get_descuento()));  
				  	    		 Chunk _cero = new Chunk(Formato.dinero(0));  
					 	  	     
			 	  	    		 _espacio.setFont(console);
			 	  	    		 //  	_lineas_de_la_factura
			 	  	     
			 	  	     
			 	  	     
			 	  	    		 
			 	  	     

				  	    		 _producto.setFont(console);
				  	    		 _descripcion.setFont(console);
				  	    		 _cantidad.setFont(console);
				  	    		 _unidad_de_medida.setFont(console);
				  	    		 _cero.setFont(console);
				  	     
				  	    		 _precio.setFont(console);
				  	    		 _precio_con_igv.setFont(console);
				  	    		 _importe.setFont(console);
				  	    		 _importe_con_igv.setFont(console);
				  	    		 _igv.setFont(console);
				  	    		 _descuento.setFont(console);
				  	    		
				  	    		 _total_linea.setFont(console);
				  	    		 
			 	  	     
			 	  	     
			 	  	     
			 	  	     
			 	  	    
			 	  	    		 // 	_linea01.add(_espacio);
			 	  	    		 if (Detalle[i].get_codigo()!=".") {
			 	  	    			 if (Detalle[i].get_codigo().equals(".")) {
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				_linea01.add(_espacio);
			 	  	    				
			 	  	    			 } else {
			 	  	    				 
			 	  	    				 _linea_factura++;
			 	  	    				 
			 	  	    				Chunk _linea = new Chunk(Formato.dinero2(_linea_factura));
			 	  	    				_linea.setFont(console);
//			 	  	    				 
			 	  	    				 
			 	  	    				_linea01.add(_espacio);
			 							_linea01.add(_linea);
			 							_linea01.add(_cantidad);
			 							_linea01.add(_espacio);
			 							_linea01.add(_espacio);
			 							_linea01.add(_espacio);
			 							_linea01.add(_unidad_de_medida);
			 							
			 		  	    			//	_linea01.add(_espacio);
			 		  	    			//	_linea01.add(_producto);
			 		  	    				
			 		  	    				

			 	  	    			 }
			 	  	    		 } else {
			 	  	    			 
			 	  	    			 
			 	  	    			for (int _l=0; _l<21; _l++) {
			 	  	    			 _linea01.add(_espacio);
			 	  	    			} 		 
			 	    		 
			 	    		 
			 	  	    		 }
			 	    		 
			 	    	 
			 	  	    //		System.out.println(_descripcion);
			 	  	    		
			 	  	    		 _linea01.add(_espacio);
			 	  	    		 _linea01.add(_descripcion);
			 	  	    		
			 	  	    		 
			 	  	    		 if (!Detalle[i].get_codigo().equals(".")) {

			 	  	    		//	 _linea01.add(_espacio);
			 	  	    		//	 _linea01.add(_espacio);
			 		  	   
			 	  	    			 
			 	  	    		//	_linea01.add(_espacio);
									_linea01.add(_espacio);
									_linea01.add(_precio);
									
									
									 if (Cabecera.get_tipo_doc().equals("03")) {
										 _linea01.add(_cero);
										 _linea01.add(_espacio);
										 _linea01.add(_precio_con_igv);
			 	  	    			 }
			 			        
			 	  	    			 else
			 	  	    			 {
			 	  	    				_linea01.add(_igv);
										_linea01.add(_espacio);
							
			 	  	    				 _linea01.add(_importe_con_igv);
			 	  	    			 }
			 	  	    		
								//	_linea01.add(_importe_sin_igv);
									_linea01.add(_descuento);
									_linea01.add(_espacio);
									_linea01.add(_total_linea);
			 		  	   
			 		  		
			 		  		   
			 		  		   
			 		  		   
			 		  		   
			 		  	   
			 		  	     
			 		  	     
			 	  	    	/*		 if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
			 	  	    				 _linea01.add(_precio_con_igv);
			 	  	    			 }
			 			        
			 	  	    			 else
			 	  	    			 {
			 	  	    				 _linea01.add(_precio_con_igv);
			 	  	    			 }
			 	  	    			 _linea01.add(_espacio);
			 		  	     
			 	  	    			 _linea01.add(_espacio);
			 	  	    			 _linea01.add(_espacio);
			 			  	     
			 	  	    			 if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
			 	  	    				 _linea01.add(_importe);
			 	  	    			 }
			 			        
			 	  	    			 else
			 	  	    			 {
			 	  	    				 _linea01.add(_importe);
			 	  	    			 }
			 	  	    			 // _linea01.add(_importe_sin_igv);
	*/
			 	       		
			 			     
			 			  	    
			 	  	    		 }
			 	  	    		 } 
			 	  	    	 }
			 	       	
			 	  	    	
			 	  	    	 document.add(_linea01);
			 	  	    	_linea01.removeAll(_linea01);
			 	  	     }


		  	     
		  	     
			        
		                                  // Q
		  	  	     
	  	     
	 	       
		        String _signo="";
		        
		  	     if (Cabecera.get_moneda().equals("PEN")) {
		  	    	 _signo="S/";
		  	     } else {
		  	    	_signo=" $";
		  	     }
		  	     
			        
			     // TOTAL subtotal
			        canvas.saveState();                               // q
			        canvas.beginText();                               // BT
			        canvas.moveText(455, 172);                         // 36 788 Td
			        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
			        
			        if (Cabecera.get_tipo_doc().equals("01")) {
			        //	canvas.showText("Subtotal:"+_signo+Formato.dinero(Cabecera.get_subtotal()));
			        }
			        
			        canvas.endText();                                 // ET
			        canvas.restoreState();                            // Q
			       
			        // TOTAL subtotal
			        canvas.saveState();                               // q
			        canvas.beginText();                               // BT
			        canvas.moveText(455, 162);                         // 36 788 Td
			        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
			        if (Cabecera.get_tipo_doc().equals("01")) {
			      //  	canvas.showText("IGV 18% :"+_signo+Formato.dinero(Cabecera.get_total_igv()));
			        }
			        canvas.endText();                                 // ET
			        canvas.restoreState();                            // Q  
			        
			        // TOTAL DE LA FACTURAS
			        canvas.saveState();                               // q
			        canvas.beginText();                               // BT
			        canvas.moveText(455, 152);                         // 36 788 Td
			        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
			     // canvas.showText("Total:      "+Cabecera.get_total());	        // (Hello World)Tj
			    //    canvas.showText("Total:   "+_signo+Formato.dinero(Cabecera.get_total()));
			        canvas.endText();                                 // ET
			        canvas.restoreState();                            // Q
			       
			  


	  	       
	  	       
	  	       
	    
	        
	        // step 5
	        document.close();		
		
		
		
	}

	
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


	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	
	
}

package imprimePDF;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class email {


	public static void main(String _correo_destino, 
							String _file_xml, 
							String _file_pdf, 
							String _file_respuesta, 
							String _file_base, 
							factura_cabecera Cabecera, 
							factura_detalle[] Detalle, 
							int  _lineas, 
							String _file_zip_respuesta, 
							String _user, 
							String _password,
							String _mail_smtp_auth,
							String _mail_smtp_starttls_enable,
							String _mail_smtp_host,
							String _puerto
							) {
  	
		
		

		System.out.println("Correo Destino:"+_correo_destino);
		
		  String to = _correo_destino;
	   //   String from = "comercialkriss@gmail.com";
		   String from = _user;
 	  
		   //   final String username = "comercialkriss@gmail.com";//change accordingly
		   final String username = _user;//change accordingly 
		   System.out.println("Usuario:"+_user);
		   
		   //   final String password = "2407kriss";//change accordingly
		   final String password = _password;//change accordingly
	      Properties props = new Properties();
	      System.out.println("Password:"+_password);
	   
	      //  _mail_smtp_auth
	    //  props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.auth", _mail_smtp_auth);
	      System.out.println("mail.smtp.auth:"+_mail_smtp_auth);
		   
	      
	      // _mail_smtp_starttls_enable
	      // props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.starttls.enable", _mail_smtp_starttls_enable);
	      System.out.println("mail.smtp.starttls.enable:"+_mail_smtp_starttls_enable);
	      
	      
	      // _mail_smtp_host
	      // props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.host", _mail_smtp_host);
	      System.out.println("mail.smtp.host"+_mail_smtp_host);
	      
	      
	      // _puerto
	      //props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.port", _puerto);
	      System.out.println("mail.smtp.port"+_puerto);
	      
	      
//	      String to = _correo_destino;
//	      String from = "facturacion@clubcsi.com.pe";
// 	      final String username = "facturacion@clubcsi.com.pe";
//	      final String password = "PgHsayzKrm";
//	      Properties props = new Properties();
//	      props.put("mail.smtp.auth", "true");
//	      props.put("mail.smtp.starttls.enable", "");
//	      props.put("mail.smtp.host", "rcentral520.webserversystems.com");
//	      props.put("mail.smtp.port", "587");
	  
	      
	      
		
	      
	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	    	  
	    	  
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));

		         
		         String _semi_serie=Cabecera.get_serie().substring(1, 4);
		         
		         if (_semi_serie.equals("005") || _semi_serie.equals("003") || _semi_serie.equals("202") || _semi_serie.equals("303") || _semi_serie.equals("403") || _semi_serie.equals("101") ) {
		        
		        	 message.addRecipient(Message.RecipientType.CC, new InternetAddress("gestionesjgomezxml@gmail.com"));
		        	 
		        	 
		        	 
		         }
		         
	//	         if (cc.length() !=0) {
//		        	 message.addRecipient(Message.RecipientType.CC, new InternetAddress(
//		                 cc));
//		         	}
		      
		         
		         
		         // Set Subject: header field
		         message.setSubject(Cabecera.get_razon_social_emisor()+"- Comprobante de Pago Electrónico Num:"+_file_base.substring(15)+", "+Cabecera.get_razon_social_receptor());

		         // Create the message part
		         BodyPart messageBodyPart = new MimeBodyPart();

		         
		         
		         
		         String _mensaje_html = Cabecera.get_mensaje_html();
		         
		    //     String _mensaje_html = "";
		         
		         
		         /// para los bancos
		         
		         // BANCO DE CREDITO
		         
		        
		         
		         
		         _mensaje_html=_mensaje_html.replace("#BANCO_SOLES1","BANCO DE CREDITO DEL PERU – BCP");
		         
		         _mensaje_html=_mensaje_html.replace("#CUENTA_SOLES1","SOLES:191-1934248-0-57");
		         _mensaje_html=_mensaje_html.replace("#CUENTA_SOLES_CCI1","SOLES CCI:002-191-001934248057-51");
		         
		         _mensaje_html=_mensaje_html.replace("#CUENTA_DOLARES1","DOLARES:191-1865961-1-99");
		         _mensaje_html=_mensaje_html.replace("#CUENTA_DOLARES_CCI1","DOLARES CCI:002-191-001865961199-52");
		         
		         
		         
		         
		         // BANCO EN DOLARES1
		         _mensaje_html=_mensaje_html.replace("#BANCO_SOLES2","BANCO CONTINENTAL - BBVA.");
		         
		         _mensaje_html=_mensaje_html.replace("#CUENTA_SOLES2","0011-0733-01-00004106");
		         _mensaje_html=_mensaje_html.replace("#CUENTA_SOLES_CCI2","SOLES CCI:011-733-000100004106-39");
		         
		         _mensaje_html=_mensaje_html.replace("#CUENTA_DOLARES2","0011-0733-01-00004114");
		         _mensaje_html=_mensaje_html.replace("#CUENTA_DOLARES_CCI2","DOLARES CCI:011-733-000100004114-33");
		         
		         
		         // CODIGO HASH
		         _mensaje_html=_mensaje_html.replace("#HASH",Cabecera.get_codigo_hash());
		         
		         
		         
		         // CODIGO NOTAS
String _notas ="Autorizado para ser emisor electrónico mediante la resolución de intendencia N° 174-005-0001120/SUNAT";
		     //    _notas=_notas+"     Si aún no hace la cancelación sírvase a realizarlo según condiciones de cotización, Órdenes de Compra - Servicio y enviar constancias de pago o número de operación a: ";
		         
		         _mensaje_html=_mensaje_html.replace("#NOTAS",_notas);
		         _mensaje_html=_mensaje_html.replace("#correo",username);
		         
		         
		         
		         
		         // para los datos de la factura
		         
		         _mensaje_html=_mensaje_html.replace("#FOLIO",Cabecera.get_folio());
		         _mensaje_html=_mensaje_html.replace("#SERIE",Cabecera.get_serie());
		         _mensaje_html=_mensaje_html.replace("#FECHA",Cabecera.get_fecha());
	//	         _mensaje_html=_mensaje_html.replace("#VENCIMIENTO",Cabecera.get_fecha_vencimiento());
		         _mensaje_html=_mensaje_html.replace("#GUIA",Cabecera.get_guia());
		//         _mensaje_html=_mensaje_html.replace("#F_PAGO",Cabecera.get_fecha_pago());
		         _mensaje_html=_mensaje_html.replace("#TIPO_DOCUMENTO",Cabecera.get_tipo_doc_descripcion());
		         
		         
		         
		         
		         
		         _mensaje_html=_mensaje_html.replace("#RUC_EMISOR",Cabecera.get_ruc_emisor());
		         _mensaje_html=_mensaje_html.replace("#RUC_RECEPTOR",Cabecera.get_ruc_receptor());
		         
		         
		         _mensaje_html=_mensaje_html.replace("#RAZON_SOCIAL_EMISOR",Cabecera.get_razon_social_emisor());
		         _mensaje_html=_mensaje_html.replace("#RAZON_SOCIAL_RECEPTOR",Cabecera.get_razon_social_receptor());
		         
		         
		         _mensaje_html=_mensaje_html.replace("#DIRECCION_EMISOR",Cabecera.get_direccion_emisor());
		         _mensaje_html=_mensaje_html.replace("#DIRECCION_RECEPTOR",Cabecera.get_direccion_receptor());
		         
		        	         
		         _mensaje_html=_mensaje_html.replace("#CORREO_EMISOR",from);
		         _mensaje_html=_mensaje_html.replace("#CORREO_RECEPTOR",_correo_destino);
		        
		         _mensaje_html=_mensaje_html.replace("#SUB_TOTAL",Formato.dinero(Cabecera.get_subtotal()));
		         _mensaje_html=_mensaje_html.replace("#IGV",Formato.dinero(Cabecera.get_total_igv()));
		         _mensaje_html=_mensaje_html.replace("#TOTAL",Formato.dinero(Cabecera.get_total()));
		         
		         
		         
		         
		         // para el detalle
		         
		         for (int i=0; i<_lineas; i++) {
			  	    	
			  	    	
		        	 System.out.println("linea."+i);
							
			  	    	
	  	    		 
		  	    	 
		     
	  	    		 
	  	    		 if (i==0) {
	  	    			 if (!Detalle[i].get_codigo().equals("www")) {
	  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY1",Formato.dinero(Detalle[i].get_cantidad()));
	  	    			_mensaje_html=_mensaje_html.replace("#ITEM1",Detalle[i].get_codigo());
	  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC1",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
	  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO1",Formato.dinero(Detalle[i].get_precio_unitario()));
	  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT1",Formato.dinero(Detalle[i].get_subtotal()));
	  	    			 }
	  	    			
	  	    		 }
	  	    		 
	  	    			
		    		 if (i==1) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY2",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM2",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC2",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO2",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT2",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

		    		 if (i==2) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY3",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM3",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC3",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO3",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT3",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

		    		 if (i==3) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY4",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM4",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC4",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO4",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT4",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

		    		 if (i==4) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY5",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM5",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC5",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO5",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT5",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

		    		 if (i==5) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY6",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM6",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC6",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO6",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT6",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

		    		 if (i==6) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY7",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM7",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC7",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO7",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT7",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

		    		 if (i==7) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY8",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM8",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC8",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO8",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT8",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

		    		 if (i==8) {
		    			 if (!Detalle[i].get_codigo().equals("www")) {
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY9",Formato.dinero(Detalle[i].get_cantidad()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM9",Detalle[i].get_codigo());
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC9",Formato.padRight(Formato.cadena40(Detalle[i].get_descripcion()),52));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO9",Formato.dinero(Detalle[i].get_precio_unitario()));
		  	    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT9",Formato.dinero(Detalle[i].get_subtotal()));
		    			 }
		  	    			
		  	    		 }

	  	    			
	  	    			
	  	    		 
	  	    	     
	  	    		 
	  	    		
		     }
	  	     
	  	     _lineas=_lineas-1;
	  	     
		      for (int x=_lineas;x<=9;x++) {  
		         
	    		 if (x==0) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY1","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM1","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC1","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO1","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT1","");
		    			
		    		 }
	    		 
	    		 
	       		 if (x==1) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY2","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM2","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC2","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO2","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT2","");
		    			
		    		 }

	       		 if (x==2) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY3","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM3","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC3","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO3","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT3","");
		    			
		    		 }

	       		 if (x==3) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY4","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM4","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC4","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO4","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT4","");
		    			
		    		 }

	       		 if (x==4) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY5","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM5","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC5","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO5","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT5","");
		    			
		    		 }

	       		 if (x==5) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY6","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM6","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC6","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO6","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT6","");
		    			
		    		 }

	       		 if (x==6) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY7","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM7","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC7","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO7","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT7","");
		    			
		    		 }

	       		 if (x==7) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY8","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM8","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC8","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO8","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT8","");
		    			
		    		 }

	       		 if (x==9) {

		    			_mensaje_html=_mensaje_html.replace("#ITEM_QTY9","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM9","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_DESC9","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_PRECIO9","");
		    			_mensaje_html=_mensaje_html.replace("#ITEM_TOT9","");
		    			
		    		 }


		      }

		      
		      
		
		         
		         
		         // _mensaje_html = _mensaje_html.replace("#DOCUMENTO",Cabecera.get_folio()) 
		         
	         
	         
	         // Now set the actual message
	         messageBodyPart.setContent(_mensaje_html,"text/html; charset=utf-8" );
	         

	         
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment _file_xml
	         messageBodyPart = new MimeBodyPart();
	         String filename_xml = _file_xml;
	         DataSource source_xml = new FileDataSource(filename_xml);
	         messageBodyPart.setDataHandler(new DataHandler(source_xml));
	         messageBodyPart.setFileName(_file_base+".xml");
	         multipart.addBodyPart(messageBodyPart);


	         // Part two is attachment _file_pdf
	         messageBodyPart = new MimeBodyPart();
	         String filename_pdf = _file_pdf;
	         DataSource source_pdf = new FileDataSource(filename_pdf);
	         messageBodyPart.setDataHandler(new DataHandler(source_pdf));
	         messageBodyPart.setFileName(_file_base+".pdf");
	         multipart.addBodyPart(messageBodyPart);
	         

	         // Part two is attachment _file_respuesta
	         File f = new File(_file_respuesta);
	         if(f.exists() && !f.isDirectory()) { 
	        		         messageBodyPart = new MimeBodyPart();
	        		         String filename_respuesta = _file_zip_respuesta;
	        		         DataSource source_respuesta = new FileDataSource(filename_respuesta);
	        		         messageBodyPart.setDataHandler(new DataHandler(source_respuesta));
	        		         messageBodyPart.setFileName("r-"+_file_base+".xml");
	        		         multipart.addBodyPart(messageBodyPart);

	             
	         }
	         
	         
	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	       //  System.out.println("Correo Enviado Correctamente....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	}






	}



package imprimePDF;

/*
 * Ticket.java
 * 
 * Copyright 2013 Josue Camara <picharras@picharras-HP-Folio>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 */

import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.ServiceUI;
import javax.print.attribute.*;



public class _TicketMiniPos {
	 
	
	  
	byte[] comecoTextoCondensado = {0x45, 0x0F};  
	byte[] fimTextoCondensado = {0x12};  
	char[] cortePapel = new char[]{0x1d, 'V', 1};  
	  
	
	String texto_bottom =  
		    new String(comecoTextoCondensado)  
		    + new String(" \n\n\n\n\n")  
		    + new String(fimTextoCondensado)  
		    + new String(cortePapel);
	
	  //Ticket attribute content
	private String contentTicket = "\f\n"+ 
		"=========================================="+"\n"+
		"{{nameLocal}}\n"+
	    "{{expedition}}\n"+
	    "{{dateTime}}\n"+
	    "{{cajero}}\n"+
	    "=========================================="+"\n"+
	    "{{_ruc}}\n"+
	    "{{_tipo_doc_descripcion}}\n"+
	    "{{_folio}}\n"+
	    "=========================================="+"\n"+   		
		"{{_ruc_receptor}}\n"+
		"{{_razon_social_receptor}}\n"+
		"{{_direccion_receptor}}\n"+
		"{{_fecha_emision}}\n"+
		"{{_tipo_moneda}}\n"+
		"{{_tipo_igv}}\n"+
    	"=========================================="+"\n"+	
	    "Descripcion         Cant. Precio     Total"+"\n"+
	    "=========================================="+"\n"+
	    "{{items}}\n"+
		"=========================================="+"\n"+
		"{{_total_letras}}\n\n"+
	    "                     SUBTOTAL: {{subTotal}}\n"+
	    "                      IGV 18%: {{tax}}\n"+
	    "                        TOTAL: {{total}}\n\n"+
		"=========================================="+"\n"+
		"{{_hash}}\n\n"+
		
		"{{_leyenda}}\n\n\n\n\n\n\n\n\n"+
		texto_bottom;
	
	 public String _cadena_ticket=contentTicket;
	
	 
	
	  
	    //El constructor que setea los valores a la instancia
	    _TicketMiniPos( String linea01,
	    		String linea02,
	    		String box,
	    		String ticket,
	    		String linea04,
	    		String linea03,
	    		String items,
	    		String subTotal,
	    		String tax,
	    		String total,
	    		String recibo,
	    		String change,	
	    		String _ruc,
	    		String _tipo_doc_descripcion,
	    		String _folio, 
	    		String _ruc_receptor,
	    		String _razon_social_receptor,
	    		String _direccion_receptor,
	    		String _fecha_emision,
	    		String _tipo_moneda,
	    		String _tipo_igv,
	    		String _total_letras,
	    		String _hash,
	    		String _leyenda,
	    		String _ruta_tickets) throws IOException {
	
	
	this.contentTicket = this.contentTicket.replace("{{nameLocal}}", linea01);
    this.contentTicket = this.contentTicket.replace("{{expedition}}", linea02);
    this.contentTicket = this.contentTicket.replace("{{box}}", box);
    this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
    this.contentTicket = this.contentTicket.replace("{{cajero}}", linea04);
    this.contentTicket = this.contentTicket.replace("{{dateTime}}", linea03);
    this.contentTicket = this.contentTicket.replace("{{items}}", items);
    this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
    this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
    this.contentTicket = this.contentTicket.replace("{{total}}", total);
    this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
    this.contentTicket = this.contentTicket.replace("{{change}}", change);
    
    this.contentTicket = this.contentTicket.replace("{{_ruc}}", _ruc);
    this.contentTicket = this.contentTicket.replace("{{_tipo_doc_descripcion}}", _tipo_doc_descripcion);
    this.contentTicket = this.contentTicket.replace("{{_folio}}", _folio);
    this.contentTicket = this.contentTicket.replace("{{_ruc_receptor}}", _ruc_receptor);
    _razon_social_receptor=_razon_social_receptor.replace('ú','u');
    _razon_social_receptor=_razon_social_receptor.replace('á','a');
    _razon_social_receptor=_razon_social_receptor.replace('ó','o');
    _razon_social_receptor=_razon_social_receptor.replace('í','i');
    _razon_social_receptor=_razon_social_receptor.replace('é','e');
    
    this.contentTicket = this.contentTicket.replace("{{_razon_social_receptor}}", _razon_social_receptor);
    this.contentTicket = this.contentTicket.replace("{{_direccion_receptor}}", _direccion_receptor);
    this.contentTicket = this.contentTicket.replace("{{_fecha_emision}}", _fecha_emision);
    this.contentTicket = this.contentTicket.replace("{{_tipo_moneda}}", _tipo_moneda);
    this.contentTicket = this.contentTicket.replace("{{_tipo_igv}}", _tipo_igv);
    this.contentTicket = this.contentTicket.replace("{{_total_letras}}", _total_letras);
    this.contentTicket = this.contentTicket.replace("{{_hash}}", _hash);
    this.contentTicket = this.contentTicket.replace("{{_leyenda}}", _leyenda);
    
    print();
    
	// imprimir a Disco
	
    String _file_name_ticket=_ruta_tickets+_folio+".txt";
    System.out.println("Ruta Ticket: "+_file_name_ticket);
    
 //   System.out.println(_cadena_ticket);
//	File archivo_ticket=new File(_file_name_ticket);
//	archivo_ticket.delete();
//	FileWriter chanel_write;

//	chanel_write = new FileWriter(archivo_ticket,true);
	
		
//	chanel_write.write(_cadena_ticket);
//	chanel_write.close();


  }
    
  public void print() {
	  
	  
    //Especificamos el tipo de dato a imprimir
    //Tipo: bytes; Subtipo: autodetectado
 
	  DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    
    //Aca obtenemos el servicio de impresion por defatul
    //Si no quieres ver el dialogo de seleccionar impresora usa esto
  //  PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    
	  
	  
	  
	  
	  
    
    //Con esto mostramos el dialogo para seleccionar impresora
    //Si quieres ver el dialogo de seleccionar impresora usalo
    //Solo mostrara las impresoras que soporte arreglo de bits
    
    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
 
    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    
   // esta linea se activa para preguntar la impresora 
  // PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
    
   // esta es la linea que se activa para mandar sin preguntar 
    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
    
  
    System.out.println("Tu impresora por default es: " + service.getName());

    //Creamos un arreglo de tipo byte
    byte[] bytes;

    //Aca convertimos el string(cuerpo del ticket) a bytes tal como
    //lo maneja la impresora(mas bien ticketera :p)
    bytes = this.contentTicket.getBytes();

    //Creamos un documento a imprimir, a el se le appendeara
    //el arreglo de bytes
    Doc doc = new SimpleDoc(bytes,flavor,null);
      
    //Creamos un trabajo de impresión
    DocPrintJob job = service.createPrintJob();

    //Imprimimos dentro de un try de a huevo
    try {
      //El metodo print imprime
      job.print(doc, null);
      
      
    } catch (Exception er) {
      JOptionPane.showMessageDialog(null,"Error al imprimir: " + er.getMessage());
    }
    
    
    
    
  }

}
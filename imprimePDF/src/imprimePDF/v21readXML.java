package imprimePDF;

import java.io.File;



import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itextpdf.text.DocumentException;

//import wsHomologador.detalle;



public class v21readXML {


	public static factura_cabecera Cabecera = new factura_cabecera();
	public static factura_detalle[] Detalle = new factura_detalle[200];
	public static factura_detalle_email[] Detalle_email = new factura_detalle_email[200];
	public static reglones[] misReglones = new reglones[10];
	public static palabras[] arregloPalabras = new palabras[200];
	public static int _lineas_de_la_factura=0;
	public static int _lineas_Descripcion=0;

	public static documentos_rel[] Rel = new documentos_rel[20];

	public static void readXML(String _file_name, 
			String _correos, 
			parametros misParametros, 
			String _user, 
			String _password,
			String _mail_smtp_auth,
			String _mail_smtp_starttls_enable,
			String _mail_smtp_host,
			String _puerto
			) throws IOException {




		String _file= _file_name;
		String _correo_destino = "";
		if (!isNullOrEmpty(_correos)) {
			_correo_destino = _correos;
		} else {
			_correo_destino= "nada";

		}



		String _file_xml = misParametros.get_ruta_xml_con_firma()+_file+".xml";;
		String _file_respuesta = misParametros.get_ruta_respuestas()+"r-"+_file+".xml";
		String _file_pdf = misParametros.get_ruta_pdfs()+_file+".pdf";
		String _file_html = misParametros.get_ruta_formatos()+"formato.htm";
		String _file_zip_respuesta = misParametros.get_ruta_respuestas()+"R-"+_file+".xml";;
		//String _file_jpg = misParametros.get_ruta_formatos();
		String _file_jpg = misParametros.get_ruta_formatos_membrete();
		String _file_pdf_para_imprimir = misParametros.get_ruta_pdfs()+"Para_Imprimir_"+_file+".pdf";

		String _file_pdf_termica = ".\\data\\20525937195\\05_pdfs\\"+_file+"_termica.pdf";




		for (int _n = 0; _n < 200; _n++) {
			Detalle[_n] = new factura_detalle();
		}


		for (int _n = 0; _n <100; _n++) {
			Detalle_email[_n] = new factura_detalle_email();
		}







		File fXmlFile = new File(_file_xml);
		try {








			String raya="----------------------------------------------------------------";

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);


			doc.getDocumentElement().normalize();

			//		NodeList nList = doc.getElementsByTagName("Invoice");

			System.out.println("DATOS DEL DOCUMENTO");
			String _temp = _file;
			int _num = _temp.length();

			Cabecera.set_serie(_temp.substring(15,19));
			Cabecera.set_folio(_temp.substring(20,_num));

			Cabecera.set_guia("");


			System.out.println(raya);

			Cabecera.set_descripcion_documento(doc.getDocumentElement().getNodeName());	
			System.out.println("Documento _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_descripcion_documento());


			NodeList nList_linea = doc.getElementsByTagName("cbc:InvoicedQuantity");
			NodeList nList_InvoicedQuantity = doc.getElementsByTagName("cbc:InvoicedQuantity");

			Cabecera.set_tipo_doc_descripcion("FACTURA");
			if (Cabecera.get_descripcion_documento().equals("Invoice")) {
				Cabecera.set_tipo_doc_descripcion("FACTURA ELECTRONICA");
				//Cabecera.set_Ruc_Dni("    ");
				Cabecera.set_Ruc_Dni("RUC:");

				// cbc:ID	cantidad


			} 

			if (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				Cabecera.set_tipo_doc_descripcion("  NOTA DE CREDITO  ");
				Cabecera.set_Ruc_Dni("    ");

				// SI ES NOTA DE CREDITO   cac:DiscrepancyResponse
				// cbc:ReferenceID

				NodeList nList_ReferenceID = doc.getElementsByTagName("cbc:ReferenceID");
				Node nNode_ReferenceID = nList_ReferenceID.item(0);
				Cabecera.set_doc_relacionado(nNode_ReferenceID.getTextContent());
				System.out.println("Documento Relacionado _ _ _ _ _: " + Cabecera.get_doc_relacionado());


				// cbc:Description
				NodeList nList_Description = doc.getElementsByTagName("cbc:Description");
				Node nNode_Description = nList_Description.item(0);
				Cabecera.set_motivo_de_anulacion(nNode_Description.getTextContent());
				System.out.println("Motivo de Anulacion_ _ _ _ _ _ _: " + Cabecera.get_motivo_de_anulacion());


				nList_linea = doc.getElementsByTagName("cbc:CreditedQuantity");
				nList_InvoicedQuantity = doc.getElementsByTagName("cbc:CreditedQuantity");



			} 


			if (Cabecera.get_descripcion_documento().equals("DebitNote")) {
				Cabecera.set_tipo_doc_descripcion("  NOTA DE DEBITO   ");
				Cabecera.set_Ruc_Dni("    ");

				NodeList nList_ReferenceID = doc.getElementsByTagName("cbc:ReferenceID");
				Node nNode_ReferenceID = nList_ReferenceID.item(0);
				Cabecera.set_doc_relacionado(nNode_ReferenceID.getTextContent());
				System.out.println("Documento Relacionado _ _ _ _ _: " + Cabecera.get_doc_relacionado());


				// cbc:Description
				NodeList nList_Description = doc.getElementsByTagName("cbc:Description");
				Node nNode_Description = nList_Description.item(0);
				Cabecera.set_motivo_de_anulacion(nNode_Description.getTextContent());
				System.out.println("Motivo de Anulacion_ _ _ _ _ _ : " + Cabecera.get_motivo_de_anulacion());


				// cbc:ID	cantidad
				nList_linea = doc.getElementsByTagName("cbc:DebitedQuantity");
				nList_InvoicedQuantity = doc.getElementsByTagName("cbc:DebitedQuantity");	



			} 




			// cbc:IssueDate
			NodeList nList_IssueDate = doc.getElementsByTagName("cbc:IssueDate");
			Node nNode_IssueDate = nList_IssueDate.item(0);

			//Cabecera.set_fecha(nNode_IssueDate.getTextContent());

			String _fecha = nNode_IssueDate.getTextContent();

			String _Dia = "";
			String _Mes = "";
			String _Ano = "";
			_Dia = _fecha.substring(8, 10);  //2016.09.17  2016-11-30
			_Mes = _fecha.substring(5, 7);  //2016.09.17  0123456789
			_Ano = _fecha.substring(0, 4);             // 1234567890
			Cabecera.set_fecha( _Dia+"-"+_Mes+"-"+_Ano);
			System.out.println("Fecha del Docto _ _ _ _ _ _ _ _: " + Cabecera.get_fecha());	


			// cbc:DueDate

			NodeList nList_DueDate = doc.getElementsByTagName("cbc:DueDate");


			try {
				Node nNode_DueDate = nList_DueDate.item(0);
				String _fecha_vencimiento = nNode_DueDate.getTextContent();

				_Dia = "";
				_Mes = "";
				_Ano = "";
				_Dia = _fecha_vencimiento.substring(8, 10);  //2016.09.17  2016-11-30
				_Mes = _fecha_vencimiento.substring(5, 7);  //2016.09.17  0123456789
				_Ano = _fecha_vencimiento.substring(0, 4);             // 1234567890
				Cabecera.set_fecha_vencimiento("");
				System.out.println("Fecha del Venciminento _ _ _ _: " + Cabecera.get_fecha_vencimiento());	

			} catch (Exception e) {
				Cabecera.set_fecha_vencimiento("");

			}



			// cbc:InvoiceTypeCode
			NodeList nList_InvoiceTypeCode = doc.getElementsByTagName("cbc:InvoiceTypeCode");
			try {
				Node nNode_InvoiceTypeCode = nList_InvoiceTypeCode.item(0);
				Cabecera.set_tipo_doc(nNode_InvoiceTypeCode.getTextContent());
				System.out.println("Tipo del Documento: _ _ _ _ _ _: " + Cabecera.get_tipo_doc());

				if (Cabecera.get_tipo_doc().substring(1).equals("3")) {
					Cabecera.set_tipo_doc_descripcion("BOLETA ELECTRONICA");
					//Cabecera.set_Ruc_Dni("    ");
					Cabecera.set_Ruc_Dni("DNI:");
				}

			} catch (Exception e) {

			}




			if (Cabecera.get_descripcion_documento().equals("CreditNote")) {
				Cabecera.set_tipo_doc("07");
				System.out.println("Tipo del Documento: _ _ _ _ _ _: " + Cabecera.get_tipo_doc());
			} 

			if (Cabecera.get_descripcion_documento().equals("DebitNote")) {
				Cabecera.set_tipo_doc("08");
				System.out.println("Tipo del Documento: _ _ _ _ _ _: " + Cabecera.get_tipo_doc());
			} 


			// cbc:ProfileID
			NodeList nList_ProfileID = doc.getElementsByTagName("cbc:ProfileID");

			try {	
				Node nNode_ProfileID = nList_ProfileID.item(0);
				Cabecera.set_profile(nNode_ProfileID.getTextContent());
			} catch (Exception e) {
				Cabecera.set_profile("0101");

			}


			String _profile=Cabecera.get_profile();

			Cabecera.set_tipo_operacion("-");

			System.out.println("Tipo Transaccion:_ _ _ _ _ _ _ : " + _profile);


			Cabecera.set_tipo_operacion("-");


			if (_profile.equals("0101")) {
				Cabecera.set_tipo_operacion("Venta Interna");
			}



			//				if (_profile.equals("02")) {
			//					Cabecera.set_tipo_operacion("Expotación");
			//				}


			//				if (_id.equals("03")) {
			//					Cabecera.set_tipo_operacion("No Domicilado");
			//				}

			if (_profile.equals("0102")) {
				Cabecera.set_tipo_operacion("Anticipo");
			}

			//				if (_id.equals("05")) {
			//				Cabecera.set_tipo_operacion("Vta Itinerante");
			//				}

			//				if (_id.equals("06")) {
			//					Cabecera.set_tipo_operacion("Factura Guia");
			//				}






			//  documento.getDocumentElement().getChildNodes().item(0).getFirstChild().getChildNodes().item(0).getAttributes().getNamedItem("data").getNodeValue());








			// cbc:DocumentCurrencyCode
			NodeList nList_DocumentCurrencyCode = doc.getElementsByTagName("cbc:DocumentCurrencyCode");
			Node nNode_DocumentCurrencyCode = nList_DocumentCurrencyCode.item(0);
			Cabecera.set_moneda(nNode_DocumentCurrencyCode.getTextContent());
			System.out.println("Tipo de Moneda_ _ _ _ _ _ _ _ _: " + Cabecera.get_moneda());

			System.out.println(raya);






			// datos adicioneales que se neesitan par ael QR

			// tipo doc aquiriente
			NodeList nList_tipo_doc_ad = doc.getElementsByTagName("cbc:ID");
			Node nNode_tipo_doc = nList_tipo_doc_ad.item(4);
			String _ident = nNode_tipo_doc.getTextContent();

			// schemeID



			//		NamedNodeMap attr_tipo_doc_ad = nNode_tipo_doc.getAttributes();
			//		String _tipo_doc_ad="";
			//		if (null != attr_tipo_doc_ad) {
			//			Node p = attr_tipo_doc_ad.getNamedItem("schemeID");
			//			_tipo_doc_ad=p.getNodeValue();
			//		}




			Cabecera.set_tipo_doc_adquiriente(nNode_tipo_doc.getTextContent());
			Cabecera.set_fecha_qr(_fecha);
			Cabecera.set_tipo_documento(_file.substring(12,14));


			// calculaor las lineas  // cbc:LineCountNumeric
			NodeList nList_LineCountNumeric = doc.getElementsByTagName("cbc:LineCountNumeric");
			Node nNode_LineCountNumeric = nList_LineCountNumeric.item(0);
			int _lines=Integer.parseInt(nNode_LineCountNumeric.getTextContent());


			System.out.println("RUC Emisor_ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_ruc_emisor());


			// cbc:CustomerAssignedAccountID
			NodeList nList_CustomerAssignedAccountID = doc.getElementsByTagName("cac:AccountingSupplierParty");
			Node nNode_CustomerAssignedAccountID = nList_CustomerAssignedAccountID.item(0);
			String _ruc_emisor=nNode_CustomerAssignedAccountID.getTextContent();
			Cabecera.set_ruc_emisor(_ruc_emisor.substring(0, 11));

			System.out.println("RUC Emisor_ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_ruc_emisor());


			// <cac:PartyName><cbc:Name><![CDATA[FACTURA GLOBAL S.A.C.]]></cbc:Name></cac:PartyName>


			// cac:PartyName
			NodeList nList_PartyName = doc.getElementsByTagName("cac:PartyName");
			Node nNode_PartyName = nList_PartyName.item(0);
			Cabecera.set_razon_social_emisor(nNode_PartyName.getTextContent());
			System.out.println("Razon Social del Emisor_ _ _ _ : " + Cabecera.get_razon_social_emisor());




			// cbc:StreetName
			NodeList nList_StreetName = doc.getElementsByTagName("cbc:Line");
			Node nNode_StreetName = nList_StreetName.item(0);
			Cabecera.set_direccion_emisor(nNode_StreetName.getTextContent());
			System.out.println("Direccion del Emisor_ _ _ _ _ _: " + Cabecera.get_direccion_emisor());



			// cbc:ID	ubigeo
			NodeList nList_ubigeo = doc.getElementsByTagName("cbc:CountrySubentity");
			Node nNode_ubigeo = nList_ubigeo.item(0);
			Cabecera.set_ubigeo_emisor(nNode_ubigeo.getTextContent());
			System.out.println("Ubigeo del Emisor _ _ _ _ _ _ _: " + Cabecera.get_ubigeo_emisor());



			System.out.println(raya);


			// get tipo de identidad receptor

			// cac:AccountingCustomerParty
			NodeList nList_AccountingCustomerParty = doc.getElementsByTagName("cac:AccountingCustomerParty");
			Node nNode_AccountingCustomerParty = nList_AccountingCustomerParty.item(0);
			String _cadena_AccountingCustomerParty=nNode_AccountingCustomerParty.getTextContent();
			int len_AccountingCustomerParty=_cadena_AccountingCustomerParty.length();
			String _ruc_receptor="";

			// valida hasta donde llega el numero de documento

			for (int _z=0; _z<=len_AccountingCustomerParty;_z++) {
				String _car=_cadena_AccountingCustomerParty.substring(_z,_z+1);
				if (isNumeric(_car)) {
					_ruc_receptor=_ruc_receptor+_car;
				} else {
					if (_z>8) {
						break;						
					}

				}
			}


			Cabecera.set_ruc_receptor(_ruc_receptor);	
			System.out.println("RUC Receptor_ _ _ _ _ _ _ _ _ _: " + Cabecera.get_ruc_receptor());

			String _tipo_ident_receptor="4";

			if (_ruc_receptor.length()==11) {
				_tipo_ident_receptor="6";
			}

			if (_ruc_receptor.length()==8) {
				_tipo_ident_receptor="1";
			}

			Cabecera.set_tipo_doc_adquiriente(_tipo_ident_receptor);
			System.out.println("Tipo de Documento Adquiriente _: " + Cabecera.get_tipo_doc_adquiriente());





			// cac:PartyName
			NodeList nList_PartyName_receptor = doc.getElementsByTagName("cbc:RegistrationName");
			Node nNode_PartyName_receptor = nList_PartyName_receptor.item(1);
			Cabecera.set_razon_social_receptor(nNode_PartyName_receptor.getTextContent());
			System.out.println("Razon Social del Receptor_ _ _ : " + Cabecera.get_razon_social_receptor());




			// cbc:StreetName
			NodeList nList_StreetName_r = doc.getElementsByTagName("cbc:Line");
			Node nNode_StreetName_r = nList_StreetName_r.item(1);
			Cabecera.set_direccion_receptor(nNode_StreetName_r.getTextContent());
			System.out.println("Direccion del Receptor_ _ _ _ _: " + Cabecera.get_direccion_receptor());





			Cabecera.set_linea01("");
			Cabecera.set_linea02("");
			Cabecera.set_linea03("");
			Cabecera.set_linea04("");
			Cabecera.set_linea05("");
			Cabecera.set_linea06("");
			Cabecera.set_linea07("");
			Cabecera.set_linea08("");




			// prepcac:PrepaidPaymentaid 

			System.out.println(raya);




			NodeList nList_pre = doc.getElementsByTagName("cac:PrepaidPayment");
			String _id_pre = "";
			double _prepaidAmount = 0;
			String _doc_id = "";



			for (int temp = 0; temp < nList_pre.getLength(); temp++) {

				Node nNode_pre = nList_pre.item(temp);


				Element eElement_pre = (Element) nNode_pre;

				_id_pre=eElement_pre.getElementsByTagName("cbc:ID").item(0).getTextContent();
				_prepaidAmount=Double.parseDouble(eElement_pre.getElementsByTagName("cbc:PaidAmount").item(0).getTextContent());
				_doc_id=eElement_pre.getElementsByTagName("cbc:InstructionID").item(0).getTextContent();
				//		System.out.println("ID:"+_id+" "+"Payable:"+_PayableAmount);





			}



			double _total_impuestos=0;


			//cbc:TaxableAmount   MONTO GRABADO TOTAL
			NodeList nList_TaxableAmount_tot = doc.getElementsByTagName("cbc:TaxAmount");
			Node nNode_TaxableAmount_tot = nList_TaxableAmount_tot.item(0);
			_total_impuestos=Double.parseDouble(nNode_TaxableAmount_tot.getTextContent());
			System.out.println("Total de Impuestos _ _ _ _ _ _: " + _total_impuestos);



			// cbc:TaxableAmount   PARTE GRAVADA

			String _texto_impuesto="";
			boolean _esta_igv=false;
			boolean _esta_exo=false;
			boolean _esta_ina=false;
			boolean _esta_isc=false;
			boolean _esta_icbper=false;




			NodeList nList_TaxableAmount_gravado_x = doc.getElementsByTagName("cac:TaxSubtotal");
			int item_TaxableAmount_gravado= nList_TaxableAmount_gravado_x.getLength();
			for (int _z=0;_z<=item_TaxableAmount_gravado;_z++) {

				_texto_impuesto="";


				try {
					Node nNode_TaxableAmount_gravado = nList_TaxableAmount_gravado_x.item(_z);
					_texto_impuesto=nNode_TaxableAmount_gravado.getTextContent();





				} catch (Exception e) {
					_texto_impuesto="";
				}	




				if  (_texto_impuesto.contains("IGV") && (_esta_igv==false)) {
					NodeList nList_TaxableAmount_gravado = doc.getElementsByTagName("cbc:TaxableAmount");
					Node nNode_TaxableAmount_gravado = nList_TaxableAmount_gravado.item(_z);
					double _total_gravado=Double.parseDouble(nNode_TaxableAmount_gravado.getTextContent());
					Cabecera.set_total_gravado(_total_gravado);
					_esta_igv=true;
					System.out.println("Base Gravable_ _ _ __ _ _ _ _ : "+ Cabecera.get_total_gravado());

					NodeList nList_TaxableAmount_igv = doc.getElementsByTagName("cbc:TaxAmount");
					Node nNode_TaxableAmount_igv = nList_TaxableAmount_igv.item(_z+1);
					double _total_igv=Double.parseDouble(nNode_TaxableAmount_igv.getTextContent());
					Cabecera.set_total_igv(_total_igv);
					System.out.println("Total de IGV_ _ _ _ _ _ _ _ _ : "+ Cabecera.get_total_igv());
				}

				if  (_texto_impuesto.contains("EXO") && (_esta_exo==false)) {
					NodeList nList_TaxableAmount_exo = doc.getElementsByTagName("cbc:TaxableAmount");
					Node nNode_TaxableAmount_exo = nList_TaxableAmount_exo.item(_z);
					double _total_exo=Double.parseDouble(nNode_TaxableAmount_exo.getTextContent());
					Cabecera.set_total_exonerado(_total_exo);
					_esta_exo=true;
					System.out.println("Exonerado_ _ _ _ _ _ _ _ _ _ _: "+ Cabecera.get_total_exonerado());
				}


				if  (_texto_impuesto.contains("INA") && (_esta_ina==false))  {
					NodeList nList_TaxableAmount_ina = doc.getElementsByTagName("cbc:TaxableAmount");
					Node nNode_TaxableAmount_ina = nList_TaxableAmount_ina.item(_z);
					double _total_ina=Double.parseDouble(nNode_TaxableAmount_ina.getTextContent());
					Cabecera.set_total_inafecto(_total_ina);
					_esta_ina=true;
					System.out.println("Inafecto_ _ _ _ _ _ _ _ _ _ _ : "+ Cabecera.get_total_inafecto());
				}
				/*
				if  (_texto_impuesto.contains("ISC") && (_esta_isc==false)) {
					NodeList nList_TaxableAmount_isc = doc.getElementsByTagName("cbc:TaxableAmount");
					Node nNode_TaxableAmount_isc = nList_TaxableAmount_isc.item(_z);
					double _total_isc=Double.parseDouble(nNode_TaxableAmount_isc.getTextContent());
					Cabecera.set_total_isc(_total_isc);
					_esta_isc=true;

					System.out.println("ISC_ _ _ _ _ _ _ _ _ _ _ _ _ _: "+ Cabecera.get_total_isc());
				}
				 */

				if  (_texto_impuesto.contains("ICBPER") && (_esta_icbper==false)) {
					NodeList nList_TaxableAmount_icbper = doc.getElementsByTagName("cbc:TaxAmount");
					Node nNode_TaxableAmount_icbper = nList_TaxableAmount_icbper.item(_z+1);
					double _total_icbper=Double.parseDouble(nNode_TaxableAmount_icbper.getTextContent());
					Cabecera.set_total_icbper(_total_icbper);
					_esta_icbper=true;
					System.out.println("ICBPER_ _ _ _ _ _ _ _ _ _ _ _ : "+ Cabecera.get_total_icbper());

				}




			}

			System.out.println(raya);

			Cabecera.set_remision01("");
			Cabecera.set_remision02("");
			Cabecera.set_remision03("");
			Cabecera.set_transportista01("");


			// cbc:Note notas de el xml

			NodeList nList_Notes = doc.getElementsByTagName("cbc:Note");
			try {
				Node nNode_Note01 = nList_Notes.item(1);
				Cabecera.set_remision01(nNode_Note01.getTextContent());
				if (Cabecera.get_remision01().equals("-")) {
					Cabecera.set_remision01("");
				}
			} catch (Exception e) {
				Cabecera.set_remision01("");
			}
			System.out.println("Remision 01:"+Cabecera.get_remision01());


			try {
				Node nNode_Note02 = nList_Notes.item(2);
				Cabecera.set_remision02(nNode_Note02.getTextContent());
				if (Cabecera.get_remision02().equals("-")) {
					Cabecera.set_remision02("");
				}

			} catch (Exception e) {
				Cabecera.set_remision02("");
			}
			System.out.println("Remision 02:"+Cabecera.get_remision02());


			try {
				Node nNode_Note03 = nList_Notes.item(3);
				Cabecera.set_remision03(nNode_Note03.getTextContent());
				if (Cabecera.get_remision03().equals("-")) {
					Cabecera.set_remision03("");
				}

				
			} catch (Exception e) {
				Cabecera.set_remision03("");
			}
			System.out.println("Remision 03:"+Cabecera.get_remision03());


			try {
				Node nNode_Note04 = nList_Notes.item(4);
				Cabecera.set_transportista01(nNode_Note04.getTextContent());
				
				if (Cabecera.get_transportista01().equals("-")) {
					Cabecera.set_transportista01("");
				}

			} catch (Exception e) {
				Cabecera.set_transportista01("");
			}
			System.out.println("Transportista 04:"+Cabecera.get_transportista01());


			
			
			

			// cbc:LineExtensionAmount
			NodeList nList_sub_total = doc.getElementsByTagName("cbc:LineExtensionAmount");
			Node nNode_sub_total = nList_sub_total.item(0);
			double _sub_total=Double.parseDouble(nNode_sub_total.getTextContent());
			Cabecera.set_subtotal(_sub_total);
			System.out.println("Sub Total _ _ _ _ _ _ _ _ _ _ : " + Cabecera.get_subtotal());



			// cbc:TaxableAmount
			NodeList nList_TaxableAmount_Det = doc.getElementsByTagName("cbc:TaxableAmount");





			Cabecera.set_total_descuentos(0.00);
			//			System.out.println("Importe Descuentos _ _ _ _ _ _: " + Cabecera.get_total_descuentos());


			NodeList nList_desc_cab = doc.getElementsByTagName("cbc:AllowanceTotalAmount");
			Node nNode_desc_cab = nList_desc_cab.item(0);
			Cabecera.set_descuentos_cabecera(Double.parseDouble(nNode_desc_cab.getTextContent()));


			System.out.println("Descuento Cabecera_  _ _ _ _ _: " + Cabecera.get_descuentos_cabecera());


			if (Cabecera.get_descripcion_documento().equals("Invoice")) {

				// cac:LegalMonetaryTotal
				NodeList nList_total = doc.getElementsByTagName("cac:LegalMonetaryTotal");
				double _PayableAmount_total = 0;
				Node nNode_total = nList_total.item(0);
				Element eElement_total = (Element) nNode_total;
				_PayableAmount_total=Double.parseDouble(eElement_total.getElementsByTagName("cbc:PayableAmount").item(0).getTextContent());
				Cabecera.set_total(_PayableAmount_total);
				System.out.println("Importe Total_ _  _ _ _ _ _ _ : " + Cabecera.get_total());
				Cabecera.set_total_letra(denomina.main(Cabecera.get_total()-Cabecera.get_total_descuentos()));
				System.out.println("Importe con Letra _ _ _ _ _ _ : " + Cabecera.get_total_letra());

			}




			if (Cabecera.get_descripcion_documento().equals("CreditNote")) {

				// cac:LegalMonetaryTotal
				NodeList nList_total = doc.getElementsByTagName("cac:LegalMonetaryTotal");
				double _PayableAmount_total = 0;
				Node nNode_total = nList_total.item(0);
				Element eElement_total = (Element) nNode_total;
				_PayableAmount_total=Double.parseDouble(eElement_total.getElementsByTagName("cbc:PayableAmount").item(0).getTextContent());
				Cabecera.set_total(_PayableAmount_total);
				System.out.println("Importe Total_ _  _ _ _ _ _ _ : " + Cabecera.get_total());
				Cabecera.set_total_letra(denomina.main(Cabecera.get_total()-Cabecera.get_total_descuentos()));
				System.out.println("Importe con Letra _ _ _ _ _ _ : " + Cabecera.get_total_letra());

			}



			if (Cabecera.get_descripcion_documento().equals("DebitNote")) {
				// cac:RequestedMonetaryTotal

				NodeList nList_total = doc.getElementsByTagName("cac:RequestedMonetaryTotal");
				double _PayableAmount_total = 0;
				Node nNode_total = nList_total.item(0);
				Element eElement_total = (Element) nNode_total;
				_PayableAmount_total=Double.parseDouble(eElement_total.getElementsByTagName("cbc:PayableAmount").item(0).getTextContent());
				Cabecera.set_total(_PayableAmount_total);
				System.out.println("Importe Total_ _  _ _ _ _ _ _ : " + Cabecera.get_total());
				Cabecera.set_total_letra(denomina.main(Cabecera.get_total()-Cabecera.get_total_descuentos()));
				System.out.println("Importe con Letra _ _ _ _ _ _ : " + Cabecera.get_total_letra());



			}









			// DigestValue
			NodeList nList_DigestValue = doc.getElementsByTagName("DigestValue");
			Node nNode_DigestValue = nList_DigestValue.item(0);
			Cabecera.set_codigo_hash(nNode_DigestValue.getTextContent());
			System.out.println("Codigo Hash_ _ _ _ _ _ _ _ _ _: " + Cabecera.get_codigo_hash());







			// documentos relacionados dinamicos

			Cabecera.set_seg_dni("");
			Cabecera.set_seg_nombre("");






			// SignatureValue
			NodeList nList_SignatureValue = doc.getElementsByTagName("SignatureValue");
			Node nNode_SignatureValue = nList_SignatureValue.item(0);
			Cabecera.set_signature(nNode_SignatureValue.getTextContent());
			//System.out.println("Codigo Hash_ _ _ _ _ _ _ _ _ : " + Cabecera.get_codigo_hash());




			//  documento.getDocumentElement().getChildNodes().item(0).getFirstChild().getChildNodes().item(0).getAttributes().getNamedItem("data").getNodeValue());


			// sac:SUNATTransaction
			// tipo de opecaion






			Cabecera.set_sello(Cabecera.get_ruc_emisor()+"|"+
					Cabecera.get_tipo_doc()+"|"+
					Cabecera.get_serie()+"|"+
					Cabecera.get_folio()+"|"+
					Cabecera.get_total_igv()+"|"+
					Cabecera.get_total()+"|"+
					Cabecera.get_fecha()+"|"+
					""+"|"+		
					""+"|"+
					Cabecera.get_codigo_hash()+
					Cabecera.get_signature()
					);



			for (int _n = 0; _n < 20; _n++) {
				Rel[_n] = new documentos_rel();
			}

			System.out.println(raya);
			System.out.println("Detalle Adicionales_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");

			NodeList nList_AdditionalDocumentReference = doc.getElementsByTagName("cac:AdditionalDocumentReference");
			int _lista_documentos=nList_AdditionalDocumentReference.getLength();

			String _id="";
			String _texto="";

			for (int _x=0;_x<_lista_documentos;_x++) {
				Node nNode_AdditionalDocumentReference = nList_AdditionalDocumentReference.item(_x);
				_texto=nNode_AdditionalDocumentReference.getTextContent();
				_texto=_texto.substring(0,_texto.length()-2);

				//System.out.println(_texto);

				Rel[_x].set_id(_texto.substring(0, 4));
				Rel[_x].set_texto(_texto.substring(5));


				System.out.println("Documento Adicional ID_ _ _ _ _ _ _ _ _ : " + Rel[_x].get_id());
				System.out.println("Documento Adicional Texto _ _ _ _ _ _ _ : " + Rel[_x].get_texto());


			}





			System.out.println(raya);
			System.out.println("Detalle del Documento_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");

			int _total_linea=nList_linea.getLength();
			System.out.println("Numero de Lineas de Detalle _ : "+ _total_linea);

			System.out.println("");


			// lineas para total linea
			NodeList nList_LineExtensionAmount = doc.getElementsByTagName("cbc:LineExtensionAmount");

			//cac:InvoiceLine
			NodeList nList_InvoiceLine = doc.getElementsByTagName("cac:InvoiceLine");

			//cbc:Description
			NodeList nList_Description = doc.getElementsByTagName("cbc:Description");


			// cac:SellersItemIdentification
			NodeList nList_SellersItemIdentification = doc.getElementsByTagName("cac:SellersItemIdentification");



			for (int _linea = 0; _linea < (nList_linea.getLength()); _linea++) {

				int _linea_local=_linea+1;


				Detalle[_linea_local] = new factura_detalle();
				Node nNode_linea = nList_linea.item(_linea);

				Detalle[_linea_local].set_cantidad(Double.parseDouble(nNode_linea.getTextContent()));
				Detalle[_linea_local].set_linea(""+(_linea_local));
				//	System.out.println("Numero de Linea_ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_linea());

				// cbc:InvoicedQuantity

				Node nNode_InvoicedQuantity = nList_InvoicedQuantity.item(_linea);
				String _cadena_InvoicedQuantity=nNode_InvoicedQuantity.getTextContent();
				Detalle[_linea_local].set_cantidad(Double.parseDouble(_cadena_InvoicedQuantity));
				//		System.out.println("Cantidad _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_cantidad());


				if (nNode_InvoicedQuantity.hasAttributes()) {
					NamedNodeMap attributes = nNode_InvoicedQuantity.getAttributes();
					Node nameAttribute = attributes.getNamedItem("unitCode");
					if (nameAttribute != null) {
						//      System.out.println("Name attribute: " + nameAttribute.getTextContent());
						Detalle[_linea_local].set_unidad(nameAttribute.getTextContent());
						//		System.out.println("Unidad de Medida _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_unidad());
					}
				}



				// cac:PricingReference
				NodeList nList_PricingReference = doc.getElementsByTagName("cac:PricingReference");
				Node nNode_PricingReference = nList_PricingReference.item(_linea);
				String _cadena_PricingReference=nNode_PricingReference.getTextContent();

				// cbc:PriceTypeCode
				String _cadena_PriceTypeCode="";

				NodeList nList_PriceTypeCode = doc.getElementsByTagName("cbc:PriceTypeCode");
				try {
					Node nNode_PriceTypeCode = nList_PriceTypeCode.item(_linea);
					_cadena_PriceTypeCode=nNode_PriceTypeCode.getTextContent();
				} catch (Exception e) {
					_cadena_PriceTypeCode="";
				}	

				int _len__cadena_PricingReference=_cadena_PricingReference.length();
				if (_cadena_PriceTypeCode.length()>0) {
					String _cadena_precio=_cadena_PricingReference.substring(0, _len__cadena_PricingReference-2);
					Detalle[_linea_local].set_precio_unitario((Double.parseDouble(_cadena_precio)));
					//		System.out.println("Precio con IGV _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_precio_unitario());
				}



				// cbc:LineExtensionAmount
				Node nNode_LineExtensionAmount = nList_LineExtensionAmount.item(_linea+1);
				String _cadena_LineExtensionAmount=nNode_LineExtensionAmount.getTextContent();
				Detalle[_linea_local].set_precio_unitario_sin_igv((Double.parseDouble(_cadena_LineExtensionAmount)/Detalle[_linea_local].get_cantidad()));
				//		System.out.println("Precio sin IGV _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_precio_unitario_sin_igv());

				// igv	
				Detalle[_linea_local].set_igv((Detalle[_linea_local].get_precio_unitario()-Detalle[_linea_local].get_precio_unitario_sin_igv())*Detalle[_linea_local].get_cantidad());
				//		System.out.println("IGV_ _ _ _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_igv());


				Detalle[_linea_local].set_subtotal_sin_igv(Double.parseDouble(_cadena_LineExtensionAmount));
				//	System.out.println("Sub Total sin IGV_ _ _ _ _ _ _: "+ Detalle[_linea_local].get_subtotal_sin_igv());



				Detalle[_linea_local].set_total_linea(Double.parseDouble(_cadena_LineExtensionAmount));
				//		System.out.println("Total Linea_ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_total_linea());





				Detalle[_linea_local].set_total_linea(Detalle[_linea_local].get_total_linea()+Detalle[_linea_local].get_igv());
				//		System.out.println("Sub Total Linea_ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_total_linea());


				//cbc:Description
				Node nNode_Description = nList_Description.item(_linea);
				String _cadena_Description=nNode_Description.getTextContent();

				if (_cadena_Description.length()>56) {
					_cadena_Description=_cadena_Description.subSequence(0, 56)+"...";


				}
				Detalle[_linea_local].set_descripcion(_cadena_Description);
				//		System.out.println("Descripcion_ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_descripcion());



				// cac:SellersItemIdentification
				Node nNode_SellersItemIdentification = nList_SellersItemIdentification.item(_linea);
				String _cadena_SellersItemIdentification=nNode_SellersItemIdentification.getTextContent();
				Detalle[_linea_local].set_codigo(_cadena_SellersItemIdentification);
				//		System.out.println("Codigo_ _ _ _ _ _ _ _ _ _ _ _ : "+ Detalle[_linea_local].get_codigo());




				System.out.println("Numero de Linea_ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_linea());
				System.out.println("Codigo_ _ _ _ _ _ _ _ _ _ _ _ : "+ Detalle[_linea_local].get_codigo());
				System.out.println("Descripcion_ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_descripcion());
				System.out.println("Unidad de Medida _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_unidad());
				System.out.println("Precio sin IGV _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_precio_unitario_sin_igv());
				System.out.println("Cantidad _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_cantidad());
				System.out.println("Sub Total sin IGV_ _ _ _ _ _ _: "+ Detalle[_linea_local].get_subtotal_sin_igv());
				System.out.println("IGV_ _ _ _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_igv());
				System.out.println("Sub Total Linea_ _ _ _ _ _ _ _: "+ Detalle[_linea_local].get_total_linea());



				System.out.println("");

			}


			System.out.println(raya);

			/*		NodeList nList_TaxableAmount_gravado_linea = doc.getElementsByTagName("cac:TaxSubtotal");
			int item_TaxableAmount_linea= nList_TaxableAmount_gravado_linea.getLength();

			int _linea_detalle=0;
			for (int _z=0;_z<item_TaxableAmount_linea;_z++) {

				_texto_impuesto="";

				try {
					Node nList_TaxableAmount_linea = nList_TaxableAmount_gravado_linea.item(_z);
					_texto_impuesto=nList_TaxableAmount_linea.getTextContent();

					if (_texto_impuesto.contains("S"))  {
						_linea_detalle++;

						if  (_texto_impuesto.contains("IGV")) {
							NodeList nList_TaxableAmount_gravado = doc.getElementsByTagName("cbc:TaxableAmount");
							Node nNode_TaxableAmount_gravado = nList_TaxableAmount_gravado.item(_z);
							double _total_gravado=Double.parseDouble(nNode_TaxableAmount_gravado.getTextContent());
							//	Cabecera.set_total_gravado(_total_gravado);
							//	System.out.println("Linea ...."+_linea_detalle+" Linea General "+_z+" Base Gravable_ _ _ __ _ _ _ _ : "+ _total_gravado);
							System.out.println(_linea_detalle+"  Linea_ _ _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_linea());

							Detalle[_linea_detalle].set_base_gravable(_total_gravado);
							System.out.println("Base Gravable_ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_base_gravable());
							int index_S = _texto_impuesto.indexOf( 'S' );

							String _parte_igv=_texto_impuesto.substring(0, index_S);
							_parte_igv=_parte_igv.replace(""+_total_gravado,"");
			//				Detalle[_linea_detalle].set_igv(Double.parseDouble(_parte_igv));
			//				System.out.println("IGV por Linea_ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_igv());

							System.out.println("");

						}

						if  (_texto_impuesto.contains("EXO")) {
							NodeList nList_TaxableAmount_exo = doc.getElementsByTagName("cbc:TaxableAmount");
							Node nNode_TaxableAmount_exo = nList_TaxableAmount_exo.item(_z);
							double _total_exo_Det=Double.parseDouble(nNode_TaxableAmount_exo.getTextContent());
							System.out.println(_linea_detalle+"  Linea_ _ _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_linea());
							Detalle[_linea_detalle].set_base_gravable(_total_exo_Det);
							_esta_exo=true;
							System.out.println(" = Base Gravable_ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_base_gravable());
							Detalle[_linea_detalle].set_igv(0.00);
							System.out.println("IGV por Linea_ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_igv());
							Detalle[_linea_detalle].set_exonerado(_total_exo_Det);
							System.out.println("Exonerado por Linea_ _ _ _ _ _: "+ Detalle[_linea_detalle].get_exonerado());
							System.out.println("");

						}


						if  (_texto_impuesto.contains("INA")) {
							NodeList nList_TaxableAmount_ina = doc.getElementsByTagName("cbc:TaxableAmount");
							Node nNode_TaxableAmount_ina = nList_TaxableAmount_ina.item(_z);
							double _total_ina_Det=Double.parseDouble(nNode_TaxableAmount_ina.getTextContent());
							System.out.println(_linea_detalle+"  Linea_ _ _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_linea());
							Detalle[_linea_detalle].set_base_gravable(_total_ina_Det);
							_esta_exo=true;
							System.out.println(" = Base Gravable_ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_base_gravable());
							Detalle[_linea_detalle].set_igv(0.00);
							System.out.println("IGV por Linea_ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_igv());
							Detalle[_linea_detalle].set_inafecto(_total_ina_Det);
							System.out.println("Inafecto_por Linea_ _ _ _ _ _ : "+ Detalle[_linea_detalle].get_inafecto());
							System.out.println("");

						}




						if  (_texto_impuesto.contains("ICBPER")) {
							NodeList nList_TaxableAmount_icbper = doc.getElementsByTagName("cbc:TaxableAmount");
							Node nNode_TaxableAmount_icbper = nList_TaxableAmount_icbper.item(_z);
							double _total_icbper_Det=Double.parseDouble(nNode_TaxableAmount_icbper.getTextContent());
							System.out.println(_linea_detalle+"  Linea_ _ _ _ _ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_linea());
							Detalle[_linea_detalle].set_icbper(_total_icbper_Det);
							_esta_exo=true;
							Detalle[_linea_detalle].set_igv(0.00);
							System.out.println("IGV por Linea_ _ _ _ _ _ _ _ _: "+ Detalle[_linea_detalle].get_igv());
							Detalle[_linea_detalle].set_inafecto(_total_icbper_Det);
							System.out.println("ICBPER_ _ _ _ _ _ _ _ _ _ _ _ : "+ Detalle[_linea_detalle].get_icbper());
							System.out.println("");

						}




					}

				} catch (Exception e) {
					_texto_impuesto="";
				}	



				//	System.out.println("***Linea "+_z+" "+_texto_impuesto);


				Detalle[_linea_detalle].set_subtotal(Detalle[_linea_detalle].get_base_gravable()+Detalle[_linea_detalle].get_exonerado()+Detalle[_linea_detalle].get_inafecto()+Detalle[_linea_detalle].get_icbper()+Detalle[_linea_detalle].get_igv()+Detalle[_linea_detalle].get_icbper());
				Detalle[_linea_detalle].set_total_linea(Detalle[_linea_detalle].get_subtotal()-Detalle[_linea_detalle].get_descuento());




			}

			 */







			Cabecera.set_descuentos_detalle(0);

			/*
			int _linea_imp2=0;
			for (int _linea_imp=0;_linea_imp<_total_linea;_linea_imp++) {
				_linea_imp2=_linea_imp+1;





				Cabecera.set_descuentos_detalle(Cabecera.get_descuentos_detalle()+ Detalle[_linea_imp].get_descuento());







				System.out.println("Subtotal"+Detalle[_linea_imp].get_subtotal());


				Cabecera.set_descuentos_cabecera(Cabecera.get_descuentos_cabecera()-Cabecera.get_descuentos_detalle());
				}
			 */
			System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ : ");

			System.out.println("Total Desc. Cabecera_ _  _: " + Cabecera.get_descuentos_cabecera());

			System.out.println("Total Desc. Detalle_ _ _ _: " + Cabecera.get_descuentos_detalle());
			Cabecera.set_total_descuentos(Cabecera.get_descuentos_detalle()+Cabecera.get_descuentos_cabecera() );

			System.out.println("Importe Total_ _  _ _ _ _ _ _ : " + Cabecera.get_total());
			Cabecera.set_total_letra(denomina.main(Cabecera.get_total()));
			System.out.println("Importe con Letra _ _ _ _ _ _ : " + Cabecera.get_total_letra());



			//factura.imp_factura(_file_xml, Cabecera, Detalle);
			Cabecera.set_mensaje_html(readFile(_file_html));

			//_lineas_Descripcion





			// printPDFmc.imp_factura(_file_xml, Cabecera, Detalle, _lineas_de_la_factura,_file_pdf);
			v21printPDF.imp_factura(_file_xml, Cabecera, Detalle, _lines+1,_file_pdf, _file_jpg, Rel);


			if (misParametros.get_tipo_impresora().equals("ticketera")) {

				//		_printTicketera.imp_factura(_file_xml, Cabecera, Detalle, _lineas_Descripcion,_file_pdf_termica, misParametros.get_direccion_impresora(),misParametros.get_ruta_tickets() );


			}




			//	printPDFA4.imp_factura(_file_xml, Cabecera, Detalle, _lineas_Descripcion,_file_pdf_para_imprimir, _file_jpg_para_membrete);
			System.out.println("Reporte PDF Generado:"+_file_pdf);
			if (_correo_destino=="nada") {
				System.out.println("Correo Vacio, no se envio correo...");
			} else {
				System.out.println("Enviando Correo a "+_correo_destino);
				email.main(_correo_destino,
						_file_xml,
						_file_pdf,
						_file_respuesta,
						_file,
						Cabecera, 
						Detalle, 
						_lineas_de_la_factura, 
						_file_zip_respuesta, 
						_user, 
						_password,
						_mail_smtp_auth,
						_mail_smtp_starttls_enable,
						_mail_smtp_host,
						_puerto
						);

				System.out.println("Correo Enviado.");



			}

		} catch (Exception e) {
			e.printStackTrace();
		}





	}


	public static boolean isNullOrEmpty(String a) {
		return a == null || a.isEmpty();
	} 


	public static String readFile(String filename) throws IOException
	{
		String content = null;
		File file = new File(filename); //for ex foo.txt
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader !=null){reader.close();}
		}
		return content;
	}


	public static int llenaPalabras(String _cadena) 
	{
		int _tam = _cadena.length();
		String _car="";
		String _palabra="";
		int _tam_palabra=0;
		int _num_palabras=0;
		int _ult_pos=0;



		for (int x=0; x<=_tam-1; x++ ) {
			_car=_cadena.substring(x,x+1);
			//System.out.println(_car+"  "+x);

			_tam_palabra++;

			if (_car.equals(" ")) {

				_palabra=_cadena.substring(_ult_pos, _ult_pos+_tam_palabra);
				_ult_pos=x+1;
				_tam_palabra=0;
				arregloPalabras[_num_palabras] = new palabras();
				arregloPalabras[_num_palabras].set_palabra(_palabra);
				//System.out.println("la palabra que subi es "+_palabra);
				_num_palabras++;


			}

		}

		_palabra=_cadena.substring(_ult_pos, _ult_pos+_tam_palabra);
		//_ult_pos=x+1;
		//_tam_palabra=0;
		arregloPalabras[_num_palabras] = new palabras();
		arregloPalabras[_num_palabras].set_palabra(_palabra);
		//System.out.println("la palabra que subi es "+_palabra);
		_num_palabras++;

		return _num_palabras;
	}


	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static boolean isNumeric(String strNum) {
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}



}

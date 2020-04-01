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



public class v21readXML_Small {


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
		String _file_jpg = misParametros.get_ruta_formatos()+"CartaCompleta.jpg";
		String _file_jpg_para_membrete = misParametros.get_ruta_formatos_membrete();
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


			System.out.println("tipo de tran:"+_profile);

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
			
			String _tipo_doc_ad="6";
			if (Cabecera.get_tipo_doc().equals("03")) {
				_tipo_doc_ad="1";
			}
			
			
	//		NamedNodeMap attr_tipo_doc_ad = nNode_tipo_doc.getAttributes();
	//		String _tipo_doc_ad="";
	//		if (null != attr_tipo_doc_ad) {
	//			Node p = attr_tipo_doc_ad.getNamedItem("schemeID");
	//			_tipo_doc_ad=p.getNodeValue();
	//		}



			if (_tipo_doc_ad.equals("1")) {
				Cabecera.set_tipo_doc_adquiriente("1");
			} 

			if (_tipo_doc_ad.equals("6")) {
				Cabecera.set_tipo_doc_adquiriente("6");
			} 


			Cabecera.set_tipo_doc_adquiriente(nNode_tipo_doc.getTextContent());
			Cabecera.set_fecha_qr(_fecha);
			Cabecera.set_tipo_documento(_file.substring(12,14));


			// cbc:CustomerAssignedAccountID "RUC DEL EMISOR"
			NodeList nList_CustomerAssignedAccountID = doc.getElementsByTagName("cbc:ID");
			Node nNode_CustomerAssignedAccountID = nList_CustomerAssignedAccountID.item(3);
			Cabecera.set_ruc_emisor(nNode_CustomerAssignedAccountID.getTextContent());
			System.out.println("RUC del Emisor_ _ _ _ _ _ _ _ _: " + Cabecera.get_ruc_emisor());


			// cac:PartyName
			NodeList nList_PartyName = doc.getElementsByTagName("cbc:Name");
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


			// cbc:IdentificationCode
			NodeList nList_IdentificationCode = doc.getElementsByTagName("cbc:IdentificationCode");
			Node nNode_IdentificationCode = nList_IdentificationCode.item(0);
			Cabecera.set_pais_emisor(nNode_IdentificationCode.getTextContent());
			System.out.println("Pais del Emisor_ _ _ _ _ _ _ _ : " + Cabecera.get_pais_emisor());


			System.out.println(raya);

			// cbc:CustomerAssignedAccountID "RUC DEL RECEPTOR"
			NodeList nList_CustomerAssignedAccountID_r = doc.getElementsByTagName("cbc:ID");
			Node nNode_CustomerAssignedAccountID_r = nList_CustomerAssignedAccountID_r.item(4);
			Cabecera.set_ruc_receptor(nNode_CustomerAssignedAccountID_r.getTextContent());
			System.out.println("RUC del Receptor_ _ _ _ _ _ _ _: " + Cabecera.get_ruc_receptor());

			
			
			
			
			
			if (Cabecera.get_tipo_doc().equals("07")) {
				Node nNode_CustomerAssignedAccountID_rnota_cre = nList_CustomerAssignedAccountID_r.item(5);
				Cabecera.set_ruc_receptor(nNode_CustomerAssignedAccountID_rnota_cre.getTextContent());
				System.out.println("RUC del Receptor_ _ _ _ _ _ _ _: " + Cabecera.get_ruc_receptor());
			
			}
			
			if (Cabecera.get_tipo_doc().equals("08")) {
				Node nNode_CustomerAssignedAccountID_rnota_deb = nList_CustomerAssignedAccountID_r.item(5);
				Cabecera.set_ruc_receptor(nNode_CustomerAssignedAccountID_rnota_deb.getTextContent());
				System.out.println("RUC del Receptor_ _ _ _ _ _ _ _: " + Cabecera.get_ruc_receptor());
			
			}
		
			// cac:PartyName
			NodeList nList_PartyName_receptor = doc.getElementsByTagName("cbc:RegistrationName");
			Node nNode_PartyName_receptor = nList_PartyName_receptor.item(1);
			Cabecera.set_razon_social_receptor(nNode_PartyName_receptor.getTextContent());
			System.out.println("Razon Social del Receptor_ _ _ : " + Cabecera.get_razon_social_receptor());


		

			// cbc:StreetName
			NodeList nList_StreetName_r = doc.getElementsByTagName("cbc:Line");
			Node nNode_StreetName_r = nList_StreetName_r.item(1);
			Cabecera.set_direccion_receptor(nNode_StreetName_r.getTextContent());
			System.out.println("Direccion del Receptor_ _ _ _ _ " + Cabecera.get_direccion_receptor());



			// cbc:ID	ubigeo
			//		NodeList nList_ubigeo_r = doc.getElementsByTagName("cbc:ID");
			//		Node nNode_ubigeo_r = nList_ubigeo_r.item(9);
			//		Cabecera.set_ubigeo_receptor(nNode_ubigeo_r.getTextContent());
			//		System.out.println("Ubigeo del Emisor _ _ _ _ _ _ _: " + Cabecera.get_ubigeo_receptor());


			// cbc:IdentificationCode
			//		NodeList nList_IdentificationCode_r = doc.getElementsByTagName("cbc:IdentificationCode");
			//		Node nNode_IdentificationCode_r = nList_IdentificationCode_r.item(1);
			//		Cabecera.set_pais_receptor(nNode_IdentificationCode_r.getTextContent());
			//		System.out.println("Pais del Receptor_ _ _ _ _ _ _ : " + Cabecera.get_pais_receptor());


			// cbc:numero de guia
			//		NodeList nList_guia = doc.getElementsByTagName("cbc:CityName");
			//		Node nNode_guia = nList_guia.item(1);
			//Cabecera.set_pais_receptor(nNode_guia.getTextContent());
			//		Cabecera.set_guia("");
			//System.out.println("Guia_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_pais_receptor());
			//		System.out.println("Guia_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_guia());


			// cbc:numero de guia
			NodeList nList_cond = doc.getElementsByTagName("cbc:CountrySubentity");
			Node nNode_cond = nList_cond.item(1);
			//Cabecera.set_pais_receptor(nNode_guia.getTextContent());
			//		Cabecera.set_condiciones(nNode_cond.getTextContent());
			//System.out.println("Guia_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_pais_receptor());
			//		System.out.println("Condiciones_ _ _ _ _ _ _ _ __ _ _ _ _ : " + Cabecera.get_condiciones());


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








			//cbc:TaxableAmount   MONTO GRABADO
			NodeList nList_TaxableAmount = doc.getElementsByTagName("cbc:TaxableAmount");
			Node nNode_TaxableAmount_gra = nList_TaxableAmount.item(0);
			Cabecera.set_total_gravado(Double.parseDouble(nNode_TaxableAmount_gra.getTextContent()));
			System.out.println("Importe Grabado_ _ _ _ _ _ _ _: " + Cabecera.get_total_gravado());

//			if (Cabecera.get_descripcion_documento().equals("Invoice")) {

				Node nNode_TaxableAmount_exo = nList_TaxableAmount.item(1);
				Cabecera.set_total_exonerado(Double.parseDouble(nNode_TaxableAmount_exo.getTextContent()));
				System.out.println("Importe Exonerado_ _ _ _ _ _ _: " + Cabecera.get_total_exonerado());

				Node nNode_TaxableAmount_ina = nList_TaxableAmount.item(2);
				Cabecera.set_total_inafecto(Double.parseDouble(nNode_TaxableAmount_ina.getTextContent()));
				System.out.println("Importe Inafecto _ _ _ _ _ _ _: " + Cabecera.get_total_inafecto());


				Node nNode_TaxableAmount_gratis = nList_TaxableAmount.item(3);
				//cbc:Name se verifica que exista transaccion gratuita
				NodeList nList_Names = doc.getElementsByTagName("cbc:Name");
				for (int _nodos=0; _nodos<=nList_Names.getLength()-1;_nodos++) {
					Node nNode_Names = nList_Names.item(_nodos);	
					if (nNode_Names.getTextContent().equals("GRA")) {
						Cabecera.set_total_gratuitas(Double.parseDouble(nNode_TaxableAmount_gratis.getTextContent()));
						System.out.println("Importe Gratuito _ _ _ _ _ _ _: " + Cabecera.get_total_gratuitas());


					}
				}

	//		}




			//cbc:TaxAmount

			// csubtota

			Cabecera.set_subtotal(Cabecera.get_total_gravado()+Cabecera.get_total_exonerado()+Cabecera.get_total_inafecto());
			System.out.println("Sub Total _ _ _ _ _ _ _ _ _ _ : " + Cabecera.get_subtotal());



			// cbc:TaxAmount
	//		NodeList nList_TaxAmount = doc.getElementsByTagName("cbc:TaxAmount");
	//		Node nNode_TaxAmount = nList_TaxAmount.item(0);
	//		Cabecera.set_total_igv(Double.parseDouble(nNode_TaxAmount.getTextContent()));
	//		System.out.println("Importe IGV_ _ _ _ _ _ _ _ _ _: " + Cabecera.get_total_igv());


			// cbc:TaxableAmount
			NodeList nList_TaxableAmount_Det = doc.getElementsByTagName("cbc:TaxableAmount");



			// cbc:PayableAmount
			NodeList nList_PayableAmount = doc.getElementsByTagName("cbc:PayableAmount");
			Node nNode_PayableAmount = nList_PayableAmount.item(0);
			Cabecera.set_total(Double.parseDouble(nNode_PayableAmount.getTextContent()));
			System.out.println("TOTAL _ _ _ _ _ _ _ _ _ _ _ _ : " + Cabecera.get_total());




			Cabecera.set_total_igv(Cabecera.get_total()-Cabecera.get_subtotal());
			System.out.println("Importe IGV_ _ _ _ _ _ _ _ _ _: " + Cabecera.get_total_igv());


			Cabecera.set_total_descuentos(0.00);
			//			System.out.println("Importe Descuentos _ _ _ _ _ _: " + Cabecera.get_total_descuentos());


			NodeList nList_desc_cab = doc.getElementsByTagName("cbc:AllowanceTotalAmount");
			Node nNode_desc_cab = nList_desc_cab.item(0);
			Cabecera.set_descuentos_cabecera(Double.parseDouble(nNode_desc_cab.getTextContent()));


			System.out.println("Descuento Cabecera_  _ _ _ _ : " + Cabecera.get_descuentos_cabecera());


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








			// 

			// Reparacion
			//			NodeList nList_reparacion = doc.getElementsByTagName("cbc:ID");
			//			Node nNode_reparacion = nList_reparacion.item(11);
			//			Cabecera.set_reparacion(nNode_reparacion.getTextContent());
			//			System.out.println("O. REPARACION_ _ _ _: " + Cabecera.get_reparacion());




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



			System.out.println("numero de lineas _: " + nList_linea.getLength());	
			int _total_linea=nList_linea.getLength();


			//	if  (Cabecera.get_descripcion_documento().equals("Invoice")) {

			for (int _linea = 0; _linea < nList_linea.getLength(); _linea++) {
				Detalle[_linea] = new factura_detalle();
				Node nNode_linea = nList_linea.item(_linea);
				Detalle[_linea].set_cantidad(Double.parseDouble(nNode_linea.getTextContent()));

			}
			//		}









			// cbc:ID	cbc:LineExtensionAmount
			NodeList nList_LineExtensionAmount = doc.getElementsByTagName("cbc:LineExtensionAmount");

			// cbc:PriceAmount
			NodeList nList_PriceAmount2 = doc.getElementsByTagName("cbc:PriceAmount");

			
			// cbc:PriceAmount  cac:Price
			NodeList nList_PriceAmount = doc.getElementsByTagName("cac:Price");




			// cbc:Description
			NodeList nList_Description = doc.getElementsByTagName("cbc:Description");


			// cac:SellersItemIdentification
			NodeList nList_SellersItemIdentification = doc.getElementsByTagName("cac:SellersItemIdentification");


			//   para sacar la unidad de medida cbc:InvoicedQuantity
			//		NodeList nList_InvoicedQuantity = doc.getElementsByTagName("cbc:InvoicedQuantity");


			// cbc:TaxExemptionReasonCode
			NodeList nList_TaxExemptionReasonCode = doc.getElementsByTagName("cbc:TaxExemptionReasonCode");


			// cbc:Amount
			NodeList nList_Descuentos = doc.getElementsByTagName("cbc:Amount");


			int _lineaArreglo=0;
			int _lineas_email=0;

			for (int _linea = 0; _linea < nList_linea.getLength(); _linea++) {

				//		System.out.println(" "+nList_linea.getLength());


				Node nNode_linea = nList_linea.item(_linea);
				System.out.println(" "+nNode_linea.getTextContent());

				Detalle[_lineaArreglo].set_cantidad(Double.parseDouble(nNode_linea.getTextContent()));

			//	nList_PriceAmount
				
			//	System.out.println(_linea+" *****"+Detalle[_lineaArreglo].get_subtotal()+"   "+Detalle[_lineaArreglo].get_cantidad());

				
				if (_linea==0) {
					Node nNode_PriceAmount2 = nList_PriceAmount2.item(_linea);
					Detalle[_lineaArreglo].set_subtotal((Double.parseDouble(nNode_PriceAmount2.getTextContent())));
					
				} else {
					Node nNode_PriceAmount2 = nList_PriceAmount2.item(_linea*2);
					Detalle[_lineaArreglo].set_subtotal((Double.parseDouble(nNode_PriceAmount2.getTextContent())));
					
				}

				Detalle[_lineaArreglo].set_subtotal(Detalle[_lineaArreglo].get_subtotal()*Detalle[_lineaArreglo].get_cantidad());
				
				
				System.out.println(_linea+" *****"+Detalle[_lineaArreglo].get_subtotal()+"   "+Detalle[_lineaArreglo].get_cantidad());


				Node nNode_PriceAmount = nList_PriceAmount.item(_linea);
				Detalle[_lineaArreglo].set_precio_unitario((Double.parseDouble(nNode_PriceAmount.getTextContent())));
				double _subtotal_sin_igv=Detalle[_lineaArreglo].get_precio_unitario()*Detalle[_lineaArreglo].get_cantidad();
				_subtotal_sin_igv=round(_subtotal_sin_igv,2);

				Detalle[_lineaArreglo].set_subtotal_sin_igv(_subtotal_sin_igv);

				if (Cabecera.get_descripcion_documento().equals("Invoice")) {


					Node Node_TaxableAmount_Det = nList_TaxableAmount_Det.item(_linea+3);
					double _igv_Det= Double.parseDouble(Node_TaxableAmount_Det.getTextContent());
					Detalle[_lineaArreglo].set_igv(_igv_Det*.18);


				} else {


					Node Node_TaxableAmount_Det = nList_TaxableAmount_Det.item(_linea+2);
					double _igv_Det= Double.parseDouble(Node_TaxableAmount_Det.getTextContent());
					Detalle[_lineaArreglo].set_igv(_igv_Det*.18);


				}









				Node nNode_SellersItemIdentification = nList_SellersItemIdentification.item(_linea);
				Node nNode_codigo = nNode_SellersItemIdentification.getFirstChild();
				Detalle[_lineaArreglo].set_codigo(nNode_codigo.getTextContent());


				Node nNode_TaxExemptionReasonCode = nList_TaxExemptionReasonCode.item(_linea);
				Node nNode_Tipo_igv = nNode_TaxExemptionReasonCode.getFirstChild();
				Detalle[_lineaArreglo].set_tipo_igv(nNode_Tipo_igv.getTextContent());





				try {
					// descuentos	
					Node nNode_Descuentos = nList_Descuentos.item(_linea);
					Detalle[_lineaArreglo].set_descuento(Double.parseDouble(nNode_Descuentos.getTextContent()));

				} catch (Exception e) {
					Detalle[_lineaArreglo].set_descuento(0);
				}






				Node nNode_InvoicedQuantity = nList_InvoicedQuantity.item(_linea);
				if (nNode_InvoicedQuantity.hasAttributes()) {
					NamedNodeMap attributes = nNode_InvoicedQuantity.getAttributes();
					Node nameAttribute = attributes.getNamedItem("unitCode");
					if (nameAttribute != null) {
						//      System.out.println("Name attribute: " + nameAttribute.getTextContent());
						Detalle[_lineaArreglo].set_unidad(nameAttribute.getTextContent());
					}
				}

				String _text = "";
				if (Cabecera.get_descripcion_documento().equals("Invoice")) {


					Node nNode_Description = nList_Description.item(_linea);
					_text = nNode_Description.getTextContent();

				} else {
					Node nNode_Description = nList_Description.item(_linea+1);
					_text = nNode_Description.getTextContent();

				}




				if (Detalle[_lineaArreglo].get_tipo_igv().equals("12") ||
						Detalle[_lineaArreglo].get_tipo_igv().equals("13") ||
						Detalle[_lineaArreglo].get_tipo_igv().equals("14") ||
						Detalle[_lineaArreglo].get_tipo_igv().equals("15") ||
						Detalle[_lineaArreglo].get_tipo_igv().equals("16") ||
						Detalle[_lineaArreglo].get_tipo_igv().equals("21") ||
						Detalle[_lineaArreglo].get_tipo_igv().equals("37") 
						) {
					_text=_text+" ** gratuito **";

				}


				Detalle_email[_lineas_email].set_codigo(Detalle[_lineaArreglo].get_codigo());
				Detalle_email[_lineas_email].set_precio_unitario(Detalle[_lineaArreglo].get_precio_unitario());
				if (Detalle[_lineaArreglo].get_cantidad()>0) {
					Detalle_email[_lineas_email].set_cantidad(Detalle[_lineaArreglo].get_cantidad());
				}

				if (Detalle[_lineaArreglo].get_subtotal()>0) {
					Detalle_email[_lineas_email].set_subtotal(Detalle[_lineaArreglo].get_subtotal());
				}

				if (Detalle[_lineaArreglo].get_igv()>0) {
					Detalle_email[_lineas_email].set_igv(Detalle[_lineaArreglo].get_igv());
				}




				Detalle_email[_lineas_email].set_descripcion(_text);
				_lineas_email++;
				//System.out.println("para email"+


				if (_text.length()<54) { 
					Detalle[_lineaArreglo].set_descripcion(_text);
					_lineaArreglo++;
					_lineas_Descripcion=_linea+_lineaArreglo;

				} else {
					System.out.println("Texto largo:"+_text);

					_lineaArreglo=_lineaArreglo+_linea;
					int y=llenaPalabras(_text);
					String _reglon="";
					int _tam_palabra=0;
					int _tam_linea=0;



					for (int _palabras=0; _palabras<=y-1; _palabras++) {
						_tam_palabra=arregloPalabras[_palabras].get_palabra().length();
						if ((_tam_linea+_tam_palabra)<54) {
							if (_reglon.equals("") && arregloPalabras[_palabras].get_palabra().equals(" ")) {

							} else {
								_reglon=_reglon+arregloPalabras[_palabras].get_palabra();
								_tam_linea=_tam_linea+_tam_palabra;
							}
						} else {
							_reglon=_reglon+arregloPalabras[_palabras].get_palabra();
							Detalle[_lineaArreglo-_linea].set_descripcion(_reglon);
							System.out.println(_reglon);
							if (Detalle[_lineaArreglo-_linea].get_cantidad()==0) {
								Detalle[_lineaArreglo-_linea].set_codigo(".");
							}


							_reglon="";
							_tam_linea=0;

							_lineaArreglo++;


						}
					}
					//System.out.println(_reglon);
					Detalle[_lineaArreglo-_linea].set_descripcion(_reglon);

					//		System.out.println(_lineaArreglo-_linea);

					//		System.out.println(Detalle[_lineaArreglo-_linea].get_descripcion());

					if (Detalle[_lineaArreglo-_linea].get_cantidad()==0) {
						Detalle[_lineaArreglo-_linea].set_codigo(".");
					}
					_lineaArreglo++;
					try {
						Detalle[_lineaArreglo-_linea].set_descripcion("");
						_lineaArreglo++;
						_lineas_Descripcion=_linea+_lineaArreglo;
						//_lineaArreglo++;

					} catch (Exception e) {
						_lineaArreglo--;
						//	e.printStackTrace();
					}
					_lineas_Descripcion=_linea+_lineaArreglo;
					//_lineaArreglo++;

				}






			}















			Cabecera.set_descuentos_detalle(0);


			int _linea_imp2=0;
			for (int _linea_imp=0;_linea_imp<_total_linea;_linea_imp++) {
				_linea_imp2=_linea_imp+1;
				System.out.println("");
				System.out.println("Linea_ _ _ _ _ _ _ _ _ _ _: " + _linea_imp2);
				System.out.println("Codigo_ _ _ _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_codigo());
				System.out.println("Unidad de Medida_ _ _ _ _ : " + Detalle[_linea_imp].get_unidad());
				System.out.println("Descripcion _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_descripcion());
				System.out.println("Cantidad_ _ _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_cantidad());
				System.out.println("Precio Unitario _ _ _ _ _ : " + Detalle[_linea_imp].get_precio_unitario());
				System.out.println("Descuento_ _ _  _ _ _ _ _ : " + Detalle[_linea_imp].get_descuento());
				System.out.println("IGV _ _ _ _ _ _ _ _ _ _ _ : " + Detalle[_linea_imp].get_igv());

				
				
			//	Detalle[_linea_imp].set_subtotal(Detalle[_linea_imp].get_subtotal()*1.18);
				
			//	_subtotal_sin_igv=round(_subtotal_sin_igv,2);
				System.out.println("Monto con IGV _ _ _ _ _ _ : " + Detalle[_linea_imp].get_subtotal());
				System.out.println("Monto sin IGV _ _ _ _ _ _ : " + Detalle[_linea_imp].get_subtotal_sin_igv());
				_lineas_de_la_factura=_linea_imp2;

				
				_lineas_de_la_factura=_linea_imp2;


				Cabecera.set_descuentos_detalle(Cabecera.get_descuentos_detalle()+ Detalle[_linea_imp].get_descuento());







				System.out.println("Subtotal"+Detalle[_linea_imp].get_subtotal());
				//		Cabecera.set_total_igv(Cabecera.get_total_igv()+Detalle[_linea_imp].get_igv());
				//		Cabecera.set_subtotal(Cabecera.get_subtotal()+Detalle[_linea_imp].get_subtotal_sin_igv());

				//		Cabecera.set_total(Cabecera.get_subtotal()+Detalle[_linea_imp].get_igv());





				Cabecera.set_descuentos_cabecera(Cabecera.get_descuentos_cabecera()-Cabecera.get_descuentos_detalle());}

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
			v21printPDF.imp_factura(_file_xml, Cabecera, Detalle, _lineas_Descripcion,_file_pdf, _file_jpg, Rel);
		//	v21printPDFmc.imp_factura(_file_xml, Cabecera, Detalle, _lineas_Descripcion,_file_pdf);

			if (misParametros.get_tipo_impresora().equals("ticketera")) {
				_printTicketeraMiniPos.imp_factura(_file_xml, Cabecera, Detalle, _lineas_Descripcion,_file_pdf_termica, misParametros.get_direccion_impresora(),misParametros.get_ruta_tickets() );			
		//		_printTicketera.imp_factura(_file_xml, Cabecera, Detalle, _lineas_Descripcion,_file_pdf_termica, misParametros.get_direccion_impresora(),misParametros.get_ruta_tickets() );
				
				//		_printTicketeraQR.imp_factura(_file_xml, Cabecera, Detalle, _lineas_Descripcion,_file_pdf_termica, misParametros.get_direccion_impresora(),misParametros.get_ruta_tickets() );			
				
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


}

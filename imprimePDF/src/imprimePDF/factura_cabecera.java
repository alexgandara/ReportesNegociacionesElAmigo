package imprimePDF;

public class factura_cabecera {
	public String _serie;
	public String _folio;
	public String _fecha;
	public String _tipo_doc;
	public String _descripcion_documento;
	public String _moneda;
	public String _porciento_igv;
	public String _ruc_emisor;
	public String _razon_social_emisor;
	public String _direccion_emisor;
	public String _ubigeo_emisor;
	public String _pais_emisor;
	public String _ruc_receptor;
	public String _razon_social_receptor;
	public String _direccion_receptor;
	public String _ubigeo_receptor;
	public String _pais_receptor;
	public double _subtotal;
	public double _total;
	public double _total_gravado;
	public double _total_exonerado;
	public double _total_inafecto;
	public double _total_igv;
	public double _total_isc;
	public double _total_icbper;
	public String _total_letra;
	public String _observaciones;
	public String _codigo_hash;
	public String _sello;
	public String _signature;
	public String _note;
	public String _tipo_doc_descripcion;
	public String _tipo_doc_relacionado;
	public String _doc_relacionado;
	public String _motivo_de_anulacion;
	public String _Ruc_Dni;
	public String _ad01;
	public String _ad02;
	public String _ad03;
	public String _ad04;
	public String _leyenda;
	public String _guia;
	public String _fecha_pago;
	public String _mensaje_html;
	public String _tipo_operacion;
	public double _total_anticipos;
	public String _fecha_vencimiento;
	public String _seg_dni;
	public String _seg_nombre;
	public String _direccion01;
	public String _direccion02;
	public String _direccion03;
	public String _tipo_documento;
	public String _fecha_qr;
	public String _tipo_doc_adquiriente;
	public String _linea01;
	public String _linea02;
	public String _linea03;
	public String _linea04;
	public String _linea05;
	public String _linea06;
	public String _linea07;
	public String _linea08;
	public double _total_gratuitas;
	public double _total_descuentos;
	public double _descuentos_cabecera;
	public double _descuentos_detalle;
	public String _profile;
	
	public String _remision01;
	public String _remision02;
	public String _remision03;
	public String _transportista01;
	
	
	
	
	

	
	public String get_remision01() {
		return _remision01;
	}
	public void set_remision01(String _remision01) {
		this._remision01 = _remision01;
	}
	public String get_remision02() {
		return _remision02;
	}
	public void set_remision02(String _remision02) {
		this._remision02 = _remision02;
	}
	public String get_remision03() {
		return _remision03;
	}
	public void set_remision03(String _remision03) {
		this._remision03 = _remision03;
	}
	public String get_transportista01() {
		return _transportista01;
	}
	public void set_transportista01(String _transportista01) {
		this._transportista01 = _transportista01;
	}
	public double get_total_icbper() {
		return _total_icbper;
	}
	public void set_total_icbper(double _total_icbper) {
		this._total_icbper = _total_icbper;
	}
	public String get_profile() {
		return _profile;
	}
	public void set_profile(String _profile) {
		this._profile = _profile;
	}
	public double get_descuentos_cabecera() {
		return _descuentos_cabecera;
	}
	public void set_descuentos_cabecera(double _descuentos_cabecera) {
		this._descuentos_cabecera = _descuentos_cabecera;
	}
	public double get_descuentos_detalle() {
		return _descuentos_detalle;
	}
	public void set_descuentos_detalle(double _descuentos_detalle) {
		this._descuentos_detalle = _descuentos_detalle;
	}
	public double get_total_gratuitas() {
		return _total_gratuitas;
	}
	public void set_total_gratuitas(double _total_gratuitas) {
		this._total_gratuitas = _total_gratuitas;
	}
	public double get_total_descuentos() {
		return _total_descuentos;
	}
	public void set_total_descuentos(double _total_descuentos) {
		this._total_descuentos = _total_descuentos;
	}
	public String get_linea08() {
		return _linea08;
	}
	public void set_linea08(String _linea08) {
		this._linea08 = _linea08;
	}
	public String get_linea06() {
		return _linea06;
	}
	public void set_linea06(String _linea06) {
		this._linea06 = _linea06;
	}
	public String get_linea07() {
		return _linea07;
	}
	public void set_linea07(String _linea07) {
		this._linea07 = _linea07;
	}
	public String get_linea01() {
		return _linea01;
	}
	public void set_linea01(String _linea01) {
		this._linea01 = _linea01;
	}
	public String get_linea02() {
		return _linea02;
	}
	public void set_linea02(String _linea02) {
		this._linea02 = _linea02;
	}
	public String get_linea03() {
		return _linea03;
	}
	public void set_linea03(String _linea03) {
		this._linea03 = _linea03;
	}
	public String get_linea04() {
		return _linea04;
	}
	public void set_linea04(String _linea04) {
		this._linea04 = _linea04;
	}
	public String get_linea05() {
		return _linea05;
	}
	public void set_linea05(String _linea05) {
		this._linea05 = _linea05;
	}
	public String get_tipo_documento() {
		return _tipo_documento;
	}
	public void set_tipo_documento(String _tipo_documento) {
		this._tipo_documento = _tipo_documento;
	}
	public String get_fecha_qr() {
		return _fecha_qr;
	}
	public void set_fecha_qr(String _fecha_qr) {
		this._fecha_qr = _fecha_qr;
	}
	public String get_tipo_doc_adquiriente() {
		return _tipo_doc_adquiriente;
	}
	public void set_tipo_doc_adquiriente(String _tipo_doc_adquiriente) {
		this._tipo_doc_adquiriente = _tipo_doc_adquiriente;
	}
	public String get_tipo_operacion() {
		return _tipo_operacion;
	}
	public void set_tipo_operacion(String _tipo_operacion) {
		this._tipo_operacion = _tipo_operacion;
	}
	public double get_total_anticipos() {
		return _total_anticipos;
	}
	public void set_total_anticipos(double _total_anticipos) {
		this._total_anticipos = _total_anticipos;
	}
	public String get_fecha_vencimiento() {
		return _fecha_vencimiento;
	}
	public void set_fecha_vencimiento(String _fecha_vencimiento) {
		this._fecha_vencimiento = _fecha_vencimiento;
	}
	public String get_seg_dni() {
		return _seg_dni;
	}
	public void set_seg_dni(String _seg_dni) {
		this._seg_dni = _seg_dni;
	}
	public String get_seg_nombre() {
		return _seg_nombre;
	}
	public void set_seg_nombre(String _seg_nombre) {
		this._seg_nombre = _seg_nombre;
	}
	public String get_direccion01() {
		return _direccion01;
	}
	public void set_direccion01(String _direccion01) {
		this._direccion01 = _direccion01;
	}
	public String get_direccion02() {
		return _direccion02;
	}
	public void set_direccion02(String _direccion02) {
		this._direccion02 = _direccion02;
	}
	public String get_direccion03() {
		return _direccion03;
	}
	public void set_direccion03(String _direccion03) {
		this._direccion03 = _direccion03;
	}
	public String get_mensaje_html() {
		return _mensaje_html;
	}
	public void set_mensaje_html(String _mensaje_html) {
		this._mensaje_html = _mensaje_html;
	}
	public String get_fecha_pago() {
		return _fecha_pago;
	}
	public void set_fecha_pago(String _fecha_pago) {
		this._fecha_pago = _fecha_pago;
	}
	public String get_guia() {
		return _guia;
	}
	public void set_guia(String _guia) {
		this._guia = _guia;
	}
	public String get_leyenda() {
		return _leyenda;
	}
	public void set_leyenda(String _leyenda) {
		this._leyenda = _leyenda;
	}
	public String get_ad01() {
		return _ad01;
	}
	public void set_ad01(String _ad01) {
		this._ad01 = _ad01;
	}
	public String get_ad02() {
		return _ad02;
	}
	public void set_ad02(String _ad02) {
		this._ad02 = _ad02;
	}
	public String get_ad03() {
		return _ad03;
	}
	public void set_ad03(String _ad03) {
		this._ad03 = _ad03;
	}
	public String get_ad04() {
		return _ad04;
	}
	public void set_ad04(String _ad04) {
		this._ad04 = _ad04;
	}
	public String get_Ruc_Dni() {
		return _Ruc_Dni;
	}
	public void set_Ruc_Dni(String _Ruc_Dni) {
		this._Ruc_Dni = _Ruc_Dni;
	}
	public String get_motivo_de_anulacion() {
		return _motivo_de_anulacion;
	}
	public void set_motivo_de_anulacion(String _motivo_de_anulacion) {
		this._motivo_de_anulacion = _motivo_de_anulacion;
	}
	public String get_tipo_doc_relacionado() {
		return _tipo_doc_relacionado;
	}
	public void set_tipo_doc_relacionado(String _tipo_doc_relacionado) {
		this._tipo_doc_relacionado = _tipo_doc_relacionado;
	}
	public String get_doc_relacionado() {
		return _doc_relacionado;
	}
	public void set_doc_relacionado(String _doc_relacionado) {
		this._doc_relacionado = _doc_relacionado;
	}
	public String get_tipo_doc_descripcion() {
		return _tipo_doc_descripcion;
	}
	public void set_tipo_doc_descripcion(String _tipo_doc_descripcion) {
		this._tipo_doc_descripcion = _tipo_doc_descripcion;
	}
	public String get_note() {
		return _note;
	}
	public void set_note(String _note) {
		this._note = _note;
	}
	public String get_sello() {
		return _sello;
	}
	public void set_sello(String _sello) {
		this._sello = _sello;
	}
	public String get_signature() {
		return _signature;
	}
	public void set_signature(String _signature) {
		this._signature = _signature;
	}
	public String get_descripcion_documento() {
		return _descripcion_documento;
	}
	public void set_descripcion_documento(String _descripcion_documento) {
		this._descripcion_documento = _descripcion_documento;
	}
	public String get_porciento_igv() {
		return _porciento_igv;
	}
	public void set_porciento_igv(String _porciento_igv) {
		this._porciento_igv = _porciento_igv;
	}
	public double get_total_igv() {
		return _total_igv;
	}
	public void set_total_igv(double _total_igv) {
		this._total_igv = _total_igv;
	}
	public double get_total_isc() {
		return _total_isc;
	}
	public void set_total_isc(double _total_isc) {
		this._total_isc = _total_isc;
	}
	
	
	
	public String get_serie() {
		return _serie;
	}
	public void set_serie(String _serie) {
		this._serie = _serie;
	}
	public String get_folio() {
		return _folio;
	}
	public void set_folio(String _folio) {
		this._folio = _folio;
	}
	public String get_fecha() {
		return _fecha;
	}
	public void set_fecha(String _fecha) {
		this._fecha = _fecha;
	}
	public String get_tipo_doc() {
		return _tipo_doc;
	}
	public void set_tipo_doc(String _tipo_doc) {
		this._tipo_doc = _tipo_doc;
	}
	public String get_moneda() {
		return _moneda;
	}
	public void set_moneda(String _moneda) {
		this._moneda = _moneda;
	}

	public String get_ruc_emisor() {
		return _ruc_emisor;
	}
	public void set_ruc_emisor(String _ruc_emisor) {
		this._ruc_emisor = _ruc_emisor;
	}
	public String get_razon_social_emisor() {
		return _razon_social_emisor;
	}
	public void set_razon_social_emisor(String _razon_social_emisor) {
		this._razon_social_emisor = _razon_social_emisor;
	}
	public String get_direccion_emisor() {
		return _direccion_emisor;
	}
	public void set_direccion_emisor(String _direccion_emisor) {
		this._direccion_emisor = _direccion_emisor;
	}
	public String get_ubigeo_emisor() {
		return _ubigeo_emisor;
	}
	public void set_ubigeo_emisor(String _ubigeo_emisor) {
		this._ubigeo_emisor = _ubigeo_emisor;
	}
	public String get_pais_emisor() {
		return _pais_emisor;
	}
	public void set_pais_emisor(String _pais_emisor) {
		this._pais_emisor = _pais_emisor;
	}
	public String get_ruc_receptor() {
		return _ruc_receptor;
	}
	public void set_ruc_receptor(String _ruc_receptor) {
		this._ruc_receptor = _ruc_receptor;
	}
	public String get_razon_social_receptor() {
		return _razon_social_receptor;
	}
	public void set_razon_social_receptor(String _razon_social_receptor) {
		this._razon_social_receptor = _razon_social_receptor;
	}
	public String get_direccion_receptor() {
		return _direccion_receptor;
	}
	public void set_direccion_receptor(String _direccion_receptor) {
		this._direccion_receptor = _direccion_receptor;
	}
	public String get_ubigeo_receptor() {
		return _ubigeo_receptor;
	}
	public void set_ubigeo_receptor(String _ubigeo_receptor) {
		this._ubigeo_receptor = _ubigeo_receptor;
	}
	public String get_pais_receptor() {
		return _pais_receptor;
	}
	public void set_pais_receptor(String _pais_receptor) {
		this._pais_receptor = _pais_receptor;
	}
	public double get_subtotal() {
		return _subtotal;
	}
	public void set_subtotal(double _subtotal) {
		this._subtotal = _subtotal;
	}
	public double get_total() {
		return _total;
	}
	public void set_total(double _total) {
		this._total = _total;
	}
	public double get_total_gravado() {
		return _total_gravado;
	}
	public void set_total_gravado(double _total_gravado) {
		this._total_gravado = _total_gravado;
	}
	public double get_total_exonerado() {
		return _total_exonerado;
	}
	public void set_total_exonerado(double _total_exonerado) {
		this._total_exonerado = _total_exonerado;
	}
	public double get_total_inafecto() {
		return _total_inafecto;
	}
	public void set_total_inafecto(double _total_inafecto) {
		this._total_inafecto = _total_inafecto;
	}
	
	public String get_total_letra() {
		return _total_letra;
	}
	public void set_total_letra(String _total_letra) {
		this._total_letra = _total_letra;
	}
	public String get_observaciones() {
		return _observaciones;
	}
	public void set_observaciones(String _observaciones) {
		this._observaciones = _observaciones;
	}
	public String get_codigo_hash() {
		return _codigo_hash;
	}
	public void set_codigo_hash(String _codigo_hash) {
		this._codigo_hash = _codigo_hash;
	}
	
	
	

}

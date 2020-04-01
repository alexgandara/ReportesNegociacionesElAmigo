package imprimePDF;

public class factura_detalle {
	public String _linea;
	public String _codigo;
	public String _unidad;
	public double _cantidad;
	public String _descripcion;
	public double _descuento;
	public double _precio_unitario;
	public double _precio_unitario_sin_igv;
	public double _subtotal;
	public double _subtotal_sin_igv;
	public double _igv;
	public int _num_linea;
	public double _total_linea;
	public String _tipo_igv;
	public double _base_gravable;
	public double _exonerado;
	public double _inafecto;
	public double _icbper;
	
	
	
	
	
	
	
	
	
	
	
	
	public double get_precio_unitario_sin_igv() {
		return _precio_unitario_sin_igv;
	}
	public void set_precio_unitario_sin_igv(double _precio_unitario_sin_igv) {
		this._precio_unitario_sin_igv = _precio_unitario_sin_igv;
	}
	public double get_icbper() {
		return _icbper;
	}
	public void set_icbper(double _icbper) {
		this._icbper = _icbper;
	}
	public double get_exonerado() {
		return _exonerado;
	}
	public void set_exonerado(double _exonerado) {
		this._exonerado = _exonerado;
	}
	public double get_inafecto() {
		return _inafecto;
	}
	public void set_inafecto(double _inafecto) {
		this._inafecto = _inafecto;
	}
	public double get_base_gravable() {
		return _base_gravable;
	}
	public void set_base_gravable(double _base_gravable) {
		this._base_gravable = _base_gravable;
	}
	public String get_tipo_igv() {
		return _tipo_igv;
	}
	public void set_tipo_igv(String _tipo_igv) {
		this._tipo_igv = _tipo_igv;
	}
	public double get_total_linea() {
		return _total_linea;
	}
	public void set_total_linea(double _total_linea) {
		this._total_linea = _total_linea;
	}
	public int get_num_linea() {
		return _num_linea;
	}
	public void set_num_linea(int _num_linea) {
		this._num_linea = _num_linea;
	}
	public double get_subtotal_sin_igv() {
		return _subtotal_sin_igv;
	}
	public void set_subtotal_sin_igv(double _subtotal_sin_igv) {
		this._subtotal_sin_igv = _subtotal_sin_igv;
	}
	public double get_igv() {
		return _igv;
	}
	public void set_igv(double _igv) {
		this._igv = _igv;
	}
	public String get_linea() {
		return _linea;
	}
	public void set_linea(String _linea) {
		this._linea = _linea;
	}
	public String get_codigo() {
		return _codigo;
	}
	public void set_codigo(String _codigo) {
		this._codigo = _codigo;
	}
	public String get_unidad() {
		return _unidad;
	}
	public void set_unidad(String _unidad) {
		this._unidad = _unidad;
	}
	public double get_cantidad() {
		return _cantidad;
	}
	public void set_cantidad(double _cantidad) {
		this._cantidad = _cantidad;
	}
	public String get_descripcion() {
		return _descripcion;
	}
	public void set_descripcion(String _descripcion) {
		this._descripcion = _descripcion;
	}
	public double get_descuento() {
		return _descuento;
	}
	public void set_descuento(double _descuento) {
		this._descuento = _descuento;
	}
	public double get_precio_unitario() {
		return _precio_unitario;
	}
	public void set_precio_unitario(double _precio_unitario) {
		this._precio_unitario = _precio_unitario;
	}
	public double get_subtotal() {
		return _subtotal;
	}
	public void set_subtotal(double _subtotal) {
		this._subtotal = _subtotal;
	}
	
	

}

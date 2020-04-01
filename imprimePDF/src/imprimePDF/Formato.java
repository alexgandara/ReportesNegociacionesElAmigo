package imprimePDF;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Formato {
    public  static DecimalFormat df;
    public  static DecimalFormatSymbols simb;
 
    public static String dinero(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("###,##0.00", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    public static String dinero_corto(double _numero) {
        String _regreso;
        
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("###0.00", simb);
        _regreso=padLeft(df.format(_numero),7);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
   
    
    
    public static String dinero0(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##0", simb);
        _regreso=padLeft(df.format(_numero),3);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    public static String cadena27(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"27"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
 
 
    public static String cadena80(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"80"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
 
    
    public static String cadena60(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"60"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    
    public static String dinero3(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##0.00", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    
    public static String cadena21(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"21"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
 
    
    public static String dinero2(int _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("#0", simb);
        _regreso=padLeft(df.format(_numero),2);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }	
    
    
    public static String cant(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##,###,##0", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    
    public static String dinero6(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##0.000000", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    public static String dinero4(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##0.0000", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
   
   
   
    public static String dinero5(double _numero) {
        String _regreso;
    	simb = new DecimalFormatSymbols();
        simb.setDecimalSeparator('.');
        simb.setGroupingSeparator(',');
        df = new DecimalFormat("##0.00000", simb);
        _regreso=padLeft(df.format(_numero),10);
        return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
  
    
    
    
    public static String cadena20(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"20"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    public static String cadena59(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"59"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
 
 
    public static String cadena10(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"10"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    
    public static String cadena5(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"5"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    
    
    public static String cadena40(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"40"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
    public static String cadena50(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"50"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
 
    
    public static String cadena63(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"65"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
 
    
    
    public static String cadena35(String _cadena) {
        String _regreso;
        String formato = "%1$-"+"35"+"s";
        _regreso=String.format(formato,_cadena);
    	return _regreso;
        
        // El resultado sería el siguiente: 94.751.890,37
    }
    
   public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);  
   }

   public static String padLeft(String s, int n) {
       return String.format("%1$" + n + "s", s);  
   }
   
 
   
}
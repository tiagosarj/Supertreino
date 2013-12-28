package br.ufba.matc89.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.text.TextWatcher;
import android.widget.EditText;
import br.ufba.matc89.R;

public class DateUtil extends Activity{

	public static java.sql.Date convertDateUtil2DateSql(java.util.Date date){
		java.sql.Date dataSql = new java.sql.Date(date.getTime());
		return dataSql;
	}
	
	public static String getDataSimples(Date data){
		String dataSimples = "00-00-0000";
		
		Locale locale = new Locale("pt", "BR");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", locale);
		
		dataSimples = sdf.format(data);			
			
		return dataSimples;
	}
	
	
	public static void validarDataEmPreenchimento(EditText data, String mascara){
		final TextWatcher nascimentoMask;
		
		nascimentoMask = Mascara.aplicarMascara(mascara, data);
		data.addTextChangedListener(nascimentoMask);
	}
	
	/*public static java.sql.Date formatarDdMMyyy(java.sql.Date date){
		
	}*/
		
//	public static void main(String[] args){
//		
//		System.out.println(getDataSimples(new Date()));
//	}
}

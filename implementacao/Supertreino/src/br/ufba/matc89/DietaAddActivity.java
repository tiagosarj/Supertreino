package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class DietaAddActivity extends Activity {
	private static final String[] alimentos = new String[]{"Pão Integral", "Presunto PeitoPeru", "Ovos", "Frango Grelhado"};           
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dieta_add);

		ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, alimentos);
		AutoCompleteTextView clubes = (AutoCompleteTextView) findViewById(R.id.alimentos);
		clubes.setAdapter(adp);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dieta_add, menu);
		return true;
	}

}

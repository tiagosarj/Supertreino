package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


public class RelatorioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relatorio);
		
		findViewById(R.id.btHome).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {						
						openTelaPrincipal();						
					}
				});
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.relatorio, menu);
		return true;
	}
	
	public void openTelaPrincipal(){
		Intent it = new Intent(this, HomeActivity.class);
		startActivity(it);
	}
	
	public void calcularIMC(){
		
	}

}

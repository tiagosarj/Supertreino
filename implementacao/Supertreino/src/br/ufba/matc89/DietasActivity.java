package br.ufba.matc89;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DietasActivity extends Activity {
	
	ListView lw;
	String[] dietas = {"Hiper-Proteíca","Calorica", "Pós-Treino"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dietas);
		
		
		ArrayAdapter<String> str = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				android.R.id.text1,dietas);
		
		lw = (ListView)findViewById(R.id.listView1);
		lw.setAdapter(str);	
		
		lw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				
				switch(position){
				
				case 0:
					break;
				case 1:
					break;
				case 3:
					break;					
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dietas, menu);
		return true;
	}

}

package br.ufba.matc89;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import br.ufba.matc89.model.Alimento;

public class AlimentoActivity extends Activity implements OnItemClickListener {

	ListView listViewAlimento;
	Button addAlimentoButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alimento);

        List<Alimento> listaAlimento = criarAlimentos();
        ArrayAdapter<Alimento> ad = new AlimentoAdapter(this, R.layout.layout_alimento, listaAlimento);
        listViewAlimento = (ListView) findViewById(R.id.listViewAlimento);
        listViewAlimento.setAdapter(ad);

        listViewAlimento.setOnItemClickListener(this);
        registerForContextMenu(listViewAlimento);
        
        addAlimentoButton = (Button) findViewById(R.id.adicionar);
        addAlimentoButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent changeActivity = new Intent( AlimentoActivity.this, AddEditAlimentoActivity.class);
				startActivityForResult(changeActivity, 1);
			}
		});
        
    }
  
    private List<Alimento> criarAlimentos(){
        List<Alimento> p = new ArrayList<Alimento>();
        p.add(new Alimento("Carne de vaca","Protéica"));
        p.add(new Alimento("Arroz","Glicêmica"));
        p.add(new Alimento("Feijão","Protéica"));
        return p; 
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alimento, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.alimento, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.editar:
				Intent changeActivity = new Intent( AlimentoActivity.this, AddEditAlimentoActivity.class);
				startActivityForResult(changeActivity, 1);
				return true;
			case R.id.excluir:
				Toast.makeText(AlimentoActivity.this, "Item excluído", Toast.LENGTH_LONG).show();
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}
	
	public void onItemClick(View v){
		openContextMenu(v);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}

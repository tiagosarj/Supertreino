package br.ufba.matc89;

import br.ufba.matc89.model.Alimento;
import br.ufba.matc89.util.NumberUtil;
import br.ufba.matc89.controller.AlimentoController;
import br.ufba.matc89.dao.AlimentoDAO;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlimentoAddEditActivity extends Activity {

	protected EditText alimento_nome;
	protected EditText alimento_fonte;
	protected EditText alimento_carboidrato;
	protected EditText alimento_proteina;
	protected EditText alimento_gordura;
	protected EditText alimento_caloria;
	protected Button salvarButton;
	protected AlimentoController controller;
	protected Alimento alimento = new Alimento();
	protected Context ctx;
	AlimentoDAO dbAlimento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alimento_add_edit);
		// Show the Up button in the action bar.
		setupActionBar();
		
		dbAlimento = new AlimentoDAO(this);
		ctx = this;
		
		alimento_nome = (EditText)findViewById(R.id.alimento_nome);
		alimento_fonte = (EditText)findViewById(R.id.alimento_fonte);
		alimento_carboidrato = (EditText)findViewById(R.id.alimento_carboidrato);
		alimento_proteina = (EditText)findViewById(R.id.alimento_proteina);
		alimento_gordura = (EditText)findViewById(R.id.alimento_gordura);
		alimento_caloria = (EditText)findViewById(R.id.alimento_caloria);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			long id_alimento = extras.getLong("br.ufba.matc89.id_alimento");
			alimento = dbAlimento.getById(id_alimento);
			
			alimento_nome.setText(alimento.getNome());
			alimento_fonte.setText(alimento.getFonte());
			alimento_carboidrato.setText(Double.toString(alimento.getCarboidrato()));
			alimento_proteina.setText(Double.toString(alimento.getProteina()));
			alimento_gordura.setText(Double.toString(alimento.getGordura()));
			alimento_caloria.setText(Double.toString(alimento.getCaloria()));
		}
		
		salvarButton = (Button) findViewById(R.id.salvar);
		salvarButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				attemptSave();
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_edit_alimento, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void attemptSave(){
		boolean cancel = false;
		View focusView = null;
		
		if (TextUtils.isEmpty(alimento_nome.getText().toString())) {
			alimento_nome.setError(getString(R.string.error_field_required));
			focusView = alimento_nome;
			cancel = true;
		}
		
		if(cancel){
			focusView.requestFocus();
		} else {
			alimento.setNome(alimento_nome.getText().toString());
			alimento.setFonte(alimento_fonte.getText().toString());
			alimento.setCarboidrato(NumberUtil.parseDouble(alimento_carboidrato.getText().toString()));
			alimento.setProteina(NumberUtil.parseDouble(alimento_proteina.getText().toString()));
			alimento.setGordura(NumberUtil.parseDouble(alimento_gordura.getText().toString()));
			alimento.setCaloria(NumberUtil.parseDouble(alimento_caloria.getText().toString()));
			
			dbAlimento.save(alimento);
			//TODO usar o controller para salvar alimento
			//controller.save(ctx,alimento);
			
			Intent changeActivity = new Intent(AlimentoAddEditActivity.this, AlimentoActivity.class);
			changeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(changeActivity, 1);
		}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		dbAlimento.openToWrite();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		dbAlimento.close();
	}
}

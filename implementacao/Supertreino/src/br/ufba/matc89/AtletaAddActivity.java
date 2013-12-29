package br.ufba.matc89;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import br.ufba.matc89.controller.AtletaController;
import br.ufba.matc89.controller.MedidaController;
import br.ufba.matc89.controller.UsuarioController;
import br.ufba.matc89.dao.UsuarioDAO;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Usuario;
import br.ufba.matc89.util.DateUtil;
import br.ufba.matc89.util.ErroUtil;

public class AtletaAddActivity extends Activity {

	private boolean cancelSave = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atleta_add);
		
//		final TextWatcher nascimentoMask;
//		final EditText dataNascimento = (EditText) findViewById(R.id.txtNascimento);
//		nascimentoMask = Mascara.aplicarMascara("##/##/####", dataNascimento);
//		dataNascimento.addTextChangedListener(nascimentoMask);
		final EditText dataNascimento = (EditText)findViewById(R.id.txtNascimento);
		DateUtil.validarDataEmPreenchimento(dataNascimento, "##/##/####");
				
		findViewById(R.id.btHome).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						
						openTelaPrincipal();						
					}
				});
		
		findViewById(R.id.bt_save).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						processSave();							
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atleta_add, menu);
		return true;
	}
	

	public void openTelaPrincipal(){
		Intent it = new Intent(this, HomeActivity.class);
		startActivity(it);
	}
	
	public void openTelaRelatorio(){
		Intent it = new Intent(this, RelatorioActivity.class);
		startActivity(it);
	}
	
	public boolean save(){
		UsuarioController usuarioControl = new UsuarioController();
		
		if(!usuarioControl.save(this)){		
			return false;
		}		
		return true;
	}
	
	public void save(Usuario usuario, Atleta atleta){
		UsuarioDAO dUsuario = new UsuarioDAO(this);
		dUsuario.save(usuario);
	}
	
	public void setValues(){
		
		String nome = ((EditText)findViewById(R.id.txtNome)).getText().toString();
		
		if (((RadioButton)findViewById(R.id.rd_masculino)).isChecked()){
			AtletaController.atleta.setGenero(1);
		}else if (((RadioButton)findViewById(R.id.rd_feminino)).isChecked()){
			AtletaController.atleta.setGenero(2);			
		}
		
		String pesoStr = ((EditText)findViewById(R.id.txtPeso)).getText().toString();
		Integer peso = null;
		if(!pesoStr.equals("")){
			peso = Integer.valueOf(pesoStr);
		}
		
		String alturaStr = ((EditText)findViewById(R.id.txtAltura)).getText().toString();
		Integer altura = null;
		if(!alturaStr.equals("")){
			altura = Integer.valueOf(alturaStr);
		}
		
		if(validarCampos()){			
			UsuarioController.usuario.setNome(nome);
			
			MedidaController.medida.setPeso(peso);
			MedidaController.medida.setAltura(altura);
									
			AtletaController.atleta.setNome(nome);
			AtletaController.atleta.setMedidaAtual(MedidaController.medida);			
		}
	}
	
	private void processSave(){
		
		setValues();
		if (!cancelSave) {
			if(save()){
				openTelaRelatorio();
			}else{
				Toast.makeText(this, "Ocorreu um erro interno. "+ErroUtil.get(),
				Toast.LENGTH_LONG).show();
			}
		}
	}
	
	private boolean validarCampos(){
		cancelSave = false;
		EditText nomeCampo = (EditText)findViewById(R.id.txtNome);
		nomeCampo.setError(null);
		View focus = null;
		
		if(nomeCampo.getText().toString().trim().equals("")){			
			nomeCampo.setError(getString(R.string.err_campo_obrigatorio));
			focus = nomeCampo;
			focus.requestFocus();
			cancelSave = true;
		}
		return !cancelSave;
	}	

}

package br.ufba.matc89;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import br.ufba.matc89.controller.AlimentoController;
import br.ufba.matc89.controller.DietaController;
import br.ufba.matc89.controller.RefeicaoController;
import br.ufba.matc89.model.Alimento;
import br.ufba.matc89.model.Dieta;
import br.ufba.matc89.model.Refeicao;

public class DietaAddActivity extends Activity {
	private static List<Alimento> alimentos = new ArrayList<Alimento>();  
	private List<Alimento> tabelaAlimentos = new ArrayList<Alimento>();
	TableLayout tLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		setContentView(R.layout.activity_dieta_add);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		tLayout = (TableLayout) findViewById(R.id.tlAlimentos);
		
		Long id;
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			id = extras.getLong("refeicao_id");
			if(id != null){
			
				RefeicaoController cRefeicao = new RefeicaoController();
				Refeicao refeicao = cRefeicao.get(id, this);
				AlimentoController cAlimento = new AlimentoController();
				tabelaAlimentos = cAlimento.getList(refeicao, this);
				
			    final EditText etRefeicao = (EditText)findViewById(R.id.txtNome);
			    etRefeicao.setText(refeicao.getNome());
			    
			    exibirTabela();
			}			
		}
		initDietas();
		
		final EditText qnt = (EditText)findViewById(R.id.etQnt);
		 
		qnt.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//if(qnt.getText().equals(findViewById(R.string.txtQnt).toString())){
							qnt.setText("");
						//}						
					}
				});
		final ImageButton imgAdd = (ImageButton)findViewById(R.id.imgAdd);
		final ImageButton imgSave = (ImageButton)findViewById(R.id.imgSave);
		final Spinner viewAlimentos = (Spinner) findViewById(R.id.cbAlimentos);
		final ImageButton imgRm = (ImageButton)findViewById(R.id.rm_bt_icon);
				
		
		
		
		imgAdd.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Alimento a = alimentos.get(viewAlimentos.getSelectedItemPosition());
						addAlimentoInTable(a);						
					}
				});
		
		imgSave.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {						
						if(processSave()){
							clean();
						}
					}
				});				
		
		imgRm.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {							
							cleanTable();						
					}
				});		
		
	}
		
	private void initDietas(){
		AlimentoController alimentoC = new AlimentoController();
		alimentos = alimentoC.getList(this);
		if(alimentos == null){
			Toast.makeText(this, "Para acessar essa tela é necessário realizar cadastro de alimento...",
					Toast.LENGTH_LONG).show();
		}
		ArrayAdapter<Alimento> adp = new ArrayAdapter<Alimento>(this,android.R.layout.simple_dropdown_item_1line, alimentos);
		Spinner viewAlimentos = (Spinner) findViewById(R.id.cbAlimentos);
		viewAlimentos.setAdapter(adp);
	}
	
	private void addAlimentoInTable(Alimento a){
			   	
	   tabelaAlimentos.add(a);	   
	  
	   
	    View linha1 = (View) findViewById(R.id.linha1);
		View linha2 = (View) findViewById(R.id.linha2);
		TableRow row1 = (TableRow) findViewById(R.id.tableRow1);
		TableRow row2 = (TableRow) findViewById(R.id.tableRow2);
		
		tLayout.removeAllViews();
        
		tLayout.addView(row1);
		tLayout.addView(linha1);
		tLayout.addView(row2);
		tLayout.addView(linha2);		
		
		exibirTabela();
       
        
	}
	private boolean processSave(){

		View focusView = null;
		boolean sucess = false;
		
		final EditText etRefeicao = (EditText)findViewById(R.id.txtNome);
		String nomeRefeicao = etRefeicao.getText().toString();
		if(TextUtils.isEmpty(nomeRefeicao)){
			etRefeicao.setError(getString(R.string.error_field_required));
			focusView = etRefeicao;
		}else if(tabelaAlimentos.isEmpty()){
			Toast.makeText(this, "Adicione ao menos um alimento à refeição...",
					Toast.LENGTH_LONG).show();
		}else{
			
			DietaController cDieta = new DietaController();
			Dieta dieta = cDieta.get(1, this);
			Refeicao refeicao = new Refeicao();
			refeicao.setNome(nomeRefeicao);
			RefeicaoController cRefeicao = new RefeicaoController();
			Long id;
			Bundle extras = getIntent().getExtras();
			if(extras != null){
				id = extras.getLong("refeicao_id");
				refeicao.setId(id);
				cRefeicao.removerAlimentosRefeicao(this, id);
			}
			cRefeicao.refeicao = refeicao;
			if(cRefeicao.save(this)){
				if(cRefeicao.saveRefeicaoDieta(this, dieta.getId())){
					for (Alimento a : tabelaAlimentos) {
						sucess = cRefeicao.saveAlimentoRefeicao(this, a.getId());
					}					
				}
			}			
		}
		String mensagem = "Refeição adicionada com sucesso.";
		if(!sucess){
			mensagem = "Erro no cadastro da refeição.";
		}
		
		Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
		return sucess;
	}
	
	private void clean(){
				   
		    final EditText qnt = (EditText)findViewById(R.id.etQnt);
		    qnt.setText("");
		    
		    final EditText etRefeicao = (EditText)findViewById(R.id.txtNome);
		    etRefeicao.setText("");
		    cleanTable();
		    
	}
	
	private void cleanTable(){
		
		alimentos.clear();
		
		View linha1 = (View) findViewById(R.id.linha1);
		View linha2 = (View) findViewById(R.id.linha2);
		TableRow row1 = (TableRow) findViewById(R.id.tableRow1);
		TableRow row2 = (TableRow) findViewById(R.id.tableRow2);
		
		tLayout.removeAllViews();
        
		tLayout.addView(row1);
		tLayout.addView(linha1);
		tLayout.addView(row2);
		tLayout.addView(linha2);
		
		tLayout.requestLayout();
	}
	
	public void exibirTabela(){
		 for(int i = 0; i < tabelaAlimentos.size(); i++){
	        	TableRow tr = new TableRow(this);
	        	tr.setId(100+i);
	        	tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
	        	
	        	TextView nomeAlimento = new TextView(this);
	        	nomeAlimento.setId(200+i);
	        	nomeAlimento.setText(tabelaAlimentos.get(i).getNome());
	        	nomeAlimento.setTextColor(Color.BLACK);
	        	nomeAlimento.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
	        	
	        	tr.addView(nomeAlimento);
	        	
	        	TextView fonteAlimento = new TextView(this);
	        	fonteAlimento.setId(200+i);
	        	fonteAlimento.setText(tabelaAlimentos.get(i).getFonte());
	        	fonteAlimento.setTextColor(Color.BLACK);
	        	fonteAlimento.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
	        	
	        	tr.addView(fonteAlimento);
	        	
	        	TextView choAlimento = new TextView(this);
	        	choAlimento.setId(200+i);
	        	choAlimento.setText(tabelaAlimentos.get(i).getCarboidrato().toString());
	        	choAlimento.setTextColor(Color.BLACK);
	        	choAlimento.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
	        	
	        	tr.addView(choAlimento);
	        	
	        	TextView ptnAlimento = new TextView(this);
	        	ptnAlimento.setId(200+i);
	        	ptnAlimento.setText(tabelaAlimentos.get(i).getProteina().toString());
	        	ptnAlimento.setTextColor(Color.BLACK);
	        	ptnAlimento.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
	        	
	        	tr.addView(ptnAlimento);
	        	
	        	 tLayout.addView(tr);//, new  TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	        	 
	        	 tLayout.requestLayout();
	        	
	        } 
	}
}

package br.ufba.matc89;

import java.util.ArrayList;
import java.util.List;

import br.ufba.matc89.controller.AlimentoController;
import br.ufba.matc89.controller.RefeicaoController;
import br.ufba.matc89.dao.AlimentoDAO;
import br.ufba.matc89.model.Alimento;
import br.ufba.matc89.model.Refeicao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class RefeicaoExibirActivity  extends Activity {
	
	Refeicao refeicao = new Refeicao();
	protected static final int EXIBIR = 3;
	List<Alimento> alimentos = new ArrayList<Alimento>(); 
	TableLayout tLayout;
	Long refeicaoId;
		
	@Override	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refeicao_exibir);
		
		TextView lbNome = (TextView) findViewById(R.id.lbNomeRefeicaoView);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			refeicaoId = extras.getLong("refeicao_id");
			if(refeicaoId != null){
				
				RefeicaoController cRefeicao = new RefeicaoController();
				refeicao = cRefeicao.get(refeicaoId, this);
				lbNome.setText(refeicao.getNome());
				AlimentoController cAlimento = new AlimentoController();
				List<Alimento> alimentosAll= cAlimento.getList(this);
				AlimentoDAO dAlimento = new AlimentoDAO(this);
				List<Long> idsAlimento = dAlimento.getIdsAlimentosPorRefeicao(refeicaoId);
				for(Alimento a: alimentosAll){
					for (int i = 0; i < idsAlimento.size(); i++) {
						if (a.getId() == idsAlimento.get(i)) {
							alimentos.add(a);
						}
					}
				}
			}
		}
		tLayout = (TableLayout) findViewById(R.id.tbExibirAlimentos);
		contruirTabela();	
		
		ImageButton btEdit = (ImageButton)findViewById(R.id.editBtRefeicao);
		
		btEdit.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						openEdit();
						
					}
				});
		ImageView btRemove = (ImageView)findViewById(R.id.btRemove);
		btRemove.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						removerRefeicao(refeicaoId);
						
						
					}
				});
		
	}

	private void contruirTabela() {
		/*TableRow rowHeader = (TableRow) findViewById(R.id.rowHeader);
		TableRow rowLinha = (TableRow) findViewById(R.id.rowLinha);
		
		tLayout.removeAllViews();
		
		tLayout.addView(rowHeader);
		tLayout.addView(rowLinha);*/
		
		for(int i = 0; i < alimentos.size(); i++){
			LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT);
			
			TableRow tr = new TableRow(this);
			tr.setId(100+i);
			//tr.setLayoutParams(new TableRow.LayoutParams(params));
			
			//Nome
			TextView nomeAlimento = new TextView(this);
        	nomeAlimento.setId(200+i);
        	nomeAlimento.setText(alimentos.get(i).getNome());
        	nomeAlimento.setTextColor(Color.BLACK);
        	nomeAlimento.setLayoutParams(params);
        	
        	tr.addView(nomeAlimento);
        	
        	//Fonte
        	TextView fonteAlimento = new TextView(this);
        	fonteAlimento.setId(200+i);
        	fonteAlimento.setText(alimentos.get(i).getFonte());
        	fonteAlimento.setTextColor(Color.BLACK);
        	fonteAlimento.setLayoutParams(params);
        	
        	tr.addView(fonteAlimento);
        	
        	//Carboidrato
        	TextView choAlimento = new TextView(this);
        	choAlimento.setId(200+i);
        	choAlimento.setText(alimentos.get(i).getCarboidrato().toString());
        	choAlimento.setTextColor(Color.BLACK);
        	choAlimento.setLayoutParams(params);
        	        	
        	tr.addView(choAlimento);
        	
        	//Proteina
        	TextView ptnAlimento = new TextView(this);
        	ptnAlimento.setId(200+i);
        	ptnAlimento.setText(alimentos.get(i).getProteina().toString());
        	ptnAlimento.setTextColor(Color.BLACK);        	
        	ptnAlimento.setLayoutParams(params);
        	        	
        	tr.addView(ptnAlimento);
        	
        	//Gordura
        	TextView gordAlimento = new TextView(this);
        	gordAlimento.setId(200+i);
        	gordAlimento.setText(alimentos.get(i).getProteina().toString());
        	gordAlimento.setTextColor(Color.BLACK);
        	gordAlimento.setLayoutParams(params);
        	
        	tr.addView(gordAlimento);        	
			
        	tLayout.addView(tr);
        	tLayout.requestLayout();
		}
	}
	
	public void openEdit(){
		Intent it = new Intent(this, DietaAddActivity.class);
		
		it.putExtra("refeicao_id", refeicao.getId());
		startActivityForResult(it, EXIBIR);
		
	}
	
	public void removerRefeicao(Long refeicaoId){
		
		RefeicaoController cRefeicao = new RefeicaoController();
		
		cRefeicao.removerAlimentosRefeicao(this, refeicaoId);
		cRefeicao.remover(this, refeicaoId);
		Toast.makeText(this, "Refeicao removida com sucesso!",
				Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(this, DietasActivity.class);
		startActivity(intent);
		
	}
}

package br.ufba.matc89.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.ufba.matc89.R;
import br.ufba.matc89.model.Refeicao;

public class RefeicaoListAdapter extends BaseAdapter {

	private  Context context;
	private List<Refeicao> refeicoes;
	public RefeicaoListAdapter(Context context, List<Refeicao> refeicoes){
		
		this.context = context;
		this.refeicoes = refeicoes;		
	}
	
	@Override
	public int getCount() {
		return refeicoes.size();
	}

	@Override
	public Object getItem(int index) {
		
		return refeicoes.get(index);
	}

	@Override
	public long getItemId(int index) {
	
		return refeicoes.get(index).getId();
	}
	
	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		Refeicao refeicao = refeicoes.get(index);
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_dietas, null);
		
		TextView nomeRefeicao = (TextView) view.findViewById(R.id.refeicaoNome);
		
		nomeRefeicao.setText(refeicao.getNome());
		
		return view;
		
	}

}


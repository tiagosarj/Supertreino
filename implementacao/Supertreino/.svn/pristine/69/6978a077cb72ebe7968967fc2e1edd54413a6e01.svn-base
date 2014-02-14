package br.ufba.matc89.controller;

import java.util.List;

import android.content.Context;
import br.ufba.matc89.dao.RefeicaoDAO;
import br.ufba.matc89.model.Refeicao;

public class RefeicaoController implements Controller<Refeicao>{
	public Refeicao refeicao = new Refeicao();
	
	
	@Override
	public boolean save(Context ctx) {
		RefeicaoDAO dRefeicao = new RefeicaoDAO(ctx);
		refeicao.setId(dRefeicao.save(refeicao));
		if(refeicao.getId()>0)
			return true;
		else
			return false;
	}

	@Override
	public Refeicao get(long id, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Refeicao get(Refeicao entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Refeicao> getList(Refeicao entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean saveRefeicaoDieta(Context ctx, long idDieta){
		RefeicaoDAO dRefeicao = new RefeicaoDAO(ctx);
		return dRefeicao.saveRefeicaoDieta(refeicao.getId(), idDieta);
	}
	
	public boolean saveAlimentoRefeicao(Context ctx, long idAlimento){
		RefeicaoDAO dRefeicao = new RefeicaoDAO(ctx);
		return dRefeicao.saveAlimentoRefeicao(idAlimento, refeicao.getId());
	}

}

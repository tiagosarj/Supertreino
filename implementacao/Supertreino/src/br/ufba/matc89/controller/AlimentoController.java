package br.ufba.matc89.controller;

import java.util.List;

import android.content.Context;
import br.ufba.matc89.dao.AlimentoDAO;
import br.ufba.matc89.model.Alimento;
import br.ufba.matc89.model.Refeicao;

public class AlimentoController implements Controller<Alimento>{
    public static Alimento alimento = new Alimento();
    
    public AlimentoController(){
    	
    }
	@Override
	public boolean save(Context ctx) {
		AlimentoDAO dAlimento = new AlimentoDAO(ctx);
		return dAlimento.save(alimento);		
	}

	@Override
	public Alimento get(long id, Context context) {
		
		return null;
	}

	@Override
	public Alimento get(Alimento entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Alimento> getList(Context ctx) {
		AlimentoDAO dAlimento = new AlimentoDAO(ctx);
		List<Alimento> alimentos = dAlimento.getAll();
		
		if(alimentos == null || alimentos.size() < 1){
			prepareAlimentos(ctx);
			alimentos = dAlimento.getAll();
		}
		return alimentos;	
	}
	@Override
	public List<Alimento> getList(Alimento entity, Context context) {
		return getList(context);
	}
	
	public List<Alimento> getList(Refeicao refeicao, Context context){
		
		AlimentoDAO dAlimento = new AlimentoDAO(context);
		return dAlimento.get(refeicao);
	}
	
	private void prepareAlimentos(Context ctx){
		alimento.setNome("Frango grelhado");
    	alimento.setFonte("proteína");
    	alimento.setProteina(22.0);
    	alimento.setCarboidrato(31.0);
    	alimento.setGordura(9.0);
    	alimento.setCaloria(120.0);
    	
    	AlimentoDAO dAlimento = new AlimentoDAO(ctx);
    	dAlimento.save(alimento);
    	
    	alimento.setNome("Pão integral");
    	alimento.setFonte("proteína");
    	alimento.setProteina(8.0);
    	alimento.setCarboidrato(10.0);
    	alimento.setGordura(1.5);
    	alimento.setCaloria(60.0);
    	
    	dAlimento.save(alimento);
    	
    	alimento.setNome("Peixe grelhado");
    	alimento.setFonte("proteína");
    	alimento.setProteina(24.0);
    	alimento.setCarboidrato(30.0);
    	alimento.setGordura(5.5);
    	alimento.setCaloria(80.0);
    	
    	dAlimento.save(alimento);
    	
    	alimento.setNome("Ovo");
    	alimento.setFonte("proteína");
    	alimento.setProteina(3.0);
    	alimento.setCarboidrato(8.0);
    	alimento.setGordura(2.5);
    	alimento.setCaloria(50.0);
    	
    	dAlimento.save(alimento);
    	
    	alimento.setNome("Banana cozida");
    	alimento.setFonte("carboidrato");
    	alimento.setProteina(0.2);
    	alimento.setCarboidrato(13.0);
    	alimento.setGordura(1.5);
    	alimento.setCaloria(40.0);
    	
    	dAlimento.save(alimento);
    	
    	alimento.setNome("Aveia");
    	alimento.setFonte("Fibra");
    	alimento.setProteina(2.2);
    	alimento.setCarboidrato(15.0);
    	alimento.setGordura(1.0);
    	alimento.setCaloria(60.0);
    	
    	dAlimento.save(alimento);
    	
    	alimento.setNome("Arroz");
    	alimento.setFonte("carboidrato");
    	alimento.setProteina(1.2);
    	alimento.setCarboidrato(20.0);
    	alimento.setGordura(1.5);
    	alimento.setCaloria(70.0);
    	
    	dAlimento.save(alimento);
	}
	
}

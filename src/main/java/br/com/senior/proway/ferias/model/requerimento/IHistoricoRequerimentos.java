package br.com.senior.proway.ferias.model.requerimento;

import br.com.senior.proway.ferias.model.entity.Requerimento;

public interface IHistoricoRequerimentos {
	
	public void adicionarHistoricoRequerimentos(Requerimento req);
	
	public void removerHistoricoRequerimentos(Requerimento req);
		
	public int verificaQuantiaRequerimentos();
	
}

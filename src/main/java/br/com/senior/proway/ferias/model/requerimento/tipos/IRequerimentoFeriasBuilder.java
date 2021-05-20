package br.com.senior.proway.ferias.model.requerimento.tipos;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;

public interface IRequerimentoFeriasBuilder {

	public void setID(int identificadorUsuario);
	
	public void setFeriasRequisitadas(Ferias ferias);

	public void setEstadoRequisicao(EstadoRequerimento emAnalise);

	public void setDataSolicitacao(LocalDate now);

}

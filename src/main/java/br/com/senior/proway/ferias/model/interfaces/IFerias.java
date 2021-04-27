package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.TiposFerias;

public interface IFerias {
	
	void setId(int id);
	int getId();
		
	void setIdentificadorUsuario(String valor);

	String getIdentificadorUsuario();

	void setDataInicio(LocalDate dataInicio);

	LocalDate getDataInicio();

	void setDataFim(LocalDate dataFim);

	LocalDate getDataFim();

	public void setDiasTotaisRequisitados(short valor);

	public short getDiasTotaisRequisitados();

	public void setDiasVendidos(short valor);

	public short getDiasVendidos();

	TiposFerias getTipo();

	void setTipo(TiposFerias total);
}

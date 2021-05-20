package br.com.senior.proway.ferias.model.DTO;

import java.time.LocalDate;

public class FeriasDTO {

	private String nomeColaborador;

	private int codigoRequerimento;
	
	private boolean usufruido;

	private LocalDate dataInicio;
	
	private LocalDate dataFim;

	private int diasRequisitados;

	private int diasVendidos;

	private String tipoFerias;
}

package br.com.senior.proway.ferias.model.DTO;

import java.time.LocalDate;

public class RequerimentoDTO {

	private String nomeColaborador;

	private String nomeGestor;

	private LocalDate dataDeAbertura;

	private String estado;

	private LocalDate dataAnalise;

	private String mensagem;

	private int diasRequisitados;
	
	private LocalDate dataInicioRequisitada;
	
	private LocalDate dataFimRequisitada;
	
	private boolean vendeuORestante;

}

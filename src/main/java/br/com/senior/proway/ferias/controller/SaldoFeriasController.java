package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.saldoferias.ISaldoFerias;
import br.com.senior.proway.ferias.model.saldoferias.ISaldoFeriasCalculos;

public class SaldoFeriasController implements ISaldoFeriasCalculos {
	
	public final short INTERVALO_ENTRE_FERIAS_EM_ANOS = 1;
	public final short DIAS_DISPONIVEIS_PARA_FERIAS = 30;

	public final short INTERVALO_FALTAS_1 = 6;
	public final short INTERVALO_FALTAS_2 = 15;
	public final short INTERVALO_FALTAS_3 = 24;
	public final short INTERVALO_FALTAS_4 = 33;

	public final short CREDITOS_FALTAS_1 = 24;
	public final short CREDITOS_FALTAS_2 = 18;
	public final short CREDITOS_FALTAS_3 = 12;
	public final short CREDITOS_FALTAS_4 = 0;
	
	/**
	 * Responsavel por definir a data em que os dias disponiveis para Ferias
	 * sao creditados para os colaboradores. Quando a estrutura e iniciada, e
	 * utilizada a informacao de admissao do colaborador para calcular o dia em que
	 * o mesmo recebe suas primeiras Ferias. Para as proximas ferias, e utilizada a
	 * data do ultimo credito de dias mais 365 dias.
	 * 
	 * @return Data dos proximos dias de ferias disponiveis
	 */	
	public LocalDate calcularProximasFerias(ISaldoFerias saldo) {
		if (saldo.getProximasFerias() == null) {
			// puxar do sistema de cadastro de funcionarios
			LocalDate admissao = LocalDate.now();
			return (admissao.plusYears(INTERVALO_ENTRE_FERIAS_EM_ANOS));
		} else {
			return (saldo.getProximasFerias().plusYears(INTERVALO_ENTRE_FERIAS_EM_ANOS));
		}
	}
	
	/**
	 * Responsavel por promover a atualização para as proximas Ferias. 
	 */	
	public void atualizarProximasFerias(ISaldoFerias saldo) {
		saldo.setProximasFerias(calcularProximasFerias(saldo));
	}
	
	/**
	 * Funcao responsavel por calcular os creditos de dias disponiveis para ferias. 
	 * Essa funcao sera chamada na data definida pela variavel "proximasFerias" e 
	 * recebe a quantidade de faltas do colaborador.
	 * 
	 * @return creditos de dias disponiveis 
	 */
	public short calcularDiasDeFerias(short faltas) {
		short creditos = DIAS_DISPONIVEIS_PARA_FERIAS;

		if (faltas >= INTERVALO_FALTAS_1 && faltas < INTERVALO_FALTAS_2)
			creditos = CREDITOS_FALTAS_1;
		else if (faltas >= INTERVALO_FALTAS_2 && faltas < INTERVALO_FALTAS_3 )
			creditos = CREDITOS_FALTAS_2;
		else if (faltas >= INTERVALO_FALTAS_3 && faltas < INTERVALO_FALTAS_4 )
			creditos = CREDITOS_FALTAS_3;
		else if (faltas >= INTERVALO_FALTAS_4)
			creditos = CREDITOS_FALTAS_4;
		return creditos;
	}

	public void atualizarDiasDeFerias(ISaldoFerias saldo, short faltas) {
		saldo.setDiasDisponiveisDeFerias(calcularDiasDeFerias(faltas));
	}
	
	/**
	 * Verifica se existem ferias do tipoDesejado.
	 *
	 * @return quantidade de ferias do tipoDesejado
	 */
	public int verificaQuantiaFeriasDeTipoNoHistorico(TiposFerias tipoDesejado, ISaldoFerias saldo) {
		ArrayList<Ferias> lista = receberFeriasEmEstado(tipoDesejado, saldo);
		return lista.size();
	}
	
	/**
	 * Verifica se existem chamados do tipoDesejado.
	 * 
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentosDeTipo(EstadoRequerimento tipoDesejado, ISaldoFerias saldo) {
		ArrayList<Requerimento> lista = receberRequerimentosEmEstado(tipoDesejado, saldo);
		return lista.size();
	}
	
	/**
	 * Pega lista de chamados do tipoDesejado. O tipoDesjado tem como opções os 
	 * termos do enum EstadosRequisicao. 
	 * Retorna uma lista de FeriasRequerimento contendo os requerimentos que possuem 
	 * status desejado.
	 * 
	 * @param listarequerimentos
	 * @return pendentes
	 */
	public ArrayList<Requerimento> receberRequerimentosEmEstado(EstadoRequerimento tipoDesejado, ISaldoFerias saldo){
		ArrayList<Requerimento> pendentes = new ArrayList<Requerimento>();
		
		for(Requerimento reqFerias : saldo.getHistoricoRequerimentos()) {
			if (reqFerias.getEstadoRequerimento() == tipoDesejado) {
				pendentes.add(reqFerias);
			}
		}
		return pendentes;
	}
	
	/**
	 * Pega lista de ferias do tipoDesejado. O tipoDesjado tem como opcoes os termos 
	 * do enum TiposFerias
	 * Retorna uma lista de Ferias contendo os chamados que possuem status desejado.
	 * 
	 * @param listaChamados
	 * @return lista
	 */
	public ArrayList<Ferias> receberFeriasEmEstado(TiposFerias tipoDesejado, ISaldoFerias saldo){
		ArrayList<Ferias> lista = new ArrayList<Ferias>();
		
		for(Ferias ferias : saldo.getHistoricoFerias()) {
			if (ferias.getTipo() == tipoDesejado) {
				lista.add(ferias);
			}
		}
		return lista;
	}
}

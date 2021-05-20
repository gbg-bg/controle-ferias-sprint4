package br.com.senior.proway.ferias.model.saldoferias;

import java.time.LocalDate;
import java.util.ArrayList;
//crtl+shift+O = AutoImport

import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.ferias.IHistoricoFerias;
import br.com.senior.proway.ferias.model.requerimento.IHistoricoRequerimentos;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFerias;

/**
 * A classe que representa o saldo e historico de ferias.
 * Possui metodos para calcular saldo, bem como a verificacao de saldo.
 * Credita dias de ferias e controle de faltas atraves de ponto.
 * A classe possui constantes para melhor entendimento e manutencao do
 * codigo. 
 */
public class SaldoFerias implements ISaldoFerias, IHistoricoFerias, IHistoricoRequerimentos, ISaldoFeriasValidacoes {

	private String identificadorUsuario; // FK Foreign Key do usuario - Decidir quais classes tem

	private LocalDate proximasFerias;
	private short diasDisponiveisDeFerias;
	private ArrayList<Ferias> historicoFerias;
	private ArrayList<RequerimentoFerias> historicoRequerimentos;

	public SaldoFerias(String identificadorUsuario, LocalDate proximasFerias, short diasDisponiveisDeFerias,
			ArrayList<Ferias> historicoFerias, ArrayList<RequerimentoFerias> historicoRequerimentos) {
		this.identificadorUsuario = identificadorUsuario;
		this.proximasFerias = proximasFerias;
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
		this.historicoFerias = historicoFerias;
		this.historicoRequerimentos = historicoRequerimentos;
	}

	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
	}

	public LocalDate getProximasFerias() {
		return proximasFerias;
	}

	public void setProximasFerias(LocalDate proximasFerias) {
		this.proximasFerias = proximasFerias;
	}

	public short getDiasDisponiveisDeFerias() {
		return diasDisponiveisDeFerias;
	}

	public void setDiasDisponiveisDeFerias(short diasDisponiveisDeFerias) {
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}

	public ArrayList<Ferias> getHistoricoFerias() {
		return historicoFerias;
	}

	public void setHistoricoFerias(ArrayList<Ferias> historicoFerias) {
		this.historicoFerias = historicoFerias;
	}

	public ArrayList<RequerimentoFerias> getHistoricoRequerimentos() {
		return historicoRequerimentos;
	}
	public void setHistoricoRequerimentos(ArrayList<RequerimentoFerias> historicoRequerimentos) {
		this.historicoRequerimentos = historicoRequerimentos;
	}

	/**
	 * Adiciona um item de ferias na lista de HistoricoFerias.
	 * 
	 * @param ferias
	 */
	public void adicionarHistoricoFerias(Ferias ferias) {
		this.historicoFerias.add(ferias);
	}

	/**
	 * Remove um item de ferias na lista de HistoricoFerias.
	 * 
	 * @param ferias
	 */
	public void removerHistoricoFerias(Ferias ferias) {
		this.historicoFerias.remove(ferias);
	}

	/**
	 * Verificar se existem requerimentos.
	 * 
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantidadeHistoricoFerias() {
		return getHistoricoFerias().size();
	}

	/**
	 * Adiciona um item de ferias na lista de HistoricoRequerimentos.
	 * 
	 * @param ferias
	 */
	public void adicionarHistoricoRequerimentos(RequerimentoFerias req) {
		this.historicoRequerimentos.add(req);
	}

	/**
	 * Remove um item de ferias na lista de HistoricoFerias.
	 * 
	 * @param ferias
	 */
	public void removerHistoricoRequerimentos(RequerimentoFerias req) {
		this.historicoRequerimentos.remove(req);
	}

	/**
	 * Verificar se existem requerimentos.
	 * 
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentos() {
		return getHistoricoRequerimentos().size();
	}

	/**
	 * Verifica se possui saldo positivo.
	 * Consulta o valor de Saldo de Ferias e verifica se ele e positivo.
	 * 
	 * @param saldoFerias, da estrutura de dados.
	 * @return
	 */
	public boolean checarSaldoPositivo() {
		return this.diasDisponiveisDeFerias > 0 ? true : false;
	}
	
}

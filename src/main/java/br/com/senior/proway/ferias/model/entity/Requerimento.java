package br.com.senior.proway.ferias.model.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

/**
 * Classe RequerimentoFerias
 * 
 * A classe RequerimentoFerias é um objeto requerimento que e gerenciado pelas
 * Classes RequerimentoController e RequerimentoDAO. E responsavel por fazer a
 * padronizacao dos requerimentos de ferias no programa. Recebe um objeto
 * {@link Ferias}, que contem as informacoes pertinentes as ferias em si.
 *
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
@Entity
public class Requerimento { // extends RequerimentoFactory<Ferias> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(targetEntity = Colaborador.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_colaborador", insertable = false, updatable = false)
	private Colaborador colaborador;

	@ManyToOne(targetEntity = Colaborador.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_gestor", insertable = false, updatable = false)
	private Colaborador gestor;

	@Column(name = "data_de_abertura")
	private LocalDate dataDeAbertura;

	@Enumerated(EnumType.ORDINAL)
	private EstadoRequerimento estado;

	@Column(name = "prazo_analise")
	private LocalDate dataAnalise;

	@Column(name = "mensagem")
	private String mensagem;

	@Transient
	public static final short PRAZO_MINIMO_SOLICITACAO_FERIAS = 10;

	private int diasRequisitados;
	private LocalDate dataInicioRequisitada;
	private LocalDate dataFimRequisitada;
	private boolean vendeuORestante;


	public Requerimento() {

	}

	public Requerimento(int id, Colaborador colaborador, Colaborador gestor, LocalDate dataDeAbertura,
			EstadoRequerimento estado, LocalDate dataAnalise, String mensagem) {
		super();
		this.id = id;
		this.colaborador = colaborador;
		this.gestor = gestor;
		this.dataDeAbertura = dataDeAbertura;
		this.estado = estado;
		this.dataAnalise = dataAnalise;
		this.mensagem = mensagem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Colaborador getGestor() {
		return gestor;
	}

	public void setGestor(Colaborador gestor) {
		this.gestor = gestor;
	}

	public LocalDate getDataDeAbertura() {
		return dataDeAbertura;
	}

	public void setDataDeAbertura(LocalDate dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}

	public EstadoRequerimento getEstado() {
		return estado;
	}

	public void setEstado(EstadoRequerimento estado) {
		this.estado = estado;
	}

	public LocalDate getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(LocalDate dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public int getDiasRequisitados() {
		return diasRequisitados;
	}

	public void setDiasRequisitados(int diasRequisitados) {
		this.diasRequisitados = diasRequisitados;
	}

	public int getDiasVendidos() {
		return diasVendidos;
	}

	public void setDiasVendidos(int diasVendidos) {
		this.diasVendidos = diasVendidos;
	}

	public int getDiasTirados() {
		return diasTirados;
	}

	public void setDiasTirados(int diasTirados) {
		this.diasTirados = diasTirados;
	}

	public TiposFerias getTipoFerias() {
		return tipoFerias;
	}

	public void setTipoFerias(TiposFerias tipoFerias) {
		this.tipoFerias = tipoFerias;
	}
	
	

}

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

import br.com.senior.proway.ferias.model.enums.TiposFerias;

/**
 * Classe que representa a estrutura de dados de um "model" de Ferias. Para
 * instanciar uma nova Ferias, e necessario informar a data de inicio, data fim
 * e os dias totais requisitados. O construtor calcula o periodo em dias entre
 * as datas, calcula dias a serem vendidos se necessario e classifica as ferias
 * em um dos tipos disponiveis do {@link TiposFerias}.
 * 
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 * @see FeriasBuilder
 * @see TiposFerias
 */
@Entity
public class Ferias {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(targetEntity = Colaborador.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "colaborador", insertable = false, updatable = false)
	private Colaborador colaborador;

	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@Column(name = "data_fim")
	private LocalDate dataFim;

	@Column(name = "dias_requisitados")
	private int diasRequisitados;

	@Column(name = "dias_vendidos")
	private int diasVendidos;

	@Enumerated(EnumType.ORDINAL)
	private TiposFerias tipoFerias;

	public Ferias() {

	}

	public Ferias(int id, LocalDate dataInicio, LocalDate dataFim, int diasRequisitados, int diasVendidos,
			TiposFerias tipoFerias) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.diasRequisitados = diasRequisitados;
		this.diasVendidos = diasVendidos;
		this.tipoFerias = tipoFerias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public TiposFerias getTipoFerias() {
		return tipoFerias;
	}

	public void setTipoFerias(TiposFerias tipoFerias) {
		this.tipoFerias = tipoFerias;
	}

}

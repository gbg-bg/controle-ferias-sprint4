package br.com.senior.proway.ferias.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A classe que representa o saldo e historico de ferias. Possui metodos para
 * calcular saldo, bem como a verificacao de saldo. Credita dias de ferias e
 * controle de faltas atraves de ponto. A classe possui constantes para melhor
 * entendimento e manutencao do codigo.
 */
@Entity
@Table(name = "saldo")
public class Saldo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_colaborador", referencedColumnName = "id")
	private Colaborador colaborador;

	@Column(name = "dias_disponiveis_de_ferias")
	private short diasDisponiveisDeFerias;

	public Saldo() {
	}

	public Saldo(Integer id, Colaborador colaborador, short diasDisponiveisDeFerias) {
		this.id = id;
		this.colaborador = colaborador;
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public short getDiasDisponiveisDeFerias() {
		return diasDisponiveisDeFerias;
	}

	public void setDiasDisponiveisDeFerias(short diasDisponiveisDeFerias) {
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}

	
}

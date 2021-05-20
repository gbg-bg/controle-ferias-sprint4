package br.com.senior.proway.ferias.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "colaborador")
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_saldo", referencedColumnName = "id")
	private Saldo saldo;

	@ManyToOne(targetEntity = Colaborador.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_gestor", insertable = false, updatable = false)
	private Colaborador gestor;

	@OneToMany(mappedBy = "gestor")
	private Set<Colaborador> colaboradoresQueEuSouGestor = new HashSet<Colaborador>();

	public Colaborador() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Saldo getSaldo() {
		return saldo;
	}

	public void setSaldo(Saldo saldo) {
		this.saldo = saldo;
	}

	public Colaborador getGestor() {
		return gestor;
	}

	public void setGestor(Colaborador gestor) {
		this.gestor = gestor;
	}

	public Set<Colaborador> getColaboradoresQueEuSouGestor() {
		return colaboradoresQueEuSouGestor;
	}

	public void setColaboradoresQueEuSouGestor(Set<Colaborador> colaboradoresQueEuSouGestor) {
		this.colaboradoresQueEuSouGestor = colaboradoresQueEuSouGestor;
	}

}

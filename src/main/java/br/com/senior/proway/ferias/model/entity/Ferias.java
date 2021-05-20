package br.com.senior.proway.ferias.model.ferias;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

/**
 * Classe que representa a estrutura de dados de um "model" de Ferias. Para
 * instanciar uma nova Ferias, e necessario informar a data de inicio,
 * data fim e os dias totais requisitados. O construtor calcula o periodo em dias entre as datas,
 * calcula dias a serem vendidos se necessario e classifica as ferias em um
 * dos tipos disponiveis do {@link TiposFerias}.
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
public class Ferias implements IFerias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private int identificadorUsuario;

	@Basic
	private LocalDate dataInicio;
	
	@Basic
	private LocalDate dataFim;
	@Transient
	private int diasTotaisRequisitados;
	private int diasVendidos;
	@Enumerated(EnumType.ORDINAL)
	private TiposFerias tipoFerias;

	public Ferias() {

	}

	public Ferias(LocalDate dataInicio, LocalDate dataFim, int diasTotaisRequisitados, int diasVendidos,
			TiposFerias tipo) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.diasTotaisRequisitados = diasTotaisRequisitados;
		this.diasVendidos = diasVendidos;
		this.tipoFerias = tipo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public int getIdentificadorUsuario() {
		return this.identificadorUsuario;
	}

	public void setIdentificadorUsuario(int valor) {
		this.identificadorUsuario = valor;
	}

	public LocalDate getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDate data) {
		this.dataInicio = data;
	}

	public LocalDate getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDate data) {
		this.dataFim = data;
	}

	public int getDiasTotaisRequisitados() {
		return this.diasTotaisRequisitados;
	}

	public void setDiasTotaisRequisitados(int valor) {
		this.diasTotaisRequisitados = valor;
	}

	public int getDiasVendidos() {
		return this.diasVendidos;
	}

	public void setDiasVendidos(int valor) {
		this.diasVendidos = valor;
	}

	public TiposFerias getTipo() {
		return this.tipoFerias;
	}

	public void setTipo(TiposFerias tipo) {
		this.tipoFerias = tipo;
	}

}

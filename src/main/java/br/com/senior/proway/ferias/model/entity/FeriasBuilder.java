package br.com.senior.proway.ferias.model.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

/**
 * Responsavel por instanciar um objeto de Ferias de acordo com os valores
 * passados pelo FeriasDirector.
 * 
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
public class FeriasBuilder {
	protected final int CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 14;
	protected final int DIAS_MAXIMOS_A_VENDER = 32;

	private int id;
	private Colaborador colaborador;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int diasRequisitados;
	private int diasVendidos; // Usado apenas pelas férias parcial.
	private int diasTirados; // Usado apenas pelas férias fracionada
	private TiposFerias tipoFerias;

	/**
	 * Instancia e retorna um objeto de Ferias apos realizar uma checagem nos
	 * valores recebidos pelo FeriasDirector.
	 * 
	 * @param creditos Saldo disponivel de creditos para ferias
	 */
	public Ferias build() {
		return new Ferias(dataInicio, dataFim, diasRequisitados, diasVendidos, tipoFerias);
	}

	public void inicializarFerias(LocalDate dataInicio, LocalDate dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public void processarFerias(TiposFerias tipo, short dias) {
		switch (tipo) {
		case TOTAL:
			diasVendidos = 0;
			break;
		case PARCIAL:
			diasVendidos = dias;
			break;
		case FRACIONADA:
			diasTirados = dias;
			break;
		default:
			break;
		}
	}

	/**
	 * Calcula a quantidade de dias entre a data de inicio e fim das Ferias. Caso as
	 * ferias nao tenham informacao de data, retorna 0.
	 * 
	 * @return int
	 */
	public void calcularPeriodoFerias() {
		if (dataInicio == null || dataFim == null) {
			diasRequisitados = 0;
		} else {
			if (validarDataDasFerias(dataInicio, dataFim)) {
				diasRequisitados = (int) dataInicio.until(dataFim, ChronoUnit.DAYS) + 1;
			} else {
				diasRequisitados = 0;
			}
		}
	}

	/**
	 * Verifica se a data de inicio do periodo de Ferias vem antes da data de final.
	 * 
	 * @param dataInicio dataFim
	 * @return periodo valido/invalido
	 */
	private boolean validarDataDasFerias(LocalDate dataInicio, LocalDate dataFim) {
		boolean check = dataInicio.isBefore(dataFim) ? true : false;
		return check;
	}

	/**
	 * Realiza a checagem de dados para realização da instanciacao de um objeto
	 * Ferias.
	 * 
	 * @param creditos Saldo disponivel de creditos para ferias
	 */
	public boolean checarValidade(int creditos) {
		// Checagem de creditos
		if (ferias.getDiasTotaisRequisitados() + ferias.getDiasVendidos() > creditos) {
			ferias.setTipo(TiposFerias.INVALIDA);
			return false;
		}
		// Checagem especifica para ferias Vendida
		if (ferias.getTipo() == TiposFerias.VENDIDA) {
			if (ferias.getDiasVendidos() > 0 && ferias.getDiasTotaisRequisitados() == 0)
				return true;
		}
		// Checagem dos outros tipos
		else {
			if (validarDataDasFerias(dataInicio, dataFim)) {
				switch (ferias.getTipo()) {
				case TOTAL:
				case FRACIONADA:
					if (ferias.getDiasVendidos() == 0 && ferias.getDiasTotaisRequisitados() > 0)
						return true;
					break;
				case PARCIAL:
					if (ferias.getDiasVendidos() >= 0 && ferias.getDiasTotaisRequisitados() > 0)
						return true;
					break;
				default:
					break;
				}
			}
		}
		// Checagens falharam, retorna falso e invalida as ferias;
		ferias.setTipo(TiposFerias.INVALIDA);
		return false;
	}

	/**
	 * Calcula os dias a serem vendidos com base nos dias de ferias disponiveis ao
	 * colaborador e no tipo de Ferias. Apenas os tipos PARCIAL e VENDIDA terao dias
	 * a ser vendidos.
	 * 
	 * @param diasDisponiveisParaFerias - vem da classe SaldoFerias
	 * @return int dias a serem vendidos
	 */
	public void calcularDiasVendidos(int diasDisponiveisParaFerias) {
		if (getTipo() == TiposFerias.PARCIAL || getTipo() == TiposFerias.VENDIDA) {
			int diasAVender = diasDisponiveisParaFerias - getDiasTotaisRequisitados();
			if (diasAVender > 0) {
				this.diasVendidos = (diasAVender >= DIAS_MAXIMOS_A_VENDER) ? DIAS_MAXIMOS_A_VENDER : diasAVender;
			}
		} else {
			this.diasVendidos = 0;
		}
	}

}
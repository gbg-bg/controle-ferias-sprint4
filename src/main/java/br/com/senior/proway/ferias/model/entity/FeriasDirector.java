package br.com.senior.proway.ferias.model.ferias.builder;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

/**
 * Responsavel por definir os valores que o Builder carrega para cada caso de
 * instanciamento de tipos de Ferias.
 * 
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
public class FeriasDirector {

	/**
	 * Instancia um objeto de Ferias classificado como Ferias do tipo TOTAL.
	 * As ferias do tipo TOTAL corresponde a um periodo completo de ferias
	 * - 30 (trinta) dias.
	 * 
	 * @param builder    Objeto Builder responsavel por instanciar Ferias
	 * @param dataInicio LocalDate que representa a data de inicio das ferias
	 * @param dataFim    LocalDate que representa a data de fim das ferias
	 */
	public void createFeriasTotal(IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.TOTAL);
		builder.calcularPeriodoFerias();
		builder.setDiasVendidos((short) 0);
	}

	/**
	 * Instancia um objeto de Ferias classificado como Ferias do tipo FRACIONADA.
	 * As ferias do tipo FRACIONADA corresponde a um periodo fracionado de ferias.
	 * 
	 * @param builder    Objeto Builder responsavel por instanciar Ferias
	 * @param dataInicio LocalDate que representa a data de inicio das ferias
	 * @param dataFim    LocalDate que representa a data de fim das ferias
	 */
	public void createFeriasFracionada(IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.FRACIONADA);
		builder.calcularPeriodoFerias();
		builder.setDiasVendidos((short) 0);
	}

	/**
	 * Instancia um objeto de Ferias classificado como Ferias do tipo VENDIDA.
	 * As ferias do tipo VENDIDA corresponde a um periodo de ferias vendida.
	 * 
	 * @param builder       Objeto Builder responsavel por instanciar Ferias
	 * @param diasEmCredito Saldo disponivel de creditos para ferias
	 */
	public void createFeriasVendida(IFeriasBuilder builder, short diasEmCredito) {
		builder.setDataInicio(null);
		builder.setDataFim(null);
		builder.setTipo(TiposFerias.VENDIDA);
		builder.calcularPeriodoFerias();
		builder.calcularDiasVendidos(diasEmCredito);
	}

	/**
	 * Um variacao que Instancia um objeto de Ferias classificado como Ferias
	 * do tipo VENDIDA.
	 * 
	 * @param builder     Objeto Builder responsavel por instanciar Ferias
	 * @param diasAVender Quantos creditos devem ser vendidos
	 */
	public void createFeriasVendidaEspecifica(IFeriasBuilder builder, short diasAVender) {
		builder.setDataInicio(null);
		builder.setDataFim(null);
		builder.setTipo(TiposFerias.VENDIDA);
		builder.calcularPeriodoFerias();
		builder.setDiasVendidos(diasAVender);
	}

	/**
	 * Instancia um objeto de Ferias classificado como Ferias do tipo PARCIAL.
	 * As ferias do tipo PARCIAL corresponde a um periodo de ferias que excede
	 * a um periodo vendido.
	 * 
	 * @param builder       Objeto Builder responsavel por instanciar Ferias
	 * @param dataInicio    LocalDate que representa a data de inicio das ferias
	 * @param dataFim       LocalDate que representa a data de fim das ferias
	 * @param diasEmCredito Saldo disponivel de creditos para ferias
	 */
	public void createFeriasParcial(IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim,
			short diasEmCredito) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.PARCIAL);
		builder.calcularPeriodoFerias();
		builder.calcularDiasVendidos(diasEmCredito);
	}
}

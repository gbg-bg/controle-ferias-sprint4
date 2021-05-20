package br.com.senior.proway.ferias.model.requerimento.tipos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.senior.proway.ferias.controller.RequerimentoController;
import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;

/**
 * Classe RequerimentoFeriasController.
 * 
 * Por ser uma extensao de RequerimentoController, e possivel adicionar metodos
 * especificos para uso com os requerimentos de ferias.
 * 
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 *
 */
public class RequerimentoFeriasController extends RequerimentoController {

	private static RequerimentoFeriasController requerimentoController;

	/**
	 * Implementacao do singleton da classe. <br>
	 * <br>
	 * 
	 * E utilizado para obter uma instancia utilizavel dessa classe.
	 * 
	 * @return RequerimentoFeriasController
	 */

	public static RequerimentoFeriasController getInstance() {
		if (requerimentoController == null) {
			requerimentoController = new RequerimentoFeriasController();
		}
		return requerimentoController;
	}

	/**
	 * Retorna quantidade de dias
	 * 
	 * Retorna a quantidade de dias entre as datas informadas.
	 * 
	 * @param inicio  LocalDate
	 * @param termino LocalDate
	 * 
	 */
	public static int retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino) {
		int dias = (int) ChronoUnit.DAYS.between(inicio, termino);
		if (inicio.isBefore(termino)) {
			return dias;
		}
		return -1;
	}

	/**
	 * Valida o Prazo da Solicitacao de Ferias.
	 * 
	 * Verifica se o inicio de uma ferias se da ao menos
	 * PRAZO_MINIMO_SOLICITACAO_FERIAS dias apos a requisicao ser criada, para
	 * evitar que uma ferias seja pedida sem antecedencia.
	 * 
	 * @param dataInicioFerias
	 * @return true caso o requerimento seja criado com a antecedencia necessaria.
	 * 
	 */
	public static boolean validacaoPrazoSolicitacaoDeFerias(Requerimento feriasRequerimento) {
		int intervalo = retornarIntervaloEmDiasEntreAsDatas(feriasRequerimento.getDataCriacaoRequerimento(),
				((Ferias) feriasRequerimento.getObjetoRequerimento()).getDataInicio());
		if (intervalo >= Requerimento.PRAZO_MINIMO_SOLICITACAO_FERIAS + 1) {
			return true;
		} else {
			feriasRequerimento.setEstadoRequerimento(EstadoRequerimento.INVALIDO);
			return false;
		}
	}

	/**
	 * Metodo defereRequerimento.
	 * 
	 * E utilizado para alterar o {@link EstadoRequerimento} de um Requerimento de Ferias
	 * @param requerimento
	 * @param estado
	 * @param idUsuario
	 * @return
	 */
	public boolean defereRequerimento(Requerimento requerimento, EstadoRequerimento estado, Integer idUsuario) {
		requerimento.setEstadoRequerimento(estado);
		requerimentoController.atualizarRequerimentoPorId(requerimento);
		return true;
	}
}

package br.com.senior.proway.ferias.model.requerimento;

import java.time.LocalDate;

import java.util.List;

import br.com.senior.proway.ferias.OBLITERAR.IControleDeAcesso;
import br.com.senior.proway.ferias.controller.RequerimentoController;
import br.com.senior.proway.ferias.exceptions.UserDoesNotHavePermissionException;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFeriasController;

/**
 * Classe controller verificadora do {@link RequerimentoFeriasController}
 * 
 * Esse controller faz a verificação sobre o {@link RequerimentoFeriasController} a cada 
 * metodo passando Id de usuario por parametro, Verificando se ele tem acesso a esse metodo
 * ou nao.
 * 
 * @see RequerimentoFeriasController
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
public class ValidacaoRequerimentoFeriasController {

	static ValidacaoRequerimentoFeriasController validacao;
	static RequerimentoFeriasController requerimentoFeriasController;
	static RequerimentoController controller;
	static IControleDeAcesso acesso;

	public static ValidacaoRequerimentoFeriasController getInstance() {
		requerimentoFeriasController = RequerimentoFeriasController.getInstance();
		if (validacao == null) {
			validacao = new ValidacaoRequerimentoFeriasController();
		}
		return validacao;
	}

	/**
	 * Busca todos os requerimentos.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo buscarTodosOsRequerimentos da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 * 
	 * 
	 * @param Class<?> classe, parametro usado no metodo chamado.
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @return List<IRequerimento>, lista de toda a inteface.
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	public List<IRequerimento> buscarTodosOsRequerimentos(Class<?> classe, Integer idUsuario) throws UserDoesNotHavePermissionException {
		if(acesso.validarAcesso(idUsuario)){	
			return requerimentoFeriasController.buscarTodosOsRequerimentos(classe);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
			
	}
	
	/**
	 * Busca os requerimentos por id.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo buscarRequerimentosPorId da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 * 
	 * 
	 * @param Class<?> classe, parametro usado no metodo chamado.
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @param Integer idRequerimento, id do requerimento usado no parametro do metodo.
	 * @return IRequerimento, requerimento encontrado.
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	public IRequerimento buscarRequerimentoPorId(Class<?> tipoRequerimento, Integer idUsuario, Integer idRequerimento) throws UserDoesNotHavePermissionException {
		
		if(acesso.validarAcesso(idUsuario)){	
			return requerimentoFeriasController.buscarRequerimentoPorId(tipoRequerimento, idRequerimento);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
	}
	
	/**
	 * Cria um requerimento.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo createRequerimento da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 * 
	 * @param IRequerimento requerimento, objeto usado no parametro do metodo chamado.
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @return boolean, caso de sucesso ou falha da execucao do metodo.
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	public boolean createRequerimento(IRequerimento requerimento, Integer idUsuario) throws UserDoesNotHavePermissionException {
		if(acesso.validarAcesso(idUsuario)){	
			return requerimentoFeriasController.criarRequerimento(requerimento);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
	}

	/**
	 * Atualiza requerimento por id.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo createRequerimento da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 *
	 * @param IRequerimento requerimento, objeto usado no parametro do metodo chamado.
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @return boolean, caso de sucesso ou falha da execucao do metodo.
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	public boolean atualizarRequerimentoPorId(IRequerimento requerimento, Integer idUsuario) throws UserDoesNotHavePermissionException {
		if(acesso.validarAcesso(idUsuario)){	
			return requerimentoFeriasController.atualizarRequerimentoPorId(requerimento);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
	}

	/**
	 * Deleta requerimento.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo deleteRequerimento da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 *
	 * @param IRequerimento requerimento, objeto usado no parametro do metodo chamado.
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	public void deleteRequerimento(IRequerimento requerimento, Integer idUsuario) throws UserDoesNotHavePermissionException {
		if(acesso.validarAcesso(idUsuario)){	
		  requerimentoFeriasController.deleteRequerimento(requerimento);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
	}

	
	/**
	 * Retorna intevalo em dias entre as datas do requerimento.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo retornarIntervaloEmDiasEntreAsDatas da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 *
	 * @param LocalDate inicio, data de inicio da requerimento
	 * @param LocalDate termino, data de inicio da requerimento
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @return short, dias entre os intervalos
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	public static int retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino, Integer idUsuario) throws UserDoesNotHavePermissionException {
		if(acesso.validarAcesso(idUsuario)){	
			return requerimentoFeriasController.retornarIntervaloEmDiasEntreAsDatas(inicio, termino);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
	}
	
	/**
	 * Validacao prazo solicitacao de ferias.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo validacaoPrazoSolicitacaoDeFerias da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 *
	 * @param Requerimento feriasRequerimento, objeto usado no parametro do metodo chamado.
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @return boolean, caso de sucesso ou falha da execucao do metodo.
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	static boolean validacaoPrazoSolicitacaoDeFerias(Requerimento feriasRequerimento, Integer idUsuario) throws UserDoesNotHavePermissionException {
		if(acesso.validarAcesso(idUsuario)){	
			return requerimentoFeriasController.validacaoPrazoSolicitacaoDeFerias(feriasRequerimento);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
	}

	/**
	 * Defere o requerimento.
	 * 
	 * O metodo recebe os mesmos parmetros do metodo defereRequerimento da Classe
	 * {@link RequerimentoFeriasController} adcionando o idUsuario ao parametro para verificar se 
	 * o usuario tem acesso ao metodo, chamando o mesmo.
	 *
	 * @param Requerimento requerimento, objeto usado no parametro do metodo chamado.
	 * @param EstadoRequerimento estado, enum utlizado para mudar o estado do requerimento e
	 *  passado no parametro do metodo chamdo
	 * @param Integer idUsuario, id do usuario para verificar se tem acesso.
	 * @return boolean, caso de sucesso ou falha da execucao do metodo.
	 * @throws UserDoesNotHavePermissionException
	 * @author Guilherme Ezequiel, Marcelo.
	 */
	public boolean defereRequerimento(Requerimento requerimento, EstadoRequerimento estado, Integer idUsuario) throws UserDoesNotHavePermissionException {
		if(acesso.validarAcesso(idUsuario)){	
			return requerimentoFeriasController.defereRequerimento(requerimento, estado, idUsuario);
		}else { 
			throw new UserDoesNotHavePermissionException();	
		}
	}

}

package br.com.senior.proway.ferias.model.requerimento;

import java.util.List;

/**
 * Classe RequerimentoController.<br>
 * <br>
 * 
 * Esta classe e a responsavel por fazer o controle de dados dos requerimentos.
 * Possui metodos para alterar, criar, deletar ou buscar requerimentos.<br>
 * <br>
 * 
 * @see RequerimentoDAO
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
public class RequerimentoController {
	private static RequerimentoDAO requerimentoDAO;
	private static RequerimentoController requerimentoController;

	/**
	 * Implementacao do singleton da classe. <br>
	 * <br>
	 * 
	 * E utilizado para obter uma instancia utilizavel dessa classe.
	 * 
	 * @return RequerimentoController
	 */
	public static RequerimentoController getInstance() {
		requerimentoDAO = RequerimentoDAO.getInstance();
		if (requerimentoController == null) {
			requerimentoController = new RequerimentoController();
		}	
		return requerimentoController;
	}

	/**
	 * Metodo buscarTodosOsRequerimentos(Class<?> classe). <br>
	 * <br>
	 * 
	 * Controller faz contato com o RequerimentoDAO, que retorna uma lista de todos
	 * os requerimentos de um tipo.
	 * 
	 * @param classe Class<?> engloba o tipo de requerimento a ser buscado
	 * @return List<IRequerimento>
	 */
	public List<IRequerimento> buscarTodosOsRequerimentos(Class<?> classe) {
		List<IRequerimento> requerimentosFerias = requerimentoDAO.buscarRequerimentos(classe);
		return requerimentosFerias;
	}

	/**
	 * Metodo buscarRequerimentoPorId(Class<?> tipoRequerimento, Integer id). <br>
	 * <br>
	 * 
	 * Controller faz contato com o RequerimentoDAO, que retorna o requerimento que
	 * possui o ID especificado.
	 * 
	 * @param tipoRequerimento Class<?> engloba o tipo do requerimento a ser buscado
	 * @param id               Integer o id do requerimento
	 * @return IRequerimento
	 */
	public IRequerimento buscarRequerimentoPorId(Class<?> tipoRequerimento, Integer id) {
		IRequerimento requerimento = requerimentoDAO.buscarRequerimento(tipoRequerimento, id);
		return requerimento;
	}

	/**
	 * Metodo criarRequerimento(IRequerimento requerimento). <br>
	 * <br>
	 * 
	 * Controller faz contato com o RequerimentoDAO, recebe como parametro um objeto
	 * do tipo IRequerimento e o insere no banco de dados
	 * 
	 * @param RequerimentoFerias requerimento
	 * @return true caso o metodo execute corretamente.
	 */
	public boolean createRequerimento(IRequerimento requerimento) {
		requerimentoDAO.criarRequerimento(requerimento);
		return true;
	}

	/**
	 * Metodo atualizarRequerimento(IRequerimento requerimento). <br>
	 * <br>
	 * 
	 * Controller faz contato com o RequerimentoDAO, recebe um objeto que implementa
	 * a interface IRequerimento (ou que extende uma classe que a implementa), e o
	 * atualiza no banco de dados.
	 * 
	 * @param requerimento (IRequerimento) o requerimento atualizado
	 */
	public boolean atualizarRequerimentoPorId(IRequerimento requerimento) {
		requerimentoDAO.atualizarRequerimento(requerimento);
		return true;
	}

	/**
	 * Metodo deletarRequerimento(IRequerimento requerimento). <br>
	 * <br>
	 * 
	 * Controller faz contato com o RequerimentoDAO, recebe um objeto que implementa
	 * a interface IRequerimento (ou que extende uma classe que a implementa), e o
	 * exclui do banco de dados. 
	 * 
	 * @param id (short)
	 * @throws Exception
	 */
	public void deleteRequerimento(IRequerimento requerimento) {
		requerimentoDAO.deletarRequerimento(requerimento);
	}
}

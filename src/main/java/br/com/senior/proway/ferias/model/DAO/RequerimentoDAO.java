package br.com.senior.proway.ferias.model.DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.Session;

import br.com.senior.proway.ferias.model.db.DBConnection;
import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.requerimento.IRequerimento;
import br.com.senior.proway.ferias.model.requerimento.IRequerimentoDAO;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoSalario;

/**
 * Classe RequerimentoDAO. <br>
 * <br>
 * 
 * Essa classe e responsavel por fazer a gestao do banco de dados para os tipos
 * de requerimento. E possivel utiliza-la par adicionar, remover, atualizar ou
 * buscar informacoes do banco de dados.
 * 
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
public class RequerimentoDAO implements IRequerimentoDAO {

	private static RequerimentoDAO requerimentoDAO;
	private Session session = DBConnection.getSession();

	/**
	 * Implementacao do singleton da classe. <br>
	 * <br>
	 * 
	 * E utilizado para obter uma instancia utilizavel dessa classe.
	 * 
	 * @return RequerimentoDAO
	 */
	public static RequerimentoDAO getInstance() {
		if (requerimentoDAO == null) {
			requerimentoDAO = new RequerimentoDAO();
		}
		return requerimentoDAO;
	}

	private RequerimentoDAO() {

	}

	/**
	 * Metodo criarRequerimento(IRequerimento requerimento).
	 * 
	 * Metodo recebe um objeto que implementa a interface IRequerimento (ou que
	 * extende uma classe que a implementa) e o adiciona ao banco de dados.
	 * 
	 * @param requerimento IRequerimento objeto a ser adicionado no banco
	 * @return true caso o metodo execute corretamente
	 */
	public boolean criarRequerimento(IRequerimento requerimento) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(requerimento);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * Metodo deletarRequerimento(IRequerimento requerimento).
	 * 
	 * Metodo recebe um objeto que implementa a interface IRequerimento (ou que
	 * extende uma classe que a implementa) e o exclui ao banco de dados.
	 * 
	 * @param requerimento IRequerimento objeto a ser removido do banco
	 * @return true caso o metodo execute corretamente
	 */
	public boolean deletarRequerimento(IRequerimento requerimento) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.delete(requerimento);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * Metodo atualizarRequerimento(IRequerimento requerimento).
	 * 
	 * Metodo recebe um objeto previamente alterado que implementa a interface
	 * IRequerimento (ou que extende uma classe que a implementa) e atualiza no
	 * banco de dados.
	 * 
	 * @param requerimento IRequerimento objeto a ser alterado no banco
	 * @return true caso o metodo execute corretamente
	 */
	public boolean atualizarRequerimento(IRequerimento requerimento) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.update(requerimento);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * Metodo buscarRequerimentos(IRequerimento requerimento).
	 * 
	 * Metodo recebe um objeto que implementa a interface IRequerimento (ou que
	 * extende uma classe que a implementa) e busca todos os outros objetos dessa
	 * classe na tabela correspondente a eles no banco de dados.
	 * 
	 * @param requerimento IRequerimento referente a classe do objeto que sera
	 *                     buscado no banco
	 * @return List<IRequerimento> correspondente ao resultado da busca pela classe
	 *         de requerimentos no banco
	 */
	public List<IRequerimento> buscarRequerimentos(IRequerimento requerimento) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<IRequerimento> cq = cb.createQuery(IRequerimento.class);
		return session.createQuery(cq.select(cq.from(IRequerimento.class))).getResultList();
	}

	/**
	 * Metodo buscarRequerimentos(Class<?> tipoRequerimento).
	 * 
	 * Metodo recebe uma classe e busca todos os outros objetos dessa classe na
	 * tabela correspondente a eles no banco de dados.
	 * 
	 * @param tipoRequerimento Class referente a classe do objeto que sera buscado
	 *                         no banco.
	 * @return List<IRequerimento> correspondente ao resultado da busca pela classe
	 *         de requerimentos no banco.
	 */
	public List<IRequerimento> buscarRequerimentos(Class<?> tipoRequerimento) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<IRequerimento> cq = (CriteriaQuery<IRequerimento>) cb.createQuery(tipoRequerimento);
		return session.createQuery(cq.select((Selection<? extends IRequerimento>) cq.from(tipoRequerimento)))
				.getResultList();
	}

	/**
	 * Metodo buscarRequerimentos(Class<?> tipoRequerimento).
	 * 
	 * Metodo recebe uma classe e busca todos os outros objetos dessa classe na
	 * tabela correspondente a eles no banco de dados que possuam determinado {@link EstadoRequerimento}.
	 * @param tipoRequerimento Class referente a classe do objeto que sera buscado
	 *                         no banco.
	 * @param estadoRequerimento Determina qual o estado de requerimento que sera buscado no banco.
	 * @return List contendo os requerimentos que satisfazem os padroes da busca
	 */
	public List<IRequerimento> buscarRequerimentos(Class<?> tipoRequerimento, EstadoRequerimento estadoRequerimento) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<IRequerimento> cq = (CriteriaQuery<IRequerimento>) cb.createQuery(tipoRequerimento);
		Root<IRequerimento> root = (Root<IRequerimento>) cq.from(tipoRequerimento);
		return session
				.createQuery(cq.select((Selection<? extends IRequerimento>) cq.from(tipoRequerimento))
						.where(cb.equal(root.get("estadoRequerimento"), estadoRequerimento.getValor())))
				.getResultList();
	}

	/**
	 * Metodo buscarRequerimentos(Class<?> tipoRequerimento).
	 * 
	 * Metodo recebe uma classe e busca todos os outros objetos dessa classe na
	 * tabela correspondente a eles no banco de dados que possuam determinada data de criacao.
	 * @param tipoRequerimento Class referente a classe do objeto que sera buscado
	 *                         no banco
	 * @param dataDeCriacao LocalDate a data de criacao do requerimento.
	 * @return List contendo os requerimentos que satisfazem os padroes da busca
	 */
	public List<IRequerimento> buscarRequerimentos(Class<?> tipoRequerimento, LocalDate dataDeCriacao) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<IRequerimento> cq = (CriteriaQuery<IRequerimento>) cb.createQuery(tipoRequerimento);
		Root<IRequerimento> root = (Root<IRequerimento>) cq.from(tipoRequerimento);
		return session.createQuery(cq.select((Selection<? extends IRequerimento>) cq.from(tipoRequerimento))
				.where(cb.equal(root.get("dataCriacaoRequerimento"), dataDeCriacao))).getResultList();
	}

	/**
	 * Metodo buscarRequerimento(Class<?> tipoRequerimento, int idRequerimento).
	 * 
	 * Metodo recebe uma classe e busca no banco o requerimento que possui o id especificado.
	 * @param tipoRequerimento Class referente a classe do objeto que sera buscado
	 *                         no banco
	 * @param idRequerimento o id do requerimento
	 * @return IRequerimento 
	 */
	public IRequerimento buscarRequerimento(Class<?> tipoRequerimento, int idRequerimento) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (IRequerimento) session.get(tipoRequerimento, idRequerimento);
	}

	public boolean limparTabela() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Requerimento> criteriaDelete = builder.createCriteriaDelete(Requerimento.class);
		criteriaDelete.from(Requerimento.class);
		session.createQuery(criteriaDelete).executeUpdate();
		CriteriaDelete<Ferias> criteriaDelete3 = builder.createCriteriaDelete(Ferias.class);
		criteriaDelete3.from(Ferias.class);
		session.createQuery(criteriaDelete3).executeUpdate();
		CriteriaDelete<RequerimentoSalario> criteriaDelete2 = builder.createCriteriaDelete(RequerimentoSalario.class);
		criteriaDelete2.from(RequerimentoSalario.class);
		session.createQuery(criteriaDelete2).executeUpdate();
		session.getTransaction().commit();
		return true;
	}
}

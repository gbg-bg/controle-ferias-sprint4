package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.senior.proway.ferias.OBLITERAR.AumentoDeSalario;
import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.db.DBConnection;
import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFeriasController;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoSalario;

public class RequerimentoControllerTest {

	private static RequerimentoDAO requerimentoDAO;
	private static Session session;
	private static RequerimentoController requerimentoController;
	private static FeriasDAO feriasDAO;		
	
	TiposFerias tipo = TiposFerias.PARCIAL;
	EstadoRequerimento estadoRequerimento = EstadoRequerimento.EM_ANALISE;
	LocalDate inicio = LocalDate.of(2021, 04, 01);
	LocalDate fim = LocalDate.of(2021, 04, 28);
	short diasTotais = 29;
	short diasVendidos = 0;
	LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
	LocalDate localDateSolicitacao2 = LocalDate.of(2021, 06, 03);
	
	@Before
	public void limparBanco() {
		requerimentoDAO.limparTabela();	
		feriasDAO.limparTabela();
	}
	
	@AfterClass
	public static void limparBancoFim() {
		requerimentoDAO.limparTabela();	
		feriasDAO.limparTabela();
	}
		
	@BeforeClass
	public static void iniciarInstancias() {
		session = DBConnection.getSession();
		requerimentoDAO = RequerimentoDAO.getInstance();
		requerimentoController = RequerimentoController.getInstance();
		feriasDAO = FeriasDAO.getInstance();
	}
	
	@Test
	public void testGetRequerimentosPorClasse() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		Ferias ferias2 = new Ferias(inicio, fim, diasTotais, diasVendidos, TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);		
		feriasDAO.cadastrar(ferias2);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		Requerimento requerimentoFerias2 = new Requerimento(ferias2, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 2");
		requerimentoDAO.criarRequerimento(requerimentoFerias2);
		
		AumentoDeSalario aumentoDeSalario = new AumentoDeSalario(1345.34);
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(aumentoDeSalario);
		RequerimentoSalario requerimentoSalario = new RequerimentoSalario(aumentoDeSalario, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoSalario);
		assertEquals(2, requerimentoDAO.buscarRequerimentos(Requerimento.class).size());
		
	}
		
	@Test
	public void testGetAllRequerimentos() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		Ferias ferias2 = new Ferias(inicio, fim, diasTotais, diasVendidos, TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);		
		feriasDAO.cadastrar(ferias2);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		Requerimento requerimentoFerias2 = new Requerimento(ferias2, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 2");
		requerimentoDAO.criarRequerimento(requerimentoFerias2);
		assertEquals(requerimentoController.buscarTodosOsRequerimentos(Requerimento.class).size(), 2);
	}

	@Test
	public void testGetRequerimentoPorId() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		
		Requerimento requerimentoFeriasSaida = (Requerimento) requerimentoController.buscarTodosOsRequerimentos(Requerimento.class).get(0);
		assertEquals(requerimentoFerias, requerimentoController.
				buscarRequerimentoPorId(Requerimento.class, requerimentoFeriasSaida.getIdRequerimentoFerias()));
	}

	@Test
	public void testCreateRequerimento() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoController.createRequerimento(requerimentoFerias);
		Requerimento requerimentoDB = (Requerimento) requerimentoController.buscarTodosOsRequerimentos(Requerimento.class).get(0);
		assertNotNull(requerimentoDB);
	}

	@Test
	public void testUpdateRequerimentoPorId() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		requerimentoFerias = (Requerimento) requerimentoDAO.buscarRequerimentos(Requerimento.class).get(0);
		requerimentoFerias.setIDCriadorRequerimento(56);
		requerimentoController.atualizarRequerimentoPorId(requerimentoFerias);
		Requerimento requerimentoFeriasSaida = (Requerimento) requerimentoController.buscarTodosOsRequerimentos(Requerimento.class).get(0);
		assertEquals(56, requerimentoFeriasSaida.getIDCriadorRequerimento());
	}

	@Test
	public void testDeleteRequerimento() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		requerimentoFerias = (Requerimento) requerimentoController.buscarTodosOsRequerimentos(Requerimento.class).get(0);
		requerimentoController.deleteRequerimento(requerimentoFerias);
		assertEquals(0, requerimentoDAO.buscarRequerimentos(Requerimento.class).size());
	}

	@Test
	public void testRetornarIntervaloEmDiasEntreAsDatas() {
		assertEquals(10, RequerimentoFeriasController.retornarIntervaloEmDiasEntreAsDatas(LocalDate.of(2022, 8, 5), LocalDate.of(2022, 8, 15)));
	}

	@Test
	public void testValidacaoPrazoSolicitacaoDeFerias() {
		Ferias ferias = new Ferias(LocalDate.of(2022, 8, 15), 
				fim, diasTotais, diasVendidos, tipo);
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		assertTrue(RequerimentoFeriasController.validacaoPrazoSolicitacaoDeFerias(requerimentoFerias));
	}
	
	@Test
	public void testValidacaoPrazoSolicitacaoDeFeriasInvalido() {
		Ferias ferias = new Ferias(LocalDate.now(), 
				fim, diasTotais, diasVendidos, tipo);
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		assertFalse(RequerimentoFeriasController.validacaoPrazoSolicitacaoDeFerias(requerimentoFerias));
	}
}

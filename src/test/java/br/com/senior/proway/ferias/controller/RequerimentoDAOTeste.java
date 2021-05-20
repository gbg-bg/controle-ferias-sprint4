package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.requerimento.IRequerimento;

public class RequerimentoDAOTeste {
	static RequerimentoDAO requerimentoDAO;
	static FeriasDAO feriasDAO;

	TiposFerias tipo = TiposFerias.PARCIAL;
	EstadoRequerimento estadoRequerimento = EstadoRequerimento.EM_ANALISE;
	LocalDate inicio = LocalDate.of(2021, 04, 01);
	LocalDate fim = LocalDate.of(2021, 04, 28);
	short diasTotais = 29;
	short diasVendidos = 0;
	LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
	LocalDate localDateSolicitacao2 = LocalDate.of(2021, 06, 03);
	
	@BeforeClass
	public static void createRequerimentoDAO() {
		requerimentoDAO = RequerimentoDAO.getInstance();
		feriasDAO = FeriasDAO.getInstance();
	}

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
	
	@Test
	public void testeACreate() {

		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		assertTrue(requerimentoDAO.criarRequerimento(requerimentoFerias));
	}

	@Test
	public void testeBGetId() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		Requerimento req = (Requerimento) requerimentoDAO.buscarRequerimentos(Requerimento.class).get(0);
		Requerimento requerimento = (Requerimento) requerimentoDAO.buscarRequerimento(Requerimento.class, req.getIdRequerimentoFerias());
		assertNotNull(requerimento);
	}

	@Test
	public void testeCGetAll() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		Ferias ferias2 = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias2);		
		Requerimento requerimentoFerias2 = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias2);
		List<IRequerimento> requerimentos = requerimentoDAO.buscarRequerimentos(Requerimento.class);
		assertEquals(2, requerimentos.size());
	}
	@Test
	public void testeDUpdate() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		requerimentoFerias.setEstadoRequerimento(EstadoRequerimento.APROVADO);
		requerimentoDAO.atualizarRequerimento(requerimentoFerias);
		Requerimento req = (Requerimento) requerimentoDAO.buscarRequerimentos(Requerimento.class).get(0);
		assertEquals(requerimentoDAO.buscarRequerimento(Requerimento.class, req.getIdRequerimentoFerias()).getEstadoRequerimento(), EstadoRequerimento.APROVADO);
	}

	@Test
	public void testeFBuscaRequerimentoPorEstado() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		List<IRequerimento> requerimento = requerimentoDAO.buscarRequerimentos(Requerimento.class, EstadoRequerimento.EM_ANALISE);
		assertEquals(requerimento.get(0).getEstadoRequerimento(), EstadoRequerimento.EM_ANALISE);
	}

	@Test
	public void testeFBuscaRequerimentoPorData() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		List<IRequerimento> requerimento = requerimentoDAO.buscarRequerimentos(Requerimento.class, LocalDate.now());
		assertEquals(requerimento.get(0).getDataCriacaoRequerimento(), LocalDate.now());
	}

	@Test
	public void testeHRemove() {

		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(ferias, estadoRequerimento, 13, 17,
				LocalDate.of(2056, 2, 5), NivelUrgencia.ALTO, "Requerimento teste 1");
		requerimentoDAO.criarRequerimento(requerimentoFerias);
		requerimentoDAO.deletarRequerimento(requerimentoFerias);
		List<IRequerimento> requerimentos = requerimentoDAO.buscarRequerimentos(Requerimento.class);
		assertEquals(0, requerimentos.size());
	}

}

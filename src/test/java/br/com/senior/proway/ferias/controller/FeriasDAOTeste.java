package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
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
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.ferias.IFerias;

public class FeriasDAOTeste {
	static FeriasDAO feriasDAO;
	static RequerimentoDAO requerimentoDAO;
	LocalDate dataInicio = LocalDate.of(2021, 04, 01);
	LocalDate dataFim = LocalDate.of(2021, 04, 10);
	int diasTotaisRequisitados = 10;
	int diasVendidos = 5;
	TiposFerias tipo = TiposFerias.PARCIAL;
	TiposFerias tipo2 = TiposFerias.PARCIAL;
	
	@BeforeClass
	public static void iniciarInstancias() {
		requerimentoDAO = RequerimentoDAO.getInstance();
		feriasDAO = FeriasDAO.getInstance();
	}
	
	@Before
	public void limparBanco() {
		feriasDAO.limparTabela();
		requerimentoDAO.limparTabela();
	}

	@AfterClass
	public static void limparBancoFim() {
		requerimentoDAO.limparTabela();
		feriasDAO.limparTabela();
	}

	@Test
	public void testLimparBanco() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		assertEquals(1, feriasDAO.pegarTodos().size());
		feriasDAO.limparTabela();
		assertEquals(0, feriasDAO.pegarTodos().size());
	}

	@Test
	public void testPegarTodos() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		IFerias ferias2 = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo2);
		feriasDAO.cadastrar(ferias);
		feriasDAO.cadastrar(ferias2);
		List<IFerias> listaFerias = feriasDAO.pegarTodos();
		assertEquals(2, listaFerias.size());
	}

	@Test
	public void testPegarPorID() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Ferias feriasRecebido = feriasDAO.pegarFeriasPorID(feriasDAO.pegarTodos().get(0).getId());
		assertEquals(dataInicio, feriasRecebido.getDataInicio());
	}

	@Test
	public void testCadastrar() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		assertTrue(feriasDAO.cadastrar(ferias));
	}

	@Test
	public void testAlterar() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		ferias = feriasDAO.pegarFeriasPorID(feriasDAO.pegarTodos().get(0).getId());
		ferias.setDataFim(LocalDate.now());
		assertTrue(feriasDAO.alterar(ferias));
	}

	@Test
	public void testDelete() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		feriasDAO.deletar(ferias);
		assertEquals(0, feriasDAO.pegarTodos().size());
	}

	@Test
	public void testPegarFeriasPorIDColaborador() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		IFerias ferias2 = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo2);
		IFerias ferias3 = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo2);
		ferias.setIdentificadorUsuario(0);
		ferias2.setIdentificadorUsuario(0);
		ferias3.setIdentificadorUsuario(1);
		feriasDAO.cadastrar(ferias);
		feriasDAO.cadastrar(ferias2);
		feriasDAO.cadastrar(ferias3);
		
		List<IFerias> listaFerias = feriasDAO.pegarTodasAsFeriasPorIDColaborador(0);

		assertEquals(2, listaFerias.size());
	}

	@Test
	public void testPegarTodasAsFeriasPorTipo() {

		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, TiposFerias.VENDIDA);
		IFerias ferias2 = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, TiposFerias.VENDIDA);
		IFerias ferias3 = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, TiposFerias.FRACIONADA);
		feriasDAO.cadastrar(ferias);
		feriasDAO.cadastrar(ferias2);
		feriasDAO.cadastrar(ferias3);
		List<IFerias> listaFerias = feriasDAO.pegarTodasAsFeriasPorTipo(TiposFerias.VENDIDA);

		assertEquals(2, listaFerias.size());

		feriasDAO.deletar(ferias);

	}

	@Test
	public void testPegarTodasAsFeriasPorDataInicio() {
		IFerias ferias = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		IFerias ferias2 = new Ferias(dataInicio, dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		IFerias ferias3 = new Ferias(LocalDate.of(2022, 01, 19), dataFim, diasTotaisRequisitados, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		feriasDAO.cadastrar(ferias2);
		feriasDAO.cadastrar(ferias3);
		List<IFerias> listaFerias = feriasDAO.pegarTodasAsFeriasPorDataInicio(dataInicio);
		assertEquals(2, listaFerias.size());
	}
}

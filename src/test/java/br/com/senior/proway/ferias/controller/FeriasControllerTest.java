package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.FeriasBuilder;
import br.com.senior.proway.ferias.model.entity.FeriasDirector;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeriasControllerTest {

	private static FeriasController feriasController;
	private static FeriasDAO feriasDAO;		
	private static RequerimentoDAO requerimentoDAO;		
	
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
		feriasController = FeriasController.getInstance();
		feriasDAO = FeriasDAO.getInstance();
		requerimentoDAO = RequerimentoDAO.getInstance();
	}
		
	@Test
	public void AtestCadastrar() throws SQLException {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasParcial(builder, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 22), (short) 30);
		Ferias ferias2 = builder.build(30);
		feriasController.cadastrar(ferias2);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(feriasController.pegarTodos().get(0).getId());
		assertEquals(8, feriasdb.getDiasVendidos());
	}

	@Test
	public void BtestAlterar() throws SQLException {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasParcial(builder, LocalDate.of(2221, 04, 01), LocalDate.of(2221, 04, 10), (short) 30);
		Ferias ferias2 = builder.build(30);
		feriasController.cadastrar(ferias2);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(feriasController.pegarTodos().get(0).getId());
		feriasdb.setIdentificadorUsuario(7);
		feriasController.alterar(feriasdb);
		
		assertEquals(7, feriasController.pegarFeriasPorId(feriasController.pegarTodos().get(0).getId()).getIdentificadorUsuario());
	}

	@Test
	public void CtestPegarTodos() throws SQLException {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		assertEquals(feriasController.pegarTodos().size(), 1);
		
	}

	@Test
	public void DtestPegarFeriasPorId() throws SQLException {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasParcial(builder, LocalDate.of(2221, 04, 01), LocalDate.of(2221, 04, 10), (short) 30);
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);
		assertEquals(ferias, feriasController.pegarFeriasPorId(feriasController.pegarTodos().get(0).getId()));
	}
	
	@Test
	public void EtestCPegarFeriasPorTipo() {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasTotal(builder, LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 30));
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);
		assertEquals(0, feriasController.pegarTodasAsFeriasPorTipo(TiposFerias.PARCIAL).size());
	}
	
	@Test
	public void FtestPegarFeriasPorData() {
		assertEquals(0, feriasController.pegarTodasAsFeriasPorDataInicio(LocalDate.of(2221, 04, 01)).size());
	}

	@Test
	public void GtestDeletar() throws SQLException {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasTotal(builder, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 31));
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);
		feriasController.deletar(ferias);
		assertEquals(0, feriasController.pegarTodos().size());
	}
	
	@Test
	public void HtestCadastrarFracionada() {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasFracionada(builder, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 15));
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(feriasController.pegarTodos().get(0).getId());
		assertEquals(15, feriasdb.getDiasTotaisRequisitados());
				
	}
		
	@Test
	public void ItestCadastrarVendida() throws SQLException {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasVendida(builder, (short) 5);
		Ferias ferias = builder.build(5);
		feriasController.cadastrar(ferias);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(feriasController.pegarTodos().get(0).getId());
		assertEquals(5, feriasdb.getDiasVendidos());
	}
	@Test
	public void JtestCadastrarVendidaEspecifica() throws SQLException {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasVendidaEspecifica(builder, (short) 7);
		Ferias ferias = builder.build(7);
		feriasController.cadastrar(ferias);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(feriasController.pegarTodos().get(0).getId());
		assertEquals(7, feriasdb.getDiasVendidos());
	}
	@Test
	public void KtestPegarFeriasPorIdColaborador() throws SQLException {
		assertEquals(0, feriasController.pegarTodasAsFeriasPorIDColaborador(0).size());
	}

}

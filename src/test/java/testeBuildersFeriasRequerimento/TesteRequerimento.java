package testeBuildersFeriasRequerimento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.controller.FeriasRequerimentoController;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.RequerimentoBuilder;
import br.com.senior.proway.ferias.model.RequerimentoDirector;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;

public class TesteRequerimento {

	@Test
	public void testAtualizarEstadoRequisicao() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 1);
		LocalDate data2 = LocalDate.of(2021, 4, 16);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasTotal(Bob, data1, data2);
		Ferias ferias = Bob.build(creditos);
		
		RequerimentoDirector requerimentoDirector = new RequerimentoDirector();
		RequerimentoBuilder requerimentoBuilder = new RequerimentoBuilder();
		requerimentoDirector.createRequerimento(requerimentoBuilder, ferias, "IdentificardorUsuario123");
		FeriasRequerimento feriasRequerimento = requerimentoBuilder.build();
		
		assertTrue(feriasRequerimento.getEstadoRequisicao() == EstadosRequisicao.EM_ANALISE);
		
		FeriasRequerimentoController controllerReq = new FeriasRequerimentoController();
		controllerReq.atualizarEstadoRequisicao(EstadosRequisicao.APROVADO, feriasRequerimento);
		
		assertTrue(feriasRequerimento.getEstadoRequisicao() == EstadosRequisicao.APROVADO);
	}
	
	@Test
	public void testValidarDataSolicitacaoComDataInicioFerias() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 5, 06);
		LocalDate data2 = LocalDate.of(2021, 5, 16);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasTotal(Bob, data1, data2);
		Ferias ferias = Bob.build(creditos);
		
		RequerimentoDirector requerimentoDirector = new RequerimentoDirector();
		RequerimentoBuilder requerimentoBuilder = new RequerimentoBuilder();
		requerimentoDirector.createRequerimento(requerimentoBuilder, ferias, "IdentificardorUsuario123");
		FeriasRequerimento feriasRequerimento = requerimentoBuilder.build();
		
		FeriasRequerimentoController controllerReq = new FeriasRequerimentoController();
		boolean validacao = controllerReq.validacaoPrazoSolicitacaoDeFerias(data1, feriasRequerimento);
		
		assertEquals(EstadosRequisicao.INVALIDO, feriasRequerimento.getEstadoRequisicao());
		assertFalse(validacao);
	}
}

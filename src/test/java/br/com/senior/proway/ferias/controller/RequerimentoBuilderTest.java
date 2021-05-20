package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.requerimento.builder.RequerimentoBuilder;

@Ignore
public class RequerimentoBuilderTest {
	
	TiposFerias tipo = TiposFerias.PARCIAL;
	EstadoRequerimento estadoRequerimento = EstadoRequerimento.EM_ANALISE;
	LocalDate inicio = LocalDate.of(2021, 04, 01);
	LocalDate fim = LocalDate.of(2021, 04, 28);
	short diasTotais = 29;
	short diasVendidos = 0;
	LocalDate solicitacao = LocalDate.of(2021, 05, 03);
	LocalDate solicitacao2 = LocalDate.of(2021, 06, 03);

	
	public RequerimentoBuilder requerimentoBuilder;

	@Test
	public void testBuild() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		
//		requerimento.setId(12);
//		RequerimentoFerias resultado = requerimentoBuilder.build();
//		assertTrue(resultado.equals(requerimento));
	}
	
	@Test
	public void testBuildFeriasInvalidas() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, TiposFerias.INVALIDA);
		requerimentoBuilder.setFeriasRequisitadas(ferias);
		requerimentoBuilder.setEstadoRequisicao(estadoRequerimento);
		requerimentoBuilder.setDataSolicitacao(solicitacao);
		requerimentoBuilder.setID(12);
		Requerimento resultado = requerimentoBuilder.build();
//		assertEquals(resultado.getEstadoRequisicao(), EstadoRequerimento.INVALIDO);
	}
	
	@Before
	public void resetarInstancias() {
		requerimentoBuilder = new RequerimentoBuilder();
	}

	@Test
	public void testGetSetEstadoRequisicao() {
		requerimentoBuilder.setEstadoRequisicao(EstadoRequerimento.APROVADO);
		assertEquals(EstadoRequerimento.APROVADO, requerimentoBuilder.getEstadoRequisicao());
	}

	@Test
	public void testGetSetDataSolicitacao() {
		requerimentoBuilder.setDataSolicitacao(LocalDate.of(2023, 5, 6));
		assertEquals(LocalDate.of(2023, 5, 6), requerimentoBuilder.getDataSolicitacao());
	}

	@Test
	public void testGetSetIdentificadorUsuario() {
		requerimentoBuilder.setID(3);
		assertEquals(3, requerimentoBuilder.getID());
	}

	@Test
	public void testGetSetFeriasRequisitadas() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		requerimentoBuilder.setFeriasRequisitadas(ferias);
		assertEquals(ferias, requerimentoBuilder.getFeriasRequisitadas());
	}
}

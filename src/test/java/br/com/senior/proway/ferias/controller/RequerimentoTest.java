package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;

public class RequerimentoTest {

	@Test
	public void testGetSetId() {
		Requerimento requerimento = new Requerimento();
		requerimento.setIdRequerimentoFerias(0);
		assertEquals(0, requerimento.getIdRequerimentoFerias());
	}

	@Test
	public void testGetSetObjetoRequerimento() {
		Requerimento requerimento = new Requerimento();
		Ferias ferias = new Ferias();
		requerimento.setObjetoRequerimento(ferias);
		assertEquals(ferias, requerimento.getObjetoRequerimento());
	}

	@Test
	public void testGetSetEstadoRequisicao() {
		Requerimento requerimento = new Requerimento();
		requerimento.setEstadoRequerimento(EstadoRequerimento.REPROVADO);
		assertEquals(EstadoRequerimento.REPROVADO, requerimento.getEstadoRequerimento());
	}

	@Test
	public void testGetSetDataSolicitacao() {
		Requerimento requerimento = new Requerimento();
		requerimento.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		assertEquals(LocalDate.of(2200, 10, 23), requerimento.getDataCriacaoRequerimento());
	}

	@Test
	public void testEquals() {
		Requerimento requerimento = new Requerimento();
		Requerimento requerimento2 = new Requerimento();
		requerimento.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		requerimento2.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		assertTrue(requerimento.getDataCriacaoRequerimento().equals(requerimento2.getDataCriacaoRequerimento()));
	}
	
	@Test
	public void testEqualsIncorreto() {
		Requerimento requerimento = new Requerimento();
		Requerimento requerimento2 = new Requerimento();
		requerimento.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		requerimento2.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 24));
		assertNotEquals(requerimento, requerimento2);
	}

}

package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.FeriasBuilder;
import br.com.senior.proway.ferias.model.entity.FeriasDirector;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

public class FeriasTest {

	@Test
	public void feriasTotal() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 15);
		LocalDate data2 = LocalDate.of(2021, 5, 14); // 30 dias
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasTotal(feriasBuilder, data1, data2);
		Ferias ferias = feriasBuilder.build(creditos);
		
		assertEquals(ferias.getTipo(), TiposFerias.TOTAL);
		assertEquals(0, ferias.getDiasVendidos());
	}
	
	@Test
	public void feriasParcial() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2221, 4, 11);
		LocalDate data2 = LocalDate.of(2221, 4, 30); // 20 dias de ferias
		// lembrando que CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 15; <- creditos que restariam
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasParcial(feriasBuilder, data1, data2, creditos);
		Ferias ferias = feriasBuilder.build(creditos);
		
		assertEquals(TiposFerias.PARCIAL, ferias.getTipo());
		assertEquals(10, ferias.getDiasVendidos());
	}
	
	@Test
	public void feriasFracionada() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 16);
		LocalDate data2 = LocalDate.of(2021, 5, 1); //15 dias 
		// lembrando que CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 15; 
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasFracionada(feriasBuilder, data1, data2);
		Ferias ferias = feriasBuilder.build(creditos);

		assertEquals(0, ferias.getDiasVendidos());
		assertEquals(TiposFerias.FRACIONADA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendida() {
		short creditos = 30;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasVendida(feriasBuilder, creditos);
		Ferias ferias = feriasBuilder.build(creditos);
		
		assertEquals(creditos, ferias.getDiasVendidos());
		assertEquals(TiposFerias.VENDIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendidaEspecifica() {
		short creditos = 30;
		short queroVender = 25;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasVendidaEspecifica(feriasBuilder, queroVender);
		Ferias ferias = feriasBuilder.build(creditos);
		
		assertEquals(queroVender, ferias.getDiasVendidos());
		assertEquals(TiposFerias.VENDIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendidaAcimaDoLimite() {
		short creditos = 45;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasVendida(feriasBuilder, creditos);
		Ferias ferias = feriasBuilder.build(creditos);

		assertNotEquals(creditos, ferias.getDiasVendidos());
		assertEquals(TiposFerias.VENDIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasInvalida_DataInicioPosteriorADataFim() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 6, 15);
		LocalDate data2 = LocalDate.of(2021, 5, 15);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasTotal(feriasBuilder, data1, data2);
		Ferias ferias = feriasBuilder.build(creditos);
		
		assertEquals(TiposFerias.INVALIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasInvalida_SemCreditosSuficientes() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 15);
		LocalDate data2 = LocalDate.of(2021, 12, 15);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasFracionada(feriasBuilder, data1, data2);
		Ferias ferias = feriasBuilder.build(creditos);
		
		assertEquals(TiposFerias.INVALIDA, ferias.getTipo());
	}
}

package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Dominio.Equipo;
import Dominio.Partido;
import Dominio.Torneo;
import Enums.TipoResultado;
import Excepciones.EquipoDuplicadoException;
import Excepciones.PartidoJugadoException;
import Excepciones.PartidoNoExiste;

public class test {

	@Test
	public void queSePuedaCrearUnTorneoCon32Equipos() {
		
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Integer valorEsperado  = 32; 
		Integer valorDevuelto = mundial.getEquipos().size();
		
		assertEquals(valorEsperado,valorDevuelto);
		
		
	}
	
	@Test
	public void queSePuedaCrearUnPartidoDeGruposConDosEquiposDelMismoGrupo() {
		
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		Equipo equipoVisitante = mundial.getEquipos().get(1);//ECUADOR
		
		try {
			mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		} catch (PartidoJugadoException e) {
			e.printStackTrace();
		} catch (EquipoDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer  partidosJugados = mundial.getPartidos().size();
		
		
		
		assertEquals((Integer)1 , partidosJugados);
		
	}
	
	
	// Este test se tiene que hacer al final. Fase eliminatorias
	@Test
	public void queSePuedaCrearUnPartidoDeEliminatoriasConDosEquiposPertenecientesALaListaDeEquiposEnEliminatorias() {
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
	}
	
	
	
	@Test	(expected= PartidoJugadoException.class)
	public void queAlCrearUnPartidoDondeLosEquiposYaJugaronSeLanceUnaPartidoJugadoException() throws PartidoJugadoException, EquipoDuplicadoException{
		
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		Equipo equipoVisitante = mundial.getEquipos().get(1);//ECUADOR
		
		mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		
	}
	
	@Test (expected= EquipoDuplicadoException.class)
	public void queAlCrearUnPartidoDeGruposDondeElEquipoLocalEsElMismoQueElEquipoRivalSeLanceUnaEquipoDuplicadoException() throws PartidoJugadoException, EquipoDuplicadoException {
		
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		
		mundial.registrarPartido(1, equipoLocal,equipoLocal);
		
		
	}
	
	@Test
	public void queAlRegistrarElResultadoDeUnPartidoDeGruposSeAcumulenLosPuntosCorrespondientesACadaEquipo() {

		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		Equipo equipoVisitante = mundial.getEquipos().get(1);//ECUADOR
		
		try {
			mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		} catch (PartidoJugadoException e) {
			e.printStackTrace();
		} catch (EquipoDuplicadoException e) {
			
			e.printStackTrace();
		}
		mundial.registrarResultado(1, 0, 2);
		
		Integer puntosLocal = equipoLocal.getPuntos();
		Integer puntosVisitante = equipoVisitante.getPuntos();
		
		assertEquals((Integer)0, puntosLocal);
		assertEquals((Integer)3, puntosVisitante);
	}
	
	@Test
	public void queAlObtenerElResultadoDeUnPartidoDeGruposSeaElElementoEmpateDelEnum() {
		
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		Equipo equipoVisitante = mundial.getEquipos().get(1);//ECUADOR
		
		try {
			mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		} catch (PartidoJugadoException e) {
			e.printStackTrace();
		} catch (EquipoDuplicadoException e) {
			
			e.printStackTrace();
		}
		mundial.registrarResultado(1, 2, 2);
		
		TipoResultado resultado = null;
		try {
			resultado = mundial.obtenerResultado(1);
		} catch (PartidoNoExiste e) {
			e.printStackTrace();
		}
		
		assertEquals(TipoResultado.EMPATE, resultado);
	}
	
	@Test
	public void queAlObtenerElResultadoDeUnPartidoDeEliminatoriasEnCasoDeEmpateSeObtengaElEnumDelGanadorPorPenales() {
		

		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		Equipo equipoVisitante = mundial.getEquipos().get(1);//ECUADOR
		
		try {
			mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		} catch (PartidoJugadoException e) {
			e.printStackTrace();
		} catch (EquipoDuplicadoException e) {
			
			e.printStackTrace();
		}
		
		mundial.registrarResultado(1, 2, 2, 4, 2);
		
		TipoResultado resultado = null;
		try {
			resultado = mundial.obtenerResultado(1);
		} catch (PartidoNoExiste e) {
			e.printStackTrace();
		}
		assertEquals(TipoResultado.GANA_LOCAL, resultado);
	}
	
	@Test 
	public void queAlConsultarPuntajeDeEquiposDeLosGrupoSeObtenganLosEquiposOrdenadosPorGrupoAscendenteYPorPuntosDescendentemente() {
		
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		Equipo equipoVisitante = mundial.getEquipos().get(1);//ECUADOR
		
		try {
			mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		} catch (PartidoJugadoException e) {
			e.printStackTrace();
		} catch (EquipoDuplicadoException e) {
			
			e.printStackTrace();
		}
		mundial.registrarResultado(1,0, 2);
		
		mundial.ordenarTablasPorPuntaje();
		
		ArrayList<Equipo> equipos = (ArrayList<Equipo>) mundial.getEquiposOrdenados();
				
		assertEquals(mundial.getEquiposOrdenados().get(0), equipoVisitante);
		
	}
	
	@Test
	public void queAlFinalizarLaFaseDeGruposSeAgreguenLosMejores2EquiposDeCadaGrupoALaColeccionDeEquiposEnEliminatorias() {
		Torneo mundial = new Torneo();
		mundial.iniciarTorneo();
		
		Equipo equipoLocal = mundial.getEquipos().get(0);// QATAR
		Equipo equipoVisitante = mundial.getEquipos().get(1);//ECUADOR
		
		try {
			mundial.registrarPartido(1, equipoLocal, equipoVisitante);
		} catch (PartidoJugadoException e) {
			e.printStackTrace();
		} catch (EquipoDuplicadoException e) {
			
			e.printStackTrace();
		}
		mundial.registrarResultado(1,0, 2);
		
		mundial.finalizarFaseDeGrupos();
		
		Integer cantidadDeEquipos = mundial.getEquiposEnEliminatorias().size();
		
		
		assertEquals((Integer)16, cantidadDeEquipos);
		
	}
	
}

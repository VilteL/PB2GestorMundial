package Dominio;


import java.util.*;

import Enums.Grupos;
import Enums.Selecciones;
import Enums.TipoResultado;
import Excepciones.EquipoDuplicadoException;
import Excepciones.PartidoJugadoException;
import Excepciones.PartidoNoExiste;

public class Torneo {
	
	private List<Equipo> equipos;
	private	List<Partido> partidos;
	private List<Equipo> equiposOrdenados;
	private List<Equipo> equiposEnEliminatorias;
 	
	
	public Torneo() {
		this.equipos = new ArrayList<>();
		this.partidos = new ArrayList<>();
		this.equiposOrdenados= new ArrayList<>();
		this.equiposEnEliminatorias= new ArrayList<>();
	}
	
	public void iniciarTorneo() {
		
		Selecciones[] selecciones = Selecciones.values();
		Grupos[] grupos = Grupos.values();
		
		for (Selecciones seleccion : selecciones) {
			Equipo nuevoEquipo = new Equipo(seleccion.toString());
			this.equipos.add(nuevoEquipo);
		}
		/*
		for (Grupos grupo : grupos) {
			for (int i = 0; i < 4; i++) {
				this.equipos.get(i).setLetraGrupo(grupo.toString());
			}
		}*/
		
		asignarGrupos();
		
	}
	
	//Pendiente de automatizar
	private void asignarGrupos() {
		this.equipos.get(0).setLetraGrupo("A");
		this.equipos.get(1).setLetraGrupo("A");
		this.equipos.get(2).setLetraGrupo("A");
		this.equipos.get(3).setLetraGrupo("A");
		this.equipos.get(4).setLetraGrupo("B");
		this.equipos.get(5).setLetraGrupo("B");
		this.equipos.get(6).setLetraGrupo("B");
		this.equipos.get(7).setLetraGrupo("B");
		this.equipos.get(8).setLetraGrupo("C");
		this.equipos.get(9).setLetraGrupo("C");
		this.equipos.get(10).setLetraGrupo("C");
		this.equipos.get(11).setLetraGrupo("C");
		this.equipos.get(12).setLetraGrupo("D");
		this.equipos.get(13).setLetraGrupo("D");
		this.equipos.get(14).setLetraGrupo("D");
		this.equipos.get(15).setLetraGrupo("D");
		this.equipos.get(16).setLetraGrupo("E");
		this.equipos.get(17).setLetraGrupo("E");
		this.equipos.get(18).setLetraGrupo("E");
		this.equipos.get(19).setLetraGrupo("E");
		this.equipos.get(20).setLetraGrupo("F");
		this.equipos.get(21).setLetraGrupo("F");
		this.equipos.get(22).setLetraGrupo("F");
		this.equipos.get(23).setLetraGrupo("F");
		this.equipos.get(24).setLetraGrupo("G");
		this.equipos.get(25).setLetraGrupo("G");
		this.equipos.get(26).setLetraGrupo("G");
		this.equipos.get(27).setLetraGrupo("G");
		this.equipos.get(28).setLetraGrupo("H");
		this.equipos.get(29).setLetraGrupo("H");
		this.equipos.get(30).setLetraGrupo("H");
		this.equipos.get(31).setLetraGrupo("H");
	}

	
	
	
	
	public List<Equipo> getEquipos() {
		return equipos;
	}
	public List<Partido> getPartidos() {
		return partidos;
	}
	
	//Se agrego la excepcion, originalente no se pedia.
	public TipoResultado obtenerResultado(Integer idPartido) throws PartidoNoExiste {
		for (Partido partido : partidos) {
			if(partido.getIdPartido().equals(idPartido)) {
				return partido.getResultado();
			}
		}
		throw new PartidoNoExiste();
	}
	
	public void registrarPartido(Integer idPartido,Equipo local, Equipo visitante) throws PartidoJugadoException, EquipoDuplicadoException {
		
		verificarQueNoSeanElMismoEquipo(local, visitante);
		
		Partido nuevoPartido = new Partido(idPartido, local,visitante);
		
		
		for (Partido partido : partidos ){
			if(local.getLetraGrupo().equals(visitante.getLetraGrupo())) {
				if(partido.getEquipoLocal().getNombre().equals(local.getNombre())&&
						partido.getEquipoVisitante().getNombre().equals(visitante.getNombre())||
						partido.getEquipoLocal().getNombre().equals(visitante.getNombre())
						&&partido.getEquipoVisitante().getNombre().equals(local.getNombre())) {
					throw new PartidoJugadoException();
				}
						
			}
			
		}
		this.partidos.add(nuevoPartido);
		
		
	}
	
	private void verificarQueNoSeanElMismoEquipo(Equipo local, Equipo visitante) throws EquipoDuplicadoException {
		if(local.getNombre().equals(visitante.getNombre())) {
			throw new EquipoDuplicadoException();
		}
	}
	public void registrarResultado(Integer idPartido, Integer golesLocal, Integer golesVisitante) {
		
		for (Partido partido : partidos) {
			if(partido.getIdPartido().equals(idPartido)) {
				partido.setGolesEquipoLocal(golesLocal);
				partido.setGolesEquipoVisitante(golesVisitante);
				
				TipoResultado resultado = saberQuienGano(golesLocal, golesVisitante);
				partido.setResultado(resultado);
				
				sumarPuntaje(resultado, idPartido);
				agregarGolesAFavorYEnContra(partido, golesLocal, golesVisitante);
			}
		}
	}
	
	private TipoResultado saberQuienGano(Integer golesLocal, Integer golesVisitante) {
		if(golesLocal>golesVisitante) {
			return TipoResultado.GANA_LOCAL;
		}
		else if (golesLocal<golesVisitante) {
			return TipoResultado.GANA_VISITANTE;
		}
		
		return TipoResultado.EMPATE;
	}
	
	private void sumarPuntaje(TipoResultado resultado, Integer idPartido) {
		
		Partido partidoEncontrado = null;
		
		for (Partido partido : partidos) {
			if(partido.getIdPartido().equals(idPartido)) {
				partidoEncontrado = partido;
			}
		}
		
		if(partidoEncontrado!=null) {
		
		Equipo equipoLocal = partidoEncontrado.getEquipoLocal();
		Equipo equipoVisitante = partidoEncontrado.getEquipoVisitante();
			
		switch (resultado) {
		case EMPATE:
				equipoLocal.setPuntos(equipoLocal.getPuntos()+1);
				equipoVisitante.setPuntos(equipoVisitante.getPuntos()+1);
			break;
		case GANA_LOCAL:
				equipoLocal.setPuntos(equipoLocal.getPuntos()+3);
			break;
		case GANA_VISITANTE:
				equipoVisitante.setPuntos(equipoVisitante.getPuntos()+3);
			break;
			}
		}
	}
	
	private void agregarGolesAFavorYEnContra(Partido partido, Integer golesLocal, Integer golesVisitante) {
		
		Equipo local = partido.getEquipoLocal();
		Equipo visitante = partido.getEquipoVisitante();
		
		local.setGolesAFavor(local.getGolesAFavor()+golesLocal);
		visitante.setGolesAFavor(visitante.getGolesAFavor()+golesVisitante);
		
		local.setGolesEnContra(local.getGolesEnContra()-golesVisitante);
		visitante.setGolesEnContra(visitante.getGolesEnContra()-golesLocal);
		
	}
	
	
	
	public void registrarResultado(Integer idPartido, Integer golesLocal, Integer golesVisitante, Integer penalesConvertidosLocal, Integer penalesConvertidosVisitante) {
		
		TipoResultado resultado = null; 
		Partido partidoEncontrado = null;
		
		
		for (Partido partido : partidos) {
			if(partido.getIdPartido().equals(idPartido)) {
				partido.setGolesEquipoLocal(golesLocal);
				partido.setGolesEquipoVisitante(golesVisitante);
				
				resultado = saberQuienGano(golesLocal, golesVisitante);
				
				partidoEncontrado = partido;
			}
		}
		
		if(resultado.equals(TipoResultado.EMPATE)) {
			TipoResultado resultadoDePenales = quienGanoPenales(penalesConvertidosLocal, penalesConvertidosVisitante);
			partidoEncontrado.setResultado(resultadoDePenales);
			
			sumarPuntaje(resultadoDePenales, idPartido);
			Integer sumaDeGolesConvertidosLocal = golesLocal + penalesConvertidosLocal;
			Integer sumaDeGolesConvertidosVisitante = golesVisitante + penalesConvertidosVisitante;
			
			agregarGolesAFavorYEnContra(partidoEncontrado, sumaDeGolesConvertidosLocal, sumaDeGolesConvertidosVisitante);
			
			
		}
	}
	
	private TipoResultado quienGanoPenales(Integer penalesConvertidosLocal , Integer penalesConvertidosVisitante) {
		if(penalesConvertidosLocal>penalesConvertidosVisitante) 
			return TipoResultado.GANA_LOCAL;
		if(penalesConvertidosLocal<penalesConvertidosVisitante)
			return TipoResultado.GANA_VISITANTE;
		return null;
	}
	
	public void ordenarTablasPorPuntaje() {
		this.equiposOrdenados = new ArrayList<>(this.equipos);
		Collections.sort(this.equiposOrdenados);
		
		
		
	}

	public List<Equipo> getEquiposOrdenados() {
		return equiposOrdenados;
	}
	
	public void finalizarFaseDeGrupos() {
		ordenarTablasPorPuntaje();
		ArrayList<Integer> indiceEquiposQuePasan = new ArrayList<>(Arrays.asList(0,1,4,5,8,9,12,13,16,17,20,21,24,25,28,29));
		for (Integer index : indiceEquiposQuePasan) {
			this.equiposEnEliminatorias.add(this.equiposOrdenados.get(index));
		}
		
	}

	public List<Equipo> getEquiposEnEliminatorias() {
		return equiposEnEliminatorias;
	}

	
}

package Dominio;

import Enums.TipoResultado;

public class Partido {
	
	private Integer idPartido;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private TipoResultado Resultado; 
	private Integer golesEquipoLocal;
	private Integer golesEquipoVisitante;
	
	public Partido(Integer idPartido,Equipo equipoLocal, Equipo equipoVisitante) {
		this.equipoLocal= equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.idPartido=idPartido;		
	}

	public void setResultado(TipoResultado resultado) {
		Resultado = resultado;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public TipoResultado getResultado() {
		return Resultado;
	}

	public Integer getGolesEquipoLocal() {
		return golesEquipoLocal;
	}

	public void setGolesEquipoLocal(Integer golesEquipoLocal) {
		this.golesEquipoLocal = golesEquipoLocal;
	}

	public Integer getGolesEquipoVisitante() {
		return golesEquipoVisitante;
	}

	public void setGolesEquipoVisitante(Integer golesEquipoVisitante) {
		this.golesEquipoVisitante = golesEquipoVisitante;
	}
	
	
}

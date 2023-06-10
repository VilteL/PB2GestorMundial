package Dominio;

import java.util.Objects;

public class Equipo implements Comparable<Equipo>{
	
	private String nombre; 
	private Integer puntos;
	private String letraGrupo;
	
	public Equipo(String nombre) {
		this.nombre = nombre;
		this.puntos = 0;
	}

	
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}


	public void setLetraGrupo(String letraGrupo) {
		this.letraGrupo = letraGrupo;
	}


	public String getNombre() {
		return nombre;
	}


	public Integer getPuntos() {
		return puntos;
	}


	public String getLetraGrupo() {
		return letraGrupo;
	}


	
	
	@Override
	public int hashCode() {
		return Objects.hash(letraGrupo, puntos);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(letraGrupo, other.letraGrupo) && Objects.equals(puntos, other.puntos);
	}


	@Override
	public int compareTo(Equipo o) {
		if(this.letraGrupo.equals(o.letraGrupo)) {
			return -this.puntos.compareTo(o.puntos);
		}
		return this.letraGrupo.compareToIgnoreCase(o.letraGrupo);
	
	}


	
	
	
}

package com.googleActRecognDataServer.api.cassandra.pojos;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table("cliente_actividad")
public class Baliza {

	@PrimaryKeyColumn(name = "etiqueta_fecha", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	public String etiquetaFecha;
	@PrimaryKeyColumn(name = "adv_id_cliente", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	public String advIdCliente;
	@PrimaryKeyColumn(name = "ins_id_cliente", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	public String insIdCliente;
	@PrimaryKeyColumn(name = "timestamp", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
	public Date timestamp;
	@Column
	public String actividad;
	@Column
	public String bandera;
	@Column
	public long duracion;

	public Baliza(String etiquetaFecha, String advIdCliente, String insIdCliente, Date timestamp, String actividad,
			String bandera, long duracion) {
		super();
		this.etiquetaFecha = etiquetaFecha;
		this.advIdCliente = advIdCliente;
		this.insIdCliente = insIdCliente;
		this.timestamp = timestamp;
		this.actividad = actividad;
		this.bandera = bandera;
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Baliza [etiquetaFecha=" + etiquetaFecha + ", advIdCliente=" + advIdCliente + ", insIdCliente="
				+ insIdCliente + ", timestamp=" + timestamp + ", actividad=" + actividad + ", bandera=" + bandera
				+ ", duracion=" + duracion + "]";
	}

	public String getEtiquetaFecha() {
		return etiquetaFecha;
	}

	public void setEtiquetaFecha(String etiquetaFecha) {
		this.etiquetaFecha = etiquetaFecha;
	}

	public String getAdvIdCliente() {
		return advIdCliente;
	}

	public void setAdvIdCliente(String advIdCliente) {
		this.advIdCliente = advIdCliente;
	}

	public String getInsIdCliente() {
		return insIdCliente;
	}

	public void setInsIdCliente(String insIdCliente) {
		this.insIdCliente = insIdCliente;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actividad == null) ? 0 : actividad.hashCode());
		result = prime * result + ((advIdCliente == null) ? 0 : advIdCliente.hashCode());
		result = prime * result + ((bandera == null) ? 0 : bandera.hashCode());
		result = prime * result + (int) (duracion ^ (duracion >>> 32));
		result = prime * result + ((etiquetaFecha == null) ? 0 : etiquetaFecha.hashCode());
		result = prime * result + ((insIdCliente == null) ? 0 : insIdCliente.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Baliza other = (Baliza) obj;
		if (actividad == null) {
			if (other.actividad != null)
				return false;
		} else if (!actividad.equals(other.actividad))
			return false;
		if (advIdCliente == null) {
			if (other.advIdCliente != null)
				return false;
		} else if (!advIdCliente.equals(other.advIdCliente))
			return false;
		if (bandera == null) {
			if (other.bandera != null)
				return false;
		} else if (!bandera.equals(other.bandera))
			return false;
		if (duracion != other.duracion)
			return false;
		if (etiquetaFecha == null) {
			if (other.etiquetaFecha != null)
				return false;
		} else if (!etiquetaFecha.equals(other.etiquetaFecha))
			return false;
		if (insIdCliente == null) {
			if (other.insIdCliente != null)
				return false;
		} else if (!insIdCliente.equals(other.insIdCliente))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	public String cogerIdsConcatenados() {
		return this.advIdCliente + "::" + this.insIdCliente;
	}

}

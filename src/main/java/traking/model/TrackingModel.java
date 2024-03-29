package traking.model;

import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@XmlRootElement(name = "TrakingModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackingModel {
	@XmlElement
	private Long id;
	@XmlElement
	private String matricula;
	@XmlElement
	private String pais;
	@XmlElement
	private Coordenadas coordenadas;
	@XmlElement
	private Date timestamp;
	
	
	public TrackingModel() {
		super();
	}

	
	public TrackingModel(Long id, String matricula, String pais, Coordenadas coordenadas, Date timestamp) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.pais = pais;
		this.coordenadas = coordenadas;
		this.timestamp = timestamp;
	}

	public TrackingModel(String matricula, String pais, Coordenadas coordenadas, Date timestamp) {
		super();
		this.matricula = matricula;
		this.pais = pais;
		this.coordenadas = coordenadas;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}

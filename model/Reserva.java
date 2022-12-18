package model;

import java.time.LocalDateTime;

public class Reserva {
	private String codigoLivro;
	private LocalDateTime dataReserva;
	
	
	
	public Reserva(String codigoLivro) {
		super();
		this.codigoLivro = codigoLivro;
		this.dataReserva = LocalDateTime.now();
	}
	public String getCodigoLivro() {
		return codigoLivro;
	}
	public void setCodigoLivro(String codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	public LocalDateTime getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(LocalDateTime dataReserva) {
		this.dataReserva = dataReserva;
	}
	
	
	
}

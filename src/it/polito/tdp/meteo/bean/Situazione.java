package it.polito.tdp.meteo.bean;

import java.time.LocalDate;
import java.util.Date;

public class Situazione {
	
	private String localita ; // città
	private LocalDate data ; 
	private int tMedia ; // in °C
	private int tMin ; // in °C
	private int tMax ; // in °C
	private int puntoRugiada ; // in °C
	private int umidita ; // in %
	private int visibilita ; // in km
	private int ventoMedia ; // in km/h
	private int ventoMax ; // in km/h
	private int raffica ; // in km/h
	private int pressioneSLM ; // in millibar
	private int pressioneMedia ; // in millibar
	private int pioggia ; // in mm
	private String fenomeni ; // descrizione, parole separate da spazi
	
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getTMedia() {
		return tMedia;
	}
	public void setTMedia(int tMedia) {
		this.tMedia = tMedia;
	}
	public int getTMin() {
		return tMin;
	}
	public void setTMin(int tMin) {
		this.tMin = tMin;
	}
	public int getTMax() {
		return tMax;
	}
	public void setTMax(int tMax) {
		this.tMax = tMax;
	}
	public int getPuntoRugiada() {
		return puntoRugiada;
	}
	public void setPuntoRugiada(int puntoRugiada) {
		this.puntoRugiada = puntoRugiada;
	}
	public int getUmidita() {
		return umidita;
	}
	public void setUmidita(int umidita) {
		this.umidita = umidita;
	}
	public int getVisibilita() {
		return visibilita;
	}
	public void setVisibilita(int visibilita) {
		this.visibilita = visibilita;
	}
	public int getVentoMedia() {
		return ventoMedia;
	}
	public void setVentoMedia(int ventoMedia) {
		this.ventoMedia = ventoMedia;
	}
	public int getVentoMax() {
		return ventoMax;
	}
	public void setVentoMax(int ventoMax) {
		this.ventoMax = ventoMax;
	}
	public int getRaffica() {
		return raffica;
	}
	public void setRaffica(int raffica) {
		this.raffica = raffica;
	}
	public int getPressioneSLM() {
		return pressioneSLM;
	}
	public void setPressioneSLM(int pressioneSLM) {
		this.pressioneSLM = pressioneSLM;
	}
	public int getPressioneMedia() {
		return pressioneMedia;
	}
	public void setPressioneMedia(int pressioneMedia) {
		this.pressioneMedia = pressioneMedia;
	}
	public int getPioggia() {
		return pioggia;
	}
	public void setPioggia(int pioggia) {
		this.pioggia = pioggia;
	}
	public String getFenomeni() {
		return fenomeni;
	}
	public void setFenomeni(String fenomeni) {
		this.fenomeni = fenomeni;
	}
	
	@Override
	public String toString() {
		return "Situazione [data=" + data + ", tMedia=" + tMedia + "]";
	}
	public Situazione(String localita, LocalDate data, int tMedia, int tMin,
			int tMax, int puntoRugiada, int umidita, int visibilita,
			int ventoMedia, int ventoMax, int raffica, int pressioneSLM,
			int pressioneMedia, int pioggia, String fenomeni) {
		super();
		this.localita = localita;
		this.data = data;
		this.tMedia = tMedia;
		this.tMin = tMin;
		this.tMax = tMax;
		this.puntoRugiada = puntoRugiada;
		this.umidita = umidita;
		this.visibilita = visibilita;
		this.ventoMedia = ventoMedia;
		this.ventoMax = ventoMax;
		this.raffica = raffica;
		this.pressioneSLM = pressioneSLM;
		this.pressioneMedia = pressioneMedia;
		this.pioggia = pioggia;
		this.fenomeni = fenomeni;
	}
	
	

}

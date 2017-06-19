package it.polito.tdp.meteo.bean;

import java.time.LocalDate;
import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;

import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {
	private List<Situazione> situazioniTorino;
	private UndirectedGraph<Integer,DefaultEdge> graph;

	public List<Situazione> getSituazioni(String localita){
		if(situazioniTorino==null){
			MeteoDAO dao = new MeteoDAO();
			this.situazioniTorino= dao.getAllSituazioniLocalita(localita);
		}
		return situazioniTorino;
	}
	
	public List<LocalDate> getDatePerTemperatura(String localita, int temp){
		MeteoDAO dao = new MeteoDAO();
		
		return dao.getDatePerTemperaturaMedia(localita, temp);

	}

	public int getTempMediaGGPrima(LocalDate d) {
		MeteoDAO dao = new MeteoDAO();
		System.out.println(d.toString());
		
		LocalDate ldp = d.minusDays(1);
		
		System.out.println(ldp.toString()+"\n");
		
		return dao.tempMediaGiorno(ldp);
		
	}
	
	public void creaGrafo(String localita){
		if(graph == null)
			this.graph = new Pseudograph<>(DefaultEdge.class);
		
		MeteoDAO dao = new MeteoDAO();
		Graphs.addAllVertices(graph, dao.getTemperatureMedie(localita));
		
		for(Situazione s1: this.getSituazioni(localita)){
			for(Situazione s2: this.getSituazioni(localita)){
				LocalDate d1 = s1.getData();
				LocalDate d2 = s2.getData();
				if(d1.getMonth()==d2.getMonth() && d2.getDayOfMonth()==d1.getDayOfMonth()+1){
					
					System.out.println(s1.toString()+" "+s2.toString()+"\n");
					
					graph.addEdge(s1.getTMedia(), s2.getTMedia());
				}
			}
		}
		
		System.out.println(graph.toString());
	}

	public Set<Integer> getTempPossibili(int temperatura) {
		Set<Integer> tempPox = new LinkedHashSet<Integer>();
		Set<Integer> daAggiungere = new LinkedHashSet<Integer>();
		
		tempPox.addAll(Graphs.neighborListOf(graph, temperatura));
		
		for(Integer i: tempPox){
			daAggiungere.addAll(Graphs.neighborListOf(graph, i));
		}
		tempPox.addAll(daAggiungere);
		
		return tempPox;
	}

}

/***************************************************************************\
 *               *                                                         *
 *    #####      *  (!) 2014 by Giovanni Squillero                         *
 *   ######      *  Politecnico di Torino - Dip. Automatica e Informatica  *
 *   ###   \     *  Cso Duca degli Abruzzi 24 / I-10129 TORINO / ITALY     *
 *    ##G  c\    *                                                         *
 *    #     _\   *  tel : +39-011-564.7092  /  Fax: +39-011-564.7099       *
 *    |   _/     *  mail: giovanni.squillero@polito.it                     *
 *    |  _/      *  www : http://www.cad.polito.it/staff/squillero/        *
 *               *                                                         *
\***************************************************************************/

package it.polito.tdp.meteo.db;

import it.polito.tdp.meteo.bean.Situazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe DAO per l'accesso al database {@code meteo}
 * 
 * @author Fulvio
 * 
 */
public class MeteoDAO {

	/**
	 * Interroga il database e restituisce tutti i dati nella tabella
	 * {@code situazione} sotto forma di un {@link ArrayList} di
	 * {@link Situazione}, ordinati in modo crescente per data.
	 * 
	 * @return la {@link ArrayList} di {@link Situazione}
	 */
	public List<Situazione> getAllSituazioniLocalita(String localita) {

		final String sql = "SELECT * "+
							"FROM situazione "+
							"where localita=? "+
							"ORDER BY data ASC ";

		List<Situazione> situazioni = new ArrayList<Situazione>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, localita);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Situazione s = new Situazione(rs.getString("Localita"),
						rs.getDate("Data").toLocalDate(), rs.getInt("Tmedia"),
						rs.getInt("Tmin"), rs.getInt("Tmax"),
						rs.getInt("Puntorugiada"), rs.getInt("Umidita"),
						rs.getInt("Visibilita"), rs.getInt("Ventomedia"),
						rs.getInt("Ventomax"), rs.getInt("Raffica"),
						rs.getInt("Pressioneslm"), rs.getInt("Pressionemedia"),
						rs.getInt("Pioggia"), rs.getString("Fenomeni"));
				situazioni.add(s);
			}
			return situazioni;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Test method for class {@link MeteoDAO}
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		
		MeteoDAO dao = new MeteoDAO() ;
		String t = "Torino";
		List<Situazione> list = dao.getAllSituazioniLocalita(t) ;
		
		for( Situazione s : list ) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d°C-%3d°C  %3d%%  %s\n", 
					s.getLocalita(), s.getData(), s.getTMin(), s.getTMax(), s.getUmidita(), s.getFenomeni()) ;
		}
		List<LocalDate> lista = dao.getDatePerTemperaturaMedia(t, 23) ;
		for(LocalDate d: lista){
			System.out.println(d.toString());
		}

	}

	public List<LocalDate> getDatePerTemperaturaMedia(String localita, int temp) {
		final String sql = "SELECT Data "+
				"FROM situazione "+
				"where localita=? and Tmedia= ? "+
				"ORDER BY data ASC ";

		List<LocalDate> date = new ArrayList<LocalDate>();

	try {
	Connection conn = DBConnect.getInstance().getConnection();
	PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, localita);
		st.setInt(2, temp);
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
		
			date.add(rs.getDate("Data").toLocalDate());
		}
		
		conn.close();
		return date;
		} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
		}
		}

	public int tempMediaGiorno(LocalDate ldp) {
		final String sql = "SELECT Tmedia "+
				"FROM situazione "+
				"where localita=? and Data= ? ";

		List<LocalDate> date = new ArrayList<LocalDate>();

	try {
	Connection conn = DBConnect.getInstance().getConnection();
	PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, "Torino");
		st.setDate(2, Date.valueOf(ldp));
		
		ResultSet rs = st.executeQuery();
		
		int tmedia=0;
		if(rs.next()) {
		
			tmedia = rs.getInt("Tmedia");
		}
		
		conn.close();
		return tmedia;
		
		} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
		}
		}

	public Set<Integer> getTemperatureMedie(String localita) {
		final String sql = "SELECT distinct Tmedia "+
				"FROM situazione "+
				"where localita=? ";

		Set<Integer> tmedie = new HashSet<Integer>();

	try {
	Connection conn = DBConnect.getInstance().getConnection();
	PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, localita);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
		
			tmedie.add(rs.getInt("Tmedia"));
		}
		
		conn.close();
		return tmedie;
		
		} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
		}
		}

}






import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.sqlite.SQLiteConfig;



public class DataSource {

	public DataSource() throws ClassNotFoundException {

	}
	// non funziona
	public static boolean pragmaDatabaseVersione() throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			
			
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery("PRAGMA user_version");
			
		
			
			int i=0;
			while (rs.next())
			{
				i= i++;
			}
			System.err.println("pragma_____/" + rs.getRow()+"__"+i);
			
			rs.close();
			conn.close();
			
			
			
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;

	}
	
	public static boolean pragmaIntegrytyControl() throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("REINDEX posizionegps");
			System.err.println("pragma/" + rs);

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;

	}
	public static boolean databaseAggiornato18(String path) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(mydbhelper.pathDataBase);
				stmt = conn.createStatement();
				
				try {
					
					rs = stmt.executeQuery("select "+ mydbhelper.COLUMN_TIPOATTIVITA  +" from "+ mydbhelper.TABLE_POSIZIONE_GPS);
					
					
				
						 if (rs.next()) return true; // connection is valid
				} catch (Exception e) {
					System.out.println("DATA SOUCE CONNESSO 64/"+ e.getMessage());
					result=false;
				}
				
				 
			} catch (ClassNotFoundException e) {
				result = false;
				
						
				e.printStackTrace();
				System.err.println("DATA SOURCE CONNESSO/"+ stmt + "/" +rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
		}
		finally {
			   if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		return result;
	}
	
	public static boolean databaseConnesso(String path) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(mydbhelper.pathDataBase);
				
				stmt = conn.createStatement();
				
				try {
					
					rs = stmt.executeQuery("select * from "+ mydbhelper.TABELLA_TIPOLOGIA);
				
						 if (rs.next()) return true; // connection is valid
				} catch (Exception e) {
					System.out.println("DATA SOUCE CONNESSO 64/"+ e.getMessage());
					result=false;
				}
				
				 
			} catch (ClassNotFoundException e) {
				result = false;
				
						
				e.printStackTrace();
				System.err.println("DATA SOURCE CONNESSO/"+ stmt + "/" +rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
		}
		finally {
			   if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		return result;
	}

	public static void selezionoTuttiMostrati(boolean value, String ricerca,
			long idtipologia) {
		Connection conn;
		Statement stat;
		ResultSet rs;
		int intValue = 0;
		if (value == true)
			intValue = 1;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();

			String sqlLikeNome = mydbhelper.COLUMN_NAME + " LIKE '%" + ricerca
					+ "%' " + " OR " + mydbhelper.COLUMN_CITTA + " LIKE '%"
					+ ricerca + "%'";
			String sqlLikeTel = mydbhelper.COLUMN_TELEFONO + " LIKE '%"
					+ ricerca + "%'";
			String sqlTipologia = mydbhelper.COLUMN_TIPOLOGIA + " = "
					+ idtipologia;
			// String WhereCondition = " WHERE (" + sqlLikeNome + " OR " +
			// sqlLikeTel + ")"+ " AND " + sqlTipologia;
			String WhereCondition = "";
			if (ricerca.length() > 0) {
				WhereCondition = " WHERE (" + sqlLikeNome + " OR " + sqlLikeTel
						+ ")";
				if (idtipologia >= 0) // bisogna selezionare le tipologis
				{
					WhereCondition = WhereCondition + " AND " + sqlTipologia;
				}
			} else {
				if (idtipologia >= 0) // bisogna selezionare le tipologis
				{
					WhereCondition = " WHERE " + sqlTipologia;
				}
			}

			stat.execute("Update posizionegps Set "
					+ mydbhelper.COLUMN_CLIENTEVISUALIZZATO + "=" + intValue
					+ WhereCondition);

			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void selezionoTutti(boolean value) {
		Connection conn;
		Statement stat;
		ResultSet rs;
		int intValue = 0;
		if (value == true)
			intValue = 1;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			// stat.execute("Update posizionegps Set clientevisualizzato="+intValue);
			stat.execute("Update posizionegps Set "
					+ mydbhelper.COLUMN_CLIENTEVISUALIZZATO + "=" + intValue);

			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	public static void settaTipologiaSelezionata(boolean value, long idTipologia) {
		Connection conn;
		Statement stat;
		ResultSet rs;
		int intValue = 0;
		if (value == true)
			intValue = 1;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			stat.execute("UPDATE " + mydbhelper.TABELLA_TIPOLOGIA + " SET "
					+ mydbhelper.COLUMN_SELEZIONETIPOLOGIA + "=" + intValue
					+ " WHERE " + mydbhelper.COLUMN_IDTIPOLOGIA + "="
					+ idTipologia);

			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void settaPerId(boolean value, long idCliente) {
		Connection conn;
		Statement stat;
		ResultSet rs;
		int intValue = 0;
		if (value == true)
			intValue = 1;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			stat.execute("Update posizionegps Set "
					+ mydbhelper.COLUMN_CLIENTEVISUALIZZATO + "=" + intValue
					+ " WHERE " + mydbhelper.COLUMN_ID + "=" + idCliente);

			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Tipologia> getAlltipologia() {
		Connection conn;
		Statement stat;
		ResultSet rs;
		List<Tipologia> listatipologia = new ArrayList<Tipologia>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_TIPOLOGIA + " ORDER BY "
					+ mydbhelper.COLUMN_DESCRIZIONETIPOLOGIA + " ASC");

			while (rs.next()) {
				Tipologia tipo = cursorToTipologia(rs);

				listatipologia.add(tipo);
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {

			Tipologia tipoVuota = new Tipologia();
			
			tipoVuota.setpTipologia(0, "", 0, 0);
			listatipologia.add(tipoVuota);

		}

		return listatipologia;

	}

	// cancella cliente referenti e attività per il cliente

	public static void cancellaReferente(long idReferente) {
		Connection conn;
		Statement stat;
		if (DLock.isLocked==false)
				{ try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
		
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_REFERENTI
							+ " WHERE " + mydbhelper.COLUMN_REFERENTI_ID + "="
							+ idReferente);
		
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}

						

	}

	public static void cancellaAttivita(long idAttività) {
		Connection conn;
		Statement stat;
		if (DLock.isLocked==false)
		{try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
			
						conn = DriverManager.getConnection(mydbhelper.pathDataBase);
						stat = conn.createStatement();
						stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_ATTIVITA
								+ " WHERE " + mydbhelper.COLUMN_ATTIVITA_ID + "="
								+ idAttività);
						conn.close();
						DGen.aggiornaFileSetting();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
					

	}
	public static void cancellaOfferta(long IdOfferta) {
		Connection conn;
		Statement stat;

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (DLock.isLocked==false)
				{	try {
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_OFFERTA
							+ " WHERE " + mydbhelper.OFFERTA_ID + "="
							+ IdOfferta);
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
	

	};
	
	public static void cancellaFiles(long IdFiles) {
		Connection conn;
		Statement stat;

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (DLock.isLocked==false)
				{try {
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_FILE
							+ " WHERE " + mydbhelper.FILE_ID + "="
							+ IdFiles);
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
		
	};
	
	public static void cancellaFileCliente(long idCliente) {
		Connection conn;
		Statement stat;

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (DLock.isLocked==false)
			{	try {
	
				conn = DriverManager.getConnection(mydbhelper.pathDataBase);
				stat = conn.createStatement();
				stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_FILE
						+ " WHERE " + mydbhelper.FILE_IDCLIENTE + "="
						+ idCliente);
				conn.close();
				DGen.aggiornaFileSetting();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	};
	
	public static void cancellaOffertaCliente(long idCliente) {
		Connection conn;
		Statement stat;

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (DLock.isLocked==false)
			{	try {
	
				conn = DriverManager.getConnection(mydbhelper.pathDataBase);
				stat = conn.createStatement();
				stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_OFFERTA
						+ " WHERE " + mydbhelper.OFFERTA_IDCLIENTE + "="
						+ idCliente);
				conn.close();
				DGen.aggiornaFileSetting();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	};

	public static void cancellaTipologi(long idTipologia) {
		Connection conn;
		Statement stat;

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (DLock.isLocked==false)
			{	try {
	
				conn = DriverManager.getConnection(mydbhelper.pathDataBase);
				stat = conn.createStatement();
				stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_TIPOLOGIA
						+ " WHERE " + mydbhelper.COLUMN_IDTIPOLOGIA + "="
						+ idTipologia);
	
				conn.close();
				DGen.aggiornaFileSetting();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	}

	public static void cancellaClienteTotale(long idCliente) {
		Connection conn;
		Statement stat;

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (DLock.isLocked==false)
				{		try {
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABLE_POSIZIONE_GPS
							+ " WHERE " + mydbhelper.COLUMN_ID + "=" + idCliente);
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_REFERENTI
							+ " WHERE " + mydbhelper.COLUMN_REFERENTI_IDCLIENTE + "="
							+ idCliente);
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_ATTIVITA
							+ " WHERE " + mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + "="
							+ idCliente);
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_OFFERTA
							+ " WHERE " + mydbhelper.OFFERTA_IDCLIENTE + "="
							+ idCliente);
					stat.executeUpdate("DELETE FROM " + mydbhelper.TABELLA_FILE
							+ " WHERE " + mydbhelper.FILE_IDCLIENTE + "="
							+ idCliente);
		
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
	

	}

	public static List<posizioneGps> getAllClientiRicerca(String ricerca,
			long idtipologia, String colonnaOrdinamento,
			String tipoOrdinamento, double lati, double longi, long mesi) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<posizioneGps> listatClienti = new ArrayList<posizioneGps>();
		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();

			// String sqlTipologia = " WHERE " + mydbhelper.COLUMN_TIPOLOGIA +
			// "= 1";

			// String sqlOrder = " ORDER BY " + colonnaOrdinamento +
			// "COLLATE NOCASE " + tipoOrdinamento;
			String sqlOrder = " ORDER BY " + colonnaOrdinamento
					+ " COLLATE NOCASE " + tipoOrdinamento;
			String sqlLikeNome = mydbhelper.COLUMN_NAME + " LIKE '%" + ricerca
					+ "%' " + " OR " + mydbhelper.COLUMN_CITTA + " LIKE '%"
					+ ricerca + "%'";
			String sqlLikeTel = mydbhelper.COLUMN_TELEFONO + " LIKE '%"
					+ ricerca + "%'";
			String sqlTipologia = mydbhelper.COLUMN_TIPOLOGIA + " = "
					+ idtipologia;

			String sqlMesi = "";
			if (mesi < -99) { // minori di 99 mesi
				sqlMesi = mydbhelper.COLUMN_MESIATTIVITA + " < -99";
			} else {
				if (mesi > 0) // tutti
				{
					sqlMesi = "";
				} else { // mese selezinato
					sqlMesi = mydbhelper.COLUMN_MESIATTIVITA + " = " + mesi;
				}
			}
			// String WhereCondition = " WHERE (" + sqlLikeNome + " OR " +
			// sqlLikeTel + ")"+ " AND " + sqlTipologia;
			String WhereCondition = "";
			if (ricerca.length() > 0) {
				WhereCondition = " WHERE (" + sqlLikeNome + " OR " + sqlLikeTel
						+ ")";
				if (idtipologia >= 0) // bisogna selezionare le tipologis
				{
					WhereCondition = WhereCondition + " AND " + sqlTipologia;
				} else {
					if (idtipologia == -2) // seleziona tipologia
					{
						sqlTipologia = calcolaSqlTipologiaSeleziona();
						WhereCondition = WhereCondition + " AND "
								+ sqlTipologia;
					}

				}
			} else {
				if (idtipologia >= 0) // bisogna selezionare le tipologis
				{
					WhereCondition = " WHERE " + sqlTipologia;
				} else {
					if (idtipologia == -2) // seleziona tipologia
					{
						sqlTipologia = calcolaSqlTipologiaSeleziona();
						WhereCondition = " WHERE " + sqlTipologia;
					}

				}

			}

			// consizione sui mesi
			if (WhereCondition.length() == 0) {
				if (sqlMesi.length() > 0)
					WhereCondition = " WHERE " + sqlMesi;
			} else {
				if (sqlMesi.length() > 0)
					WhereCondition = WhereCondition + " AND " + sqlMesi;
			}

			WhereCondition = WhereCondition + " " + sqlOrder;
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABLE_POSIZIONE_GPS + WhereCondition);

			int i = 0;

			while (rs.next()) {
				posizioneGps cli = cursorToCliente(rs);
				listatClienti.add(cli);
				i++;

			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {

		}

		return listatClienti;

	}

	public static String calcolaSqlTipologiaSeleziona() {
		String result = "";
		List<Tipologia> tipologiaSelezionate = getAlltipologiaSelezionate();
		if (tipologiaSelezionate.size() > 0) {
			for (int i = 0; i < (tipologiaSelezionate.size()); i++) // -1 perche
																	// ullitmo
																	// lo
																	// aggiungo
																	// senza and
			{
				result = result + mydbhelper.COLUMN_TIPOLOGIA + " LIKE "
						+ tipologiaSelezionate.get(i).getpIdTipologia()
						+ " OR ";
			}
			result = result.substring(0, result.length() - 3);
			result = "( " + result + " ) ";
		}

		return result;
	}

	public static List<Tipologia> getAlltipologiaSelezionate() {
		Connection conn;
		Statement stat;
		ResultSet rs;
		List<Tipologia> listatipologia = new ArrayList<Tipologia>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_TIPOLOGIA + " WHERE "
					+ mydbhelper.COLUMN_SELEZIONETIPOLOGIA + " =1");

			while (rs.next()) {
				Tipologia tipo = cursorToTipologia(rs);

				listatipologia.add(tipo);
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {

			Tipologia tipoVuota = new Tipologia();

			tipoVuota.setpTipologia(0, "", 0, 0);
			listatipologia.add(tipoVuota);

		}

		return listatipologia;

	}

	public static List<posizioneGps> getAllClientiSelezionati() {
		List<posizioneGps> listatClienti = new ArrayList<posizioneGps>();

		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager
					.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery("select * from "
					+ mydbhelper.TABLE_POSIZIONE_GPS + " WHERE "
					+ mydbhelper.COLUMN_CLIENTEVISUALIZZATO + "=1");
			int i = 0;

			while (rs.next()) {
				posizioneGps cli = cursorToCliente(rs);
				listatClienti.add(cli);
				i++;
			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listatClienti;

	}

	public static posizioneGps getClientID(long idCliente) {
		posizioneGps listatClienti = new posizioneGps();

		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager
					.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();

			// ResultSet rs = stat.executeQuery("select * from " +
			// mydbhelper.TABLE_POSIZIONE_GPS + " ORDER BY " +
			// mydbhelper.COLUMN_NAME + " ASC");
			ResultSet rs = stat.executeQuery("select * from "
					+ mydbhelper.TABLE_POSIZIONE_GPS + " WHERE "
					+ mydbhelper.COLUMN_ID + "=" + idCliente);
			if (!rs.next()) {
				// System.out.println("No records found");
			} else {
				listatClienti = cursorToCliente(rs);
			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listatClienti;

	}

	public static int aggiungiCliente(posizioneGps cliente) {
		int idNewRow = -1;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					Connection conn = DriverManager
							.getConnection(mydbhelper.pathDataBase);
					Statement stat = conn.createStatement();
		
					// ResultSet rs = stat.executeQuery("select * from " +
					// mydbhelper.TABLE_POSIZIONE_GPS + " ORDER BY " +
					// mydbhelper.COLUMN_NAME + " ASC");
					// ResultSet rs = stat.executeQuery("select * from " +
					// mydbhelper.TABLE_POSIZIONE_GPS + " WHERE " + mydbhelper.COLUMN_ID
					// + "="+idCliente);
					String sql = "INSERT INTO " + mydbhelper.TABLE_POSIZIONE_GPS + " ("
							+ mydbhelper.COLUMN_NAME + ","
							+ mydbhelper.COLUMN_TIPOLOGIA + ","
							+ mydbhelper.COLUMN_INDIRIZZO + ","
							+ mydbhelper.COLUMN_CITTA + "," + mydbhelper.COLUMN_NAZIONE
							+ "," + mydbhelper.COLUMN_TELEFONO + ","
							+ mydbhelper.COLUMN_FAX + "," + mydbhelper.COLUMN_SITO
							+ "," + mydbhelper.COLUMN_LATITUDINE + ","
							+ mydbhelper.COLUMN_LONGITUDINE + ","
							+ mydbhelper.COLUMN_DATA_IN_MILLIS + ","
							+ mydbhelper.COLUMN_PRECISIONE + ","
							+ mydbhelper.COLUMN_DISTANZA + ","
							+ mydbhelper.COLUMN_PROVINCIA + ","
							+ mydbhelper.COLUMN_PROIEZIONE_LATITUDINE + ","
							+ mydbhelper.COLUMN_PROIEZIONE_LONGITUDINE + ","
							+ mydbhelper.COLUMN_CLIENTEVISUALIZZATO + ","
							+ mydbhelper.COLUMN_CAP + "," 
							+ mydbhelper.COLUMN_MEMO  + ","
							+ mydbhelper.COLUMN_GPSFREE1  + ","
							+ mydbhelper.COLUMN_GPSFREE2   + ","
							+ mydbhelper.COLUMN_GPSFREE3  + ","
							+ mydbhelper.COLUMN_GPSFREE4  +","
							+ mydbhelper.COLUMN_TIPOATTIVITA+","
							+ mydbhelper.COLUMN_SPARE1 +","
							+ mydbhelper.COLUMN_SPARE2+ ","
							+ mydbhelper.COLUMN_SPARE3+ ","
							+ mydbhelper.COLUMN_SPARE4  
									
							
							+ ") "
		
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, cliente.getpNome());
					pstmt.setLong(2, cliente.getpTipo());
					pstmt.setString(3, cliente.getpInidirizzo());
					pstmt.setString(4, cliente.getpCittà());
					pstmt.setString(5, cliente.getpNazione());
					pstmt.setString(6, cliente.getpTelefono());
					pstmt.setString(7, cliente.getpFax());
					pstmt.setString(8, cliente.getpSito());
					pstmt.setDouble(9, cliente.getpLatitudine());
					pstmt.setDouble(10, cliente.getpLongitudine());
					pstmt.setLong(11, cliente.getpDataInMillis());
					pstmt.setLong(12, cliente.getpPrecisione());
		
					pstmt.setFloat(13, cliente.getpDistanza());
					pstmt.setString(14, cliente.getpProvincia());
					pstmt.setDouble(15, cliente.getpLatitudineProi());
					pstmt.setDouble(16, cliente.getpLongitudineProi());
					pstmt.setBoolean(17, cliente.getpSettato());
					pstmt.setString(18, cliente.getpCap());
		
					pstmt.setString(19, cliente.getpMemo());
					pstmt.setString(20, cliente.getpFree1());
					pstmt.setString(21, cliente.getpFree2());
					pstmt.setString(22, cliente.getpFree3());
					pstmt.setString(23, cliente.getpFree4());
					
					pstmt.setInt(24, cliente.getpTipoattivita());
					pstmt.setLong(25, cliente.getpSpare1());
					pstmt.setLong(26, cliente.getpSpare2());
					pstmt.setString(27, cliente.getpSpare3());
					pstmt.setString(28, cliente.getpSpare4());
					
					pstmt.executeUpdate();
					ResultSet keyset = pstmt.getGeneratedKeys();
					keyset.next();
					idNewRow = keyset.getInt(1);
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

		return idNewRow;

	}
	
	
	public static int aggiungiFile(FileSincronizzati file) {
		int idNewRow = -1;
		if (DLock.isLocked==false)
		{		
				try {
					Class.forName("org.sqlite.JDBC");
		
					Connection conn = DriverManager
							.getConnection(mydbhelper.pathDataBase);
					Statement stat = conn.createStatement();
					String sql = "INSERT INTO " + mydbhelper.TABELLA_FILE + " ("
							+ mydbhelper.FILE_IDCLIENTE + ","
							+ mydbhelper.FILE_NOMEFILEORI + ","
							+ mydbhelper.FILE_NOMEFILEMODIFICATO + ","
							+ mydbhelper.FILE_SERVIZIODICONDIVIZIONE + ","
							+ mydbhelper.FILE_MD5 + ","
							+ mydbhelper.FILE_DIMENSIONE + ","
							+ mydbhelper.FILE_DATAFILE + ","
							+ mydbhelper.FILE_DATAUPLOAD + ","
							+ mydbhelper.FILE_DESCRIZIONE + ","
							+ mydbhelper.FILE_TMP1STRING + ","
							+ mydbhelper.FILE_TMP2STRING + ","
							+ mydbhelper.FILE_TMP1INT + ","
								+ mydbhelper.FILE_TMP2INT + ") "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					pstmt.setLong(1, file.getpIdCliente());
					pstmt.setString(2, file.getpNomeFileOriginario());
					pstmt.setString(3,file.getpNomeFileOModificato());
					pstmt.setInt(4, 0);
					pstmt.setString(5, file.getpMd5());
					pstmt.setLong(6,file.getpDimensioneFile());
					pstmt.setLong(7, file.getpDataFile());
					pstmt.setLong(8, System.currentTimeMillis());
					pstmt.setString(9, file.getpDescrizione());
					pstmt.setString(10, file.getpPathOriginario());
					pstmt.setString(11, "");
					pstmt.setInt(12, 0);
					pstmt.setInt(13, 0);
		
					pstmt.executeUpdate();
					ResultSet keyset = pstmt.getGeneratedKeys();
					keyset.next();
					idNewRow = keyset.getInt(1);
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}



		return idNewRow;

	}
	
	
	
	public static int aggiungiReferente(Referenti refe) {
		int idNewRow = -1;
		
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					Connection conn = DriverManager
							.getConnection(mydbhelper.pathDataBase);
					Statement stat = conn.createStatement();
					String sql = "INSERT INTO " + mydbhelper.TABELLA_REFERENTI + " ("
							+ mydbhelper.COLUMN_REFERENTI_NOME + ","
							+ mydbhelper.COLUMN_REFERENTI_COGNOME + ","
							+ mydbhelper.COLUMN_REFERENTI_FUNZIONE + ","
							+ mydbhelper.COLUMN_REFERENTI_TELEFONO + ","
							+ mydbhelper.COLUMN_REFERENTI_FAX + ","
							+ mydbhelper.COLUMN_REFERENTI_CELLULARE + ","
							+ mydbhelper.COLUMN_REFERENTI_IDCLIENTE + ","
							+ mydbhelper.COLUMN_REFERENTI_MAIL + ","
							+ mydbhelper.COLUMN_REFERENTI_NOTE + ") "
							+ "VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, refe.getpNomeReferente());
					pstmt.setString(2, refe.getpCognomeReferente());
					pstmt.setString(3, refe.getpFunzioneReferente());
					pstmt.setString(4, refe.getpTelRefernte());
					pstmt.setString(5, refe.getpFaxReferente());
					pstmt.setString(6, refe.getpCelReferente());
					pstmt.setLong(7, refe.getpIdClienteReferente());
					pstmt.setString(8, refe.getpMaileferente());
					pstmt.setString(9, refe.getpMemoReferente());
		
					pstmt.executeUpdate();
					ResultSet keyset = pstmt.getGeneratedKeys();
					keyset.next();
					idNewRow = keyset.getInt(1);
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
		

		return idNewRow;

	}
	
	public static int aggiungiTipologia(String descrizioneTipologia, int colore) {
		int idNewRow = -1;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					Connection conn = DriverManager
							.getConnection(mydbhelper.pathDataBase);
					Statement stat = conn.createStatement();
					String sql = "INSERT INTO " + mydbhelper.TABELLA_TIPOLOGIA + " ("
							+ mydbhelper.COLUMN_DESCRIZIONETIPOLOGIA + ","
							+ mydbhelper.COLUMN_COLORETIPOLOIGA + ","
							+ mydbhelper.COLUMN_SELEZIONETIPOLOGIA + ") "
							+ "VALUES(?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
		
					pstmt.setString(1, descrizioneTipologia);
					pstmt.setInt(2, colore);
					pstmt.setInt(3, 0);
		
					pstmt.executeUpdate();
					ResultSet keyset = pstmt.getGeneratedKeys();
					keyset.next();
					idNewRow = keyset.getInt(1);
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

		return idNewRow;

	}
	public static int aggiungiOfferta(Offerta offe) {
		int idNewRow = -1;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					Connection conn = DriverManager
							.getConnection(mydbhelper.pathDataBase);
					Statement stat = conn.createStatement();
					/*
					 * modificato per aggiungere campi inutili ma per averre database
					 * coerente String sql = "INSERT INTO "+
					 * mydbhelper.TABELLA_ATTIVITA+ " (" +
					 * mydbhelper.COLUMN_ATTIVITA_DATAATIVITA + "," +
					 * mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + "," +
					 * mydbhelper.COLUMN_ATTIVITA_DESCRIZIONEATTIVITA + ") " +
					 * "VALUES(?,?,?)"; PreparedStatement pstmt =
					 * conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					 * pstmt.setLong(1, atti.getpDataAttivita()); pstmt.setLong(2,
					 * atti.getpIdCliente()); pstmt.setString(3,
					 * atti.getpDescrizioneAttività());
					 */
					
					
					String sql = "INSERT INTO " + mydbhelper.TABELLA_OFFERTA + " ("
							+ mydbhelper.OFFERTA_IDCLIENTE + ","
							+ mydbhelper.OFFERTA_IDTIPOLOGIA + ","
							+ mydbhelper.OFFERTA_DESCRIZIONE + ","
							+ mydbhelper.OFFERTA_IMPORTO + ","
							+ mydbhelper.OFFERTA_DATACHIUSURA + ","
							+ mydbhelper.OFFERTA_DATAPRESENTAZIONE + ","
							+ mydbhelper.OFFERTA_PERCENTUALECHIUSURA + ","
							+ mydbhelper.OFFERTA_ACLIENTE + ","
							+ mydbhelper.OFFERTA_NOTA + ","
							+ mydbhelper.OFFERTA_TMP1 + ") "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					
					pstmt.setLong(1, offe.getpIdClienteOfferta());
					pstmt.setLong(2, 0);
					pstmt.setString(3, offe.getpDescrizioneOfferta());
		
					pstmt.setDouble(4, offe.getpImportoOfferta());
					pstmt.setLong(5, offe.getpDataChiusura());
					pstmt.setLong(6, 0);
					//calendario pstmt.setInt(7, 1); 			
					pstmt.setLong(7, offe.getpPercentuale());
					
					Long giaCliente = (long) (offe.getpAcliente()?1:0);
					pstmt.setLong(8,giaCliente);
					pstmt.setString(9, offe.getpNota());
					String annoMese =DGen.annoMese(offe.getpDataChiusura());
					pstmt.setString(10,annoMese);
		
					pstmt.executeUpdate();
					ResultSet keyset = pstmt.getGeneratedKeys();
					keyset.next();
					idNewRow = keyset.getInt(1);
					conn.close();
					DGen.aggiornaFileSetting();
		
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
		

		return idNewRow;

	}
	
	

	public static int aggiungiAttivita(Attivita atti) {
		int idNewRow = -1;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					Connection conn = DriverManager
							.getConnection(mydbhelper.pathDataBase);
					Statement stat = conn.createStatement();
					/*
					 * modificato per aggiungere campi inutili ma per averre database
					 * coerente String sql = "INSERT INTO "+
					 * mydbhelper.TABELLA_ATTIVITA+ " (" +
					 * mydbhelper.COLUMN_ATTIVITA_DATAATIVITA + "," +
					 * mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + "," +
					 * mydbhelper.COLUMN_ATTIVITA_DESCRIZIONEATTIVITA + ") " +
					 * "VALUES(?,?,?)"; PreparedStatement pstmt =
					 * conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					 * pstmt.setLong(1, atti.getpDataAttivita()); pstmt.setLong(2,
					 * atti.getpIdCliente()); pstmt.setString(3,
					 * atti.getpDescrizioneAttività());
					 */
					String sql = "INSERT INTO " + mydbhelper.TABELLA_ATTIVITA + " ("
							+ mydbhelper.COLUMN_ATTIVITA_DATAATIVITA + ","
							+ mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + ","
							+ mydbhelper.COLUMN_ATTIVITA_DESCRIZIONEATTIVITA + ","
							+ mydbhelper.COLUMN_ATTIVITA_DATAALLARME + ","
							+ mydbhelper.COLUMN_ATTIVITA_IDREFERENTE + ","
							+ mydbhelper.COLUMN_ATTIVITA_IDTIPOATTIVITA + ","
							+ mydbhelper.COLUMN_ATTIVITA_C1 + ","
							+ mydbhelper.COLUMN_ATTIVITA_C2 + ","
							+ mydbhelper.COLUMN_ATTIVITA_C1 + ") "
							+ "VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					pstmt.setLong(1, atti.getpDataAttivita());
					pstmt.setLong(2, atti.getpIdCliente());
					pstmt.setString(3, atti.getpDescrizioneAttività());
		
					pstmt.setLong(4, 0);
					pstmt.setLong(5, -1);
					pstmt.setLong(6, -1);
					//calendario pstmt.setInt(7, 1); 			
					pstmt.setInt(7, atti.getpFattaAttivita());
					pstmt.setInt(8, atti.getpTipoAttivita());
					pstmt.setString(9, "");
		
					pstmt.executeUpdate();
					ResultSet keyset = pstmt.getGeneratedKeys();
					keyset.next();
					idNewRow = keyset.getInt(1);
					conn.close();
					DGen.aggiornaFileSetting();
		
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

		return idNewRow;

	}

	public static void aggiornaClientedaAttivita(long idCliente,
			long dataAttivita, int tipologiaAttivita) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					String sql = "UPDATE " + mydbhelper.TABLE_POSIZIONE_GPS + "  SET "
							+ mydbhelper.COLUMN_DATAATTIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_GIORNIATTIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_MESIATTIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_TIPOATTIVITA + " = ?  "
		
							+ " WHERE " + mydbhelper.COLUMN_ID + " = ? ";
		
					PreparedStatement pstmt = conn.prepareStatement(sql);
		
					pstmt.setLong(1, dataAttivita);
					pstmt.setLong(2, DGen.giorniAttivita(dataAttivita));
					pstmt.setLong(3, DGen.mesiPassati(dataAttivita));
					pstmt.setLong(4, tipologiaAttivita);
					pstmt.setLong(5, idCliente);
		
					pstmt.executeUpdate();
		
					/*
					 * System.out.println("ClienteInd/"+cliente.getpInidirizzo());
					 * stat.execute("UPDATE " + mydbhelper.TABLE_POSIZIONE_GPS+ " SET "
					 * + mydbhelper.COLUMN_NAME + " = '"+cliente.getpNome() +"',  " +
					 * mydbhelper.COLUMN_INDIRIZZO + " = '"+cliente.getpInidirizzo()
					 * +"',  " + mydbhelper.COLUMN_CITTA + " = '"+cliente.getpCittà()
					 * +"',  " + mydbhelper.COLUMN_NAZIONE +
					 * " = '"+cliente.getpNazione() +"',  " + mydbhelper.COLUMN_TELEFONO
					 * + " = '"+cliente.getpTelefono() +"',  " + mydbhelper.COLUMN_FAX +
					 * " = '"+cliente.getpFax() +"',  " + mydbhelper.COLUMN_SITO +
					 * " = '"+cliente.getpSito() +"',  " + mydbhelper.COLUMN_LATITUDINE
					 * + " = '"+cliente.getpLatitudine() +"',  " +
					 * mydbhelper.COLUMN_LONGITUDINE + " = '"+cliente.getpLongitudine()
					 * +"',  " + mydbhelper.COLUMN_MEMO + " = '"+cliente.getpMemo()
					 * +"',  " + mydbhelper.COLUMN_CAP + " = '"+cliente.getpCap()
					 * 
					 * +"',  " + mydbhelper.COLUMN_DATA_IN_MILLIS +
					 * " = '"+System.currentTimeMillis() +"' WHERE " +
					 * mydbhelper.COLUMN_ID + " = "+cliente.getpId()+"");
					 * 
					 * conn.close();
					 */
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
		
		

	}

	public static void aggiornaClientedaID(posizioneGps cliente) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					String sql = "UPDATE " + mydbhelper.TABLE_POSIZIONE_GPS + "  SET "
							+ mydbhelper.COLUMN_NAME + " = ?  ,"
							+ mydbhelper.COLUMN_TIPOLOGIA + " = ?  ,"
							+ mydbhelper.COLUMN_INDIRIZZO + " = ?  ,"
							+ mydbhelper.COLUMN_CITTA + " = ?  ,"
							+ mydbhelper.COLUMN_NAZIONE + " = ?  ,"
							+ mydbhelper.COLUMN_TELEFONO + " = ?  ,"
							+ mydbhelper.COLUMN_FAX + " = ?  ,"
							+ mydbhelper.COLUMN_SITO + " = ?  ,"
							+ mydbhelper.COLUMN_LATITUDINE + " = ?  ,"
							+ mydbhelper.COLUMN_LONGITUDINE + " = ?  ,"
							+ mydbhelper.COLUMN_DATA_IN_MILLIS + " = ?  ,"
							+ mydbhelper.COLUMN_PRECISIONE + " = ?  ,"
							+ mydbhelper.COLUMN_DISTANZA + " = ?  ,"
							+ mydbhelper.COLUMN_PROVINCIA + " = ?  ,"
							+ mydbhelper.COLUMN_PROIEZIONE_LATITUDINE + " = ?  ,"
							+ mydbhelper.COLUMN_PROIEZIONE_LONGITUDINE + " = ?  ,"
							+ mydbhelper.COLUMN_CLIENTEVISUALIZZATO + " = ?  ,"
							+ mydbhelper.COLUMN_CAP + " = ?  ,"
							+ mydbhelper.COLUMN_MEMO + " = ?  ,"
							+ mydbhelper.COLUMN_DATAATTIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_GIORNIATTIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_MESIATTIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_GPSFREE1  + " = ?  ,"
							+ mydbhelper.COLUMN_GPSFREE2  + " = ?  ,"
							+ mydbhelper.COLUMN_GPSFREE3  + " = ?  ,"
							+ mydbhelper.COLUMN_GPSFREE4  + " = ?  ,"
							+ mydbhelper.COLUMN_TIPOATTIVITA  + " = ?  ,"
							+ mydbhelper.COLUMN_SPARE1  + " = ?  ,"
							+ mydbhelper.COLUMN_SPARE2  + " = ?  ,"
							+ mydbhelper.COLUMN_SPARE3  + " = ?  ,"
							+ mydbhelper.COLUMN_SPARE4  + " = ?  "
		
							+ " WHERE " + mydbhelper.COLUMN_ID + " = ? ";
		
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cliente.getpNome());
					pstmt.setLong(2, cliente.getpTipo());
					pstmt.setString(3, cliente.getpInidirizzo());
					pstmt.setString(4, cliente.getpCittà());
					pstmt.setString(5, cliente.getpNazione());
					pstmt.setString(6, cliente.getpTelefono());
					pstmt.setString(7, cliente.getpFax());
					pstmt.setString(8, cliente.getpSito());
					pstmt.setDouble(9, cliente.getpLatitudine());
					pstmt.setDouble(10, cliente.getpLongitudine());
					pstmt.setLong(11, cliente.getpDataInMillis());
					pstmt.setLong(12, cliente.getpPrecisione());
					pstmt.setFloat(13, cliente.getpDistanza());
					pstmt.setString(14, cliente.getpProvincia());
					pstmt.setDouble(15, cliente.getpLatitudineProi());
					pstmt.setDouble(16, cliente.getpLongitudineProi());
					pstmt.setBoolean(17, cliente.getpSettato());
					pstmt.setString(18, cliente.getpCap());
					pstmt.setString(19, cliente.getpMemo());
					pstmt.setLong(20, cliente.getpDataAttività());
					pstmt.setLong(21, cliente.getpGiorniAttività());
					pstmt.setLong(22, cliente.getpMesiAttivita());
					pstmt.setString(23, cliente.getpFree1());
					pstmt.setString(24, cliente.getpFree2());
					pstmt.setString(25, cliente.getpFree3());
					pstmt.setString(26, cliente.getpFree4());
					
					
					pstmt.setInt(27, cliente.getpTipoattivita());
					pstmt.setLong(28, cliente.getpSpare1());
					pstmt.setLong(29, cliente.getpSpare2());
					pstmt.setString(30, cliente.getpSpare3());
					pstmt.setString(31, cliente.getpSpare4());
					
					
					pstmt.setLong(32, cliente.getpId());
		
					pstmt.executeUpdate();
		
					/*
					 * System.out.println("ClienteInd/"+cliente.getpInidirizzo());
					 * stat.execute("UPDATE " + mydbhelper.TABLE_POSIZIONE_GPS+ " SET "
					 * + mydbhelper.COLUMN_NAME + " = '"+cliente.getpNome() +"',  " +
					 * mydbhelper.COLUMN_INDIRIZZO + " = '"+cliente.getpInidirizzo()
					 * +"',  " + mydbhelper.COLUMN_CITTA + " = '"+cliente.getpCittà()
					 * +"',  " + mydbhelper.COLUMN_NAZIONE +
					 * " = '"+cliente.getpNazione() +"',  " + mydbhelper.COLUMN_TELEFONO
					 * + " = '"+cliente.getpTelefono() +"',  " + mydbhelper.COLUMN_FAX +
					 * " = '"+cliente.getpFax() +"',  " + mydbhelper.COLUMN_SITO +
					 * " = '"+cliente.getpSito() +"',  " + mydbhelper.COLUMN_LATITUDINE
					 * + " = '"+cliente.getpLatitudine() +"',  " +
					 * mydbhelper.COLUMN_LONGITUDINE + " = '"+cliente.getpLongitudine()
					 * +"',  " + mydbhelper.COLUMN_MEMO + " = '"+cliente.getpMemo()
					 * +"',  " + mydbhelper.COLUMN_CAP + " = '"+cliente.getpCap()
					 * 
					 * +"',  " + mydbhelper.COLUMN_DATA_IN_MILLIS +
					 * " = '"+System.currentTimeMillis() +"' WHERE " +
					 * mydbhelper.COLUMN_ID + " = "+cliente.getpId()+"");
					 * 
					 * conn.close();
					 */
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	}

	public static void aggiornaReferentedaID(Referenti refe) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
				{	try {
		
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					String sql = "UPDATE " + mydbhelper.TABELLA_REFERENTI + "  SET "
							+ mydbhelper.COLUMN_REFERENTI_NOME + " = ?  ,"
							+ mydbhelper.COLUMN_REFERENTI_COGNOME + " = ?  ,"
							+ mydbhelper.COLUMN_REFERENTI_FUNZIONE + " = ?  ,"
							+ mydbhelper.COLUMN_REFERENTI_TELEFONO + " = ?  ,"
							+ mydbhelper.COLUMN_REFERENTI_FAX + " = ?  ,"
							+ mydbhelper.COLUMN_REFERENTI_CELLULARE + " = ?  ,"
							+ mydbhelper.COLUMN_REFERENTI_MAIL + " = ?  ,"
							+ mydbhelper.COLUMN_REFERENTI_NOTE + " = ?  " + " WHERE "
							+ mydbhelper.COLUMN_REFERENTI_ID + " = ? ";
		
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, refe.getpNomeReferente());
					pstmt.setString(2, refe.getpCognomeReferente());
					pstmt.setString(3, refe.getpFunzioneReferente());
					pstmt.setString(4, refe.getpTelRefernte());
					pstmt.setString(5, refe.getpFaxReferente());
					pstmt.setString(6, refe.getpCelReferente());
					pstmt.setString(7, refe.getpMaileferente());
					pstmt.setString(8, refe.getpMemoReferente());
					pstmt.setLong(9, refe.getpIdReferente());
		
					pstmt.executeUpdate();
		
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	}
	
	public static void aggiornaOffertaID(Offerta offe) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
			{		try {
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					String sql = "UPDATE " + mydbhelper.TABELLA_OFFERTA + "  SET "
							+ mydbhelper.OFFERTA_DESCRIZIONE + " = ?  ,"
							+ mydbhelper.OFFERTA_DATACHIUSURA + " = ?  ,"
							+ mydbhelper.OFFERTA_PERCENTUALECHIUSURA + " = ?  ,"
							+ mydbhelper.OFFERTA_IMPORTO + " = ? ,"
							+ mydbhelper.OFFERTA_TMP1 + " = ? ,"
							+ mydbhelper.OFFERTA_NOTA + " = ? ,"
							+ mydbhelper.OFFERTA_ACLIENTE + " = ? "
							+ " WHERE " + mydbhelper.OFFERTA_ID + " = ? ";
				
		
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, offe.getpDescrizioneOfferta());
					pstmt.setLong(2, offe.getpDataChiusura());
					//calendario pstmt.setInt(3, 1); 
					pstmt.setLong(3, offe.getpPercentuale());
					pstmt.setDouble(4, offe.getpImportoOfferta());
					String annoMese =DGen.annoMese(offe.getpDataChiusura());
					pstmt.setString(5, annoMese);
					pstmt.setString(6, offe.getpNota());
					Long giaCliente = (long) (offe.getpAcliente()?1:0);
					pstmt.setLong(7, giaCliente);
					
					
				
					System.out.println("DATA SOURCE UPDATE OFFERTA________/" + giaCliente + "/" + offe.getpAcliente());
					//
					pstmt.setLong(8, offe.getpIdOfferta());
					pstmt.executeUpdate();
		
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	}
	
	public static void aggiornaFileNomeModificato(FileSincronizzati file)
	{
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					String sql = "UPDATE " + mydbhelper.TABELLA_FILE + "  SET "
							
							+ mydbhelper.FILE_NOMEFILEMODIFICATO + " = ? "
							
							+ " WHERE " + mydbhelper.FILE_ID + " = ? ";
		
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, file.getpNomeFileOModificato());
					//calendario pstmt.setInt(3, 1); 
					
					pstmt.setLong(2,  file.getpIdFile());
		
					pstmt.executeUpdate();
		
					conn.close();
					DGen.aggiornaFileSetting();
				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
	}
	public static void aggiornaFileID(FileSincronizzati file)
	{
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					String sql = "UPDATE " + mydbhelper.TABELLA_FILE + "  SET "
							+ mydbhelper.FILE_NOMEFILEORI + " = ?  ,"
							+ mydbhelper.FILE_NOMEFILEMODIFICATO + " = ?  ,"
							+ mydbhelper.FILE_MD5 + " = ?  ,"
							+ mydbhelper.FILE_DIMENSIONE + " = ?  ,"
							+ mydbhelper.FILE_DATAFILE + " = ?  ,"
							+ mydbhelper.FILE_DESCRIZIONE + " = ?  ,"
							+ mydbhelper.FILE_DATAUPLOAD + " = ?  ,"
							+ mydbhelper.FILE_TMP1STRING + " = ? "
							
							+ " WHERE " + mydbhelper.FILE_ID + " = ? ";
		
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, file.getpNomeFileOriginario());
					pstmt.setString(2, file.getpNomeFileOModificato());
					//calendario pstmt.setInt(3, 1); 
					pstmt.setString(3, file.getpMd5());
					pstmt.setLong(4,  file.getpDimensioneFile());
					pstmt.setLong(5,  file.getpDataFile());
					pstmt.setString(6,  file.getpDescrizione());
					pstmt.setLong(7,  System.currentTimeMillis());
					pstmt.setString(8, file.getpPathOriginario());
					pstmt.setLong(9,  file.getpIdFile());
		
					pstmt.executeUpdate();
		
					conn.close();
					DGen.aggiornaFileSetting();
				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
	}
	
	public static void aggiornaAttivitaID(Attivita atti) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					String sql = "UPDATE " + mydbhelper.TABELLA_ATTIVITA + "  SET "
							+ mydbhelper.COLUMN_ATTIVITA_DATAATIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_ATTIVITA_DESCRIZIONEATTIVITA + " = ?  ,"
							+ mydbhelper.COLUMN_ATTIVITA_C1 + " = ? ,"
							+ mydbhelper.COLUMN_ATTIVITA_C2 + " = ? "
							+ " WHERE " + mydbhelper.COLUMN_ATTIVITA_ID + " = ? ";
		
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setLong(1, atti.getpDataAttivita());
					pstmt.setString(2, atti.getpDescrizioneAttività());
					//calendario pstmt.setInt(3, 1); 
					pstmt.setInt(3, atti.getpFattaAttivita());
					pstmt.setInt(4, atti.getpTipoAttivita());
					pstmt.setLong(5, atti.getpIdAttivita());
					
					pstmt.executeUpdate();
		
					conn.close();
					DGen.aggiornaFileSetting();
					/*
					 * Class.forName("org.sqlite.JDBC");
					 * 
					 * conn = DriverManager.getConnection(mydbhelper.pathDataBase); stat
					 * = conn.createStatement(); stat.execute("UPDATE " +
					 * mydbhelper.TABELLA_ATTIVITA+ " SET " +
					 * mydbhelper.COLUMN_ATTIVITA_DATAATIVITA +
					 * " = '"+atti.getpDataAttivita() +"',  " +
					 * mydbhelper.COLUMN_ATTIVITA_DESCRIZIONEATTIVITA +
					 * " = '"+atti.getpDescrizioneAttività() +"' WHERE " +
					 * mydbhelper.COLUMN_ATTIVITA_ID + " = "+atti.getpIdAttivita()+"");
					 * 
					 * conn.close(); DGen.aggiornaFileSetting();
					 */
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	}

	public static void aggiornaTipologiaID(long idtipo, String descrizioneTipo,
			int colore, int selezione) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
		
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					stat.execute("UPDATE " + mydbhelper.TABELLA_TIPOLOGIA + " SET "
							+ mydbhelper.COLUMN_DESCRIZIONETIPOLOGIA + " = '"
							+ descrizioneTipo + "', "
							+ mydbhelper.COLUMN_COLORETIPOLOIGA + " = " + colore + ","
							+ mydbhelper.COLUMN_SELEZIONETIPOLOGIA + " = " + selezione
							+ " WHERE " + mydbhelper.COLUMN_IDTIPOLOGIA + " = "
							+ idtipo + "");
		
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		

	}
	public static void aggiornaProprieta(long idPro, String descrizione,
			boolean boo, long num) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;
		int booNum=0;
		if (boo) booNum=1;
		
		if (DLock.isLocked==false)
				{	try {
					Class.forName("org.sqlite.JDBC");
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
					
					stat.execute("UPDATE " + mydbhelper.TABELLA_PROPRIETA + " SET "
							+ mydbhelper.COLUMN_PROTXT + " = '"
							+ descrizione + "', "
							+ mydbhelper.COLUMN_PROBOO + " = " + booNum + ","
							+ mydbhelper.COLUMN_PRONUM + " = " + num
							+ " WHERE " + mydbhelper.COLUMN_PROID + " = "
							+ idPro + "");
					
					conn = DriverManager.getConnection(mydbhelper.pathDataBase);
					stat = conn.createStatement();
				
					
					
					conn.close();
					DGen.aggiornaFileSetting();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		}else{
			DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
		}
		
		
		

	}
	
	public static int tipoUltimaAttivita(long idcliente) {
		int result = 0;
		
		try {
			
			
			List<Attivita> attivitaLista = getAttivitaClienteNome(DData.decrescente, mydbhelper.COLUMN_ATTIVITA_DATAATIVITA, 1, "");
			result = attivitaLista.get(0).getpTipoAttivita();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return result;
	}
	
	public static long massimaDataattivita(long idcliente) {
		long result = 0;
		
		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager
					.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();
			// select * from posizionegps group by mesiattivita order by
			// mesiattivita desc
			// ResultSet rs = stat.executeQuery("select * from " +
			// mydbhelper.TABLE_POSIZIONE_GPS);
			ResultSet rs = stat.executeQuery("select MAX ( "
					+ mydbhelper.COLUMN_ATTIVITA_DATAATIVITA + ") from "
					+ mydbhelper.TABELLA_ATTIVITA + " WHERE "
					+ mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + " = " + idcliente + " AND "
					+ mydbhelper.COLUMN_ATTIVITA_C1 + " = 1" );

			while (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return result;
	}
	
	public static long massimaDataattivitaTabellaGps(long idcliente) {
		long result = 0;
		
		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager
					.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();
			// select * from posizionegps group by mesiattivita order by
			// mesiattivita desc
			// ResultSet rs = stat.executeQuery("select * from " +
			// mydbhelper.TABLE_POSIZIONE_GPS);
			ResultSet rs = stat.executeQuery("select MAX ( "
					+ mydbhelper.COLUMN_DATAATTIVITA + ") from "
					+ mydbhelper.TABLE_POSIZIONE_GPS + " WHERE "
					+ mydbhelper.COLUMN_ID + " = " + idcliente);

			while (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return result;
	}

	public static long massimaDataattivitaTabellaAttivita(long idcliente) {
		long result = 0;

		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager
					.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();
			// select * from posizionegps group by mesiattivita order by
			// mesiattivita desc
			// ResultSet rs = stat.executeQuery("select * from " +
			// mydbhelper.TABLE_POSIZIONE_GPS);
			ResultSet rs = stat.executeQuery("select MAX ( "
					+ mydbhelper.COLUMN_ATTIVITA_DATAATIVITA + ") from "
					+ mydbhelper.TABELLA_ATTIVITA + " WHERE "
					+ mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + " = " + idcliente);

			while (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static List<String> raggruppaMesi() {
		List<String> listaMesi = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager
					.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();
			// select * from posizionegps group by mesiattivita order by
			// mesiattivita desc
			// ResultSet rs = stat.executeQuery("select * from " +
			// mydbhelper.TABLE_POSIZIONE_GPS);
			
				
			ResultSet rs = stat.executeQuery("select * from "
					+ mydbhelper.TABLE_POSIZIONE_GPS + " WHERE " + mydbhelper.COLUMN_MESIATTIVITA + "<=0 " 
					+ " GROUP BY "
					+ mydbhelper.COLUMN_MESIATTIVITA  
					+ " ORDER BY "+ mydbhelper.COLUMN_MESIATTIVITA + " DESC ");

			listaMesi.add(DString.cmbTuttiMesi);
			DData.HashIDComboMesi.put(0, (long) 1);
			int i = 1;
			while (rs.next()) {
				posizioneGps cli = cursorToCliente(rs);
				if (cli.getpMesiAttivita() > -99) {
					listaMesi.add(-cli.getpMesiAttivita() + " "
							+ DString.cmmesi);

					DData.HashIDComboMesi.put(i, cli.getpMesiAttivita());

					i++;
				}
			}
			listaMesi.add(DString.cmb99);

			DData.HashIDComboMesi.put(i, (long) -100);
			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaMesi;
	}

	public static List<posizioneGps> getAllClienti() {
		List<posizioneGps> listatClienti = new ArrayList<posizioneGps>();
		List<Tipologia> listatipologia = new ArrayList<Tipologia>();
		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager
					.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery("select * from "
					+ mydbhelper.TABLE_POSIZIONE_GPS);

			int i = 0;

			while (rs.next()) {
				posizioneGps cli = cursorToCliente(rs);
				listatClienti.add(cli);
				i++;
			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listatClienti;

	}
	public static List<Proprieta> getAllProprieta() {
		List<Proprieta> listaproprieta = new ArrayList<Proprieta>();
	
		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery("select * from "+ mydbhelper.TABELLA_PROPRIETA);

			int i = 0;

			while (rs.next()) {
				Proprieta pro = cursorToProprieta(rs);
				listaproprieta.add(pro);
				i++;
			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaproprieta;

	}
	
	public static FileSincronizzati getFile(long idFile) throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		FileSincronizzati files = new FileSincronizzati();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_FILE + " WHERE "
					+ mydbhelper.FILE_ID + "=" + idFile
					);

			files = cursorToFile(rs);

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return files;

	}
	
	public static Referenti getReferente(long idrefente) throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		Referenti referente = new Referenti();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_REFERENTI + " WHERE "
					+ mydbhelper.COLUMN_REFERENTI_ID + "=" + idrefente
					+ " ORDER BY " + mydbhelper.COLUMN_REFERENTI_COGNOME
					+ " ASC");

			referente = cursorToReferente(rs);

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return referente;

	}
	/*
	sql = "SELECT * FROM " + mydbhelper.TABELLA_ATTIVITA + " LEFT JOIN "+ TABLE_POSIZIONE_GPS + " ON " + 
			mydbhelper.TABELLA_ATTIVITA+"."+mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
			"."+ mydbhelper.COLUMN_ID + " WHERE " +mydbhelper.COLUMN_ATTIVITA_C1 + " = " +  selezionefatto +
			" ORDER BY "+ colonna + " COLLATE NOCASE " + ordinamento;*/
	
	public static List<Offerta> getOffertaClienteTutti(long idCliente, String ordinamento, String colonna, int  selezione, String ricerca) throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		String sql="";
		List<Offerta> listaOfferta = new ArrayList<Offerta>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			String whereCondizione = "";
			
			String sqlWhere =" WHERE " ;
			String SqlOrder = " ORDER BY "+ colonna + " COLLATE NOCASE " + ordinamento;
			String sqlSelezionetabelle = "SELECT * FROM " + mydbhelper.TABELLA_OFFERTA + " LEFT JOIN "+  mydbhelper.TABLE_POSIZIONE_GPS + " ON " + 
				mydbhelper.TABELLA_OFFERTA+"."+mydbhelper.OFFERTA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
				"."+ mydbhelper.COLUMN_ID + " ";
			
			String sqlWhereRicerca="";
			if (idCliente>=0)   sqlWhere = sqlWhere + mydbhelper.OFFERTA_IDCLIENTE + " = "+ idCliente + " AND ";
			String parametroDiRicerca="";
			if (idCliente<0)
			{
				parametroDiRicerca = " LIKE '%" + ricerca + "%' ";
			}else{
				parametroDiRicerca = " LIKE '%" + ricerca + "%' ";
			}
			
			int vintaPersa=100;
			if (selezione == 2) vintaPersa=0;
			if (ricerca.length()>0) 
				{if (idCliente<0)
					{sqlWhereRicerca = "( " + mydbhelper.TABELLA_OFFERTA+"." + mydbhelper.OFFERTA_DESCRIZIONE+  parametroDiRicerca +  " OR " + mydbhelper.TABLE_POSIZIONE_GPS+"." + mydbhelper.COLUMN_NAME + parametroDiRicerca + ") " ;
					
					}else{
						sqlWhereRicerca = mydbhelper.OFFERTA_DESCRIZIONE+  parametroDiRicerca;
					}
				
				}
			
			switch (selezione) {
			case 0:
				sqlWhere = sqlWhere +mydbhelper.OFFERTA_PERCENTUALECHIUSURA + " <100 AND " + mydbhelper.OFFERTA_PERCENTUALECHIUSURA + ">0 ";
				break;
			case 1:
				sqlWhere = sqlWhere + mydbhelper.OFFERTA_PERCENTUALECHIUSURA + " = " + vintaPersa;
				break;
			case 2:
				sqlWhere = sqlWhere + mydbhelper.OFFERTA_PERCENTUALECHIUSURA + " = " + vintaPersa;
				break;
			case 3:
				
				
				
				break; 

			default:
				break;
			}
			if (ricerca.length()>0) 
			{if (selezione ==3)
				{sqlWhere = sqlWhere + sqlWhereRicerca;
				
				}else{
					sqlWhere = sqlWhere + " AND " + sqlWhereRicerca;
				}
			
			}else{
				if (selezione==3)
				{
					if (idCliente>=0)   
						{sqlWhere = " WHERE " + mydbhelper.OFFERTA_IDCLIENTE + " = "+ idCliente;
						
						}else{
							sqlWhere = "";
						}
				}
				
			}
			sql = sqlSelezionetabelle + sqlWhere + SqlOrder;
			
			
			
			rs = stat.executeQuery(sql);
			
			int i =1;
			while (rs.next()) {

				Offerta tipo = cursorToOffertaNome(rs);
				listaOfferta.add(tipo);
				i++;
			
			}
			
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaOfferta;

	}
	
	public static List<Offerta> getOffertaClienteNome(long idCliente, String ordinamento, String colonna, int  selezione, String ricerca) throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		String sql="";
		List<Offerta> listaOfferta = new ArrayList<Offerta>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			String whereCondizione = "";
			
			String sqlWhere =" WHERE " ;
			String SqlOrder = " ORDER BY "+ colonna + " COLLATE NOCASE " + ordinamento;
			String sqlSelezionetabelle = "SELECT * FROM " + mydbhelper.TABELLA_OFFERTA + " LEFT JOIN "+  mydbhelper.TABLE_POSIZIONE_GPS + " ON " + 
				mydbhelper.TABELLA_OFFERTA+"."+mydbhelper.OFFERTA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
				"."+ mydbhelper.COLUMN_ID + " ";
			
			String sqlWhereRicerca="";
			if (idCliente>=0)   sqlWhere = sqlWhere + mydbhelper.OFFERTA_IDCLIENTE + " = "+ idCliente + " AND ";
			String parametroDiRicerca="";
			if (idCliente<0)
			{
				parametroDiRicerca = " LIKE '%" + ricerca + "%' ";
			}else{
				parametroDiRicerca = " LIKE '%" + ricerca + "%' ";
			}
			
			int vintaPersa=100;
			if (selezione == 2) vintaPersa=0;
			if (ricerca.length()>0) 
				{if (idCliente<0)
					{sqlWhereRicerca = "( " + mydbhelper.TABELLA_OFFERTA+"." + mydbhelper.OFFERTA_DESCRIZIONE+  parametroDiRicerca +  " OR " + mydbhelper.TABLE_POSIZIONE_GPS+"." + mydbhelper.COLUMN_NAME + parametroDiRicerca + ") " ;
					
					}else{
						sqlWhereRicerca = mydbhelper.OFFERTA_DESCRIZIONE+  parametroDiRicerca;
					}
				
				}
			
			switch (selezione) {
			case 0:
				sqlWhere = sqlWhere +mydbhelper.OFFERTA_PERCENTUALECHIUSURA + " <100 AND " + mydbhelper.OFFERTA_PERCENTUALECHIUSURA + ">0 ";
				break;
			case 1:
				sqlWhere = sqlWhere + mydbhelper.OFFERTA_PERCENTUALECHIUSURA + " = " + vintaPersa;
				break;
			case 2:
				sqlWhere = sqlWhere + mydbhelper.OFFERTA_PERCENTUALECHIUSURA + " = " + vintaPersa;
				break;
			case 3:
				
				
				
				break; 

			default:
				break;
			}
			if (ricerca.length()>0) 
			{if (selezione ==3)
				{sqlWhere = sqlWhere + sqlWhereRicerca;
				
				}else{
					sqlWhere = sqlWhere + " AND " + sqlWhereRicerca;
				}
			
			}else{
				if (selezione==3)
				{
					if (idCliente>=0)   
						{sqlWhere = " WHERE " + mydbhelper.OFFERTA_IDCLIENTE + " = "+ idCliente;
						
						}else{
							sqlWhere = "";
						}
				}
				
			}
			sql = sqlSelezionetabelle + sqlWhere + SqlOrder;
			
			
			
			rs = stat.executeQuery(sql);
			
			int i =1;
			while (rs.next()) {

				Offerta tipo = cursorToOffertaNome(rs);
				listaOfferta.add(tipo);
				i++;
			
			}
			
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaOfferta;

	}
	public static List<Attivita> getAttivitaClienteNome(String ordinamento, String colonna, int selezionefatto, String ricerca) throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		String sql="";
		List<Attivita> listaAttivita = new ArrayList<Attivita>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			if (selezionefatto<2) //00ad fare;1=fatto;2=tutti

			{	
						if (ricerca.length()==0)
						{
						sql = "SELECT * FROM " + mydbhelper.TABELLA_ATTIVITA + " LEFT JOIN "+ mydbhelper.TABLE_POSIZIONE_GPS + " ON " + 
								mydbhelper.TABELLA_ATTIVITA+"."+mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
								"."+ mydbhelper.COLUMN_ID + " WHERE " +mydbhelper.COLUMN_ATTIVITA_C1 + " = " +  selezionefatto +
								" ORDER BY "+ colonna + " COLLATE NOCASE " + ordinamento;
								//porov sopra e poi metto where
								//
						}else{
							String parametroDiRicerca = " LIKE '%" + ricerca + "%' ";
							//String parametroDiRicerca = " 'unisalute' ";
							sql = "SELECT * FROM " + mydbhelper.TABELLA_ATTIVITA + " LEFT JOIN "+ mydbhelper.TABLE_POSIZIONE_GPS + " ON " + 
									mydbhelper.TABELLA_ATTIVITA+"."+mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
									"."+ mydbhelper.COLUMN_ID + " WHERE " +mydbhelper.COLUMN_ATTIVITA_C1 + " = " +  selezionefatto +
									 " AND " +  mydbhelper.TABLE_POSIZIONE_GPS+"." + mydbhelper.COLUMN_NAME +  parametroDiRicerca + 
									" ORDER BY "+ colonna + " COLLATE NOCASE " + ordinamento;
						}
			}else{
				
				if (ricerca.length()==0)
				{
				sql = "SELECT * FROM " + mydbhelper.TABELLA_ATTIVITA + " LEFT JOIN "+ mydbhelper.TABLE_POSIZIONE_GPS + " ON " + 
						mydbhelper.TABELLA_ATTIVITA+"."+mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
						"."+ mydbhelper.COLUMN_ID + " ORDER BY "+ colonna + " " + ordinamento;
						//porov sopra e poi metto where
						//
				}else{
					String parametroDiRicerca = " LIKE '%" + ricerca + "%' ";
					//String parametroDiRicerca = " 'unisalute' ";
					sql = "SELECT * FROM " + mydbhelper.TABELLA_ATTIVITA + " LEFT JOIN "+ mydbhelper.TABLE_POSIZIONE_GPS + " ON " + 
							mydbhelper.TABELLA_ATTIVITA+"."+mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
							"."+ mydbhelper.COLUMN_ID + " WHERE " + mydbhelper.TABLE_POSIZIONE_GPS+"." + mydbhelper.COLUMN_NAME +  parametroDiRicerca + 
							" ORDER BY "+ colonna + " " + ordinamento;
				}
				
				
			}
			
			rs = stat.executeQuery(sql);

			while (rs.next()) {

				Attivita tipo = cursorToAttivitaNome(rs);
				listaAttivita.add(tipo);
			}

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaAttivita;

	}
	
	public static Offerta getOfferta(long idOff) throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		Offerta off = new Offerta();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			String sql = "SELECT * FROM " + mydbhelper.TABELLA_OFFERTA + " LEFT JOIN "+  mydbhelper.TABLE_POSIZIONE_GPS + " ON " + 
			mydbhelper.TABELLA_OFFERTA+"."+mydbhelper.OFFERTA_IDCLIENTE + " = " + mydbhelper.TABLE_POSIZIONE_GPS+
			"."+ mydbhelper.COLUMN_ID + " " + " WHERE "
			+ mydbhelper.OFFERTA_ID + "=" + idOff;
			rs = stat.executeQuery(sql);
			off = cursorToOffertaNome(rs);
			

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return off;

	}
	
	
	public static Attivita getAttivita(long idatti) throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		Attivita atti = new Attivita();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_ATTIVITA + " WHERE "
					+ mydbhelper.COLUMN_ATTIVITA_ID + "=" + idatti);

			atti = cursorToAttivita(rs);

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return atti;

	}

	public static List<Referenti> getAllReferentiExport() throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		List<Referenti> listareferenti = new ArrayList<Referenti>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_REFERENTI);

			while (rs.next()) {
				Referenti tipo = cursorToReferente(rs);

				listareferenti.add(tipo);
			}

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listareferenti;

	}
	
	
	
	public static List<FileSincronizzati> getAllFileSincronizzati(long idcliente)
			throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		List<FileSincronizzati> listaFiles = new ArrayList<FileSincronizzati>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_FILE + " WHERE "
					+ mydbhelper.FILE_IDCLIENTE + "=" + idcliente + " AND " + mydbhelper.FILE_TMP1INT + ">=0"
					);

			while (rs.next()) {
				FileSincronizzati tipo = cursorToFile(rs);

				listaFiles.add(tipo);
			}

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaFiles;

	}
	
	
	public static List<Referenti> getAllReferenti(long idcliente)
			throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		List<Referenti> listareferenti = new ArrayList<Referenti>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_REFERENTI + " WHERE "
					+ mydbhelper.COLUMN_REFERENTI_IDCLIENTE + "=" + idcliente
					+ " ORDER BY " + mydbhelper.COLUMN_REFERENTI_COGNOME
					+ " ASC");

			while (rs.next()) {
				Referenti tipo = cursorToReferente(rs);

				listareferenti.add(tipo);
			}

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listareferenti;

	}

	public static List<Attivita> getAllAttivitaExport() throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		List<Attivita> listaAttivita = new ArrayList<Attivita>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_ATTIVITA);

			while (rs.next()) {

				Attivita tipo = cursorToAttivita(rs);
				listaAttivita.add(tipo);
			}

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaAttivita;

	}

	public static List<Attivita> getAllAttivita(long idcliente)
			throws SQLException {
		Connection conn;
		Statement stat;
		ResultSet rs;
		List<Attivita> listaAttivita = new ArrayList<Attivita>();

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from "
					+ mydbhelper.TABELLA_ATTIVITA + " WHERE "
					+ mydbhelper.COLUMN_ATTIVITA_IDCLIENTE + "=" + idcliente
					+ " ORDER BY " + mydbhelper.COLUMN_ATTIVITA_DATAATIVITA
					+ " DESC");

			while (rs.next()) {

				Attivita tipo = cursorToAttivita(rs);
				listaAttivita.add(tipo);
			}

			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaAttivita;

	}

	public static void aggiornaDistanzaProiezioni(double latiProiezioneNew,
			double longiProiezioneNew) {
		Connection conn;
		Statement stat;
		ResultSet rs = null;
		int intValue = 0;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(mydbhelper.pathDataBase);
			stat = conn.createStatement();
			stat.execute("UPDATE " + mydbhelper.TABLE_POSIZIONE_GPS + " SET "
					+ mydbhelper.COLUMN_DISTANZA + " = (("
					+ mydbhelper.COLUMN_PROIEZIONE_LATITUDINE + " - "
					+ latiProiezioneNew + " ) * ( "
					+ mydbhelper.COLUMN_PROIEZIONE_LATITUDINE + " - "
					+ latiProiezioneNew + " ) + ( "
					+ mydbhelper.COLUMN_PROIEZIONE_LONGITUDINE + " - "
					+ longiProiezioneNew + " ) * ( "
					+ mydbhelper.COLUMN_PROIEZIONE_LONGITUDINE + " - "
					+ longiProiezioneNew + " )), "
					+ mydbhelper.COLUMN_CLIENTEVISUALIZZATO + " = 0 ");

			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static posizioneGps cursorToCliente(ResultSet cursor) {
		posizioneGps posGPS = new posizioneGps();

		try {

			posGPS.setpId(cursor.getLong(mydbhelper.COLUMN_ID));
			posGPS.setpTipo(cursor.getLong(mydbhelper.COLUMN_TIPOLOGIA));
			posGPS.setpnome(cursor.getString(mydbhelper.COLUMN_NAME));
			posGPS.setpDataInMillis(cursor
					.getLong(mydbhelper.COLUMN_DATA_IN_MILLIS));
			posGPS.setpLatitudine(cursor
					.getDouble(mydbhelper.COLUMN_LATITUDINE));
			posGPS.setpLongitudine(cursor
					.getDouble(mydbhelper.COLUMN_LONGITUDINE));
			posGPS.setpMemo(cursor.getString(mydbhelper.COLUMN_MEMO));
			posGPS.setpInidirizzo(cursor.getString(mydbhelper.COLUMN_INDIRIZZO));
			posGPS.setpCittà(cursor.getString(mydbhelper.COLUMN_CITTA));
			posGPS.setpDistanza(cursor.getFloat(mydbhelper.COLUMN_DISTANZA));
			posGPS.setpPrecisione(cursor.getLong(mydbhelper.COLUMN_PRECISIONE));

			posGPS.setpCap(cursor.getString(mydbhelper.COLUMN_CAP));
			posGPS.setpProvincia(cursor.getString(mydbhelper.COLUMN_PROVINCIA));
			posGPS.setptelefono(cursor.getString(mydbhelper.COLUMN_TELEFONO));
			posGPS.setpFax(cursor.getString(mydbhelper.COLUMN_FAX));
			posGPS.setpSito(cursor.getString(mydbhelper.COLUMN_SITO));
			posGPS.setpNazione(cursor.getString(mydbhelper.COLUMN_NAZIONE));
			posGPS.setpSettato(cursor
					.getBoolean(mydbhelper.COLUMN_CLIENTEVISUALIZZATO));

			posGPS.setpLatitudineProi(cursor
					.getDouble(mydbhelper.COLUMN_PROIEZIONE_LATITUDINE));
			posGPS.setpLongitudineProi(cursor
					.getDouble(mydbhelper.COLUMN_PROIEZIONE_LONGITUDINE));

			posGPS.setpDataAttività(cursor
					.getLong(mydbhelper.COLUMN_DATAATTIVITA));
			posGPS.setpGiorniAttività(cursor
					.getLong(mydbhelper.COLUMN_GIORNIATTIVITA));
			posGPS.setpMesiAttivita(cursor.getLong(mydbhelper.COLUMN_MESIATTIVITA));
			posGPS.setpFree1(cursor.getString(mydbhelper.COLUMN_GPSFREE1));
			posGPS.setpFree2(cursor.getString(mydbhelper.COLUMN_GPSFREE2));
			posGPS.setpFree3(cursor.getString(mydbhelper.COLUMN_GPSFREE3));
			posGPS.setpFree4(cursor.getString(mydbhelper.COLUMN_GPSFREE4));
			
			
			posGPS.setpTipoattivita(cursor.getInt(mydbhelper.COLUMN_TIPOATTIVITA));
			posGPS.setpSpare1(cursor.getLong(mydbhelper.COLUMN_SPARE1));
			posGPS.setpSpare2(cursor.getLong(mydbhelper.COLUMN_SPARE2));
			posGPS.setpSpare3(cursor.getString(mydbhelper.COLUMN_SPARE3));
			posGPS.setpSpare4(cursor.getString(mydbhelper.COLUMN_SPARE4));
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return posGPS;
	}

	public static Referenti cursorToReferente(ResultSet cursor) {
		Referenti ref = new Referenti();
		try {

			ref.setpIdReferente(cursor.getLong(mydbhelper.COLUMN_REFERENTI_ID));
			ref.setpIdClienteReferente(cursor.getLong(mydbhelper.COLUMN_REFERENTI_IDCLIENTE));
			ref.setpNomeRefente(cursor.getString(mydbhelper.COLUMN_REFERENTI_NOME));
			ref.setpCognomeReferente(cursor.getString(mydbhelper.COLUMN_REFERENTI_COGNOME));
			ref.setpFunzioneReferente(cursor.getString(mydbhelper.COLUMN_REFERENTI_FUNZIONE));
			ref.setpTelReferente(cursor.getString(mydbhelper.COLUMN_REFERENTI_TELEFONO));
			ref.setpFaxReferente(cursor.getString(mydbhelper.COLUMN_REFERENTI_FAX));
			ref.setpCelReferentea(cursor.getString(mydbhelper.COLUMN_REFERENTI_CELLULARE));
			ref.setpMailReferentea(cursor.getString(mydbhelper.COLUMN_REFERENTI_MAIL));
			ref.setpMemoReferente(cursor.getString(mydbhelper.COLUMN_REFERENTI_NOTE));
			// ref.setpImmagineRefente(cursor.getBlob(10));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ref;

	}
	
	public static FileSincronizzati cursorToFile(ResultSet cursor)
	{FileSincronizzati file = new FileSincronizzati();
	try {
		file.setpIdFile(cursor.getLong(mydbhelper.FILE_ID));
		file.setpIdCliente(cursor.getLong(mydbhelper.FILE_IDCLIENTE));
		file.setpNomeFileOriginario(cursor.getString(mydbhelper.FILE_NOMEFILEORI));
		file.setpNomeFileOModificato(cursor.getString(mydbhelper.FILE_NOMEFILEMODIFICATO));
		file.setpServizioCondivizione(cursor.getInt(mydbhelper.FILE_SERVIZIODICONDIVIZIONE));
		file.setpMd5(cursor.getString(mydbhelper.FILE_MD5));
		file.setpDimensioneFile(cursor.getLong(mydbhelper.FILE_DIMENSIONE));
		file.setpDataFile(cursor.getLong(mydbhelper.FILE_DATAFILE));
		file.setpDataUploadFile(cursor.getLong(mydbhelper.FILE_DATAUPLOAD));
		
		file.setpDescrizione(cursor.getString(mydbhelper.FILE_DESCRIZIONE));
		file.setpPathOriginario(cursor.getString(mydbhelper.FILE_TMP1STRING));
		file.setptmp2String(cursor.getString(mydbhelper.FILE_TMP2STRING));
		
		file.setpDaUplodare(cursor.getInt(mydbhelper.FILE_TMP1INT));
		file.setpTmp2Int(cursor.getInt(mydbhelper.FILE_TMP2INT));
	} catch (Exception e) {
		// TODO: handle exception
	}
		
		
	
	return file;
	}

	public static Tipologia cursorToTipologia(ResultSet cursor) {
		Tipologia tipo = new Tipologia();

		try {
			tipo.setpIdTipologia(cursor.getLong(mydbhelper.COLUMN_IDTIPOLOGIA));
			tipo.setpDescrizioneTipologia(cursor
					.getString(mydbhelper.COLUMN_DESCRIZIONETIPOLOGIA));
			tipo.setpColoreTipologia((cursor
					.getInt(mydbhelper.COLUMN_COLORETIPOLOIGA)));
			tipo.setpSelezionaTipologia((cursor
					.getInt(mydbhelper.COLUMN_SELEZIONETIPOLOGIA)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tipo;
	}
	
	public static Offerta cursorToOffertaNome(ResultSet cursor)
	{Offerta off = new Offerta();
	try {
		off.setpIdOfferta(cursor.getLong(mydbhelper.OFFERTA_ID));
		off.setpIdClienteOfferta(cursor.getLong(mydbhelper.OFFERTA_IDCLIENTE));
		off.setpIdTipologiaOfferta(cursor.getLong(mydbhelper.OFFERTA_IDTIPOLOGIA));
		off.setpNomeCliente(cursor.getString(mydbhelper.COLUMN_NAME));
		off.setpDescrizioneOfferta(cursor.getString(mydbhelper.OFFERTA_DESCRIZIONE));
		off.setpNota(cursor.getString(mydbhelper.OFFERTA_NOTA));
		off.setpTmp1(cursor.getString(mydbhelper.OFFERTA_TMP1));
		off.setpDataPresentazione(cursor.getLong(mydbhelper.OFFERTA_DATAPRESENTAZIONE));
		off.setpDataChiusura(cursor.getLong(mydbhelper.OFFERTA_DATACHIUSURA));
		
		off.setpImportoOfferta(cursor.getDouble(mydbhelper.OFFERTA_IMPORTO));
		off.setpPercentuale(cursor.getLong(mydbhelper.OFFERTA_PERCENTUALECHIUSURA));
		
		if (cursor.getInt(mydbhelper.OFFERTA_ACLIENTE)==0)
		{
		off.setpAcliente(false);
		}else{
			off.setpAcliente(true);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	return off;
	}
	
	public static Attivita cursorToAttivita(ResultSet cursor) {
		Attivita att = new Attivita();
		try {
			att.setpIdAttivita(cursor.getLong(mydbhelper.COLUMN_ATTIVITA_ID));
			att.setpIdCliente(cursor
					.getLong(mydbhelper.COLUMN_ATTIVITA_IDCLIENTE));
			// att.setpIdRefente(cursor.getLong(2)); daimplementare
			// att.setpIdTipoAttivita(cursor.getLong(3)); daimplementare
			att.setpDataAttivita(cursor
					.getLong(mydbhelper.COLUMN_ATTIVITA_DATAATIVITA));
			// att.setpDataAllarme(cursor.getLong(5)); daimplementare
			att.setpDescrizioneAttivita(cursor
					.getString(mydbhelper.COLUMN_ATTIVITA_DESCRIZIONEATTIVITA));
			att.setpFattaAttivita(cursor.getInt(mydbhelper.COLUMN_ATTIVITA_C1)); 
			att.setpTipoAttivita(cursor.getInt(mydbhelper.COLUMN_ATTIVITA_C2));
			// att.setpc3(cursor.getString(9)); daimplementare
		} catch (Exception e) {
			// TODO: handle exception
		}
		return att;
	}
	
	public static Attivita cursorToAttivitaNome(ResultSet cursor) {
		Attivita att = new Attivita();
		try {
			att.setpIdAttivita(cursor.getLong(mydbhelper.COLUMN_ATTIVITA_ID));
			att.setpIdCliente(cursor
					.getLong(mydbhelper.COLUMN_ATTIVITA_IDCLIENTE));
			// att.setpIdRefente(cursor.getLong(2)); daimplementare
			// att.setpIdTipoAttivita(cursor.getLong(3)); daimplementare
			att.setpDataAttivita(cursor
					.getLong(mydbhelper.COLUMN_ATTIVITA_DATAATIVITA));
			// att.setpDataAllarme(cursor.getLong(5)); daimplementare
			att.setpDescrizioneAttivita(cursor
					.getString(mydbhelper.COLUMN_ATTIVITA_DESCRIZIONEATTIVITA));
			att.setpFattaAttivita(cursor.getInt(mydbhelper.COLUMN_ATTIVITA_C1)); 
			att.setpTipoAttivita(cursor.getInt(mydbhelper.COLUMN_ATTIVITA_C2));
		
			// att.setpc3(cursor.getString(9)); daimplementare
			try {
				att.setpNomeClienteAttivita(cursor.getString(13));
				//Log.i("__DATA SOUCE__ NAME", att.getpNomeClienteAttivita());
			} catch (Exception e) {
				att.setpNomeClienteAttivita("");
				//Log.i("__DATA SOUCE__ NAME", att.getpNomeClienteAttivita());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return att;
	}
	
		public static Proprieta cursorToProprieta(ResultSet cursor) {
			Proprieta pro = new Proprieta();
			try {
				
				pro.setPIdPropieta(cursor.getLong(mydbhelper.COLUMN_PROID));
				pro.setpNomeProprieta(cursor.getString(mydbhelper.COLUMN_PRONAME));	
				pro.setpTxtProprieta(cursor.getString(mydbhelper.COLUMN_PROTXT));
				pro.setpBooProprieta(cursor.getBoolean(mydbhelper.COLUMN_PROBOO));
				pro.setPLongProprieta(cursor.getLong(mydbhelper.COLUMN_PRONUM));
			
			} catch (Exception e) {
				// TODO: handle exception
			}

		return pro;
	}

}





public class mydbhelper  {
	public static final String pathDataBaseold = "jdbc:sqlite:C:\\Users\\Enrico\\workspace\\apk\\Clienti.db";
	public static final String prefissoPathDatabase = "jdbc:sqlite:";
	public static String pathDataBase = "";
	public static final String CRESCENTE = "ASC";
	public static final String DECRESCENTE = "DESC";
	
	
	public static final String TABLE_POSIZIONE_GPS= "posizionegps";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TIPOLOGIA = "tipo";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DATA_IN_MILLIS = "datainmillis";
	public static final String COLUMN_LATITUDINE = "latitudine";
	public static final String COLUMN_LONGITUDINE = "longitudine";
	public static final String COLUMN_MEMO = "memo";
	public static final String COLUMN_INDIRIZZO = "indirizzo";
	public static final String COLUMN_CITTA = "citta";
	public static final String COLUMN_DISTANZA = "distanza";
	public static final String COLUMN_PRECISIONE = "precisione";
	public static final String COLUMN_IMAGE = "image";
	//aggiunte per indirizzo completo
	public static final String COLUMN_CAP = "cap";
	public static final String COLUMN_PROVINCIA = "provincia";
	public static final String COLUMN_TELEFONO = "telefono";
	public static final String COLUMN_FAX = "fax";
	public static final String COLUMN_SITO = "www";
	public static final String COLUMN_NAZIONE = "nazione";
	
	public static final String COLUMN_PROIEZIONE_LATITUDINE = "proiezionelatitudine";
	public static final String  COLUMN_PROIEZIONE_LONGITUDINE = "proiezionelongitudine";
	public static final String  COLUMN_CLIENTEVISUALIZZATO = "clientevisualizzato";
	
	public static final String COLUMN_DATAATTIVITA = "dataattivita";
	public static final String  COLUMN_GIORNIATTIVITA = "giorniattivita";
	public static final String  COLUMN_MESIATTIVITA = "mesiattivita";
	
	public static final String COLUMN_GPSFREE1 = "free1"; 
    public static final String COLUMN_GPSFREE2 = "free2"; 
    public static final String COLUMN_GPSFREE3 = "free3"; 
    public static final String COLUMN_GPSFREE4 = "free4"; 
    
    public static final String  COLUMN_TIPOATTIVITA = "tipoattivita";
	
   	public static final String COLUMN_SPARE1 = "spare1"; 
       public static final String COLUMN_SPARE2 = "spare2"; 
       public static final String COLUMN_SPARE3 = "spare3"; 
       public static final String COLUMN_SPARE4 = "spare4"; 
    
	
	//tabella tipologia
	public static final String TABELLA_TIPOLOGIA= "tabellatipologia";
	public static final String COLUMN_IDTIPOLOGIA = "idtipologia";
	public static final String COLUMN_DESCRIZIONETIPOLOGIA = "descrizionetipologia";
	
	public static final String COLUMN_COLORETIPOLOIGA = "colore";
	public static final String COLUMN_SELEZIONETIPOLOGIA = "seleziona";
	
	//tabella immagine
	public static final String TABLE_IMMAGINE= "tabellaimmagine";
	public static final String COLUMN_IDIMMAGINE = "idImmagine";
	public static final String COLUMN_IDNOTA = "idNota";
	public static final String COLUMN_IMMAGINE = "Immagine";

	//tabella referenti
	public static final String TABELLA_REFERENTI= "tabellareferenti";
	public static final String COLUMN_REFERENTI_ID = "idreferente";
	public static final String COLUMN_REFERENTI_IDCLIENTE = "idreferentecliente";
	
	public static final String COLUMN_REFERENTI_NOME = "referentennome";
	public static final String COLUMN_REFERENTI_COGNOME = "referentencognome";
	public static final String COLUMN_REFERENTI_FUNZIONE = "referentenfunzione";
	public static final String COLUMN_REFERENTI_TELEFONO = "referententelefono";
	public static final String COLUMN_REFERENTI_CELLULARE = "referentencellulare";
	
	public static final String COLUMN_REFERENTI_FAX = "referentenfax";
	public static final String COLUMN_REFERENTI_MAIL = "referentemail";
	public static final String COLUMN_REFERENTI_NOTE = "referentenote";
	public static final String COLUMN_REFERENTI_IMMAGINE = "referenteimmagine";
	
	//tabella attività
	public static final String TABELLA_ATTIVITA= "tabellaattivita";
	public static final String COLUMN_ATTIVITA_ID = "idattività";
	public static final String COLUMN_ATTIVITA_IDCLIENTE = "idcliente";
	public static final String COLUMN_ATTIVITA_IDREFERENTE = "idreferente";
	public static final String COLUMN_ATTIVITA_IDTIPOATTIVITA= "idtipoattività";
	public static final String COLUMN_ATTIVITA_DATAATIVITA= "dataattività";
	public static final String COLUMN_ATTIVITA_DATAALLARME= "dataallarme";
	public static final String COLUMN_ATTIVITA_DESCRIZIONEATTIVITA = "descrizioneattivita";
	public static final String COLUMN_ATTIVITA_C1 = "temp1";
	public static final String COLUMN_ATTIVITA_C2 = "temp2";
	public static final String COLUMN_ATTIVITA_C3 = "temp3";
	
		
			
			
	
	 //TABELLA PROPRIETA 
    public static final String TABELLA_PROPRIETA= "tabellaproprieta"; 
    public static final String COLUMN_PROID = "idpro"; 
    public static final String COLUMN_PRONAME = "nomepro"; 
    public static final String COLUMN_PROTXT = "protext"; 
    public static final String COLUMN_PROBOO = "proboo"; 
    public static final String COLUMN_PRONUM = "pronum"; 
	
  //tabelle offerta
    public static final String TABELLA_OFFERTA= "tabellaofferta";
    public static final String OFFERTA_ID = "idofferta";
    public static final String OFFERTA_IDCLIENTE = "idcliente";
    public static final String OFFERTA_IDTIPOLOGIA = "idtipologia"; //da eventualemnte aggiungere
    public static final String OFFERTA_DESCRIZIONE = "descrizioneofferta";
    public static final String OFFERTA_IMPORTO = "importoofferta";
    public static final String OFFERTA_DATACHIUSURA = "datachiusura";
    public static final String OFFERTA_DATAPRESENTAZIONE = "datapresentazione";
    public static final String OFFERTA_PERCENTUALECHIUSURA = "percentualechiusura";
    public static final String OFFERTA_ACLIENTE= "offertapersa";
    public static final String OFFERTA_NOTA= "notaofferta";
    public static final String OFFERTA_TMP1= "varia1";
    
  //tabelle File
    public static final String TABELLA_FILE= "tabellaFILE";
    public static final String FILE_ID = "idfile"; //long
    public static final String FILE_IDCLIENTE = "idCliente"; //long
    public static final String FILE_NOMEFILEORI = "nomefileoriginario"; //text
    public static final String FILE_NOMEFILEMODIFICATO = "nomefilemodificato"; //NomeCliente_idcliente_nomefile
    public static final String FILE_SERVIZIODICONDIVIZIONE = "Servizio"; //INT DROPBOX/DRIVE
    public static final String FILE_MD5 = "md5"; //text
    public static final String FILE_DIMENSIONE = "dimensione"; //long
    public static final String FILE_DATAFILE = "datafile"; //long
    public static final String FILE_DATAUPLOAD = "dataupload"; //long
    public static final String FILE_DESCRIZIONE = "descrizione"; //text
    public static final String FILE_TMP1STRING = "tmp1string"; //text
    public static final String FILE_TMP2STRING = "tmp2string"; //text
    public static final String FILE_TMP1INT = "tmp1int"; //integer
    public static final String FILE_TMP2INT= "tmp2int"; //integer
				
			
			
	
	
	private static final String DATABASE_NAME = "Clienti.db";
	public static final int DATABASE_VERSION = 18; 
	//13 versione con tabella parametri
	
	
	//
	// Database creation sql statement
		private static final String DATABASE_CREATE = "create table "
				+ TABLE_POSIZIONE_GPS + " ( " + COLUMN_ID
				+ " integer primary key autoincrement, " + COLUMN_TIPOLOGIA
				+ " integer not null,"+ COLUMN_NAME
				+ " text," + COLUMN_DATA_IN_MILLIS + " integer not null," +
				COLUMN_LATITUDINE + " integer not null," + COLUMN_LONGITUDINE + " integer not null," + COLUMN_MEMO + " text, " +
				COLUMN_INDIRIZZO + " text," + COLUMN_CITTA + " text," + COLUMN_DISTANZA + " integer, "
				+COLUMN_PRECISIONE + " integer ,"+ COLUMN_CAP + " text," + COLUMN_PROVINCIA + " text," + COLUMN_TELEFONO +  " text," + 
				COLUMN_FAX +  " text," + COLUMN_SITO +  " text," + COLUMN_NAZIONE + " text, " + COLUMN_PROIEZIONE_LATITUDINE + 
				" integer, "  + COLUMN_PROIEZIONE_LONGITUDINE + " integer, " + COLUMN_CLIENTEVISUALIZZATO + " integer, " + 
				COLUMN_DATAATTIVITA + " integer, " + COLUMN_GIORNIATTIVITA + " integer, "+ COLUMN_MESIATTIVITA + " integer, "+
				COLUMN_GPSFREE1 + " text, " +COLUMN_GPSFREE2 + " text, " +COLUMN_GPSFREE3 + " text, " +COLUMN_GPSFREE4 + " text,"+
				COLUMN_TIPOATTIVITA + " integer, " + COLUMN_SPARE1 + " integer, " +COLUMN_SPARE2 + " integer, " +COLUMN_SPARE3 + " text, " +COLUMN_SPARE3 + " text "
				+");";
		
	private static final String DATABASE_CREATE_TIPOLOGIA = "create table "
			+ TABELLA_TIPOLOGIA + " ( " + COLUMN_IDTIPOLOGIA
			+ " integer primary key autoincrement, " + COLUMN_DESCRIZIONETIPOLOGIA
			+ " text, " + COLUMN_COLORETIPOLOIGA + " integer, " + COLUMN_SELEZIONETIPOLOGIA + " integer );";
	private static final String DATABASE_CREATE_IMMGAGINE = "create table "
			+ TABLE_IMMAGINE + " ( " + COLUMN_IDIMMAGINE
			+ " integer primary key autoincrement, " + COLUMN_IDNOTA
			+ " integer not null," + COLUMN_IMMAGINE + " byte[] );";
	/*private static final String DATABASE_CREATE_REFERENTE = "create table "
			+ TABELLA_REFERENTI + " ( " + COLUMN_REFERENTI_ID
			+ " integer primary key autoincrement, " + COLUMN_REFERENTI_IDCLIENTE
			+ " integer not null," + COLUMN_REFERENTI_NOME  + " text,  " + COLUMN_REFERENTI_COGNOME + " text,  " 
			 +COLUMN_REFERENTI_FUNZIONE  + " text,  " + COLUMN_REFERENTI_TELEFONO  + " text,  "  
			+COLUMN_REFERENTI_CELLULARE  + " text,  " + COLUMN_REFERENTI_FAX + " text,  "
			    + COLUMN_REFERENTI_MAIL  + " text,  " + COLUMN_REFERENTI_NOTE + "text, " 
			 + COLUMN_REFERENTI_IMMAGINE + " byte[] );";*/
	
	private static final String DATABASE_CREATE_REFERENTE= "create table "
			+ TABELLA_REFERENTI + " ( " + COLUMN_REFERENTI_ID
			+ " integer primary key autoincrement, " + COLUMN_REFERENTI_IDCLIENTE + " integer not null," + 
			COLUMN_REFERENTI_NOME+ " text," + COLUMN_REFERENTI_COGNOME+ " text," + COLUMN_REFERENTI_FUNZIONE+ " text," +
			COLUMN_REFERENTI_TELEFONO+ " text," + COLUMN_REFERENTI_FAX + " text," + COLUMN_REFERENTI_CELLULARE+ " text," +
			COLUMN_REFERENTI_MAIL+ " text," +COLUMN_REFERENTI_NOTE + " text " + COLUMN_REFERENTI_IMMAGINE + " byte[] );";
	
	
	
	private static final String DATABASE_CREATE_ATTIVITA= "create table "
			+ TABELLA_ATTIVITA + " ( " + COLUMN_ATTIVITA_ID
			+ " integer primary key autoincrement, " + COLUMN_ATTIVITA_IDCLIENTE + " integer not null, " + 
			COLUMN_ATTIVITA_IDREFERENTE+ " integer, " + COLUMN_ATTIVITA_IDTIPOATTIVITA+ " integer, " + 
			COLUMN_ATTIVITA_DATAATIVITA+ " integer not null," + COLUMN_ATTIVITA_DATAALLARME + " integer, "+
			COLUMN_ATTIVITA_DESCRIZIONEATTIVITA + " text, " +
			COLUMN_ATTIVITA_C1+ " integer," + COLUMN_ATTIVITA_C2 + " text," + 
			COLUMN_ATTIVITA_C3 + " text );";
	
	private static final String DATABASE_CREATE_PROPRIETA= "create table "
            + TABELLA_PROPRIETA + " ( " + COLUMN_PROID 
            + " integer primary key autoincrement, " + COLUMN_PRONAME 
            + " text," + COLUMN_PROTXT + " text," +  COLUMN_PROBOO + " integer," +  
            COLUMN_PRONUM + " integer );"; 
	

}
	
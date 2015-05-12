

public class FileSincronizzati  {
	
	 
	  
	    public static final String FILE_NOMEFILEORI = "nomefileoriginario"; //text
	    public static final String FILE_NOMEFILEMODIFICATO = "nomefilemodificato"; //NomeCliente_idcliente_nomefile
	    public static final String FILE_SERVIZIODICONDIVIZIONE = "Servizio"; //INT DROPBOX/DRIVE
	    public static final String FILE_MD5 = "md5"; //text
	    public static final String FILE_DIMENSIONE = "dimensione"; //long
	    public static final String FILE_DATAFILE = "datafile"; //long
	    public static final String FILE_DATAUPLOAD = "dataupload"; //long
	    public static final String FILE_DESCRIZIONE = "descrizione"; //text
	    public static final String FILE_TMP1STRING = "tmp1string"; //text utilizzato per Path orifinario
	    public static final String FILE_TMP2STRING = "tmp2string"; //text
	    public static final String FILE_TMP1INT = "tmp1int"; //integer utilizzato per segnare per upload
	    public static final String FILE_TMP2INT= "tmp2int"; //integer
	
	
	private long piDfile;
	private long pIdCliente;
	
	private String pNomeFileOriginario;
	private String pNomeFileOModificato; //NomeCliente_idcliente_nomefile
	private int pServizioCondivizione; //00 DropBox 1 Drive
	private String pMd5;
	private long pDimensioneFile;
	private long pDataFile;
	private long pDataUploadFile;
	private String pDescrizione;
	private String pPathOriginario; //prendo TMP1String
	private String ptmp2String;
	private int pDaUplodare;
	private int pTmp2Int;
	
	
	
	public long getpIdFile (){
		return piDfile;
	}
	public void setpIdFile (long id){
		this.piDfile = id;
	}
	
	public long getpIdCliente (){
		return pIdCliente;
	}
	public void setpIdCliente (long pidcliente){
		this.pIdCliente = pidcliente;
	}
	
	public String getpNomeFileOModificato (){
		return pNomeFileOModificato;
	}
	public void setpNomeFileOModificato (String nomeModi){
		this.pNomeFileOModificato = nomeModi;
	}
	
	public String getpNomeFileOriginario (){
		return pNomeFileOriginario;
	}
	public void setpNomeFileOriginario (String nomeORi){
		this.pNomeFileOriginario = nomeORi;
	}
	
	public int getpServizioCondivizione (){
		return pServizioCondivizione;
	}
	public void setpServizioCondivizione (int pcondi){
		this.pServizioCondivizione = pcondi;
	}
	
	
	public String getpMd5 (){
		return pMd5;
	}
	public void setpMd5 (String md5){
		this.pMd5 = md5;
	}
	
	
	public long getpDimensioneFile (){
		return pDimensioneFile;
	}
	public void setpDimensioneFile (long dim){
		this.pDimensioneFile = dim;
	}
	
	public long getpDataFile (){
		return pDataFile;
	}
	public void setpDataFile (long dataFile){
		this.pDataFile = dataFile;
	}
	
	public long getpDataUploadFile (){
		return pDataUploadFile;
	}
	public void setpDataUploadFile (long dataUpload){
		this.pDataUploadFile = dataUpload;
	}
	
	
	public String getpDescrizione (){
		return pDescrizione;
	}
	public void setpDescrizione (String des){
		this.pDescrizione = des;
	}
	public String getpPathOriginario (){
		return pPathOriginario;
	}
	public void setpPathOriginario (String pathori){
		this.pPathOriginario = pathori;
	}
	
	public String getptmp2String (){
		return ptmp2String;
	}
	public void setptmp2String (String tmp2s){
		this.ptmp2String = tmp2s;
	}
		
	public int getpDaUplodare (){
		return pDaUplodare;
	}
	public void setpDaUplodare (int daUpload){
		this.pDaUplodare = daUpload;
	}
	public int getpTmp2Int (){
		return pDaUplodare;
	}
	public void setpTmp2Int (int tmp2i){
		this.pTmp2Int = tmp2i;
	}
	
	
		 
}



	


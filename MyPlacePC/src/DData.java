

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTable;


public class DData {
	//database
	public static long versioneDatabase=0;
	//file
	public static String pathInDropbox = File.separator + "Files";
	
	//link per verifica nuova release
	public static String linkVerificaaggiornamento = "https://sites.google.com/site/drdsoftwareapps/resources/v.txt?attredirects=0&d=1";
	public static String linkdownload ="http://goo.gl/N3ftAf";
	public static int releaseAttuale = 4;
	//vriabile vuote per problemi spl
		final static public String NOME_FILE_SETTING = "settings.txt";
		public static  String pathSetting = ""; //folder del databse
	// hash iftipologia descrizione
		public static  HashMap<String, String> HashIDTipologiaToDescrizione=new HashMap<String, String>();
		public static  HashMap<String, String> HashIDComboToIDTipologia=new HashMap<String, String>();
		public static  HashMap<String, String> HashIDTipologiaToIDComboSenza=new HashMap<String, String>();
		public static  HashMap<String, String> HashIDComboToIDTipologiaSenza=new HashMap<String, String>();
		public static  HashMap<Integer, Long> HashIDComboMesi =new HashMap<Integer, Long>();
		
		public  static HashMap<Integer, Proprieta> hashProprieta =new HashMap<Integer, Proprieta>();
		
		//parametri della lingua/localizzazione//configurazione
		public static int linguaScelta;
		public static boolean GenUnitaDiMisuraMetri = true;
		public static boolean GenChiediCOnfermaPrimacancellare = true;
		//distanza
		public static double latitudinePartenza ;
		public static double longitudinePartenza ;
	//immagini
		public static String iconaCerca = "icon/ic_menu_search.png";
		public static String iconaCancella = "/icon/ic_delete.png";
		public static String iconaNuova = "/icon/ic_action_new.png";
		
		public static String iconaEdit = "icon/ic_action_edit.png";
		public static String iconaSave = "icon/ic_action_save.png";
		public static String iconaCancellaTesto = "icon/ic_action_cancel.png";
		public static String iconaAddReferenti = "icon/ic_menu_addref.png";
		public static String iconaLauncer = "icon/ic_launcher.png";
		public static String iconaMenuApri = "icon/open16.png";
		public static String iconaMenuEsci = "icon/exit16.png";
		public static String iconaAddAttivita = "icon/ic_action_new_event.png";
		public static String iconaRecuperaCoordinate = "icon/ic_menu_compass.png";
		public static String iconaAggiungiEvento = "icon/ic_action_time.png";
		public static String iconaVisualizzaWWW = "icon/ic_action_web_site.png";
		public static String iconaOfferte = "icon/ic_monete.png";
		public static String iconaCartella = "icon/ic_action_collection.png";
		public static String iconaAttach = "icon/ic_action_new_attachment.png";
		public static String iconaVisualizza = "icon/ic_menu_view.png";
		
		
		
		
		//jdialog aggiungi evento
		public static String[] durataEvento = { "15 m", "30 m", " 45 m ", "1h" , "2 h" , "3 h", "4 h", "5 h", "6 h", "7 h", "8 h"};
		public static long[] durataEventoMillis = { 15*60000, 30*60000, 45*60000, 3600000 , 2*3600000 ,3*3600000, 
								4*3600000, 5*3600000, 6*3600000, 7*3600000,8*3600000};
	//
		public static long idClienteSelezionato = -1;
		public static int coloreTipologiaSelezionato;
		//layout referenti attivita
		 public static int numeroColonnaConPulsantiAttivita = 5; 
		    public static int numeroColonnaConPulsantiReferenti = 9; 
		    public static int  numeroColonneTotalReferenti=5; 
		    public static int numeroColonneTotalAttivita=6; 
		    public static int numeroColonnaConJtextArea=4; 
		
		  //layout referenti attivitaTodo
			 public static final int numeroColonnaConPulsantiAttivitaTodo = 6; 
			
			    public static int numeroColonneTotalAttivitaToDo=7; 
			    public static int numeroColonnaConJtextAreaToDo=5; 
		    
		    public static int numeroColonnaTipologiacolore=1; 
		   //files
		    public static final int numeroColonnaConPulsantiFile = 5; 
		    
			
		    public static int numeroColonneTotalFile=6; 
		    public static int numeroColonnaConJtextAreaFile=4; 
	    
	   // public static int numeroColonnaTipologiacolore=1; 
		   
		//offerta
		    public static final int numeroColonnaConPulsantiOfferta  = 7; 
			
		    public static int numeroColonneTotalOfferta=8; 
		    public static int numeroColonnaConJtextAreaoffertaDesc=2; 
		    public static int numeroColonnaConJtextAreaoffertaNota=6; 
		  
	    
	   //file
		  
		    
		//layout
	public static int numeroPulsantiElencoClienti = 3;
	public static int numeroPulsantiCliente = 4;
	public static int numeroPulsantiReferente = 5;
	public static int numeroPulsantiAttività = 2;
	//layout tipologia
	//id è nascosto
	public static int numeroColonnaConColore = 2;  
	public static int numeroColonnaConPulsantiTipologia = 3;  
	public static int numeroDiColonneTipologia  =4;  
	
	public static int numeroClientiSelezionati = 30;
	//referenti
	public static int numeroColonneReferenti = 8;
	//attività
	public static int numeroColonneAttività = 2;
	//distazasplitpanel
	public static Dimension dimensioniframe;
	public static int  distanzaSplitReferentiAttivita = 150;
	public static int  distanzaSplitClientiReferenti  = 250;
	public static int  distanzaElencoClientiPanelSinistra  = 600;
	public static int  dimPulsantiX  = 40;
	public static int  dimPulsantiy  = 25;
	//tipologia selezione
	public static int selTipoColonnaConColore = 3;  
	public static int selTipoNumeroColonne  =4;  
	public static int selTipocheck  =0;  
	
	public static Font FONT = new JLabel().getFont();
	// SQL
	public static String crescente = "ASC";
	public static String decrescente = "DESC";
	
	
}
/*
System.out.println(" ");
*/
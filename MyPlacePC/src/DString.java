
public class DString {
	//dowload nuova versione
	public static String downloadInfo;
	//tjtabbedpanel
	public static String jTabbedList, jTabbedTodo, jTabbedOfferte,jTabbedAttivita,jTabbedFiles;
	
	//clienti
	public static String cliIDcliente, cliNome,cliChkCliente, cliIndirizzio, cliCitta,cliNazione,cliTel,cliFax, cliWww, cliCapMail,cliLati,cliLongi,cliNota, cliCheckXls;
	//referenti
	public static String RefTitolo;
	public static String RefID, RefNome, RefCognome, RefFunzione,RefTele, RefFax, RefCell, RefEmail, RefNota, RefIDCLiente;
	//attivita
	public static String atttitolo;
	public static String attid, attData, attDescrizione, attFatto;
	public static String attCliente;
	public static String attsalvaaggiungi;
	public static String attAggiungiCalendario;
	//offerte
	public static String offtitolo;
	public static String offid, offData, offDescrizione, offImporto, offpercentuale, offNote;
	public static String offCliente, offAzienda;
	
	//lsitaclienti
	
	public static String listSelect, listIDcliente,listDistanza,listNome,listTipologia, listData;
	public static String listRicerca, listCancella;
	//jdialog opzioni  - posizione
	public static String dOpzioniLingua,dopzioniUnitaMisura, dOpzioniConfermePrimaCancellare;
	public static String dApplica, dAnnulla;
	//jdialog campiutente
	public static String jdTitolocampi,jdDescrizioneCapi,jdvisualizzaCampi;
	
	public static String dpLati,dpLongi;
	//jdialog evento
	public static String jdEvTitolo,jdEvDescrizione, jdEvDataInizio, jdEvDurata,jdEvOraInizio,jdEvOrafine;
	
	
	//Tipologia
	public static String tipDescrizioneTipo, tipTutteTipologie, tipid, tipNuova;
	public static String tipoColore, tipSeleziona;
	//preferenze
	
	public static final String PPATHDB_DIR= "pathdbdir"; //direcotriey dove andare a mettere settingstxt
	public static final String PPATHDB= "pathdb";
	public static final String PLATI= "lati";
	public static final String PLONGI= "longi";
	public static final String PLINGUA= "lingua"; //0 Inglese - 1 Italiano
	public static final String PUNITA= "unita"; //0 km - 1 miles
	public static final String PCONFERMADELETE= "delete";
	public static final String PPRIMAVOLTA="primavolta";
	
	
	//Label listclient
	public static String jLListSorting;
	public static String[] jCListSorting, jAttivitaTipo;
	public static String[] jTodoSorting, jTodoSelezione, jOffertaSorting, jOffertaSelezione;
	public static String msgcancellazione,msgcancellazioneAttivita, msgcancellazioneOfferta, msgcancellazioneFiles, msglockedFile1, msglockedFile2;
	public static String msgNessunSelezionato, msgClienteModificato, msgErroreCoordinate;
	public static String msgDataBaseNonCollegato;
	public static String msgDataBaseNonAggiornato;
	public static String msgCancellaTipologia;
	public static String optionMenu;
	public static String msgSelezionaFilePrimaVolta;
	public static String msgErroreRecuperoCoordinate;
	public static String msgEProgressAspetta;
	public static String msgErroreCalendario;
	public static String msgErroreImporto;
	
	
	
	//Generali
	public static String gAttenzione;
	public static String gVistato = "\u2713";//codifica utf?
	public static String gSalva;
	public static String gAnnulla;
	public static String unitaDiMisura = "Km";
	public static String gtitoloApplicazione;
	public static String gOk;
	public static String gSeleziona;
	public static String gDownload;
	
	//combo  mesi
	public static String cmbTuttiMesi, cmb99,cmmesi ;
	
	
	//menu
	public static String mFile,mApri,mEsporta, mEsci, mSetting, mCampbiaPosizioneRiferimento, mTipologia, mCampiUtenti,mCancellaCredenziali, mHelp, mAbout;
	
	//tool tip
	public static String ttCancellaTutto, ttCancellaRicerca,ttNuovoCli, ttSalvaCliente, 
					ttCancellaCliente, ttAddRef, ttAddAttivita, ttRicercaCoordinate, ttInserisciEvento, ttaddofferta, ttAddFile;
	
	//file espostazione
	public static String fileClienti, fileReferenti, fileAttività,fileOfferte;
	public static String xlsClienti, xlsreferenti, xlsattivita,xlsOfferte;
	
	
	//file tabella
	
	public static String fileTitolo;
	public static String idFile, fileNome, fileDimensione, fileData, fileDesc;
	
	
	//locked
	public static String lockForza, lockDatabaseSoloLettura;
	
	public static void stringheItaliano()
	{	
		//combo mesi
		cmbTuttiMesi = "Tutti mesi";
		cmb99 = ">99 mesi";
		cmmesi = "mesi";
		
		//jdialog evento
		jdEvTitolo = "Titolo";
		jdEvDescrizione = "Descrizione";
		jdEvDataInizio = "Data Inizio";
		jdEvDurata = "Durata";
		jdEvOraInizio = "Ora Inizio";
	
		
		
		//tootip
		ttCancellaTutto = "Cancella tutti i selezionati";
		ttCancellaRicerca = "Cancella ricerca";
		ttNuovoCli = "Aggiungi nuovo cliente";
		ttSalvaCliente  = "Salva modifiche al cliente";
		ttCancellaCliente = "Cancella Cliente";
		ttAddRef = "Aggiungi referente";
		ttAddFile = "Aggiungi File";
		ttAddAttivita = "Aggiungi attività";
		ttaddofferta = "Aggiungi offerta";
		ttRicercaCoordinate = "Recupera coordinate da indirizzo";
		ttInserisciEvento = "Imposta evento sul calendario di Google";
		
		//generali
		gAttenzione = "Attenzione!";
		gSalva = "Salva";
		gAnnulla = "Annulla";
		gSeleziona = "Seleziona";
		gDownload = "Download";
		gtitoloApplicazione = "I Miei Clienti";
		gOk = "OK";
		//messaggi
		msgClienteModificato = "Il cliente è stato modificato! Vuoi salvarlo?";
		msgErroreCoordinate = "Le coordinate non sono numeri!! \nLe modifiche non saranno salvate. Vuoi continuare?";
		msgDataBaseNonCollegato = "Database non collegato, Seleziona il database clienti.db";
		msgDataBaseNonAggiornato = "Il database non è aggiornato \nPer aggiornare seguire la seguente procedura:\1) Controllare che l'applicazione sia stata aggiornata all'ultima versione \n2) Aprire l\'applicazione I MIEI CLIENTI\n3) Dal menu laterale SINCRONIZZA MANUALMENTE\n4) UPLOAD DATABASE\n5) Chiudere e riavviare il programma per PC dopo che il database sul PC è stato aggiornato";
		msgCancellaTipologia = "Saranno cancellati tutti i clienti collegati alla tipologia. Sei sicuro?";
		msgSelezionaFilePrimaVolta = "Devi selezionare il file Clienti.db \ndalla cartella condivisa di DropBox";
		msgErroreRecuperoCoordinate = "Errore sul recupero coordinate! Controllare l'indirizzo o la connessione internet!";
		msgEProgressAspetta = "Aspetta...";
		msgErroreCalendario = "Impossibile connettersi al calendario di Google, verifica la connessione";
		msgErroreImporto = "Devi inserire solo cifre!!";
		//download
		downloadInfo = "E' disponibile una nuova versione dell'applicazione. E' consigliato di scaricarla";
		//Clienti
		cliIDcliente = "ID";
		cliChkCliente = "Cliente";
		cliCheckXls = "Cliente Si =1";
		cliNome = "Azienda";
		cliIndirizzio = "Indirizzo";
		cliCitta = "Città";
		cliNazione = "Nazione";
		cliTel = "Tel.";
		cliFax = "Fax";
		cliCapMail = "eMail";
		cliWww = "www";
		cliLati = "Lati.";
		cliLongi = "Long.";
		cliNota = "Nota";
		//referenti
		RefTitolo = "Referente";
		RefID = "ID";
		RefNome = "Nome";
		RefCognome = "Cognome";
		RefFunzione = "Funzione";
		RefTele = "Telefono";
		RefFax = "Fax";
		RefCell = "Cell";
		RefEmail = "e-mail";
		RefNota = "Nota";
		RefIDCLiente = "ID Cliente";
		//attivita
		atttitolo = "Attività";
		attid = "ID";
		attData= "Data"; 
		attDescrizione = "Descrizione";
		attFatto = "Fatto";
		attCliente = "Cliente";
		attsalvaaggiungi = "Salva e aggiungi \nnuova attività";
		attAggiungiCalendario = "Aggiungi a calendario";
		//offerte
		offtitolo = "Offerta";
		offid= "ID";
		offData = "Data";
		offDescrizione = "Descrizione";
		offImporto = "Importo";
		offpercentuale = "%"; 
		offNote = "Note";
		offCliente=   "Cliente";
		offAzienda = "Azienda";
		//jtablefile
		fileTitolo= "File";
		idFile = "IDFILE";
		fileNome = "Nome File";
		fileDimensione= "Dim (k)";
		fileData = "Data";
		fileDesc =  "Descrizione";
		//locked
		lockForza = "Forza apertura";
		lockDatabaseSoloLettura ="Il data base è aperto in solo lettura \n Non si Può modificare!! ";
		//jtabbedpanel
		jTabbedList = "Lista";
		jTabbedTodo  = "Da Fare..";
		jTabbedOfferte = "Offerte";
		jTabbedAttivita = "Attività";
		jTabbedFiles = "Files";
		//listaclienti
		listSelect = gVistato;
		listIDcliente= "ID"; 
		listDistanza = "Dist";
		listNome = "Azienda";
		listTipologia = "Tipologia";
		listData = "Data Attività";
		//listaclienti bottoni
		listRicerca = "Ricerca";
		listCancella = "Cancella";
		//tipologia
		tipDescrizioneTipo = "Descrizione tipologia";
		tipNuova = "Nuova Tipologia";
		tipTutteTipologie = "Tutte le tipologie";
		tipid = "ID tipologia";
		tipoColore = "Colore";
		tipSeleziona = "Tipologia multipla";
		
		
		//
		if(DData.GenUnitaDiMisuraMetri==true)
		{
			unitaDiMisura = "Km";
		}else{
			unitaDiMisura = "mi";
		}
		
		//Label listclient
		jLListSorting = "Ordinamento";
		jAttivitaTipo = new String [] {"Altro","Telefonata","Visita","E-mail"};
		jCListSorting = new String[]{"Azienda Crescente", "Azienda Decrescente", 
				"Data Crescente", "Data Decrescente", 
				"Distanza Crescente", "Distanza Descrescente", "Data attività Crescente", "Data attività Descrescente"};
		
		jTodoSorting = new String[]{"Data Crescente", "Data Decrescente", "Azienda Crescente", "Azienda Decrescente"};
		jTodoSelezione = new String[]{"Da fare", "Fatto", "Tutti"};
		jOffertaSorting = new String[]{"Data Crescente", "Data Decrescente", "Importo Crescente", "Importo Decrescente", "% crescente", "% descrescente"};
		jOffertaSelezione = new String[]{"In Essere", "Vinte", "Perse", "Tutte"};
		
		msgcancellazione = "cliente/i saranno eliminati!!";
		msgcancellazioneAttivita = "L'attività sarà eliminata!!";
		msgcancellazioneOfferta  = "L'offerta sarà eliminata";
		msgcancellazioneFiles = "Il files sarà eliminato";
		msgNessunSelezionato = " Nessun Cliente Selezionato";
		optionMenu = "Imposta come posizione di riferimento";
		msglockedFile1 = "Il database è bloccato da un'altro dispositivo dalle \n";
		msglockedFile2 = "\nIl Database sarà apero un SOLA LETTURA";
		
		
		//menu
		mFile = "File";
		mApri = "Apri database...";
		mEsporta = "Exporta database in xls";
		mEsci = "Esci";
		mSetting = "Impostazioni";
		mCampbiaPosizioneRiferimento = "Cambia Posizione Riferimento";
		mTipologia = "Tipologie...";
		mCampiUtenti = "Campi Cliente definibili utente...";
		mCancellaCredenziali = "Cancella credenziali Calendario";
		mHelp = "Help";
		mAbout = "About";
		
		
		//jdialog opzioni
		dOpzioniLingua = "Lingua";
		dopzioniUnitaMisura = "Unita di misura";
		dOpzioniConfermePrimaCancellare = "Conferma prima di cancellare";
		dApplica = "Applica";
		dAnnulla = "Annulla";
		//jdialog campi utente
		jdTitolocampi ="Campi Cliente dediniti dall'utente";
		jdDescrizioneCapi= "Descrizione";
		jdvisualizzaCampi="Visibile";
		
		
		
		dpLati = "Latitudine";
		dpLongi = "Longitudine";
		
		
		fileAttività = "Attivita.xls";
		fileOfferte = "Offerte.xls";
		fileClienti = "Clienti.xls";
		fileReferenti = "Referenti.xls";
		xlsattivita = "Attivita";
		xlsClienti = "Clienti";
		xlsreferenti = "Referenti";
		xlsOfferte = "Offerte";
	}
	public static void stringheInglese()
	{	
		//combo mesi
				cmbTuttiMesi = "All months";
				cmb99 = ">99 months ";
				cmmesi = " months";
		
		//jdialog evento
		jdEvTitolo = "Title";
		jdEvDescrizione = "Description";
		jdEvDataInizio = "Begin Data";
		jdEvDurata = "Durata";
		jdEvOraInizio = "Begin Time";
		
		
		
		//tootip
		ttCancellaTutto = "Delete all selected customer";
		ttCancellaRicerca = "Delete search";
		ttNuovoCli = "Add new customer";
		ttSalvaCliente  = "Save customer";
		ttCancellaCliente = "Delete customer";
		ttAddRef = "Add new referent";
		ttAddFile = "Add new file";
		ttAddAttivita = "Add new activity";
		ttaddofferta = "Add new bid";
		ttRicercaCoordinate = "Get coordinates from address";
		ttInserisciEvento = "Set event on Google calendar";
		
		//generali
		gAttenzione = "Attention!";
		gSalva = "Save";
		gAnnulla = "Cancel";
		gSeleziona = "Select";
		gDownload = "Download";
		gtitoloApplicazione = "My Customer";
		gOk = "OK";
		//messaggi
		msgClienteModificato = "The client has been modified! Do you want to save?";
		msgErroreCoordinate = "GPS coordinates are not number!! \nChanges will not save. Do you wont to continue?";
		msgDataBaseNonCollegato = "Database not linked, Select database clienti.db";
		msgDataBaseNonAggiornato = "Il database is not updated \nTo update database follow this instruction:\1) Check your apps is updated at last release \n2) Open app MY CUSTOMER\n3) from Left lateral menu choose MANUALLY SINCH\n4) UPLOAD DATABASE\n5) Close and restart PC software after database on oyur PC is updated";
		msgCancellaTipologia = "It will delete all the clients connected to the type. Are you sure?";
		msgSelezionaFilePrimaVolta = "You must select Clienti.db \n from DropBox folder";
		msgErroreRecuperoCoordinate = "Error in coordinates recover! Check address or internet connession!";
		msgEProgressAspetta = "Waiting...";
		msgErroreCalendario = "Google Calendar not available, please check network connection!";
		msgErroreImporto ="You must enter digits only!! ";
		//download
		downloadInfo = "It\'s available a new versione of software, It\'s recommended to download it!";
		//Clienti
		cliIDcliente = "ID";
		cliChkCliente = "Customer";
		cliCheckXls = "Cust. Yes=1 ";
		cliNome = "Company";
		cliIndirizzio = "Address";
		cliCitta = "City";
		cliNazione = "Country";
		cliTel = "Tel.";
		cliFax = "Fax";
		cliCapMail = "eMail";
		cliWww = "www";
		cliLati = "Lati.";
		cliLongi = "Long.";
		cliNota = "Note";
		//referenti
		RefTitolo = "Referente";
		RefID = "ID";
		RefNome = "Name";
		RefCognome = "Surname";
		RefFunzione = "Funzione";
		RefTele = "Phone";
		RefFax = "Fax";
		RefCell = "Mobile";
		RefEmail = "e-mail";
		RefNota = "Note";
		RefIDCLiente = "ID Customer";
		//attivita
		atttitolo = "Activity";
		attid = "ID";
		attData= "Date"; 
		attDescrizione = "Description";
		attFatto = "Done";
		attCliente = "Customer";
		attsalvaaggiungi = "Save and add new Activity";
		attAggiungiCalendario = "Add to calendar";
		//offerte
		offtitolo = "Bid";
		offid= "ID";
		offData = "Date";
		offDescrizione = "Description";
		offImporto = "Value";
		offpercentuale = "%"; 
		offCliente=   "Customer";
		offAzienda = "Company";
		//jtablefile
				fileTitolo= "File";
				idFile = "IDFILE";
				fileNome = "File Name";
				fileDimensione= "Dim (k)";
				fileData = "Date";
				fileDesc =  "Description";
				//locked
				lockForza= "Forced Open";
				lockDatabaseSoloLettura ="Database is in Read Only mode \n You can\'t modify it!! ";
		//jtabbedpanel
				jTabbedList = "List";
				jTabbedTodo  = "To Do";
				jTabbedOfferte = "Bids";
				jTabbedAttivita = "Activity";
				jTabbedFiles = "Files";
		//listaclienti
		listSelect = gVistato;
		listIDcliente= "ID"; 
		listDistanza = "Dist.";
		listNome = "Company";
		listTipologia = "Type";
		listData = "Activity Date";
		//listaclienti bottoni
		listRicerca = "Search";
		listCancella = "Delete";
		//tipologia
		tipDescrizioneTipo = "Type Description";
		tipNuova = "New Type";
		tipTutteTipologie = "All type";
		tipid = "ID type";
		tipoColore = "Color";
		tipSeleziona = "Multiple Type";
		//
		if(DData.GenUnitaDiMisuraMetri==true)
		{
			unitaDiMisura = "Km";
		}else{
			unitaDiMisura = "mi";
		}
		
		//Label listclient
		jLListSorting = "Sorting";
		jAttivitaTipo = new String [] {"Other","Phone Call","Visit","E-mail"};
		jCListSorting = new String[]{"Name increasing", "Name decreasing", 
				"Date increasing", "Date decreasing", 
				"Distance increasing", "Distance decreasing", "Activity date increasing", "Activity date decreasing"};
		jTodoSorting = new String[]{"Date increasing", "Date decreasing", "Name increasing", "Name decreasing"};
		jTodoSelezione = new String[]{"To do", "Done", "All"};
		jOffertaSorting = new String[]{"Date increasing", "Date decreasing", "Value increasing", "Value decreasing", "% increasing", "% decreasing"};
		jOffertaSelezione = new String[]{"Current", "Won", "Lost", "All"};
		
		msgcancellazioneAttivita = "Activity will be delete!!";
		msgcancellazioneOfferta = "Bid will be delete!!";
		msgcancellazioneFiles = "File will be delete !!!";
		msgcancellazione = "Customer/s will be deleted!!";
		msgNessunSelezionato = " No customer checked";
		optionMenu = "Set as position reference";
		msglockedFile1 = "Database is locked by another device from  \n";
		msglockedFile2 = "\nDatabase opens as Read Only";
		
		
		//menu
		mFile = "File";
		mApri = "Open database...";
		mEsporta = "Export database in xls";
		mEsci = "Exit";
		mSetting = "Setting";
		mCampbiaPosizioneRiferimento = "Change position reference";
		mTipologia = "Type...";
		mCampiUtenti = "User Customer field ....";
		mCancellaCredenziali = "Remove Calendar credentials";
		mHelp = "Help";
		mAbout = "About";
		
		
		//jdialog opzioni
		dOpzioniLingua = "Language";
		dopzioniUnitaMisura = "Unit of measure";
		dOpzioniConfermePrimaCancellare = "Confirmation before deleting";
		dApplica = "Apply";
		dAnnulla = "Cancel";
		//jdialof campi utente
		jdTitolocampi ="Customer field defined by user";
		jdDescrizioneCapi= "Description";
		jdvisualizzaCampi="Visible";
		
		dpLati = "Latitude";
		dpLongi = "Longitude";
		
		fileAttività = "Activitis.xls";
		fileOfferte = "Bids.xls";
		fileClienti = "Customers.xls";
		fileReferenti = "Referents.xls";
		xlsattivita = "Activitis";
		xlsClienti = "Customers";
		xlsreferenti = "Referents";
		xlsOfferte = "Bids";
	}
	
	public static void iniString()
	{	int lingua = DGen.getPreferencesInt(DString.PLINGUA, 0);
		if(lingua==0)
		{	DData.linguaScelta=0;
			stringheInglese();
		}else{
			stringheItaliano();
			DData.linguaScelta=1;
		}
		
		
	}
}

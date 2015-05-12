




import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;




import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class DEsportaExcel {
	public static int exportazioneDaLanciare = 0; //0clienti, 1 referenti 2 referenit
	public static HashMap<String, String> HashTipologia = new HashMap<String, String>();
	
	
	public static void aggiungiLabelExcel(WritableSheet sheet)
	{
	Label label0 = new Label(0, 0, DString.cliIDcliente); 
	Label label1 = new Label(1, 0, DString.cliNome); 
	Label label2 = new Label(2, 0, DString.cliIndirizzio); 
	Label label3 = new Label(3, 0, DString.cliCitta); 
	Label label4 = new Label(4, 0, DString.cliNazione); 
	Label label5 = new Label(5, 0, DString.cliTel); 
	Label label6 = new Label(6, 0, DString.cliFax); 
	Label label7 = new Label(7, 0, DString.cliWww); 
	Label label8 = new Label(8, 0, DString.cliNota); 
	Label label9 = new Label(9, 0, DString.cliLati); 
	Label label10 = new Label(10, 0, DString.cliLongi); 
	
	String free1 = DData.hashProprieta.get(1).getpTxtProprieta();
	String free2 = DData.hashProprieta.get(2).getpTxtProprieta();
	String free3 = DData.hashProprieta.get(3).getpTxtProprieta();
	String free4 = DData.hashProprieta.get(4).getpTxtProprieta();
	
	
	Label label11 = new Label(11, 0, free1);
	Label label12 = new Label(12, 0, free2);
	Label label13 = new Label(13, 0, free3);
	Label label14 = new Label(14, 0, free4);
	Label label15 = new Label(15, 0, DString.cliCapMail); 
	Label label16 = new Label(16, 0, DString.cliCheckXls); 
	Label label17 = new Label(17, 0, DString.tipid); 
	Label label18= new Label(18, 0, DString.tipDescrizioneTipo); 
	try {
		sheet.addCell(label0);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		sheet.addCell(label6);
		sheet.addCell(label7);
		sheet.addCell(label8);
		sheet.addCell(label9);
		sheet.addCell(label10);
		sheet.addCell(label11);
		sheet.addCell(label12);
		sheet.addCell(label13);
		sheet.addCell(label14);
		sheet.addCell(label15);
		sheet.addCell(label16);
		sheet.addCell(label17);
		sheet.addCell(label18);
		
		
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void aggiungiclienteExcel(WritableSheet sheet, int i, posizioneGps cliGps)
	{	Label lcliente,lindirizzo,lcitta,lnazione,ltel,lfax,lmemo,ltipo,lwww,lfree1,lfree2,lfree3,lfree4, lCapMail;
		Number lid,llati,llongi,lidtipo,lchkCli;
		
		lid = new jxl.write.Number (0, i, cliGps.getpId()); 
		lcliente = new Label(1, i, cliGps.getpNome()); 
		lindirizzo = new Label(2, i, cliGps.getpInidirizzo()); 
		lcitta = new Label(3, i, cliGps.getpCitt‡()); 
		lnazione = new Label(4, i, cliGps.getpNazione()); 
		ltel = new Label(5, i, cliGps.getpTelefono()); 
		lfax = new Label(6, i, cliGps.getpFax()); 
		lwww = new Label(7,i,cliGps.getpSito());
		lmemo = new Label(8, i, cliGps.getpMemo()); 
		llati = new jxl.write.Number(9, i, cliGps.getpLatitudine()); 
		llongi = new jxl.write.Number(10, i, cliGps.getpLongitudine()); 
		
		lfree1 = new Label(11, i, cliGps.getpFree1());
		lfree2 = new Label(12, i, cliGps.getpFree2());
		lfree3 = new Label(13, i, cliGps.getpFree3());
		lfree4 = new Label(14, i, cliGps.getpFree4());
		
		lCapMail = new Label(15, i, cliGps.getpCap());
		lchkCli = new jxl.write.Number(16, i, cliGps.getpSpare1());
		lidtipo = new jxl.write.Number(17, i, cliGps.getpTipo()); 
		ltipo= new Label(18, i, HashTipologia.get(cliGps.getpTipo()+"")); 
		try {
			sheet.addCell(lid);
			sheet.addCell(lcliente);
			sheet.addCell(lindirizzo);
			sheet.addCell(lcitta);
			sheet.addCell(lnazione);
			sheet.addCell(ltel);
			sheet.addCell(lfax);
			sheet.addCell(lwww);
			sheet.addCell(lmemo);
			sheet.addCell(llati);
			sheet.addCell(llongi);
			sheet.addCell(lfree1);
			sheet.addCell(lfree2);
			sheet.addCell(lfree3);
			sheet.addCell(lfree4);
			sheet.addCell(lCapMail);
			if ( cliGps.getpSpare1() ==1) sheet.addCell(lchkCli);
			sheet.addCell(lidtipo);
			sheet.addCell(ltipo);
			
			
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void esportaClientiExcel()
	{	
		
		try {
			  String fileClienti = DData.pathSetting+"/"+DString.fileClienti;
			   System.out.println("File clienti----"+ fileClienti);
			  
	            File fileXlsClienti = new File(fileClienti);
	            
			WritableWorkbook workbook = Workbook.createWorkbook(fileXlsClienti);
			WritableSheet sheet = workbook.createSheet(DString.xlsClienti, 0);
			aggiungiLabelExcel(sheet);
			
			
			List<posizioneGps> tutticlienti = DataSource.getAllClienti();
			List<Tipologia> tuttetipo = DataSource.getAlltipologia();
			
			int i=0;
			for (i=0;i<tuttetipo.size();i++)
			{HashTipologia.put(tuttetipo.get(i).getpIdTipologia()+"",tuttetipo.get(i).getpDescrizioneTipologia());
			}
			for(i=0;i<tutticlienti.size();i++) //la riga 0 Ë quella delle label
			{	
				aggiungiclienteExcel(sheet, i+1, tutticlienti.get(i));
			}
			
			workbook.write(); 
			workbook.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
					
					 
					
		
					
					
					
				
}
			 
	
	public static void taskEsportaClienti()
	{	exportazioneDaLanciare =0;
	/*
			System.err.println("esporta");
			Task1 task = new Task1();
			task.execute();
			*/
			esportaClientiExcel();
			esportaReferntiExcel();
			esportaAttivitaExcel();
			esportaOfferteExcel();
	}
						
		
	
	public static void aggiungiLabelExcelReferetenti(WritableSheet sheet)
	{
	
	
	
	Label label0 = new Label(0, 0, DString.RefID); 
	Label label1 = new Label(1, 0, DString.RefIDCLiente); 
	Label label2 = new Label(2, 0, DString.RefNome); 
	Label label3 = new Label(3, 0, DString.RefCognome); 
	Label label4 = new Label(4, 0, DString.RefFunzione); 
	Label label5 = new Label(5, 0, DString.RefTele); 
	Label label6 = new Label(6, 0, DString.RefCell); 
	
	Label label7 = new Label(7, 0, DString.RefFax); 
	Label label8 = new Label(8, 0, DString.RefEmail); 
	Label label9 = new Label(9, 0, DString.RefNota); 
	Label label10 = new Label(10, 0, DString.cliNome); 
	
	try {
		sheet.addCell(label0);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		sheet.addCell(label6);
		sheet.addCell(label7);
		sheet.addCell(label8);
		sheet.addCell(label9);
		sheet.addCell(label10);
		
		
		
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void aggiungiReferenteExcel(WritableSheet sheet, int i, Referenti refe,String Nomecliente)
	{	Label Nome,Cognome,funzione,Reftel,Refcell,RefFax,RefMail,RefNote,Cliente;
		Number IDreferente,IDcliente;
		
		
		IDreferente = new jxl.write.Number (0, i, refe.getpIdReferente()); 
		IDcliente = new jxl.write.Number (1, i, refe.getpIdClienteReferente()); 
		Nome = new Label(2, i, refe.getpNomeReferente()); 
		Cognome = new Label(3, i, refe.getpCognomeReferente()); 
		funzione = new Label(4, i, refe.getpFunzioneReferente()); 
		Reftel = new Label(5, i, refe.getpTelRefernte()); 
		Refcell = new Label(6, i, refe.getpCelReferente()); 
		RefFax = new Label(7, i, refe.getpFaxReferente()); 
		RefMail = new Label(8, i, refe.getpMaileferente());
		RefNote = new Label(9, i, refe.getpMemoReferente());
		Cliente = new Label(10, i, Nomecliente);
		try {
			sheet.addCell(IDreferente);
			sheet.addCell(IDcliente);
			sheet.addCell(Nome);
			sheet.addCell(Cognome);
			sheet.addCell(funzione);
			sheet.addCell(Reftel);
			sheet.addCell(Refcell);
			sheet.addCell(RefFax);
			sheet.addCell(RefMail);
			sheet.addCell(RefNote);
			sheet.addCell(Cliente);
			
			
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void esportaReferntiExcel()
	{	
			try {	  
		
					String fileReferenti = DData.pathSetting+"/"+DString.fileReferenti;
				   System.out.println("File clienti----"+ fileReferenti);
				   File fileXlsReferenti = new File(fileReferenti);
		           WritableWorkbook workbook = Workbook.createWorkbook(fileXlsReferenti);
				
					WritableSheet sheet = workbook.createSheet(DString.xlsreferenti, 0);
					aggiungiLabelExcelReferetenti(sheet);
					
					List<Referenti> tuttireferenti;
				
						tuttireferenti = DataSource.getAllReferentiExport();
					
					int i=0;
					Referenti referenteTemp;
					posizioneGps  Poitemp;
					int j=1;
					for(i=0;i<tuttireferenti.size();i++) //la riga 0 Ë quella delle label
					{	referenteTemp = tuttireferenti.get(i);
						try {
							Poitemp = DataSource.getClientID(referenteTemp.getpIdClienteReferente());
							
							aggiungiReferenteExcel(sheet, j, referenteTemp, Poitemp.getpNome());
							j++;
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
					
					workbook.write(); 
				
						try {
							workbook.close();
						} catch (WriteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
			
			
			 
		
		
						
		
	}
	public static void aggiungiLabelExcelAttivita(WritableSheet sheet)
	{
		
	Label label0 = new Label(0, 0, DString.attid); 
	Label label1 = new Label(1, 0, DString.RefIDCLiente); 
	Label label2 = new Label(2, 0, DString.attData); 
	Label label3 = new Label(3, 0, DString.attDescrizione); 
	Label label4 = new Label(4, 0, DString.cliNome); 
	
	
	try {
		sheet.addCell(label0);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
				
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static String restituisciData(long dataInMillis, boolean visualizzaOra)
     { 
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		return dateFormatter.format(new Date(dataInMillis)); 
     	
     }
	public static void aggiungiAttivit‡Excel(WritableSheet sheet, int i, Attivita atti,String Nomecliente)
	{	Label notaattivita,Cliente,datamodifica;
		Number IDatti,IDcliente;
		
		
		IDatti = new jxl.write.Number (0, i, atti.getpIdAttivita()); 
		IDcliente = new jxl.write.Number (1, i, atti.getpIdCliente()); 
		long data  = atti.getpDataAttivita(); 
		String DataDaVisualizzarev = restituisciData(data, false);
		datamodifica = new Label(2, i,DataDaVisualizzarev); 
		notaattivita = new Label(3, i,atti.getpDescrizioneAttivit‡() ); 
		Cliente = new Label(4, i, Nomecliente);
		try {
			sheet.addCell(IDatti);
			sheet.addCell(IDcliente);
			sheet.addCell(datamodifica);
			sheet.addCell(notaattivita);
			sheet.addCell(Cliente);
			
			
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void esportaAttivitaExcel()
	{	
				try {	
					
					String fileAttivita = DData.pathSetting+"/"+DString.fileAttivit‡;
					   System.out.println("File clienti----"+ fileAttivita);
					   File fileXlsAttivita = new File(fileAttivita);
			           WritableWorkbook workbook = Workbook.createWorkbook(fileXlsAttivita);
					
						WritableSheet sheet = workbook.createSheet(DString.xlsattivita, 0);
						aggiungiLabelExcelAttivita(sheet);
					
					List<Attivita> tutteattivit‡ = DataSource.getAllAttivitaExport();
					
					int i=0;
					Attivita attivit‡Temp;
					posizioneGps  Poitemp;
					int j = 1;
					for(i=0;i<tutteattivit‡.size();i++) //la riga 0 Ë quella delle label
					{	attivit‡Temp = tutteattivit‡.get(i);
						try {
							Poitemp = DataSource.getClientID(attivit‡Temp.getpIdCliente());
							aggiungiAttivit‡Excel(sheet, j, attivit‡Temp, Poitemp.getpNome());
							j++;
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
					
					workbook.write(); 
					try {
						workbook.close();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}
		
					
					
					
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			
		}
			 
		
	public static void aggiungiLabelOfferte(WritableSheet sheet) {
 		
   	   
		
		

		Label label0 = new Label(0, 0,  DString.offid);
		Label label1 = new Label(1, 0, DString.offCliente);
		Label label2 = new Label(2, 0, DString.offDescrizione);
		Label label3 = new Label(3, 0, DString.offImporto);
		Label label4 = new Label(4, 0, DString.offData);
		Label label5 = new Label(5, 0, DString.offpercentuale);
		Label label6 = new Label(6, 0, DString.offNote);

		

		try {
			sheet.addCell(label0);
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
		

		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	public static void aggiungiOfferta(WritableSheet sheet,int i,Offerta off)
 	{
 		Label nomecliente, descrizione, dataChiusura, nota;
		Number IDoffe, importo, percentuale;

		IDoffe = new jxl.write.Number(0, i, off.getpIdOfferta());
		nomecliente = new Label(1, i, off.getpNomeCliente());
		descrizione = new Label(2, i, off.getpDescrizioneOfferta());
		importo = new jxl.write.Number(3, i, off.getpImportoOfferta());
		
		long data = off.getpDataChiusura();
		String DataDaVisualizzarev = restituisciData(data, false);
		dataChiusura = new Label(4, i, DataDaVisualizzarev);
		
		percentuale = new jxl.write.Number(5, i, off.getpPercentuale());
		nota = new Label(6, i, off.getpNota());
		
		try {
			sheet.addCell(IDoffe);
			sheet.addCell(nomecliente);
			sheet.addCell(descrizione);
			sheet.addCell(importo);
			sheet.addCell(dataChiusura);
			sheet.addCell(percentuale);
			sheet.addCell(nota);

		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	public static void esportaOfferteExcel() {
		
				
				try {
				
					String fileOfferte =  DData.pathSetting+"/"+DString.fileOfferte;
					
					File fileXlsOfferte = new File(fileOfferte);
					WritableWorkbook workbook = Workbook.createWorkbook(fileXlsOfferte);
					WritableSheet sheet = workbook.createSheet(DString.xlsOfferte, 0);
					aggiungiLabelOfferte(sheet);
					
					List<Offerta> tuttiOfferte = DataSource.getOffertaClienteTutti(-1, DData.decrescente, mydbhelper.OFFERTA_DATACHIUSURA, 3, "");
					
						
					int i = 0;
					Offerta offertaTemp;
					
					int j = 1;
					for (i = 0; i < tuttiOfferte.size(); i++) // la riga 0 Ë
																// quella delle
																// label
					{
						offertaTemp = tuttiOfferte.get(i);
						try {
							
							aggiungiOfferta(sheet, j, offertaTemp);
						

							j++;
						} catch (Exception e) {
							// TODO: handle exception
						}

					}

					workbook.write();
					try {
						workbook.close();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
				
			}


	
						
		
	
	
}


class  Task1 extends SwingWorker<Void, Void> {
    /*
     * Main task. Executed in background thread.
     */
 jDialogProgress jdp;
    @Override
    public Void doInBackground() {
    
    	 SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                	 jdp = new jDialogProgress();
                	 jdp.setResizable(false);
               
                	 jdp.pack();
                	 jdp.setLocationRelativeTo(null);
     		    	jdp.setModal(true);
     		    	jdp.setVisible(true);
     		    	
                  
                }
            });
    	
    	//funzione da lanciare
    	switch (DEsportaExcel.exportazioneDaLanciare) {
		case 0:
			System.out.println("DESPORTA");
			DEsportaExcel.taskEsportaClienti();
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		default:
			break;
		}
    	
      return null;
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
    	
    	jdp.setVisible(false);
    }
  }


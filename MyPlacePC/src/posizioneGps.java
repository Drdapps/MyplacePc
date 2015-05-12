





public class posizioneGps  {
	private long pIdPos;
	private long pTipo;
	private String pNome;
	private long pDataInMillis;
	private double pLatitudine;
	private double pLongitudine;
	private String pMemo;
	private String pInidirizzo;
	private String pCittà;
	private float pDistanza;
	private long pPrecisione;
	private byte[] pImmagine;
	private String pCap;
	private String pprovincia;
	private String ptelefono;
	private String pfax;
	private String psito;
	private String pNazione;
	private boolean pSettato;
	private double pLatitudineProi;
	private double pLongitudineProi;
	
	private long pDataAttività;
	private long pGiorniAttività;
	private long pMesiAttivita;
	
	private String pfree1,pfree2,pfree3,pfree4;
	
	private int pTipoattività;
	private long pspare1;
	private long pspare2;
	private String pspare3;
	private String pspare4;
	
	
	private String compare;
	
	public long getpId (){
		return pIdPos;
	}
	public void setpId (long pIdPos){
		this.pIdPos = pIdPos;
	}
	public long getpTipo(){
		return pTipo;
	}
	public void setpTipo (long pTipo){
		this.pTipo = pTipo;
	}
	
	public String getpNome (){
		return pNome;
	}
	public void setpnome(String pnome){
		this.pNome = pnome;
	}
	public long getpDataInMillis (){
		return pDataInMillis;
	}
	public void setpDataInMillis (long pDataInMillis){
		this.pDataInMillis = pDataInMillis;
	}
	public double getpLatitudine (){
		return pLatitudine;
	}
	public void setpLatitudine (double pLatitudine){
		this.pLatitudine = pLatitudine;
	}
	public double getpLongitudine (){
		return pLongitudine;
	}
	public void setpLongitudine (double pLongitudine){
		this.pLongitudine = pLongitudine;
	}
	public String getpMemo (){
		return pMemo;
	}
	public void setpMemo (String pMemo){
		this.pMemo = pMemo;
	}
	
	public String getpInidirizzo (){
		return pInidirizzo;
	}
	public void setpInidirizzo (String pInidirizzo){
		this.pInidirizzo = pInidirizzo;
	}
	public String getpCittà (){
		return pCittà;
	}
	public void setpCittà (String pCittà){
		this.pCittà = pCittà;
	}
	public float getpDistanza (){
		return pDistanza;
	}
	public void setpDistanza (float pDistanza){
		this.pDistanza = pDistanza;
	}
	public long getpPrecisione(){
		return pPrecisione;
	}
	public void setpPrecisione (long pPrecisione){
		this.pPrecisione = pPrecisione;
	}
	
	public byte[] getpImmagine(){
		return pImmagine;
	}
	public void setpImmagine(byte[] bs){
		this.pImmagine = bs;
		
	}
	
	public String getpCap (){
		return pCap;
	}
	public void setpCap (String pCap){
		this.pCap = pCap;
	}
	public String getpProvincia(){
		return pprovincia;
	}
	public void setpProvincia (String pProvincia){
		this.pprovincia	 = pProvincia;
	}
	
	public String getpTelefono(){
		return ptelefono;
	}
	public void setptelefono (String ptelefono){
		this.ptelefono	 = ptelefono;
	}
	public String getpFax(){
		return pfax;
	}
	public void setpFax (String pFax){
		this.pfax	 = pFax;
	}
	public String getpSito(){
		return psito;
	}
	public void setpSito (String pSito){
		this.psito	 = pSito;
	}
	public String getpNazione(){
		return pNazione;
	}
	public void setpNazione (String pNazione){
		this.pNazione	 = pNazione;
	}
	
	public void setpSettato (Boolean boo)
	{
		this.pSettato	 = boo;
	}
	public boolean getpSettato(){
		return pSettato;
	}
	
	
	///
	public double getpLatitudineProi (){
		return pLatitudineProi;
	}
	public void setpLatitudineProi (double pLatitudineProi){
		this.pLatitudineProi = pLatitudineProi;
	}
	public double getpLongitudineProi (){
		return pLongitudineProi;
	}
	public void setpLongitudineProi (double pLongitudineProi){
		this.pLongitudineProi = pLongitudineProi;
	}

	public long getpDataAttività (){
		return pDataAttività;
	}
	public void setpDataAttività (long pDataAttività){
		this.pDataAttività = pDataAttività;
	}
	
	public long getpGiorniAttività (){
		return pGiorniAttività;
	}
	public void setpGiorniAttività (long pGiorniAttività){
		this.pGiorniAttività = pGiorniAttività;
	}
	public long getpMesiAttivita (){
		return pMesiAttivita;
	}
	public void setpMesiAttivita (long pMesiAttivita){
		this.pMesiAttivita = pMesiAttivita;
	}
	
	public posizioneGps setpProiezioneSettato(posizioneGps cliente, double proilati, double proilongi, boolean settato)
	{	
		cliente.setpLatitudineProi(proilati);
		cliente.setpLongitudineProi(proilongi);
		cliente.setpSettato(settato);
		return cliente;
	}

	
public String getpFree1(){
		
		return pfree1;
	}
	public void setpFree1 (String free1){
	
		this.pfree1	 = free1;
	}
	public String getpFree2(){
		return pfree2;
	}
	public void setpFree2 (String free2){
		this.pfree2	 = free2;
	}
	public String getpFree3(){
		return pfree3;
	}
	public void setpFree3 (String free3){
		this.pfree3	 = free3;
	}
	public String getpFree4(){
		return pfree4;
	}
	public void setpFree4 (String free4){
		this.pfree4	 = free4;
	}
	
	

	public int getpTipoattivita (){
		return pTipoattività;
	}
	public void setpTipoattivita (int tipo){
		this.pTipoattività = tipo;
	
	}
	
	public void setpSpare1 (long sapre1){
		this.pspare1	 = sapre1;
	}
	public long getpSpare1(){
		return pspare1;
	}
	public void setpSpare2 (long sapre2){
		this.pspare2	 = sapre2;
	}
	public long getpSpare2(){
		return pspare2;
	}
	
	
	
	public void setpSpare3 (String sapre3){
		this.pspare3	 = sapre3;
	}
	public String getpSpare3(){
		return pspare3;
	}
	public void setpSpare4 (String sapre4){
		this.pspare4	 = sapre4;
	}
	public String getpSpare4(){
		return pspare4;
	}
	
	
	public posizioneGps setpPosizioneGPSsenzaID(Long pIdPos, Long pTipo,String pNome, long pDataInMillis,
							double pLatitudine, double pLongitudine,String pMemo, String pInidirizzo,
							String pCittà, float pDistanza , long pPrecisione, byte[] pImmagine, String pCap,
							String pprovincia, String ptelefono , String pfax, String psito, String pNazione, 
							long dataAttivita, long giorniAttivita, long mesiattività, String free1,String free2,
							String free3,String free4, int tipoAttivita, long spare1, long spare2, String spare3, String spare4){
		posizioneGps result = new posizioneGps();
		
	
		result.setpId(pIdPos);
		result.setpTipo(pTipo);
		result.setpnome(pNome);
		result.setpDataInMillis(pDataInMillis);
		result.setpLatitudine(pLatitudine);
		result.setpLongitudine(pLongitudine);
		result.setpMemo(pMemo);
		result.setpInidirizzo(pInidirizzo);
		result.setpCittà(pCittà);
		result.setpDistanza(pDistanza);
		result.setpPrecisione(pPrecisione);
		result.setpImmagine(pImmagine);
		result.setpCap(pCap);
		result.setpProvincia(pprovincia);
		result.setptelefono(ptelefono);
		result.setpFax(pfax);
		result.setpSito(psito);
		result.setpNazione(pNazione);
		result.setpDataAttività(dataAttivita);
		result.setpGiorniAttività(giorniAttivita);
		result.setpMesiAttivita(mesiattività);
		result.setpFree1(free1);
		result.setpFree2(free2);
		result.setpFree3(free3);
		result.setpFree4(free4);
		result.setpTipoattivita(tipoAttivita);
		result.setpSpare1(spare1);
		result.setpSpare2(spare2);
		result.setpSpare3(spare3);
		result.setpSpare4(spare4);
		
		return result;
	}
	
}



	


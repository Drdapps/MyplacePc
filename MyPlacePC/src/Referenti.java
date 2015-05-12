

public class Referenti  {
	private long pIdReferente;
	private long pidclienteReferente;
	private String pNomeReferente;
	private String pCognomeReferente;
	private String pFunzioneReferente;
	private String pMemoReferente;
	private String pTelReferente;
	private String pFaxReferente;
	private String pCelReferente;
	private String pMailReferente;
	private byte[] pImmagineRefente;
	
	public long getpIdReferente (){
		return pIdReferente;
	}
	public void setpIdReferente (long pIdPos){
		this.pIdReferente = pIdPos;
	}
	public long getpIdClienteReferente(){
		return pidclienteReferente;
	}
	public void setpIdClienteReferente (long pIdCliente){
		this.pidclienteReferente = pIdCliente;
	}
	
	public String getpNomeReferente (){
		return pNomeReferente;
	}
	public void setpNomeRefente(String pnomeReferente){
		this.pNomeReferente = pnomeReferente;
	}
	public String getpCognomeReferente (){
		return pCognomeReferente;
	}
	public void setpCognomeReferente(String pcognomeReferente){
		this.pCognomeReferente = pcognomeReferente;
	}
	public String getpFunzioneReferente (){
		return pFunzioneReferente;
	}
	public void setpFunzioneReferente(String pFunzioneReferente){
		this.pFunzioneReferente = pFunzioneReferente;
	}
	
	
	public String getpMemoReferente (){
		return pMemoReferente;
	}
	public void setpMemoReferente (String pmemoRefente){
		this.pMemoReferente = pmemoRefente;
	}
	
	public String getpTelRefernte (){
		return pTelReferente;
	}
	public void setpTelReferente (String ptelReferente){
		this.pTelReferente = ptelReferente;
	}
	public String getpFaxReferente (){
		return pFaxReferente;
	}
	public void setpFaxReferente (String pfaxReferente){
		this.pFaxReferente = pfaxReferente;
	}
	public String getpCelReferente (){
		return pCelReferente;
	}
	public void setpCelReferentea (String pcelReferente){
		this.pCelReferente = pcelReferente;
	}
	public String getpMaileferente (){
		return pMailReferente;
	}
	public void setpMailReferentea (String pmailReferente){
		this.pMailReferente = pmailReferente;
	}

	
	public byte[] getpImmagineReferente(){
		return pImmagineRefente;
	}
	public void setpImmagineRefente(byte[] bs){
		this.pImmagineRefente = bs;
		
	}
	
	
	
	
	
	public Referenti setpReferenti(Long pIdReferente, Long pidclienteReferente,String pNomeReferente, 
							String pCognomeReferente, String pFunzioneReferente,String pMemoReferente,
							String pTelReferente,String pFaxReferente,String pCelReferente,
							String pMailReferente, byte[] pImmagine){
		Referenti result = new Referenti();
		
		
		result.setpIdReferente(pIdReferente);
		result.setpIdClienteReferente(pidclienteReferente);
		result.setpNomeRefente(pNomeReferente);
		result.setpCognomeReferente(pCognomeReferente);
		result.setpFunzioneReferente(pFunzioneReferente);
		result.setpMemoReferente(pMemoReferente);
		result.setpTelReferente(pTelReferente);
		result.setpFaxReferente(pFaxReferente);
		result.setpCelReferentea(pCelReferente);
		result.setpMailReferentea(pMailReferente);
		
		result.setpImmagineRefente(pImmagine);
		
		
		
		return result;
	}
	
	
	 
}



	


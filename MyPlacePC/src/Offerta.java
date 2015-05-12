

public class Offerta {
	
	
	
	private long pIdOfferta;
	private long pIdClienteOfferta;
	
	private long pIdTipologiaOfferta;
	
	private String pNomeCliente;
	
	private String pDescrizioneOfferta;
	private double pImportoOfferta;
	private long pDataPresentazione;
	private long pDataChiusura;
	private long pPercentuale;
	private boolean pAcliente;
	private String pNota;
	private String pTmp1; //da utilizzato per archiviare il mese e anno
	
	
	
	//
	
	
	public long getpIdOfferta (){
		return pIdOfferta;
	}
	public void setpIdOfferta (long pIdOff){
		this.pIdOfferta = pIdOff;
	}
	public long getpIdClienteOfferta (){
		return pIdClienteOfferta;
	}
	public void setpIdClienteOfferta (long idCli){
		this.pIdClienteOfferta = idCli;
	}
	
	public long getpIdTipologiaOfferta (){
		return pIdTipologiaOfferta;
	}
	public void setpIdTipologiaOfferta (long idTipo){
		this.pIdTipologiaOfferta = idTipo;
	}
	
	
	public String getpNomeCliente(){
		return pNomeCliente;
	}
	public void setpNomeCliente (String pnome){
		this.pNomeCliente = pnome;
	}
	
	public String getpDescrizioneOfferta(){
		return pDescrizioneOfferta;
	}
	public void setpDescrizioneOfferta (String pDescrizione){
		this.pDescrizioneOfferta = pDescrizione;
	}
	
	
	
	public double getpImportoOfferta (){
		return pImportoOfferta;
	}
	public void setpImportoOfferta (double impof){
		this.pImportoOfferta = impof;
	}
	
	
	public long getpDataPresentazione (){
		return pDataPresentazione;
	}
	public void setpDataPresentazione (long datapre){
		this.pDataPresentazione = datapre;
	}
	
	public long getpDataChiusura (){
		return pDataChiusura;
	}
	public void setpDataChiusura (long datachi){
		this.pDataChiusura = datachi;
	}
	
	public long getpPercentuale (){
		return pPercentuale;
	}
	public void setpPercentuale (long perc){
		this.pPercentuale = perc;
	}
	
	
	
	public boolean getpAcliente(){
		return pAcliente;
	}
	public void setpAcliente (boolean pAcliente){
		this.pAcliente = pAcliente;
	}
	
	public String getpNota(){
		return pNota;
	}
	public void setpNota (String pNota){
		this.pNota = pNota;
	}
	
	public String getpTmp1(){
		return pTmp1;
	}
	public void setpTmp1 (String tt){
		this.pTmp1 = tt;
	}
	
	public Offerta setpOfferta(long pIdOfferta,long pIdClienteOfferta, long pIdTipologiaOfferta,String nomeCliente,
			String pDescrizioneOfferta,double pImportoOfferta,long pDataPresentazione, long pDataChiusura, 
			long pPercentuale, boolean pPersa,String pNota,String pTmp1)
	{
		Offerta result = new Offerta();
		result.setpIdOfferta(pIdOfferta);
		result.setpIdClienteOfferta(pIdClienteOfferta);
		result.setpIdTipologiaOfferta(pIdTipologiaOfferta);
		result.setpNomeCliente(nomeCliente);
		result.setpDescrizioneOfferta(pDescrizioneOfferta);
		result.setpImportoOfferta(pImportoOfferta);
		result.setpDataPresentazione(pDataPresentazione);
		result.setpDataChiusura(pDataChiusura);
		result.setpNota(pNota);
		result.setpTmp1(pTmp1);
		
		return result;
	}
	
	
}



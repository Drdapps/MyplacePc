



public class Tipologia {
	private long pIdTipologia;
	private String pDescrizioneTipologia;
	private int pColoreTipologia;
	private int pSelezionaTipologia;
	
	private String compare;
	
	public long getpIdTipologia (){
		return pIdTipologia;
	}
	public void setpIdTipologia (long pIdTipologia){
		this.pIdTipologia = pIdTipologia;
	}
	public String getpDescrizioneTipologia(){
		return pDescrizioneTipologia;
	}
	public void setpDescrizioneTipologia (String pDescrizioneTipologia){
		this.pDescrizioneTipologia = pDescrizioneTipologia;
	}
	public int getpColoreTipologia(){
		return pColoreTipologia;
	}
	public void setpColoreTipologia (int pColoreTipologia){
		this.pColoreTipologia = pColoreTipologia;
	}
	
	
	public int getpSelezionaTipologia(){
		return pSelezionaTipologia;
	}
	public void setpSelezionaTipologia (int pSelezionaTipologia){
		this.pSelezionaTipologia = pSelezionaTipologia;
	}
	public Tipologia setpTipologia(long pIdTipologia, String pDescrizioneTipologia, int Colore, int selezione)
	{
		Tipologia result = new Tipologia();
		result.setpIdTipologia(pIdTipologia);
		result.setpDescrizioneTipologia(pDescrizioneTipologia);
		result.setpColoreTipologia(Colore);
		result.setpSelezionaTipologia(selezione);
		return result;
	}
}



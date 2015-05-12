

public class Proprieta  {
	

	//campi proprieta da 1 a 4 sono i cambi definiti dall'utente
	//5 propieta locked //viene usata per trasferire il locked al PC
	
	private long pIdPropieta;
	private String pNomeProprieta;
	private String pTxtProprieta;
	private boolean pBooProprieta;
	
	private long pLongProprieta;
	
	
	
	public long getpIdPropieta (){
		return pIdPropieta;
	}
	public void setPIdPropieta (long pIdPropieta){
		this.pIdPropieta = pIdPropieta;
	}
	public String getpNomeProprieta (){
		return pNomeProprieta;
	}
	public void setpNomeProprieta (String pNomeProprieta){
		this.pNomeProprieta = pNomeProprieta;
	}
	
	public String getpTxtProprieta (){
		return pTxtProprieta;
	}
	public void setpTxtProprieta (String pTxtProprieta){
		this.pTxtProprieta = pTxtProprieta;
	}
	
	public boolean getpBooProprieta (){
		return pBooProprieta;
	}
	public void setpBooProprieta (boolean pBooProprieta){
		this.pBooProprieta = pBooProprieta;
	}
	
	public long getpLongProprieta (){
		return pLongProprieta;
	}
	public void setPLongProprieta (long pLongProprieta){
		this.pLongProprieta = pLongProprieta;
	}
	
	
	
	
	public Proprieta setPropieta(long idPro, String nome, String txt, boolean boo, long lng)
	{
		Proprieta result = new Proprieta();
		result.setPIdPropieta(idPro);
		result.setpNomeProprieta(nome);
		result.setpTxtProprieta(txt);
		result.setpBooProprieta(boo);
		result.setPLongProprieta(lng);
	System.out.println("on result set pro/"+ result.getpIdPropieta()+"/" + result.getpNomeProprieta()+"/" + result.getpBooProprieta());
		
		return result;
		
	}
		 
}



	


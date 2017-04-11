
public class FichaTratamiento 
{
	private Tratamiento dato;
	private FichaTratamiento enlace;
	
	public FichaTratamiento(Tratamiento m)
	{
		enlace = null;
		dato = m;
	}
	
	public FichaTratamiento(Tratamiento m, FichaTratamiento n)
	{
		enlace = n;
		dato = m;
	}
	
	public FichaTratamiento getEnlace()
	{
		return enlace;
	}
	
	public Tratamiento getDato()
	{
		return dato;
	}
	
	public void setEnlace(FichaTratamiento e)
	{
		enlace = e;
	}
	

}

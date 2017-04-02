
public class FichaMedicamento 
{
	private Medicamento dato;
	private FichaMedicamento enlace;
	
	public FichaMedicamento(Medicamento m)
	{
		enlace = null;
		dato = m;
	}
	
	public FichaMedicamento(Medicamento m, FichaMedicamento n)
	{
		enlace = n;
		dato = m;
	}
	
	public FichaMedicamento getEnlace()
	{
		return enlace;
	}
	
	public Medicamento getDato()
	{
		return dato;
	}
	
	public void setEnlace(FichaMedicamento e)
	{
		enlace = e;
	}
	

}

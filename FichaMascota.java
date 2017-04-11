public class FichaMascota 
{
	private Mascota dato;
	private FichaMascota enlace;
	private FichaTratamiento tratamientos;
	
	public FichaMascota(Mascota c)
	{
		enlace = null;
		dato = c;
	}
	
	public FichaMascota(Mascota m, FichaMascota n)
	{
		enlace = n;
		dato = m;
	}
	
	public FichaMascota getEnlace()
	{
		return enlace;
	}
	
	public Mascota getDato()
	{
		return dato;
	}
	
	public void setEnlace(FichaMascota e)
	{
		enlace = e;
	}
	
	public void setMedicamento(Tratamiento t)
	{
		tratamientos = new FichaTratamiento(t, tratamientos);
	}
	
	public FichaTratamiento getMedicamentos()
	{
		return tratamientos;
	}

}

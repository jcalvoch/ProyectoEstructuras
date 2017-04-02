
public class FichaMascota 
{
	private Mascota dato;
	private FichaMascota enlace;
	private FichaMedicamento medicamentos;
	
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
	
	public void setMedicamento(Medicamento m)
	{
		medicamentos = new FichaMedicamento(m, medicamentos);
	}
	
	public FichaMedicamento getMedicamentos()
	{
		return medicamentos;
	}

}

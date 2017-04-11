
public class Tratamiento 
{
	
	private int numTratamiento;
	private String nombre;
	private Items item;
	
	public Tratamiento ()
	{}
	
	public Tratamiento (int num, String nom, String ape, String dir)
	{
		numTratamiento = num;
		nombre = nom;
	}
	
	public void NumMedicamento (int numMedicamento)
	{
		this.numTratamiento = numMedicamento;
	}
	
	public void Nombre (String nombre)
	{
		this.nombre = nombre;
	}
	
	public void SetItem(Items item)
	{
		this.item = item;
	}
	
	
	public int getnumMascota()
	{
		return numTratamiento;
	}
	
	public Items getItem()
	{
		return item;
	}
	
	public String getNombre()
	{
		return nombre;
	}

}

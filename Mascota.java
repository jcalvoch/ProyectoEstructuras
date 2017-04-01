
public class Mascota 
{
	private int numMascota;
	private String nombre;

	
	public Mascota ()
	{}
	
	public Mascota (int num, String nom, String ape, String dir)
	{
		numMascota = num;
		nombre = nom;
	}
	
	public void NumMascota (int numMascota)
	{
		this.numMascota = numMascota;
	}
	
	public void Nombre (String nombre)
	{
		this.nombre = nombre;
	}
	
	
	public int getnumMascota()
	{
		return numMascota;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	

}

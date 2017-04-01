
public class Cliente 
{
	private int cedula;
	private String nombre;
	private String apellido;
	private String direccion;
	
	public Cliente ()
	{}
	
	public Cliente (int ced, String nom, String ape, String dir)
	{
		cedula = ced;
		nombre = nom;
		apellido = ape;
		direccion = dir;
	}
	
	public void Cedula (int cedula)
	{
		this.cedula = cedula;
	}
	
	public void Nombre (String nombre)
	{
		this.nombre = nombre;
	}
	
	public void Apellido (String apellido)
	{
		this.apellido = apellido;
	}
	
	public void Direccion (String direccion)
	{
		this.direccion = direccion;
	}
	
	public int getCedula()
	{
		return cedula;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public String getApellido()
	{
		return apellido;
	}
	
	public String getDireccion()
	{
		return direccion;
	}
	
}

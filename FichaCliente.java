import java.util.Scanner;

public class FichaCliente 
{
		private Cliente dato;
		private FichaCliente enlace;
		private FichaMascota mascotas;
		

		
		public FichaCliente(Cliente c)
		{
			enlace = null;
			dato = c;
		}
		
		public FichaCliente(Cliente c, FichaCliente n)
		{
			enlace = n;
			dato = c;
		}
		
		public FichaCliente getEnlace()
		{
			return enlace;
		}
		
		public Cliente getDato()
		{
			return dato;
		}
		
		public void setEnlace(FichaCliente e)
		{
			enlace = e;
		}
		
		public void setMascota(Mascota m)
		{
			mascotas = new FichaMascota(m);
		}
		
		public void setMascota(Mascota m, FichaMascota fm)
		{
			mascotas = new FichaMascota(m, mascotas);
		}
		
		public FichaMascota getMascota()
		{
			return mascotas;
		}


}

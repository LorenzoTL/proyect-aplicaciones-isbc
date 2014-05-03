package Clases_Auxiliares;

public class Preferencias {
		private String localizacion;
		private int habitaciones;
		private int superficie;
		
		public Preferencias(){
			localizacion = "";
			habitaciones = 1;
			superficie = 100;
		}
		
		public int getHabitaciones() {
			return habitaciones;
		}
		
		public String getLocalizacion() {
			return localizacion;
		}
		
		public int getSuperficie() {
			return superficie;
		}
		
		public void setHabitaciones(int habitaciones) {
			this.habitaciones = habitaciones;
		}
		
		public void setLocalizacion(String localizacion) {
			this.localizacion = localizacion;
		}
		
		public void setSuperficie(int superficie) {
			this.superficie = superficie;
		}
}

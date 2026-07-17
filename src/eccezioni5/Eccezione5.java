package eccezioni5;

public class Eccezione5 {

	public static void main(String[] args) {
		try { // subito il try per intercettare l'eccezione lanciata dal 2° catch di generaEccezione()
			Rethrow.generaEccezione(); // generaEccezione() worker del main()
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Programma terminato!");
		}
	}

}

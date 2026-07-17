package eccezioni5;

public class Rethrow {
	public static void generaEccezione() {
		int numer[] = { 4, 8, 16, 32, 64, 128, 256, 512 };
		int denom[] = { 2, 0, 4, 4, 0, 8 };
		
		for(int i = 0; i < numer.length; i++) {
			try {
				System.out.println(numer[i] + "/" + denom[i] + "=" + (numer[i] / denom[i]));
			}
			// qui gestisco la divisione per zero
			catch(ArithmeticException e) {
				System.out.println("Non puoi dividere " + numer[i] + " per 0!");
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Denominatore mancante!");
				throw e; // ... per uscire dal for, rilancio l'eccezione e: trovandomi fuori dal try, Java cerca un catch "a monte", cioè nel caller del metodo
			}
		}
	}
}

package eccezioni3;

public class Eccezione3 {

	public static void main(String[] args) {
		int numer[] = { 4, 8, 16, 32, 64, 128, 256, 512 };
		int denom[] = { 2, 0, 4, 4, 0, 8 };

		try { // try esterno per la lunghezza dell'array
			for (int i = 0; i < numer.length; i++) {

				try { // try interno per le divisioni
					System.out.println(numer[i] + "/" + denom[i] + "=" + (numer[i] / denom[i]));
				} catch (ArithmeticException e) {
					System.out.println("Non puoi dividere " + numer[i] + " per 0!");
				}
			}
			// usando il try fuori dal for, generandosi l'eccezione il ciclo si interrompe perchè Java cerca il catch
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Denominatore mancante, programma terminato!");
		}
	}
}

package eccezioni1;

public class Eccezione1 {

	public static void main(String[] args) {
		int numeri[] = new int[4];

		try {// try: provo a svolgere il codice nelle parentesi graffe
			System.out.println("Prima che l'eccezione sia generata");
			numeri[7] = 10; // provo l'eccezione
			System.out.println("Questo non verrà stampato"); // perchè Java ha urgenza di cercare un catch dell'eccezione, quindi "salta al rigo 13"
		} catch (ArrayIndexOutOfBoundsException exc) { // avendo provocato l'eccezione nel try, posso gestirla ed evitare che il programma crashi
			System.out.println("Indice array fuori dai parametri!");
		} finally { // il codice nel finally viene svolto comunque se l'eccezione è ben gestita
					// oppure non si manifesta
			System.out.println("Dopo che il try-catch è stato attraversato");
		}
	}
}

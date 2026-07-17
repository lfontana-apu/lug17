package eccezioni2;

public class Eccezione2 {

	public static void main(String[] args) {
		int numer[] = {4, 8, 16, 32, 64, 128};
		int denom[] = {2, 0, 4, 4, 0, 8};
		
		// voglio stampare le divisioni di ogni elemento di numer per il corrispondente elemento di denom: chiaramente la divisione per 0 non è cpnsentita
		for(int i = 0; i < numer.length; i++) {
			try {
				System.out.println(numer[i] + "/" + denom[i] + "=" + (numer[i]/denom[i]));
			}catch(ArithmeticException e) {
				System.out.println("Non puoi dividere " + numer[i] + " per 0!");
			}
			
		}
	}

}

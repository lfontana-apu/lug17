package eccezioni6;

public class DivisioneRadice {
	// scrivere un programma che tenti di calcolare la radice quadrata di un rapporto, con il dovuto controllo delle eccezioni
	static double div(int a, int b) throws Exception { // throws autorizza il metodo a lanciare eccezioni liberamente, come se fossero normali istanze di una classe
		if (b == 0) { // controllo sul denominatore
			throw new Exception("Impossibile dividere per 0!"); // Java interrompe l'esecuzione di div() e salta al caller (main())
		}
		if (a*b < 0) { // controllo sul radicando (deve essere > 0 per una radice pari
			throw new Exception("Impossibile estrarre la radice quadrata di un numero negativo!"); // anche qui Java salta al caller...
		}
		return Math.sqrt(a/b);
	}
}

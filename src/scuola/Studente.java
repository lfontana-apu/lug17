package scuola;

public class Studente extends Persona implements Certificato {

	private static final int ANNO_CORRENTE = 2026;
	public static final int ETA_MINIMA = 5;
	public static final int ETA_MASSIMA = 12;
	private static int progressivoStudenti = 0;
	private final int classe;
	private final char sezione;
	private final String matricola;
	private int annoRilascioCertificato;
	private String cognomeMedico;

	public Studente(String nome, String cognome, int annoNascita, int classe, char sezione) {
		super(nome, cognome, annoNascita);
		if (!annoNascitaValido(annoNascita)) {
			throw new IllegalArgumentException("Anno di nascita non compatibile con la scuola primaria.");
		}
		if (classe < 1 || classe > 5) {
			throw new IllegalArgumentException("La classe deve essere compresa tra 1 e 5.");
		}
		char sezioneMaiuscola = Character.toUpperCase(sezione);
		if (sezioneMaiuscola < 'A' || sezioneMaiuscola > 'D') {
			throw new IllegalArgumentException("La sezione deve essere una lettera compresa tra A e D.");
		}
		if (!classeCompatibileConAnnoNascita(annoNascita, classe)) {
			throw new IllegalArgumentException("La classe scelta non è compatibile con l'anno di nascita.");
		}
		this.classe = classe;
		this.sezione = sezioneMaiuscola;
		this.matricola = generaMatricola(annoNascita, sezioneMaiuscola);
	}

	@Override
	public boolean controllaAnnoNascita(int annoNascita) {
		return annoNascitaValido(annoNascita);
	}

	public static boolean annoNascitaValido(int annoNascita) {
		int eta = ANNO_CORRENTE - annoNascita;
		return eta >= ETA_MINIMA && eta <= ETA_MASSIMA;
	}

	public static boolean classeCompatibileConAnnoNascita(int annoNascita, int classe) {
		if (classe < 1 || classe > 5) {
			return false;
		}
		int eta = ANNO_CORRENTE - annoNascita;
		int etaIdeale = classe + 5;
		return eta >= etaIdeale - 1 && eta <= etaIdeale + 2;
	}

	private static String generaMatricola(int annoNascita, char sezione) {
		if (progressivoStudenti > 999) {
			throw new IllegalStateException("Numero massimo di matricole disponibili raggiunto.");
		}
		String matricolaGenerata = String.format("%02d%03d%c", annoNascita % 100, progressivoStudenti, sezione);
		progressivoStudenti++;
		return matricolaGenerata;
	}

	@Override
	public void impostaCertificato(int annoRilascio, String cognomeMedico) {
		if (annoRilascio > ANNO_CORRENTE) {
			throw new IllegalArgumentException("L'anno del certificato non può essere futuro.");
		}
		if (annoRilascio <= 0 || ANNO_CORRENTE - annoRilascio > 3) {
			throw new IllegalArgumentException("Il certificato medico è scaduto o non valido.");
		}
		if (cognomeMedico == null || cognomeMedico.trim().isEmpty()) {
			throw new IllegalArgumentException("Il cognome del medico non può essere vuoto.");
		}
		this.annoRilascioCertificato = annoRilascio;
		this.cognomeMedico = cognomeMedico.trim();
	}

	@Override
	public int getAnnoRilascio() {
		return annoRilascioCertificato;
	}

	@Override
	public String getCognomeMedico() {
		return cognomeMedico;
	}

	@Override
	public boolean isCertificatoValido(int annoCorrente) {
		return annoRilascioCertificato > 0 && annoRilascioCertificato <= annoCorrente
				&& annoCorrente - annoRilascioCertificato <= 3 && cognomeMedico != null
				&& !cognomeMedico.trim().isEmpty();
	}

	public int getClasse() {
		return classe;
	}

	public char getSezione() {
		return sezione;
	}

	public String getMatricola() {
		return matricola;
	}

	@Override
	public String toString() {
		String informazioniCertificato;
		if (isCertificatoValido(ANNO_CORRENTE)) {
			informazioniCertificato = " | Certificato medico del " + annoRilascioCertificato + " (dott. "
					+ cognomeMedico + ")";
		} else {
			informazioniCertificato = " | Nessun certificato medico valido registrato";
		}
		return super.toString() + " | Matricola: " + matricola + " | Classe: " + classe + sezione
				+ informazioniCertificato;
	}
}

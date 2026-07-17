package Scuola;

import java.util.Calendar;

public class Studente extends Persona implements Certificato {
	private int classe;
	private char sezione;
	private final String matricola;

	private int annoRilascioCertificato;
	private String cognomeMedico;

	private static int progressivoStudenti = 0;

	public Studente(String nome, String cognome, int annoNascita, int classe, char sezione)
			throws IllegalArgumentException {
		super(nome, cognome, annoNascita);

		if (classe < 1 || classe > 5) {
			throw new IllegalArgumentException("La classe deve essere compresa tra 1 e 5.");
		}

		sezione = Character.toUpperCase(sezione);
		if (sezione < 'A' || sezione > 'D') {
			throw new IllegalArgumentException("La sezione deve essere una tra A, B, C, D.");
		}

		this.classe = classe;
		this.sezione = sezione;
		this.matricola = generaMatricola(annoNascita, sezione);
	}

	@Override
	public boolean controllaAnnoNascita(int anno) {
		int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
		int eta = annoCorrente - anno;
		return eta >= 5 && eta <= 14;
	}

	private String generaMatricola(int annoNascita, char sezione) {
		int ultimeDueCifre = annoNascita % 100;
		String annoStr = "";
		if (ultimeDueCifre < 10) {
			annoStr = "0" + ultimeDueCifre;
		} else {
			annoStr = "" + ultimeDueCifre;
		}
		String progressivoStr = "";

		if (progressivoStudenti < 10) {
			progressivoStr = "00" + progressivoStudenti;
		} else if (progressivoStudenti < 100) {
			progressivoStr = "0" + progressivoStudenti;
		} else {
			progressivoStr = "" + progressivoStudenti;
		}

		progressivoStudenti = (progressivoStudenti + 1) % 1000;

		return annoStr + progressivoStr + sezione;
	}

	@Override
	public void impostaCertificato(int annoRilascio, String cognomeMedico) {
		this.annoRilascioCertificato = annoRilascio;
		this.cognomeMedico = cognomeMedico;
	}

	@Override
	public int getAnnoRilascio() {
		return annoRilascioCertificato;
	}

	@Override
	public String getCognomeMedico() {
		return cognomeMedico;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public char getSezione() {
		return sezione;
	}

	public void setSezione(char sezione) {
		this.sezione = Character.toUpperCase(sezione);
	}

	public String getMatricola() {
		return matricola;
	}

	@Override
	public String toString() {
		String infoCertificato = (annoRilascioCertificato != 0)
				? " | Certificato Medico del " + annoRilascioCertificato + " (Dr. " + cognomeMedico + ")"
				: " | Nessun certificato medico registrato";
		return super.toString() + " | Matricola: " + matricola + " | Classe: " + classe + sezione + infoCertificato;
	}
	
	@Override
	public boolean isCertificatoValido(int annoCorrente) {
		return (annoCorrente - this.annoRilascioCertificato) <= 3;
	}
}
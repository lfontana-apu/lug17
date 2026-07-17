package Scuola;

public abstract class Persona {
	private final String nome;
	private final String cognome;
	private final int annoNascita;

	public Persona(String nome, String cognome, int annoNascita) throws IllegalArgumentException {
		if (!controllaAnnoNascita(annoNascita)) {
			throw new IllegalArgumentException("Anno di nascita non valido per questa categoria!");
		}
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
	}

	public abstract boolean controllaAnnoNascita(int anno);

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getAnnoDiNascita() {
		return annoNascita;
	}

	@Override
	public String toString() {
		return nome + " " + cognome + " (Nato/a nel " + annoNascita + ")";
	}
}

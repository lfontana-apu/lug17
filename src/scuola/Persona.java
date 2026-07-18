package scuola;

public abstract class Persona {

	private final String nome;
	private final String cognome;
	private final int annoNascita;

	protected Persona(String nome, String cognome, int annoNascita) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Il nome non può essere vuoto.");
		}
		if (cognome == null || cognome.trim().isEmpty()) {
			throw new IllegalArgumentException("Il cognome non può essere vuoto.");
		}
		this.nome = nome.trim();
		this.cognome = cognome.trim();
		this.annoNascita = annoNascita;
	}

	public abstract boolean controllaAnnoNascita(int annoNascita);

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getAnnoNascita() {
		return annoNascita;
	}

	@Override
	public String toString() {
		return nome + " " + cognome + " (nato/a nel " + annoNascita + ")";
	}
}

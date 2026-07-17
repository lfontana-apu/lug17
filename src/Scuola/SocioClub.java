package Scuola;

public class SocioClub implements Certificato {
	private final String nome;
	private final String cognome;
	private int annoRilascioCertificato;
	private String cognomeMedico;

	public SocioClub(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
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

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	@Override
	public String toString() {
		return nome + " " + cognome + " | Socio Club Pallavolo | Certificato: Anno " + annoRilascioCertificato
				+ " (Dr. " + cognomeMedico + ")";
	}
}
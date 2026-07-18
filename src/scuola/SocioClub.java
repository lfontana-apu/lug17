package scuola;

public class SocioClub implements Certificato {

	private static final int ANNO_CORRENTE = 2026;
	private final String nome;
	private final String cognome;
	private int annoRilascioCertificato;
	private String cognomeMedico;

	public SocioClub(String nome, String cognome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Il nome non può essere vuoto.");
		}
		if (cognome == null || cognome.trim().isEmpty()) {
			throw new IllegalArgumentException("Il cognome non può essere vuoto.");
		}
		this.nome = nome.trim();
		this.cognome = cognome.trim();
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

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	@Override
	public String toString() {
		return nome + " " + cognome + " | Socio del club di pallavolo" + " | Certificato medico del "
				+ annoRilascioCertificato + " (dott. " + cognomeMedico + ")";
	}
}

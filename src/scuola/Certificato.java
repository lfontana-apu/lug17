package scuola;

public interface Certificato {

	void impostaCertificato(int annoRilascio, String cognomeMedico);
	int getAnnoRilascio();

	String getCognomeMedico();

	boolean isCertificatoValido(int annoCorrente);
}

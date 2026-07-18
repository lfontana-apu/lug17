package scuola;

import java.util.Arrays;

public class Docente extends Persona {

	private static final String[] MATERIE_VALIDE = { "Italiano", "Matematica", "Scienze", "Storia", "Geografia",
			"Inglese", "Musica", "Arte" };

	private final String materiaInsegnata;

	public Docente(String nome, String cognome, int annoNascita, String materiaInsegnata) {
		super(nome, cognome, annoNascita);
		if (!annoNascitaValido(annoNascita)) {
			throw new IllegalArgumentException("I docenti devono essere nati tra il 1965 e il 1995.");
		}
		this.materiaInsegnata = convalidaMateria(materiaInsegnata);
	}

	@Override
	public boolean controllaAnnoNascita(int annoNascita) {
		return annoNascitaValido(annoNascita);
	}

	public static boolean annoNascitaValido(int annoNascita) {
		return annoNascita >= 1965 && annoNascita <= 1995;
	}

	private static String convalidaMateria(String materia) {
		if (materia == null || materia.trim().isEmpty()) {
			throw new IllegalArgumentException("La materia non può essere vuota.");
		}
		for (String materiaValida : MATERIE_VALIDE) {
			if (materiaValida.equalsIgnoreCase(materia.trim())) {
				return materiaValida;
			}
		}
		throw new IllegalArgumentException(
				"Materia non valida. Selezionare una materia tra: " + getElencoMaterieValide());
	}

	public static String getElencoMaterieValide() {
		return Arrays.toString(MATERIE_VALIDE);
	}

	public String getMateriaInsegnata() {
		return materiaInsegnata;
	}

	@Override
	public String toString() {
		return super.toString() + " | Ruolo: docente" + " | Materia: " + materiaInsegnata;
	}
}

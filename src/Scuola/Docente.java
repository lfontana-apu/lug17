package Scuola;

import java.util.Arrays;
import java.util.List;

public class Docente extends Persona {
	private String materiaInsegnata;
	private static final List<String> MATERIE_VALIDE = Arrays.asList("Italiano", "Matematica", "Scienze", "Storia",
			"Geografia", "Inglese", "Musica", "Arte");

	public Docente(String nome, String cognome, int annoNascita, String materia) throws IllegalArgumentException {
		super(nome, cognome, annoNascita);
		setMateriaInsegnata(materia);
	}

	@Override
	public boolean controllaAnnoNascita(int anno) {
		return anno >= 1965 && anno <= 1995;
	}

	public String getMateriaInsegnata() {
		return materiaInsegnata;
	}

	public void setMateriaInsegnata(String materia) {
		boolean materiaTrovata = false;

		for (String materiaValida : MATERIE_VALIDE) {
			if (materiaValida.equalsIgnoreCase(materia)) {
				materiaTrovata = true;
				break;
			}
		}

		if (materiaTrovata) {
			this.materiaInsegnata = materia;
		} else {
			throw new IllegalArgumentException(
					"Materia non valida per la scuola primaria. Selezionare tra: " + MATERIE_VALIDE);
		}
	}

	@Override
	public String toString() {
		return super.toString() + " | Ruolo: Docente | Materia " + materiaInsegnata;
	}
}

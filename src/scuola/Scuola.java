package scuola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scuola {

	private static final int ANNO_CORRENTE = 2026;
	private static final Scanner scanner = new Scanner(System.in);
	private static final List<Studente> studenti = new ArrayList<Studente>();
	private static final List<Docente> docenti = new ArrayList<Docente>();
	private static final List<SocioClub> sociClub = new ArrayList<SocioClub>();

	public static void main(String[] args) {
		int scelta;
		do {
			stampaMenu();
			scelta = leggiIntero("Scegli un'opzione: ");

			switch (scelta) {
			case 1 -> inserisciStudente();
			case 2 -> inserisciDocente();
			case 3 -> inserisciSocioClub();
			case 4 -> stampaStudenti();
			case 5 -> stampaDocenti();
			case 6 -> stampaSociClub();
			case 7 -> System.out.println("Uscita dal programma in corso. Arrivederci!");
			default -> System.out.println("Opzione non valida! Riprova.");
			}
			System.out.println("-----------------------------------------------");
		} while (scelta != 7);
		scanner.close();
	}

	private static void stampaMenu() {
		System.out.println("--- MENU GESTIONE SCUOLA E CLUB ---\n");
		System.out.println("1. Inserisci uno studente");
		System.out.println("2. Inserisci un docente");
		System.out.println("3. Inserisci un socio del club di pallavolo");
		System.out.println("4. Stampa tutti gli studenti");
		System.out.println("5. Stampa tutti i docenti");
		System.out.println("6. Stampa tutti i soci del club di pallavolo");
		System.out.println("7. Chiudi il programma");
	}

	private static void inserisciStudente() {
		System.out.println("-----------------------------------------------");
		System.out.println("*NUOVA ISCRIZIONE STUDENTE*");
		String nome = leggiTesto("Nome: ");
		if (nome.isEmpty()) {
			System.out.println("[ERRORE] Il nome non può essere vuoto. " + "Iscrizione annullata.");
			return;
		}
		String cognome = leggiTesto("Cognome: ");
		if (cognome.isEmpty()) {
			System.out.println("[ERRORE] Il cognome non può essere vuoto. " + "Iscrizione annullata.");
			return;
		}
		int annoMinimo = ANNO_CORRENTE - Studente.ETA_MASSIMA;
		int annoMassimo = ANNO_CORRENTE - Studente.ETA_MINIMA;
		int annoNascita = leggiIntero("Anno di nascita (" + annoMinimo + "-" + annoMassimo + "): ");
		if (!Studente.annoNascitaValido(annoNascita)) {
			System.out.println(
					"[ERRORE] Età non compatibile con la scuola " + "primaria (5-12 anni). " + "Iscrizione annullata.");
			return;
		}
		int classe;
		while (true) {
			classe = leggiIntero("Classe frequentata (1-5): ");
			if (classe < 1 || classe > 5) {
				System.out.println("[ERRORE] La classe deve essere compresa " + "tra 1 e 5.");
				continue;
			}
			if (!Studente.classeCompatibileConAnnoNascita(annoNascita, classe)) {
				int eta = ANNO_CORRENTE - annoNascita;
				int etaIdeale = classe + 5;
				System.out.println("[ERRORE] Lo studente ha circa " + eta + " anni, mentre l'età ideale "
						+ "per la classe " + classe + " è " + etaIdeale + " anni.");
				continue;
			}
			break;
		}
		char sezione = leggiSezione();
		int annoCertificato = leggiIntero("Anno di rilascio dell'ultimo certificato " + "medico (" + (ANNO_CORRENTE - 3)
				+ "-" + ANNO_CORRENTE + "): ");
		if (annoCertificato > ANNO_CORRENTE) {
			System.out.println("[ERRORE] L'anno del certificato non può " + "essere futuro. Iscrizione annullata.");
			return;
		}
		if (annoCertificato <= 0 || ANNO_CORRENTE - annoCertificato > 3) {
			System.out.println("[ERRORE] Il certificato medico è scaduto " + "o non valido. Iscrizione annullata.");
			return;
		}
		String cognomeMedico = leggiTesto("Cognome del medico certificatore: ");
		if (cognomeMedico.isEmpty()) {
			System.out.println("[ERRORE] Il cognome del medico non può " + "essere vuoto. Iscrizione annullata.");
			return;
		}
		try {
			Studente studente = new Studente(nome, cognome, annoNascita, classe, sezione);
			studente.impostaCertificato(annoCertificato, cognomeMedico);
			studenti.add(studente);
			System.out.println("-----------------------------------------------");
			System.out.println("[SUCCESSO] Studente registrato. " + "Matricola assegnata: " + studente.getMatricola());
		} catch (IllegalArgumentException | IllegalStateException eccezione) {
			System.out.println("[ERRORE] " + eccezione.getMessage() + " Iscrizione annullata.");
		}
	}

	private static void inserisciDocente() {
		System.out.println("-----------------------------------------------");
		System.out.println("*NUOVA ASSUNZIONE DOCENTE*");
		String nome = leggiTesto("Nome: ");
		if (nome.isEmpty()) {
			System.out.println("[ERRORE] Il nome non può essere vuoto. " + "Assunzione annullata.");
			return;
		}
		String cognome = leggiTesto("Cognome: ");
		if (cognome.isEmpty()) {
			System.out.println("[ERRORE] Il cognome non può essere vuoto. " + "Assunzione annullata.");
			return;
		}
		int annoNascita = leggiIntero("Anno di nascita del docente (1965-1995): ");
		if (!Docente.annoNascitaValido(annoNascita)) {
			System.out.println(
					"[ERRORE] I docenti devono essere nati " + "tra il 1965 e il 1995. " + "Assunzione annullata.");
			return;
		}
		System.out.println("Materie disponibili: " + Docente.getElencoMaterieValide());
		String materia = leggiTesto("Materia insegnata: ");
		try {
			Docente docente = new Docente(nome, cognome, annoNascita, materia);
			docenti.add(docente);
			System.out.println("-----------------------------------------------");
			System.out.println("[SUCCESSO] Docente registrato.");
		} catch (IllegalArgumentException eccezione) {
			System.out.println("[ERRORE] " + eccezione.getMessage() + " Assunzione annullata.");
		}
	}

	private static void inserisciSocioClub() {
		System.out.println("-----------------------------------------------");
		System.out.println("*NUOVA ISCRIZIONE SOCIO CLUB PALLAVOLO*");
		String nome = leggiTesto("Nome: ");
		if (nome.isEmpty()) {
			System.out.println("[ERRORE] Il nome non può essere vuoto. " + "Iscrizione annullata.");
			return;
		}
		String cognome = leggiTesto("Cognome: ");
		if (cognome.isEmpty()) {
			System.out.println("[ERRORE] Il cognome non può essere vuoto. " + "Iscrizione annullata.");
			return;
		}
		int annoCertificato = leggiIntero("Anno di rilascio dell'ultimo certificato " + "medico (" + (ANNO_CORRENTE - 3)
				+ "-" + ANNO_CORRENTE + "): ");
		if (annoCertificato > ANNO_CORRENTE) {
			System.out.println("[ERRORE] L'anno del certificato non può " + "essere futuro. Iscrizione annullata.");
			return;
		}
		if (annoCertificato <= 0 || ANNO_CORRENTE - annoCertificato > 3) {
			System.out.println("[ERRORE] Il certificato medico è scaduto " + "o non valido. Iscrizione annullata.");
			return;
		}
		String cognomeMedico = leggiTesto("Cognome del medico certificatore: ");
		if (cognomeMedico.isEmpty()) {
			System.out.println("[ERRORE] Il cognome del medico non può " + "essere vuoto. Iscrizione annullata.");
			return;
		}
		try {
			SocioClub socio = new SocioClub(nome, cognome);
			socio.impostaCertificato(annoCertificato, cognomeMedico);
			sociClub.add(socio);
			System.out.println("-----------------------------------------------");
			System.out.println("[SUCCESSO] Socio registrato.");
		} catch (IllegalArgumentException eccezione) {
			System.out.println("[ERRORE] " + eccezione.getMessage() + " Iscrizione annullata.");
		}
	}

	private static void stampaStudenti() {
		System.out.println("-----------------------------------------------");
		System.out.println("*ELENCO STUDENTI*");
		if (studenti.isEmpty()) {
			System.out.println("Nessuno studente registrato.");
			return;
		}
		for (Studente studente : studenti) {
			System.out.println(studente);
		}
	}

	private static void stampaDocenti() {
		System.out.println("-----------------------------------------------");
		System.out.println("*ELENCO DOCENTI*");
		if (docenti.isEmpty()) {
			System.out.println("Nessun docente registrato.");
			return;
		}
		for (Docente docente : docenti) {
			System.out.println(docente);
		}
	}

	private static void stampaSociClub() {
		System.out.println("-----------------------------------------------");
		System.out.println("ELENCO SOCI DEL CLUB DI PALLAVOLO*");
		if (sociClub.isEmpty()) {
			System.out.println("Nessun socio registrato.");
			return;
		}
		for (SocioClub socio : sociClub) {
			System.out.println(socio);
		}
	}

	private static int leggiIntero(String messaggio) {
		while (true) {
			System.out.print(messaggio);
			if (scanner.hasNextInt()) {
				int valore = scanner.nextInt();
				scanner.nextLine();
				return valore;
			}
			System.out.println("[ERRORE] Inserisci un numero intero valido.");
			scanner.nextLine();
		}
	}

	private static String leggiTesto(String messaggio) {
		System.out.print(messaggio);
		return scanner.nextLine().trim();
	}

	private static char leggiSezione() {
		while (true) {
			String sezioneInserita = leggiTesto("Sezione (A-D): ").toUpperCase();
			if (sezioneInserita.length() == 1) {
				char sezione = sezioneInserita.charAt(0);
				if (sezione >= 'A' && sezione <= 'D') {
					return sezione;
				}
			}
			System.out.println("[ERRORE] La sezione deve essere una " + "lettera tra A e D.");
		}
	}
}

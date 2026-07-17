package Scuola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scuola {
	private static final List<Studente> students = new ArrayList<>();
	private static final List<Docente> docenti = new ArrayList<>();
	private static final List<SocioClub> sociClub = new ArrayList<>();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int scelta;
		do {
			stampaMenu();
			System.out.print("Scegli un'opzione: ");
			while (!scanner.hasNextInt()) {
				System.out.println("Inserisci un numero valido!");
				scanner.next();
			}
			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1 -> inserisciStudente();
			case 2 -> inserisciDocente();
			case 3 -> inserisciSocio();
			case 4 -> stampaStudenti();
			case 5 -> stampaDocenti();
			case 6 -> stampaSoci();
			case 7 -> System.out.println("Uscita dal programma in corso. Arrivederci!");
			default -> System.out.println("Opzione non valida! Riprova.");
			}
			System.out.println("-----------------------------------------------");
		} while (scelta != 7);
	}

	private static void stampaMenu() {
		System.out.println("--- MENU GESTIONE SCUOLA E CLUB ---\n");
		System.out.println("1. Inserisci uno studente");
		System.out.println("2. Inserisci un docente");
		System.out.println("3. Inserisci un socio del club di pallavolo");
		System.out.println("4. Stampa tutti gli studenti");
		System.out.println("5. Stampa tutti i docenti");
		System.out.println("6. Stampa tutti i soci del club di pallavolo");
		System.out.println("7. Chiudere il programma");
	}

	private static void inserisciStudente() {
		System.out.println("-----------------------------------------------");
		System.out.println("*NUOVA ISCRIZIONE STUDENTE*");
		String nome = leggiStringaNonVuota("Nome: ");
		String cognome = leggiStringaNonVuota("Cognome: ");
		int annoNascita = 0;
		while (true) {
			System.out.print("Anno di nascita (2012-2021): ");
			try {
				annoNascita = Integer.parseInt(scanner.nextLine());
				int eta = 2026 - annoNascita;
				// Copre i 6-11 anni regolari + anticipi (5 anni) e bocciature (fino a 14)
				if (eta >= 5 && eta <= 14) {
					break;
				} else {
					System.out.println("[ERRORE] Età non compatibile con la scuola primaria (5-14 anni). Riprova.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[ERRORE] Inserisci un anno valido (es. 2018).");
			}
		}
		int classe = 0;
		while (true) {
			System.out.print("Classe (1-5): ");
			try {
				classe = Integer.parseInt(scanner.nextLine());
				if (classe < 1 || classe > 5) {
					System.out.println("[ERRORE] La classe deve essere compresa tra 1 e 5. Riprova.");
					continue;
				}
				int eta = 2026 - annoNascita;
				int etaIdeale = classe + 5;

				// Età ideale = classe + 5. Tolleranza: -1 anno (anticipi), +2 anni (bocciati)
				if (eta < (etaIdeale - 1) || eta > (etaIdeale + 2)) {
					System.out.println(
							"[ERRORE] Incoerenza! Un bambino nato nel " + annoNascita + " ha " + eta + " anni.");
					System.out.println("Non può frequentare la classe " + classe + " (l'età ideale è " + etaIdeale
							+ " anni). Riprova.");
				} else {
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("[ERRORE] Inserisci un numero intero tra 1 e 5.");
			}
		}

		char sezione = ' ';
		while (true) {
			System.out.print("Sezione (A-D): ");
			String inputSezione = scanner.nextLine().toUpperCase();
			if (inputSezione.length() == 1) {
				sezione = inputSezione.charAt(0);
				if (sezione >= 'A' && sezione <= 'D') {
					break;
				}
			}
			System.out.println("[ERRORE] La sezione deve essere una singola lettera tra A, B, C e D. Riprova.");
		}

		Studente nuovoStudente = new Studente(nome, cognome, annoNascita, classe, sezione);
		int annoCorrente = 2026;
		int annoCert = 0;
		while (true) {
			System.out.print("Anno rilascio certificato medico (2023-2026): ");
			try {
				annoCert = Integer.parseInt(scanner.nextLine());

				if (annoCorrente - annoCert > 3) {
					System.out.println("[ERRORE] Il certificato è SCADUTO! Deve essere stato rilasciato dal "
							+ (annoCorrente - 3) + " in poi (2023-2026). Riprova.");
				} else if (annoCert > annoCorrente) {
					System.out.println("[ERRORE] L'anno del certificato non può essere nel futuro! Riprova.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[ERRORE] Inserisci un anno numerico valido.");
			}
		}

		String medico = leggiStringaNonVuota("Cognome medico certificatore: ");
		nuovoStudente.impostaCertificato(annoCert, medico);

		students.add(nuovoStudente);
		System.out.println("-----------------------------------------------");
		System.out.println("[SUCCESSO] Studente registrato! Matricola assegnata: " + nuovoStudente.getMatricola());
	}

	private static void inserisciDocente() {
		System.out.println("-----------------------------------------------");
		System.out.println("*NUOVA ASSUNZIONE DOCENTE*");
		String nome = leggiStringaNonVuota("Nome: ");
		String cognome = leggiStringaNonVuota("Cognome: ");
		int annoNascita = 0;
		while (true) {
			System.out.print("Anno di nascita (1965-1995): ");
			try {
				annoNascita = Integer.parseInt(scanner.nextLine());
				// accetta docenti in età lavorativa (31-61 anni nel
				// 2026)
				if (annoNascita >= 1965 && annoNascita <= 1995) {
					break;
				} else {
					System.out.println("[ERRORE] I docenti devono essere nati tra il 1965 e il 1995. Riprova.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[ERRORE] Inserisci un anno valido (es. 1980).");
			}
		}
		String materia = "";
		java.util.List<String> materieValide = java.util.Arrays.asList("Italiano", "Matematica", "Scienze", "Storia",
				"Geografia", "Inglese", "Musica", "Arte");
		while (true) {
			System.out.println("Materie disponibili: " + materieValide);
			System.out.print("Inserisci la materia da insegnare: ");
			materia = scanner.nextLine();
			boolean materiaOk = false;
			for (String mv : materieValide) {
				if (mv.equalsIgnoreCase(materia)) {
					materia = mv;
					materiaOk = true;
					break;
				}
			}

			if (materiaOk) {
				break;
			} else {
				System.out.println("[ERRORE] Materia non valida per una scuola primaria! Riprova.");
			}
		}
		Docente nuovoDocente = new Docente(nome, cognome, annoNascita, materia);
		docenti.add(nuovoDocente);
		System.out.println("-----------------------------------------------");
		System.out.println("[SUCCESSO] Docente registrato con successo!");
	}

	private static void inserisciSocio() {
		System.out.println("-----------------------------------------------");
		System.out.println("*NUOVA ISCRIZIONE SOCIO CLUB PALLAVOLO*");
		String nome = leggiStringaNonVuota("Nome: ");
		String cognome = leggiStringaNonVuota("Cognome: ");
		SocioClub nuovoSocio = new SocioClub(nome, cognome);
		int annoCorrente = 2026;
		int annoCert = 0;

		while (true) {
			System.out.print("Anno rilascio certificato medico (2023-2026): ");
			try {
				annoCert = Integer.parseInt(scanner.nextLine());

				if (annoCorrente - annoCert > 3) {
					System.out.println("[ERRORE] Il certificato è SCADUTO! Deve essere stato rilasciato dal "
							+ (annoCorrente - 3) + " in poi (2023-2026). Riprova.");
				} else if (annoCert > annoCorrente) {
					System.out.println("[ERRORE] L'anno del certificato non può essere nel futuro! Riprova.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[ERRORE] Inserisci un anno numerico valido.");
			}
		}

		String medico = leggiStringaNonVuota("Cognome medico certificatore: ");
		nuovoSocio.impostaCertificato(annoCert, medico);

		sociClub.add(nuovoSocio);
		System.out.println("-----------------------------------------------");
		System.out.println("[SUCCESSO] Socio registrato con successo!");
	}

	private static void stampaStudenti() {
		System.out.println("-----------------------------------------------");
		System.out.println("*ELENCO STUDENTI REGISTRATI*");
		if (students.isEmpty()) {
			System.out.println("Nessun studente registrato.");
		} else {
			for (Studente s : students) {
				System.out.println(s);
			}
		}
	}

	private static void stampaDocenti() {
		System.out.println("-----------------------------------------------");
		System.out.println("*ELENCO DOCENTI REGISTRATI*");
		if (docenti.isEmpty()) {
			System.out.println("Nessun docente registrato.");
		} else {
			for (Docente d : docenti) {
				System.out.println(d);
			}
		}
	}

	private static void stampaSoci() {
		System.out.println("-----------------------------------------------");
		System.out.println("*ELENCO SOCI CLUB DI PALLAVOLO*");
		if (sociClub.isEmpty()) {
			System.out.println("Nessun socio registrato.");
		} else {
			for (SocioClub socio : sociClub) {
				System.out.println(socio);
			}
		}
	}

	private static String leggiStringaNonVuota(String messaggio) {
		String input = "";
		while (true) {
			System.out.print(messaggio);
			input = scanner.nextLine().trim();
			if (!input.isEmpty()) {
				break;
			}
			System.out.println("[ERRORE] Questo campo non può essere vuoto! Riprova.");
		}
		return input;
	}
}
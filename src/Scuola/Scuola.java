package Scuola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scuola {
	private static final List<Studente> studenti = new ArrayList<>();
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
			System.out.println("\n-----------------------------------------------\n");
		} while (scelta != 7);
	}

	private static void stampaMenu() {
		System.out.println("*MENU GESTIONE*");
		System.out.println("1. Inserisci uno studente");
		System.out.println("2. Inserisci un docente");
		System.out.println("3. Inserisci un socio del club di pallavolo");
		System.out.println("4. Stampa tutti gli studenti");
		System.out.println("5. Stampa tutti i docenti");
		System.out.println("6. Stampa tutti i soci del club di pallavolo");
		System.out.println("7. Chiudere il programma");
	}

	private static void inserisciStudente() {
	    System.out.println("\n *NUOVA ISCRIZIONE STUDENTE* ");
	    System.out.print("Nome: ");
	    String nome = scanner.nextLine();
	    System.out.print("Cognome: ");
	    String cognome = scanner.nextLine();
	    int annoNascita = 0;
	    while (true) {
	        System.out.print("Anno di nascita: ");
	        try {
	            annoNascita = Integer.parseInt(scanner.nextLine());
	            int eta = 2026 - annoNascita;
	            if (eta >= 5 && eta <= 14) {
	                break;
	            } else {
	                System.out.println("[ERRORE] Età non compatibile con la scuola primaria (l'alunno deve avere tra 5 e 14 anni). Riprova.");
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
	            if (classe >= 1 && classe <= 5) {
	                break;
	            } else {
	                System.out.println("[ERRORE] La classe deve essere compresa tra 1 e 5. Riprova.");
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
	    int annoCert = 0;
	    while (true) {
	        System.out.print("Anno rilascio certificato medico (es. 2025 o 2026): ");
	        try {
	            annoCert = Integer.parseInt(scanner.nextLine());
	            if (annoCert >= 2020 && annoCert <= 2026) {
	                break;
	            } else {
	                System.out.println("[ERRORE] L'anno del certificato non sembra plausibile. Riprova.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("[ERRORE] Inserisci un anno numerico valido.");
	        }
	    }
	    System.out.print("Cognome medico certificatore: ");
	    String medico = scanner.nextLine();
	    nuovoStudente.impostaCertificato(annoCert, medico);
	    studenti.add(nuovoStudente);
	    System.out.println("\n[SUCCESSO] Studente registrato! Matricola assegnata: " + nuovoStudente.getMatricola());
	}

	private static void inserisciDocente() {
	    System.out.println("\n*NUOVA ASSUNZIONE DOCENTE*");
	    System.out.print("Nome: ");
	    String nome = scanner.nextLine();
	    System.out.print("Cognome: ");
	    String cognome = scanner.nextLine();
	    int annoNascita = 0;
	    while (true) {
	        System.out.print("Anno di nascita (1965-1995): ");
	        try {
	            annoNascita = Integer.parseInt(scanner.nextLine());
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
	    java.util.List<String> materieValide = java.util.Arrays.asList(
	        "Italiano", "Matematica", "Scienze", "Storia", "Geografia", "Inglese", "Musica", "Arte"
	    );
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
	    System.out.println("\n[SUCCESSO] Docente registrato con successo!");
	}

	private static void inserisciSocio() {
		try {
			System.out.print("Nome: ");
			String nome = scanner.nextLine();
			System.out.print("Cognome: ");
			String cognome = scanner.nextLine();

			SocioClub nuovoSocio = new SocioClub(nome, cognome);

			System.out.print("Anno rilascio certificato medico: ");
			int annoCert = Integer.parseInt(scanner.nextLine());
			System.out.print("Cognome medico certificatore: ");
			String medico = scanner.nextLine();
			nuovoSocio.impostaCertificato(annoCert, medico);

			sociClub.add(nuovoSocio);
			System.out.println("[SUCCESSO] Socio registrato correttamente!");
		} catch (Exception e) {
			System.out.println("[ERRORE] Inserimento fallito: " + e.getMessage());
		}
	}

	private static void stampaStudenti() {
		System.out.println(" *ELENCO STUDENTI REGISTRATI* ");
		if (studenti.isEmpty()) {
			System.out.println("Nessun studente registrato.");
		} else {
			studenti.forEach(System.out::println);
		}
	}

	private static void stampaDocenti() {
		System.out.println(" *ELENCO DOCENTI REGISTRATI* ");
		if (docenti.isEmpty()) {
			System.out.println("Nessun docente registrato.");
		} else {
			docenti.forEach(System.out::println);
		}
	}

	private static void stampaSoci() {
		System.out.println(" *ELENCO SOCI CLUB DI PALLAVOLO* ");
		if (sociClub.isEmpty()) {
			System.out.println("Nessun socio registrato.");
		} else {
			for (SocioClub socio : sociClub) {
				System.out.println(socio);
			}
		}
	}
}
package eccezioni6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisioneRadiceTest {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try { // controlla gli input da Scanner
			System.out.print("Immetti il numeratore: ");
			int num = input.nextInt();
			System.out.print("Immetti il denominatore: ");
			int den = input.nextInt();
			
			try { // controlla le eccezioni generatore dal worker div()
				System.out.println("La radice di: " + num + "/" + den + " è " + DivisioneRadice.div(num, den));
			}catch(Exception e) {
				System.out.println(e.getMessage()); // stampo l'argomento delle Exception di div()
			}
		}catch(InputMismatchException ime) { // eccezione da importare
			System.out.println("Dato immesso incompatibile!");
		}catch(Exception e) { // catch finale per intercettare tutte le eccezioni non previste
			System.out.println("Problema imprevisto, segnalare allo sviluppatore!");
		}finally {
			input.close();
			System.exit(0);
		}
	}

}

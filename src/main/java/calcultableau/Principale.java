package calcultableau;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Point d'entree du programme.
 * Saisie interactive des notes, affichage et stockage du resultat.
 */
public class Principale {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Identifiants de l'enseignant ---
        System.out.print("Prenom de l'enseignant : ");
        String prenom = sc.nextLine().trim();

        System.out.print("Nom de l'enseignant : ");
        String nom = sc.nextLine().trim();

        UtilisateurTab utilisateur = null;
        while (utilisateur == null) {
            System.out.print("Adresse mail : ");
            String email = sc.nextLine().trim();
            try {
                utilisateur = new UtilisateurTab(prenom, nom, email, LocalDate.now());
            } catch (IllegalArgumentException e) {
                System.out.println("Email invalide, veuillez reessayer.");
            }
        }

        // --- Saisie des notes ---
        CalculTab calcul = new CalculTab();
        System.out.println("\n****DEBUT SAISIE DES NOTES****");

        boolean continuer = true;
        while (continuer) {
            System.out.print("Entrez une note (ou -1 pour terminer) : ");
            try {
                int note = sc.nextInt();
                if (note == -1) {
                    continuer = false;
                } else {
                    calcul.ajouterNote(note);
                }
            } catch (InputMismatchException e) {
                System.out.println("Valeur invalide, entrez un entier.");
                sc.next(); // vide le token invalide
            }
        }

        // --- Affichage des resultats ---
        System.out.println("\n****RESULTATS****");
        System.out.println("Nombre d'etudiants : " + calcul.getTaille());
        System.out.printf("Moyenne            : %.2f%n", calcul.calculerMoyenne());
        System.out.printf("Mediane            : %.2f%n", calcul.calculerMediane());

        // --- Resultat stockable ---
        System.out.println("\n*** Resultat a stocker ***");
        System.out.println(calcul.toResultatString(utilisateur));

        System.out.println("****FIN PROGRAMME****");
        sc.close();
    }
}

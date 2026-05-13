package calcultableau;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Represente l'enseignant qui saisit les notes de son groupe TP.
 */
public class UtilisateurTab {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[\\w.-]+@[\\w.-]+\\.[a-z]{2,}");

    private final String prenom;
    private final String nom;
    private final String email;
    private final LocalDate dateExamen;

    /**
     * @param prenom      prenom de l'enseignant
     * @param nom         nom de l'enseignant
     * @param email       adresse mail (valide sinon IllegalArgumentException)
     * @param dateExamen  date de l'examen
     */
    public UtilisateurTab(String prenom, String nom, String email, LocalDate dateExamen) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Adresse mail invalide : " + email);
        }
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.dateExamen = dateExamen;
    }

    public String getPrenom()         { return prenom; }
    public String getNom()            { return nom; }
    public String getEmail()          { return email; }
    public LocalDate getDateExamen()  { return dateExamen; }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(prenom).append(", ")
                .append(nom).append(", ")
                .append(email).append(", ")
                .append(dateExamen)
                .toString();
    }
}

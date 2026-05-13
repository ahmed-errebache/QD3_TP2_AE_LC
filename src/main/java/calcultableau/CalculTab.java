package calcultableau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Gere une liste de notes et calcule moyenne et mediane.
 */
public class CalculTab {

    private final List<Integer> notes = new ArrayList<>();

    public CalculTab() {}

    public CalculTab(ArrayList<Integer> notes) {
        this.notes.addAll(notes);
    }

    
    /**
     * Ajoute une note dans la liste.
     *
     * @param note valeur entiere de la note
     */
    public void ajouterNote(int note) {
        if (note < 0 || note > 20)
            throw new IllegalArgumentException("Note invalide : " + note + " (doit etre entre 0 et 20)");
        notes.add(note);
    }

    /** @return nombre de notes saisies */
    public int getTaille() {
        return notes.size();
    }

    /**
     * Calcule la moyenne arithmetique des notes.
     *
     * @return moyenne, ou 0.0 si aucune note
     */
    public double calculerMoyenne() {
        if (notes.isEmpty()) return 0.0;
        int somme = 0;
        for (int note : notes) {
            somme += note;
        }
        return (double) somme / notes.size();
    }

    /**
     * Trie la liste puis calcule la mediane.
     * Pour un nombre pair d'elements, fait la moyenne des deux valeurs centrales.
     *
     * @return mediane, ou 0.0 si aucune note
     */
    public double calculerMediane() {
        if (notes.isEmpty()) return 0.0;

        List<Integer> triees = new ArrayList<>(notes);
        Collections.sort(triees);

        int milieu = triees.size() / 2;
        if (triees.size() % 2 == 1) {
            return triees.get(milieu);
        } else {
            return (triees.get(milieu - 1) + triees.get(milieu)) / 2.0;
        }
    }

    /** @return copie non modifiable de la liste des notes */
    public List<Integer> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    /**
     * Formate les statistiques pour affichage ou stockage.
     *
     * @param utilisateur enseignant proprietaire des resultats
     * @return chaine : prenom, nom, email, date, nb etudiants, moyenne, mediane
     */
    public String toResultatString(UtilisateurTab utilisateur) {
        return new StringBuilder()
                .append(utilisateur)
                .append(", ")
                .append(getTaille())
                .append(", ")
                .append(String.format(Locale.ROOT, "%.2f", calculerMoyenne()))
                .append(", ")
                .append(String.format(Locale.ROOT, "%.2f", calculerMediane()))
                .toString();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Notes : ").append(notes)
                .append(" | Taille : ").append(getTaille())
                .append(" | Moyenne : ").append(String.format(Locale.ROOT, "%.2f", calculerMoyenne()))
                .append(" | Mediane : ").append(String.format(Locale.ROOT, "%.2f", calculerMediane()))
                .toString();
    }
}

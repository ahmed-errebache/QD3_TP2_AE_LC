package calcultableau;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Scenarios complets d'utilisation de l'application.
 *
 * Contexte : Alain Dupont, enseignant en informatique (alain.dupont@iut.fr),
 * saisit les notes de son groupe TP, consulte les statistiques et stocke le resultat.
 *
 * Auteur : Ahmed Errebache (AE)
 */
@DisplayName("Scenarios : Alain Dupont analyse les notes de son groupe TP")
class ScenarioGroupeTPTest {

    private static final LocalDate DATE_EXAMEN = LocalDate.of(2026, 5, 13);

    // =========================================================================
    // Scenario 1 : groupe standard de 5 etudiants
    // =========================================================================
    @Nested
    @DisplayName("Scenario 1 : groupe standard de 5 etudiants avec notes variees")
    class ScenarioGroupeStandard {

        /**
         * Alain saisit 5 notes regulierement reparties.
         * Il attend une moyenne de 14.0 et une mediane de 14.0.
         */
        @Test
        @DisplayName("Moyenne et mediane correctes pour un groupe de 5 etudiants")
        void givenGroupeDeCinqEtudiants_whenCalculStats_thenMoyenneEtMedianeCorrectes() {
            // Given : un enseignant valide et 5 notes saisies
            UtilisateurTab enseignant = new UtilisateurTab(
                    "Alain", "Dupont", "alain.dupont@iut.fr", DATE_EXAMEN);
            CalculTab calcul = new CalculTab();
            calcul.ajouterNote(10);
            calcul.ajouterNote(12);
            calcul.ajouterNote(14);
            calcul.ajouterNote(16);
            calcul.ajouterNote(18);

            // When : calcul des statistiques
            double moyenne = calcul.calculerMoyenne();
            double mediane = calcul.calculerMediane();

            // Then : resultats attendus
            assertThat(calcul.getTaille()).isEqualTo(5);
            assertThat(moyenne).isEqualTo(14.0);
            assertThat(mediane).isEqualTo(14.0);
        }

        /**
         * Le resultat a stocker doit contenir : prenom, nom, email, date,
         * nombre d'etudiants, moyenne et mediane.
         */
        @Test
        @DisplayName("Le resultat stocke contient toutes les informations requises")
        void givenGroupeDeCinqEtudiants_whenGenerateResultat_thenContainsAllInfos() {
            // Given
            UtilisateurTab enseignant = new UtilisateurTab(
                    "Alain", "Dupont", "alain.dupont@iut.fr", DATE_EXAMEN);
            CalculTab calcul = new CalculTab();
            calcul.ajouterNote(10);
            calcul.ajouterNote(12);
            calcul.ajouterNote(14);
            calcul.ajouterNote(16);
            calcul.ajouterNote(18);

            // When
            String resultat = calcul.toResultatString(enseignant);

            // Then : chaque champ obligatoire est present
            assertThat(resultat)
                    .as("Le resultat doit contenir le prenom")
                    .contains("Alain");
            assertThat(resultat)
                    .as("Le resultat doit contenir le nom")
                    .contains("Dupont");
            assertThat(resultat)
                    .as("Le resultat doit contenir l'email")
                    .contains("alain.dupont@iut.fr");
            assertThat(resultat)
                    .as("Le resultat doit contenir la date de l'examen")
                    .contains("2026-05-13");
            assertThat(resultat)
                    .as("Le resultat doit contenir le nombre d'etudiants")
                    .contains("5");
            assertThat(resultat)
                    .as("Le resultat doit contenir la moyenne")
                    .contains("14.00");
            assertThat(resultat)
                    .as("Le resultat doit contenir la mediane")
                    .contains("14.00");
        }
    }

    // =========================================================================
    // Scenario 2 : groupe avec notes toutes identiques
    // =========================================================================
    @Nested
    @DisplayName("Scenario 2 : groupe ou tous les etudiants ont la meme note")
    class ScenarioNotesIdentiques {

        /**
         * Quand toutes les notes sont identiques, moyenne == mediane.
         */
        @Test
        @DisplayName("Moyenne et mediane sont egales quand toutes les notes sont identiques")
        void givenNotesIdentiques_whenCalculStats_thenMoyenneEgaleMediane() {
            // Given
            CalculTab calcul = new CalculTab();
            calcul.ajouterNote(12);
            calcul.ajouterNote(12);
            calcul.ajouterNote(12);

            // When
            double moyenne = calcul.calculerMoyenne();
            double mediane = calcul.calculerMediane();

            // Then
            assertThat(moyenne).isEqualTo(mediane);
            assertThat(moyenne).isEqualTo(12.0);
        }
    }

    // =========================================================================
    // Scenario 3 : grand groupe de 10 etudiants (nombre pair)
    // =========================================================================
    @Nested
    @DisplayName("Scenario 3 : grand groupe de 10 etudiants — nombre pair de notes")
    class ScenarioGrandGroupe {

        /**
         * Pour 10 etudiants avec notes de 8 a 17, la mediane est la moyenne
         * des deux valeurs centrales : (12+13)/2 = 12.5.
         */
        @Test
        @DisplayName("Mediane correcte pour un nombre pair de notes")
        void givenGrandGroupeDeDixEtudiants_whenCalculStats_thenStatsCorrectes() {
            // Given
            CalculTab calcul = new CalculTab();
            for (int note : new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17}) {
                calcul.ajouterNote(note);
            }

            // When
            double moyenne = calcul.calculerMoyenne();
            double mediane = calcul.calculerMediane();

            // Then
            assertThat(calcul.getTaille()).isEqualTo(10);
            assertThat(moyenne).isEqualTo(12.5);
            assertThat(mediane).isEqualTo(12.5);
        }

        @Test
        @DisplayName("Le resultat stocke contient le bon nombre d'etudiants")
        void givenGrandGroupeDeDixEtudiants_whenGenerateResultat_thenTailleCorrecte() {
            // Given
            UtilisateurTab enseignant = new UtilisateurTab(
                    "Alain", "Dupont", "alain.dupont@iut.fr", DATE_EXAMEN);
            CalculTab calcul = new CalculTab();
            for (int note : new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17}) {
                calcul.ajouterNote(note);
            }

            // When
            String resultat = calcul.toResultatString(enseignant);

            // Then
            assertThat(resultat).contains("10");
        }
    }

    // =========================================================================
    // Scenario 4 : un seul etudiant dans le groupe
    // =========================================================================
    @Nested
    @DisplayName("Scenario 4 : groupe avec un seul etudiant")
    class ScenarioEtudiantUnique {

        /**
         * Cas limite : un seul etudiant. Moyenne == Mediane == la note unique.
         */
        @Test
        @DisplayName("Moyenne et mediane egales a la note unique pour un seul etudiant")
        void givenUnSeulEtudiant_whenCalculStats_thenMoyenneEtMedianeEgalesALaNote() {
            // Given
            CalculTab calcul = new CalculTab();
            calcul.ajouterNote(15);

            // When
            double moyenne = calcul.calculerMoyenne();
            double mediane = calcul.calculerMediane();

            // Then
            assertThat(calcul.getTaille()).isEqualTo(1);
            assertThat(moyenne).isEqualTo(15.0);
            assertThat(mediane).isEqualTo(15.0);
        }
    }

    // =========================================================================
    // Scenario 5 : validation de l'identite de l'enseignant
    // =========================================================================
    @Nested
    @DisplayName("Scenario 5 : Alain Dupont saisit ses identifiants")
    class ScenarioIdentifiantsEnseignant {

        @Test
        @DisplayName("Creation reussie avec l'email institutionnel d'Alain Dupont")
        void givenEmailInstitutionnel_whenCreerUtilisateur_thenCreationReussie() {
            // Given + When
            UtilisateurTab enseignant = new UtilisateurTab(
                    "Alain", "Dupont", "alain.dupont@iut.fr", DATE_EXAMEN);

            // Then
            assertThat(enseignant.getPrenom()).isEqualTo("Alain");
            assertThat(enseignant.getEmail()).isEqualTo("alain.dupont@iut.fr");
        }

        @Test
        @DisplayName("Echec de creation si l'email est mal formate")
        void givenEmailInvalide_whenCreerUtilisateur_thenIllegalArgumentException() {
            // Given
            String emailInvalide = "alain.dupont-iut.fr";

            // When + Then
            assertThatThrownBy(() ->
                    new UtilisateurTab("Alain", "Dupont", emailInvalide, DATE_EXAMEN))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("invalide");
        }
    }
}

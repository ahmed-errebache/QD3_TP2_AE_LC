package calcultableau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Tests de CalculTab")
class CalculTabTest {

    private CalculTab calcul;

    @BeforeEach
    void setUp() {
        // Arrange commun : nouveau CalculTab vide avant chaque test
        calcul = new CalculTab();
    }

    // ---- ajouterNote / getTaille ----

    @Test
    @DisplayName("Taille vaut 0 quand aucune note n'est ajoutee")
    void getTaille_returnsZero_whenNoNoteAdded() {
        // Act + Assert
        assertThat(calcul.getTaille()).isZero();
    }

    @Test
    @DisplayName("Taille augmente apres chaque ajout de note")
    void getTaille_increases_afterEachNoteAdded() {
        // Act
        calcul.ajouterNote(10);
        calcul.ajouterNote(14);

        // Assert
        assertThat(calcul.getTaille()).isEqualTo(2);
    }

    // ---- calculerMoyenne ----

    @Test
    @DisplayName("Moyenne vaut 0 quand aucune note n'est presente")
    void calculerMoyenne_returnsZero_whenNoNote() {
        assertThat(calcul.calculerMoyenne()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Moyenne est correcte pour plusieurs notes")
    void calculerMoyenne_returnsCorrectAverage_forMultipleNotes() {
        // Arrange
        calcul.ajouterNote(10);
        calcul.ajouterNote(12);
        calcul.ajouterNote(14);

        // Act
        double moyenne = calcul.calculerMoyenne();

        // Assert
        assertThat(moyenne).isEqualTo(12.0);
    }

    @Test
    @DisplayName("Moyenne est correcte pour une seule note")
    void calculerMoyenne_returnsSingleNote_whenOnlyOneNote() {
        // Arrange
        calcul.ajouterNote(17);

        // Assert
        assertThat(calcul.calculerMoyenne()).isEqualTo(17.0);
    }

    // ---- calculerMediane ----

    @Test
    @DisplayName("Mediane vaut 0 quand aucune note n'est presente")
    void calculerMediane_returnsZero_whenNoNote() {
        assertThat(calcul.calculerMediane()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Mediane est la valeur centrale pour un nombre impair de notes")
    void calculerMediane_returnsMiddleValue_forOddCount() {
        // Arrange : notes non triees intentionnellement
        calcul.ajouterNote(15);
        calcul.ajouterNote(10);
        calcul.ajouterNote(12);

        // Act
        double mediane = calcul.calculerMediane();

        // Assert : liste triee = [10, 12, 15] -> mediane = 12
        assertThat(mediane).isEqualTo(12.0);
    }

    @Test
    @DisplayName("Mediane est la moyenne des deux valeurs centrales pour un nombre pair de notes")
    void calculerMediane_returnsAverageOfTwoMiddle_forEvenCount() {
        // Arrange
        calcul.ajouterNote(10);
        calcul.ajouterNote(12);
        calcul.ajouterNote(14);
        calcul.ajouterNote(16);

        // Act
        double mediane = calcul.calculerMediane();

        // Assert : [10, 12, 14, 16] -> (12+14)/2 = 13.0
        assertThat(mediane).isEqualTo(13.0);
    }

    @Test
    @DisplayName("Mediane vaut la note unique quand une seule note est presente")
    void calculerMediane_returnsSingleNote_whenOnlyOneNote() {
        calcul.ajouterNote(8);
        assertThat(calcul.calculerMediane()).isEqualTo(8.0);
    }

    // ---- getNotes ----

    @Test
    @DisplayName("getNotes renvoie une liste non modifiable")
    void getNotes_returnsUnmodifiableList() {
        // Arrange
        calcul.ajouterNote(5);

        // Assert
        assertThatThrownBy(() -> calcul.getNotes().add(99))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}

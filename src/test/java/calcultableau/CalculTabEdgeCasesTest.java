package calcultableau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests des cas limites de CalculTab : validation des notes hors plage [0, 20].
 *
 * Auteur : Lucas Charpentier (LC)
 */
@DisplayName("Tests cas limites : validation des notes")
class CalculTabEdgeCasesTest {

    private CalculTab calcul;

    @BeforeEach
    void setUp() {
        calcul = new CalculTab();
    }

    // ---- Notes invalides (hors plage) ----

    @ParameterizedTest(name = "Note invalide : {0}")
    @DisplayName("ajouterNote leve une exception pour toute note superieure a 20")
    @ValueSource(ints = { 21, 25, 100 })
    void ajouterNote_throwsException_whenNoteAbove20(int noteInvalide) {
        // Given + When + Then
        assertThatThrownBy(() -> calcul.ajouterNote(noteInvalide))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalide");
    }

    @ParameterizedTest(name = "Note invalide : {0}")
    @DisplayName("ajouterNote leve une exception pour toute note negative")
    @ValueSource(ints = { -1, -5, -100 })
    void ajouterNote_throwsException_whenNoteNegative(int noteInvalide) {
        // Given + When + Then
        assertThatThrownBy(() -> calcul.ajouterNote(noteInvalide))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalide");
    }

    // ---- Notes valides aux bornes ----

    @Test
    @DisplayName("ajouterNote accepte la note 0 (borne inferieure)")
    void ajouterNote_succeeds_whenNoteIsZero() {
        // Given + When
        calcul.ajouterNote(0);
        // Then
        assertThat(calcul.getTaille()).isEqualTo(1);
        assertThat(calcul.calculerMoyenne()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("ajouterNote accepte la note 20 (borne superieure)")
    void ajouterNote_succeeds_whenNoteIsTwenty() {
        // Given + When
        calcul.ajouterNote(20);
        // Then
        assertThat(calcul.getTaille()).isEqualTo(1);
        assertThat(calcul.calculerMoyenne()).isEqualTo(20.0);
    }

    // ---- Cas limites de calcul ----

    @Test
    @DisplayName("Moyenne correcte avec les notes aux bornes 0 et 20")
    void calculerMoyenne_returnsCorrectAverage_withBoundaryNotes() {
        // Given
        calcul.ajouterNote(0);
        calcul.ajouterNote(20);
        // When + Then : (0 + 20) / 2 = 10.0
        assertThat(calcul.calculerMoyenne()).isEqualTo(10.0);
    }

    @Test
    @DisplayName("Mediane correcte avec notes identiques aux bornes")
    void calculerMediane_returnsCorrectMedian_withIdenticalBoundaryNotes() {
        // Given
        calcul.ajouterNote(0);
        calcul.ajouterNote(0);
        calcul.ajouterNote(0);
        // When + Then
        assertThat(calcul.calculerMediane()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Le tri interne ne modifie pas la liste originale")
    void calculerMediane_doesNotModifyOriginalList_whenSorting() {
        // Given : notes saisies en desordre
        calcul.ajouterNote(15);
        calcul.ajouterNote(5);
        calcul.ajouterNote(10);

        // When : calcul de la mediane (effectue un tri interne)
        calcul.calculerMediane();

        // Then : l'ordre original est conserve
        assertThat(calcul.getNotes()).containsExactly(15, 5, 10);
    }
}

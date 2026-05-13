package calcultableau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

@DisplayName("Tests de CalculTabEdgeCases")
public class CalculTabEdgeCasesTest {
    
    @Test
    @DisplayName("calculerMoyenne avec uniquement des zeros retourne zero")
    void calculerMoyenne_notesToutesNulles_retourneZero() {

        // Given
        CalculTab calculTab = new CalculTab();

        calculTab.ajouterNote(0);
        calculTab.ajouterNote(0);
        calculTab.ajouterNote(0);

        // When
        double moyenne = calculTab.calculerMoyenne();
        double mediane = calculTab.calculerMediane();

        // Then
        assertThat(moyenne).isEqualTo(0);
        assertThat(mediane).isEqualTo(0);
    }


    @Test
    @DisplayName("calcul avec notes minimales et maximales fonctionne")
    void calculerMoyenne_notesMinEtMax_fonctionneCorrectement() {

        // Given
        ArrayList<Integer> notes = new ArrayList<>();
        notes.add(0);
        notes.add(20);

        CalculTab calculTab = new CalculTab(notes);

        // When
        double moyenne = calculTab.calculerMoyenne();
        double mediane = calculTab.calculerMediane();

        // Then
        assertThat(moyenne).isEqualTo(10);
        assertThat(mediane).isEqualTo(10);
    }


    @Test
    @DisplayName("calculerMediane ne modifie pas la liste originale")
    void calculerMediane_notesDesordonnees_neModifiePasListeOriginale() {

        // Given
        ArrayList<Integer> notes = new ArrayList<>();
        notes.add(15);
        notes.add(2);
        notes.add(19);

        ArrayList<Integer> original = new ArrayList<>(notes);

        CalculTab calculTab = new CalculTab(notes);

        // When
        calculTab.calculerMediane();

        // Then
        assertThat(notes).isEqualTo(original);
    }


    @Test
    @DisplayName("calcul mediane avec notes identiques fonctionne")
    void calculerMediane_notesIdentiques_retourneBonneValeur() {

        // Given
        ArrayList<Integer> notes = new ArrayList<>();
        notes.add(10);
        notes.add(10);
        notes.add(15);
        notes.add(20);

        CalculTab calculTab = new CalculTab(notes);

        // When
        double mediane = calculTab.calculerMediane();

        // Then
        assertThat(mediane).isEqualTo(12.5);
    }


    // ---- Validation notes négatives (hors -1 sentinel) ----

    @ParameterizedTest(name = "Note invalide : {0}")
    @DisplayName("ajouterNote refuse toute note negative : note invalide")
    @ValueSource(ints = { -2, -5, -10, -100 })
    void ajouterNote_throwsException_whenNoteIsNegative(int noteNegative) {
        // Given
        CalculTab calculTab = new CalculTab();

        // When + Then
        assertThatThrownBy(() -> calculTab.ajouterNote(noteNegative))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalide");
    }

    @Test
    @DisplayName("ajouterNote refuse -1 : note invalide")
    void ajouterNote_throwsException_whenNoteIsMinusOne() {
        // Given
        CalculTab calculTab = new CalculTab();

        // When + Then
        assertThatThrownBy(() -> calculTab.ajouterNote(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalide");
    }
}

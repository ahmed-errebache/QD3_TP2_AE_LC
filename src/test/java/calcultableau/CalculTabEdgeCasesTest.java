package calcultableau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}

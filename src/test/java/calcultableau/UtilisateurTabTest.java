package calcultableau;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Tests de UtilisateurTab")
class UtilisateurTabTest {

    private static final LocalDate DATE_EXAMEN = LocalDate.of(2026, 5, 13);

    // ---- Constructeur avec email valide ----

    @Test
    @DisplayName("Creation reussie avec un email valide")
    void constructor_createsUser_whenEmailIsValid() {
        // Arrange + Act
        UtilisateurTab u = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr", DATE_EXAMEN);

        // Assert
        assertThat(u.getPrenom()).isEqualTo("Alain");
        assertThat(u.getNom()).isEqualTo("Dupont");
        assertThat(u.getEmail()).isEqualTo("alain.dupont@iut.fr");
        assertThat(u.getDateExamen()).isEqualTo(DATE_EXAMEN);
    }

    // ---- Constructeur avec email invalide ----

    @ParameterizedTest(name = "Email invalide : \"{0}\"")
    @DisplayName("Construction echoue pour des emails invalides")
    @ValueSource(strings = { "pasunmail", "sans@domaine", "@nodomain.fr", "manque.arobase.fr", "" })
    void constructor_throwsException_whenEmailIsInvalid(String emailInvalide) {
        // Act + Assert
        assertThatThrownBy(() ->
                new UtilisateurTab("Alain", "Dupont", emailInvalide, DATE_EXAMEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalide");
    }

    // ---- toString ----

    @Test
    @DisplayName("toString contient prenom, nom, email et date")
    void toString_containsAllFields() {
        // Arrange
        UtilisateurTab u = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr", DATE_EXAMEN);

        // Act
        String resultat = u.toString();

        // Assert
        assertThat(resultat)
                .contains("Alain")
                .contains("Dupont")
                .contains("alain.dupont@iut.fr")
                .contains("2026-05-13");
    }
}

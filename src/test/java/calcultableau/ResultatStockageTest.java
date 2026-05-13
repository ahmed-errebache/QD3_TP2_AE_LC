package calcultableau;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests de ResultatStockage : ecriture du resultat dans un fichier texte.
 *
 * Auteur : Lucas Charpentier (LC)
 */
@DisplayName("Tests de ResultatStockage : persistance du resultat")
class ResultatStockageTest {

    private static final String FICHIER_TEST = "test_resultat.txt";

    @AfterEach
    void nettoyage() throws IOException {
        Files.deleteIfExists(Paths.get(FICHIER_TEST));
    }

    @Test
    @DisplayName("sauvegarder cree le fichier s'il n'existe pas")
    void sauvegarder_createsFile_whenFileDoesNotExist() throws IOException {
        // Given
        ResultatStockage stockage = new ResultatStockage();
        String contenu = "Alain, Dupont, alain.dupont@iut.fr, 2026-05-13, 5, 14.00, 14.00";

        // When
        stockage.sauvegarder(contenu, FICHIER_TEST);

        // Then
        assertThat(Paths.get(FICHIER_TEST)).exists();
    }

    @Test
    @DisplayName("sauvegarder ecrit exactement le contenu fourni")
    void sauvegarder_writesExactContent_whenCalled() throws IOException {
        // Given
        ResultatStockage stockage = new ResultatStockage();
        String contenu = "Alain, Dupont, alain.dupont@iut.fr, 2026-05-13, 5, 14.00, 14.00";

        // When
        stockage.sauvegarder(contenu, FICHIER_TEST);

        // Then
        String contenuLu = Files.readString(Paths.get(FICHIER_TEST));
        assertThat(contenuLu).isEqualTo(contenu);
    }

    @Test
    @DisplayName("sauvegarder fonctionne avec le resultat reel de toResultatString")
    void sauvegarder_worksWithRealResultat_fromCalculTab() throws IOException {
        // Given
        UtilisateurTab enseignant = new UtilisateurTab(
                "Lucas", "Charpentier", "lucas.charpentier@iut.fr",
                LocalDate.of(2026, 5, 13));
        CalculTab calcul = new CalculTab();
        calcul.ajouterNote(10);
        calcul.ajouterNote(14);
        calcul.ajouterNote(18);

        ResultatStockage stockage = new ResultatStockage();

        // When
        String contenu = calcul.toResultatString(enseignant);
        stockage.sauvegarder(contenu, FICHIER_TEST);

        // Then
        Path fichier = Paths.get(FICHIER_TEST);
        assertThat(fichier).exists();
        assertThat(Files.readString(fichier))
                .contains("Lucas")
                .contains("Charpentier")
                .contains("14.00");
    }
}

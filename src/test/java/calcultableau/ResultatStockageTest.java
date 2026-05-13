package calcultableau;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultatStockageTest {
    
    @Test
    @DisplayName("sauvegarder cree le fichier")
    void sauvegarder_contenuValide_creeFichier() throws IOException {

        // Given
        ResultatStockage stockage = new ResultatStockage();

        String chemin = "test.txt";

        // When
        stockage.sauvegarder("bonjour", chemin);

        // Then
        File fichier = new File(chemin);

        assertThat(fichier.exists()).isTrue();

        fichier.delete();
    }


    @Test
    @DisplayName("sauvegarder ecrit le bon contenu")
    void sauvegarder_contenuValide_ecritBonContenu() throws IOException {

        // Given
        ResultatStockage stockage = new ResultatStockage();

        String chemin = "test.txt";

        // When
        stockage.sauvegarder("bonjour", chemin);

        String contenu =
                Files.readString(Path.of(chemin));

        // Then
        assertThat(contenu).isEqualTo("bonjour");

        new File(chemin).delete();
    }
}

package calcultableau;

import java.io.FileWriter;
import java.io.IOException;

public class ResultatStockage {

    public void sauvegarder(String contenu, String cheminFichier)
            throws IOException {

        FileWriter writer = new FileWriter(cheminFichier);

        writer.write(contenu);

        writer.close();
    }
}
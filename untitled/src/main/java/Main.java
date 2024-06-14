import javafx.application.Application;
import org.javatuples.Pair;
import java.io.*;
import static modell.Szavak.szotar;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String line = "";
        BufferedReader buffer = new BufferedReader(new FileReader("/angolmagyar.csv.txt"));

        do {
            try {
                line = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(line == null) break;

            String[] token = line.split(",");
            szotar.add(new Pair<>(token[0], token[1]));
        } while (true);

        Application.launch(SzoTanuloApplication.class, args);

    }

}

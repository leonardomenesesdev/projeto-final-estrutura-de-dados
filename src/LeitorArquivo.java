import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {
    public static void processarTexto(String caminhoTexto, Hash tabela) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoTexto))) {
            String linha;
            int numeroLinha = 1;

            while ((linha = br.readLine()) != null) {
                // Remove pontuações e quebra a linha em palavras
                String[] palavras = linha.toLowerCase().split("[^a-zA-Z]+");

                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        tabela.inserir(palavra, numeroLinha);
                    }
                }

                numeroLinha++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de texto: " + e.getMessage());
        }
    }
}

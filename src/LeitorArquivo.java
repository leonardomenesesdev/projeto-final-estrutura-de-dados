import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class LeitorArquivo {

    public static void processarTexto(String caminhoTexto, Hash tabela) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoTexto))) {
            String linha;
            int numeroLinha = 1;

            while ((linha = br.readLine()) != null) {
                // Regex que aceita letras com acento (usar \p{L} = qualquer letra de qualquer idioma)
                String[] palavras = linha.split("[^\\p{L}-]+");

                for (String palavraOriginal : palavras) {
                    if (!palavraOriginal.isEmpty()) {
                        String normalizada = removerAcentos(palavraOriginal).toLowerCase();
                        String chave = removerPlural(normalizada);
                        tabela.inserir(chave, numeroLinha);
                    }
                }

                numeroLinha++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de texto: " + e.getMessage());
        }
    }

    private static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
    private static String removerPlural(String palavra) {
        if (palavra.length() > 2 && palavra.endsWith("s") && !palavra.endsWith("ss")) {
            return palavra.substring(0, palavra.length() - 1);
        }
        return palavra;
    }
}

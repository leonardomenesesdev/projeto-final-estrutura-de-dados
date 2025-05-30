import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.Normalizer;

public class LeitorChave {

    public static void gerarIndice(String caminhoChaves, String caminhoSaida, Hash tabela) {

        try (
                BufferedReader br = new BufferedReader(new FileReader(caminhoChaves));
                PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))
        ) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] chaves = linha.split("[^\\p{L}-]+"); // aceita acentos

                for (String chaveOriginal : chaves) {
                    if (!chaveOriginal.isEmpty()) {
                        String chaveNormalizada = removerPlural(removerAcentos(chaveOriginal).toLowerCase());
                        Palavra palavraBusca = new Palavra(chaveNormalizada);
                        Palavra encontrada = tabela.buscar(palavraBusca);
                        if (encontrada != null && !encontrada.foiImpressa()) {
                            pw.println(chaveOriginal + ": " + encontrada.getOcorrencias());
                            encontrada.marcarComoImpressa();
                        }

                    }
                }
            }

            System.out.println("Índice remissivo gerado em: " + caminhoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever arquivos: " + e.getMessage());
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

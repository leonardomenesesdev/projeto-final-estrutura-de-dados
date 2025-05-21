import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.Normalizer;

public class LeitorChave {

    public static void gerarIndice(String caminhoChaves, String caminhoSaida, Hash tabela) {
        final int MAX_RESULTADOS = 10000; // Limite de entradas (ajuste conforme necessário)
        String[] resultados = new String[MAX_RESULTADOS];
        int contador = 0;

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
                        Palavra p = tabela.buscar(chaveNormalizada);
                        if (p != null) {
                            resultados[contador++] = chaveOriginal + ": " + p.getOcorrencias(); // ou p.toString()
                        } else {
                            resultados[contador++] = chaveOriginal + ": (não encontrada)";
                        }
                    }
                }
            }

            // Ordenação alfabética (bubble sort)
            for (int i = 0; i < contador - 1; i++) {
                for (int j = 0; j < contador - 1 - i; j++) {
                    if (resultados[j].compareTo(resultados[j + 1]) > 0) {
                        String temp = resultados[j];
                        resultados[j] = resultados[j + 1];
                        resultados[j + 1] = temp;
                    }
                }
            }

            // Escreve os resultados no arquivo
            for (int i = 0; i < contador; i++) {
                pw.println(resultados[i]);
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

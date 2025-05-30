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
                String[] chavesOriginais = linha.split("[^\\p{L}-]+");
                int n = chavesOriginais.length;

                String[] chavesNormalizadas = new String[n];
                for (int i = 0; i < n; i++) {
                    chavesNormalizadas[i] = removerPlural(removerAcentos(chavesOriginais[i]).toLowerCase());
                }

                // Ordena com base nas versões normalizadas
                ordenaChaves(chavesOriginais, chavesNormalizadas, n);
                for (String chaveOriginal : chavesOriginais) {
                    if (!chaveOriginal.isEmpty() && !chaveOriginal.endsWith("s")) {
                        String chaveNormalizada = removerPlural(removerAcentos(chaveOriginal).toLowerCase());
                        Palavra palavraBusca = new Palavra(chaveNormalizada);
                        Palavra encontrada = tabela.buscar(palavraBusca);
                        if (encontrada != null  && !encontrada.isPlural()) {
                            pw.println(chaveOriginal + ": " + encontrada.getOcorrencias());
                        } else {
                            pw.println(chaveOriginal + ": não encontrada");
                        }
                    }
                }
            }

            System.out.println("Índice remissivo gerado em: " + caminhoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever arquivos: " + e.getMessage());
        }
    }

    private static void ordenaChaves(String[] chaves, int length) {
        for(int i = 0; i < length-1; i++){
            for (int j = 0; j < length-i-1; j++){
                if(chaves[j].compareTo(chaves[j+1]) > 0){
                    String temp = chaves[j];
                    chaves[j] = chaves[j+1];
                    chaves[j+1] = temp;
                }
            }
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
    private static void ordenaChaves(String[] originais, String[] normalizadas, int length) {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (normalizadas[j].compareTo(normalizadas[j + 1]) > 0) {
                    // Troca nas normalizadas
                    String tempNorm = normalizadas[j];
                    normalizadas[j] = normalizadas[j + 1];
                    normalizadas[j + 1] = tempNorm;

                    // Troca correspondente nos originais
                    String tempOrig = originais[j];
                    originais[j] = originais[j + 1];
                    originais[j + 1] = tempOrig;
                }
            }
        }
    }

}
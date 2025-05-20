import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorChave {
    public static void gerarIndice(String caminhoChaves, String caminhoSaida, Hash tabela) {
        try (
                BufferedReader br = new BufferedReader(new FileReader(caminhoChaves));
                PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))
        ) {
            String linha;
            ArrayList<String> resultados = new ArrayList<>();

            while ((linha = br.readLine()) != null) {
                String[] chaves = linha.toLowerCase().split("[^a-zA-Z-]+");

                for (String chave : chaves) {
                    if (!chave.isEmpty()) {
                        Palavra p = tabela.buscar(chave);
                        if (p != null) {
                            resultados.add(p.toString());
                        } else {
                            resultados.add(chave + ": (não encontrada)");
                        }
                    }
                }
            }

            // Ordenação alfabética manual (bubble sort)
            for (int i = 0; i < resultados.size() - 1; i++) {
                for (int j = 0; j < resultados.size() - 1 - i; j++) {
                    if (resultados.get(j).compareTo(resultados.get(j + 1)) > 0) {
                        // troca
                        String temp = resultados.get(j);
                        resultados.set(j, resultados.get(j + 1));
                        resultados.set(j + 1, temp);
                    }
                }
            }

            // Escreve no arquivo em ordem alfabética
            for (String resultado : resultados) {
                pw.println(resultado);
            }

            System.out.println("Índice remissivo gerado em: " + caminhoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever arquivos: " + e.getMessage());
        }
    }
}

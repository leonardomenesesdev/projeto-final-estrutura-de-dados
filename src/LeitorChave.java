import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class LeitorChave {
    public static void gerarIndice(String caminhoChaves, String caminhoSaida, Hash tabela) {
        try (
                BufferedReader br = new BufferedReader(new FileReader(caminhoChaves));
                PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))
        ) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] chaves = linha.toLowerCase().split("[^a-zA-Z-]+");

                for (String chave : chaves) {
                    if (!chave.isEmpty()) {
                        Palavra p = tabela.buscar(chave);
                        if (p != null) {
                            pw.println(p);
                        } else {
                            pw.println(chave + ": (não encontrada)");
                        }
                    }
                }
            }

            System.out.println("Índice remissivo gerado em: " + caminhoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever arquivos: " + e.getMessage());
        }
    }
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Hash tabela = new Hash();
        String caminhoTexto = "C:\\Users\\Leo\\texto.txt";
        String caminhoChaves = "C:\\Users\\Leo\\entrada.txt";
        String caminhoSaida = "C:\\Users\\Leo\\indice_remissivo.txt";
        // Passo 1: Processar o texto
        LeitorArquivo.processarTexto(caminhoTexto, tabela);

        // Passo 2: Gerar índice remissivo com as palavras-chave
        LeitorChave.gerarIndice(caminhoChaves, caminhoSaida, tabela);
    }
}
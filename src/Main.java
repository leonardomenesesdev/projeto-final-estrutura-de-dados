//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Hash tabela = new Hash();
        //caminhos notebook
//        String caminhoTexto = "C:\\Users\\leomc\\texto.txt";
//        String caminhoChaves = "C:\\Users\\leomc\\entrada.txt";
//        String caminhoSaida = "C:\\Users\\leomc\\indice_remissivo.txt";
        //caminhos PC
        String caminhoTexto = "C:\\Users\\Leo\\texto.txt";
        String caminhoChaves = "C:\\Users\\Leo\\entrada.txt";
        String caminhoSaida = "C:\\Users\\Leo\\indice_remissivo.txt";
        LeitorArquivo.processarTexto(caminhoTexto, tabela);
        LeitorChave.gerarIndice(caminhoChaves, caminhoSaida, tabela);
    }
}
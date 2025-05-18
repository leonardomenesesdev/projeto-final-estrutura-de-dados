public class Palavra implements Comparable<Palavra> {
    String texto;
    ListOcorrencia ocorrencias;

    public Palavra(String texto) {
        this.texto = texto;
        this.ocorrencias = new ListOcorrencia();
    }

    public void adicionarOcorrencia(int linha) {
        ocorrencias.inserir(linha);
    }

    public String getTexto() {
        return texto;
    }

    public ListOcorrencia getOcorrencias() {
        return ocorrencias;
    }

    @Override
    public int compareTo(Palavra outra) {
        return this.texto.compareTo(outra.texto);
    }

    // nao sei se utiliza pro arquivo txt mas pra teste ai é bom
    @Override
    public String toString() {
        return texto + ": " + ocorrencias.toString();
    }
}
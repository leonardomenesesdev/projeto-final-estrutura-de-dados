public class Palavra implements Comparable<Palavra> {
    String texto; //atributo palavra
    ListOcorrencia ocorrencias; //atributo list

    public Palavra(String texto) {
        this.texto = texto;
        this.ocorrencias = new ListOcorrencia();
    }
    public void adicionarOcorrencia(int linha) {
        ocorrencias.inserir(linha);
    }

    public ListOcorrencia getOcorrencias() {
        return ocorrencias;
    }
    @Override
    public int compareTo(Palavra outra) {
        return this.texto.compareTo(outra.texto);
    }
    @Override
    public String toString() {
        return texto + ": " + ocorrencias.toString();
    }

    public boolean isPlural() {
        if(texto.endsWith("s")||texto.endsWith("ss")){
            return true;
        }
        return false;
    }
}

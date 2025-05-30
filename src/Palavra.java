public class Palavra implements Comparable<Palavra> {
    String texto; //atributo palavra
    ListOcorrencia ocorrencias; //atributo list
    private boolean impressa = false;
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
    public void marcarComoImpressa() {
        this.impressa = true;
    }
    public ListOcorrencia getOcorrencias() {
        return ocorrencias;
    }
    public boolean terminaemS(){
        if(texto.endsWith("s")){
            return true;
        }
        return false;
    }

    public void marcarComoNaoEncontrada() {
        this.ocorrencias.limpar();
    }

    public void setOcorrencias(ListOcorrencia lista) {
        this.ocorrencias = copiarLista(lista);
    }

    private ListOcorrencia copiarLista(ListOcorrencia original) {
        ListOcorrencia nova = new ListOcorrencia();
        NoOcorrencia atual = original.getPrimeiro();
        while (atual != null) {
            nova.inserir(atual.linha);
            atual = atual.prox;
        }
        return nova;
    }
    public boolean foiImpressa() {
        return impressa;
    }
    //adicionei pra conseguir imprimir palavras com acento com a ordenação normal
    public String textoNormalizado() {
        return java.text.Normalizer.normalize(this.texto.toLowerCase(), java.text.Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    @Override
    public int compareTo(Palavra outra) {
        return this.texto.compareTo(outra.texto);
    }

    @Override
    public String toString() {
        return texto + ": " + ocorrencias.toString();
    }
}

public class ListOcorrencia {
    private NoOcorrencia primeiro;
    private NoOcorrencia ultimo;
    private int tamanho;

    public ListOcorrencia() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }


    public void inserir(int linha) {
        if (!contem(linha)) {
            NoOcorrencia novo = new NoOcorrencia(linha);
            if (primeiro == null) {
                primeiro = novo;
                ultimo = novo;
            } else {
                ultimo.prox = novo;
                ultimo = novo;
            }
            tamanho++;
        }
    }

    public boolean contem(int linha) {
        NoOcorrencia atual = primeiro;
        while (atual != null) {
            if (atual.linha == linha) {
                return true;
            }
            atual = atual.prox;
        }
        return false;
    }

    @Override
    public String toString() {
        String resultado = "";
        NoOcorrencia atual = primeiro;

        while (atual != null) {
            resultado += atual.linha;

            if (atual.prox != null) {
                resultado += ", ";
            }

            atual = atual.prox;
        }

        return resultado;
    }
    // meio inutil ach, mas ta ai pq usei o modelo base dele
    public int getTamanho() {
        return tamanho;
    }
}
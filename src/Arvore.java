public class Arvore {
    private NoArvore raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void inserir(String texto, int linha) {
        raiz = inserirRec(raiz, texto.toLowerCase(), linha);
    }

    private NoArvore inserirRec(NoArvore no, String texto, int linha) {
        if (no == null) {
            Palavra novaPalavra = new Palavra(texto);
            novaPalavra.adicionarOcorrencia(linha);
            return new NoArvore(novaPalavra);
        }

        int comp = texto.compareTo(no.palavra.texto);

        if (comp < 0) {
            no.esquerda = inserirRec(no.esquerda, texto, linha);
        } else if (comp > 0) {
            no.direita = inserirRec(no.direita, texto, linha);
        } else {
            // Palavra já existe, só adiciona a ocorrência
            no.palavra.adicionarOcorrencia(linha);
        }

        return no;
    }

    public Palavra buscar(String texto) {
        return buscarRec(raiz, texto.toLowerCase());
    }

    private Palavra buscarRec(NoArvore no, String texto) {
        if (no == null) return null;

        int comparacao = texto.compareTo(no.palavra.texto);
        if (comparacao < 0) {
            return buscarRec(no.esquerda, texto);
        } else if (comparacao > 0) {
            return buscarRec(no.direita, texto);
        } else {
            return no.palavra;
        }
    }

    public void imprimirEmOrdem() {
        imprimirRec(raiz);
    }

    private void imprimirRec(NoArvore no) {
        if (no != null) {
            imprimirRec(no.esquerda);
            System.out.println(no.palavra.texto + ": " + no.palavra.ocorrencias);
            imprimirRec(no.direita);
        }
    }

    // Gera uma lista de palavras em ordem alfabética
    public void emOrdemParaLista(java.util.List<Palavra> lista) {
        emOrdemParaListaRec(raiz, lista);
    }

    private void emOrdemParaListaRec(NoArvore no, java.util.List<Palavra> lista) {
        if (no != null) {
            emOrdemParaListaRec(no.esquerda, lista);
            lista.add(no.palavra);
            emOrdemParaListaRec(no.direita, lista);
        }
    }
}

import java.io.PrintWriter;

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

    public void inserirResultado(Palavra resultado) {
        raiz = inserirResultadoRec(raiz, resultado);
    }

    private NoArvore inserirResultadoRec(NoArvore no, Palavra resultado) {
        if (no == null) {
            return new NoArvore(resultado);
        }

        int comp = resultado.texto.compareTo(no.palavra.texto);
        if (comp > 0) {
            no.esquerda = inserirResultadoRec(no.esquerda, resultado);
        } else if (comp < 0) {
            no.direita = inserirResultadoRec(no.direita, resultado);
        } else {
            // evita duplicação
        }

        return no;
    }
    public void imprimirResultadosOrdenados(PrintWriter pw) {
        imprimirResultadosRec(raiz, pw);
    }

    private void imprimirResultadosRec(NoArvore no, PrintWriter pw) {
        if (no != null) {
            imprimirResultadosRec(no.esquerda, pw);

            if (no.palavra.getOcorrencias() == null) {
                pw.println(no.palavra.texto + ": (não encontrada)");
            } else {
                if (no.palavra.terminaemS() == false) {
                    pw.println(no.palavra.texto + ": " + no.palavra.getOcorrencias());
                }
            }
            imprimirResultadosRec(no.direita, pw);
        }
    }

}

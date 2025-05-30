import java.io.PrintWriter;
import java.text.Normalizer;

public class Arvore {
    private NoArvore raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void inserir(Palavra palavra, int linha) {
        raiz = inserirRec(raiz, palavra, linha);
    }

    private NoArvore inserirRec(NoArvore no, Palavra palavra, int linha) {
        if (no == null) {
            palavra.adicionarOcorrencia(linha);
            return new NoArvore(palavra);
        }

        String textoNovo = normalizar(palavra.texto);
        String textoNo = normalizar(no.palavra.texto);

        int comp = textoNovo.compareTo(textoNo);

        if (comp < 0) {
            no.esquerda = inserirRec(no.esquerda, palavra, linha);
        } else if (comp > 0) {
            no.direita = inserirRec(no.direita, palavra, linha);
        } else {
            no.palavra.adicionarOcorrencia(linha);
        }

        return no;
    }

    public Palavra buscar(Palavra texto) {
        return buscarRec(raiz, texto.texto);
    }

    private Palavra buscarRec(NoArvore no, String texto) {
        if (no == null) return null;

        String textoNormalizado = normalizar(texto);
        String noNormalizado = normalizar(no.palavra.texto);

        int comparacao = textoNormalizado.compareTo(noNormalizado);
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
            System.out.println(no.palavra.texto + ": " + no.palavra.getOcorrencias());
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

        String novo = normalizar(resultado.texto);
        String atual = normalizar(no.palavra.texto);

        int comp = novo.compareTo(atual);
        if (comp < 0) {
            no.esquerda = inserirResultadoRec(no.esquerda, resultado);
        } else if (comp > 0) {
            no.direita = inserirResultadoRec(no.direita, resultado);
        } else {
            // já existe, ignora
        }

        return no;
    }

    public void imprimirResultadosOrdenados(PrintWriter pw) {
        imprimirResultadosRec(raiz, pw);
    }

    private void imprimirResultadosRec(NoArvore no, PrintWriter pw) {
        if (no != null) {
            imprimirResultadosRec(no.esquerda, pw);

            if (no.palavra.getOcorrencias() == null || no.palavra.getOcorrencias().isEmpty()) {
                pw.println(no.palavra.texto + ": (não encontrada)");
            } else {
                pw.println(no.palavra.texto + ": " + no.palavra.getOcorrencias());
            }

            imprimirResultadosRec(no.direita, pw);
        }
    }

    // Metodo auxiliar para remover acentos
    private String normalizar(String texto) {
        return Normalizer.normalize(texto.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}

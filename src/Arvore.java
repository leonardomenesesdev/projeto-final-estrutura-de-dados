import java.io.PrintWriter;
import java.text.Normalizer;

public class Arvore {
    private NoArvore raiz;
    int n_elementos;
    public Arvore() {
        this.raiz = null;
        n_elementos = 0;
    }

    public void inserir(Palavra palavra, int linha) {
        raiz = insereRecu(raiz, palavra, linha);
    }

    public NoArvore insereRecu(NoArvore no, Palavra palavra, int linha) {
        if (no == null) {
            palavra.adicionarOcorrencia(linha);
            no = new NoArvore(palavra); //mexi agora
        }
        //o primeiro textoNo seria a raiz, daí vai comparando recursivamente
        String textoNovo = normalizar(palavra.texto);
        String textoNo = normalizar(no.palavra.texto);

        int comp = textoNovo.compareTo(textoNo);

        if (comp < 0) {
            no.esquerda = insereRecu(no.esquerda, palavra, linha);
        } else if (comp > 0) {
            no.direita = insereRecu(no.direita, palavra, linha);
        } else {
            no.palavra.adicionarOcorrencia(linha);
        }

        return no;
    }

    public Palavra buscar(Palavra texto) {
        return buscarRec(raiz, texto.texto);
    }

    public Palavra buscarRec(NoArvore no, String texto) {
        if (no == null){
            return null;
        }

        String textoNormalizado = normalizar(texto);
        String noNormalizado = normalizar(no.palavra.texto);

        int comp = textoNormalizado.compareTo(noNormalizado);
        if (comp < 0) {
            return buscarRec(no.esquerda, texto);
        } else if (comp > 0) {
            return buscarRec(no.direita, texto);
        } else {
            return no.palavra;
        }
    }


    // metodo pra tirar os acentos
    public String normalizar(String texto) {
        return Normalizer.normalize(texto.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}

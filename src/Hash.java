import java.io.PrintWriter;
import java.text.Normalizer;

public class Hash {
    Arvore[] arvore;

    public Hash() {
        arvore = new Arvore[26];
        for (int i = 0; i < 26; i++) {
            arvore[i] = new Arvore();
        }
    }


    private String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    private int hash(Palavra palavra) {
        String textoSemAcento = removerAcentos(palavra.texto.toLowerCase());
        char primeiraLetra = textoSemAcento.charAt(0);
        return primeiraLetra - 'a';
    }

    public void inserir(Palavra palavra, int linha) {
        int indice = hash(palavra);
        if (indice >= 0 && indice < 26) {
            arvore[indice].inserir(palavra, linha);
        }
    }

    public Palavra buscar(Palavra palavra) {
        int indice = hash(palavra);
        if (indice >= 0 && indice < 26) {
            return arvore[indice].buscar(palavra);
        }
        return null;
    }

    //deu ruim ff
    public void imprimirIndiceCompleto() {
        for (int i = 0; i < 26; i++) {
            arvore[i].imprimirEmOrdem();
        }
    }

    public void imprimeResultado(Palavra resultado, PrintWriter pw) {
        int indice = hash(resultado);
        if (indice >= 0 && indice < 26) {
            arvore[indice].imprimirResultadosOrdenados(pw);
        }
    }

    public boolean contains(Palavra resultado) {
        int indice = hash(resultado);
        if (indice >= 0 && indice < 26) {
            if(arvore[indice].buscar(resultado) == null){
                return false;
            }
        }

        return true;
    }
    public void imprimeOcorrencias(Palavra chave, PrintWriter pw) {
        Palavra encontrada = buscar(chave);
        if (encontrada != null && encontrada.getOcorrencias() != null && !encontrada.getOcorrencias().isEmpty()) {
            pw.println(chave.texto + ": " + encontrada.getOcorrencias());
        }
    }
    public void imprimirPalavrasChaveOrdenadas(PrintWriter pw) {
        for (int i = 0; i < 26; i++) {
            arvore[i].imprimirResultadosOrdenados(pw);
        }
    }

    //imprimeResultado(Palavra resultado) -> deve imprimir no PW 'palavra' : 1, 2, 3. Vai chamar o
    //arvore[indice].algum metodo que retorne no.palavra.getOcorrencias
}

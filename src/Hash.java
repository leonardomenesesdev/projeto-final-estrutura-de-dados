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
    public String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public int hash(Palavra palavra) {
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

}

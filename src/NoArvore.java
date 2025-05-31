public class NoArvore {
    Palavra palavra;
    NoArvore esquerda;
    NoArvore direita;

    public NoArvore(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }
}

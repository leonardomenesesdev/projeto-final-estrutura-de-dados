public class NoArvore {
    Palavra palavra;
    NoArvore esquerda, direita;

    public NoArvore(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }
}

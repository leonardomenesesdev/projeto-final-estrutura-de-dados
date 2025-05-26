public class Hash {
    Arvore[] tabela;
    public Hash() {
        tabela = new Arvore[26];
        for(int i = 0; i<26; i++){
            tabela[i] = new Arvore();
        }
    }
    private int hash(String palavra) {
        return Character.toLowerCase(palavra.charAt(0)) - 'a';
    }
    public void inserir(String palavra, int linha) {
        int indice = hash(palavra);
        if (indice >= 0 && indice < 26) {
            tabela[indice].inserir(palavra, linha);
        }
    }

    // Busca uma palavra (usado ao processar palavras-chave)
    public Palavra buscar(String palavra) {
        int indice = hash(palavra);
        if (indice >= 0 && indice < 26) {
            return tabela[indice].buscar(palavra);
        }
        return null;
    }

    // Imprime to  o índice em ordem alfabética (para depuração ou saída)
    public void imprimirIndiceCompleto() {
        for (int i = 0; i < 26; i++) {
            tabela[i].imprimirEmOrdem();
        }
    }
}

public class Arvore<T> {
    T data; // Dado armazenado no nó
    MinhaLista<Arvore<T>> children; // Lista de filhos do nó

    public Arvore(T data) {
        this.data = data; 
        this.children = new MinhaLista<>(); // Inicializa a lista de filhos
    }
    // Método para adicionar um filho ao nó
    public void addChild(Arvore<T> child) {
        this.children.adicionar(child); // Adiciona o filho à lista de filhos
    }
    // Método para obter um filho com base nos dados fornecidos
    public Arvore<T> getChild(T data) {
        for (int i = 0; i < children.tamanho(); i++) {
            Arvore<T> child = children.get(i); // Obtém o filho atual na posição 'i'
            if (child != null && child.data.equals(data)) { // Verifica se o filho não é nulo e se seus dados correspondem aos fornecidos
                return child; // Retorna o filho encontrado
            }
        }
        return null; // Retorna null se nenhum filho com os dados fornecidos for encontrado
    }
    // Método para imprimir a árvore de forma recursiva
    public void printTree(String prefix) {
        System.out.println(prefix + data); // Imprime o dado do nó com o prefixo fornecido
        for (int i = 0; i < children.tamanho(); i++) {
            Arvore<T> child = children.get(i); // Obtém o filho atual na posição 'i'
            if (child != null) { // Verifica se o filho não é nulo
                child.printTree(prefix + "--"); // Chama recursivamente o método printTree para imprimir a subárvore
            }
        }
    }
}

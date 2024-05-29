// Classe genérica Arvore, implementa uma árvore onde cada nó pode ter vários filhos
public class Arvore<T> {
    T data; // Dado armazenado no nó
    MinhaLista<Arvore<T>> children; // Lista de filhos do nó
    public Arvore(T data) {
        this.data = data;
        this.children = new MinhaLista<>(10); // Inicialmente com capacidade 10, pode ser ajustado conforme necessário
    }
    public void addChild(Arvore<T> child) {
        this.children.adicionar(child);
    }
    public Arvore<T> getChild(T data) {
        for (int i = 0; i < children.tamanho(); i++) {
            Arvore<T> child = children.get(i);
            if (child != null && child.data.equals(data)) {
                return child;
            }
        }
        return null;
    }
    public void printTree(String prefix) {
        System.out.println(prefix + data);
        for (int i = 0; i < children.tamanho(); i++) {
            Arvore<T> child = children.get(i);
            if (child != null) {
                child.printTree(prefix + "--");
            }
        }
    }
}

// Classe genérica MinhaLista, implementa uma lista com capacidade fixa
public class MinhaLista<T> {
    private T[] elementos;
    private int tamanhoAtual = 0;

    @SuppressWarnings("unchecked")
    public MinhaLista(int capacidade) {
        elementos = (T[]) new Object[capacidade];
    }

    public void adicionar(T elemento) {
        if (tamanhoAtual < elementos.length) {
            elementos[tamanhoAtual] = elemento;
            tamanhoAtual++;
        } else {
            System.out.println("Lista cheia, não é possível adicionar mais elementos.");
        }
    }

    public T get(int indice) {
        if (indice >= 0 && indice < tamanhoAtual) {
            return elementos[indice];
        } else {
            return null;
        }
    }

    public int tamanho() {
        return tamanhoAtual;   
    }

    public void removerPorIndice(int indice) {
        if (indice >= 0 && indice < tamanhoAtual) {
            for (int i = indice; i < tamanhoAtual - 1; i++) {
                elementos[i] = elementos[i + 1];
            }
            elementos[tamanhoAtual - 1] = null;
            tamanhoAtual--;
        }
    }

    public void removerPorElemento(T elemento) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                this.removerPorIndice(i);
                return;
            }
        }
    }

    public boolean estaVazia() {
        return tamanhoAtual == 0;
    }
    
    public boolean contem(T elemento) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public void limpar() {
        elementos = (T[]) new Object[elementos.length];
        tamanhoAtual = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tamanhoAtual; i++) {
            sb.append(elementos[i]);
            if (i < tamanhoAtual - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
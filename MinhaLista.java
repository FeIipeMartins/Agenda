public class MinhaLista {
    private String[] elementos;
    private int tamanhoAtual = 0;

    public MinhaLista(int capacidade) {
        elementos = new String[capacidade];
    }

    public void adicionar(String elemento) {
        if (tamanhoAtual < elementos.length) {
            elementos[tamanhoAtual] = elemento;
            tamanhoAtual++;
        } else {
            System.out.println("Lista cheia, não é possível adicionar mais elementos.");
        }
    }

    public String get(int indice) {
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

    public void removerPorElemento(String elemento) {
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
    
    public boolean contem(String elemento) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public void limpar() {
        elementos = new String[elementos.length];
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

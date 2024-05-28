public class Evento {
    String nome;
    String descricao;

    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return nome + ": " + descricao;
    }
}

// Classe para armazenar informações do evento
public class Evento {
    private String nome;
    private String descricao;

    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return nome + ": " + descricao;
    }
}

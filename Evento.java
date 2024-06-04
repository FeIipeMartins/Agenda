// Classe para armazenar informações do evento

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Evento evento = (Evento) obj;
        return nome.equals(evento.nome) && descricao.equals(evento.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }
}


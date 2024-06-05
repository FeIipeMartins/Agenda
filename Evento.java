import java.util.Objects; // Importa a classe Objects para facilitar a implementação de métodos equals e hashCode

// Classe para armazenar informações do evento
public class Evento {
    private String nome; 
    private String descricao;
    // Construtor
    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Retorna uma representação em string do evento
    @Override
    public String toString() {
        return nome + ": " + descricao; 
    }
    
    // Verifica se dois objetos Evento são iguais
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Se os objetos são a mesma instância, são iguais
        if (obj == null || getClass() != obj.getClass()) return false; // Se o objeto é null ou não é da mesma classe, não são iguais
        Evento evento = (Evento) obj; // Converte o objeto para o tipo Evento
        return nome.equals(evento.nome) && descricao.equals(evento.descricao); // Compara os atributos nome e descrição
    }

//Gera um codigo com base nos atributos nome e descricao     		
    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }
}

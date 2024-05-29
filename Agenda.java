// Classe Agenda para gerenciar eventos utilizando a estrutura de árvore
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private Arvore<String> root; // Raiz da árvore, que representa a agenda
    public Agenda() {
        root = new Arvore<>("Agenda"); // Inicializa a árvore com a raiz "Agenda"
    }
    // Adiciona um evento na árvore, estruturando-o por ano, mês e dia
    public void adicionarEvento(int dia, int mes, int ano, Evento evento) {
        if (!dataValida(dia, mes, ano)) {
            System.out.println("Data inválida: " + dia + "/" + mes + "/" + ano);
            return;
        }
        // Verifica se o nó do ano já existe, senão, cria-o
        Arvore<String> anoNode = root.getChild(String.valueOf(ano));
        if (anoNode == null) {
            anoNode = new Arvore<>(String.valueOf(ano));
            root.addChild(anoNode);
        }
        // Verifica se o nó do mês já existe, senão, cria-o
        Arvore<String> mesNode = anoNode.getChild(String.format("%02d", mes));
        if (mesNode == null) {
            mesNode = new Arvore<>(String.format("%02d", mes));
            anoNode.addChild(mesNode);
        }
        // Verifica se o nó do dia já existe, senão, cria-o
        Arvore<String> diaNode = mesNode.getChild(String.format("%02d", dia));
        if (diaNode == null) {
            diaNode = new Arvore<>(String.format("%02d", dia));
            mesNode.addChild(diaNode);
        }
        // Adiciona o evento no nó do dia
        diaNode.addChild(new Arvore<>(evento.toString()));
    }
    // Imprime a estrutura da árvore no console
    public void listarEventos() {
        root.printTree("");
    }
    // Retorna uma lista com todos os eventos na forma de strings contendo o caminho completo
    public List<String> listarTodosEventos() {
        List<String> eventos = new ArrayList<>();
        listarEventosRecursivo(root, "", eventos);
        return eventos;
    }
    // Método recursivo auxiliar para percorrer a árvore e coletar eventos
    private void listarEventosRecursivo(Arvore<String> node, String caminho, List<String> eventos) {
        if (node != null) {
            String novoCaminho = caminho.isEmpty() ? node.data : caminho + " -> " + node.data;
            if (node.children.estaVazia()) {
                eventos.add(novoCaminho); // Adiciona o evento com o caminho completo
            } else {
                for (int i = 0; i < node.children.tamanho(); i++) {
                    Arvore<String> child = node.children.get(i);
                    listarEventosRecursivo(child, novoCaminho, eventos);
                }
            }
        }
    }
    // Valida se a data fornecida é válida considerando anos bissextos
    private boolean dataValida(int dia, int mes, int ano) {
        if (mes < 1 || mes > 12) {
            return false;
        }
        if (dia < 1) {
            return false;
        }
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (anoBissexto(ano)) {
            diasPorMes[1] = 29;
        }
        return dia <= diasPorMes[mes - 1];
    }
    // Verifica se um ano é bissexto
    private boolean anoBissexto(int ano) {
        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
            return true;
        }
        return false;
    }
    
    /*
    // Retorna todas as datas que não têm eventos cadastrados para os anos criados
    public List<String> listarDatasVazias() {
        List<String> datasVazias = new ArrayList<>();
        for (int i = 0; i < root.children.tamanho(); i++) {
            Arvore<String> anoNode = root.children.get(i);
            if (anoNode != null) {
                int ano = Integer.parseInt(anoNode.data);
                for (int mes = 1; mes <= 12; mes++) {
                    Arvore<String> mesNode = anoNode.getChild(String.format("%02d", mes));
                    int maxDia = mes == 2 && anoBissexto(ano) ? 29 : new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}[mes - 1];
                    for (int dia = 1; dia <= maxDia; dia++) {
                        Arvore<String> diaNode = mesNode == null ? null : mesNode.getChild(String.format("%02d", dia));
                        if (diaNode == null) {
                            datasVazias.add(String.format("%02d/%02d/%d", dia, mes, ano));
                        }
                    }
                }
            }
        }
        return datasVazias;
    }
*/
    // Método principal para testes
    public static void main(String[] args) {
    	Agenda agenda = new Agenda();
        agenda.adicionarEvento(31, 12, 2024, new Evento("Meu Niver", "Ano novo"));
        agenda.adicionarEvento(31, 12, 2023, new Evento("Meu Niver", "Ano novo"));
        agenda.adicionarEvento(31, 12, 2022, new Evento("Meu Niver", "Ano novo"));
        agenda.adicionarEvento(29, 2, 2024, new Evento("Dia Bissexto", "Ano bissexto"));
        agenda.adicionarEvento(28, 2, 2023, new Evento("Dia Bissexto", "Ano não bissexto"));
        agenda.listarEventos();
        List<String> todosEventos = agenda.listarTodosEventos();
        System.out.println("\nTodos os eventos:");
        for (String evento : todosEventos) {
            System.out.println(evento);
        }
        /*
        List<String> datasVazias = agenda.listarDatasVazias();
        System.out.println("\nDatas vazias:");
        for (String data : datasVazias) {
            System.out.println(data);
        }
        */
    }
}
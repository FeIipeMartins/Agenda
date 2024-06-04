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
    
    public void removerEvento(int dia, int mes, int ano, Evento evento) {
        Arvore<String> anoNode = root.getChild(String.valueOf(ano));
        if (anoNode != null) {
            Arvore<String> mesNode = anoNode.getChild(String.format("%02d", mes));
            if (mesNode != null) {
                Arvore<String> diaNode = mesNode.getChild(String.format("%02d", dia));
                if (diaNode != null) {
                    for (int i = 0; i < diaNode.children.tamanho(); i++) {
                        Arvore<String> eventoNode = diaNode.children.get(i);
                        if (eventoNode != null && eventoNode.data.equals(evento.toString())) {
                            diaNode.children.removerPorIndice(i);
                            break;
                        }
                    }
                }
            }
        }
    }

    public List<String> buscarEventos(int dia, int mes, int ano) {
        List<String> eventos = new ArrayList<>();
        Arvore<String> anoNode = root.getChild(String.valueOf(ano));
        if (anoNode != null) {
            Arvore<String> mesNode = anoNode.getChild(String.format("%02d", mes));
            if (mesNode != null) {
                Arvore<String> diaNode = mesNode.getChild(String.format("%02d", dia));
                if (diaNode != null) {
                    for (int i = 0; i < diaNode.children.tamanho(); i++) {
                        Arvore<String> eventoNode = diaNode.children.get(i);
                        if (eventoNode != null) {
                            eventos.add(eventoNode.data);
                        }
                    }
                }
            }
        }
        return eventos;
    }

    public int contarEventosAno(int ano) {
        int count = 0;
        Arvore<String> anoNode = root.getChild(String.valueOf(ano));
        if (anoNode != null) {
            for (int i = 0; i < anoNode.children.tamanho(); i++) {
                Arvore<String> mesNode = anoNode.children.get(i);
                if (mesNode != null) {
                    for (int j = 0; j < mesNode.children.tamanho(); j++) {
                        Arvore<String> diaNode = mesNode.children.get(j);
                        if (diaNode != null) {
                            count += diaNode.children.tamanho();
                        }
                    }
                }
            }
        }
        return count;
    }

    
public boolean anoBissesto(int ano) {
    if (ano % 4 == 0) {
        if (ano % 100 == 0) {
            return ano % 400 == 0;
        } else {
            return true;
        }
    } else {
        return false;
    }
}

public void editarEvento(int dia, int mes, int ano, Evento eventoAntigo, Evento eventoNovo) {
    Arvore<String> anoNode = root.getChild(String.valueOf(ano));
    if (anoNode != null) {
        Arvore<String> mesNode = anoNode.getChild(String.format("%02d", mes));
        if (mesNode != null) {
            Arvore<String> diaNode = mesNode.getChild(String.format("%02d", dia));
            if (diaNode != null) {
                for (int i = 0; i < diaNode.children.tamanho(); i++) {
                    Arvore<String> eventoNode = diaNode.children.get(i);
                    if (eventoNode != null && eventoNode.data.equals(eventoAntigo.toString())) {
                        eventoNode.data = eventoNovo.toString();
                        break;
                    }
                }
            }
        }
    }
}


public void limparAgenda() {
    root = new Arvore<>("Agenda");
}

    

    
    // Método principal para testes
        public static void main(String[] args) {
        	
            // Criação da agenda
            Agenda agenda = new Agenda();
            
            // Adição de eventos
            agenda.adicionarEvento(31, 12, 2024, new Evento("Meu Niver", "Ano novo"));

            // Listagem de eventos
            System.out.println("Estrutura da Agenda:");
            agenda.listarEventos(); 

            // Listar todos os eventos com o caminho completo
            List<String> todosEventos = agenda.listarTodosEventos();
            System.out.println("\nTodos os eventos:");
            for (String evento : todosEventos) {
                System.out.println(evento);
            }
            
            // Buscar eventos específicos
            System.out.println("\nEventos em 31/12/2024:");
            List<String> eventosEncontrados = agenda.buscarEventos(31, 12, 2024);
            for (String evento : eventosEncontrados) {
                System.out.println(evento);
            }
           
            
            // Ate aki SHOW
            
             
            // Editar evento
            System.out.println("\nEditando evento 'Meu Niver' de 31/12/2024:");
            agenda.editarEvento(31, 12, 2024, new Evento("Meu Niver", "Ano novo"), new Evento("Aniversário", "Celebração de aniversário"));
            agenda.listarEventos();
        
            // Remover evento
            System.out.println("\nRemovendo evento 'Meu Niver'");
            agenda.removerEvento(31, 12, 2024, new Evento("Meu Niver", "Ano novo"));
            agenda.listarEventos();

            
            // Contar eventos em um ano específico
            int count2024 = agenda.contarEventosAno(2024);
            System.out.println("\nNúmero de eventos em 2024: " + count2024);

            
            // Limpar agenda
            System.out.println("\nLimpando a agenda:");
            agenda.limparAgenda();
            agenda.listarEventos();
        }
    }

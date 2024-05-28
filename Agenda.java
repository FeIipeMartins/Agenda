public class Agenda {
    private Arvore<String> root;
    public Agenda() {
        root = new Arvore<>("Agenda");
    }
    public void adicionarEvento(int dia, int mes, int ano, Evento evento) {
        Arvore<String> diaNode = root.getChild(String.format("%02d", dia));
        if (diaNode == null) {
            diaNode = new Arvore<>(String.format("%02d", dia));
            root.addChild(diaNode);
        }
        Arvore<String> mesNode = diaNode.getChild(String.format("%02d", mes));
        if (mesNode == null) {
            mesNode = new Arvore<>(String.format("%02d", mes));
            diaNode.addChild(mesNode);
        }
        Arvore<String> anoNode = mesNode.getChild(String.valueOf(ano));
        if (anoNode == null) {
            anoNode = new Arvore<>(String.valueOf(ano));
            mesNode.addChild(anoNode);
        }
        anoNode.addChild(new Arvore<>(evento.toString()));
    }
    public void listarEventos() {
        root.printTree("");
    }
    public static void main(String[] args) {
        Agenda agenda = new Agenda();        
        agenda.adicionarEvento(31, 12, 2024, new Evento("Meu Niver", "Ano novo"));
        agenda.adicionarEvento(31, 12, 2023, new Evento("Meu Niver", "Ano novo"));
        agenda.adicionarEvento(31, 12, 2022, new Evento("Meu Niver", "Ano novo"));
        agenda.listarEventos();
    }
}

package atendimentobancario;

import TADS.Fila;
import atendimentobancario.model.Guiche;
import atendimentobancario.model.Cliente;
import java.util.Random;

public class Main {

    private static Fila fila = fila = new Fila();
    private static Guiche[] guiches = {new Guiche(1), new Guiche(2), new Guiche(3)};
    private static int numDepositos = 0;
    private static int numSaques = 0;
    private static int numPagamentos = 0;
    private static int tempoRestanteGuiches = 0;
    private static int tempoTotalCliente = 0;
    private static int expediente = 21600;
    
    public static void main(String[] args) throws InterruptedException {

        var temGuicheOcupado = false;
        boolean filaDesocupada = true;
        
        //Caso o expediente não tenha acabado ou tenha alguém sendo atendido ou tenha alguém na fila, todo o fluxo continuará rodando, pois não pode-se fechar a empresa com clientes dentro.
        while (expediente >= 0  || temGuicheOcupado || !filaDesocupada) {
            System.out.println("Tempo de expediente: " + gerarHorarioPorSegs(expediente--));
            System.out.println("Clientes na fila: " + fila.length);
            tempoRestanteGuiches = 0;
            
            Guiche guicheVazio = null;
            
            for (Guiche guiche : guiches) {     
                //Se o guiche em questão está ocupado, não faz nada, apenas passa 1 segundo e verifica se algum cliente chegou.
                if (guiche.ocupado()) {
                    guiche.removerTempoOcupacao(1);
                    continue;
                }
                //Caso o guichê esteja disponível, haverá a possibilidade de um cliente ir até ele fazer uma operação.
                guicheVazio = guiche;
            }

            //A cada segundo, ocorre um sorteio de 0 a 29 e se cair no zero, um cliente entra na fila.
            if (expediente >= 0) {
                if (sorteioCliente()) {
                    Cliente cliente = new Cliente(expediente);
                    fila.enqueue(cliente);
                    System.out.println("Chegou um novo cliente.");
                }
            }
            
            //Se a tiver alguém na fila e um guichê vazio, o cliente vai até o guichê.
            filaDesocupada = fila.isEmpty();
            if (guicheVazio != null && !filaDesocupada) {
                adicionarClienteGuiche(guicheVazio);
            }

            //Verifica se há algum guiche disponível a partir do tempo de atendimento restante em cada um.
            for(var guiche : guiches){
                tempoRestanteGuiches += guiche.tempoRestante();
            }
                
            temGuicheOcupado = tempoRestanteGuiches > 0 ? true : false;
        }

        System.out.println("Expediente encerrado.");

        //Dados do dia obtidos:
        var mediaTempoClientes = tempoTotalCliente / (numSaques + numDepositos + numPagamentos);
        var clientesAtendidos = numSaques + numDepositos + numPagamentos;
        System.out.println("O tempo médio de espera dos clientes foi de: " + mediaTempoClientes + " segundos");
        System.out.println(clientesAtendidos + " clientes foram atendidos.");
        System.out.println(numSaques + " clientes fizeram saque, " + numDepositos + ", clientes fizeram depositos e " + numPagamentos + " clientes fizeram pagamentos");
        if (expediente < 0) {
            System.out.println("Tempo extra de expediente: " + expediente * -1 + " segundos");
        } else {
            System.out.println("Não houve tempo extra de expediente");
        }
    }
    public static boolean sorteioCliente() {
        Random random = new Random(); return random.nextInt(30) == 0;
    }
    public static void adicionarClienteGuiche(Guiche guiche) {
        var numeroOperacao = gerarNumeroOperacao();
        adicionarTempoOcupacao(guiche, numeroOperacao);

        Cliente cliente = fila.dequeue();
        tempoTotalCliente += cliente.getId() - expediente;
        System.out.println("O cliente chegou as " + gerarHorarioPorSegs(cliente.getId()) + "e foi atendido no gichê " + guiche.getNumero());
    }


    
    public static String gerarHorarioPorSegs(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segundosRestantes = segundos % 60;

        if (segundos < 0) {
            segundosRestantes = segundosRestantes * -1;
            minutos = minutos * -1;
            horas = horas * -1;
        }
        String tempo = String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);

        var string = "";
        
        if(segundos < 0)
            string = "Hora extra: "+ tempo;
        else
            string = tempo;
        
        return string;
    }
    
    public static int gerarNumeroOperacao() {
        Random random = new Random(); return random.nextInt(3);
    }

    public static void adicionarTempoOcupacao(Guiche guiche, int transacao) {
        if (transacao == 0) {
            System.out.println("O cliente realizará um saque. Ocupação: 60 segundos");
            guiche.setTempoOcupacao(60);
            numSaques++;
        } else if (transacao == 1) {
            System.out.println("O cliente realizará um depósito. Ocupação: 90 segundos");
            guiche.setTempoOcupacao(90);
            numDepositos++;
        } else if (transacao == 2) {
            System.out.println("O cliente realizará um pagamento. Ocupação: 120 segundos");
            guiche.setTempoOcupacao(120);
            numPagamentos++;
        }
    }
}

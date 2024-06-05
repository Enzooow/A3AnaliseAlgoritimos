package atendimentobancario.model;

import atendimentobancario.Main;

public class Guiche {
    private int numero;
    private int tempoOcupacao;
    private int contadorSaques;
    private int contadorPagamentos;
    private int contadorDepositos;

    public Guiche(int numero) {
        this.numero = numero;
        tempoOcupacao = 0;
        contadorSaques = 0;
        contadorDepositos = 0;
    }
    
    public boolean estaOcupado() {
        return tempoOcupacao > 0;
    }
    
    public int tempoRestante() {
        return tempoOcupacao;
    }
    
    public void adicionarTempoOcupacao(int transacao) {
        switch(transacao) {
            case 0:
                System.out.println("O cliente realizará um saque. Ocupação: 60 segundos" );
                tempoOcupacao += 60;
                contadorSaques++;
                break;
            case 1:
                System.out.println("O cliente realizará um depósito. Ocupação: 90 segundos" );
                tempoOcupacao += 90;
                contadorDepositos++;
                break;
            case 2:
                System.out.println("O cliente realizará um pagamento. Ocupação: 120 segundos" );
                tempoOcupacao += 120;
                contadorPagamentos++;
                break;
        }
    }
    
    public void removerTempoOcupacao(int tempo) {
        tempoOcupacao--;
    }

    public int getNumero() {
        return numero;
    }
    
}

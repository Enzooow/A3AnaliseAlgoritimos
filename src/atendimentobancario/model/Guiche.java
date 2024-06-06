package atendimentobancario.model;

import atendimentobancario.Main;

public class Guiche {
    private int numero;
    private int tempoOcupacao;

    public Guiche(int numero) {
        this.numero = numero;
        tempoOcupacao = 0;
    }
    
    public boolean ocupado() {
        return tempoOcupacao > 0;
    }
    
    public int tempoRestante() {
        return tempoOcupacao;
    }
    
    public void removerTempoOcupacao(int tempo) {
        tempoOcupacao--;
    }

    public int getNumero() {
        return numero;
    }
    
    public void setTempoOcupacao(int segs){
        this.tempoOcupacao += segs;
    }
    
}

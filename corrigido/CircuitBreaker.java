package corrigido;

import java.util.ArrayList;
import java.util.List;

public class CircuitBreaker {

    private EstadoCircuito estado;
    private int contadorFalhas;
    private int limiteParaAbrir;
    private List<ObservadorVoo> observadores;

    public CircuitBreaker(int limiteParaAbrir) {
        this.estado          = EstadoCircuito.FECHADO;
        this.contadorFalhas  = 0;
        this.limiteParaAbrir = limiteParaAbrir;
        this.observadores    = new ArrayList<ObservadorVoo>();
    }

    public void adicionarObservador(ObservadorVoo obs) {
        observadores.add(obs);
    }

    private void notificarTodos(String evento, String detalhe) {
        for (ObservadorVoo obs : observadores) {
            obs.notificar(evento, detalhe);
        }
    }

    public boolean permitePassagem() {
        return estado == EstadoCircuito.FECHADO || estado == EstadoCircuito.SEMI_ABERTO;
    }

    public void registrarFalha(String motivo) {
        contadorFalhas++;
        notificarTodos("FALHA_SRI", "Falha #" + contadorFalhas + " | " + motivo);

        if (contadorFalhas >= limiteParaAbrir && estado == EstadoCircuito.FECHADO) {
            estado = EstadoCircuito.ABERTO;
            notificarTodos("CIRCUITO_ABERTO", "Dados do SRI bloqueados. Modo contingência ativado.");
        }
    }

    public void registrarSucesso() {
        if (estado == EstadoCircuito.SEMI_ABERTO) {
            estado = EstadoCircuito.FECHADO;
            contadorFalhas = 0;
            notificarTodos("CIRCUITO_FECHADO", "Componente recuperado. Operação normal retomada.");
        }
    }

    public EstadoCircuito getEstado() {
        return estado;
    }

    public int getContadorFalhas() {
        return contadorFalhas;
    }
}


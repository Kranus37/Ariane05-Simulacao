package corrigido;

public class OBCCorrigido {

    private CircuitBreaker circuitBreaker;
    private short ultimaVelocidadeValida;
    private boolean temDadoValido;
    private String estado;

    public OBCCorrigido(CircuitBreaker circuitBreaker) {
        this.circuitBreaker      = circuitBreaker;
        this.ultimaVelocidadeValida = 0;
        this.temDadoValido       = false;
        this.estado              = "OPERACIONAL";
    }

    public void processarDados(ResultadoSRI resultado) {

        if (!circuitBreaker.permitePassagem()) {
            System.out.println("[OBC] Circuit Breaker ABERTO - bloqueando dados de " + resultado.getOrigemSRI());
            ativarContingencia();
            return;
        }

        if (!resultado.isDadoValido()) {
            System.out.println("[OBC] Dado de " + resultado.getOrigemSRI() + " marcado como INVÁLIDO. Descartando.");
            circuitBreaker.registrarFalha("Dado fora do range recebido de " + resultado.getOrigemSRI());
            return;
        }

        circuitBreaker.registrarSucesso();
        this.ultimaVelocidadeValida = resultado.getVelocidade();
        this.temDadoValido = true;
        this.estado = "OPERACIONAL";

        System.out.println("[OBC] Dado VÁLIDO recebido de " + resultado.getOrigemSRI()
                + " -> vel_h = " + resultado.getVelocidade());
        System.out.println("[OBC] Calculando trajetória. Estado: " + estado);
        System.out.println("[OBC] Comandando atuadores com ângulo seguro.");
    }

    private void ativarContingencia() {
        this.estado = "CONTINGENCIA";
        if (temDadoValido) {
            System.out.println("[OBC] Modo CONTINGENCIA ativado. Usando último dado válido: "
                    + ultimaVelocidadeValida);
            System.out.println("[OBC] Mantendo trajetória estável. Aguardando recuperação do SRI.");
        } else {

            System.out.println("[OBC] Modo CONTINGENCIA ativado. Sem dados válidos anteriores.");
            System.out.println("[OBC] Executando protocolo de segurança: manter ângulo neutro.");
        }
    }

    public String getEstado() {
        return estado;
    }
}


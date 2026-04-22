package corrigido;

public class OBCCorrigido {

    private CircuitBreaker circuitBreaker;
    private short ultimaVelocidadeValida;
    private boolean temDadoValido;
    private String estado;

    public OBCCorrigido(CircuitBreaker cb) {
        this.circuitBreaker = cb;
        this.ultimaVelocidadeValida = 0;
        this.temDadoValido = false;
        this.estado = "OPERACIONAL";
    }

    public void processarDados(ResultadoSRI resultado) {

        if (!circuitBreaker.permitePassagem()) {
            System.out.println("[OBC] Circuito ABERTO. Ignorando " + resultado.getOrigemSRI());
            ativarContingencia();
            return;
        }

        if (!resultado.isDadoValido()) {
            System.out.println("[OBC] Dado invalido recebido. Descartando.");
            circuitBreaker.registrarFalha("Dado fora do limite");
            return;
        }

        circuitBreaker.registrarSucesso();
        this.ultimaVelocidadeValida = resultado.getVelocidade();
        this.temDadoValido = true;
        this.estado = "OPERACIONAL";

        System.out.println("[OBC] OK. Velocidade atualizada: " + this.ultimaVelocidadeValida);
    }

    private void ativarContingencia() {
        this.estado = "CONTINGENCIA";
        if (temDadoValido) {
            System.out.println("[OBC] Usando ultima velocidade valida: " + ultimaVelocidadeValida);
        } else {
            System.out.println("[OBC] Sem dado anterior. Mantendo angulo neutro.");
        }
    }

    public String getEstado() {
        return estado;
    }
}
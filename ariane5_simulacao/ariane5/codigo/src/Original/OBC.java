package original;

public class OBC {

    private String estado;

    public OBC() {
        this.estado = "OPERACIONAL";
    }

    public void receberDados(short velocidade, String origem) {
        System.out.println("OBC recebeu: " + velocidade + " de " + origem);

        if (velocidade < -200) {
            System.out.println("ALERTA: OBC detectou atitude critica!");
            System.out.println("Aplicando correcao extrema (deflexao 28 graus)");
            this.estado = "CORRECAO_FALHA";

            System.out.println("Falha estrutural. Autodestruicao ativada.");
        } else {
            System.out.println("Processando normal...");
        }
    }

    public String getEstado() {
        return estado;
    }
}
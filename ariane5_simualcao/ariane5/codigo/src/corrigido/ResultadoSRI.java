package corrigido;

public class ResultadoSRI {

    private short velocidade;
    private boolean dadoValido;
    private String origemSRI;

    public ResultadoSRI(short velocidade, boolean dadoValido, String origemSRI) {
        this.velocidade  = velocidade;
        this.dadoValido  = dadoValido;
        this.origemSRI   = origemSRI;
    }

    public short getVelocidade() {
        return velocidade;
    }

    public boolean isDadoValido() {
        return dadoValido;
    }

    public String getOrigemSRI() {
        return origemSRI;
    }

    @Override
    public String toString() {
        return "ResultadoSRI{vel=" + velocidade + ", valido=" + dadoValido + ", origem=" + origemSRI + "}";
    }
}


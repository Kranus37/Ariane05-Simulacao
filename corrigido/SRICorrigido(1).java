package corrigido;

public class SRICorrigido {

    private String nome;
    private boolean backup;
    private EstrategiaConversao estrategia;

    public SRICorrigido(String nome, boolean backup, EstrategiaConversao estrategia) {
        this.nome       = nome;
        this.backup     = backup;
        this.estrategia = estrategia;
    }

    public ResultadoSRI calcularDados(double velocidadeReal) {
        System.out.println("  [" + nome + "] Calculando dados. Velocidade real: " + velocidadeReal);

        boolean valorOk = estrategia.eValorSeguro(velocidadeReal);
        short velocidadeConvertida = estrategia.converter(velocidadeReal);

        if (!valorOk) {
            System.out.println("  [" + nome + "] AVISO: valor fora do range seguro. Dado marcado como inválido.");
        }

        return new ResultadoSRI(velocidadeConvertida, valorOk, nome);
    }

    public String getNome() {
        return nome;
    }

    public boolean isBackup() {
        return backup;
    }
}


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

    public ResultadoSRI calcularDados(double velReal) {
        System.out.println("  [" + nome + "] Lendo velocidade: " + velReal);

        boolean valorOk = estrategia.eValorSeguro(velReal);
        short velConvertida = estrategia.converter(velReal);

        if (!valorOk) {
            System.out.println("  [" + nome + "] ERRO: Valor fora da margem. Marcando como invalido.");
        }

        return new ResultadoSRI(velConvertida, valorOk, nome);
    }

    public String getNome() {
        return nome;
    }

    public boolean isBackup() {
        return backup;
    }
}
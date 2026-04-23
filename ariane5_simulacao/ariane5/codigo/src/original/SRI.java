package original;

public class SRI {

    private String nome;
    private boolean isBackup;

    public SRI(String nome, boolean isBackup) {
        this.nome = nome;
        this.isBackup = isBackup;
    }

    public short getVelocidade(double velocidade) {
        return (short) velocidade;
    }

    public String getNome() {
        return nome;
    }

    public boolean isBackup() {
        return isBackup;
    }
}
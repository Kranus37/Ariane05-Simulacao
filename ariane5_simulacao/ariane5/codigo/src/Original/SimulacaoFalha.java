package original;

public class SimulacaoFalha {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(" Teste do Voo 501 (Com Erro) ");

        double velHorizontal = 32768.5; // O valor que quebrou tudo

        SRI primario = new SRI("SRI-Primario", false);
        SRI backup = new SRI("SRI-Backup", true);
        OBC obc = new OBC();

        System.out.println("Lancamento ok...");
        Thread.sleep(600);

        System.out.println("Lendo dados de telemetria...");
        Thread.sleep(600);

        System.out.println("Velocidade atual: " + velHorizontal);

        short resPrimario = primario.getVelocidade(velHorizontal);

        System.out.println("Resultado conversao primario: " + resPrimario);
        System.out.println("Erro: Operand Overflow no primario");
        Thread.sleep(500);

        System.out.println("\nAcionando backup...");
        short resBackup = backup.getVelocidade(velHorizontal);

        System.out.println("Resultado conversao backup: " + resBackup);
        System.out.println("Erro: Backup tambem deu overflow");
        Thread.sleep(500);

        System.out.println("\nEnviando lixo de memoria pro OBC...");
        obc.receberDados(resBackup, backup.getNome());

        System.out.println("\nFim do teste. Foguete destruido.");
    }
}
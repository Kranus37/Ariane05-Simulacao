package corrigido;

public class SimulacaoCorrigida {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(" Início da Simulação Ariane 5 (Corrigida) - Grupo 4 ");

        EstacaoTerrestre estacao = new EstacaoTerrestre();
        MonitorSeguranca monitor = new MonitorSeguranca();

        CircuitBreaker cb = new CircuitBreaker(1);
        cb.adicionarObservador(estacao);
        cb.adicionarObservador(monitor);

        ConversaoSegura strategyConversao = new ConversaoSegura();
        SRICorrigido sriA = new SRICorrigido("SRI-A (Primario)", false, strategyConversao);
        SRICorrigido sriB = new SRICorrigido("SRI-B (Backup)", true, strategyConversao);

        OBCCorrigido obc = new OBCCorrigido(cb);

        double velHorizontal = 32768.5; // valor que causou o overflow no original

        System.out.println("Lancamento iniciado...");
        Thread.sleep(500);

        System.out.println("Enviando dados do SRI Primario...");
        ResultadoSRI resA = sriA.calcularDados(velHorizontal);
        obc.processarDados(resA);

        System.out.println("\nStatus Circuit Breaker: " + cb.getEstado());
        System.out.println("Falhas: " + cb.getContadorFalhas());

        Thread.sleep(500);

        System.out.println("\nEnviando dados do SRI Backup...");
        ResultadoSRI resB = sriB.calcularDados(velHorizontal);
        obc.processarDados(resB);

        System.out.println("\n Resumo Final ");
        System.out.println("Estado OBC: " + obc.getEstado());
        System.out.println("Circuit Breaker final: " + cb.getEstado());
        System.out.println("Foguete salvo. Operando em contingência.");
    }
}
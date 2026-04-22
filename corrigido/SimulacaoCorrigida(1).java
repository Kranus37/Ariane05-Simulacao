package corrigido;

public class SimulacaoCorrigida {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("============================================================");
        System.out.println("   SIMULAÇÃO - ARIANE 5 ARQUITETURA 2026                   ");
        System.out.println("   Circuit Breaker + Strategy + Observer em ação            ");
        System.out.println("============================================================");
        System.out.println();

        EstacaoTerrestre estacao = new EstacaoTerrestre();
        MonitorSeguranca monitor = new MonitorSeguranca();

        CircuitBreaker cb = new CircuitBreaker(1);
        cb.adicionarObservador(estacao);
        cb.adicionarObservador(monitor);

        ConversaoSegura conversaoSegura = new ConversaoSegura();
        SRICorrigido sriA = new SRICorrigido("SRI-A (Primario)", false, conversaoSegura);
        SRICorrigido sriB = new SRICorrigido("SRI-B (Backup)",   true,  conversaoSegura);

        OBCCorrigido obc = new OBCCorrigido(cb);

        double velocidadeHorizontal = 32768.5;

        System.out.println("[T+00s] Lançamento! Foguete em ascensão.");
        Thread.sleep(500);

        System.out.println("[T+10s] Dados fluindo normalmente.");
        Thread.sleep(500);

        System.out.println("[T+37s] Velocidade horizontal: " + velocidadeHorizontal + " m/s");
        System.out.println("[T+37s] SRI-A calculando dados...");
        System.out.println();
        Thread.sleep(400);

        ResultadoSRI resultadoA = sriA.calcularDados(velocidadeHorizontal);
        System.out.println();

        System.out.println("[T+37s] OBC processando dado do SRI-A...");
        obc.processarDados(resultadoA);
        System.out.println();
        Thread.sleep(500);

        System.out.println("[T+37s] Estado do Circuit Breaker: " + cb.getEstado());
        System.out.println("[T+37s] Falhas registradas: " + cb.getContadorFalhas());
        System.out.println("[T+37s] Alerta ativo no Monitor de Segurança: " + monitor.isAlertaAtivado());
        System.out.println();
        Thread.sleep(500);

        System.out.println("[T+37s+72ms] SRI-B (backup) ativado, calculando dados...");
        System.out.println();
        Thread.sleep(400);

        ResultadoSRI resultadoB = sriB.calcularDados(velocidadeHorizontal);
        System.out.println();

        System.out.println("[T+37s+72ms] OBC processando dado do SRI-B...");
        obc.processarDados(resultadoB);
        System.out.println();
        Thread.sleep(500);

        System.out.println("============================================================");
        System.out.println("  Estado final do OBC: " + obc.getEstado());
        System.out.println("  Estado do Circuit Breaker: " + cb.getEstado());
        System.out.println();
        System.out.println("  Nenhuma correção incorreta foi aplicada.");
        System.out.println("  Foguete operando em modo de contingência (trajetória estável).");
        System.out.println("  Estação Terrestre notificada da falha em tempo real.");
        System.out.println();
        System.out.println("  Resultado: foguete SALVO. Missão pode ser avaliada para");
        System.out.println("  procedimento de recuperação ou abort seguro.");
        System.out.println("============================================================");
    }
}


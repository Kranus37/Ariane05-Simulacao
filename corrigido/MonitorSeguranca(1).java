package corrigido;

public class MonitorSeguranca implements ObservadorVoo {

    private boolean alertaAtivado;

    public MonitorSeguranca() {
        this.alertaAtivado = false;
    }

    @Override
    public void notificar(String evento, String detalhe) {

        if (evento.startsWith("FALHA") || evento.startsWith("CIRCUITO")) {
            this.alertaAtivado = true;
            System.out.println("  [MONITOR SEGURANCA] ⚠ ALERTA CRITICO: " + evento + " -> " + detalhe);
            System.out.println("  [MONITOR SEGURANCA] Protocolo de contingência ativado.");
        } else {
            System.out.println("  [MONITOR SEGURANCA] Status OK: " + evento);
        }
    }

    public boolean isAlertaAtivado() {
        return alertaAtivado;
    }
}


package corrigido;

public class EstacaoTerrestre implements ObservadorVoo {

    @Override
    public void notificar(String evento, String detalhe) {

        System.out.println("  [ESTACAO TERRESTRE] Evento recebido: " + evento + " | " + detalhe);
    }
}


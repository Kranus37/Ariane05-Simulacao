package corrigido;

public class ConversaoSegura implements EstrategiaConversao {

    private static final double MAXIMO_SHORT = 32767.0;
    private static final double MINIMO_SHORT = -32768.0;

    @Override
    public short converter(double valor) {

        if (valor > MAXIMO_SHORT) {
            System.out.println("    [ConversaoSegura] Valor " + valor + " acima do limite. Aplicando clamping -> " + (short) MAXIMO_SHORT);
            return (short) MAXIMO_SHORT;
        }
        if (valor < MINIMO_SHORT) {
            System.out.println("    [ConversaoSegura] Valor " + valor + " abaixo do mínimo. Aplicando clamping -> " + (short) MINIMO_SHORT);
            return (short) MINIMO_SHORT;
        }

        return (short) valor;
    }

    @Override
    public boolean eValorSeguro(double valor) {

        return valor >= MINIMO_SHORT && valor <= MAXIMO_SHORT;
    }
}


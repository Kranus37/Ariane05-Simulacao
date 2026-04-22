package corrigido;

public class ConversaoSegura implements EstrategiaConversao {

    private static final double MAX_SHORT = 32767.0;
    private static final double MIN_SHORT = -32768.0;

    @Override
    public short converter(double valor) {
        // Clamping para evitar o overflow maldito (vulgo Nine) do voo 501
        if (valor > MAX_SHORT) {
            System.out.println("  [Aviso] Valor alto demais. Cortando para o teto do short.");
            return (short) MAX_SHORT;
        }
        if (valor < MIN_SHORT) {
            System.out.println("  [Aviso] Valor baixo demais. Cortando para o piso do short.");
            return (short) MIN_SHORT;
        }

        return (short) valor;
    }

    @Override
    public boolean eValorSeguro(double valor) {
        return valor >= MIN_SHORT && valor <= MAX_SHORT;
    }
}
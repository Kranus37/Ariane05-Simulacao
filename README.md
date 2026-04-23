# Ariane 5 - Simulação Arquitetural

Simulação desenvolvida como parte da N1 da disciplina de **Arquitetura de Software** (Prof. Jefferson Salomão Rodrigues), Engenharia de Software, 5º Semestre.

**Grupo 4:** André Costa França, Flávio Araújo Leal, Gabriel Pinheiro Paiva, Geovanna Candido Miro da Silva, Samuel Telles de Vasconcellos Resende.

---

## O que essa simulação faz

O código está dividido em dois módulos:

- **`original/`**: simula o comportamento real do Ariane 5 no Voo 501 de 1996, mostrando o overflow de inteiro e a injeção do dump de erro no barramento de controle.

- **`corrigido/`**: simula a arquitetura proposta pelo grupo para 2026, com os padrões **Circuit Breaker**, **Strategy** e **Observer** aplicados para evitar o mesmo tipo de falha.

---

## Como compilar e executar

Pré-requisito: Java 11 ou superior instalado.

A partir do diretório `codigo/`, execute:

```bash
# Criar diretório de saída
mkdir -p out

# Compilar o módulo original
javac -d out src/original/*.java

# Compilar o módulo corrigido
javac -d out src/corrigido/*.java

# Executar a simulação da falha original
java -cp out original.SimulacaoFalha

# Executar a simulação da arquitetura corrigida
java -cp out corrigido.SimulacaoCorrigida
```

---

## Design Patterns aplicados

### Circuit Breaker
Localizado em `CircuitBreaker.java`. Funciona como um disjuntor: quando o número de falhas ultrapassa o limite configurado, ele abre e impede que dados inválidos cheguem ao OBC. O OBC então usa o último estado válido conhecido. Resolve o problema central do Ariane 5: a injeção do dump de erro no barramento de controle.

### Strategy
A interface `EstrategiaConversao` e sua implementação `ConversaoSegura` separam a lógica de conversão do SRI. Em vez da conversão direta que causou o overflow, aplica clamping e sinaliza se o valor estava fora do range antes da conversão.

### Observer
A interface `ObservadorVoo` permite que qualquer componente seja notificado sobre eventos do `CircuitBreaker`. A `EstacaoTerrestre` e o `MonitorSeguranca` são os observadores que recebem alertas em tempo real quando falhas acontecem.

---

## Por que esse código é uma simulação

Os dados de voo são mockados (velocidade fixa de 32768.5 m/s). O código não tem integração com hardware real nem com sistemas de tempo real. O foco é mostrar a estrutura arquitetural e como os padrões se relacionam, não reproduzir as condições exatas de voo.

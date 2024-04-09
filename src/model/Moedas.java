package model;

public class Moedas {
    String moedaOrigem;
    String moedaDestino;

    public Moedas(String moedaOrigem, String moedaDestino) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
    }

    public double converteMoeda(double valorParaConverter, double cotacaoMoeda) {
        var valorConvertido = valorParaConverter * cotacaoMoeda;
        System.out.printf("O valor convertido de %.2f para %.2f é de %.2f", valorParaConverter, cotacaoMoeda, valorConvertido);
        return valorConvertido;
    }

    @Override
    public String toString() {
        return moedaOrigem + "_" + moedaDestino;
    }
}

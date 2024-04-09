import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.ConsultaApi;
import model.Moedas;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitor = new Scanner(System.in);

        System.out.println("Digite a moeda de origem (ex: USD):");
        String moedaOrigem = leitor.nextLine();
        System.out.println("Digite a moeda de destino (ex: BRL):");
        String moedaDestino = leitor.nextLine();


        Moedas moedas = new Moedas(moedaOrigem, moedaDestino);
        ConsultaApi consultaApi = new ConsultaApi(moedas);
        String json = consultaApi.consultaDados();

        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);


        System.out.println("Digite o valor para converter:");
        var valorParaConverter = leitor.nextDouble();
        var cotacaoMoeda = jsonObject.getAsJsonObject(moedas.toString()).get("price").getAsDouble();
        moedas.converteMoeda(valorParaConverter, cotacaoMoeda);

        leitor.close();
    }
}

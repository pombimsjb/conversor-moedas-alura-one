import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Moedas;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitor = new Scanner(System.in);

        System.out.println("Digite a moeda de origem (ex: USD):");
        String moedaOrigem = leitor.nextLine();
        System.out.println("Digite a moeda de destino (ex: BRL):");
        String moedaDestino = leitor.nextLine();

        Moedas moedas = new Moedas(moedaOrigem, moedaDestino);
        var token = "SEU_TOKEN";
        String tokenCodificado = URLEncoder.encode(token, "UTF-8");

        var linkApi = "https://api.invertexto.com/v1/currency/" + moedas + "?token=" + tokenCodificado;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(linkApi))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);


        System.out.println("Digite o valor para converter:");
        var valorParaConverter = leitor.nextDouble();
        var cotacaoMoeda = jsonObject.getAsJsonObject(moedas.toString()).get("price").getAsDouble();
        moedas.converteMoeda(valorParaConverter, cotacaoMoeda);

        leitor.close();
    }
}

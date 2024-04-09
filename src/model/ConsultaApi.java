package model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsultaApi {
    private String token = "7298|PfcisrVCYb0OX1AYOB9pF46Wc4uGQN2S";
    private String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);
    private Moedas moedas;

    public ConsultaApi(Moedas moedas) {
        this.moedas = moedas;
    }

    public Moedas getMoedas() {
        return moedas;
    }

    public String gerarLink() throws UnsupportedEncodingException {
        String extensao = moedas.toString();
        //String link =
        return "https://api.invertexto.com/v1/currency/" + extensao + "?token=" + encodedToken;
    }

    public String consultaDados() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(gerarLink()))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}

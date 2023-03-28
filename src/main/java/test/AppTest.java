package test;

//import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class AppTest {

	public static void main(String[] args) throws Exception {
		
		String url = "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json";
		URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request= HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (titulo , poster,classificaçao)
        var parser  = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //System.out.println(listaDeFilmes.get(0));
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
	}

}

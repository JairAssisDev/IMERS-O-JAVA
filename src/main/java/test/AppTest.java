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
		
		String url = "https://api.nasa.gov/planetary/apod?api_key=mcktZ5KkYyHzgjXSbcY4mSfaaGuJ0F76AuRCjhA7&start_date=2023-03-10&end_date=2023-03-15";
		URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request= HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);
        //extrair só os dados que interessam (titulo , poster,classificaçao)
        var parser  = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(body);
        //System.out.println(listaDeFilmes.get(0));
        var geradora = new GeradoraDeFigurinhas();
        for (int i=0;i<5; i++) {

            Map<String,String>conteudo = listaDeConteudos.get(i);
            String urlImagem = conteudo.get("url")
            .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String titulo = conteudo.get("title").replace(":", "-");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/"+titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
	}

}

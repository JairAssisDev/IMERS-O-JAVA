package test;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AppTest {

	public static void main(String[] args) throws Exception {
		
		String url = "https://api.nasa.gov/planetary/apod?api_key=mcktZ5KkYyHzgjXSbcY4mSfaaGuJ0F76AuRCjhA7&start_date=2023-03-10&end_date=2023-03-15";
        
        var http =new ClienteHttp();
        String json = http.buscarDados(url);

        ExtratorDeConteudoNasa extrador = new ExtratorDeConteudoNasa();
        List<Conteudo>conteudos = extrador.extraiConteudos(json);
        
        var geradora = new GeradoraDeFigurinhas();

        for (int i=0;i<6; i++) {

            Conteudo conteudo = conteudos.get(i);
            

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/"+ conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
	}

}

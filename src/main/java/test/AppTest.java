package test;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AppTest {

	public static void main(String[] args) throws Exception {
		
		//String url = "https://api.nasa.gov/planetary/apod?api_key=mcktZ5KkYyHzgjXSbcY4mSfaaGuJ0F76AuRCjhA7&start_date=2023-03-10&end_date=2023-03-15";
        //ExtradorDeConteudo extrador = new ExtratorDeConteudoNasa();

        String url ="http://localhost:8080/linguagens";
        ExtradorDeConteudo extrador = new ExtratorDeConteudoDoIMDB();

        var http =new ClienteHttp();
        String json = http.buscarDados(url);

       

        List<Conteudo>conteudos = extrador.extraiConteudos(json);
        
        var geradora = new GeradoraDeFigurinhas();

        for (int i=0;i<2; i++) {

            Conteudo conteudo = conteudos.get(i);
            

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/"+ conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
	}

}

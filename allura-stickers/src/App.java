import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        
        //String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        //String URL = "https://api.nasa.gov/planetary/apod?api_key=dhClp0ahrhqj0WI7GaSef4eJCCKduE8xzmYiSRGx&////start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String URL = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new clientHttp();
        String json = http.buscaDados(URL);

        //Manipulação dos dados
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        
        //Mostrar os dados
        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0 ; i < 2 ; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }

}

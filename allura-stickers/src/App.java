import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        
        //String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String URL = "https://api.nasa.gov/planetary/apod?api_key=dhClp0ahrhqj0WI7GaSef4eJCCKduE8xzmYiSRGx&start_date=2022-06-12&end_date=2022-06-14";

        var http = new clientHttp();
        String json = http.buscaDados(URL);

        //Manipulação dos dados
        var parser = new JsonParse();
        List<Map<String,String>> listaDeConteudo = parser.parse(json);
        
        //Mostrar os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> conteudo : listaDeConteudo) {
            //String urlImagem = conteudo.get("image");
            String urlImagem = conteudo.get("url");
            String title = conteudo.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = title + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.get("title"));
            System.out.println();
        }
    }
}

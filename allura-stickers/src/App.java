import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Fazer a conexao http
        String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(URL);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //Manipulação dos dados
        var parser = new JsonParse();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);
        
        //Mostrar os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String title = filme.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = title + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(filme.get("title"));
            System.out.println();
        }
    }
}

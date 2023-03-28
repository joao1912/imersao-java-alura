import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Font;

public class GeradoraDeFigurinhas {
    public void cria() throws Exception {

        BufferedImage imagemOriginal = ImageIO.read(new File("./allura-stickers/entrada/filme.jpg"));

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        graphics.drawString("TOPZERA", 0, novaAltura - 100);

        var fonte = new Font("Arial", Font.BOLD, 32);
        graphics.setFont(fonte);

        ImageIO.write(novaImagem, "png", new File("./allura-stickers/saida/figurinha.png"));
    }

    public static void main(String[] args) throws Exception {
        var gerador = new GeradoraDeFigurinhas();
        gerador.cria();
    }
}

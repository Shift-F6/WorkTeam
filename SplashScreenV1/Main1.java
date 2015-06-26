package splashscreenv1;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main1 {
     
 static final SplashScreen splash = SplashScreen.getSplashScreen();//metodo que trae la clase splashscreen
    static class splash {
        
        int ancho_de_barra=10;
        int posicion_en_x_barra=20;
        int posicion_en_y_barra=15;
        int longitud_de_barra=560;

        void propiedades(Graphics2D g, int frame) {
            final String[] comps = {"datos", "interfaz", "aplicacion"};//Letras al cargar la aplicacion
            g.setComposite(AlphaComposite.Clear);
            g.fillRect(0,0,560,400);//posicion del rectangulo donde se colocan las letras y barra
            g.setPaintMode();
            g.setColor(Color.DARK_GRAY);//color de letras
            g.setFont(new Font("default", Font.ITALIC, 16));
            g.drawString("Cargando " + comps[(frame / 40) % 3] + "...", 400, 50);//texto que cambia y posicion del mismo
            g.setColor(Color.GREEN);//color de letras
            g.setFont(new Font("default", Font.BOLD, 20));
            g.drawString("Splash Screen", 40, 200);//texto que cambia y posicion del mismo
            g.setColor(Color.BLUE);//color del rectangulo barra
            g.fillRect(posicion_en_x_barra, posicion_en_y_barra, (frame * 5) % longitud_de_barra, ancho_de_barra);//posicion donde se coloca la barra
            g.drawImage(new ImageIcon(getClass().getResource("/splashscreenv1/logoShiftKey.png")).getImage(), 40, 60, 120, 120, null);
            g.drawImage(new ImageIcon(getClass().getResource("/splashscreenv1/logoShiftKey.png")).getImage(), 480, 300, 90, 90, null);
        }
        void empezar() throws NullPointerException, IOException {
            if (splash == null) {//verifica que tenga la imagen y no sea nulo
                JOptionPane.showMessageDialog(null, "SplashScreen.getSplashScreen() returned null");
                return;
            }
            Graphics2D g = splash.createGraphics();//crea los graficos
            if (g == null) {//verifica que los grafiicos no sean nulo
                JOptionPane.showMessageDialog(null, "g is null");
                return;
            }
            for (int i = 0; i < 5; i++) { // tiempo que tarda la barra del hilo 
                try {
                    Thread.sleep(1000);//tiempo del hilo
                } catch (InterruptedException e) {
              }
            }
             splash.setImageURL(getClass().getResource("/splashscreenv1/fondoblanco.jpg"));
            for (int i = 0; i < 112; i++) { // tiempo que tarda la barra del hilo 
                propiedades(g, i);//llama a las propiedades
                splash.update();//metodo que Aactualiza la ventana de bienvenida con contenido actual de la superposición de imágenes.
                try {
                    Thread.sleep(100);//tiempo del hilo
                } catch (InterruptedException e) {
                }
            }
            splash.close();
            try {
               //llamada a la nueva ventana
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public static void main(String args[]) throws Exception {
        splash me = new splash();
        me.empezar();
    }
}

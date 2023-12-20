package irrgarten;

import irrgarten.UI.TextUI;
import irrgarten.UI.UI;
import irrgarten.controller.Controller;
import org.w3c.dom.Text;


public class main {
    private TextUI ui;
    //private UI ui;
    public static void main(String[] args) {

        // Crear el juego, la vista y el controlador
        Game game = new Game(2); // Número de jugadores como ejemplo
        TextUI view = new TextUI();
        Controller controller = new Controller(game, view);
        view.showGame(game.getGameState());
        // Iniciar el juego
        controller.play();
    }
}
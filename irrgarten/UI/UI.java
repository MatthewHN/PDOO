package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

public interface UI {

    public default Directions nextMove(){
        return TextUI.nextMove();
    }

    public default void showGame(GameState gamestate){
        TextUI.showGame(gamestate);
    }
}

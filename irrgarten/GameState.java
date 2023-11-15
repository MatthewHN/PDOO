package irrgarten;

public class GameState {
    private final String labyrinth;
    private final String players;
    private final String monsters;
    private final int currentPlayer;
    private final boolean winner;
    private final String log;

    public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log) {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }

    public String getLabyrinth() {
        return labyrinth;
    }

    public String getPlayers() {
        return players;
    }

    public String getMonsters() {
        return monsters;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isWinner() {
        return winner;
    }

    public String getLog() {
        return log;
    }

    public String toString(){
        String aux = "";

        aux += getLabyrinth();
        aux += "\n";
        aux += getPlayers();
        aux += "\n";
        aux += getMonsters();
        aux += "\n";
        aux += getCurrentPlayer();
        aux += "\n";
        aux += isWinner();
        aux += "\n";
        aux += getLog();
        aux += "\n";

        return aux;
    }
}

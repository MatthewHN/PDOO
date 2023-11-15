package irrgarten;

public class MonsterSquare {
    private int row;
    private int col;

    private Monster monster;

    public MonsterSquare(int row, int col, Monster monster){
        this.monster = monster;
    }

    public boolean hasMonster(){
        return monster != null;
    }

    public Monster getMonster(){
        return monster;
    }

}

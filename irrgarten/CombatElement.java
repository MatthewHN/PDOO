package irrgarten;

public class CombatElement {
    private float effect; // El efecto del elemento de combate
    private int uses; // Número de usos disponibles

    /**
     * Constructor para CombatElement.
     * @param effect El efecto del elemento de combate.
     * @param uses El número de usos disponibles.
     */
    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }

    /**
     * Método abstracto para producir un efecto.
     * @return El valor del efecto producido.
     */
    protected float produceEffect(){
        if (uses > 0) {
            uses--;
            return effect;
        } else
            return 0;
    }

    /**
     * Descarta el elemento de combate cuando se agotan los usos.
     * @return Verdadero si el elemento fue descartado, falso en caso contrario.
     */
    public boolean discard() {
        if (uses > 0) {
            uses--;
            return uses == 0;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Effect: " + effect + ", Uses: " + uses;
    }
}

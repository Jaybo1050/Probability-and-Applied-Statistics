
public class Potion extends Trainer {
	
	
	public Potion() {
        super("Potion");
    }

    public void heal(Pokemon pokemon) {
        int newHP = pokemon.getHP() + 20;
        pokemon.setHP(newHP);
        System.out.println(pokemon.getClass().getSimpleName() + " used POTION healed by 20 HP! New HP: " + newHP);
    }
}

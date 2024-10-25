
public class Pokemon extends Card {
	private int HP;
    private boolean isEvolved;
    private int dmg;
    private boolean active;

    
    
  
    public int getDmg() {
        return dmg;
    }
   

    public void setDmg(int userInput) {
        dmg = userInput;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int userInput) {
        HP = userInput;
    }

    public boolean isEvolved() {
        return isEvolved;
    }

    public void setEvolved(boolean evolved) {
        this.isEvolved = evolved;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    // Override toString to display the Pokémon's name and HP
    @Override
    public String toString() {
        return getClass().getSimpleName() + " (HP: " + HP + ")";
    }



	
}

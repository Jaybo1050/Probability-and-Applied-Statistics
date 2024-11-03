
public class Pokemon extends Card {
	private int HP;
    private boolean isEvolved;
    private int dmg;
    private boolean active;

    
    public Pokemon(String requiredEnergyType) {
        this.type = requiredEnergyType;
    }
    
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
    
    public void attack(Pokemon target) {
        if (this.type != null) {
            target.setHP(target.getHP() - this.dmg);
            System.out.println(getClass().getSimpleName() + " attacks " + target.getClass().getSimpleName() + " for " + this.dmg + " damage!");
        } else {
            System.out.println(getClass().getSimpleName() + " doesn't have the required energy to attack!");
        }
    }

    // override toString to display the Pokemon name and HP
    public String toString() {
        String status;
        if (active) {
            status = "(Active)";
        } else {
            status = "(Benched)";
        }
        return getClass().getSimpleName() + " (" + type + " Type) " + status;
    }
	
}

public class Player extends GameObj {
    private String playerName;
    private String playerID;
    public int health = 3;

    public Player(int positionX, int positionY, int width, int height, String playerName, String playerID) {
        super(positionX, positionY, width, height);

        this.playerName = playerName;
        this.playerID = playerID;
    }

    public Player() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getHealth() {
        return health;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Projectile shoot() {
        return new Projectile(getPositionX(), getPositionY(), 10, 10, this);
    }

    public void hit() {
        if (health > 0) {
            health--;
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public int hashCode() {
        return playerID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            Player other = (Player) obj;
            return playerID.equals(other.playerID);
        }

        return false;
    }
}

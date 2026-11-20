public class Projectile extends GameObj {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean active = true;
    private Player player;

    public Projectile(int x, int y, int width, int height, Player player) {
        super(x, y, width, height);
        this.player = player;
    }

    public Projectile() {

    }

    public void destroy() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }
}


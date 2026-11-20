public class Projectile extends GameObj {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean active = true;

    public Projectile(int x, int y, int width, int height) {
        super(x, y, width, height);
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


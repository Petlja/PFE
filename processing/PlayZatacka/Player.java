
public class Player {
    float x, y;
    float angle;
    int color;
    boolean alive;
    PlayZatacka parent;

    Player(PlayZatacka parent, int idx) {
        this.parent = parent;
        this.alive = true;
        this.x = parent.random(parent.width);
        this.y = parent.random(parent.height);
        this.angle = parent.random(2 * parent.PI);
        this.color = parent.color(((idx % 7 + 1) & 1) * 255, ((idx % 7 + 1) & 2) * 255, ((idx % 7 + 1) & 4) * 255);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {

        this.alive = alive;
    }

    public void moveLeft() {
        this.angle -= GameSettings.TURNING_ANGLE;
    }

    public void moveRight() {
        this.angle += GameSettings.TURNING_ANGLE;
    }
    public void move() {
        if (!this.alive) {
            return;
        }
        this.x += Math.cos(this.angle);
        this.y += Math.sin(this.angle);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

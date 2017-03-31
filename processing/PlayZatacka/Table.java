import java.util.ArrayList;

public class Table {
    final float COLLISION_THRESHOLD = 0.9f;
    class Point {

        float x, y;
        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
        double distanceTo(Point other) {
            return Math.sqrt((this.x - other.x) * (this.x - other.x) + ((this.y - other.y)) * ((this.y - other.y)));
        }
    }
    ArrayList<Point> visitedPoints = new ArrayList<>();
    boolean isVisited(float x, float y) {
        for (int i = 0; i < visitedPoints.size(); i++) {
            if (visitedPoints.get(i).distanceTo(new Point(x, y)) < COLLISION_THRESHOLD) {
                return true;
            }
        }
        return false;
    }
    void setVisited(float x, float y) {
        visitedPoints.add(new Point(x, y));
    }

    boolean isInsideTable(int x, int y) {
        if (x < 0) {
            return false;
        }
        if (x >= GameSettings.TABLE_WIDTH) {
            return false;
        }
        if (y < 0) {
            return false;
        }
        if (y >= GameSettings.TABLE_HEIGHT) {
            return false;
        }
        return true;
    }

    boolean collisionDetection(Player player) {
        if (!player.isAlive()) {
            return true;
        }
        int x = (int) player.getX();
        int y = (int) player.getY();
        if (!isInsideTable(x, y)) {
            return true;
        }
        if (isVisited(player.getX(), player.getY())) {
            return true;
        }
        return false;
    }
}

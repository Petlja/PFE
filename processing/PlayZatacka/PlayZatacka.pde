

    Player[] players = new Player[GameSettings.PLAYER_COUNT];
    Table table = new Table();
    FirstPlayerStrategy startegy;


    public void settings() {
        size(800, 600);
        pixelDensity(2);
    }

    public void setup() {
        frameRate(100);
        background(0);
        stroke(255);
        initializeGame();
    }

    public void draw() {
        for (int i = 0; i < players.length; i++) {
            stroke(players[i].getColor());
            ellipse(players[i].getX(), players[i].getY(), GameSettings.FIGURE_WIDTH, GameSettings.FIGURE_WIDTH);

            players[i].move();
            players[i].setAlive(!table.collisionDetection(players[i]));
            table.setVisited(players[i].getX(), players[i].getY());

        }
    }

    void initializeGame() {
        for (int i = 0; i < GameSettings.PLAYER_COUNT; i++) {
            players[i] = new Player(this, i);

        }
    }

    public void keyPressed() {
        for (int i = 0; i < GameSettings.KEY_CODES.length; i++) {
            if (keyCode == GameSettings.KEY_CODES[i].charAt(0)) {
                players[i].moveLeft();
            } else if (keyCode == GameSettings.KEY_CODES[i].charAt(1)) {
                players[i].moveRight();
            }
        }
    }
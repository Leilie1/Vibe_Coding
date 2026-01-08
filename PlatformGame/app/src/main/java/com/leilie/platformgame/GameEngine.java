package com.leilie.platformgame;

public class GameEngine {
    private Player player;
    private Level level;
    private int coinsCollected = 0;
    private boolean gameWon = false;
    private boolean gameLost = false;

    public GameEngine(Level level) {
        this.level = level;
        this.player = new Player(level.startX, level.startY);
    }

    public void update() {
        if (gameWon || gameLost) return;

        player.update();
        handleCollisions();
        checkWinCondition();
        checkLoseCondition();
    }

    private void handleCollisions() {
        player.setOnGround(false);

        for (Level.Platform platform : level.platforms) {
            if (player.collidesWith(platform.x, platform.y, platform.width, platform.height)) {
                if (player.velocityY > 0 && player.y < platform.y + platform.height / 2) {
                    player.y = platform.y - player.radius;
                    player.velocityY = 0;
                    player.setOnGround(true);
                } else if (player.velocityY < 0 && player.y > platform.y + platform.height / 2) {
                    player.y = platform.y + platform.height + player.radius;
                    player.velocityY = 0;
                }
            }
        }

        for (Level.Coin coin : level.coins) {
            if (!coin.collected) {
                float dx = player.x - coin.x;
                float dy = player.y - coin.y;
                if (dx * dx + dy * dy < (player.radius + 20) * (player.radius + 20)) {
                    coin.collected = true;
                    coinsCollected++;
                }
            }
        }
    }

    private void checkWinCondition() {
        float dx = player.x - level.endX;
        float dy = player.y - level.endY;
        if (dx * dx + dy * dy < (player.radius + 30) * (player.radius + 30)) {
            gameWon = true;
        }
    }

    private void checkLoseCondition() {
        if (player.y > 2000) {
            gameLost = true;
            return;
        }

        for (Level.Enemy enemy : level.enemies) {
            if (player.collidesWith(enemy.x, enemy.y, enemy.width, enemy.height)) {
                gameLost = true;
                return;
            }
        }
    }

    public Player getPlayer() { return player; }
    public Level getLevel() { return level; }
    public int getCoinsCollected() { return coinsCollected; }
    public boolean isGameWon() { return gameWon; }
    public boolean isGameLost() { return gameLost; }
    public void reset() {
        player = new Player(level.startX, level.startY);
        coinsCollected = 0;
        gameWon = false;
        gameLost = false;
        for (Level.Coin coin : level.coins) {
            coin.collected = false;
        }
    }
}

package com.leilie.platformgame;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public float startX, startY;
    public float endX, endY;
    public List<Platform> platforms;
    public List<Enemy> enemies;
    public List<Coin> coins;

    public Level() {
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        coins = new ArrayList<>();
    }

    public static class Platform {
        public float x, y, width, height;
        
        public Platform(float x, float y, float width, float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    public static class Enemy {
        public float x, y, width, height;
        
        public Enemy(float x, float y, float width, float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    public static class Coin {
        public float x, y;
        public boolean collected;
        
        public Coin(float x, float y) {
            this.x = x;
            this.y = y;
            this.collected = false;
        }
    }
}

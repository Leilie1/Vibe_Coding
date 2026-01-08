package com.leilie.platformgame;

public class Player {
    public float x, y;
    public float velocityX, velocityY;
    public float radius = 40;
    private static final float GRAVITY = 0.8f;
    private static final float MAX_VELOCITY_X = 8f;
    private static final float JUMP_STRENGTH = 20f;
    private boolean onGround = false;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void update() {
        velocityY += GRAVITY;
        x += velocityX;
        y += velocityY;
        
    }

    public void moveLeft(float tilt) {
        velocityX -= tilt * 0.3f;
        velocityX = Math.max(-MAX_VELOCITY_X, velocityX);
    }

    public void moveRight(float tilt) {
        velocityX += tilt * 0.3f;
        velocityX = Math.min(MAX_VELOCITY_X, velocityX);
    }

    public void jump(float strength) {
        if (onGround) {
            velocityY = -Math.min(strength * JUMP_STRENGTH, 25f);
            onGround = false;
        }
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public boolean collidesWith(float x, float y, float width, float height) {
        float closestX = Math.max(x, Math.min(this.x, x + width));
        float closestY = Math.max(y, Math.min(this.y, y + height));
        float distanceX = this.x - closestX;
        float distanceY = this.y - closestY;
        return (distanceX * distanceX + distanceY * distanceY) < (radius * radius);
    }
}

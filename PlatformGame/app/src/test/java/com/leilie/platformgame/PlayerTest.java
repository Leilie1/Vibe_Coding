package com.leilie.platformgame;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new Player(100, 100);
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals(100, player.x, 0.01);
        assertEquals(100, player.y, 0.01);
        assertEquals(0, player.velocityX, 0.01);
        assertEquals(0, player.velocityY, 0.01);
    }

    @Test
    public void testPlayerUpdate() {
        player.velocityY = 5;
        float initialY = player.y;
        player.update();
        assertTrue(player.y > initialY);
    }

    @Test
    public void testPlayerJump() {
        player.setOnGround(true);
        player.jump(1.0f);
        assertTrue(player.velocityY < 0);
    }

    @Test
    public void testPlayerCannotJumpInAir() {
        player.setOnGround(false);
        player.jump(1.0f);
        assertEquals(0, player.velocityY, 0.01);
    }

    @Test
    public void testPlayerCollision() {
        player.x = 100;
        player.y = 100;
        assertTrue(player.collidesWith(80, 80, 50, 50));
        assertFalse(player.collidesWith(200, 200, 50, 50));
    }

    @Test
    public void testPlayerMoveRight() {
        player.moveRight(2.0f);
        assertTrue(player.velocityX > 0);
    }

    @Test
    public void testPlayerMoveLeft() {
        player.moveLeft(2.0f);
        assertTrue(player.velocityX < 0);
    }
}

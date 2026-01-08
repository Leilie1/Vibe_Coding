package com.leilie.platformgame;

import org.junit.Test;
import static org.junit.Assert.*;

public class LevelTest {
    @Test
    public void testLevelCreation() {
        Level level = new Level();
        assertNotNull(level.platforms);
        assertNotNull(level.enemies);
        assertNotNull(level.coins);
        assertEquals(0, level.platforms.size());
        assertEquals(0, level.enemies.size());
        assertEquals(0, level.coins.size());
    }

    @Test
    public void testPlatformCreation() {
        Level.Platform platform = new Level.Platform(10, 20, 100, 50);
        assertEquals(10, platform.x, 0.01);
        assertEquals(20, platform.y, 0.01);
        assertEquals(100, platform.width, 0.01);
        assertEquals(50, platform.height, 0.01);
    }

    @Test
    public void testEnemyCreation() {
        Level.Enemy enemy = new Level.Enemy(50, 60, 40, 40);
        assertEquals(50, enemy.x, 0.01);
        assertEquals(60, enemy.y, 0.01);
        assertEquals(40, enemy.width, 0.01);
        assertEquals(40, enemy.height, 0.01);
    }

    @Test
    public void testCoinCreation() {
        Level.Coin coin = new Level.Coin(100, 200);
        assertEquals(100, coin.x, 0.01);
        assertEquals(200, coin.y, 0.01);
        assertFalse(coin.collected);
    }

    @Test
    public void testCoinCollection() {
        Level.Coin coin = new Level.Coin(100, 200);
        coin.collected = true;
        assertTrue(coin.collected);
    }
}

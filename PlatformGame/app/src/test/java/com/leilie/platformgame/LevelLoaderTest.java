package com.leilie.platformgame;

import org.junit.Test;
import static org.junit.Assert.*;

public class LevelLoaderTest {
    @Test
    public void testCreateDefaultLevel() {
        Level level = LevelLoader.createDefaultLevel();
        
        assertNotNull(level);
        assertEquals(100, level.startX, 0.01);
        assertEquals(300, level.startY, 0.01);
        assertEquals(2000, level.endX, 0.01);
        assertEquals(300, level.endY, 0.01);
        
        assertTrue(level.platforms.size() > 0);
        assertTrue(level.enemies.size() > 0);
        assertTrue(level.coins.size() > 0);
    }

    @Test
    public void testDefaultLevelHasValidStructure() {
        Level level = LevelLoader.createDefaultLevel();
        
        for (Level.Platform platform : level.platforms) {
            assertTrue(platform.width > 0);
            assertTrue(platform.height > 0);
        }
        
        for (Level.Enemy enemy : level.enemies) {
            assertTrue(enemy.width > 0);
            assertTrue(enemy.height > 0);
        }
        
        for (Level.Coin coin : level.coins) {
            assertFalse(coin.collected);
        }
    }
}

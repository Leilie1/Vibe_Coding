package com.leilie.platformgame;

import android.content.Context;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelLoader {
    public static Level loadLevel(Context context, String filename) {
        try {
            InputStream is = context.getAssets().open(filename);
            InputStreamReader reader = new InputStreamReader(is);
            Gson gson = new Gson();
            Level level = gson.fromJson(reader, Level.class);
            reader.close();
            return level;
        } catch (Exception e) {
            e.printStackTrace();
            return createDefaultLevel();
        }
    }

    public static Level createDefaultLevel() {
        Level level = new Level();
        level.startX = 100;
        level.startY = 300;
        level.endX = 2000;
        level.endY = 300;

        level.platforms.add(new Level.Platform(0, 400, 500, 50));
        level.platforms.add(new Level.Platform(600, 400, 300, 50));
        level.platforms.add(new Level.Platform(1000, 350, 300, 50));
        level.platforms.add(new Level.Platform(1400, 300, 300, 50));
        level.platforms.add(new Level.Platform(1800, 350, 300, 50));

        level.enemies.add(new Level.Enemy(700, 350, 40, 40));
        level.enemies.add(new Level.Enemy(1100, 300, 40, 40));

        level.coins.add(new Level.Coin(300, 300));
        level.coins.add(new Level.Coin(750, 300));
        level.coins.add(new Level.Coin(1150, 250));
        level.coins.add(new Level.Coin(1500, 200));
        level.coins.add(new Level.Coin(1900, 250));

        return level;
    }
}

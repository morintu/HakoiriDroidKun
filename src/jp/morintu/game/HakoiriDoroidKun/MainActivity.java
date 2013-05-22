/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun;

import java.util.ArrayList;
import java.util.List;

import jp.morintu.game.HakoiriDoroidKun.parts.GameMainView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.jp.morintu.HakoiriDoroidKun.R;

/**
 * InitialActivity
 * 
 * @author Morintu
 * 
 */
public class MainActivity extends Activity
{

    private GameMainView gameView;
    private List<Bitmap> bitmapList = new ArrayList<Bitmap>();

    private int charactorResouceID[] = { R.drawable.chr_front_01,
            R.drawable.chr_front_02, R.drawable.chr_left_01, R.drawable.chr_left_01 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameMainView(this);

        // Set full screen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Load XML
        setContentView(R.layout.activity_initial);

        // TODO Load Bitmap Class
        // Load Bitmap
        for (int i = 0; i < charactorResouceID.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(
                    getResources(),
                    charactorResouceID[i]);
            // Set Bitmap
            bitmapList.add(bitmap);
        }

        addContentView(gameView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        for (int i = 0; i < 15; i++) {
            gameView.makeCharacter(bitmapList.get(i % 4));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.stopGame();
    }

    @Override
    protected void onDestroy() {
        gameView.close();
        super.onDestroy();
    }
}

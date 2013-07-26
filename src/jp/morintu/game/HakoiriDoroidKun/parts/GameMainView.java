/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.parts;

import java.util.Random;

import jp.morintu.game.HakoiriDoroidKun.Function;
import jp.morintu.game.HakoiriDoroidKun.obj.CharacterManager;
import jp.morintu.game.HakoiriDoroidKun.obj.DownDroid;
import jp.morintu.game.HakoiriDoroidKun.util.MyLog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameMainView extends SurfaceView implements
        SurfaceHolder.Callback, OnTouchListener
{
    private final static boolean DEBUG = true;
    private final static String TAG = GameMainView.class.getSimpleName();

    private static enum GAME_STATE
    {
        LOADING, TITLE, MAIN, RESULT,
    };

    private Thread mDrawLooper;
    private SurfaceHolder mSurfaceHolder;
    private Random mRandom;
    private Vector2 mDisplaySize;
    private static final Vector2 mTouchVec = new Vector2();
    private GameStatusBase mGameStatusBase;
    private CharacterManager mCharacterManager;

    private boolean mIsGameMove = false;
    private GAME_STATE mState;

    /**
     * Construct
     * 
     * @param context
     */
    public GameMainView(Context context) {
        super(context);
        mRandom = new Random();
        mDisplaySize = new Vector2();
        mCharacterManager = new CharacterManager();
        mState = GAME_STATE.LOADING;

        mGameStatusBase = new GameStatusBase();
        mGameStatusBase.setCharcterManager(mCharacterManager);

        // Surface process
        {
            // Get SurfaceHolder
            mSurfaceHolder = getHolder();

            // Set callback in SurfaceHolder
            mSurfaceHolder.addCallback(this);
            mSurfaceHolder.setFixedSize(getWidth(), getHeight());
        }

        this.setOnTouchListener(this);

        // Set focus
        setFocusable(true);
        requestFocus();
    }

    /**
     * Surface View Create
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mDrawLooper = new Thread(new DrawThread());
    }

    private class DrawThread implements Runnable
    {
        // TODO: Use Timer 30FPS?
        @Override
        public void run() {
            while ((mDrawLooper != null) && (mIsGameMove)) {
                doDraw();

                mGameStatusBase.onMoveGame();
            }
        }
    }

    /**
     * Check display size
     * 
     * Call back.
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        mDisplaySize.x = width;
        mDisplaySize.y = height;
        Function.setDisplaySize(mDisplaySize);
        MyLog.d(DEBUG, TAG, "width=" + width + " height=" + height);

        // Thread Start
        mDrawLooper.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawLooper = null;
    }

    public void close() {
        surfaceDestroyed(mSurfaceHolder);
    }

    public void startGame() {
        if (mIsGameMove == false) {
            mIsGameMove = true;
        }
    }

    public void stopGame() {
        mIsGameMove = false;
    }

    /**
     * Drawing
     * 
     * doDraw() between lockCanvas() and unlockCanvas(), It processes for a
     * short time.
     * 
     * @param canvas
     */
    public void doDraw() {
        Canvas canvas = mSurfaceHolder.lockCanvas();
        if (canvas != null) {
            canvas.save();
            canvas.drawColor(Color.BLACK);
            mGameStatusBase.onDrawGame(canvas);
            canvas.restore();
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void gameStateChange(GAME_STATE state) {
        mState = state;
        switch (state)
        {
        case LOADING:
            break;
        case TITLE:
            break;
        case MAIN:
            break;
        case RESULT:
            break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mTouchVec.set((int) event.getX(), (int) event.getY());
        switch (event.getAction())
        {
        case MotionEvent.ACTION_DOWN:
            mGameStatusBase.onPressTouch(mTouchVec);
            break;
        case MotionEvent.ACTION_UP:
            break;
        case MotionEvent.ACTION_MOVE:
            break;
        default:
            break;
        }
        return false;
    }

    public void makeCharacter(Bitmap bitmap) {
        int newX = mRandom.nextInt(200);
        newX += (bitmap.getWidth() / 2);
        Vector2 vec = new Vector2(newX, 0 - (bitmap.getHeight() / 2));
        DownDroid obj = new DownDroid(bitmap, vec);
        obj.setSize(120, 120);
        mCharacterManager.addCharacter(obj);
    }
}

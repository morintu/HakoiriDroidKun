/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.obj;

import java.util.Random;

import jp.morintu.game.HakoiriDoroidKun.parts.Vector2;
import android.graphics.Bitmap;

public class DownDroid extends CharacterObj
{
    private Random mRandom;

    public DownDroid() {
        super();
        create();
    }

    public DownDroid(Bitmap bitmap, Vector2 initPos) {
        super(bitmap, initPos);
        create();
    }

    private void create() {
        mRandom = new Random();
        inital();
    }

    @Override
    public void renewCharacterObj(Bitmap bitmap, Vector2 initPos) {
        super.renewCharacterObj(bitmap, initPos);
        inital();
    }

    private void inital() {
        int speed = mRandom.nextInt(5) + 1;
        mMove.set(0, speed);
        if (mSize.x != 0) {
            int newX = mRandom.nextInt(200);
            newX += (mSize.x / 2);
            mPos.set(newX, -mSize.y / 2);

            setHalfSizeFromSize();
            setHitSizeFromHalfSize();
        }
        updateMatrix();
    }

    @Override
    public void moveCharacter() {
        super.moveCharacter();
        if (mState == CHARACTOR_STATE.STATE_HIDE) {
            renewCharacterObj(mBitmap, mPos);
        }
    }

    @Override
    protected void movePos() {
        mPos.plusEqual(mMove);
        if ((mState == CHARACTOR_STATE.STATE_WAIT) && (mPos.y >= 0)) {
            mState = CHARACTOR_STATE.STATE_NORMAL;
        }
    }

    @Override
    protected void onTouch() {
        mRotateSpeed = 20;
    }
}

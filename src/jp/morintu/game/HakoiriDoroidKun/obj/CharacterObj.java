/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.obj;

import jp.morintu.game.HakoiriDoroidKun.parts.Vector2;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public abstract class CharacterObj
{
    @SuppressWarnings("unused")
    private final static boolean DEBUG = false;
    @SuppressWarnings("unused")
    private final static String TAG = CharacterObj.class.getSimpleName();

    enum CHARACTOR_STATE
    {
        STATE_WAIT, STATE_NORMAL, STATE_HIDE, STATE_TOUCHED,
    };

    protected abstract void movePos();

    protected abstract void onTouch();

    protected Vector2 mPos;
    protected Vector2 mSize;
    protected Vector2 mScale;
    protected Vector2 mHalfSize;
    protected int mHitSize; // minimum half size

    protected Vector2 mMove;
    protected float mRotate;
    protected float mRotateSpeed;

    protected Bitmap mBitmap;
    protected Matrix mMat;

    protected Vector2 mDisplaySize; // check display out
    protected CHARACTOR_STATE mState;
    protected boolean mInDisplay;

    /**
     * Construct
     */
    public CharacterObj() {
        create(null, new Vector2(0, 0));
    }

    public CharacterObj(Bitmap bitmap, Vector2 initPos) {
        create(bitmap, initPos);
    }

    /**
     * Renew
     * 
     * @param bitmap
     * @param initPos
     */
    public void renewCharacterObj(Bitmap bitmap, Vector2 initPos) {
        initial(bitmap, initPos);
    }

    /**
     * Create inner parameter and initial
     * 
     * @param bitmap
     * @param initPos
     */
    private void create(Bitmap bitmap, Vector2 initPos) {
        mPos = new Vector2();
        mSize = new Vector2();
        mScale = new Vector2();
        mHalfSize = new Vector2();
        mMove = new Vector2();
        mDisplaySize = new Vector2();
        mMat = new Matrix();
        initial(bitmap, initPos);
    }

    /**
     * Initialize inner parameter
     * 
     * @param bitmap
     * @param initPos
     */
    private void initial(Bitmap bitmap, Vector2 initPos) {
        mRotate = 0;
        mRotateSpeed = 0;
        mHitSize = 0;
        mMove.set(0, 0);
        setPos(initPos);
        setBitmap(bitmap);

        // changed setting
        updateMatrix();
        mState = CHARACTOR_STATE.STATE_WAIT;
    }

    /**
     * Position set & get
     * 
     * @param pos
     */
    public void setPos(Vector2 pos) {
        mPos.set(pos);
    }

    public Vector2 getPos() {
        return mPos;
    }

    /**
     * Bitmap set & get
     * 
     * @param bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        setBitmapPrivate(bitmap);
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    private void setBitmapPrivate(Bitmap bitmap) {
        mBitmap = bitmap;
        // preview not set size
        if (mSize.x == 0) {
            mSize.set(bitmap.getWidth(), bitmap.getHeight());
            mScale.set(1, 1);
            setHalfSizeFromSize();
            setHitSizeFromHalfSize();
        }
        // preview set size
        else {
            setScaleFromBitmapSize(bitmap);
        }
    }

    /**
     * Size set & get
     * 
     * @param size
     */
    public void setSize(Vector2 size) {
        if (size == null) {
            return;
        }
        mSize.set(size);
        setSizePrivate();
    }

    public void setSize(float x, float y) {
        mSize.set(x, y);
        setSizePrivate();
    }

    public void setSizeZeroClear() {
        mSize.set(0, 0);
        mHalfSize.set(0, 0);
        mHitSize = 0;
        mScale.set(0, 0);
    }

    public Vector2 getSize() {
        return mSize;
    }

    private void setSizePrivate() {
        // Set Half size
        setHalfSizeFromSize();
        // Set Hit size
        setHitSizeFromHalfSize();
        // Set Scale
        if (mBitmap == null) {
            mScale.set(1, 1);
        } else {
            setScaleFromBitmapSize(mBitmap);
        }
    }

    protected void setHalfSizeFromSize() {
        mHalfSize.set(mSize.x / 2.0f, mSize.y / 2.0f);
    }

    protected void setHitSizeFromHalfSize() {
        if (mHalfSize.x < mHalfSize.y) {
            mHitSize = (int) mHalfSize.x;
        } else {
            mHitSize = (int) mHalfSize.y;
        }
    }

    private void setScaleFromBitmapSize(Bitmap bitmap) {
        mScale.set(mSize.x / bitmap.getWidth(), mSize.y / bitmap.getHeight());
    }

    /**
     * Display size set & get
     * 
     * @param size
     */
    public void setDisplaySize(Vector2 size) {
        if (size == null) {
            return;
        }
        mDisplaySize = size;
    }

    public Vector2 getDisplaySize() {
        return mDisplaySize;
    }

    protected void updateMatrix() {
        mMat.reset();
        mMat.postScale(mScale.x, mScale.y);
        mMat.postTranslate(-(mHalfSize.x), -(mHalfSize.y));
        mMat.postRotate(mRotate);
        mMat.postTranslate(mPos.x, mPos.y);
    }

    protected boolean checkCanNotMove() {
        // Hide character is non move
        if (mState == CHARACTOR_STATE.STATE_HIDE) {
            return true;
        }
        return false;
    }

    public void moveCharacter() {
        if (checkCanNotMove()) {
            return;
        }

        // Character is moving position.
        movePos();

        // Change rotation
        mRotate += mRotateSpeed;

        // Over display check
        mInDisplay = true;
        if (((mPos.x + mHalfSize.x) < 0)
                || ((mPos.x - mHalfSize.x) > mDisplaySize.x)) {
            overDisplayX();
            mInDisplay = false;
        }
        if (((mPos.y + mHalfSize.y) < 0)
                || ((mPos.y - mHalfSize.y) > mDisplaySize.y)) {
            overDisplayY();
            mInDisplay = false;
        }
        if ((mState == CHARACTOR_STATE.STATE_WAIT) && (mInDisplay == true)) {
            mState = CHARACTOR_STATE.STATE_NORMAL;
        }

        // Updating
        updateMatrix();
    }

    protected void overDisplayX() {
        mMove.x = -mMove.x;
    }

    protected void overDisplayY() {
        overDisplay();
    }

    protected void overDisplay() {
        if ((mState != CHARACTOR_STATE.STATE_WAIT)) {
            mState = CHARACTOR_STATE.STATE_HIDE;
        }
    }

    public void onTouchCharacter() {
        if (mState != CHARACTOR_STATE.STATE_TOUCHED) {
            onTouch();
        }
        mState = CHARACTOR_STATE.STATE_TOUCHED;
    }

    public void drawCharacter(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        canvas.drawBitmap(mBitmap, mMat, null);
    }

    /**
     * Check Visibility
     * 
     * @return true: invisible, false: visible
     */
    public boolean isHide() {
        if (mState == CHARACTOR_STATE.STATE_HIDE) {
            return true;
        }
        return false;
    }

    /**
     * Check Touched
     * 
     * @return true: touched, false: none
     */
    public boolean isTouched() {
        if (mState == CHARACTOR_STATE.STATE_TOUCHED) {
            return true;
        }
        return false;
    }

    /**
     * Check touched
     * 
     * @param pos
     *            touch position
     * @return true: hit, false no hit
     */
    public boolean checkTouch(Vector2 pos) {
        Vector2 sa = new Vector2(pos.x - mPos.x, pos.y - mPos.y);
        int kyori = (int) Math.sqrt((sa.x * sa.x) + (sa.y * sa.y));

        if (kyori < mHitSize) {
            return true;
        }
        return false;
    }
}

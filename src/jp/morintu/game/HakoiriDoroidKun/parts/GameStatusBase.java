/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.parts;

import jp.morintu.game.HakoiriDoroidKun.obj.CharacterManager;
import jp.morintu.game.HakoiriDoroidKun.obj.CharacterObj;
import android.graphics.Canvas;

public class GameStatusBase
{
    protected CharacterManager mCharacterManager;
    protected CharacterManager mTouchList;

    public GameStatusBase() {
        mTouchList = new CharacterManager();
    }

    public void onCreateGame() {

    }

    public void onResetGame() {

    }

    public void onMoveGame() {
        mCharacterManager.moveCharacter();
        mTouchList.moveCharacter();
    }

    public void onDrawGame(Canvas canvas) {
        // Droid Obj Draw
        mCharacterManager.drawCharacter(canvas);

        mTouchList.drawCharacter(canvas);
    }

    public void onPressTouch(Vector2 touchVec) {
        CharacterObj obj = mCharacterManager.getTouchCharacter(touchVec);
        if (obj != null) {
            // mCharacterManager.popCharacter(obj);
            // mTouchList.addCharacter(obj);
        }
    }

    /**
     * Set a CharacterManager in this class
     * 
     * @param characterManager
     */
    public void setCharcterManager(CharacterManager characterManager) {
        mCharacterManager = characterManager;
    }

    /**
     * Is the character set
     * 
     * @return true:was set, false: non set
     */
    public boolean isCharacterManagerSetting() {
        if (mCharacterManager != null) {
            return false;
        }
        return true;
    }

}

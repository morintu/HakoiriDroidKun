/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.obj;

import java.util.ArrayList;
import java.util.List;

import jp.morintu.game.HakoiriDoroidKun.parts.Vector2;
import android.graphics.Canvas;

public class CharacterList
{
    private List<CharacterObj> mObjList;
    private Vector2 mDisplaySize;
    private int mObjCount;

    public CharacterList() {
        mObjList = new ArrayList<CharacterObj>();
        mDisplaySize = new Vector2();
        mObjCount = 0;
    }

    public void setDisplaySize(Vector2 displaySize) {
        if (displaySize != null) {
            mDisplaySize = displaySize;
        }
    }

    public void addCharacter(CharacterObj obj) {
        if (obj != null) {
            mObjList.add(obj);
            mObjCount++;
        }
    }

    public void getCharacter(int num) {
        mObjList.get(num);
        mObjCount--;
    }

    public int getCharacterCount() {
        return mObjCount;
    }

    public void drawCharacter(Canvas canvas) {
        if (canvas != null) {
            for (CharacterObj obj : mObjList) {
                if (obj.isHide() == false) {
                    obj.drawCharacter(canvas);
                }
            }
        }
    }

    public void moveCharacter() {
        for (CharacterObj obj : mObjList) {
            if (obj.getDisplaySize().x == 0) {
                if (mDisplaySize.x != 0) {
                    obj.setDisplaySize(mDisplaySize);
                }
            }
            obj.moveCharacter();
        }
    }

    /**
     * 
     * @return true:hit false:nohit
     */
    public boolean isTouchCharacter(Vector2 touchVec) {
        if (touchVec != null) {
            for (int i = mObjList.size() - 1; i >= 0; i--) {
                final CharacterObj obj = mObjList.get(i);
                if (obj.isTouched() || obj.isHide()) {
                    continue;
                }
                if (obj.checkTouch(touchVec)) {
                    obj.onTouchCharacter();
                    return true;
                }
            }
        }
        return false;
    }
}

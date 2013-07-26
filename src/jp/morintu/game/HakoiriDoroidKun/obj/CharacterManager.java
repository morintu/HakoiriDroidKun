/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.obj;

import java.util.ArrayList;
import java.util.List;

import jp.morintu.game.HakoiriDoroidKun.Function;
import jp.morintu.game.HakoiriDoroidKun.parts.Vector2;
import android.graphics.Canvas;

public class CharacterManager
{
    protected List<CharacterObj> mObjList;
    protected int mObjCount;

    public CharacterManager() {
        mObjList = new ArrayList<CharacterObj>();
        mObjCount = 0;
    }

    public void addCharacter(CharacterObj obj) {
        if (obj != null) {
            mObjList.add(obj);
            mObjCount++;
        }
    }

    public CharacterObj getCharacter(int num) {
        CharacterObj obj = mObjList.get(num);
        if (obj != null) {
            mObjCount--;
        }
        return obj;
    }

    public boolean popCharacter(CharacterObj obj) {
        int num = mObjList.indexOf(obj);
        if (num != -1) {
            mObjList.get(num);
            mObjCount--;
            return true;
        }
        return false;
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
                if (Function.getDisplaySize().x != 0) {
                    obj.setDisplaySize(Function.getDisplaySize());
                }
            }
            obj.moveCharacter();
        }
    }

    /**
     * getTouchCharacter
     * 
     * @return hitObject
     */
    public CharacterObj getTouchCharacter(Vector2 touchVec) {
        if (touchVec != null) {
            for (int i = mObjList.size() - 1; i >= 0; i--) {
                final CharacterObj obj = mObjList.get(i);
                if (obj.isTouched() || obj.isHide()) {
                    continue;
                }
                if (obj.checkTouch(touchVec)) {
                    obj.onTouchCharacter();
                    return obj;
                }
            }
        }
        return null;
    }
}

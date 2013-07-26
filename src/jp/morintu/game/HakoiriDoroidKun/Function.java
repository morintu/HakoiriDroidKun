/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun;

import jp.morintu.game.HakoiriDoroidKun.parts.Vector2;

public class Function
{
    private final static Vector2 mDisplaySize = new Vector2();
    private final static Vector2 mSizeDemesion = new Vector2();

    public static void setDisplaySize(Vector2 displaySize) {
        mDisplaySize.x = displaySize.x;
        mDisplaySize.y = displaySize.y;
        mSizeDemesion.x = displaySize.x / Define.BASIC_DIMENSION.x;
        mSizeDemesion.y = displaySize.y / Define.BASIC_DIMENSION.y;
    }

    public static Vector2 getSizeDemesion() {
        return mSizeDemesion;
    }

    public static Vector2 getDisplaySize() {
        return mDisplaySize;
    }
}

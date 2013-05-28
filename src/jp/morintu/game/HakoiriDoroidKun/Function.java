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
    private final static Vector2 sizeDemesion = new Vector2();

    public static void setSizeDemesion(Vector2 displaySize) {
        sizeDemesion.x = displaySize.x / Define.BASIC_DIMENSION.x;
        sizeDemesion.y = displaySize.y / Define.BASIC_DIMENSION.y;
    }

    public static Vector2 getSizeDemesion() {
        return sizeDemesion;
    }
}

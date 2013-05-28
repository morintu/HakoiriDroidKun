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

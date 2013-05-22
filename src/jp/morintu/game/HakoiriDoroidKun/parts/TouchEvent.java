/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.parts;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchEvent implements OnTouchListener
{
    interface TouchAcitonListener
    {
        void onActionMove(View view, Vector2 vec);
    }

    private Vector2 nowPoint;
    private TouchAcitonListener actionListener;

    public void setTouchAcitonListener(TouchAcitonListener listener) {
        actionListener = listener;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // get touch point
        Vector2 pos = new Vector2((int) event.getRawX(), (int) event.getRawY());

        switch (event.getAction())
        {
        case MotionEvent.ACTION_MOVE:
            actionListener.onActionMove(view, pos);
            break;
        }

        // save point
        nowPoint.x = pos.x;
        nowPoint.y = pos.y;

        return true;
    }

    public Vector2 getLastPostion() {
        return nowPoint;
    }
}

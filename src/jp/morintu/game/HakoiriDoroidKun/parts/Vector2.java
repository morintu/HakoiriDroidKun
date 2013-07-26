/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [���쌠�\���̕ύX�E�폜�A�܂��͖{�t�@�C���̃w�b�_�̍폜���ւ���B]
 */
package jp.morintu.game.HakoiriDoroidKun.parts;

public class Vector2
{
    public float x;
    public float y;

    public Vector2() {
        x = 0;
        y = 0;
    }

    public Vector2(float sx, float sy) {
        x = sx;
        y = sy;
    }

    public void set(float sx, float sy) {
        x = sx;
        y = sy;
    }

    public void set(Vector2 initVec) {
        if (initVec == null) {
            return;
        }
        x = initVec.x;
        y = initVec.y;
    }

    public void plusEqual(Vector2 vec) {
        x += vec.x;
        y += vec.y;
    }

    public void plusAnser(Vector2 vec, Vector2 ans) {
        ans.x = x + vec.x;
        ans.y = y + vec.y;
    }

    public void minusEqual(Vector2 vec) {
        x -= vec.x;
        y -= vec.y;
    }

    public void minusAnser(Vector2 vec, Vector2 ans) {
        ans.x = x - vec.x;
        ans.y = y - vec.y;
    }
}

package com.example.tank1990;

import android.graphics.Canvas;

import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<>();

    public void update() {
        for(int i=0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.update();
        }
    }

    public void draw(Canvas canvas) {
        for(int i=0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.draw(canvas);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}

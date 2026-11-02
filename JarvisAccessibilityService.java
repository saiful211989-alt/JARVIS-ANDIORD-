package com.jarvis.assistant;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.view.accessibility.AccessibilityEvent;

public class JarvisAccessibilityService extends AccessibilityService {
    private static JarvisAccessibilityService instance;

    @Override public void onServiceConnected() { super.onServiceConnected(); instance = this; }
    @Override public void onAccessibilityEvent(AccessibilityEvent event) { }
    @Override public void onInterrupt() { }
    @Override public void onDestroy() { if (instance == this) instance = null; super.onDestroy(); }

    public static boolean scroll(boolean down) {
        if (instance == null) return false;
        Path path = new Path();
        float x = 540f;
        if (down) { path.moveTo(x, 450f); path.lineTo(x, 1450f); }
        else { path.moveTo(x, 1450f); path.lineTo(x, 450f); }
        GestureDescription gesture = new GestureDescription.Builder()
            .addStroke(new GestureDescription.StrokeDescription(path, 0, 450))
            .build();
        return instance.dispatchGesture(gesture, null, null);
    }
}

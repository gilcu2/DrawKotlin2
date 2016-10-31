package com.gilcu2.drawkotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

/**
 * Created by gilcu2 on 10/30/16.
 */
class DrawingView(context: Context) : View(context) {
    init {
        setupDrawing()
    }

    fun setupDrawing() = {

    }
}

public inline fun ViewManager.drawingView(theme: Int = 0) = drawingView(theme) {}
public inline fun ViewManager.drawingView(theme: Int = 0, init: DrawingView.() -> Unit) =
        ankoView({ DrawingView(it) }, theme, init)

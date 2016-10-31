package com.gilcu2.drawkotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

/**
 * Created by gilcu2 on 10/30/16.
 */
class DrawingView(context: Context) : View(context) {
    init {
        setupDrawing()
    }

    val drawPath = Path()
    val drawPaint = Paint()
    var paintColor = 0xFF6600
    val canvasPaint = Paint(Paint.DITHER_FLAG)


    fun setupDrawing() = {
        drawPaint.setColor(paintColor)
        drawPaint.setAntiAlias(true)
        drawPaint.setStrokeWidth(20.0f)
        drawPaint.setStyle(Paint.Style.STROKE)
        drawPaint.setStrokeJoin(Paint.Join.ROUND)
        drawPaint.setStrokeCap(Paint.Cap.ROUND)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas (canvasBitmap)
    }
}

public inline fun ViewManager.drawingView(theme: Int = 0) = drawingView(theme) {}
public inline fun ViewManager.drawingView(theme: Int = 0, init: DrawingView.() -> Unit) =
        ankoView({ DrawingView(it) }, theme, init)

package com.gilcu2.drawkotlin

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

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


    val canvasPaint = Paint(Paint.DITHER_FLAG)
    var canvasBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    var drawCanvas = Canvas(canvasBitmap)

    fun setupDrawing() = {
        drawPaint.setAntiAlias(true)
        drawPaint.setStrokeWidth(20.0f)
        drawPaint.setStyle(Paint.Style.STROKE)
        drawPaint.setStrokeJoin(Paint.Join.ROUND)
        drawPaint.setStrokeCap(Paint.Cap.ROUND)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(canvasBitmap, 0.0f, 0.0f, canvasPaint)
        canvas.drawPath(drawPath, drawPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.getX()
        val touchY = event.getY()
        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> drawPath.moveTo(touchX, touchY)
            MotionEvent.ACTION_MOVE -> drawPath.lineTo(touchX, touchY)
            MotionEvent.ACTION_UP -> {
                drawCanvas.drawPath(drawPath, drawPaint)
                drawPath.reset()
            }
            else -> return false

        }
        invalidate()
        return true
    }

    fun setColor(color: Int) {
        invalidate()
        drawPaint.setColor(color)
    }

    fun setBrushSize(brushSize: Float) {
        invalidate()
        drawPaint.setStrokeWidth(brushSize)
    }

    fun startNew() {
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()
    }
}

public inline fun ViewManager.drawingView(theme: Int = 0) = drawingView(theme) {}
public inline fun ViewManager.drawingView(theme: Int = 0, init: DrawingView.() -> Unit) =
        ankoView({ DrawingView(it) }, theme, init)

package com.gilcu2.drawkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.style

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivityUi().setContentView(this)

    }

}

class MainActivityUi : AnkoComponent<MainActivity> {
    private val customStyle = { v: Any ->
        when (v) {
//            is Button -> v.textSize = 26f
//            is EditText -> v.textSize = 24f
        }
    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        data class MainButtonData(val image: Int, val description: String)

        val activityButtonsData = listOf(
                MainButtonData(R.drawable.new_pic, "@string/start_new"),
                MainButtonData(R.drawable.brush, "@string/brush"),
                MainButtonData(R.drawable.eraser, "@string/eraser"),
                MainButtonData(R.drawable.save, "@string/save"))

        val colorsData = listOf(0xFF0000, 0x00FF00, 0x0000FF, 0xFFAA00, 0, 0xFFFFFF)
        val brushSizes = listOf(20f, 40f, 60f)

        var brushSize = brushSizes[0]
        var lastBrushSize = brushSize

        verticalLayout {

            // Activity selection
            linearLayout {

                activityButtonsData.forEach {
                    imageButton {
                        contentDescription = it.description
                        imageResource = it.image
                    }.lparams {
                        height = matchParent
                    }
                }

            }.lparams {
                height = dip(50)
                gravity = Gravity.CENTER
            }

            val drawView = drawingView {
                backgroundColor = 0xFFFFFF.opaque
            }.lparams {
                margin = dip(5)
            }.lparams {
                weight = 1F
            }

            // Color selection
            val paintLayout = linearLayout {

                colorsData.forEach {
                    imageButton {
                        backgroundColor = it.opaque
                        tag = it.opaque
                        contentDescription = "@string/paint"
//                        imageResource=R.drawable.paint
                        onClick {
                            drawView.setColor(this.tag.toString().toInt())
                        }
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                        margin = dip(2)
                    }
                }

            }.lparams {
                height = dip(54)
                gravity = Gravity.CENTER
            }

            var currPaint = paintLayout.getChildAt(0)
            drawView.setColor(currPaint.tag.toString().toInt())
            drawView.setBrushSize(brushSize)

        }.applyRecursively(customStyle)
    }
}

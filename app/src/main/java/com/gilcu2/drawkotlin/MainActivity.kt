package com.gilcu2.drawkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.style

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivityUi().setContentView(this)

    }

}

class MainActivityUi : AnkoComponent<MainActivity>, AnkoLogger {

    lateinit var activityLayout: LinearLayout
    lateinit var drawView: DrawingView
    lateinit var colorLayout: LinearLayout

    val activityButtons = mutableMapOf<String, ImageButton>()

    //TODO use onClick to set the method

    data class MainButtonData(val image: Int, val description: String, val onClick: (View?) -> Unit = {})

    val activityButtonsData = listOf(
            MainButtonData(R.drawable.new_pic, "@string/start_new"),
            MainButtonData(R.drawable.brush, "@string/brush"),
            MainButtonData(R.drawable.eraser, "@string/eraser"),
            MainButtonData(R.drawable.save, "@string/save"))

    val colorsData = listOf(0xFF0000, 0x00FF00, 0x0000FF, 0xFFAA00, 0, 0xFFFFFF)
    val brushSizes = listOf(20f, 80f, 320f)
    var brushSize = brushSizes[0]

    private val customStyle = { v: Any ->
        when (v) {
//            is Button -> v.textSize = 26f
//            is EditText -> v.textSize = 24f
        }
    }

    fun setBrushButtonClick(v: View, ui: AnkoContext<MainActivity>) = with(ui) {
        v.onClick {
            alert {
                title(R.string.brushSizeDialogTitle)
                customView {
                    verticalLayout {
                        brushSizes.forEach {
                            button(it.toString()) {
                                tag = it
                                onClick {
                                    brushSize = this.tag.toString().toFloat()
                                    drawView.setBrushSize(brushSize)
//                                    info("new size: " + brushSize)

                                }
                            }
                        }
                    }
                    okButton { }

                }
            }.show()

        }
    }

    fun setNewButtonClick(v: View, ui: AnkoContext<MainActivity>) = with(ui) {
        v.onClick {
            alert {
                title(R.string.newPaintDialogTitle)
                message(R.string.newPaintDialogMsg)
                positiveButton(R.string.yes) { drawView.startNew() }
                negativeButton(R.string.no)
            }.show()

        }

    }

    fun setSaveButtonClick(v: View, ui: AnkoContext<MainActivity>) = with(ui) {
        v.onClick {
            alert {
                title(R.string.savePaintDialogTitle)
                message(R.string.savePaintDialogMsg)
                positiveButton(R.string.yes) {
                    if (drawView.save(ui.ctx)) toast(R.string.savedPaintMsg)
                    else toast(R.string.notSavedPaintMsg)
                }
                negativeButton(R.string.no)
            }.show()

        }

    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

        //TODO implementing mainLayout subcomponents in separate functions

        val mainLayout = verticalLayout {

            // Activity selection
            activityLayout = linearLayout {

                activityButtonsData.forEach {
                    activityButtons[it.description] = imageButton {
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


            drawView = drawingView {
                backgroundColor = 0xFFFFFF.opaque
            }.lparams {
                margin = dip(5)
                weight = 1F
            }

            // Color selection
            colorLayout = linearLayout {

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

        }.applyRecursively(customStyle)

        var currPaint = colorLayout.getChildAt(0)
        drawView.setColor(currPaint.tag.toString().toInt())
        drawView.setBrushSize(brushSize)

        val newButton = activityButtons["@string/start_new"]
        setNewButtonClick(newButton!!, ui)

        val brushButton = activityButtons["@string/brush"]
        setBrushButtonClick(brushButton!!, ui)

        val saveButton = activityButtons["@string/save"]
        setSaveButtonClick(saveButton!!, ui)

        mainLayout

    }


}



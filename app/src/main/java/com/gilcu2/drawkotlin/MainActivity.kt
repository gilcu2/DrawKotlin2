package com.gilcu2.drawkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data class MainButtonData(val image: Int, val description: String)

        val activityButtonsData = listOf(
                MainButtonData(R.drawable.new_pic, "@string/start_new"),
                MainButtonData(R.drawable.brush, "@string/brush"),
                MainButtonData(R.drawable.eraser, "@string/eraser"),
                MainButtonData(R.drawable.save, "@string/save"))

        val colorsData = listOf(0xFF0000, 0x00FF00, 0x0000FF, 0xFFAA00, 0, 0xFFFFFF)

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

            drawingView {
                backgroundColor = 0xFFFFFF.opaque
            }.lparams {
                margin = dip(5)
            }.lparams {
                weight = 1F
            }

            // Color selection
            linearLayout {

                colorsData.forEach {
                    imageButton {
                        backgroundColor = it.opaque
                        contentDescription = "@string/paint"
//                        imageResource=R.drawable.paint
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

        }
    }
}

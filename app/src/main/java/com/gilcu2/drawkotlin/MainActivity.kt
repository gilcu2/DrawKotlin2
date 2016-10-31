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

        val colorsData = listOf(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_green_light)

        verticalLayout {

            // Activity selection
            linearLayout {

                for (r in activityButtonsData)
                    imageButton {
                        contentDescription = r.description
                        imageResource = r.image
                    }.lparams {
                        height = matchParent
                    }

            }.lparams {
                height = dip(50)
                gravity = Gravity.CENTER
            }

            drawingView {
                backgroundColor = R.color.colorBackground.opaque
            }.lparams {
                margin = dip(5)
            }

            // Color selection
            linearLayout {

                colorsData.forEach {
                    imageButton {
                        backgroundColor = it.opaque
                    }.lparams {
                        height = matchParent
                    }
                }

            }

        }
    }
}

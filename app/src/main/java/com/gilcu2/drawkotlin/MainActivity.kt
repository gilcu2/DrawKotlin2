package com.gilcu2.drawkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val buttonsImage = listOf(R.drawable.new_pic, R.drawable.brush, R.drawable.eraser, R.drawable.save)

        verticalLayout {
            linearLayout {
                for (r in buttonsImage)
                    imageButton {
                        contentDescription = "@string/start_new"
                        imageResource = r
                    }
            }.lparams {
                height = dip(50)
                gravity = Gravity.CENTER
            }
        }
    }
}

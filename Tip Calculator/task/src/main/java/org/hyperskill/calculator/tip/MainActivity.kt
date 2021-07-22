package org.hyperskill.calculator.tip

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.slider.Slider
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tipInfo: TextView = findViewById(R.id.text_view)
        tipInfo.setText("")
        val slider: Slider = findViewById(R.id.slider)
        val input: EditText = findViewById(R.id.edit_text)
        slider.addOnChangeListener { slider, value, fromUser ->
            if (input.text.isNotEmpty()) {
                updateInfo(tipInfo, input, slider.value.toDouble())
            }
        }
        input.doAfterTextChanged {
            updateInfo(tipInfo, input, slider.value.toDouble())
        }
    }
    fun updateInfo(
        target: TextView,
        input: EditText,
        sliderValue: Double,
    ) {
        val res = BigDecimal(sliderValue * input.text.toString().toDouble() / 100).setScale(2, RoundingMode.DOWN)
        target.setText("Tip amount: $res")
    }
}
package com.example.bootcamp_odev5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bootcamp_odev5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var firstNumber = ""
    private var currentInput = StringBuilder()
    private var currentOperator: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numericButtons = arrayOf(
            binding.sifir, binding.bir, binding.iki,
            binding.uc, binding.dort, binding.bes,
            binding.alti, binding.yedi, binding.sekiz, binding.dokuz
        )

        for (button in numericButtons) {
            button.setOnClickListener { onNumericButtonClick(button) }
        }

        binding.toplama.setOnClickListener { onOperatorButtonClick('+') }
        binding.cikarma.setOnClickListener { onOperatorButtonClick('-') }
        binding.carpma.setOnClickListener { onOperatorButtonClick('*') }
        binding.bol.setOnClickListener { onOperatorButtonClick('/') }
        binding.temizleButton.setOnClickListener { onOperatorButtonClick('C') }

        binding.esittir.setOnClickListener { onEqualButtonClick() }
    }

    private fun onNumericButtonClick(button: Button) {
        currentInput.append(button.text)
        updateDisplay()
    }

    private fun onOperatorButtonClick(operator: Char) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toString()
            currentInput.clear()
            currentOperator = operator
            updateDisplay()
        }
    }

    private fun onEqualButtonClick() {
        if (currentInput.isNotEmpty() && currentOperator != null) {
            val secondNumber = currentInput.toString()
            val result = performCalculation(firstNumber.toDouble(), secondNumber.toDouble())
            firstNumber = result.toString()
            currentInput.clear()
            currentInput.append(result)
            currentOperator = null
            updateDisplay()
        }
    }

    private fun performCalculation(number1: Double, number2: Double): Double {
        return when (currentOperator) {
            '+' -> number1 + number2
            '-' -> number1 - number2
            '*' -> number1 * number2
            '/' -> if (number2 != 0.0) number1 / number2 else 0.0

            else -> 0.0
        }
    }

    private fun updateDisplay() {
        binding.sonuc.text = currentInput.toString()
    }
}
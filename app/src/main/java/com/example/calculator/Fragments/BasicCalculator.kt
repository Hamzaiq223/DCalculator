package com.example.calculator.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.calculator.R
import com.example.calculator.databinding.FragmentBasicCalculatorBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat


class BasicCalculator : Fragment() {

    private lateinit var binding : FragmentBasicCalculatorBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basic_calculator, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding.buttonClear.setOnClickListener {
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.buttonBracket.setOnClickListener {

            binding.input.text = addToInputText("(")

        }
        binding.buttonBracketR.setOnClickListener {

            binding.input.text = addToInputText(")")

        }

        binding.buttonCroxx.setOnClickListener {
            val removedLast = binding.input.text.toString().dropLast(1)
            binding.input.text = removedLast
        }

        binding.button0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }
        binding.button1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }
        binding.button2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }
        binding.button3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }
        binding.button4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }
        binding.button5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }
        binding.button6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }
        binding.button7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }
        binding.button8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }
        binding.button9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }
        binding.buttonDot.setOnClickListener {
            binding.input.text = addToInputText(".")
        }
        binding.buttonDivision.setOnClickListener {
            binding.input.text = addToInputText("÷") // ALT + 0247
        }
        binding.buttonMultiply.setOnClickListener {
            binding.input.text = addToInputText("×") // ALT + 0215
        }

        binding.buttonSubtraction.setOnClickListener {
            binding.input.text = addToInputText("-")
        }
        binding.buttonAddition.setOnClickListener {
            binding.input.text = addToInputText("+")
        }

        binding.buttonEquals.setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String): String {

        return binding.input.text.toString() + "" + buttonValue
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                binding.output.text = ""
                binding. output.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                // Show Result
                binding.output.text = DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        } catch (e: Exception) {
            // Show Error Message
            binding.output.text = ""
            binding.output.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        }
    }
}
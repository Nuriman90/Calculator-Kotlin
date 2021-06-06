package com.example.calculatorwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // Number Listeners
        btn_0.setOnClickListener {appendOnClick(true,"0") }
        btn_1.setOnClickListener {appendOnClick(true,"1") }
        btn_2.setOnClickListener {appendOnClick(true,"2") }
        btn_3.setOnClickListener {appendOnClick(true,"3") }
        btn_4.setOnClickListener {appendOnClick(true,"4") }
        btn_5.setOnClickListener {appendOnClick(true,"5") }
        btn_6.setOnClickListener {appendOnClick(true,"6") }
        btn_7.setOnClickListener {appendOnClick(true,"7") }
        btn_8.setOnClickListener {appendOnClick(true,"8") }
        btn_9.setOnClickListener {appendOnClick(true,"9") }
        btn_Dot.setOnClickListener {appendOnClick(true,".") }

    // Operator Listeners
        btn_Plus.setOnClickListener {appendOnClick(false,"+") }
        btn_Minus.setOnClickListener {appendOnClick(false,"-") }
        btn_Multiply.setOnClickListener {appendOnClick(false,"*") }
        btn_Divide.setOnClickListener {appendOnClick(false,"/") }
        btn_leftB.setOnClickListener {appendOnClick(false,"(") }
        btn_rightB.setOnClickListener {appendOnClick(false,")") }

        btn_Clean.setOnClickListener {
            tv_Input.text = ""
            clear()
        }

        btn_Percent.setOnClickListener {
            val string = tv_Input.text.toString()
            if (string.isNotEmpty()) {
                tv_Input.text = string.substring(0,string.length-1)
            }
        }

        btn_Equal.setOnClickListener {
            calculate()
        }
    }

    // Now Create Methods
    fun appendOnClick(clear: Boolean,string: String) {
        if(clear) {
            tv_Output.text = ""
            tv_Input.append(string)
        } else  {
            tv_Input.append(tv_Output.text)
            tv_Input.append(string)
            tv_Output.text = ""
        }
    }

    fun clear() {
        tv_Input.text = ""
        tv_Output.text = ""
    }

    private fun calculate() {
        try {
            //Now Import ExpressionBuilder Dependencies
            val input = ExpressionBuilder(tv_Input.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()) {
                tv_Output.text = longOutput.toString()
            } else{
                tv_Output.text = output.toString()
            }
        } catch (e:Exception) {
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }
}
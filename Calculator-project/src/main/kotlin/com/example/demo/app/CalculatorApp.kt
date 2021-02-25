package com.example.demo.app

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.KeyEvent
import javafx.scene.layout.VBox
import javafx.stage.Stage
import tornadofx.App
import tornadofx.*

class CalculatorApp : App(Calculator::class) {
    override fun start(stage: Stage) {
        importStylesheet("/style.css")
        stage.isResizable = false
        super.start(stage)
    }
}

class Calculator : View() {
    override val root: VBox by fxml("/CalculatorView.fxml")

    @FXML
    lateinit var displayAllOps: Label

    @FXML
    lateinit var display: Label
    private val displayValue: Double
        get() = when (display.text) {
            "" -> 0.0
            else -> display.text.toDouble()
        }

    var state: MutableList<Operator> = mutableListOf(Operator.Add(0.0))

    init {
        title = "Calculator"
        root.lookupAll(".button").forEach { b ->
            b.setOnMouseClicked {
                operator((b as Button).text)
            }
        }
        display.text = "0.0"
    }

    var operationsString: String = ""
    var isDisplayReady: Boolean = true

    private fun operator(text: String) {
        if (Regex("[0-9]").matches(text)) {
            if (!display.text.contains('.')) {
                display.text += text
            } else {
                val txtSplittedAtDot = display.text.split('.')
                val intValue = txtSplittedAtDot[0]
                val decimalValue = txtSplittedAtDot[1]

                val intValueToDisplay: String = if (intValue == "0") {
                    text
                } else {
                    intValue + text
                }
                display.text = "$intValueToDisplay.$decimalValue"
            }
        } else {
            when (text) {
                "+" -> {
                    if(!isDisplayReady){
                        operationsString += "+"
                        displayAllOps.text = operationsString
                        isDisplayReady=true
                    }else{
                        operationsString += "$displayValue+"
                        displayAllOps.text = operationsString
                    }
                    onAction(Operator.Add(displayValue))
                }

                "-" -> {

                    if(!isDisplayReady){
                        operationsString += "-"
                        displayAllOps.text = operationsString
                        isDisplayReady=true
                    }else{
                        operationsString += "$displayValue-"
                        displayAllOps.text = operationsString
                    }
                    onAction(Operator.Subtract(displayValue))
                }
                "*" -> {
                    if(!isDisplayReady){
                        operationsString += "*"
                        displayAllOps.text = operationsString
                        isDisplayReady=true
                    }else{
                        operationsString += "$displayValue*"
                        displayAllOps.text = operationsString
                    }
                    onAction(Operator.Multiply(displayValue))
                }
                "/" -> {
                    if(!isDisplayReady){
                        operationsString += "/"
                        displayAllOps.text = operationsString
                        isDisplayReady=true
                    }else{
                        operationsString += "$displayValue/"
                        displayAllOps.text = operationsString
                    }
                    onAction(Operator.Divide(displayValue))
                }
                "^" -> {
                    if(!isDisplayReady){
                        operationsString += "^"
                        displayAllOps.text = operationsString
                        isDisplayReady=true
                    }else{
                        operationsString += "$displayValue^"
                        displayAllOps.text = operationsString
                    }
                    onAction(Operator.Power(displayValue))
                }
                "sqrt" -> {
                    isDisplayReady=false
                    operationsString += "sqrt($displayValue)"
                    displayAllOps.text = operationsString
                    onAction(Operator.Sqrt(displayValue))
                }
                "C" -> {
                    operationsString=""
                    displayAllOps.text = operationsString
                    state.clear(); operator("=")
                }
                "=" -> {
                    operationsString=""
                    displayAllOps.text = operationsString
                    calculate(state)
                }
                "+/-" -> {
                    onAction(Operator.Invert(displayValue))
                }
            }
        }
    }

    private fun calculate(actions: MutableList<Operator>) {
        val orderedOperations = actions.sortedByDescending { a -> a.priority }
        var result = 0.0
        var tempDisplayValue = displayValue

        for (op in orderedOperations) {
            if (op == actions.last()) {
                result = op.calculate(tempDisplayValue)
                if (actions.size > 1) {
                    tempDisplayValue = result
                    actions.remove(op)
                }
            } else {
                val nextAction = actions[(actions.indexOf(op) + 1)]
                result = op.calculate(nextAction.x)
                nextAction.x = result
                actions.remove(op)

            }
        }
        display.text = result.toString()
        state.clear()
    }

    private fun onAction(fn: Operator) {
        state.add(fn)
        display.text = ""
    }
}

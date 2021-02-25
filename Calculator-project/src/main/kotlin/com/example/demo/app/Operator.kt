package com.example.demo.app

import kotlin.math.pow
import kotlin.math.sqrt

sealed class Operator(var x: Double) {
    abstract fun calculate(y: Double): Double
    abstract var priority: Int

    class Add(x: Double) : Operator(x) {
        override var priority = 0
        override fun calculate(y: Double): Double = x + y
    }

    class Subtract(x: Double) : Operator(x) {
        override var priority = 0
        override fun calculate(y: Double): Double = x - y
    }

    class Multiply(x: Double) : Operator(x) {
        override var priority = 1
        override fun calculate(y: Double): Double = x * y
    }

    class Divide(x: Double) : Operator(x) {
        override var priority = 1
        override fun calculate(y: Double): Double = x / y
    }

    class Power(x: Double) : Operator(x) {
        override var priority = 2
        override fun calculate(y: Double): Double = x.pow(y)
    }

    class Sqrt(x: Double) : Operator(x) {
        override var priority = 2
        override fun calculate(y: Double): Double = sqrt(x)
    }

    class Invert(x: Double) : Operator(x) {
        override var priority = 2
        override fun calculate(y: Double): Double = -1.0 * x
    }
}

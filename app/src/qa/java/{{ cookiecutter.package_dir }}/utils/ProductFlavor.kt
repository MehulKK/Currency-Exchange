package com.macv.currencyconverter.utils

object ProductFlavor {
    val CURRENT: Flavor = Flavor.QA

    sealed class Flavor(val name: String) {
        object DEV : Flavor("DEV")

        object QA : Flavor("QA")

        object PRODUCTION : Flavor("PRODUCTION")

        override fun toString(): String = name
    }
}

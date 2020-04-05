package com.jojartbence.helpers

object LicensePlateNumberValidityChecker {

    private val regex = "[A-Z][A-Z][A-Z]-[0-9][0-9][0-9]".toRegex()

    fun isValidLicensePlateNumber(text: String): Boolean {
        return regex.matches(text)
    }
}
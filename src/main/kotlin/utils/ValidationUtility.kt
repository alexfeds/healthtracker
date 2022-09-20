package utils

import java.util.regex.Pattern

object Utils {
    fun getAndValidateGender(): Char {
        var gender: Char
        do {
            print("Gender:")
            gender = readLine()?.getOrNull(0) ?: ' '.uppercaseChar()
            when (gender) {
                !in listOf('F', 'M') -> println("Valid Options are F and M")
                in listOf('F', 'M') -> return gender
                else -> print("Invalid option")
            }
        } while (gender !in listOf('F', 'M'))
        return gender
    }

    fun getAndValidateName(): String {
        print("Name:")
        val result = readLine()!!
        val isValid: Boolean = checkIfVarIsEmpty(result)
        if (isValid) {
            return result
        } else {
            getAndValidateName()
        }
        return result
    }

    fun getAndValidateEmail(): String {
        print("Email:")
        val result = readLine()!!
        val isNotEmptyInput: Boolean = checkIfVarIsEmpty(result)

        val isValidEmail: Boolean = isValidEmail(result)
        if (isNotEmptyInput && isValidEmail) {
            return result
        } else {
            getAndValidateEmail()
        }
        return result
    }

    private fun checkIfVarIsEmpty(input: Any): Boolean {
        if (input == null || input == "") {
            println("Cannot leave empty")
            return false
        } else {
            return true
        }
    }

    private fun isValidEmail(input: String): Boolean {
        val isValid = EMAIL_ADDRESS_PATTERN.matcher(input).matches()
        if (!isValid) {
            println("Not correct email format")
            return false
        } else {
            return true
        }

    }

    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

}
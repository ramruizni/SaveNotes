package com.example.savenotes.domain.login.usecases

import java.util.regex.Pattern

class ValidateEmail {

    private val EMAIL_PATTERN =
        "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"


    //En 'isValidEmail' se compara el pattern de EMAIL_PATTERN con el matcher de 'email', arrojando
    //'true si hay coincidecia o 'flase' si no la hay
    private fun isValidEmail(email: String) = Pattern.compile(EMAIL_PATTERN).matcher(email).matches()

    operator fun invoke(email: String): String? {
        if (email.isBlank()) {
            return "The email can't be blank"
        }

        //'!isValidEmail(email)' significa que 'isValidEmail(email)' devuelve 'false'
        if (!isValidEmail(email)) {
            return "That's not a valid email"
        }

        return null
    }

}
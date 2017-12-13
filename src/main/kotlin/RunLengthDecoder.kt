object RunLengthDecoder {
    operator fun invoke(s: String) = s.let {
        when {
            it.isEmpty() -> ""
            else -> decode(it)
        }
    }

    private fun `decode`(s: String): String {
        var currentCharIndex = 0
        var duplicationValue = 1
        val decodedChars: MutableList<Char> = mutableListOf()
        while (currentCharIndex < s.length) {
            val currentChar = s[currentCharIndex]
            val lastChar = lastChar(s, currentCharIndex)
            if (currentChar.isDigit()) {
                duplicationValue = foundValue(lastChar, currentChar)
            }
            if (currentChar.isLetter() || currentChar.isWhitespace()) {
                storeDecodedChars(duplicationValue, decodedChars, currentChar)
                duplicationValue = 1
            }
            currentCharIndex++
        }
        return decodedChars.joinToString("")
    }

    private fun storeDecodedChars(duplicationValue: Int, decodedChars: MutableList<Char>, currentChar: Char) {
        (1..duplicationValue).forEach {
            decodedChars.add(currentChar)
        }
    }

    private fun foundValue(lastChar: Char?, currentChar: Char) =
            when {
                lastChar == null || lastChar.isNonNumerical() -> Integer.parseInt(currentChar.toString())
                lastChar.isDigit() -> Integer.parseInt("$lastChar$currentChar")
                else -> error("not sure what to do with $lastChar")
            }

    private fun Char.isNonNumerical() = this.isLetter() || this.isWhitespace()

    private fun lastChar(s: String, currentCharIndex: Int) = when (currentCharIndex) {
        0 -> null
        else -> s[currentCharIndex - 1]
    }
}
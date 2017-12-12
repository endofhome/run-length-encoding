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
                when {
                    lastChar == null || lastChar.isLetter() -> duplicationValue = Integer.parseInt(currentChar.toString())
                    lastChar.isDigit() -> duplicationValue = Integer.parseInt("$lastChar$currentChar")
                }
            }
            if (currentChar.isLetter()) {
                (1..duplicationValue).forEach {
                    decodedChars.add(currentChar)
                }
                duplicationValue = 1
            }
            currentCharIndex++
        }
        return decodedChars.joinToString("")
    }

    private fun lastChar(s: String, currentCharIndex: Int) = when (currentCharIndex) {
        0 -> null
        else -> s[currentCharIndex - 1]
    }
}
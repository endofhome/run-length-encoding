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
            if (currentChar.isDigit()) {
                duplicationValue = Integer.parseInt(currentChar.toString())
            }
            if (currentChar.isLetter()) {
                (1..duplicationValue).forEach {
                    decodedChars.add(currentChar)
                }
            }
            currentCharIndex++
        }
        return decodedChars.joinToString("")
    }
}
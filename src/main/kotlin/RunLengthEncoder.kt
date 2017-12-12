object RunLengthEncoder {
    operator fun invoke(string: String) = string.let {
        when {
            it.isEmpty() -> ""
            else -> encode(it)
        }
    }

    fun encode(s: String): String {
        var currentCharIndex = 0
        var selectedCharIndex = 0
        val encodedPairs: MutableList<Pair<Int, Char>> = mutableListOf()
        while (moreCharacters(currentCharIndex, s)) {
            var count = 0
            while (moreCharacters(selectedCharIndex, s) && characterMatches(s, selectedCharIndex, currentCharIndex)) {
                count++
                selectedCharIndex++
            }
            if (count > 0) {
                encodedPairs.add(count to s[currentCharIndex])
            }
            currentCharIndex++
        }
        return stringPresentationOf(encodedPairs)
    }

    private fun moreCharacters(currentCharIndex: Int, s: String) = currentCharIndex < s.length

    private fun characterMatches(s: String, selectedCharIndex: Int, currentCharIndex: Int) =
            s[selectedCharIndex] == s[currentCharIndex]

    private fun Int.ifNotOne() =
        when (this) {
            1 -> ""
            else -> this.toString()
        }

    private fun stringPresentationOf(encodedPairs: MutableList<Pair<Int, Char>>): String {
        return encodedPairs
                .map { "${it.first.ifNotOne()}${it.second}" }
                .joinToString("")
    }
}

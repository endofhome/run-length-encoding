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
        while (currentCharIndex < s.length) {
            var count = 0
            while (selectedCharIndex < s.length && s[selectedCharIndex] == s[currentCharIndex]) {
                count++
                selectedCharIndex++
            }
            if (count > 0) {
                encodedPairs.add(count to s[currentCharIndex])
            }
            currentCharIndex++
        }
        return encodedPairs
                .map { "${it.first.ifNotOne()}${it.second}" }
                .joinToString("")
    }

    private fun Int.ifNotOne() =
        when (this) {
            1 -> ""
            else -> this.toString()
        }
}

object RunLengthDecoder {
    operator fun invoke(s: String) = ""
}
object RunLengthEncoding {
    operator fun invoke(string: String) = string.let {
        when {
            it.isEmpty() -> ""
            else -> encode(it)
        }
    }

    fun encode(s: String): String {
        var currentCharIndex = 0
        var selectedCharIndex= 0
        val encodedPairs: MutableList<Pair<Int, Char>> = mutableListOf()
        while (currentCharIndex < s.length) {
            var count = 0
            while (selectedCharIndex < s.length && s[selectedCharIndex] == s[currentCharIndex]) {
                count ++
                selectedCharIndex ++
            }
            encodedPairs.add(count to s[currentCharIndex])
            currentCharIndex ++
        }
        return encodedPairs
                .map { "${countIfNotZeroOrOne(it.first)}${charIfNotZero(it.first, it.second)}" }
                .joinToString("")
    }

    private fun charIfNotZero(count: Int, char: Char) = count.let {
        when (count) {
            0 -> ""
            else -> char.toString()
        }
    }

    private fun countIfNotZeroOrOne(count: Int) = count.let {
        when (count) {
            0, 1 -> ""
            else -> count.toString()
        }
    }
}
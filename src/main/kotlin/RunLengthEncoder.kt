object RunLengthEncoder {
    operator fun invoke(s: String) = s.let {
        when {
            it.isEmpty() -> ""
            else -> encode(it, "")
        }
    }

    private fun encode(s: String, processed: String): String {
        val nextChar = s.find { it != s.first() }
        val partitionedString: Partition = nextChar?.let { next ->
            val nextCharIndex = s.indexOfFirst { it == next }
            val repeated = s.subSequence(0, nextCharIndex)
            val remainder = s.subSequence(nextCharIndex, s.lastIndex + 1)
            Partition(repeated, remainder)
        } ?: Partition(s, "")

        val numberOfRepeats = partitionedString.repeatedChars.length.display()
        val character = partitionedString.repeatedChars.first()
        val encoded = "$processed$numberOfRepeats$character"
        val remainderExists = partitionedString.remainder.isNotEmpty()
        return when {
            remainderExists -> encode(partitionedString.remainder.toString(), encoded)
            else -> encoded
        }
    }

    private fun Int.display() = when (this) {
        0, 1 -> ""
        else -> "$this"
    }

    data class Partition(val repeatedChars: CharSequence, val remainder: CharSequence)
}

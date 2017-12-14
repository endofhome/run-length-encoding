object RunLengthEncoder {
    operator fun invoke(s: String) = s.let {
        when {
            it.isEmpty() -> ""
            else -> encode(it, "")
        }
    }

    private fun encode(s: String, processed: String): String {
        val partitionedString = partition(s)
        val remainder = partitionedString.remainder
        val numberOfRepeats = partitionedString.repeatedChars.length.display()
        val characterToRepeat = partitionedString.repeatedChars.first()
        val encoded = "$processed$numberOfRepeats$characterToRepeat"
        return when {
            remainder.isNotEmpty() -> encode(remainder.toString(), encoded)
            else -> encoded
        }
    }

    private fun partition(s: String): Partition {
        val nextChar = s.find { it != s.first() }
        return nextChar?.let { next ->
            val nextCharIndex = s.indexOfFirst { it == next }
            val repeated = s.subSequence(0, nextCharIndex)
            val remainder = s.subSequence(nextCharIndex, s.lastIndex + 1)
            Partition(repeated, remainder)
        } ?: Partition(s, "")
    }

    private fun Int.display() = when (this) {
        0, 1 -> ""
        else -> "$this"
    }

    data class Partition(val repeatedChars: CharSequence, val remainder: CharSequence)
}

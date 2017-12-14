object RunLengthDecoder {
    operator fun invoke(s: String) = s.let {
        when {
            it.isEmpty() -> ""
            else -> decode(it)
        }
    }

    private fun decode(s: String, processed: String = ""): String {
        val partitionedString = partition(s)
        val remainder = partitionedString.remainder
        val times = repeatsFor(partitionedString.repeatedChars)
        val characterToRepeat = partitionedString.repeatedChars.first { !it.isDigit() }
        val repeatedChar = (1..times).map { characterToRepeat }
                                     .joinToString("")
        val decoded = "$processed$repeatedChar"

        return when {
            remainder.isNotEmpty() -> decode(remainder.toString(), decoded)
            else                   -> decoded
        }
    }

    private fun partition(s: String): Partition {
        val nextNonDigit = s.find { !it.isDigit() }

        return nextNonDigit?.let {
            val nextEncodedCharIndex = s.indexOf(it) + 1
            val encodedChar = s.subSequence(0, nextEncodedCharIndex)
            val remainder = s.subSequence(nextEncodedCharIndex, s.length)
            Partition(encodedChar, remainder)
        } ?: Partition(s, "")
    }

    private fun repeatsFor(repeatedChars: CharSequence): Int {
        val stringRepeats = repeatedChars.filter { it.isDigit() }
                                         .toList()
                                         .joinToString("")
        return when {
            stringRepeats.isEmpty() -> 1
            else                    -> Integer.parseInt(stringRepeats)
        }
    }
}
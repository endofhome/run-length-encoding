object RunLengthDecoder {
    operator fun invoke(s: String) = s.let {
        when {
            it.isEmpty() -> ""
            else -> it
        }
    }
}
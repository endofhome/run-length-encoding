object RunLengthEncoding {
    operator fun invoke(string: String) = string.let {
        when {
            it.isEmpty() -> ""
            else -> it
        }
    }
}
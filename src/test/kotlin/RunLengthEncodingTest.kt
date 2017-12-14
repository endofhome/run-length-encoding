import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RunLengthEncodingTest {

    @Test
    fun `encode an empty string`() {
        assertThat(RunLengthEncoder(""), equalTo(""))
    }

    @Test
    fun `encode single characters without count`() {
        assertThat(RunLengthEncoder("XYZ"), equalTo("XYZ"))
    }

    @Test
    fun `encode string with no single characters`() {
        assertThat(RunLengthEncoder("AABBBCCCC"), equalTo("2A3B4C"))
    }

    @Test
    fun `encode string with single and mixed characters`() {
        assertThat(RunLengthEncoder("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB"), equalTo("12WB12W3B24WB"))
    }

    @Test
    fun `encode string with whitespace characters mixed in it`() {
        assertThat(RunLengthEncoder("  hsqq qww  "), equalTo("2 hs2q q2w2 "))
    }

    @Test
    fun `encode string with lowercase characters`() {
        assertThat(RunLengthEncoder("aabbbcccc"), equalTo("2a3b4c"))
    }

    @Test
    fun `decode empty string`() {
        assertThat(RunLengthDecoder(""), equalTo(""))
    }

    @Test
    fun `decode string with single characters only`() {
        assertThat(RunLengthDecoder("XYZ"), equalTo("XYZ"))
    }

    @Test
    fun `decode string with no single characters`() {
        assertThat(RunLengthDecoder("2A3B4C"), equalTo("AABBBCCCC"))
    }

    @Test
    fun `decode string with single and repeated characters`() {
        assertThat(RunLengthDecoder("12WB12W3B24WB"), equalTo("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB"))
    }

    @Test
    fun `decode string with lowercase characters`() {
        assertThat(RunLengthDecoder("2a3b4c"), equalTo("aabbbcccc"))
    }

    @Test
    fun `decode string with mixed whitespace characters in it`() {
        assertThat(RunLengthDecoder("2 hs2q q2w2 "), equalTo("  hsqq qww  "))
    }

    @Test
    fun `Encode a string and then decode it - should return the same one`() {
        val originalString = "zzz ZZ  zZ"
        val encoded = RunLengthEncoder(originalString)
        val decoded = RunLengthDecoder(encoded)
        assertThat(decoded, equalTo(originalString))
    }
}

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RunLengthEncodingTest {

    @Test
    fun `encode an empty string`() {
        assertThat(RunLengthEncoding(""), equalTo(""))
    }

    @Test
    fun `encode single characters without count`() {
        assertThat(RunLengthEncoding("XYZ"), equalTo("XYZ"))
    }

    @Test
    fun `encode string with no single characters`() {
        assertThat(RunLengthEncoding("AABBBCCCC"), equalTo("2A3B4C"))
    }

    @Test
    fun `encode string with single and mixed characters`() {
        assertThat(RunLengthEncoding("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB"), equalTo("12WB12W3B24WB"))
    }

    @Test
    fun `encode string with whitespace characters mixed in it`() {
        assertThat(RunLengthEncoding("  hsqq qww  "), equalTo("2 hs2q q2w2 "))
    }
}

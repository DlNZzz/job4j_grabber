import junit.framework.TestCase;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    @Test
    public void testBB() {
        int a = new Solution().bb();
        assertThat(a, is(2));
    }
}
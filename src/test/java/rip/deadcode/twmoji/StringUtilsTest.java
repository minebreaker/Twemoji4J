package rip.deadcode.twmoji;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {

    @Test
    public void testReplace1() {

        String res = StringUtils.replace("abc", Pattern.compile("b"), String::toUpperCase);
        assertThat(res, is("aBc"));
    }

    @Test
    public void testReplace2() {

        String res = StringUtils.replace("a1b2c3", Pattern.compile("[0-9]"), s -> Integer.toString(Integer.parseInt(s) + 10));
        assertThat(res, is("a11b12c13"));
    }

}
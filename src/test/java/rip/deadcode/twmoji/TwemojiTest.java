package rip.deadcode.twmoji;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TwemojiTest {

    @Test
    public void testParse() {
        String ret = Twemoji.parse("I \u2764\uFE0F emoji!");
        assertThat(
                ret,
                is("I <img class=\"emoji\" draggable=\"false\" alt=\"\u2764\uFE0F\" src=\"https://twemoji.maxcdn.com/2/72x72/2764.png\"/> emoji!")
        );
        ret = Twemoji.parse("\u2764\uFE0F", new TwemojiOption.Builder().attributes(ImmutableMap.of("a", "<", "b", ">")).build());
        assertThat(
                ret,
                is("<img class=\"emoji\" draggable=\"false\" alt=\"\u2764\uFE0F\" " +
                   "src=\"https://twemoji.maxcdn.com/2/72x72/2764.png\" a=\"&lt;\" b=\"&gt;\"/>")
        );
    }

    @Test
    public void testFromCodePoint() {
        String ret = Twemoji.fromCodePoint(127464);
        assertThat(ret, is("\ud83c\udde8"));
    }

    @Test
    public void testToCodePoint() {
        int[] ret = Twemoji.toCodePoint("\ud83c\udde8\ud83c\uddf3");  // surrogate pairs
        assertThat(ret, is(new int[] { 127464, 127475 }));  // 1f1e8-1f1f3
        ret = Twemoji.toCodePoint("ABC");  // usual text
        assertThat(ret, is(new int[] { 65, 66, 67 }));
        ret = Twemoji.toCodePoint("AB\ud83c\udde8\ud83c\uddf3C");
        assertThat(ret, is(new int[] { 65, 66, 127464, 127475, 67 }));
    }

}

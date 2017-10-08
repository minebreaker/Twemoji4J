package rip.deadcode.twmoji;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    /**
     * Replace given string to the result of the replacer using the pattern.
     *
     * @param original String to replace
     * @param pattern  {@link Pattern} used to match
     * @param replacer Function to replace the matching
     * @return Replaced string
     */
    public static String replace(String original, Pattern pattern, Function<String, String> replacer) {

        Matcher matcher = pattern.matcher(original);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String emoji = original.substring(start, end);
            String replaceTo = replacer.apply(emoji);
            matcher.appendReplacement(sb, replaceTo);
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

}

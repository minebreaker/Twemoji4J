package rip.deadcode.twmoji;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public final class Twemoji {

    /**
     * RegExp based on emoji's official Unicode standards
     * <a href="http://www.unicode.org/Public/UNIDATA/EmojiSources.txt">http://www.unicode.org/Public/UNIDATA/EmojiSources.txt</a>
     */
    private static final Pattern RE = Pattern.compile(
            "\\ud83d[\\udc68-\\udc69](?:\\ud83c[\\udffb-\\udfff])?\\u200d(?:\\u2695\\ufe0f|\\u2696\\ufe0f|\\u2708\\ufe0f|\\ud83c[\\udf3e\\udf73\\udf93\\udfa4\\udfa8\\udfeb\\udfed]|\\ud83d[\\udcbb\\udcbc\\udd27\\udd2c\\ude80\\ude92])|(?:\\ud83c[\\udfcb\\udfcc]|\\ud83d\\udd75|\\u26f9)(?:\\ufe0f|\\ud83c[\\udffb-\\udfff])\\u200d[\\u2640\\u2642]\\ufe0f|(?:\\ud83c[\\udfc3\\udfc4\\udfca]|\\ud83d[\\udc6e\\udc71\\udc73\\udc77\\udc81\\udc82\\udc86\\udc87\\ude45-\\ude47\\ude4b\\ude4d\\ude4e\\udea3\\udeb4-\\udeb6]|\\ud83e[\\udd26\\udd37-\\udd39\\udd3d\\udd3e\\uddd6-\\udddd])(?:\\ud83c[\\udffb-\\udfff])?\\u200d[\\u2640\\u2642]\\ufe0f|\\ud83d\\udc68\\u200d\\u2764\\ufe0f\\u200d\\ud83d\\udc8b\\u200d\\ud83d\\udc68|\\ud83d\\udc68\\u200d\\ud83d\\udc68\\u200d\\ud83d\\udc66\\u200d\\ud83d\\udc66|\\ud83d\\udc68\\u200d\\ud83d\\udc68\\u200d\\ud83d\\udc67\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc68\\u200d\\ud83d\\udc69\\u200d\\ud83d\\udc66\\u200d\\ud83d\\udc66|\\ud83d\\udc68\\u200d\\ud83d\\udc69\\u200d\\ud83d\\udc67\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc69\\u200d\\u2764\\ufe0f\\u200d\\ud83d\\udc8b\\u200d\\ud83d[\\udc68\\udc69]|\\ud83d\\udc69\\u200d\\ud83d\\udc69\\u200d\\ud83d\\udc66\\u200d\\ud83d\\udc66|\\ud83d\\udc69\\u200d\\ud83d\\udc69\\u200d\\ud83d\\udc67\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc68\\u200d\\u2764\\ufe0f\\u200d\\ud83d\\udc68|\\ud83d\\udc68\\u200d\\ud83d\\udc66\\u200d\\ud83d\\udc66|\\ud83d\\udc68\\u200d\\ud83d\\udc67\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc68\\u200d\\ud83d\\udc68\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc68\\u200d\\ud83d\\udc69\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc69\\u200d\\u2764\\ufe0f\\u200d\\ud83d[\\udc68\\udc69]|\\ud83d\\udc69\\u200d\\ud83d\\udc66\\u200d\\ud83d\\udc66|\\ud83d\\udc69\\u200d\\ud83d\\udc67\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc69\\u200d\\ud83d\\udc69\\u200d\\ud83d[\\udc66\\udc67]|\\ud83c\\udff3\\ufe0f\\u200d\\ud83c\\udf08|\\ud83c\\udff4\\u200d\\u2620\\ufe0f|\\ud83d\\udc41\\u200d\\ud83d\\udde8|\\ud83d\\udc68\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc69\\u200d\\ud83d[\\udc66\\udc67]|\\ud83d\\udc6f\\u200d\\u2640\\ufe0f|\\ud83d\\udc6f\\u200d\\u2642\\ufe0f|\\ud83e\\udd3c\\u200d\\u2640\\ufe0f|\\ud83e\\udd3c\\u200d\\u2642\\ufe0f|\\ud83e\\uddde\\u200d\\u2640\\ufe0f|\\ud83e\\uddde\\u200d\\u2642\\ufe0f|\\ud83e\\udddf\\u200d\\u2640\\ufe0f|\\ud83e\\udddf\\u200d\\u2642\\ufe0f|(?:[\\u0023\\u002a\\u0030-\\u0039])\\ufe0f?\\u20e3|(?:(?:\\ud83c[\\udfcb\\udfcc]|\\ud83d[\\udd74\\udd75\\udd90]|[\\u261d\\u26f7\\u26f9\\u270c\\u270d])(?:\\ufe0f|(?!\\ufe0e))|\\ud83c[\\udf85\\udfc2-\\udfc4\\udfc7\\udfca]|\\ud83d[\\udc42\\udc43\\udc46-\\udc50\\udc66-\\udc69\\udc6e\\udc70-\\udc78\\udc7c\\udc81-\\udc83\\udc85-\\udc87\\udcaa\\udd7a\\udd95\\udd96\\ude45-\\ude47\\ude4b-\\ude4f\\udea3\\udeb4-\\udeb6\\udec0\\udecc]|\\ud83e[\\udd18-\\udd1c\\udd1e\\udd1f\\udd26\\udd30-\\udd39\\udd3d\\udd3e\\uddd1-\\udddd]|[\\u270a\\u270b])(?:\\ud83c[\\udffb-\\udfff]|)|\\ud83c\\udff4\\udb40\\udc67\\udb40\\udc62\\udb40\\udc65\\udb40\\udc6e\\udb40\\udc67\\udb40\\udc7f|\\ud83c\\udff4\\udb40\\udc67\\udb40\\udc62\\udb40\\udc73\\udb40\\udc63\\udb40\\udc74\\udb40\\udc7f|\\ud83c\\udff4\\udb40\\udc67\\udb40\\udc62\\udb40\\udc77\\udb40\\udc6c\\udb40\\udc73\\udb40\\udc7f|\\ud83c\\udde6\\ud83c[\\udde8-\\uddec\\uddee\\uddf1\\uddf2\\uddf4\\uddf6-\\uddfa\\uddfc\\uddfd\\uddff]|\\ud83c\\udde7\\ud83c[\\udde6\\udde7\\udde9-\\uddef\\uddf1-\\uddf4\\uddf6-\\uddf9\\uddfb\\uddfc\\uddfe\\uddff]|\\ud83c\\udde8\\ud83c[\\udde6\\udde8\\udde9\\uddeb-\\uddee\\uddf0-\\uddf5\\uddf7\\uddfa-\\uddff]|\\ud83c\\udde9\\ud83c[\\uddea\\uddec\\uddef\\uddf0\\uddf2\\uddf4\\uddff]|\\ud83c\\uddea\\ud83c[\\udde6\\udde8\\uddea\\uddec\\udded\\uddf7-\\uddfa]|\\ud83c\\uddeb\\ud83c[\\uddee-\\uddf0\\uddf2\\uddf4\\uddf7]|\\ud83c\\uddec\\ud83c[\\udde6\\udde7\\udde9-\\uddee\\uddf1-\\uddf3\\uddf5-\\uddfa\\uddfc\\uddfe]|\\ud83c\\udded\\ud83c[\\uddf0\\uddf2\\uddf3\\uddf7\\uddf9\\uddfa]|\\ud83c\\uddee\\ud83c[\\udde8-\\uddea\\uddf1-\\uddf4\\uddf6-\\uddf9]|\\ud83c\\uddef\\ud83c[\\uddea\\uddf2\\uddf4\\uddf5]|\\ud83c\\uddf0\\ud83c[\\uddea\\uddec-\\uddee\\uddf2\\uddf3\\uddf5\\uddf7\\uddfc\\uddfe\\uddff]|\\ud83c\\uddf1\\ud83c[\\udde6-\\udde8\\uddee\\uddf0\\uddf7-\\uddfb\\uddfe]|\\ud83c\\uddf2\\ud83c[\\udde6\\udde8-\\udded\\uddf0-\\uddff]|\\ud83c\\uddf3\\ud83c[\\udde6\\udde8\\uddea-\\uddec\\uddee\\uddf1\\uddf4\\uddf5\\uddf7\\uddfa\\uddff]|\\ud83c\\uddf4\\ud83c\\uddf2|\\ud83c\\uddf5\\ud83c[\\udde6\\uddea-\\udded\\uddf0-\\uddf3\\uddf7-\\uddf9\\uddfc\\uddfe]|\\ud83c\\uddf6\\ud83c\\udde6|\\ud83c\\uddf7\\ud83c[\\uddea\\uddf4\\uddf8\\uddfa\\uddfc]|\\ud83c\\uddf8\\ud83c[\\udde6-\\uddea\\uddec-\\uddf4\\uddf7-\\uddf9\\uddfb\\uddfd-\\uddff]|\\ud83c\\uddf9\\ud83c[\\udde6\\udde8\\udde9\\uddeb-\\udded\\uddef-\\uddf4\\uddf7\\uddf9\\uddfb\\uddfc\\uddff]|\\ud83c\\uddfa\\ud83c[\\udde6\\uddec\\uddf2\\uddf3\\uddf8\\uddfe\\uddff]|\\ud83c\\uddfb\\ud83c[\\udde6\\udde8\\uddea\\uddec\\uddee\\uddf3\\uddfa]|\\ud83c\\uddfc\\ud83c[\\uddeb\\uddf8]|\\ud83c\\uddfd\\ud83c\\uddf0|\\ud83c\\uddfe\\ud83c[\\uddea\\uddf9]|\\ud83c\\uddff\\ud83c[\\udde6\\uddf2\\uddfc]|\\ud800\\udc00|\\ud83c[\\udccf\\udd8e\\udd91-\\udd9a\\udde6-\\uddff\\ude01\\ude32-\\ude36\\ude38-\\ude3a\\ude50\\ude51\\udf00-\\udf20\\udf2d-\\udf35\\udf37-\\udf7c\\udf7e-\\udf84\\udf86-\\udf93\\udfa0-\\udfc1\\udfc5\\udfc6\\udfc8\\udfc9\\udfcf-\\udfd3\\udfe0-\\udff0\\udff4\\udff8-\\udfff]|\\ud83d[\\udc00-\\udc3e\\udc40\\udc44\\udc45\\udc51-\\udc65\\udc6a-\\udc6d\\udc6f\\udc79-\\udc7b\\udc7d-\\udc80\\udc84\\udc88-\\udca9\\udcab-\\udcfc\\udcff-\\udd3d\\udd4b-\\udd4e\\udd50-\\udd67\\udda4\\uddfb-\\ude44\\ude48-\\ude4a\\ude80-\\udea2\\udea4-\\udeb3\\udeb7-\\udebf\\udec1-\\udec5\\uded0-\\uded2\\udeeb\\udeec\\udef4-\\udef8]|\\ud83e[\\udd10-\\udd17\\udd1d\\udd20-\\udd25\\udd27-\\udd2f\\udd3a\\udd3c\\udd40-\\udd45\\udd47-\\udd4c\\udd50-\\udd6b\\udd80-\\udd97\\uddc0\\uddd0\\uddde-\\udde6]|[\\u23e9-\\u23ec\\u23f0\\u23f3\\u2640\\u2642\\u2695\\u26ce\\u2705\\u2728\\u274c\\u274e\\u2753-\\u2755\\u2795-\\u2797\\u27b0\\u27bf\\ue50a]|(?:\\ud83c[\\udc04\\udd70\\udd71\\udd7e\\udd7f\\ude02\\ude1a\\ude2f\\ude37\\udf21\\udf24-\\udf2c\\udf36\\udf7d\\udf96\\udf97\\udf99-\\udf9b\\udf9e\\udf9f\\udfcd\\udfce\\udfd4-\\udfdf\\udff3\\udff5\\udff7]|\\ud83d[\\udc3f\\udc41\\udcfd\\udd49\\udd4a\\udd6f\\udd70\\udd73\\udd76-\\udd79\\udd87\\udd8a-\\udd8d\\udda5\\udda8\\uddb1\\uddb2\\uddbc\\uddc2-\\uddc4\\uddd1-\\uddd3\\udddc-\\uddde\\udde1\\udde3\\udde8\\uddef\\uddf3\\uddfa\\udecb\\udecd-\\udecf\\udee0-\\udee5\\udee9\\udef0\\udef3]|[\\u00a9\\u00ae\\u203c\\u2049\\u2122\\u2139\\u2194-\\u2199\\u21a9\\u21aa\\u231a\\u231b\\u2328\\u23cf\\u23ed-\\u23ef\\u23f1\\u23f2\\u23f8-\\u23fa\\u24c2\\u25aa\\u25ab\\u25b6\\u25c0\\u25fb-\\u25fe\\u2600-\\u2604\\u260e\\u2611\\u2614\\u2615\\u2618\\u2620\\u2622\\u2623\\u2626\\u262a\\u262e\\u262f\\u2638-\\u263a\\u2648-\\u2653\\u2660\\u2663\\u2665\\u2666\\u2668\\u267b\\u267f\\u2692-\\u2694\\u2696\\u2697\\u2699\\u269b\\u269c\\u26a0\\u26a1\\u26aa\\u26ab\\u26b0\\u26b1\\u26bd\\u26be\\u26c4\\u26c5\\u26c8\\u26cf\\u26d1\\u26d3\\u26d4\\u26e9\\u26ea\\u26f0-\\u26f5\\u26f8\\u26fa\\u26fd\\u2702\\u2708\\u2709\\u270f\\u2712\\u2714\\u2716\\u271d\\u2721\\u2733\\u2734\\u2744\\u2747\\u2757\\u2763\\u2764\\u27a1\\u2934\\u2935\\u2b05-\\u2b07\\u2b1b\\u2b1c\\u2b50\\u2b55\\u3030\\u303d\\u3297\\u3299])(?:\\ufe0f|(?!\\ufe0e))");

    private static final Pattern RESCAPER = Pattern.compile("[&<>'\"]");

    public static String parse(String text) {
        return parse(text, TwemojiOption.DEFAULT);
    }

    public static String parse(String text, TwemojiOption options) {
        return replace(text, raw -> {
            String iconId = grabIcon(raw);
            String src = options.getCallback().apply(iconId, options);

            if (Strings.isNullOrEmpty(src)) {
                return raw;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("<img ")
                  .append("class=\"").append(options.getClassName()).append("\" ")
                  .append("draggable=\"false\" ")
                  .append("alt=\"").append(raw).append("\" ")
                  .append("src=\"").append(src).append("\"");
                Map<String, String> attributes = options.getAttributes().apply(raw, iconId);
                for (Map.Entry<String, String> attributePair : attributes.entrySet()) {
                    if (!attributePair.getKey().contains("on") && sb.indexOf(attributePair.getKey() + "=") == -1) {
                        sb.append(" ")
                          .append(attributePair.getKey())
                          .append("=")
                          .append("\"").append(escapeHtml(attributePair.getValue())).append("\"");
                    }
                }
                sb.append("/>");
                return sb.toString();
            }
        });
    }

    private static String grabIcon(String raw) {
        int[] codePoints = toCodePoint(!raw.contains("\u200d") ? raw.replace("\ufe0f", "") : raw);
        return Arrays.stream(codePoints).mapToObj(Integer::toHexString).collect(joining("-"));
    }

    /**
     * Escapes special HTML chars.
     *
     * @param html HTML String
     * @return escaped HTML
     */
    private static String escapeHtml(String html) {
        return StringUtils.replace(html, RESCAPER, s -> {
            switch (s) {
            case "&":
                return "&amp;";
            case "<":
                return "&lt;";
            case ">":
                return "&gt;";
            case "'":
                return "&#39;";
            case "\"":
                return "&quot;";
            default:
                throw new AssertionError("Should never happen.");
            }
        });
    }

    public static String replace(String text, Function<String, String> replacer) {
        return StringUtils.replace(text, RE, replacer);
    }

    public static boolean test(String text) {
        return RE.matcher(text).find();
    }

    public static String fromCodePoint(int codePoint) {
        return String.valueOf(Character.toChars(codePoint));
    }

    public static int[] toCodePoint(String text) {
        char[] surrogates = text.toCharArray();
        List<Integer> res = new ArrayList<>();  // TODO write int list impl. (Don't use any library to avoid dependencies)
        for (int i = 0; i < surrogates.length; i++) {
            if (Character.isHighSurrogate(surrogates[i])) {
                res.add(Character.toCodePoint(surrogates[i], surrogates[i + 1]));
                i++;
            } else {
                res.add((int) surrogates[i]);
            }
        }
        return Ints.toArray(res);
    }

}

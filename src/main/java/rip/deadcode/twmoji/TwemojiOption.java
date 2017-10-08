package rip.deadcode.twmoji;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.BiFunction;

public final class TwemojiOption {

    public static final TwemojiOption DEFAULT = new TwemojiOption();

    // default assets url, by default will be Twitter Inc. CDN
    private static final String BASE_URL = "https://twemoji.maxcdn.com/2/";

    // default assets file extensions, by default '.png'
    private static final String EXTENSION = ".png";

    // default assets/folder size, by default "72x72"
    // available via Twitter CDN: 72
    private static final String SIZE = "72x72";

    // default class name, by default 'emoji'
    private static final String CLASS_NAME = "emoji";

    private BiFunction<String, TwemojiOption, String> callback;

    private BiFunction<String, String, Map<String, String>> attributes;

    private String base;

    private String extension;

    private String size;

    private String className;

    public TwemojiOption() {
        this.callback = TwemojiOption::generateDefaultImageSrc;
        this.attributes = (_1, _2) -> ImmutableMap.of();
        this.base = BASE_URL;
        this.extension = EXTENSION;
        this.size = SIZE;
        this.className = CLASS_NAME;
    }

    private static String generateDefaultImageSrc(String iconId, TwemojiOption options) {
        return options.base + options.size + "/" + iconId + options.extension;
    }

    // TODO provide fluent builder
    public TwemojiOption(
            BiFunction<String, TwemojiOption, String> callback,
            BiFunction<String, String, Map<String, String>> attributes,
            String base,
            String extension,
            String size,
            String className) {
        this.callback = callback;
        this.attributes = attributes;
        this.base = base;
        this.extension = extension;
        this.size = size;
        this.className = className;
    }

    public BiFunction<String, TwemojiOption, String> getCallback() {
        return callback;
    }

    public BiFunction<String, String, Map<String, String>> getAttributes() {
        return attributes;
    }

    public String getBase() {
        return base;
    }

    public String getExtension() {
        return extension;
    }

    public String getSize() {
        return size;
    }

    public String getClassName() {
        return className;
    }

}

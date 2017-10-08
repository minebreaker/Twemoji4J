package rip.deadcode.twmoji;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * Options used with {@link Twemoji#parse(String, TwemojiOption)}.
 */
// TODO create fluent API
public final class TwemojiOption {

    /**
     * Singleton instance of the default value.
     */
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

    /**
     * Function that receives icon ID and {@link TwemojiOption}, and return the value to be replaced with emoji.
     */
    private BiFunction<String, TwemojiOption, String> callback;

    /**
     * Function that receives raw emoji and icon ID, returns {@link Map} of the attributes.
     */
    private BiFunction<String, String, Map<String, String>> attributes;

    /**
     * Base url.
     */
    private String base;

    /**
     * File extension to be used.
     */
    private String extension;

    /**
     * Icon size.
     */
    private String size;

    /**
     * HTML class name.
     */
    private String className;

    public TwemojiOption() {
        this.callback = (iconId, options) -> options.base + options.size + "/" + iconId + options.extension;
        this.attributes = (_1, _2) -> ImmutableMap.of();
        this.base = BASE_URL;
        this.extension = EXTENSION;
        this.size = SIZE;
        this.className = CLASS_NAME;
    }

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

    public static class Builder {

        private TwemojiOption o = new TwemojiOption();

        public Builder callback(BiFunction<String, TwemojiOption, String> callback) {
            this.o.callback = callback;
            return this;
        }

        public Builder attributes(BiFunction<String, String, Map<String, String>> attributes) {
            this.o.attributes = attributes;
            return this;
        }

        public Builder attributes(Map<String, String> attributes) {
            this.o.attributes = (_1, _2) -> ImmutableMap.copyOf(attributes);
            return this;
        }

        public Builder base(String base) {
            this.o.base = base;
            return this;
        }

        public Builder extension(String extension) {
            this.o.extension = extension;
            return this;
        }

        public Builder size(String size) {
            this.o.size = size;
            return this;
        }

        public Builder size(int size) {
            this.o.size = size + "x" + size;
            return this;
        }

        public Builder className(String className) {
            this.o.className = className;
            return this;
        }

        public TwemojiOption build() {
            return o;
        }

    }

}

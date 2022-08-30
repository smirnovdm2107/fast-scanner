package fastscanner;

import javax.crypto.spec.PSource;
import java.io.InputStream;
import java.util.function.Predicate;

public class FastScanner {
    private final CharSource source;
    private static final char END = '\0';
    private char ch;

    public FastScanner(CharSource source) {
        this.source = source;
        this.ch = take();
    }
    public FastScanner(InputStream inputStream) {
        this(new InputStreamCharSource(inputStream));
    }

    public String next() {
        skipWhitespaces();

    }

    private void expect(char expected) {
        if (take(expected)) {
            return;
        }
        throw new IllegalArgumentException(String.format("expected %s, found %s", expected, ch));
    }
    private boolean take(char expected) {
        return take((ch) -> {return ch == expected;});
    }

    private boolean take(Predicate<Character> predicate) {
        if (predicate.test(ch)) {
            take();
            return true;
        }
        return false;
    }


    private char take() {
        char result = ch;
        ch = isEnd() ? END : source.next();
        return result;
    }

    private boolean isEnd() {
        return !source.hasNext();
    }

    private void skipWhitespaces() {
        while(Character.isWhitespace(ch)) {
            take();
        }
    }
}

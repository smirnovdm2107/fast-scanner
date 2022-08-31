package fastscanner;

import javax.crypto.spec.PSource;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Objects;
import java.util.function.Function;
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

    // whitespace method -> method that calls whitespace method in the beginning
    @FunctionalInterface
    private interface SkipWhitespaceMethod<T> {
        T run();
    }

    private <T> T callWhitespaceMethod(SkipWhitespaceMethod<T> method) {
        skipWhitespaces();
        return method.run();
    }

    public String next() {
        return callWhitespaceMethod(() -> takeWhile((ch) -> !isWhitespace(ch)));
    }

    public int nextInt() {
        return nextNumber(Integer::parseInt);
    }

    public long nextLong() {
        return nextNumber(Long::parseLong);
    }

    public BigInteger nextBigInteger() {
        return nextNumber(BigInteger::new);
    }

    public <T> T nextNumber(Function<String, T> parseFunction) {
        return callWhitespaceMethod(() -> {
            return parseFunction.apply(new StringBuilder()
                    .append(take('-') ? '-' : "")
                    .append(takeWhile(Character::isDigit)).toString());
        });
    }

    private String takeWhile(Predicate<Character> predicate) {
        StringBuilder sb = new StringBuilder();
        while(predicate.test(ch)) {
            sb.append(take());
        }
        return sb.toString();
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

    private boolean between(char min, char max) {
        return test((ch) -> min <= ch && ch <= max);
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

    private boolean test(Predicate<Character> predicate) {
        Objects.requireNonNull(predicate);
        return predicate.test(ch);
    }

    private boolean test(char expected) {
        return expected == ch;
    }

    private boolean isEnd() {
        return !source.hasNext();
    }

    private void skipWhitespaces() {
        while(isWhitespace(ch)) {
            take();
        }
    }

    private static boolean isWhitespace(char ch) {
        return Character.isWhitespace(ch);
    }

    public void close() {
        source.close();
    }

}

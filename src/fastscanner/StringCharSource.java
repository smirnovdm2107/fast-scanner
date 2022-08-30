package fastscanner;

import java.util.Objects;

public class StringCharSource implements CharSource {

    private final String source;
    private int pos = 0;

    public StringCharSource(String source) {
        Objects.requireNonNull(source);
        this.source = source;
    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public char next() {
        char ch = source.charAt(pos);
        ++pos;
        return ch;
    }

    @Override
    public boolean hasNext() {
        return pos == source.length();
    }

    @Override
    public void close() {
    }
}

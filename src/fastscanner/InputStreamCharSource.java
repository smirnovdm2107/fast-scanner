package fastscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamCharSource implements CharSource {

    private final BufferedReader source;
    private int ch;
    private int pos;

    public InputStreamCharSource(InputStream inputStream) {
        source = new BufferedReader(new InputStreamReader(inputStream));

    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public char next() {
        try {
            int result = ch;
            ch = source.read();
            ++pos;
            return (char) ch;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return ch == -1;
    }

    public void close() {
        try {
            source.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

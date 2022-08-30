package fastscanner;

public interface CharSource {
    int getPos();
    char next();
    boolean hasNext();
    void close();
}

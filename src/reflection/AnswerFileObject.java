package reflection;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class AnswerFileObject extends SimpleJavaFileObject {

    private final String sourceCode;
    /**
     * Construct a SimpleJavaFileObject of the given kind and with the
     * given URI.
     *
     * @param uri  the URI for this file object
     * @param kind the kind of this file object
     */
    protected AnswerFileObject(URI uri, Kind kind, String source) {
        super(uri, kind);
        sourceCode = source;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return sourceCode;
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GlobPatternTest {
    private final GlobPattern p = new GlobPattern("?at");

    @Test void matchesWithWildcard()  { assertTrue(p.match("Cat")); assertTrue(p.match("bat")); }
    @Test void tooShort()             { assertFalse(p.match("at")); }
    @Test void tooLong()              { assertFalse(p.match("boat")); }
    @Test void differentLiteralChar() { assertFalse(p.match("cot")); }
    @Test void caseSensitive()        { assertFalse(new GlobPattern("cat").match("Cat")); }
    @Test void emptyPatternAndText()  { assertTrue(new GlobPattern("").match("")); }
    @Test void onlyWildcards()        { assertTrue(new GlobPattern("??").match("xy")); }
    @Test void questionMarkInText()   { assertFalse(new GlobPattern("a").match("?")); }
}
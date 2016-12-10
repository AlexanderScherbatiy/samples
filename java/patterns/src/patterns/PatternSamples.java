package patterns;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexsch on 12/9/2016.
 */
public class PatternSamples {

    public static void main(String[] args) {
        checkMatches();
        checkSplit();
        checkFind();
    }

    static void checkMatches() {

        Pattern pattern = Pattern.compile("a");
        matchTrue(pattern, "a");
        matchFalse(pattern, "b");

        pattern = Pattern.compile("[^a]");
        matchTrue(pattern, "b");
        matchFalse(pattern, "a");

        pattern = Pattern.compile("[abc]");
        matchTrue(pattern, "a");
        matchTrue(pattern, "b");
        matchTrue(pattern, "c");
        matchFalse(pattern, "d");

        pattern = Pattern.compile("[a-c]");
        matchTrue(pattern, "a");
        matchTrue(pattern, "b");
        matchTrue(pattern, "c");
        matchFalse(pattern, "d");

        pattern = Pattern.compile(".");
        matchTrue(pattern, "a");
        matchTrue(pattern, "b");
        matchTrue(pattern, "c");
        matchFalse(pattern, "\n");

        pattern = Pattern.compile("ab*c");
        matchTrue(pattern, "ac");
        matchTrue(pattern, "abc");
        matchTrue(pattern, "abbc");
        matchTrue(pattern, "abbbc");
        matchFalse(pattern, "ab*c");

        pattern = Pattern.compile("a[bcd]*e");
        matchTrue(pattern, "ae");
        matchTrue(pattern, "abe");
        matchTrue(pattern, "ace");
        matchTrue(pattern, "abbe");
        matchTrue(pattern, "abbcce");
        matchFalse(pattern, "abcd");

        pattern = Pattern.compile("a[bcd]+e");
        matchFalse(pattern, "ae");
        matchTrue(pattern, "abe");
        matchTrue(pattern, "ace");
        matchTrue(pattern, "abbe");
        matchTrue(pattern, "abbcce");
        matchFalse(pattern, "abcd");

        pattern = Pattern.compile("a[bcd]?e");
        matchTrue(pattern, "ae");
        matchTrue(pattern, "abe");
        matchTrue(pattern, "ace");
        matchFalse(pattern, "abbe");
        matchFalse(pattern, "abbcce");
        matchFalse(pattern, "abcd");

        pattern = Pattern.compile("ab{2}c");
        matchFalse(pattern, "ac");
        matchFalse(pattern, "abc");
        matchTrue(pattern, "abbc");
        matchFalse(pattern, "abbbc");
        matchFalse(pattern, "abbbbc");

        pattern = Pattern.compile("ab{2,3}c");
        matchFalse(pattern, "ac");
        matchFalse(pattern, "abc");
        matchTrue(pattern, "abbc");
        matchTrue(pattern, "abbbc");
        matchFalse(pattern, "abbbbc");

        pattern = Pattern.compile("(ab|cd|ef)");
        matchTrue(pattern, "ab");
        matchTrue(pattern, "cd");
        matchFalse(pattern, "ac");
    }

    static void checkSplit() {
        Pattern pattern = Pattern.compile(":|;");
        String[] splitText = pattern.split("a:b;cc");

        assertEquals(splitText.length, 3);
        assertEquals(splitText[0], "a");
        assertEquals(splitText[1], "b");
        assertEquals(splitText[2], "cc");
    }

    static void checkFind() {
        Pattern pattern = Pattern.compile("s+");
        Matcher matcher = pattern.matcher("asbbsscccsssdddd");

        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, "-");
        }
        matcher.appendTail(buffer);

        assertEquals(buffer.toString(), "a-bb-ccc-dddd");
    }

    private static void matchTrue(Pattern pattern, String str) {
        if (!pattern.matcher(str).matches()) {
            throw new RuntimeException(String.format("Pattern '%s' does not match string '%s'", pattern, str));
        }
    }

    private static void matchFalse(Pattern pattern, String str) {
        if (pattern.matcher(str).matches()) {
            throw new RuntimeException(String.format("Pattern '%s' matches string '%s'", pattern, str));
        }
    }

    private static void assertEquals(Object obj1, Object obj2) {
        if (!Objects.equals(obj1, obj2)) {
            throw new RuntimeException(String.format("Object %s does not equal to %s", obj1, obj2));
        }
    }
}

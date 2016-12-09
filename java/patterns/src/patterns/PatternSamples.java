package patterns;

import java.util.regex.Pattern;

/**
 * Created by alexsch on 12/9/2016.
 */
public class PatternSamples {

    public static void main(String[] args) {

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

    private static void matchTrue(Pattern pattern, String str) {
        if (!pattern.matcher(str).matches()) {
        }
    }

    private static void matchFalse(Pattern pattern, String str) {
        if (pattern.matcher(str).matches()) {
        }
    }
}


import de.jscholz.simplecalc.postfix.ShuntingYard;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author JScholz
 */
public class ShuntingYardTest {

    private ShuntingYard algo;
    
    @Before
    public void setup() {
        algo = new ShuntingYard ();
    }

    @Test
    public void validPositiveNumbersRegexTest () {

        final Pattern regexPattern = algo.getNumberRegex ();
        final Matcher match = regexPattern.matcher ( "" );
        final String[] numbers = new String[]{
            "3",
            "13",
            "1293",
            "0",
            "25",
            "34",
            "2",
            "1",
            "10",
            "100",
            "77"
        };

        for ( int i = 0; i < numbers.length; ++i ) {

            match.reset ( numbers[ i ] );
            assertTrue ( match.matches () );

        }
    }

    @Test
    public void validNegativeNumbersRegexTest () {

        final Pattern regexPattern = algo.getNumberRegex ();
        final Matcher match = regexPattern.matcher ( "" );
        final String[] numbers = new String[]{
            "um3",
            "um13",
            "um1293",
            "um22",
            "um25",
            "um34",
            "um2",
            "um1",
            "um10",
            "um100",
            "um77"
        };

        for ( int i = 0; i < numbers.length; ++i ) {

            match.reset ( numbers[ i ] );
            assertTrue ( match.matches () );

        }
    }

    @Test
    public void simpleAddTest () {
        final String[][] expression = new String[][]{
            { "1 + 3", "1 3 +" },
            { "5 + 8", "5 8 +" },
            { "15 + 38", "15 38 +" },
            { "20 + 0", "20 0 +" },
            { "113 + 1", "113 1 +" },
            { "2 + 8", "2 8 +" },
            { "um55 + 28", "um55 28 +" },
            { "um17 + 13", "um17 13 +" },
            { "0 + 3", "0 3 +" },
            { "0 + 0", "0 0 +" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            assertTrue ( postFix.equals ( expression[ i ][ 1 ] ) );
        }
    }

    @Test
    public void simpleSubTest () {
        final String[][] expression = new String[][]{
            { "1 - 3", "1 3 -" },
            { "5 - 8", "5 8 -" },
            { "15 - 38", "15 38 -" },
            { "20 - 0", "20 0 -" },
            { "113 - 1", "113 1 -" },
            { "2 - 8", "2 8 -" },
            { "um55 - 28", "um55 28 -" },
            { "um17 - 13", "um17 13 -" },
            { "0 - 3", "0 3 -" },
            { "0 - 0", "0 0 -" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            assertTrue ( postFix.equals ( expression[ i ][ 1 ] ) );
        }
    }

    @Test
    public void simpleMulTest () {
        final String[][] expression = new String[][]{
            { "um3 * 2", "um3 2 *" },
            { "4 * 4", "4 4 *" },
            { "4 * um3", "4 um3 *" },
            { "12 * 7", "12 7 *" },
            { "um28 * um2", "um28 um2 *" },
            { "5 * 5", "5 5 *" },
            { "6 * 3", "6 3 *" },
            { "12 * 12", "12 12 *" },
            { "2 * 8", "2 8 *" },
            { "um13 * um3", "um13 um3 *" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            assertTrue ( postFix.equals ( expression[ i ][ 1 ] ) );
        }
    }

    @Test
    public void complexAddTest () {
        final String[][] expression = new String[][]{
            { "132 + 3 + 45 + 5 + 10", "132 3 + 45 + 5 + 10 +" },
            { "5 + 8 + 34 + 23 + 44", "5 8 + 34 + 23 + 44 +" },
            { "15 + 38 + 5", "15 38 + 5 +" },
            { "20 + 0 + 0", "20 0 + 0 +" },
            { "0 + 0 + 0 + 0", "0 0 + 0 + 0 +" },
            { "2 + 8 + 34 + 73 + 23", "2 8 + 34 + 73 + 23 +" },
            { "123 + 34 + 3444 + 34", "123 34 + 3444 + 34 +" },
            { "1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9", "1 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 +" },
            { "0 + 3 + 0", "0 3 + 0 +" },
            { "0 + 0 + 1", "0 0 + 1 +" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            assertTrue ( postFix.equals ( expression[ i ][ 1 ] ) );
        }
    }
    
    @Test
    public void complexSubTest () {
        final String[][] expression = new String[][]{
            { "um132 - 3 - 45 - 5 - 10", "um132 3 - 45 - 5 - 10 -" },
            { "um5 - 8 - 34 - 23 - 44", "um5 8 - 34 - 23 - 44 -" },
            { "um15 - 38 - 5", "um15 38 - 5 -" },
            { "um20 - 0 - 0", "um20 0 - 0 -" },
            { "0 - 0 - 0 - 0", "0 0 - 0 - 0 -" },
            { "um2 - 8 - 34 - 73 - 23", "um2 8 - 34 - 73 - 23 -" },
            { "123 - 34 - 3444 - 34", "123 34 - 3444 - 34 -" },
            { "1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9", "1 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 -" },
            { "0 - 3 - 0", "0 3 - 0 -" },
            { "0 - 0 - 1", "0 0 - 1 -" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            assertTrue ( postFix.equals ( expression[ i ][ 1 ] ) );
        }
    }
    
    @Test
    public void complexMixTest () {
        final String[][] expression = new String[][]{
            { "um132 * 3 + 45 + 5 * 10", "um132 3 * 45 + 5 10 * +" },
            { "5 * 8 + 3", "5 8 * 3 +" },
            { "um15 - 38 * 5", "um15 38 5 * -" },
            { "um20 * 0 - 0", "um20 0 * 0 -" },
            { "0 + 0 * 0 - 0", "0 0 0 * + 0 -" },
            { "um2 + 8 - 34 - 73 * 23", "um2 8 + 34 - 73 23 * -" },
            { "123 - 34 + 3444 + 34", "123 34 - 3444 + 34 +" },
            { "1 - 2 - 3 * 4 + 5 + 6 - 7 - 8 - 9", "1 2 - 3 4 * - 5 + 6 + 7 - 8 - 9 -" },
            { "0 * 3 - 0", "0 3 * 0 -" },
            { "0 + 0 - 1", "0 0 + 1 -" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            assertTrue ( postFix.equals ( expression[ i ][ 1 ] ) );
        }
    }

}

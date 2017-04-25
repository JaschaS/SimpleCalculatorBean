
import de.jscholz.simplecalc.ShuntingYard;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JScholz
 */
public class ShuntingYardTest {

    private final ShuntingYard algo = new ShuntingYard ();

    @Test
    public void simpleAddTest () {
        final String[][] expression = new String[][]{
            { "1 + 3", "1 3 +" },
            { "5 + 8", "5 8 +" },
            { "15 + 38", "15 38 +" },
            { "20 + 0", "20 0 +" },
            { "113 + 1", "113 1 +" },
            { "2 + 8", "2 8 +" },
            { "bn55 + 28", "bn55 28 +" },
            { "bn17 + 13", "bn17 13 +" },
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
            { "bn55 - 28", "bn55 28 -" },
            { "bn17 - 13", "bn17 13 -" },
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
            { "bn3 * 2", "bn3 2 *" },
            { "4 * 4", "4 4 *" },
            { "4 * bn3", "4 bn3 *" },
            { "12 * 7", "12 7 *" },
            { "bn28 * bn2", "bn28 bn2 *" },
            { "5 * 5", "5 5 *" },
            { "6 * 3", "6 3 *" },
            { "12 * 12", "12 12 *" },
            { "2 * 8", "2 8 *" },
            { "bn13 * bn3", "bn13 bn3 *" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            assertTrue ( postFix.equals ( expression[ i ][ 1 ] ) );
        }
    }
/*
    @Test
    public void simpleTest () {

        final String[] expression = new String[]{ "5 + 5", "2 * 3", "4 - 3", "1 / 3" };
        final String[] results = new String[]{ "5 5 +", "2 3 *", "4 3 -", "1 3 /" };

        for ( int i = 0; i < expression.length; ++i ) {

            final String postFix = algo.createPostFixExpression ( expression[ i ] );

            assertTrue ( postFix != null && !postFix.isEmpty () );

            System.out.println ( postFix + " " + postFix.length () );

            assertTrue ( postFix.equals ( results[ i ] ) );

        }
    }

    @Test
    public void complexTest () {

        final String[] expression = new String[]{ "5 + 5 - 3 + 5", "2 * 3 + 5 - 3", "4 - 3 * 4 * 5 + 1", "1 / 3 + 4 * 4" };
        final String[] results = new String[]{ "5 5 + 3 - 5 +", "2 3 * 5 + 3 -", "4 3 4 * 5 * - 1 +", "1 3 / 4 4 * +" };

        for ( int i = 0; i < expression.length; ++i ) {

            final String postFix = algo.createPostFixExpression ( expression[ i ] );

            assertTrue ( postFix != null && !postFix.isEmpty () );

            System.out.println ( postFix + " " + postFix.length () );

            assertTrue ( postFix.equals ( results[ i ] ) );

        }
    }
    */
}

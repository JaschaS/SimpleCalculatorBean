
import de.jscholz.simplecalc.postfix.PostFixEvaluation;
import de.jscholz.simplecalc.postfix.ShuntingYard;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author JScholz
 */
public class PostFixEvaluationTest {

    private PostFixEvaluation evaluation;
    private ShuntingYard algo;

    @Before
    public void setup () {
        evaluation = new PostFixEvaluation ();
        algo = new ShuntingYard ();
    }

    @Test
    public void evaluateSimpleAddTest () {
        final String[][] expression = new String[][]{
            { "1 + 3", "1 3 +", "4" },
            { "5 + 8", "5 8 +", "13" },
            { "15 + 38", "15 38 +", "53" },
            { "20 + 0", "20 0 +", "20" },
            { "113 + 1", "113 1 +", "114" },
            { "2 + 8", "2 8 +", "10" },
            { "0 + 3", "0 3 +", "3" },
            { "0 + 0", "0 0 +", "0" },
            { "um55 + 28", "um55 28 +", "-27" },
            { "um17 + 13", "um17 13 +", "-4" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            final String evaluate = evaluation.evaluate ( postFix );
            assertTrue ( evaluate.equals ( expression[ i ][ 2 ] ) );
        }
    }

    @Test
    public void evaluateSimpleSubTest () {
        final String[][] expression = new String[][]{
            { "1 - 3", "1 3 -", "-2" },
            { "5 - 8", "5 8 -", "-3" },
            { "15 - 38", "15 38 -", "-23" },
            { "20 - 0", "20 0 -", "20" },
            { "113 - 1", "113 1 -", "112" },
            { "2 - 8", "2 8 -", "-6" },
            { "um55 - 28", "um55 28 -", "-83" },
            { "um17 - 13", "um17 13 -", "-30" },
            { "0 - 3", "0 3 -", "-3" },
            { "0 - 0", "0 0 -", "0" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            final String evaluate = evaluation.evaluate ( postFix );
            assertTrue ( evaluate.equals ( expression[ i ][ 2 ] ) );
        }
    }

    @Test
    public void evaluateSimpleMulTest () {
        final String[][] expression = new String[][]{
            { "um3 * 2", "um3 2 *", "-6" },
            { "4 * 4", "4 4 *", "16" },
            { "4 * um3", "4 um3 *", "-12" },
            { "12 * 7", "12 7 *", "84" },
            { "um28 * um2", "um28 um2 *", "56" },
            { "5 * 5", "5 5 *", "25" },
            { "6 * 3", "6 3 *", "18" },
            { "12 * 12", "12 12 *", "144" },
            { "2 * 8", "2 8 *", "16" },
            { "um13 * um3", "um13 um3 *", "39" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            final String evaluate = evaluation.evaluate ( postFix );
            assertTrue ( evaluate.equals ( expression[ i ][ 2 ] ) );
        }
    }

    @Test
    public void evaluateComplexAddTest () {
        final String[][] expression = new String[][]{
            { "132 + 3 + 45 + 5 + 10", "132 3 + 45 + 5 + 10 +", "195" },
            { "5 + 8 + 34 + 23 + 44", "5 8 + 34 + 23 + 44 +", "114" },
            { "15 + 38 + 5", "15 38 + 5 +", "58" },
            { "20 + 0 + 0", "20 0 + 0 +", "20" },
            { "0 + 0 + 0 + 0", "0 0 + 0 + 0 +", "0" },
            { "2 + 8 + 34 + 73 + 23", "2 8 + 34 + 73 + 23 +", "140" },
            { "123 + 34 + 3444 + 34", "123 34 + 3444 + 34 +", "3635" },
            { "1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9", "1 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 +", "45" },
            { "0 + 3 + 0", "0 3 + 0 +", "3" },
            { "0 + 0 + 1", "0 0 + 1 +", "1" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            final String evaluate = evaluation.evaluate ( postFix );
            assertTrue ( evaluate.equals ( expression[ i ][ 2 ] ) );
        }
    }

    @Test
    public void evaluateComplexSubTest () {
        final String[][] expression = new String[][]{
            { "um132 - 3 - 45 - 5 - 10", "um132 3 - 45 - 5 - 10 -", "-195" },
            { "um5 - 8 - 34 - 23 - 44", "um5 8 - 34 - 23 - 44 -", "-114" },
            { "um15 - 38 - 5", "um15 38 - 5 -", "-58" },
            { "um20 - 0 - 0", "um20 0 - 0 -", "-20" },
            { "0 - 0 - 0 - 0", "0 0 - 0 - 0 -", "0" },
            { "um2 - 8 - 34 - 73 - 23", "um2 8 - 34 - 73 - 23 -", "-140" },
            { "123 - 34 - 3444 - 34", "123 34 - 3444 - 34 -", "-3389" },
            { "1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9", "1 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 -", "-43" },
            { "0 - 3 - 0", "0 3 - 0 -", "-3" },
            { "0 - 0 - 1", "0 0 - 1 -", "-1" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            final String evaluate = evaluation.evaluate ( postFix );
            assertTrue ( evaluate.equals ( expression[ i ][ 2 ] ) );
        }
    }

    @Test
    public void evaluateComplexMixTest () {
        final String[][] expression = new String[][]{
            { "um132 * 3 + 45 + 5 * 10", "um132 3 * 45 + 5 10 * +", "-301" },
            { "5 * 8 + 3", "5 8 * 3 +", "43" },
            { "um15 - 38 * 5", "um15 38 5 * -", "-205" },
            { "um20 * 0 - 0", "um20 0 * 0 -", "0" },
            { "0 + 0 * 0 - 0", "0 0 0 * + 0 -", "0" },
            { "um2 + 8 - 34 - 73 * 23", "um2 8 + 34 - 73 23 * -", "-1707" },
            { "123 - 34 + 3444 + 34", "123 34 - 3444 + 34 +", "3567" },
            { "1 - 2 - 3 * 4 + 5 + 6 - 7 - 8 - 9", "1 2 - 3 4 * - 5 + 6 + 7 - 8 - 9 -", "-26" },
            { "0 * 3 - 0", "0 3 * 0 -", "0" },
            { "0 + 0 - 1", "0 0 + 1 -", "-1" }
        };

        for ( int i = 0; i < expression.length; ++i ) {
            final String postFix = algo.createPostFixExpression ( expression[ i ][ 0 ] );
            final String evaluate = evaluation.evaluate ( postFix );
            System.out.println ( "postfix: " + postFix + " ev: " + evaluate + " exp: " + expression[ i ][ 2 ] );
            assertTrue ( evaluate.equals ( expression[ i ][ 2 ] ) );
        }
    }

}

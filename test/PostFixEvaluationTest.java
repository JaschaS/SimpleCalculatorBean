
import de.jscholz.simplecalc.PostFixEvaluation;
import de.jscholz.simplecalc.ShuntingYard;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JScholz
 */
public class PostFixEvaluationTest {
    
    private final PostFixEvaluation evaluation = new PostFixEvaluation ();
    private final ShuntingYard algo = new ShuntingYard ();
    
    @Test
    public void simpleTest() {
    
        final String[] expression = new String[] {"5 5 +","2 3 *", "4 3 -", "3 3 *"};
        final String[] results = new String[] {"10","6", "1", "9"};
    
        for(int i=0; i < expression.length; ++i) {
        
            final String result = evaluation.evaluate ( expression[i] );
            
            assertTrue ( result != null && !result.isEmpty ());
            
            System.out.println ( result + " " + result.length () );
            
            assertTrue ( result.equals ( results[i] ) );
            
        }
        
    }
    
    @Test
    public void complexTest() {
    
        final String[] expression = new String[] {"5 + 5 - 3 + 5","2 * 3 + 5 - 3","4 - 3 * 4 * 5 + 1","1 * 3 + 4 * 4"};
        final String[] results = new String[] {"12", "8", "-55", "19"};
        
        for(int i=0; i < expression.length; ++i) {
        
            final String postFix = algo.createPostFixExpression ( expression[i] );
            final String result = evaluation.evaluate ( postFix );
            
            assertTrue ( result != null && !result.isEmpty ());
            
            System.out.println ( postFix + " r: " + result + " l: " + result.length () );
            
            assertTrue ( result.equals ( results[i] ) );
            
        }
    }
}

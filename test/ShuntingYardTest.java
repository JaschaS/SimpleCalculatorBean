
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
    public void simpleTest() {
    
        final String[] expression = new String[] {"5 + 5","2 * 3","4 - 3","1 / 3"};
        final String[] results = new String[] {"5 5 +","2 3 *", "4 3 -", "1 3 /"};
        
        for(int i=0; i < expression.length; ++i) {
        
            final String postFix = algo.createPostFixExpression ( expression[i] );
            
            assertTrue ( postFix != null && !postFix.isEmpty ());
            
            System.out.println ( postFix + " " + postFix.length () );
            
            assertTrue ( postFix.equals ( results[i] ) );
            
        }
    }
    
    @Test
    public void complexTest() {
    
        final String[] expression = new String[] {"5 + 5 - 3 + 5","2 * 3 + 5 - 3","4 - 3 * 4 * 5 + 1","1 / 3 + 4 * 4"};
        final String[] results = new String[] {"5 5 + 3 - 5 +", "2 3 * 5 + 3 -", "4 3 4 * 5 * - 1 +", "1 3 / 4 4 * +"};
        
        for(int i=0; i < expression.length; ++i) {
        
            final String postFix = algo.createPostFixExpression ( expression[i] );
            
            assertTrue ( postFix != null && !postFix.isEmpty ());
            
            System.out.println ( postFix + " " + postFix.length () );
            
            assertTrue ( postFix.equals ( results[i] ) );
            
        }
    }
}

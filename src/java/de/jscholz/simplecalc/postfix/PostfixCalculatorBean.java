package de.jscholz.simplecalc.postfix;

import de.jscholz.simplecalc.PostFixCalculator;
import javax.ejb.Stateless;

/**
 *
 * @author JScholz
 */
@Stateless(name="PostfixCalcBean")
public class PostfixCalculatorBean implements PostFixCalculator {

    private final ShuntingYard postFixGenerator;
    private final PostFixEvaluation evaluator;
    
    public PostfixCalculatorBean() {
        
        this.postFixGenerator = new ShuntingYard ();
        this.evaluator = new PostFixEvaluation ();
        
    }
    
    @Override
    public String calculate (final String expression) {
        
        //TODO Validate that the expression is valid.
        
        final String postFixExpression = postFixGenerator.createPostFixExpression ( expression );
        
        return evaluator.evaluate ( postFixExpression );
    }
    
}

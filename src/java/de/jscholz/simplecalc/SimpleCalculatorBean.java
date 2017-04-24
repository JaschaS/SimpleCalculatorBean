package de.jscholz.simplecalc;

import javax.ejb.Stateless;

/**
 *
 * @author JScholz
 */
@Stateless(name="SimpleCalcBean")
public class SimpleCalculatorBean implements SimpleCalculator {

    private final ShuntingYard postFixGenerator;
    private final PostFixEvaluation evaluator;
    
    public SimpleCalculatorBean() {
        
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

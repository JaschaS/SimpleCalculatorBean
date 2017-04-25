package de.jscholz.simplecalc;

import java.util.regex.Pattern;
import javax.ejb.Stateless;

/**
 *
 * @author JScholz
 */
@Stateless(name="SimpleCalcBean")
public class SimpleCalculatorBean implements SimpleCalculator {

    private final ShuntingYard postFixGenerator;
    private final PostFixEvaluation evaluator;
    private final Pattern numberRegexPattern;
    
    public SimpleCalculatorBean() {
        
                
        /* To detect if the substring is a number or an operator,
         * the following regex is been used.
         * 
         * [0-9]+ => checks if the number is only positive
         * 
         * um[1-9][0-9]* => checks a negative number.
         * um defines the unary minus.
         * After the unary minus follows a number [1-9]. By definition
         * zero can't be negative.
         * [0-9]* is for more digits.
         * 
         * Note: the unary minus is only needed at the beginning of a
         * expression.
         */
        numberRegexPattern = Pattern.compile ( "[0-9]+|um[1-9][0-9]*" );
        
        this.postFixGenerator = new ShuntingYard (numberRegexPattern);
        this.evaluator = new PostFixEvaluation ();
        
    }
    
    @Override
    public String calculate (final String expression) {
        
        //TODO Validate that the expression is valid.
        
        final String postFixExpression = postFixGenerator.createPostFixExpression ( expression );
        
        return evaluator.evaluate ( postFixExpression );
    }
    
}

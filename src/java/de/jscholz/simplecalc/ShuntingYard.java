
package de.jscholz.simplecalc;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple Post Fix Expression generator.
 * 
 * @author JScholz
 */
public class ShuntingYard {

    private final Pattern numberRegexPattern;
    private Matcher numberRegexMatcher;
    
    public ShuntingYard(final Pattern numberPattern) {
        this.numberRegexPattern = numberPattern;
        this.numberRegexMatcher = null;
    }
    
    public Pattern getNumberRegex() {
        return numberRegexPattern;
    }
    
    /**
     * Generates a post fix expression from the given expression.
     * 
     * there will be no validation check. Do a validation check before
     * calling the method.
     * 
     * @param expression a expression in infix form.
     * @return 
     */
    public String createPostFixExpression (final String expression ) {
        
        final Stack<String> operators = new Stack<>();
        final StringBuilder output = new StringBuilder ();
        
        final String[] seperated = expression.split ( "\\s+" );
        
        for(final String c : seperated) {
    
            if(numberRegexMatcher == null) numberRegexMatcher = numberRegexPattern.matcher ( c );
            else numberRegexMatcher.reset ( c );
            
            //If it is a number, add it to the output.
            //The number contains only digits or um and digits,
            //where um is unary minus.
            if(numberRegexMatcher.matches ()) {
                //Add a whitespace to the output to separate the operators and numbers.
                output.append ( c ).append ( " " );
            }
            //Otherwise it is a operator. Again, we expect that this expression is valid.
            else {
            
                    //Check if the new operator has a higher order.
                    //mul > add >= sub
                    final int size = operators.size ();
                    final int currentOrder = getOrder ( c );
                    int i=0;
                    boolean done = false;
                    
                    while(i < size && !done) {
                    
                        final int topOrder = getOrder ( operators.peek () );
                    
                        if(currentOrder <= topOrder) {
                        
                            final String top = operators.pop ();
                            
                            output.append ( top ).append ( " " );
                            
                        }
                        else done = true;
                        
                        ++i;
                    }
                    
                    operators.push ( c );
                
            }
            
        }
        
        //Add all operators from stack to output.
        while(!operators.isEmpty ()) output.append ( operators.pop () ).append ( " " );
        
        return output.toString ().trim ();
    }
    
    private int getOrder(final String operator) {
    
        if(operator.equals ( "+" )) return 1;
        if(operator.equals ( "-" )) return 1;
        if(operator.equals ( "*" )) return 2;
        
        return 0;
    }
    
}

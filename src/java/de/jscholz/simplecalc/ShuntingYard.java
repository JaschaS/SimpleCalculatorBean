
package de.jscholz.simplecalc;

import java.util.Stack;

/**
 * A simple Post Fix Expression generator.
 * 
 * @author JScholz
 */
public class ShuntingYard {

    public String createPostFixExpression (final String expression ) {
        
        /*
         * We expect that the given expression is valid.
         * The number is less than 10. Which means a single character.
         * Numbers and Operators are seperated by whitespaces.
         */
        //TODO check if expression is valid and not null.
        
        final Stack<String> operators = new Stack<>();
        final StringBuilder output = new StringBuilder ();
        
        final String[] seperated = expression.split ( "\\s" );
        
        for(final String c : seperated) {
    
            //If it is a number, add it to the output.
            if(c.matches ( "[0-9]")) {
                output.append ( c ).append ( " " );
            }
            //Otherwise it is a operator. Again, we expect that this expression is valid.
            else {
            
                    //Check if the new operator has a higher order.
                    // Div >= mul > add >= sub
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
        if(operator.equals ( "/" )) return 2;
        
        return 0;
    }
    
}

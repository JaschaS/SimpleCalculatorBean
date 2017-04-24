package de.jscholz.simplecalc;

import java.util.Stack;

/**
 *
 * @author JScholz
 */
public class PostFixEvaluation {

    public String evaluate ( final String expression ) {

        //TODO Expression should be valid
        //TODO Division is allowed, but integer is been used for calculation.
        
        final Stack<String> operands = new Stack<> ();
        final String[] seperated = expression.split ( "\\s" );

        for ( final String c : seperated ) {

            if ( c.matches ( "[0-9]" ) ) {
                operands.push ( c );
            }
            else {

                int second = Integer.parseInt ( operands.pop () );
                int first = Integer.parseInt ( operands.pop () );
                
                final int result = calculate ( first, second, c );
                
                operands.push ( result + "" );

            }

        }
        
        //TODO the operand should only contain one element at the end. Validate this.
        
        return operands.pop ();
    }

    private int calculate ( final int first, final int second, final String operator ) {

        switch ( operator ) {
            case "+":

                return first + second;
            case "-":

                return first - second;
            case "*":

                return first * second;
            case "/":

                return first * second;
        }

        return Integer.MAX_VALUE;
    }

}

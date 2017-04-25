package de.jscholz.simplecalc;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JScholz
 */
public class PostFixEvaluation {

    private final Pattern positiveNumberPattern;
    private final Pattern negativeNumberPattern;
    private Matcher numberMatcher;

    public PostFixEvaluation () {
        this.positiveNumberPattern = Pattern.compile ( "[0-9]+" );
        this.negativeNumberPattern = Pattern.compile ( "um[1-9][0-9]*" );
        this.numberMatcher = null;
    }

    public String evaluate ( final String expression ) {

        final Stack<String> operands = new Stack<> ();
        final String[] seperated = expression.split ( "\\s" );

        for ( final String c : seperated ) {

            numberMatcher = positiveNumberPattern.matcher ( c );

            if ( numberMatcher.matches () ) {
                operands.push ( c );
            }
            else {

                numberMatcher = negativeNumberPattern.matcher ( c );

                if ( numberMatcher.matches () ) {

                    //Remove um in c.
                    final String s = c.substring ( 2, c.length () );

                    //Add minus symbol to number.
                    operands.push ( "-" + s );

                }
                else {
                    int second = Integer.parseInt ( operands.pop () );
                    int first = Integer.parseInt ( operands.pop () );

                    final int result = calculate ( first, second, c );

                    operands.push ( result + "" );
                }

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
        }

        return Integer.MAX_VALUE;
    }

}

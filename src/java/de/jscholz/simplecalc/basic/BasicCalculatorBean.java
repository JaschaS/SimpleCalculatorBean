
package de.jscholz.simplecalc.basic;

import de.jscholz.simplecalc.BasicCalculator;
import javax.ejb.Stateless;

/**
 *
 * @author JScholz
 */
@Stateless(name="BasicCalcBean")
public class BasicCalculatorBean implements BasicCalculator {

    @Override
    public int addition ( int a, int b ) {
        return a + b;
    }

    @Override
    public int subtract ( int a, int b ) {
        return a - b;
    }

    @Override
    public int multiply ( int a, int b ) {
        return a * b;
    }

    @Override
    public float addition ( float a, float b ) {
        return a + b;
    }

    @Override
    public float subtract ( float a, float b ) {
        return a - b;
    }

    @Override
    public float multiply ( float a, float b ) {
        return a * b;
    }

    @Override
    public float divide ( float a, float b ) {
        
        try {
            return a / b;
        }
        catch(ArithmeticException e) {
            return Float.NaN;
        }
    }
    
}

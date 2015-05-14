/*
 * license
 */
package utils.exceptions;

/**
 *
 * @author oglandx
 * created on 22.04.2015 by oglandx
 */
public class ParameterNotFoundException extends IllegalArgumentException{
    public ParameterNotFoundException(final String parameterName){
        super("Parameter \'" + parameterName + "\' not found");
    }
}

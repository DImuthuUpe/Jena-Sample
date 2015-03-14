package com.hp.hpl.jena.sparql.xpath3;

import com.hp.hpl.jena.sparql.ARQInternalErrorException;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.function.FunctionBase1;

/**
 * Created by dimuthuupeksha on 3/14/15.
 */
public class XPath3_Floor extends FunctionBase1{

    @Override
    public NodeValue exec(NodeValue v) {
        if(!v.isNumber()){
            throw new Xpath3EvalException("Input is not a numerical value");
        }
        if(v.isInteger()){
            double floorVal = Math.floor(v.getInteger().intValue());
            return NodeValue.makeDouble(floorVal);
        }else if(v.isDouble()){
            double floorVal = Math.floor(v.getDouble());
            return NodeValue.makeDouble(floorVal);
        }else if(v.isFloat()){
            double floorVal = Math.floor(v.getFloat());
            return NodeValue.makeDouble(floorVal);
        }else if(v.isDecimal()){
            double floorVal = Math.floor(v.getDecimal().intValue());
            return NodeValue.makeDouble(floorVal);
        }else{
            throw new ARQInternalErrorException("Unrecognized numeric operation : (" + v +")");
        }
    }
}

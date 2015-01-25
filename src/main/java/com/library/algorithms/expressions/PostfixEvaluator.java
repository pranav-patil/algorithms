package com.library.algorithms.expressions;

/**
 * PostfixEvaluator.java
 * @author: Lewis/Chase
 *
 * Represents an evaluator of postfix expressions. Assumes the
 * operands are constants.
 *
 * Ref: http://courses.cs.vt.edu/~cs1706/notes/labs/code/PostfixEvaluator.java
 */

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * postfix evaluator of expressions, uses a stack internally
 * @author manuel
 * @version 2
 */
public class PostfixEvaluator {

    private static final char ADD = '+';
    private static final char SUBTRACT = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';

    /**
     * evaluates a postfix expresssion
     * @param expr expression in string format tokens separated
     * by space
     * @return integer results of expression evaluation
     * @precondition expression must be valid (isValid() == true)
     */
    public int evaluate(String expr) {

        int op1, op2;
        String token;
        StringTokenizer tokenizer = new StringTokenizer(expr);
        Stack<Integer> stack = new Stack<Integer>();

        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();

            if (isOperator(token)) {
                op2 = stack.pop();
                op1 = stack.pop();

                int result = evaluateOperator(token.charAt(0), op1, op2);
                stack.push(result);
            }
            else
                stack.push(Integer.parseInt(token));
        }

        return stack.pop();
    }

    /**
     *  Determines if the specified token is an operator.
     */
    private boolean isOperator(String token) {
        return (
                   token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/"));
    }

    /**
     *  Evaluates a single expression consisting of the specified
     *  operator and operands.
     */
    private int evaluateOperator(char operation, int op1, int op2) {
        int result = 0;

        switch (operation) {
            case ADD :
                result = op1 + op2;
                break;
            case SUBTRACT :
                result = op1 - op2;
                break;
            case MULTIPLY :
                result = op1 * op2;
                break;
            case DIVIDE :
                result = op1 / op2;
        }

        return result;
    }
}

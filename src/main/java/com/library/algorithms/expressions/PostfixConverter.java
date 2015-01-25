package com.library.algorithms.expressions;

import java.util.Stack;

/**
 * @author ACHCHUTHAN
 *         http://www.java.achchuthan.org/2012/03/convert-infix-to-postfix-using-stack-in.html
 *         http://courseware.deadcodersociety.org/csis3103-data_structures/code/InfixToPostfix.java
 */
public class PostfixConverter {

    /**
     * The operators
     */
    private static final String OPERATORS = "-+*/";
    /**
     * The precedence of the operators, matches order in OPERATORS.
     */
    private static final int[] PRECEDENCE = {1, 1, 2, 2};

    public String infixToPostfix(String infixString) {

        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();

        for (int index = 0; index < infixString.length(); ++index) {
            char curChar = infixString.charAt(index);

            if (curChar == '(') {
                stack.push('(');
            } else if (curChar == ')') {
                Character stackTop = stack.peek();
                while (!(stackTop.equals('(')) && !(stack.isEmpty())) {
                    postfix.append(stackTop).append(' ');
                    stack.pop();
                    stackTop = stack.peek();
                }
                stack.pop();
            } else if (isOperator(curChar)) {

                if (stack.isEmpty()) {
                    stack.push(curChar);
                } else {
                    Character stackTop = stack.peek();

                    if (!stackTop.equals('(') && precedence(curChar) > precedence(stackTop)) {
                        stack.push(curChar);
                    } else {
                        // Pop all stacked operators with equal or higher precedence than op.
                        while (!stack.empty() && !stackTop.equals('(') && precedence(curChar) <= precedence(stackTop)) {
                            stack.pop();
                            postfix.append(stackTop).append(' ');
                            if (!stack.empty()) {
                                stackTop = stack.peek(); // Reset topOp.
                            }
                        }
                        // assert: Operator stack is empty or
                        // current operator precedence > top of stack operator precedence.
                        stack.push(curChar);
                    }
                }
            } else {
                postfix.append(curChar).append(' ');
            }
        }
        while (!stack.isEmpty()) {
            Character oper = stack.peek();
            if (!oper.equals(new Character('('))) {
                stack.pop();
                postfix.append(oper).append(' ');
            }
        }
        return postfix.toString();
    }

    /**
     * Determine the precedence of an operator.
     *
     * @param op The operator
     * @return the precedence
     */
    private int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }

    private boolean isOperator(char op) {
        if (OPERATORS.indexOf(op) == -1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PostfixConverter converter = new PostfixConverter();
        String str = "(1+2)*(3+4)/(12-5)";
        System.out.println("The Expression in infix form :\n" + str);
        System.out.println("The Equivalent Postfix Expression :\n" + converter.infixToPostfix(str));
    }
}

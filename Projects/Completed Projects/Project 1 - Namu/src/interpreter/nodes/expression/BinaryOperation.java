package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;
import java.util.List;

public class BinaryOperation implements ExpressionNode {
    /** NMU addition operator */
    public static final String ADD = "+";

    /** NMU division operator */
    public static final String DIV = "/";

    /** NMU modulus operator */
    public static final String MOD = "%";

    /** NMU multiply operator */
    public static final String MUL = "*";

    /** NMU power operator */
    public static final String POW = "^";

    /** NMU subtraction operator */
    public static final String SUB = "-";

    /** the legal binary operators, for use when parsing */
    public static final List<String> OPERATORS = List.of(ADD, DIV, MOD, MUL, POW, SUB);

    /**
     * Create a new BinaryOperation node.
     *
     * @param operator - the operator
     * @param leftChild - the left child expression
     * @param rightChild - the right child expression
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {

    }

    /**
     * Evaluate the expression representing by this node.
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the result of the evaluation.
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        return 0;
    }

    /**
     * Print to standard output the infix format for this node.
     */
    @Override
    public void emit() {

    }

    /**
     * Generates the compiled ALT instructions for this node/descendants to be
     * later executed.
     *
     * @param out the stream to write output to using out.println()
     */
    @Override
    public void compile(PrintWriter out) {

    }
}

package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;
import java.util.List;

public class UnaryOperation implements ExpressionNode {
    /**
     * NMU negation operator
     */
    public static final String NEG = "!";
    /**
     * NMU square root operator
     */
    public static final String SQRT = "$";
    /**
     * the legal unary operators, for use when parsing
     */
    public static final List<String> OPERATORS = List.of(NEG, SQRT);

    /**
     * Create a new UnaryOperation node.
     *
     * @param operator the operator
     * @param child the child expression
     */
    public UnaryOperation(String operator, ExpressionNode child) {

    }

    /**
     * Compute the result of evaluating the expression and applying the operator to it.
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the result of the computation
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        return 0;
    }

    /**
     * Print to standard output the infix display of the child nodes
     * preceded by the operator and without an intervening blank.
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

package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;

import java.io.PrintWriter;

public class Print implements ActionNode {
    private final ExpressionNode child;

    /**
     * Create a new Print node.
     *
     * @param child the child expression.
     */
    public Print(ExpressionNode child) {
        this.child = child;
    }

    /**
     * Evaluate the child expression and print the result to standard output.
     *
     * @param symTbl the table where variable values are stored
     */
    @Override
    public void execute(SymbolTable symTbl) {
        System.out.println(this.child.evaluate(symTbl));
    }

    /**
     * Print the statement to standard output in the format "Print" followed by the infix form of the expression.
     */
    @Override
    public void emit() {
        System.out.println("Print " + this.child);
    }

    /**
     * Generates the ALT instructions that when instructed will perform the print action.
     *
     * @param out the stream to write output to using out.println()
     */
    @Override
    public void compile(PrintWriter out) {
        this.child.compile(out);
        out.println("PRINT");
    }
}

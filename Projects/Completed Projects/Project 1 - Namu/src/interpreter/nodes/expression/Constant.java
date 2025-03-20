package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;

public class Constant implements ExpressionNode {
    public final int value;

    /**
     * Create a new constant.
     *
     * @param value the value
     */
    public Constant(int value) {
        this.value = value;
    }

    /**
     * Return the stored value when evaluated.
     *
     * @param symTbl the symbol table (Ignored)
     * @return the value
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        return this.value;
    }

    /**
     * Print to standard output the infix format for this node.
     */
    @Override
    public void emit() {
        System.out.println(this.value);
    }

    /**
     * Generates the ALT instruction for pushing the value.
     *
     * @param out the stream to write output to using out.println()
     */
    @Override
    public void compile(PrintWriter out) {
        out.write("PUSH " + this.value);
    }
}

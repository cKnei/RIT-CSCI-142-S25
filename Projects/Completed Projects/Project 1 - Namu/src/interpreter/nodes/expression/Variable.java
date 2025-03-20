package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;

import java.io.PrintWriter;

public class Variable implements ExpressionNode {
    private final String name;

    /**
     * Create a new Variable for the identifier.
     *
     * @param name the name of this variable
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Evaluate the expression representing by this node.
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the result of the evaluation.
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        if (!symTbl.has(this.name))
            Errors.report(Errors.Type.UNINITIALIZED, this.name);

        return symTbl.get(this.name);
    }

    /**
     * Print to standard output the infix format for this node.
     */
    @Override
    public void emit() {
        System.out.println(this.name);
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

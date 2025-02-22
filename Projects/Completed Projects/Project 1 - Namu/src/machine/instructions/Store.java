package machine.instructions;

import common.SymbolTable;
import machine.Alaton;
import machine.InstructionStack;

/**
 * The STORE instruction.
 *
 * @author knei
 */
public class Store implements Instruction {
    /**
     * The variable name
     */
    private final String name;
    /**
     * The Symbol Table
     */
    private final SymbolTable table;
    /**
     * The Instruction Stack
     */
    private final InstructionStack stack;

    public Store(String name, Alaton machine) {
        this.name = name;
        this.table = machine.getSymbolTable();
        this.stack = machine.getInstructionStack();
    }

    /**
     * Set the variable name onto {@link common.SymbolTable} first integer value on then {@link machine.InstructionStack}
     */
    @Override
    public void execute() {
        this.table.set(this.name, this.stack.pop());
    }

    /**
     * Show the instruction using text so that it can be understood
     * by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return "STORE " + this.name;
    }
}

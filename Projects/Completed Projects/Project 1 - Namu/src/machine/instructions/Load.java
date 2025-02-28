package machine.instructions;

import common.Errors;
import common.SymbolTable;
import machine.Alaton;
import machine.InstructionStack;

/**
 * The LOAD instruction.
 *
 * @author knei
 */
public class Load implements Instruction {
    private final String name;
    private final SymbolTable table;
    private final InstructionStack stack;

    public Load(Alaton machine, String name) {
        this(name, machine);
    }

    public Load(String name, Alaton machine) {
        this.name = name;
        this.table = machine.getSymbolTable();
        this.stack = machine.getInstructionStack();
    }

    /**
     * Run this instruction on the machine, using the machine's
     * value stack and symbol table.
     */
    @Override
    public void execute() {
        if ( this.table.has(this.name) )
            this.stack.push(this.table.get(this.name));
        else Errors.report(Errors.Type.UNINITIALIZED);
    }

    /**
     * Show the instruction using text so that it can be understood
     * by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return "LOAD " + this.name;
    }
}

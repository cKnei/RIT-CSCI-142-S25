package machine.instructions;

import machine.Alaton;
import machine.InstructionStack;

public class SquareRoot implements Instruction {
    private final InstructionStack stack;

    public SquareRoot(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Run this instruction on the machine, using the machine's
     * value stack and symbol table.
     */
    @Override
    public void execute() {
        this.stack.push((int) Math.pow(this.stack.pop(), 0.5d));
    }

    /**
     * Show the instruction using text so that it can be understood
     * by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return "SQRT";
    }
}

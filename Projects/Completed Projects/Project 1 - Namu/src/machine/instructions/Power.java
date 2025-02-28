package machine.instructions;

import machine.Alaton;
import machine.InstructionStack;

public class Power implements Instruction {
    private final InstructionStack stack;

    public Power(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Run this instruction on the machine, using the machine's
     * value stack and symbol table.
     */
    @Override
    public void execute() {
        int pow = this.stack.pop();
        this.stack.push((int) Math.pow(this.stack.pop(), pow));
    }

    /**
     * Show the instruction using text so that it can be understood
     * by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return "POW";
    }
}

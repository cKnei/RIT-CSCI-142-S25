package machine.instructions;

import common.Errors;
import machine.Alaton;
import machine.InstructionStack;

public class Modulus implements Instruction {
    private final InstructionStack stack;

    public Modulus(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Run this instruction on the machine, using the machine's
     * value stack and symbol table.
     */
    @Override
    public void execute() {
        int divisor = this.stack.pop();
        if (divisor == 0)
            Errors.report(Errors.Type.DIVIDE_BY_ZERO);
        else
            this.stack.push(this.stack.pop() % divisor);
    }

    /**
     * Show the instruction using text so that it can be understood
     * by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return "MOD";
    }
}

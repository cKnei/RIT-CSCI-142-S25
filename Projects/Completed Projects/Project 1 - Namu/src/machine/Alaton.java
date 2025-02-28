package machine;

import common.Errors;
import common.SymbolTable;
import machine.instructions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The machine can process/execute a series of low level ALT instructions using
 * an instruction stack and symbol table.
 *
 * @author RIT CS
 */
public class Alaton {
    /** the push instruction */
    public final static String PUSH = "PUSH";
    /** the print instruction */
    public final static String PRINT = "PRINT";
    /** the store instruction */
    public final static String STORE = "STORE";
    /** the load instruction */
    public final static String LOAD = "LOAD";
    /** the negate instruction */
    public final static String NEGATE = "NEG";
    /** the square root instruction */
    public final static String SQUARE_ROOT = "SQRT";
    /** the add instruction */
    public final static String ADD = "ADD";
    /** the subtract instruction */
    public final static String SUBTRACT = "SUB";
    /** the multiply instruction */
    public final static String MULTIPLY = "MUL";
    /** the divide instruction */
    public final static String DIVIDE = "DIV";
    /** the modulus instruction */
    public final static String MODULUS = "MOD";
    /** the power instruction */
    public final static String POWER = "POW";

    /** the list of valid machine instructions */
    public static final List< String > OPERATIONS =
            List.of(
                    ADD,
                    DIVIDE,
                    LOAD,
                    MODULUS,
                    MULTIPLY,
                    NEGATE,
                    POWER,
                    PUSH,
                    PRINT,
                    SQUARE_ROOT,
                    STORE,
                    SUBTRACT
            );

    public final InstructionStack instructions;
    public final SymbolTable symbols;
    public final ArrayList<Instruction> operations;

    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";

    // TODO

    /**
     * Create a new machine, with an empty symbol table, instruction stack, and
     * list of instructions.
     */
    public Alaton() {
        this.symbols = new SymbolTable();
        this.instructions = new InstructionStack();
        this.operations = new ArrayList<>(0);
    }

    /**
     * Return the instruction stack.
     *
     * @return the stack
     */
    public InstructionStack getInstructionStack() {
        return this.instructions;
    }

    /**
     * Return the symbol table.
     *
     * @return the symbol table
     */
    public SymbolTable getSymbolTable() {
        return this.symbols;
    }


    /**
     * Assemble the machine instructions.
     *
     * @param altIn the input source
     * @param stdin true if input is coming from standard input (for prompting)
     */
    public void assemble(Scanner altIn, boolean stdin) {
        if (stdin) {
            System.out.print("🤖 ");
        }

        w: while (altIn.hasNextLine()) {
            String[] tokens = altIn.nextLine().strip().split("\\s+");
            switch ( tokens[0] ) {
                case "ADD"      -> this.operations.add(new Add        (this));
                case "DIV"      -> this.operations.add(new Divide     (this));
                case "LOAD"     -> this.operations.add(new Load       (this, tokens[1]));
                case "MOD"      -> this.operations.add(new Modulus    (this));
                case "MUL"      -> this.operations.add(new Multiply   (this));
                case "NEG"      -> this.operations.add(new Negate     (this));
                case "POW"      -> this.operations.add(new Power      (this));
                case "PRINT"    -> this.operations.add(new Print      (this));
                case "PUSH"     -> this.operations.add(new Push       (this, Integer.parseInt(tokens[1])));
                case "SQRT"     -> this.operations.add(new SquareRoot (this));
                case "STORE"    -> this.operations.add(new Store      (this, tokens[1]));
                case "SUB"      -> this.operations.add(new Subtract   (this));
                case Alaton.EOF -> {break w;}
                default         -> Errors.report(Errors.Type.ILLEGAL_INSTRUCTION, tokens[0]);
            }
        }

        System.out.println("(ALT) Machine instructions:");

        for (Instruction i : this.operations) {
            System.out.println(i);
        }
    }

    /**
     * Executes each assembled machine instruction in order.  When completed it
     * displays the symbol table and the instruction stack.
     */
    public void execute() {
        System.out.println("(ALT) Executing...");

        while (!this.operations.isEmpty()) {
            this.operations.removeFirst().execute();
        }

        System.out.println("(ALT) Completed execution!");
        System.out.println("(ALT) Symbol table:");

        System.out.println(this.symbols);
    }

    /**
     * The main method.  Machine instructions can either be specified from standard input
     * (no command line), or from a file (only argument on command line).  From
     * here the machine assembles the instructions and then executes them.
     *
     * @param args command line argument (optional)
     * @throws FileNotFoundException if the machine file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // determine input source
        Scanner altIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            altIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1){
            altIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Alaton [filename.alt]");
            System.exit(1);
        }

        Alaton machine = new Alaton();
        machine.assemble(altIn, stdin);            // assemble the machine instructions
        machine.execute();                         // execute the program
        altIn.close();
    }
}

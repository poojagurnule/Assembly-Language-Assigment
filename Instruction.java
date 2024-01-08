public class Instruction {
    Operation operation ;
    String[] operands ;

    public Instruction(Operation operation, String[] operands) {
        this.operation = operation;
        this.operands = operands;
    }
}

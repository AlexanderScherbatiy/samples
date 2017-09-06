package structural.facade;

public class Processor {

    public static final int SUM = 1;
    public static final int MUL = 2;

    private Registers registers;

    public Registers getRegisters() {
        return registers;
    }

    public void setRegisters(Registers registers) {
        this.registers = registers;
    }

    public void execute(int code, int register1, int register2, int resultRegister) {

        int value1 = registers.getRegister(register1);
        int value2 = registers.getRegister(register2);

        switch (code) {
            case SUM:
                int res = value1 + value2;
                registers.setRegister(resultRegister, res);
                break;
            case MUL:
                res = value1 * value2;
                registers.setRegister(resultRegister, res);
                break;
            default: // Do Nothing
        }
    }
}

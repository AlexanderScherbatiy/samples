package structural.facade;

public class Computer {

    private Registers registers;
    private Processor processor;

    public Computer() {
        registers = new Registers();
        processor = new Processor();
        processor.setRegisters(registers);
    }

    public int sum(int a, int b) {

        registers.setRegister(1, a);
        registers.setRegister(2, b);
        processor.execute(Processor.SUM, 1, 2, 0);
        return registers.getRegister(0);
    }

    public int mul(int a, int b) {

        registers.setRegister(1, a);
        registers.setRegister(2, b);
        processor.execute(Processor.MUL, 1, 2, 0);
        return registers.getRegister(0);
    }
}

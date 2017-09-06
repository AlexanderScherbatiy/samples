package structural.facade;

public class Registers {

    private int[] registers = new int[8];

    public int getRegister(int register) {
        return registers[register];
    }

    public void setRegister(int register, int value) {
        registers[register] = value;
    }
}

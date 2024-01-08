import java.util.HashMap;

public class AssemblyProgram {

    HashMap<String , Integer> registers = new HashMap<>();
    boolean flag = true ;


    public void executeProgram(Instruction instruction){
            if(flag){
                switch (instruction.operation){
                    case MV -> mvInstruction(instruction.operands);
                    case ADD -> addInstruction(instruction.operands);
                    case SHOW -> showInstruction(instruction.operands);
                }
            }
            else
                System.out.println("Sorry !!!!! can't execute any command");
    }


    private  void  mvInstruction(String[] operands){
               String reg = operands[0];
             int value = Integer.parseInt(operands[1].substring(1));
             if(!registers.containsKey(reg)){
                   registers.put(reg,value);
             }
             else{
                  System.out.println("This register already exists");
             }
    }

    private void addInstruction(String[] operands){
             String reg = operands[0];
             if(!registers.containsKey(reg)){
                       System.out.println("This register " + reg + " doesn't exit ");
             }else {
                      int value = 0;
                      if(!registers.containsKey(operands[1])){
                             value = registers.get(reg) + Integer.parseInt(operands[1]) ;
                      }else{
                            value = registers.get(reg) + registers.get(operands[1]);
                      }

                      registers.put(reg , value);
             }
    }

       private  void showInstruction(String[] operands){
             String reg = operands[0] ;
             if(registers.containsKey(reg) && flag)
                 System.out.println(reg + ":" + registers.get(reg));
             else
                 System.out.println("This register doesn't exist");
       }
}

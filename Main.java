
import java.io.*;
import java.util.*;

public class Main {
     public static AssemblyProgram assemblyProgram = new AssemblyProgram();
     private static List<String> commandHistory = new ArrayList<>();

     public static void main(String[] args) throws IOException {

        Scanner scn = new Scanner(System.in) ;
        System.out.println("Welcome to Your Database Program!!!");
        System.out.println("Available Commands:");
        System.out.println(" - 'MV': Create a new variable");
        System.out.println(" - 'ADD': Add values to a register");
        System.out.println(" - 'SHOW': View the result of a register");
        System.out.println(" - 'SHOW-ALL': Display all registers");
        System.out.println(" - 'HISTORY': View command history");
        System.out.println(" - 'EXIT': Terminate the program");

        boolean flag = true ;

        assemblyProgram.registers = readFromFile() ;

        while (flag){
             String input = scn.nextLine() ;
            addToHistory(input); // Add to history before executing
             String[] word = input.split("[\\s,]+");

             switch (word[0].toUpperCase()){
                    case "MV" -> {
                           assemblyProgram.executeProgram(new Instruction(Operation.MV , new String[] {word[1] , word[2]}));
                           storeRegisterValues(assemblyProgram.registers);
                    }
                    case  "ADD" -> {
                        assemblyProgram.executeProgram(new Instruction(Operation.ADD , new String[]{word[1] , word[2]}));
                        storeRegisterValues(assemblyProgram.registers);
                    }
                    case "SHOW" ->{
                        assemblyProgram.executeProgram(new Instruction(Operation.SHOW , new String[]{word[1]}));
                        storeRegisterValues(assemblyProgram.registers);
                    }
                    case "EXIT" -> {
                           flag = false ;
                           System.out.println("This program has been terminated");
                    }
                    case "SHOW-ALL" -> displayAllRegisters();

                    case "HISTORY" -> showCommandHistory();


                 default ->
                            System.out.println("Unexpected value: " + word[0]);

             }
        }

    }


    private static void showCommandHistory() {
        System.out.println("Command History:");
        for (String command : commandHistory) {
            System.out.println(command);
        }
    }


    private static void storeRegisterValues(Map<String , Integer> registers){
        try{
            FileWriter writer = new FileWriter("registerValues.txt");
            for(Map.Entry<String,Integer> entry : registers.entrySet()){
                     writer.append(entry.getKey() + " " + entry.getValue() + "\n") ;
            }
              writer.close();
        }catch (IOException e){
              e.printStackTrace();
        }
    }

      private  static HashMap<String , Integer> readFromFile() throws IOException{
            HashMap<String , Integer> map = new HashMap<>() ;
          BufferedReader reader = new BufferedReader(new FileReader("registerValues.txt"));
           String line ;
           while((line = reader.readLine()) != null){
               String[] parts = line.split(" " , 2) ;
                 if(parts.length >= 2){
                        String key = parts[0] ;
                        int value = Integer.parseInt(parts[1]);
                        if(!map.containsKey(key))
                            map.put(key , value);
                 }
           }
             return map ;
      }

    private static void displayAllRegisters() {
        assemblyProgram.registers.forEach((key, value) -> System.out.println(key + ":" + value));
    }


    // Add this method to store commands in history
    private static void addToHistory(String command) {
        commandHistory.add(command);

    }
}

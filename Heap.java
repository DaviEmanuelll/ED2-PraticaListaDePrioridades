import java.util.ArrayList;
import java.util.List;

public class Heap {
    List<Process> processList;
    int sizeList = 0;
   
    public Heap() {
        this.processList = new ArrayList<Process>();
        sizeList = 0;
    } 
    public Heap(List<Process> processList) {
        this.processList = processList;
        this.sizeList = processList.size();
        organizeHeap();
    }
    //Escrever
    public void write() {
        int i=0;
        for (Process process : processList) {
            System.out.println(i +" - "+ process.getId() + " (" + process.getRuntime() + "s)");
            i++;
        }
        System.out.println("-----------------------------------");
    }
    // Trocar
    public void trade(int firstProcess, int secondProcess){
        Process saveProcess = processList.get(firstProcess);
        
        processList.set(firstProcess, processList.get(secondProcess));
        processList.set(secondProcess, saveProcess);
    }
    //Organiza a lista de acordo com NÓS INTERNOS
    public void organizeHeap(){
        int lastProcess = sizeList - 1;
        for(int i = (lastProcess-1)/2; i >= 0; i--){
            comeDown(i);
        }
    }
    // Subir
    public void rideUp(int indexProcess){
        if(indexProcess>0){
            int topProcess = (indexProcess-1) / 2;
            
            if(processList.get(topProcess).getRuntime()>=processList.get(indexProcess).getRuntime()){
                trade(topProcess, indexProcess);
            }
            rideUp(topProcess);
        }   
    }
    // Descer
    public void comeDown(int indexProcess){
        if(sizeList > 1){
            int lastProcess = sizeList - 1;
            int dawnProcess = indexProcess * 2 + 1;

            if(dawnProcess<=lastProcess){
                if(dawnProcess<lastProcess){
                    if(processList.get(dawnProcess+1).getRuntime()<processList.get(dawnProcess).getRuntime()){
                        dawnProcess++;
                    }
                }
                if(processList.get(dawnProcess).getRuntime()<processList.get(indexProcess).getRuntime()){
                    trade(indexProcess,dawnProcess);
                    comeDown(indexProcess);
                }
            }
        }
    }
    // Inserir
    public void insert(Process newProcess){
        processList.add(newProcess);
        rideUp(sizeList);
        sizeList++;
        organizeHeap();
    }
    // Remover
    public Process remove(int removedProcess){
        if(sizeList!=0){
            Process removed = processList.get(removedProcess);
            int lastProcess = sizeList - 1;

            if(removedProcess<lastProcess){
                for(int i = removedProcess; i < lastProcess; i++){
                    trade(i, i + 1);
                }
            }

            processList.remove(lastProcess);
            sizeList--;
            organizeHeap();

            return removed;

        } else {
            System.out.println("Não existe processo para remover");
        }
        return null;
    }
    // Seleção para a execução
    public void select(){
        Process selectedProcess = remove(0);

        System.out.print(selectedProcess);

        int runtime = selectedProcess.getRuntime() - 3;
        selectedProcess.setRuntime(runtime);
        
        if(runtime >= 3){
            insert(selectedProcess);
        } else {
            System.out.print(" [Encerrado] ");
        }
        System.out.println("");
    }

    public void start(){
        int id = 0;
        createProcess(4, id);
        id += 4;
        System.out.println("-----------------------------------");
        System.out.println("INICIADO COM 4 PROCESSOS");
        System.out.println("-----------------------------------");
        write();
        int executedTime = 0;
        do{
            select();
            for(int i = 0; i < 3; i++){
                executedTime += 1;
                if(executedTime==10) {
                    createProcess(8, id); 
                    id += 8;
                    System.out.println("-----------------------------------");
                    System.out.println("ADICIONADO 8 PROCESSOS");
                    System.out.println("-----------------------------------");
                }
                if(executedTime==20) {
                    createProcess(16, id);
                    id += 16;
                    System.out.println("-----------------------------------");
                    System.out.println("ADICIONADO 16 PROCESSOS");
                    System.out.println("-----------------------------------");
                }
            }
            write();
        } while(sizeList > 0);

        System.out.println("Finalizado");
    }

    public void createProcess(int num, int id){
        
        for (int i = 0; i < num; i++){
            String name = "Name" + num + i;
            Process process = new Process(id, name);
            id += 1;
            insert(process);
        }
    }

}

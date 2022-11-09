public class Process {
    private int id;
    private String name;
    private int runtime;
    private String message;
    
    public Process(int id, String name) {
        this.id = id;
        this.name = name;
        this.runtime = (int)(Math.random()*20);
        this.message = " Oi, sou o processo {" + id + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Oi, sou o processo {" + id + "}.";
    }
}

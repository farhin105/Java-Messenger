package sample;

//import util.NetworkUtil;

public class ReadThread implements Runnable {
    private static TableViewController table;
    private Thread thr;
    private NetworkUtil nc;
    private static ClientMessegeController controller;

    public ReadThread(NetworkUtil nc) {
        this.nc = nc;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            String s = (String) nc.read();
            controller.setMsg(s);


        } catch(Exception e) {
            System.out.println ("Error in ReadThread"+e);
        }
        //nc.closeConnection();
    }

    public static void setC(ClientMessegeController c){controller=c;}
}
public class Log {
    static void i(String message){
        System.out.println("[i] " + message);
    }

    static void d(String message){
        System.out.println("[d] " + message);
    }

    static void e(String message){
        System.err.println("[e] " + message);
    }
}

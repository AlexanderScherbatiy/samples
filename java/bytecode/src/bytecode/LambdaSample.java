package bytecode;

/**
 * Created by alexsch on 2/10/2017.
 */
public class LambdaSample {

    /*
      public static void run(java.lang.Runnable);
        Code:
           0: new           #3                  // class java/lang/Thread
           3: dup
           4: aload_0
           5: invokespecial #4                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
           8: invokevirtual #5                  // Method java/lang/Thread.start:()V
          11: return
    */
    public static void run(Runnable r) {
        new Thread(r).start();
    }

    /*
      private static void hello();
        Code:
           0: getstatic     #11                 // Field java/lang/System.out:Ljava/io/PrintStream;
           3: ldc           #12                 // String Hello World!
           5: invokevirtual #13                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
           8: return

     */
    private static void hello() {
        System.out.println("Hello World!");
    }

    /*
      public static void runLambda();
        Code:
           0: invokedynamic #9,  0              // InvokeDynamic #0:run:()Ljava/lang/Runnable;
           5: invokestatic  #8                  // Method run:(Ljava/lang/Runnable;)V
           8: return

       private static void lambda$runLambda$0();
        Code:
           0: invokestatic  #1                  // Method hello:()V
           3: return
    */
    public static void runLambda() {
        run(() -> hello());
    }

    /*
      public static void runLambdaStaticMethod();
        Code:
           0: invokedynamic #10,  0             // InvokeDynamic #1:run:()Ljava/lang/Runnable;
           5: invokestatic  #8                  // Method run:(Ljava/lang/Runnable;)V
           8: return

     */
    public static void runLambdaStaticMethod() {
        run(LambdaSample::hello);
    }

    /*
      public static void runAnonymousClasses();
        Code:
           0: new           #6                  // class bytecode/LambdaSample$1
           3: dup
           4: invokespecial #7                  // Method bytecode/LambdaSample$1."<init>":()V
           7: invokestatic  #8                  // Method run:(Ljava/lang/Runnable;)V
          10: return

      static void access$000();
        Code:
           0: invokestatic  #1                  // Method hello:()V
           3: return

    class bytecode.LambdaSample$1 implements java.lang.Runnable {
      bytecode.LambdaSample$1();
        Code:
           0: aload_0
           1: invokespecial #1                  // Method java/lang/Object."<init>":()V
           4: return

      public void run();
        Code:
           0: invokestatic  #2                  // Method bytecode/LambdaSample.access$000:()V
           3: return
    }
         */
    public static void runAnonymousClasses() {
        run(new Runnable() {
            @Override
            public void run() {
                hello();
            }
        });
    }
}
package mockit.usage.cut;

import java.lang.reflect.InvocationTargetException;

public class SwingUtilities {
    public static void invokeAndWait(final Runnable doRun)
            throws InterruptedException, InvocationTargetException
    {
        Thread thread = new Thread(doRun);
        thread.start();
        thread.join();
    }
}

package software.app.takeaway;

import android.content.Context;

public class MyContext {
    private static Context context = null;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context c) {
        context = c;
    }
}

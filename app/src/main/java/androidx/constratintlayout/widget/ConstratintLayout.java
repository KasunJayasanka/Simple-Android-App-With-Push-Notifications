package androidx.constratintlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class ConstratintLayout extends View {
    public ConstratintLayout(Context context) {
        this(context, null);
    }

    public ConstratintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConstratintLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}

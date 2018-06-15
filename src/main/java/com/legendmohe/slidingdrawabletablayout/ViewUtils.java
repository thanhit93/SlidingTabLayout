package com.legendmohe.slidingdrawabletablayout;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

class ViewUtils {

    static final ValueAnimatorCompat.Creator DEFAULT_ANIMATOR_CREATOR
            = new ValueAnimatorCompat.Creator() {
        @Override
        public ValueAnimatorCompat createAnimator() {
            return new ValueAnimatorCompat(new ValueAnimatorCompatImplHoneycombMr1());
        }
    };

    private interface ViewUtilsImpl {
        void setBoundsViewOutlineProvider(View view);
    }

    private static class ViewUtilsImplBase implements ViewUtilsImpl {
        @Override
        public void setBoundsViewOutlineProvider(View view) {
            // no-op
        }
    }

    private static class ViewUtilsImplLollipop implements ViewUtilsImpl {
        @Override
        public void setBoundsViewOutlineProvider(View view) {
            setBoundsViewOutlineProviderLollipop(view);
        }
    }

    private static final ViewUtilsImpl IMPL;

    static {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            IMPL = new ViewUtilsImplLollipop();
        } else {
            IMPL = new ViewUtilsImplBase();
        }
    }

    static void setBoundsViewOutlineProvider(View view) {
        IMPL.setBoundsViewOutlineProvider(view);
    }

    static ValueAnimatorCompat createAnimator() {
        return DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static void setBoundsViewOutlineProviderLollipop(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

}

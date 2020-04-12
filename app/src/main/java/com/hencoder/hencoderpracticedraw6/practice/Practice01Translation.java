package com.hencoder.hencoderpracticedraw6.practice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw6.R;
import com.hencoder.hencoderpracticedraw6.Utils;

import static android.os.Build.VERSION.SDK_INT;
import static com.hencoder.hencoderpracticedraw6.Utils.dpToPixel;

public class Practice01Translation extends RelativeLayout {
    Button animateBt;
    ImageView imageView;

    public Practice01Translation(Context context) {
        super(context);
    }

    public Practice01Translation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01Translation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        if (SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            // 给音乐图标加上合适的阴影
            imageView.setOutlineProvider(new MusicOutlineProvider());
        }

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                // TODO 在这里处理点击事件，通过 View.animate().translationX/Y/Z() 来让 View 平移

                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView, "translationX", Utils.dpToPixel(100));
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView, "translationX", 0);
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView, "translationY", Utils.dpToPixel(50));
                ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(imageView, "translationY", 0);

                ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(imageView, "translationZ", Utils.dpToPixel(15));
                ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(imageView, "translationZ", 0);


                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(objectAnimator1
                        , objectAnimator2
                        , objectAnimator3
                        , objectAnimator4
                        , objectAnimator5
                        , objectAnimator6);

                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        animateBt.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        animateBt.setEnabled(true);
                    }
                });
                animatorSet.start();
            }
        });
    }

    /**
     * 为音乐图标设置三角形的 Outline。
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    class MusicOutlineProvider extends ViewOutlineProvider {
        Path path = new Path();

        {
            path.moveTo(0, dpToPixel(10));
            path.lineTo(dpToPixel(7), dpToPixel(2));
            path.lineTo(dpToPixel(116), dpToPixel(58));
            path.lineTo(dpToPixel(116), dpToPixel(70));
            path.lineTo(dpToPixel(7), dpToPixel(128));
            path.lineTo(0, dpToPixel(120));
            path.close();
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setConvexPath(path);
        }
    }
}
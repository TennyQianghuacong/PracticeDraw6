package com.hencoder.hencoderpracticedraw6.practice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw6.R;

public class Practice03Scale extends RelativeLayout {
    Button animateBt;
    ImageView imageView;

    public Practice03Scale(Context context) {
        super(context);
    }

    public Practice03Scale(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice03Scale(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);

        animateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // TODO 在这里处理点击事件，通过 View.animate().scaleX/Y() 来让 View 放缩
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.5f);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleX", 1);
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.5f);
                ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(imageView, "scaleY", 1);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(objectAnimator1
                        , objectAnimator2
                        , objectAnimator3
                        , objectAnimator4);

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
}

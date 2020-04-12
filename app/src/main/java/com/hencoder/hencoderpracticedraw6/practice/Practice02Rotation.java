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
import com.hencoder.hencoderpracticedraw6.Utils;

public class Practice02Rotation extends RelativeLayout {
    Button animateBt;
    ImageView imageView;

    public Practice02Rotation(Context context) {
        super(context);
    }

    public Practice02Rotation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02Rotation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                // // TODO 在这里处理点击事件，通过 View.animate().rotation/X/Y() 来让 View 旋转
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView, "rotation", 180);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView, "rotation", 0);
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView, "rotationX", 180);
                ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(imageView, "rotationX", 0);

                ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(imageView, "rotationY", 180);
                ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(imageView, "rotationY", 0);


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
}
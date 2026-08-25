package com.error404.reelix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class ShimmerTextView extends TextView {
    private boolean mAnimating;
    private Matrix mGradientMatrix;
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mTranslate;
    private int mViewWidth;

    public ShimmerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mViewWidth = 0;
        this.mTranslate = 0;
        this.mAnimating = true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mAnimating || this.mGradientMatrix == null) {
            return;
        }
        this.mTranslate += this.mViewWidth / 10;
        if (this.mTranslate > 2 * this.mViewWidth) {
            this.mTranslate = -this.mViewWidth;
        }
        this.mGradientMatrix.setTranslate(this.mTranslate, 0);
        this.mLinearGradient.setLocalMatrix(this.mGradientMatrix);
        postInvalidateDelayed(50);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mViewWidth == 0) {
            this.mViewWidth = getMeasuredWidth();
            if (this.mViewWidth > 0) {
                this.mPaint = getPaint();
                this.mLinearGradient = new LinearGradient(-this.mViewWidth, 0, 0, 0, new int[]{-33554431, -1, -33554431}, new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
                this.mPaint.setShader(this.mLinearGradient);
                this.mGradientMatrix = new Matrix();
            }
        }
    }
}
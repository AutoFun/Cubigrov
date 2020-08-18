package com.example.cubigrov.showData;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ArcView extends View {

    //����������ʾ��Բ��Paint
    private Paint mArcPaint;
    //����������paint
    private Paint mTextPaint;
    //Բ����ʼ�ĽǶ�
    private float startAngle=135;
    //Բ�������ĽǶ�
    private float endAngle=45;
    //Բ�������Ŀ�ʼ�ͽ�����ļнǴ�С
    private float mAngle=270;
    //��ǰ���ȼнǴ�С
    private float mIncludedAngle=0;
    //Բ���Ļ��ʵĿ��
    private float mStrokeWith=10;
    //���ĵ���������
    private String mDes="";
    //����Ч�������ݼ����/Сֵ
    private int mAnimatorValue,mMinValue,mMaxValue;
    //���ĵ��XY����
    private float centerX,centerY;

    public ArcView(Context context) {
        this(context,null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init();
    }

    private void initPaint() {
        //Բ����paint
        mArcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        //�����
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.parseColor("#85FFDD"));
        //����͸���ȣ���ֵΪ0-255��
        mArcPaint.setAlpha(100);
        //���û��ʵĻ�������״
        mArcPaint.setStrokeJoin(Paint.Join.ROUND);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        //���û�������
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(dp2px(mStrokeWith));

        //�������ֵ�paint
        mTextPaint=new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#FFFFFF"));
        //�����ı��Ķ��뷽ʽ
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        //mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.dp_12));
        mTextPaint.setTextSize(dp2px(25));

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX=getWidth()/2;
        centerY=getHeight()/2;
        //��ʼ��paint
        initPaint();
        //���ƻ���
        drawArc(canvas);
        //�����ı�
        drawText(canvas);
    }

    /**
     * �����ı�
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Rect mRect=new Rect();
        String mValue=String.valueOf(mAnimatorValue);
        //�������ĵ���ֵ
        mTextPaint.getTextBounds(mValue,0,mValue.length(),mRect);
        canvas.drawText(String.valueOf(mAnimatorValue) + "%",centerX,centerY+mRect.height()/2,mTextPaint);

        //����������������
        mTextPaint.setColor(Color.parseColor("#FFFFFF"));
        mTextPaint.setTextSize(dp2px(12));
        mTextPaint.getTextBounds(mDes,0,mDes.length(),mRect);
        canvas.drawText(mDes,centerX,centerY+2*mRect.height()+dp2px(40),mTextPaint);

//        //������Сֵ
//        String minValue=String.valueOf(mMinValue);
//        String maxValue=String.valueOf(mMaxValue);
//        mTextPaint.setTextSize(dp2px(18));
//        mTextPaint.getTextBounds(minValue,0,minValue.length(),mRect);
//        canvas.drawText(minValue, (float) (centerX-0.6*centerX-dp2px(5)), (float) (centerY+0.75*centerY+mRect.height()+dp2px(5)),mTextPaint);
//        //�������ָ
//        mTextPaint.getTextBounds(maxValue,0,maxValue.length(),mRect);
//        canvas.drawText(maxValue, (float) (centerX+0.6*centerX+dp2px(5)), (float) (centerY+0.75*centerY+mRect.height()+dp2px(5)),mTextPaint);
    }

    /**
     * ���Ƶ�ǰ��Բ��
     * @param canvas
     */
    private void drawArc(Canvas canvas) {
        //����Բ������
        RectF mRectF=new RectF(mStrokeWith+dp2px(5),mStrokeWith+dp2px(5),getWidth()-mStrokeWith-dp2px(5),getHeight()-mStrokeWith);
        canvas.drawArc(mRectF,startAngle,mAngle,false,mArcPaint);
        //���Ƶ�ǰ��ֵ��Ӧ��Բ��
        mArcPaint.setColor(Color.parseColor("#85FFDD"));
        //���ݵ�ǰ���ݻ��ƶ�Ӧ��Բ��
        canvas.drawArc(mRectF,startAngle,mIncludedAngle,false,mArcPaint);
    }

    /**
     * Ϊ���ƻ��ȼ��������ö���
     *
     * @param startAngle ��ʼ�Ļ���
     * @param currentAngle ��Ҫ���ƵĻ���
     * @param currentValue ��Ҫ���Ƶ�����
     * @param time ����ִ�е�ʱ��
     */
    private void setAnimation(float startAngle, float currentAngle,int currentValue, int time) {
        //���Ƶ�ǰ���ݶ�Ӧ��Բ���Ķ���Ч��
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(startAngle, currentAngle);
        progressAnimator.setDuration(time);
        progressAnimator.setTarget(mIncludedAngle);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mIncludedAngle = (float) animation.getAnimatedValue();
                //���»��ƣ���Ȼ�������Ч��
                postInvalidate();
            }
        });
        //��ʼִ�ж���
        progressAnimator.start();

        //�������ݵĶ���Ч��
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mAnimatorValue, currentValue);
        valueAnimator.setDuration(2500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAnimatorValue = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * ��������
     * @param minValue ��Сֵ
     * @param maxValue ���ֵ
     * @param currentValue ��ǰ���Ƶ�ֵ
     * @param des ������Ϣ
     */
    public void setValues(int minValue,int maxValue, int currentValue,String des) {
        mDes=des;
        mMaxValue=maxValue;
        mMinValue=minValue;
        //��ȫ����
        if (currentValue > maxValue) {
            currentValue = maxValue;
        }
        //���㻡�ȱ���
        float scale = (float) currentValue / maxValue;
        //���㻡��
        float currentAngle = scale * mAngle;
        //��ʼִ�ж���
        setAnimation(0, currentAngle, currentValue,2500);
    }

    public float dp2px(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

}

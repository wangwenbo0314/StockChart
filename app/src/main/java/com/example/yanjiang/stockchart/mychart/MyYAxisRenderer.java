package com.example.yanjiang.stockchart.mychart;

import android.graphics.Canvas;
import android.text.TextUtils;

import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * author：ajiang
 * mail：1025065158@qq.com
 * blog：http://blog.csdn.net/qqyanjiang
 */
public class MyYAxisRenderer extends YAxisRenderer {
    protected MyYAxis mYAxis;
    public MyYAxisRenderer(ViewPortHandler viewPortHandler, MyYAxis yAxis, Transformer trans) {
        super(viewPortHandler, yAxis, trans);
        mYAxis = yAxis;
    }

    @Override
    protected void computeAxisValues(float min, float max) {
        /*只显示最大最小情况下*/
        if (mYAxis.isShowOnlyMinMaxEnabled()) {
            mYAxis.mEntryCount = 2;
            mYAxis.mEntries = new float[2];
            mYAxis.mEntries[0] = min;
            mYAxis.mEntries[1] = max;
            return;
        }
    }

    @Override
    protected void drawYLabels(Canvas c, float fixedPosition, float[] positions, float offset) {
        /*当有最小text的时候*/
        if (!TextUtils.isEmpty(mYAxis.getMinValue()) && mYAxis.isShowOnlyMinMaxEnabled()) {
            for (int i = 0; i < mYAxis.mEntryCount; i++) {
                String text = mYAxis.getFormattedLabel(i);
                if (i == 0) {
                    text = mYAxis.getMinValue();
                }
                if (i == 1) {
                    c.drawText(text, fixedPosition, mViewPortHandler.contentTop() + offset * 2.5f + 3, mAxisLabelPaint);
                    //c.drawText(text, mViewPortHandler.contentRight(), mViewPortHandler.contentTop() + offset * 2.5f + 3, mAxisLabelPaint);
                } else if (i == 0) {
                    c.drawText(text, fixedPosition, mViewPortHandler.contentBottom() - 3, mAxisLabelPaint);
                    //c.drawText(text, mViewPortHandler.contentRight(), mViewPortHandler.contentBottom() - 3, mAxisLabelPaint);
                }
            }
        }
    }

}
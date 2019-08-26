package com.android.mb.assistant.widget;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;

/**
 *
 *描述   ：金额输入过滤器，限制小数点后输入位数
 *
 * 默认限制小数点2位
 * 默认第一位输入小数点时，转换为0.
 * 如果起始位置为0,且第二位跟的不是".",则无法后续输入
 *
 *作者   ：Created by DuanRui on 2017/9/28.
 */

public class NumberValueFilter extends DigitsKeyListener {


    //输入的最大金额
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private static final String ZERO = "0";

    public NumberValueFilter() {
    }

    /**
     * @param source    新输入的字符串
     * @param start     新输入的字符串起始下标，一般为0
     * @param end       新输入的字符串终点下标，一般为source长度-1
     * @param dest      输入之前文本框内容
     * @param dstart    原内容起始坐标，一般为0
     * @param dend      原内容终点坐标，一般为dest长度-1
     * @return          输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //如果输入前字符串为空，后输入0无法输入（首位不能为0）
        if(TextUtils.isEmpty(destText) && sourceText.equals("0")){
            return "";
        }

        return new SpannableStringBuilder(source, start, end);
    }
}

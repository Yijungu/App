package com.example.myapplication;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;


class DayDecorator implements DayViewDecorator {

    ArrayList<CalendarDay> date = new ArrayList<CalendarDay>();

    public DayDecorator() {
    }

    public void setDate(ArrayList<CalendarDay> day){
        date = day;
    }

    // true를 리턴 시 모든 요일에 내가 설정한 드로어블이 적용된다
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date.contains(day);
    }

    // 일자 선택 시 내가 정의한 드로어블이 적용되도록 한다
    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5F, Color.parseColor("#1D8300")));// 달력 안의 모든 숫자들이 볼드 처리됨
    }
}


package com.example.myapplication;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;


class TotalDecorator implements DayViewDecorator {

    ArrayList<ArrayList<CalendarDay>> date = new ArrayList<ArrayList<CalendarDay>>();

    public TotalDecorator() {
    }

    public void setDate(ArrayList<ArrayList<CalendarDay>> day){
        date = day;
    }

    // true를 리턴 시 모든 요일에 내가 설정한 드로어블이 적용된다
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date.get(1).contains(day);
    }

    // 일자 선택 시 내가 정의한 드로어블이 적용되도록 한다
    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5F, Color.parseColor("#FFFF4081")));  // 달력 안의 모든 숫자들이 볼드 처리됨
    }
}
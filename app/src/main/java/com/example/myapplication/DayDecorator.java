package com.example.myapplication;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;


class DayDecorator implements DayViewDecorator {

    ArrayList<CalendarDay> date = new ArrayList<CalendarDay>();
    String colorString = "";
    float position = 0;
    int size = 0;

    public DayDecorator(String color, int size) {
        colorString = color;
        this.size = size;
        position = (float) (size+1)/2;
    }

    public void setDate(CalendarDay day){
        date.add(day);
    }

    public void setPosition(int i){
        position = i;
    }

    public void resetPosition(){
        position = (float) (size+1)/2;
    }

    public boolean contain(CalendarDay day){
        return date.contains(day);
    }

    public void deleteDay(CalendarDay day){
        date.remove(day);
    }
    // true를 리턴 시 모든 요일에 내가 설정한 드로어블이 적용된다
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date.contains(day);
    }

    // 일자 선택 시 내가 정의한 드로어블이 적용되도록 한다
    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotsSpan(5F, Color.parseColor(colorString), position, size));// 달력 안의 모든 숫자들이 볼드 처리됨
    }
}


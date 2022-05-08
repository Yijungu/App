package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;


public class TextViewPagerAdapter extends PagerAdapter {

    // LayoutInflater 서비스 사용을 위한 Context 참조 저장.
    private Context mContext = null;
    public ArrayList<Calendar> calendar;
    FrameLayout frameLayout;
    LayoutInflater inflater;
    DayDecorator dotDecorator;
    MaterialCalendarView calendarView;
    int page = 0;
    int count = 3;
    MainActivity main;

    public TextViewPagerAdapter() {
        calendar = new ArrayList<Calendar>();
    }

    // Context를 전달받아 mContext에 저장하는 생성자 추가.
    public TextViewPagerAdapter(Context context, FrameLayout frameLayout) {
        mContext = context;
        calendar = new ArrayList<Calendar>();
        this.frameLayout = frameLayout;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cal = inflater.inflate(R.layout.activity_calendar, frameLayout, true);
        calendarView = (MaterialCalendarView) cal.findViewById(R.id.calendar_view);
        calendar.add(new Calendar());
        calendar.add(new Calendar());
        calendar.add(new Calendar());
        calendar.add(new Calendar());
        calendar.add(new Calendar());
        calendar.add(new Calendar());
        dotDecorator = new DayDecorator();
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay day, boolean selected) {
                Log.v("asd","asd");
                calendar.get(page).date.add(day);
                dotDecorator.setDate(calendar.get(page).date);
                calendarView.addDecorator(dotDecorator);
            }
        });
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;

        if (mContext != null) {
            view = inflater.inflate(R.layout.page, container, false);

            TextView textView = (TextView) view.findViewById(R.id.ticket_page);
            textView.setText("" + position);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView ticket = (TextView) view.findViewById(R.id.ticket_page);
                    int beforePage = page;
                    page = Integer.parseInt(textView.getText().toString());
                    dotDecorator.setDate(calendar.get(page).date);
                    calendarView.addDecorator(dotDecorator);
                    Log.v("test", "2 : " + calendar.size());
                }
            });
        }
        // 뷰페이저에 추가.
        container.addView(view);
        return view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 6;    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}

class Calendar{

    public ArrayList<CalendarDay> date;


    public Calendar(){
        date = new ArrayList<CalendarDay>();
    }
}

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
        view.addSpan(new DotSpan(5F, Color.parseColor("#1D872A")));  // 달력 안의 모든 숫자들이 볼드 처리됨
    }
}
package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class TextViewPagerAdapter extends PagerAdapter {

    // LayoutInflater 서비스 사용을 위한 Context 참조 저장.
    private Context mContext = null;
    FrameLayout frameLayout;
    LayoutInflater inflater;
    DayDecorator dotDecorator[];
    MaterialCalendarView calendarView;
    String color[] = {"#FFFF00", "#0000FF", "#009900"};
    int size = 3;
    int page = 0;


    public TextViewPagerAdapter(){
    }

    // Context를 전달받아 mContext에 저장하는 생성자 추가.
    public TextViewPagerAdapter(Context context, FrameLayout frameLayout) {
        mContext = context;
        this.frameLayout = frameLayout;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cal = inflater.inflate(R.layout.activity_calendar, frameLayout, true);
        calendarView = (MaterialCalendarView) cal.findViewById(R.id.calendar_view);
        dotDecorator = new DayDecorator[size];
        for(int i = 0; i < size; i++) {
            dotDecorator[i] = new DayDecorator(color[i], size);
        }
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay day, boolean selected) {
                if(page == -1) return;
                if(dotDecorator[page].contain(day)) dotDecorator[page].deleteDay(day);
                else dotDecorator[page].setDate(day);
                calendarView.invalidateDecorators();
            }
        });
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;

        if (mContext != null) {
            if(position == 0){
                view = inflater.inflate(R.layout.activity_total, container, false);

                Button totalButton = (Button) view.findViewById(R.id.total_button);

                totalButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        page = -1;
                        calendarView.removeDecorators();
                        for(int i = 0; i < size; i++){
                            dotDecorator[i].setPosition(i+1);
                        }
                        calendarView.addDecorators(dotDecorator);
                        for(int i = 0; i < size; i++){
                            dotDecorator[i].resetPosition();
                        }
                    }
                });
            }
            else if (position == getCount()-1) {
                view = inflater.inflate(R.layout.activity_search, container, false);

                Button searchButton = (Button) view.findViewById(R.id.search_button);

                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, Searchable.class);
                        mContext.startActivity(intent);
                    }
                });
            } else {
                view = inflater.inflate(R.layout.page, container, false);

                TextView textView = (TextView) view.findViewById(R.id.ticket_page);
                textView.setText("" + (position-1));

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        page = Integer.parseInt(textView.getText().toString());
                        calendarView.removeDecorators();
                        calendarView.addDecorator(dotDecorator[page]);
                    }
                });
            }
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
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}


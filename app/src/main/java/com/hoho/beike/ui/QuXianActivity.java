package com.hoho.beike.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.egbert.rconcise.internal.http.Request;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hoho.beike.R;
import com.hoho.beike.bean.GongYiBean;
import com.hoho.beike.bean.QuxianBean;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.utils.Const;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/22
 * Time: 3:33 PM
 */
public class QuXianActivity extends DemoBase implements OnChartValueSelectedListener, OnChartGestureListener {

    @BindView(R.id.chart1)
    LineChart chart;
    XAxis xAxis;

    String title;
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view_shu1)
    View viewShu1;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.pop_tv)
    TextView popTv;
    @BindView(R.id.login_btn)
    Button loginBtn;

    String name;
    GongYiBean bean;
    List<String> list = new ArrayList<>();
    PopupWindow mPopWindow;
    View contentView;

    private final int[] colors = new int[]{
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quxian);
        ButterKnife.bind(this);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        title = getIntent().getExtras().getString("title");
        t1.setText(title);

        bean = (GongYiBean) getIntent().getSerializableExtra("bean");
        name = getIntent().getExtras().getString("name");
        getQuXian(0);

        contentView = LayoutInflater.from(this).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView);

        intChat();
    }

    @Override
    protected void saveToGallery() {

    }

    private void intChat() {
        {   // // Chart Style // //
            chart = findViewById(R.id.chart1);
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);
            chart.getDescription().setEnabled(false);
            chart.setDrawBorders(false);
            chart.setBorderColor(ContextCompat.getColor(this, R.color.colorAccent));
            chart.setBorderWidth(5f);

//            chart.getAxisLeft().setEnabled(false);
//            chart.getAxisRight().setDrawAxisLine(false);
//            chart.getAxisRight().setDrawGridLines(false);
//            chart.getXAxis().setDrawAxisLine(false);
//            chart.getXAxis().setDrawGridLines(false);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);

            chart.getLegend().setEnabled(false);

            // force pinch zoom along both axis
            chart.setPinchZoom(false);
            chart.setKeepPositionOnRotation(true);
//            chart.setAutoScaleMinMaxEnabled(true);

            Legend l = chart.getLegend();
//            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//            l.setOrientation(Legend.LegendOrientation.VERTICAL);
//            l.setDrawInside(false);
            l.setTextColor(R.color.colorAccent);
            // draw legend entries as lines
            l.setForm(Legend.LegendForm.LINE);

        }


        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setTextColor(ColorTemplate.rgb("#03DAC5"));
            xAxis.setAxisLineColor(ColorTemplate.rgb("#03DAC5"));
            xAxis.setAxisLineWidth(1f);
            xAxis.setDrawLabels(true);
//            xAxis.setDrawAxisLine(false);
//            xAxis.setAxisMinimum(0f);
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(new MyXAxisFormatter(list));

//            xAxis.setLabelCount(list.size());
//            ValueFormatter formatter = new ValueFormatter() {
//
//                @Override
//                public String getPointLabel(Entry entry) {
//                    LogUtils.e(entry.getX(), entry.getY());
//                    return super.getPointLabel(entry);
//                }
//
//                @Override
//                public String getAxisLabel(float value, AxisBase axis) {
//
//
//                    if (value > list.size() - 1) {
//                        return null;
//                    } else {
//                        if (value == -1) {
//                            return "0";
//                        } else {
//                            return list.get(Math.abs((int) value));
//                        }
//                    }
//
//
//                }
//            };
//                xAxis.setValueFormatter(formatter);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();
            yAxis.setTextColor(ColorTemplate.rgb("#03dac5"));
            yAxis.setDrawZeroLine(true);
            yAxis.setAxisLineColor(ColorTemplate.rgb("#03DAC5"));
            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);


            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
//            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(0f);
        }

        // draw points over time
        chart.animateX(1500);


        chart.resetTracking();

//
//        // get the legend (only possible after setting data)
//        Legend l = chart.getLegend();
//        l.setTextColor(R.color.colorAccent);
//        // draw legend entries as lines
//        l.setForm(Legend.LegendForm.LINE);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
    }
//    @Override
//    protected void saveToGallery() {
//        saveToGallery(chart, "LineChartActivity1");
//    }

    private void getQuXian(int id) {

        String sToken = SPUtils.getInstance().getString("token");
        Request.Builder.create("http://39.101.181.123:8080/bk/rest/bkGraphController/" + name + "&" + id)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .addHeader("X-AUTH-TOKEN", sToken)
                .setActivity(this)
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);
                        QuxianBean bean = QuxianBean.objectFromData(s);
                        if (bean.ok) {
                            List<QuxianBean.DataBean> data = bean.data;

                            setData(data);

                        }
                    }

                    @Override
                    public void onError(Exception e, String desp) {
                        super.onError(e, desp);
                        e.printStackTrace();
                        LogUtils.e(desp);
                    }

                    @Override
                    public void onFailure(int respCode, String desp) {
                        super.onFailure(respCode, desp);
                        LogUtils.e(respCode, desp);
                        startActivity(new Intent(QuXianActivity.this, LoginActivity2.class));
                    }
                })
                .get();
    }

    private void setData(List<QuxianBean.DataBean> dataBean) {

        chart.clear();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        ArrayList<Entry> values = new ArrayList<>();


        list.clear();
        for (int i = 0; i < dataBean.size(); i++) {
            list.add(dataBean.get(i).dt);
            values.add(new Entry(i, dataBean.get(i).tjrc));
        }
//        xAxis.setLabelCount(list.size());
//


        LineDataSet set1 = null;

        // create a dataset and give it a type
        set1 = new LineDataSet(values, "");

        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setDrawIcons(false);

        // black lines and points
        set1.setColor(ContextCompat.getColor(this, R.color.colorText));

//        // line thickness and point size
        set1.setLineWidth(3f);
        set1.setCircleRadius(3f);
//
//        // draw points as solid circles
        set1.setDrawCircleHole(true);
//
//        // customize legend entry
        set1.setFormLineWidth(1f);
        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);
//
//        // text size of values
        set1.setValueTextSize(9f);
        set1.setValueTextColor((ColorTemplate.rgb("#03dac5")));
//        // draw selection line as dashed
//        set1.enableDashedHighlightLine(10f, 5f, 0f);

        set1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + value;
            }

        });

        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        LineData data = new LineData(dataSets);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            chart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void showPopupWindow() {


        mPopWindow.setWidth(popTv.getWidth());
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView) contentView.findViewById(R.id.pop_manage);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTv.setText("日");
                getQuXian(0);
                mPopWindow.dismiss();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTv.setText("周");
                getQuXian(1);
                mPopWindow.dismiss();
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTv.setText("月");
                getQuXian(2);
                mPopWindow.dismiss();
            }
        });
        //相对位置 以mMenuTv为坐标,
        mPopWindow.showAsDropDown(popTv);
    }

    @OnClick({R.id.back_iv, R.id.pop_tv, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.pop_tv:
                if (!mPopWindow.isShowing()) {
                    showPopupWindow();
                }
                break;
            case R.id.login_btn:
                Intent intent = new Intent(QuXianActivity.this, DataRecordActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
                break;
        }
    }
}

class MyXAxisFormatter extends ValueFormatter {
    private final DecimalFormat mFormat;
    private String suffix;

    private List<String> list;

    public MyXAxisFormatter(List<String> list) {
        mFormat = new DecimalFormat("#");
        this.list = list;
    }


    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + suffix;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
//        LogUtils.e(mFormat.format(value));
        if (value >= 0 && value < list.size()) {
            return list.get(Integer.parseInt(mFormat.format(value)));
        } else {
            return mFormat.format(value);
        }
    }
}
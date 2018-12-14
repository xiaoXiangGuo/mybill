package gui.panel;

import gui.panelData.SpendData;
import service.SpendService;
import utils.ColorUtil;
import utils.GUIUtil;
import gui.widget.CircleProgressBar;

import javax.swing.*;
import java.awt.*;

public class SpendPanel extends WorkingPanel {
    private static SpendPanel instance = new SpendPanel();
    JLabel lMonthSpend = new JLabel("本月消费");
    JLabel lTodaySpend = new JLabel("今日消费");
    JLabel lAvgSpendPerDay = new JLabel("日均消费");
    JLabel lMonthLeft = new JLabel("本月剩余");
    JLabel lDayAvgAvailable = new JLabel("日均可用");
    JLabel lMonthLeftDay = new JLabel("距离月末");

    JLabel vMonthSpend = new JLabel("￥0");
    JLabel vTodaySpend = new JLabel("￥0");
    JLabel vAvgSpendPerDay = new JLabel("￥0");
    JLabel vMonthAvailable = new JLabel("￥0");
    JLabel vDayAvgAvailable = new JLabel("￥0");
    JLabel vMonthLeftDay = new JLabel("0天");
    private final CircleProgressBar bar;

    private SpendService sService = new SpendService();

    private SpendPanel() {
        setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);
        bar.setForegroundColor(ColorUtil.grayColor);
        GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);

        vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));

        add(center(), BorderLayout.CENTER);

//        add(west(),BorderLayout.WEST);
        add(south(), BorderLayout.SOUTH);
        addListener();
    }

    /**
     * 南边
     */
    private Component south() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));
        panel.add(lAvgSpendPerDay);
        panel.add(lMonthLeft);
        panel.add(lDayAvgAvailable);
        panel.add(lMonthLeftDay);
        panel.add(vAvgSpendPerDay);
        panel.add(vMonthAvailable);
        panel.add(vDayAvgAvailable);
        panel.add(vMonthLeftDay);
        return panel;
    }

    private Component center() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(west(), BorderLayout.WEST);
        panel.add(east());
        return panel;
    }

    /**
     * 东边
     */
    private Component east() {
        return bar;
    }

    /**
     * 西边
     */
    private Component west() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(lMonthSpend);
        panel.add(vMonthSpend);
        panel.add(lTodaySpend);
        panel.add(vTodaySpend);
        return panel;
    }

    public static SpendPanel getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SpendPanel instance = SpendPanel.getInstance();
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        SpendData spendData = sService.getSpendData();
        vMonthSpend.setText("￥"+spendData.monthSpend);
        vTodaySpend.setText("￥"+spendData.todaySpend);
        vAvgSpendPerDay.setText("￥"+spendData.avgSpendPerDay);
        vMonthAvailable.setText("￥"+spendData.monthAvailable);
        vDayAvgAvailable.setText("￥"+spendData.dayAvgAvailable);
        vMonthLeftDay.setText(spendData.monthLeftDay+"天");
        bar.setProgress(spendData.usagePercentage);
        bar.setForegroundColor(ColorUtil.getByPercentage(spendData.usagePercentage));
    }

    @Override
    public void addListener() {

    }
}

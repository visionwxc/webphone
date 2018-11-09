package com.wu.websocket.webphone.entity;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class ChartCreate {

    private static final String CHART_PATH = "D:\\charts\\";

    public String createTimeXYChart(String chartTitle,String x,String y,CategoryDataset xyDataset,String charName){
        JFreeChart chart = ChartFactory.createLineChart(chartTitle,x,y,xyDataset,PlotOrientation.VERTICAL,true,true,false);
        chart.setTextAntiAlias(false);
        chart.setBackgroundPaint(Color.white);
        //设置图标题的字体重新设置title
        Font font = new Font("SansSerif",Font.BOLD,25);
        TextTitle title = new TextTitle(chartTitle);
        title.setFont(font);
        chart.setTitle(title);
        //设置面板字体
        Font labelFont = new Font("SansSerif",Font.TRUETYPE_FONT,12);
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        categoryPlot.setDomainGridlinesVisible(true);
        categoryPlot.setRangeGridlinesVisible(true);
        categoryPlot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩
        categoryPlot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩
        categoryPlot.setBackgroundPaint(Color.lightGray);
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();

        domainAxis.setLabelFont(labelFont);// 轴标题
        domainAxis.setTickLabelFont(labelFont);// 轴数值

        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的
        // Lable
        // 45度倾斜
        // 设置距离图片左端距离
        domainAxis.setLowerMargin(0.0);
        // 设置距离图片右端距离
        domainAxis.setUpperMargin(0.0);

        NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setAutoRangeIncludesZero(true);

        // 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryPlot
                .getRenderer();

        lineandshaperenderer.setDefaultShapesVisible(true); // series 点（即数据点）可见
        lineandshaperenderer.setDefaultLinesVisible(true); // series 点（即数据点）间有连线可见

        // 显示折点数据
        // lineandshaperenderer.setBaseItemLabelGenerator(new
        // StandardCategoryItemLabelGenerator());
        // lineandshaperenderer.setBaseItemLabelsVisible(true);

        FileOutputStream fos_jpg = null;
        try {
            isChartPathExist(CHART_PATH);
            String chartName = CHART_PATH + charName;
            fos_jpg = new FileOutputStream(chartName);

            // 将报表保存为png文件
            ChartUtils.writeChartAsPNG(fos_jpg, chart, 500, 510);

            return chartName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fos_jpg.close();
                System.out.println("create time-createTimeXYChar.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void isChartPathExist(String chartPath) {
        File file = new File(chartPath);
        if (!file.exists()) {
            file.mkdirs();
            // log.info("CHART_PATH="+CHART_PATH+"create.");
        }
    }

    public CategoryDataset getBarData(double[][] data, String[] rowKeys,
                                      String[] columnKeys) {
        return DatasetUtils
                .createCategoryDataset(rowKeys, columnKeys, data);

    }

    public static void main(String[] args) {
        double[][] data = new double[][]{{672,766,233,540,126},{325,521,210,340,106},{332,256,523,240,526}};
        String[] rowKeys = {"苹果","梨子","葡萄"};
        String[] columnKeys = {"北京","上海","广州","成都","深圳"};
        ChartCreate chartCreate = new ChartCreate();
        CategoryDataset dataset = chartCreate.getBarData(data,rowKeys,columnKeys);
        chartCreate.createTimeXYChart("折线图","x轴","y轴",dataset,"lineAndShap.jpg");
    }
}

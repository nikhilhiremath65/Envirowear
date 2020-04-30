package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import data.Constants;

/**
 *  
 * @author somesh
 * @since 04-08-2020
 */

public class GraphPanel extends JPanel {
	
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	
	private Color gridColor;
	private Color upperTempColor; 
	private Color lowerTempColor;
	
    private int padding;
    private int yPoints;
    private int maxTemp;
    private int minTemp;
    private int pointWidth;
    private int labelPadding;
   
    private List<Double> upperTempList;
    private List<Double> lowerTempList;

	public GraphPanel() {
		
    	this.setMaximumSize(new Dimension(400,400));
    	
    	this.gridColor = new Color(200, 200, 200, 200);
    	this.upperTempColor = new Color(114, 147, 203, 180);
    	this.lowerTempColor = new Color(62, 150, 81, 180);		
    	
        this.upperTempList = new ArrayList<Double>();
        this.lowerTempList = new ArrayList<Double>();
        
        this.padding = 25;
        this.labelPadding = 25;
        this.pointWidth = 4;
        this.yPoints = 9;
        this.maxTemp = (int) Constants.UPPER_TEMP_LIMIT;
        this.minTemp = (int) Constants.LOWER_TEMP_LIMIT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xPoints;
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (upperTempList.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (maxTemp - minTemp);
        
        List<Point> upperGraphPoints = generatePoints(upperTempList, xScale, yScale);
        List<Point> lowerGraphPoints = generatePoints(lowerTempList, xScale, yScale);
     
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, 
        		padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, 
        		getWidth() - padding, getHeight() - padding - labelPadding);
        
        if(upperTempList.size() >= lowerTempList.size())
        	xPoints = upperTempList.size() - 1;
        else
        	xPoints = lowerTempList.size() - 1;
        
        drawYaxis(g2,yPoints);
        drawXaxis(g2,xPoints,((int) ((xPoints / 5.0)) + 1));
        
        drawLine(g2,upperGraphPoints,upperTempColor);
        drawLine(g2,lowerGraphPoints,lowerTempColor);
           
    }
    
    private void drawLine(Graphics2D g2, List<Point> points, Color color) {
    	g2.setColor(color);
    	g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < points.size() - 1; i++) {
            int x1 = points.get(i).x;
            int y1 = points.get(i).y;
            int x2 = points.get(i + 1).x;
            int y2 = points.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
    }
    
    private void drawXaxis(Graphics2D g2, int points, int p) {
    	
    	for (int i = 0; i < points; i++) {
            if (points > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) /
                		points + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if (i % p == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - 
                    		labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }
    }
    
    private void drawYaxis(Graphics2D g2, int points) {
    	
    	for (int i = 0; i < points + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / points + padding + labelPadding);
            int y1 = y0;
            
            g2.setColor(gridColor);
            g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
            g2.setColor(Color.BLACK);
            String yLabel = ((int) ((minTemp + (maxTemp - minTemp) * ((i * 1.0) / points)) * 100)) / 100.0 + "";
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            g2.drawLine(x0, y0, x1, y1);
        }
    }
    
    private List<Point> generatePoints(List<Double> tempList, double xScale, double yScale){
    	
    	List<Point> points = new ArrayList<>();
    	for (int i = 0; i < tempList.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((maxTemp - tempList.get(i)) * yScale + padding);
            points.add(new Point(x1, y1));
        }
    	return points;
    }
    
    public void setUpperTempList(List<Double> upperTempList) {
		this.upperTempList = upperTempList;
	}
	
	public void setLowerTempList(List<Double> lowerTempList) {
		this.lowerTempList = lowerTempList;
	}
}
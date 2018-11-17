package myMath;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.PlotArea;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.AbstractLineRenderer2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


public class SineGraph extends JFrame{
	//private static final long serialVersionUID=1L;
	
	
	public SineGraph()  throws FileNotFoundException,IOException {
		setTitle("Polynomial Drawer beta'");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,800);
		
		DataTable data=new DataTable(Double.class,Double.class);
		DataTable datamax=new DataTable(Double.class,Double.class);
		DataTable test=new DataTable(Double.class,Double.class);
		Polynom p=new Polynom("0.2x^4+-1.5x^3+3.0x^2+-1.0x+-5");
		System.out.println("The polynom is "+p.toString());
		Polynom pc=new Polynom("0.2x^4+-1.5x^3+3.0x^2+-1.0x+-5");
		p.derivative();
		for(double x=-3.0;x<=6;x+=0.01) {
			double y=pc.f(x);
			data.add(x,y);
			if((p.f(x)+0.01>0)&&(p.f(x)-0.01<0)){
				datamax.add(x,pc.f(x));
			}else 
			if((p.f(x)+0.01<0)&&(p.f(x)-0.01>0)) {
				datamax.add(x,pc.f(x));
				
			}
		}
		
		XYPlot plot=new XYPlot(data);
		plot.add(test);
		String area="The area of this polynom is "+p.area(-3.0,6,0.01)/2;
		System.out.println(area);
		plot.add(datamax);
		
		PointRenderer points=new DefaultPointRenderer2D();
		Shape newShape = new Rectangle.Double(-5.0, -10.0, 10.0, 20.0);
		points.setShape(newShape);
		points.setColor(Color.RED);
		points.setValueVisible(true);
		plot.setPointRenderers(datamax,points);
		PointRenderer pointstest=new DefaultPointRenderer2D();
		pointstest.setShape(newShape);
		points.setColor(Color.green);
		points.setValueVisible(true);
		plot.setPointRenderers(test,pointstest);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer Lines = new DefaultLineRenderer2D();
		Lines.setColor(Color.black);
		plot.setLineRenderers(data, Lines);
		plot.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(20.0);
		
		
	}
	public static void main(String[] args) {
		SineGraph frame=null;
		try {
			frame=new SineGraph();
		} catch (IOException e) {
		}
		frame.setVisible(true);
	}
	
	
}

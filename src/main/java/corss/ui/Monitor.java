package corss.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Monitor extends JFrame {

	//详细数据
	private DefaultListModel<String> dml;

	public Monitor() {
		init();
		windowClose();
	}
    
    public static void main(String args[]){
        new Monitor();
    }

	/**
	 * 窗口关闭
	 */
	public void windowClose(){
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * ui初始化
	 */
	public void init(){
		layoutInit(this);

		menuInit(this);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(5, 5));

		//状态总数据
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel.add(panel_1,BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		//详细数据
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.PINK);
		panel_2.setLayout(new BorderLayout(5, 5));
		panel.add(panel_2,BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(true);
		//scrollPane.setSize(null);

		panel_2.add(scrollPane,BorderLayout.CENTER);

		setDml(new DefaultListModel<String>());
		getDml().add(0,"a");
		getDml().add(1,"b");
		getDml().add(2,"c");

		JList list = new JList(new String[]{"1","2","1","2","1","2"});

		scrollPane.add(list);

	}

	/**
	 * 布局初始化
	 * @param jFrame
	 */
	private void layoutInit(JFrame jFrame) {
		jFrame.setTitle("道口通道监控");
		jFrame.setSize(600, 700);
		jFrame.setVisible(true);
		jFrame.setLocationRelativeTo(null);
	}

	/**
	 * 菜单初始化
	 * @param jFrame
	 */
	private void menuInit(JFrame jFrame) {
		JMenuBar menuBar = new JMenuBar();
		jFrame.setJMenuBar(menuBar);

		JMenu menu = new JMenu("菜单");
		menuBar.add(menu);
		
		JButton button = new JButton("关于");
		menu.add(button);

	}

	public DefaultListModel<String> getDml() {
		return dml;
	}

	public void setDml(DefaultListModel<String> dml) {
		this.dml = dml;
	}
}

package corss.ui;

import corss.ui.data.ChannelTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;


public class Monitor extends JFrame {
    //表格头部
    private final Vector title = new Vector();
    //表格数据
    private Vector<Vector> tableData = new Vector<Vector>();

    {
        title.add("设备id");
        title.add("通道状态");
        //title.add("设备id");
        //title.add("设备id");
    }

    //详细数据
    private ChannelTableModel dml = new ChannelTableModel(tableData, title);
    //排序
    private TableRowSorter sorter = new TableRowSorter<ChannelTableModel>(dml);
    private JTable jTable = new JTable(dml);

    //设备总数量
    private JLabel countNum;
    //已注册通道
    private JLabel registerNum;
    //未注册通道
    private JLabel uncheckedNum;
    //异常通道数量
    private JLabel warnNum;

    public Monitor() {

        init();
        windowClose();
        //添加排序
        jTable.setRowSorter(sorter);

        setVisible(true);

    }

    /*public static void main(String args[]) {
        Monitor monitor = new Monitor();

        Random random = new Random();
        Vector<SortVector> sortVectors = new Vector<>();
        for (int i = 0; i < 50; i++) {
            SortVector strings = new SortVector<>();
            strings.add(i);

            int i1 = random.nextInt(2);
            if (i1 == 1) {
                strings.add(ChannelState.CHANNEL_RUNNING);
            } else {
                strings.add(ChannelState.CHANNEL_CLOSED);
            }
            sortVectors.add(strings);
        }
        RowSorter sorter = new TableRowSorter(monitor.getDml());
        monitor.getjTable().setRowSorter(sorter);
        monitor.getDml().setVector(sortVectors);
        monitor.getjTable().updateUI();
    }*/

    /**
     * 窗口关闭
     */
    public void windowClose() {
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
    public void init() {
        layoutInit(this);

        menuInit(this);

        dataUiInit(this);
    }

    /**
     * 数据界面初始化
     */
    private void dataUiInit(JFrame jFrame) {
        JPanel panel = new JPanel();
        jFrame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        //状态总数据
        channelGather(panel);

        //详细数据
        channelDetail(panel);
    }

    private JPanel makePanel(String title, JComponent label) {
        JPanel p = new JPanel(new GridLayout(1, 1));
        p.setBorder(BorderFactory.createTitledBorder(title));
        p.add(label);
        return p;
    }

    private void channelGather(JPanel panel) {
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(new GridLayout(1, 4));

        countNum = new JLabel("0");
        JPanel jPanel_1 = makePanel("设备总数量:", countNum);
        panel_1.add(jPanel_1);

        registerNum = new JLabel("0");
        JPanel jPanel_2 = makePanel("已注册通道数量:", registerNum);
        panel_1.add(jPanel_2);

        uncheckedNum = new JLabel("0");
        JPanel jPanel_3 = makePanel("未注册通道数量:", uncheckedNum);
        panel_1.add(jPanel_3);

        warnNum = new JLabel("0");
        JPanel jPanel_4 = makePanel("异常通道数量:", warnNum);
        panel_1.add(jPanel_4);

        panel.add(panel_1, BorderLayout.NORTH);
    }

    private void channelDetail(JPanel panel) {
        JPanel panel_2 = new JPanel();

        JPanel panelDetail = makePanel("详情", panel_2);

        panel_2.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(getjTable());

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        final JTextField textField = new JTextField();
        textField.setColumns(35);
        jPanel.add(textField);

        JButton button = new JButton("搜索");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (text.length() == 0) {

                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });

        jPanel.add(button);

        panel_2.add(jPanel, BorderLayout.NORTH);
        panel_2.add(scrollPane, BorderLayout.CENTER);


        panel.add(panelDetail);
    }

    /**
     * 布局初始化
     *
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
     *
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


    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public Vector<Vector> getTableData() {
        return tableData;
    }

    public JLabel getCountNum() {
        return countNum;
    }

    public void setCountNum(JLabel countNum) {
        this.countNum = countNum;
    }

    public JLabel getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(JLabel registerNum) {
        this.registerNum = registerNum;
    }

    public JLabel getUncheckedNum() {
        return uncheckedNum;
    }

    public void setUncheckedNum(JLabel uncheckedNum) {
        this.uncheckedNum = uncheckedNum;
    }

    public JLabel getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(JLabel warnNum) {
        this.warnNum = warnNum;
    }
}

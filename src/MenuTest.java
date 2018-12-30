import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AA implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);  // ���α׷� ����
    }
}

public class MenuTest extends Frame{
    // ��� �������� ���� �Ǿ� ����

    public MenuTest(String title) {
        // #Initiate
        super(title);  // ���� ������ ȣ�� (Frame("���ڿ�")ó�� ��).
        //setInitiate(200,100,500,500);  // ũ�� ��ġ ���̾ƿ� �ݱ��ư��μ���
        setSize(500,500);
        setResizable(false);//ũ�������Ұ����ϰ� ��.

        // ������ ����
        setIconImage(new ImageIcon("icon.png").getImage());

        // ������ ��ġ ���� (�߾ӿ� ��ġ��Ű�� ����)
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setLocation(screenSize.width/2-500/2,screenSize.height/2-500/2);

        // �ݱ� ��ư���� ����
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // �޸𸮿��� ���� (���� �����Ӹ� ����)
            }
        });

        // #Frame Menu Area //////////////////////////////
        MenuBar mb = new MenuBar();
        setMenuBar(mb);  // �����ӿ� �޴��� ���̱�
        ///  File Menu
        Menu mFile = new Menu("File");
        MenuItem miJoin = new MenuItem("Join", new MenuShortcut('J',true)); // ����Ű ���� (��Ʈ��+����Ʈ+J)
        MenuItem miExit = new MenuItem("Exit", new MenuShortcut('E')); // ����Ű ���� (��Ʈ��+E)

        miExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // ���α׷� ����
            }
        });
        miJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinMember();
            }
        });
        mb.add(mFile);
        mFile.add(miJoin);
        mFile.addSeparator();  // ���м� �߰�
        mFile.add(miExit);

        ///  Edit Menu
        Menu mMenu = new Menu("Menu");
        MenuItem miMenu1 = new MenuItem("Coffee", new MenuShortcut('A'));
        MenuItem miMenu2 = new MenuItem("Quiz", new MenuShortcut('B'));
        MenuItem miMenu3 = new MenuItem("Color", new MenuShortcut('C'));
        miMenu1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new Coffee("���� ���ñ�");
            }
        });
        miMenu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckboxEventTest("����");
            }
        });
        miMenu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckboxEventTest2("�� �ٲٱ�");
            }
        });
        mb.add(mMenu);
        mMenu.add(miMenu1);
        mMenu.add(miMenu2);
        mMenu.add(miMenu3);
        ///  MyMenu Menu
        Menu mMyEvent = new Menu("MyEvent");
        MenuItem miEvent1 = new MenuItem("Bingo", new MenuShortcut('D'));
        MenuItem miEvent2 = new MenuItem("CoffeeShop", new MenuShortcut('F'));
        MenuItem miEvent3 = new MenuItem("LifeGame", new MenuShortcut('G'));
        MenuItem miEvent4 = new MenuItem("ChristMas",new MenuShortcut('K'));
        miEvent4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cristmas();
            }
        });
        miEvent1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Bingo();
            }
        });
        miEvent2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyEvent2();
            }
        });
        miEvent3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LifeGame();
            }
        });
        mb.add(mMyEvent);
        mMyEvent.add(miEvent1);
        mMyEvent.add(miEvent2);
        mMyEvent.add(miEvent3);
        mMyEvent.add(miEvent4);

        ///  Help Menu
        Menu mHelp = new Menu("Help");
        MenuItem miHelp = new MenuItem("Help", new MenuShortcut('H',true));
        miHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Help();
            }
        });
        mb.add(mHelp);
        mHelp.add(miHelp);
        // #End of Frame Menu Area //////////////////////

        // #Show window
        setVisible(true);  // ������ ���̱�
    }

    public MenuTest() {
        this("");
    }

    private void setInitiate(int x,int y,int w,int h){ // ũ�� ��ġ ���̾ƿ� �ݱ��ư ����ȭ ����
        setSize(w,h);  // Frame ���� 500, ���� 500 ����
        setLayout(null);  // �⺻ borderLayout ������. ��ǥ ���� �� ����� �ض�.
        setLocation(x,y);  // ������ ���� ��ġ ����
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) { // �������̵� ��
        Image img;

        img = Toolkit.getDefaultToolkit().getImage("java.png");// �̹����� �����´�.
        g.drawImage(img,0,250-307/2,500,307,this);
    }

    public static void main(String[] args){  // ���α׷� ���� ��ġ.
        /*MenuTest m = */new MenuTest("������Ʈ_�̻��");
        new Login("Login");
    }
}

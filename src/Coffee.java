import java.awt.*;
import java.awt.event.*;

public class Coffee extends Frame{
    Button coffee,coke;
    Label label,label2;

    Coffee(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // �޸𸮿��� ���� (���� �����Ӹ� ����)
            }
        });

        setLayout(null);
        setBounds(200,200,400,500);
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-400/2,screenSize.height/2-500/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.

        Font f1 = new Font("Serif",Font.BOLD,15);
        Font f2 = new Font("SansSerif",Font.ITALIC,15);
        Font f3 = new Font("Dialog",Font.PLAIN,15);
        Font f4 = new Font("Monospaced",Font.BOLD,15);

        label = new Label("���Ḧ �������ּ���.");
        label.setBounds(150,50,200,50);
        label.setForeground(new Color(61, 72, 255));
        label.setFont(f2);
        label2 = new Label("����� Ŭ���ϸ� ����� �� �ֽ��ϴ�.^^");
        label2.setFont(f1);
        label2.setBounds(100,100,300,50);
        label2.setForeground(Color.RED);
        coffee = new Button("Ŀ��");
        coffee.setFont(f3);
        coffee.setBounds(50,50,50,50);
        coffee.addActionListener(new buttonHandler());
        coffee.setBackground(new Color(255, 150, 35));
        coke = new Button("�ݶ�");
        coke.setBounds(50,150,50,50);
        coke.setBackground(new Color(166, 55, 54));
        coke.addActionListener(new buttonHandler());
        coke.setFont(f4);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setText("���Ḧ �������ּ���.");
                setBackground(Color.WHITE);
                label.setBackground(Color.WHITE);
                label2.setBackground(Color.WHITE);
            }
        });

        add(coffee);
        add(coke);
        add(label);
        add(label2);
        setVisible(true);
    }

//    public static void main(String[] args){
//       new Coffee("����");
//    }

    public void paint(Graphics g) {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("back_coffee.png");// �̹����� �����´�.
        g.drawImage(img,0,0,400,500,this);
    }

    class buttonHandler implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText(e.getActionCommand()+"�� ���õǾ����ϴ�.");
            if(e.getActionCommand()=="Ŀ��"){
                setBackground(Color.ORANGE);
                label.setBackground(Color.ORANGE);
                label2.setBackground(Color.ORANGE);
            }else{
                setBackground(Color.GREEN);
                label.setBackground(Color.GREEN);
                label2.setBackground(Color.GREEN);
            }
        }
    }
}

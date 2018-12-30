import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyEvent2 extends Frame {
    final int WIDTH=500;
    final int HEIGHT=400;
    int fee=0;
    public MyEvent2(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // �޸𸮿��� ���� (���� �����Ӹ� ����)
            }
        });
        setSize(WIDTH,HEIGHT);
        setLayout(null);

        // ȭ�� ���߾ӿ� ǥ�� �ϴ� �ڵ�
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-WIDTH/2,screenSize.height/2-HEIGHT/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.

        Label coffee_select = new Label("Ŀ�Ǹ� �����ϼ���.");
        coffee_select.setLocation(20,40);
        coffee_select.setSize(100,20);
        coffee_select.setBackground(new Color(255, 124, 114));

        CheckboxGroup coffee = new CheckboxGroup();
        Checkbox coffee_c1 = new Checkbox("�Ƹ޸�ī��(1500��)", coffee, false);
        Checkbox coffee_c2 = new Checkbox("ī���(2000��)", coffee, false);
        Checkbox coffee_c3 = new Checkbox("īǪġ��(2500��)", coffee, false);

        coffee_c1.setBounds(45,163,150,20);
        coffee_c2.setBounds(208,163,130,20);
        coffee_c3.setBounds(351,163,150,20);

        Label size_select = new Label("����� �����ϼ���.");
        size_select.setBounds(20,190,150,20);
        size_select.setBackground(new Color(116, 255, 248));

        CheckboxGroup size = new CheckboxGroup();
        Checkbox small_size = new Checkbox("����(+100��)", size, false);
        Checkbox mid_size = new Checkbox("�߰�(+200��)", size, false);
        Checkbox large_size = new Checkbox("ū(+300��)", size, false);
        //45,255
        small_size.setBounds(45,215,100,20);
        mid_size.setBounds(45,235,100,20);
        large_size.setBounds(45,255,100,20);

        //���� ���� ���� 20,286
        Label score = new Label("���� ���ּ���");
        score.setBackground(new Color(253, 255, 153));
        score.setBounds(20,286,400,100);

        //�����ϱ� ��ư 241,218
        Button buy_button = new Button("�����ϱ�");
        buy_button.setBounds(241,218,200,50);
        buy_button.setBackground(new Color(71, 255, 139));
        buy_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fee=0;
                score.setText("");
                String setThis="�����Ͻ� Ŀ�Ǵ� ";
                if(coffee_c1.getState()){
                    fee+=1500;
                    setThis+="[�Ƹ޸�ī��] ";
                }
                else if(coffee_c2.getState()){
                    fee+=2000;
                    setThis+="[ī���] ";
                }
                else if(coffee_c3.getState()){
                    fee+=2500;
                    setThis+="[īǪġ��] ";
                }
                else{
                    score.setText("Ŀ�Ǹ� �����ϼ���.");
                    return;
                }

                if(small_size.getState()){
                    fee+=100;
                    setThis+="[����] ������ ";
                }
                else if(mid_size.getState()){
                    fee+=200;
                    setThis+="[�߰�] ������ ";
                }
                else if(large_size.getState()){
                    fee+=300;
                    setThis+="[ū] ������ ";
                }
                else{
                    score.setText("����� �����ϼ���.");
                    return;
                }
                setThis+="�Դϴ�. �Ѱ����� ["+fee+"] �Դϴ�.";
                score.setText(setThis);
            }
        });



        add(coffee_c1);
        add(coffee_c2);
        add(coffee_c3);
        add(small_size);
        add(mid_size);
        add(large_size);
        add(coffee_select);
        add(size_select);
        add(buy_button);
        add(score);
        setVisible(true);
    }

    public MyEvent2(){
        this("Ŀ�� ���Ǳ�");
    }

    @Override
    public void paint(Graphics g) { // �������̵� ��
        Image img;

        img = Toolkit.getDefaultToolkit().getImage("back_shop.png");// �̹����� �����´�.
        g.drawImage(img,0,0,500,400,this);
        img = Toolkit.getDefaultToolkit().getImage("americano.png");// �̹����� �����´�.
        g.drawImage(img,28,75,50,80,this);
        img = Toolkit.getDefaultToolkit().getImage("latte.png");// �̹����� �����´�.
        g.drawImage(img,186,75,60,80,this);
        img = Toolkit.getDefaultToolkit().getImage("capucino.png");// �̹����� �����´�.
        g.drawImage(img,350,75,60,80,this);
    }

//    public static void main(String[] args){
//        new MyEvent2();
//    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//2018-12-15 ȸ�������߰�����
public class JoinMember extends Frame{
    Boolean doublecheck=false;
    Label id_label = new Label("ID");
    Label name_label = new Label("�̸�");
    Label pw_label = new Label("��ȣ");
    Label pwcheck_label = new Label("��ȣȮ��");
    Label email_label = new Label("e-mail");
    Label golbang_label = new Label("@");
    Label address_label = new Label("�ּ�");
    Label phone_label = new Label("�޴���");
    Label like_label = new Label("���ɻ�");
    Label info_label = new Label("�ڱ�Ұ�");

    TextField id_tf = new TextField(10);
    TextField name_tf = new TextField(10);
    TextField pw_tf = new TextField(10);
    TextField pwcheck_tf = new TextField(10);
    TextField email_tf = new TextField(10);
    TextField email2_tf = new TextField(10);
    TextField address_tf = new TextField(10);
    TextField phone_tf = new TextField(10);
    TextField like_tf = new TextField(10);
    TextArea info_ta = new TextArea();

    Button join_button = new Button("����");
    Button cancel_button = new Button("���");
    Button double_button = new Button("�ߺ�Ȯ��");

    Choice choice_email = new Choice();

    JoinMember(){
        super("ȸ������");
        // ȭ�� ���߾ӿ� ǥ�� �ϴ� �ڵ�
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-500/2,screenSize.height/2-400/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.
        setSize(500,400);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // �ݱ��ư Ŭ����
            }
        });
        setResizable(false); // �� ������ �Ұ�
        setLayout(null); // ��ǥ�� �Ұ���.
        doublecheck=false;//�ߺ�üũ false
        ////////////////////
        choice_email.add("�����ϱ�");
        choice_email.add("naver.com");
        choice_email.add("daum.com");
        choice_email.add("gamil.com");
        choice_email.add("hanmail.net");
        //

        id_label.setBounds(15,40,100,25);
        name_label.setBounds(15,70,100,25);
        pw_label.setBounds(15,100,100,25);
        pwcheck_label.setBounds(15,130,100,25);
        email_label.setBounds(15,160,100,25);
        golbang_label.setBounds(220,160,20,25);
        address_label.setBounds(15,190,100,25);
        phone_label.setBounds(15,220,100,25);
        like_label.setBounds(15,250,100,25);
        info_label.setBounds(15,280,100,25);


        id_tf.setBounds(120,40,100,25);
        name_tf.setBounds(120,70,100,25);
        pw_tf.setBounds(120,100,100,25);
        pwcheck_tf.setBounds(120,130,100,25);
        email_tf.setBounds(120,160,100,25);
        email2_tf.setBounds(236,160,100,25);
        address_tf.setBounds(120,190,350,25);
        phone_tf.setBounds(120,220,100,25);
        like_tf.setBounds(120,250,100,25);
        info_ta.setBounds(120,280,366,85);

        pwcheck_tf.setEchoChar('*'); //��й�ȣ Ȯ��

        choice_email.setBounds(338,160,100,25);

        join_button.setBounds(150,370,100,20);
        cancel_button.setBounds(260,370,100,20);
        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // ��ҹ�ưŬ����
            }
        });

        double_button.setBackground(new Color(255, 145, 161));

        double_button.setBounds(230,40,50,25);//�ߺ�Ȯ�ι�ư
        double_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
                    doublecheck=false;
                 }
                else if(id_tf.getText().equals("admin")){
                    JOptionPane.showMessageDialog(null, "�̹� ����� �Դϴ�.");
                    doublecheck=false;
                }
                else {
                    JOptionPane.showMessageDialog(null, "��� ���� �մϴ�.");
                    doublecheck=true;
                }
            }
        });

        //���Թ�ư
        join_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
                }
                else if(name_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
                }else if(pw_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
                }else if(pwcheck_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "��й�ȣ Ȯ���� �Է����ּ���.");
                }else if(email_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.");
                }else if(address_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���.");
                }else if(phone_tf.getText().length()<=0) {
                    JOptionPane.showMessageDialog(null, "�ڵ�����ȣ�� �Է����ּ���.");
                }else if(doublecheck==false){
                    JOptionPane.showMessageDialog(null, "�ߺ� Ȯ���� ���ּ���.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "���ԿϷ�");
                    dispose();
                }

            }
        });

        //�̸��� ���̽�
        choice_email.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object obj = e.getItem();
                if(obj.equals("�����ϱ�")){ //equals�� ���Ͽ� �ؽ�Ʈ ����
                    email2_tf.setText("");
                }else if(obj.equals("naver.com")) {
                    email2_tf.setText("naver.com");
                }else if(obj.equals("daum.com")) {
                    email2_tf.setText("daum.com");
                }else if(obj.equals("gamil.com")) {
                    email2_tf.setText("gamil.com");
                }else if(obj.equals("hanmail.net")) {
                    email2_tf.setText("hanmail.net");
                }
            }
        });

        add(id_tf);
        add(name_tf);
        add(pw_tf);
        add(pwcheck_tf);
        add(email_tf);
        add(email2_tf);
        add(address_tf);
        add(phone_tf);
        add(like_tf);
        add(info_ta);

        add(id_label);
        add(name_label);
        add(pw_label);
        add(pwcheck_label);
        add(email_label);
        add(address_label);
        add(phone_label);
        add(like_label);
        add(info_label);
        add(golbang_label);
        add(choice_email);

        add(join_button);
        add(cancel_button);
        add(double_button);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("back_main.png");// �̹����� �����´�.
        g.drawImage(img,0,0,500,500,this);
    }

    //public static void main(String[] args){
    //    new JoinMember();
    //}

}

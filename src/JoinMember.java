import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//2018-12-15 회원가입추가구현
public class JoinMember extends Frame{
    Boolean doublecheck=false;
    Label id_label = new Label("ID");
    Label name_label = new Label("이름");
    Label pw_label = new Label("암호");
    Label pwcheck_label = new Label("암호확인");
    Label email_label = new Label("e-mail");
    Label golbang_label = new Label("@");
    Label address_label = new Label("주소");
    Label phone_label = new Label("휴대폰");
    Label like_label = new Label("관심사");
    Label info_label = new Label("자기소개");

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

    Button join_button = new Button("가입");
    Button cancel_button = new Button("취소");
    Button double_button = new Button("중복확인");

    Choice choice_email = new Choice();

    JoinMember(){
        super("회원가입");
        // 화면 정중앙에 표시 하는 코드
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-500/2,screenSize.height/2-400/2); // 화면 정중앙에 표시한다.
        setSize(500,400);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // 닫기버튼 클릭시
            }
        });
        setResizable(false); // 재 사이즈 불가
        setLayout(null); // 좌표로 할것임.
        doublecheck=false;//중복체크 false
        ////////////////////
        choice_email.add("선택하기");
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

        pwcheck_tf.setEchoChar('*'); //비밀번호 확인

        choice_email.setBounds(338,160,100,25);

        join_button.setBounds(150,370,100,20);
        cancel_button.setBounds(260,370,100,20);
        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 취소버튼클릭시
            }
        });

        double_button.setBackground(new Color(255, 145, 161));

        double_button.setBounds(230,40,50,25);//중복확인버튼
        double_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
                    doublecheck=false;
                 }
                else if(id_tf.getText().equals("admin")){
                    JOptionPane.showMessageDialog(null, "이미 사용중 입니다.");
                    doublecheck=false;
                }
                else {
                    JOptionPane.showMessageDialog(null, "사용 가능 합니다.");
                    doublecheck=true;
                }
            }
        });

        //가입버튼
        join_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
                }
                else if(name_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
                }else if(pw_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                }else if(pwcheck_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력해주세요.");
                }else if(email_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
                }else if(address_tf.getText().length()<=0){
                    JOptionPane.showMessageDialog(null, "주소를 입력해주세요.");
                }else if(phone_tf.getText().length()<=0) {
                    JOptionPane.showMessageDialog(null, "핸드폰번호를 입력해주세요.");
                }else if(doublecheck==false){
                    JOptionPane.showMessageDialog(null, "중복 확인을 해주세요.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "가입완료");
                    dispose();
                }

            }
        });

        //이메일 초이스
        choice_email.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object obj = e.getItem();
                if(obj.equals("선택하기")){ //equals로 비교하여 텍스트 설정
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
        img = Toolkit.getDefaultToolkit().getImage("back_main.png");// 이미지를 가져온다.
        g.drawImage(img,0,0,500,500,this);
    }

    //public static void main(String[] args){
    //    new JoinMember();
    //}

}

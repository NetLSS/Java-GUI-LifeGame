import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AA implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);  // 프로그램 종료
    }
}

public class MenuTest extends Frame{
    // 멤버 변수들이 선언 되어 있음

    public MenuTest(String title) {
        // #Initiate
        super(title);  // 조상 생성자 호출 (Frame("문자열")처럼 됨).
        //setInitiate(200,100,500,500);  // 크기 위치 레이아웃 닫기버튼모두설정
        setSize(500,500);
        setResizable(false);//크기조절불가능하게 함.

        // 아이콘 설정
        setIconImage(new ImageIcon("icon.png").getImage());

        // 프레임 위치 지정 (중앙에 위치시키기 위해)
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setLocation(screenSize.width/2-500/2,screenSize.height/2-500/2);

        // 닫기 버튼으로 종료
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // 메모리에서 해제 (현재 프레임만 닫힘)
            }
        });

        // #Frame Menu Area //////////////////////////////
        MenuBar mb = new MenuBar();
        setMenuBar(mb);  // 프레임에 메뉴바 붙이기
        ///  File Menu
        Menu mFile = new Menu("File");
        MenuItem miJoin = new MenuItem("Join", new MenuShortcut('J',true)); // 단축키 지정 (컨트롤+시프트+J)
        MenuItem miExit = new MenuItem("Exit", new MenuShortcut('E')); // 단축키 지정 (컨트롤+E)

        miExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // 프로그램 종료
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
        mFile.addSeparator();  // 구분선 추가
        mFile.add(miExit);

        ///  Edit Menu
        Menu mMenu = new Menu("Menu");
        MenuItem miMenu1 = new MenuItem("Coffee", new MenuShortcut('A'));
        MenuItem miMenu2 = new MenuItem("Quiz", new MenuShortcut('B'));
        MenuItem miMenu3 = new MenuItem("Color", new MenuShortcut('C'));
        miMenu1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new Coffee("음료 선택기");
            }
        });
        miMenu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckboxEventTest("퀴즈");
            }
        });
        miMenu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckboxEventTest2("색 바꾸기");
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
        setVisible(true);  // 프레임 보이기
    }

    public MenuTest() {
        this("");
    }

    private void setInitiate(int x,int y,int w,int h){ // 크기 위치 레이아웃 닫기버튼 가시화 설정
        setSize(w,h);  // Frame 가로 500, 세로 500 설정
        setLayout(null);  // 기본 borderLayout 사용안함. 좌표 직접 찍어서 사용을 해라.
        setLocation(x,y);  // 프레임 시작 위치 설정
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) { // 오버라이딩 함
        Image img;

        img = Toolkit.getDefaultToolkit().getImage("java.png");// 이미지를 가져온다.
        g.drawImage(img,0,250-307/2,500,307,this);
    }

    public static void main(String[] args){  // 프로그램 시작 위치.
        /*MenuTest m = */new MenuTest("프로젝트_이상수");
        new Login("Login");
    }
}

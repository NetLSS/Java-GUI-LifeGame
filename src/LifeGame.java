import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Random;

/*
[자바2 기말 최종 프로젝트]
이름  : 이상수
학번  : 201455035
phone : 010-8454-5406
email : vviprogrammer@gmail.com , futureprogrammer@naver.com

[work log.]
2018-12-03 : 프로젝트 기획 및 기본 레이아웃 설계 및 코딩
2018-12-04 : 좌표 그리기, 모든 좌표 계산..
2018-12-08 : 라이프게임 룰에 따른 세포분열 로직 코딩 및 버튼 추가 +alpha
2018-12-15 : 쓰레드를 이용한 자동 갱신 방법 공부 및 기능 추가,
             Sleep시간 텍스트필드로 수정가능,
             디자인 개선, Gif 이미지 넣기 추가,
             회원가입 창 구현,
             +alpha 및 마무리 단계 돌입.
2018-12-16 : 일부 주석 추가         
*/
//Copyright2018.이상수(futureprogrammer@naver.com) all rights reserved.
public class LifeGame extends Frame {
    final int frameWidth=1050;
    final int frameHeight=600;
    int generation; // 세대 수를 카운트한다.
    int liveCell; // 살아있는 세포 수를 카운트 한다.
    boolean[][] arr = new boolean[13][13]; // 좌표에 표시될 세포 생존 유무 배열
    boolean[][] arr_next = new boolean[13][13]; // 다음 세대 갱신에 사용될 생존 유무 배열
    Label cell_label; // 생존 세포 라벨
    Random generator = new Random(); // 랜덤을 사용하기위해서 랜덤 객체 생성
    Color liveColor = new Color(82, 124, 255); // 생존 세포의 색 설정
    Color deadColor = new Color(174, 174, 174); // 죽은 세포의 색 설정
    long sleepTime=1000; // 자동 버튼 스레드 sleep시간
    TextField sleepTimeTextTield; // 자동 갱신 시간 텍스트 필드
    public LifeGame(){
        //[기본설정]===============================================================================================
        super("Life Game"); // 창 타이틀 지정
        generation=0; // 0세대 부터 시작
        liveCell=0; // 생존한 세포 개수 0으로 초기화
        setLayout(null); // 기본 레이아웃 사용안함
        addWindowListener(new WindowAdapter() { // 닫기버튼 클릭시 해당 창만 닫도록 설정
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();// 닫기버튼 클릭시 해당 창만 닫도록 설정
            }
        });
        Toolkit tk = Toolkit.getDefaultToolkit(); // (책참고) 구현된 Toolkit 객체를 얻는다.
        Dimension screenSize = tk.getScreenSize(); // 화면의 크기를 가져온다.
        setBounds(screenSize.width/2-frameWidth/2,screenSize.height/2-frameHeight/2,frameWidth,frameHeight); // 화면에 중앙에 오도록 설정한다.
        setResizable(false); // 창 크기 변경 불가 설정
        setBackground(new Color(130, 130, 130)); // Frame 배경 색 지정
        Frame f = this; // 현재 프래임을 넘겨 줄 때 사용할 Frame 변수
        Font defaultFont = new Font("Serif",Font.PLAIN,20);
        //========================================================================================================

        Canvas canvas = new LifeGameCanvas(); // 게임 화면을 표시할 캔버스 지정

        Label gen_label = new Label(); // 세대 라벨 추가
        gen_label.setLocation(610,185);
        gen_label.setSize(120,20);
        gen_label.setBackground(Color.WHITE);
        gen_label.setText(generation+" 세대");

        cell_label = new Label();	//생존라벨 추가
        cell_label.setLocation(610,210);
        cell_label.setSize(120,20);
        cell_label.setBackground(Color.WHITE);
        cell_label.setText(liveCell+" 생존");

        Button generateButton = new Button("Generate"); // Generate 버튼 (클릭시 1세대식 갱신)
        generateButton.setBounds(570+40,250,570-450,370-250); //  Generate 버튼 위치와 크기 설정
        generateButton.setBackground(new Color(148, 167, 255)); // 버튼 배경색 지정
        generateButton.setFont(defaultFont);
        generateButton.addActionListener(new ActionListener() {
            // generate 버튼의 액션 리스너를 지정하여
            // 클릭할 때마다 세대가 갱신되도록 리스너를 설정한다.
            @Override
            public void actionPerformed(ActionEvent e) {
                // arr배열 전체를 돌며 검사한다.
                for (int i = 0; i < arr.length; i++)
                {
                    for (int j = 0; j < arr.length; j++)
                    {
                        int cnt = 0; // 이웃 확인 카운트
                        // Life Game 규칙 적용. (아래)
                        // 이웃 확인 (주의점: 범위를 벗어나는 경우는 검사하지 않는다.)
                        //↖ 이웃 확인
                        if (i > 0 && j > 0 && arr[i - 1][ j - 1]) cnt++;
                        //← 이웃 확인
                        if (j > 0 && arr[i][j - 1]) cnt++;
                        //↗ 이웃 확인
                        if (i > 0 && j < 12 && arr[i-1][j+1]) cnt++;
                        //↑ 이웃 확인
                        if (i > 0 && arr[i-1][ j]) cnt++;
                        //→ 이웃 확인
                        if (j<12 && arr[i][j+1]) cnt++;
                        //↙ 이웃 확인
                        if (i<12 && j >0 && arr[i+1][j-1]) cnt++;
                        //↓ 이웃 확인
                        if (i<12&& arr[i+1][j]) cnt++;
                        //↘ 이웃 확인
                        if (i<12&& j<12 && arr[i+1][j+1]) cnt++;
                        //죽어있고, 살아있는 이웃이 3개있으면 다음 세대에서 살아남.
                        //Copyright2018.이상수(futureprogrammer@naver.com) all rights reserved.
                        if (!arr[i][j] && cnt == 3)
                        {
                           arr_next[i][j] = true;
                        }//살아있고 이웃이 2개 또는 3개 살아있으면 계속 살아있음.
                        else if (arr[i][j] && (cnt == 2 || cnt == 3))
                        {
                            arr_next[i][j] = true;
                        }
                        else// 위 사항들이 일치하지 않으면 죽게됨.
                        {
                            arr_next[i][j] = false;
                        }
                    }
                }
                liveCell=0; // 생존세포 개수를 다시 세기위해 0으로 초기화한다
                for(int i=0;i<arr.length;++i){ // 다음 세대 배열을 현새대 배열로 복사한다. (갱신하기 위해서)
                    for(int j=0;j<arr.length;++j){
                        arr[i][j]=arr_next[i][j]; // 다음 세대 배열을 현새대 배열로 복사한다. (갱신하기 위해서)
                        if(arr[i][j]) // 살아있는 경우
                            liveCell++; // 생존셀 카운트를 증가한다.
                    }
                }
                generation++; // 세대수를 증가한다.
                gen_label.setText(generation+" 세대"); // 라벨에 세대수를 새로 셋팅해준다.
                cell_label.setText(liveCell+" 생존"); // 라벨에 생존수 세팅
                canvas.repaint(); // 컨버스를 다시 그려준다.
            }
        });

        Button autoGenerationButton = new Button("Auto Generate"); // 자동 갱신 버튼 생성
        autoGenerationButton.setBounds(570+40,250+370-250+10,570-450,370-250); //  Generate 버튼 위치와 크기 설정
        autoGenerationButton.setBackground(new Color(148, 167, 255)); // 버튼 배경색 지정
        autoGenerationButton.setFont(defaultFont);
        class myActListener implements ActionListener, Runnable{ // 자동으로 하기위해서 쓰레드를 사용하기 때문에 Runnable을 구현한다.
            ActionEvent event; // 클릭한 이벤트를 여기에 저장한다.
            Thread t = new Thread(this); // 사용할 쓰레드를 생성한다.
            boolean autoLoop=false; // 자동 갱신 루프 변수
            @Override
            public void actionPerformed(ActionEvent e) { // 자동 갱신 버튼이 눌리면

                String text=sleepTimeTextTield.getText(); // 현재 설정된 텍스트를 가져온다.
                long milisec=1000; // 1000은 1초
                try {
                    milisec = Long.parseLong(text);
                    if(milisec<1000||milisec>5000) // 1000에서 5000으로 제한한다. (100도 되지만 설정상 제한함)
                        throw new Exception(); // 그것이 아니면 예외를던진다. 또는 변환이 안되어도 예외가 던져진다.
                }
                catch (Exception ee){
                    Dialog dl = new Dialog(f,"잘못된 입력",true); // 범위를 벗어나거나, 문자가 입력되면 예외를 처리한다.
                    dl.setBounds(screenSize.width/2-200/2,screenSize.height/2-100/2,200,100);
                    dl.setLayout(new FlowLayout());

                    dl.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            dl.setVisible(false); // 다이얼로그의 닫기버튼 닫기로 처리 (dispose하면 다 닫혀서 이것을 사용)
                        }
                    });

                    Label msg = new Label("잘못된 입력입니다. (재입력)");
                    sleepTime=1000; // 잘못된 입력일 경우 다시 1000(기본)밀리초로 설정함.
                    sleepTimeTextTield.setText(String.valueOf(sleepTime)); // int를 String형으로 변환해서 설정
                    dl.add(msg);
                    Button ok = new Button("ok");
                    dl.add(ok);
                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dl.setVisible(false); // ok 버튼을 눌러도 사라지게 함.
                        }
                    });
                    dl.setVisible(true);
                    return;
                }
                sleepTime=milisec; // 변환이 된다면 슬립타임에 대입시킴




                event = e;
                Button autoButton = (Button)e.getSource(); // 이벤트에서 버튼소스를 가져옴
                if(autoLoop==false) { // 안눌려져있었다면
                    Thread t = new Thread(this);//쓰레드를 생성하고
                    autoButton.setBackground(new Color(0, 255, 14)); 
                    autoLoop=true;//루프를 true로 바꾼다.
                    sleepTimeTextTield.setEditable(false); // 슬립타임 설정 필드를 비활성화 (수정못하게)
                    generateButton.setEnabled(false); // 갱신버튼도 못누르게 비활성화
                    generateButton.setBackground(new Color(130, 130, 130));
                    t.start(); // 쓰레드를 시작한다!
                }
                else{
                    autoLoop=false; // 쓰레드가 실행되고있을 때 눌렀다면 중지.
                    t.interrupt(); // 디스트로이는 사용금지가 되어있어서 인터럽트를 임의로 발생시켜 종료했다.
                    sleepTimeTextTield.setEditable(true); // 다시 활성화
                    autoButton.setBackground(new Color(148, 167, 255));
                    generateButton.setBackground(new Color(148, 167, 255));
                    generateButton.setEnabled(true); // 다시 활성화
                }
            }
            //Copyright2018.이상수(futureprogrammer@naver.com) all rights reserved.
            @Override
            public void run() { // 쓰레드 start시 실행될 것 설정
                //generateButton.notify();
                autoLoop=true;
                while(autoLoop&&!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(sleepTime); // 설정된 밀리초만큼 정지후
                    } catch (InterruptedException e1) {
                        //e1.printStackTrace();
                    }
                    generateButton.dispatchEvent(event); 
                    generateButton.getActionListeners(); // 갱신버튼이 눌린것 처럼 처리한다.
                    canvas.repaint(); // 그리고 다시 그려주라는 요청을 해주어야 제대로 그려진다.
                }
            }
        }
        autoGenerationButton.addActionListener(new myActListener()); // 해당 핸들러를 리스너에 달아준다.



        // 모두 dead한 상태로 만드는 버튼
        Button clearButton = new Button("Clear (all dead)");
        clearButton.setBounds(770,50,224,40); // 버튼 위치, 크기 지정
        clearButton.setFont(defaultFont);
        clearButton.addActionListener(new ActionListener() { // 버튼 액션 리스너 지정
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<arr.length;++i){ // 세포 배열 전체를 돌면서.
                    for(int j=0;j<arr.length;++j){
                        arr[i][j]=false; // 클릭시 모두 false(dead)하게 만듬.
                    }
                }
                liveCell=0; // 세포 개수 초기화
                generation=0; // 세대수 초기화
                gen_label.setText(generation+" 세대"); // 라벨에 세대수를 새로 셋팅해준다.
                cell_label.setText(liveCell+" 생존"); // 라벨에 생존수 세팅
                canvas.repaint(); // 컨버스를 다시 그려준다.
            }
        });

        // 모두 true(생존)으로 세팅해주는 버튼
        Button ClearLiveButton = new Button("Clear (all live)");
        ClearLiveButton.setBounds(770,100,224,40);
        ClearLiveButton.setFont(defaultFont);
        ClearLiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<arr.length;++i){ // 세포 배열 전체를 돌면서
                    for(int j=0;j<arr.length;++j){
                        arr[i][j]=true; // 모두 true(생존) 상태로 만듬
                    }
                }
                liveCell=13*13; // 생존 세포는 전체이므로 13*13
                generation=0; // 세대수 초기화
                gen_label.setText(generation+" 세대"); // 라벨에 세대수를 새로 셋팅해준다.
                cell_label.setText(liveCell+" 생존"); // 라벨에 생존수 세팅
                canvas.repaint(); // 컨버스를 다시 그려준다.
            }
        });



        // 랜덤으로 세팅해주는 버튼
        Button setRandomButton = new Button("Set RANDOM");
        setRandomButton.setBounds(770,150,224,40);
        setRandomButton.setFont(defaultFont);
        setRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRandomArr(); // 랜덤으로 세팅하는 함수 호출
                generation=0; // 세대수 초기화
                gen_label.setText(generation+" 세대"); // 라벨에 세대수를 새로 셋팅해준다.
                cell_label.setText(liveCell+" 생존"); // 라벨에 생존수 세팅
                canvas.repaint(); // 컨버스를 다시 그려준다.
            }
        });

        Label setSleepTime = new Label("갱신시간(1000이상 5000이하)");
        setSleepTime.setBounds(610,510,170,16);

        sleepTimeTextTield = new TextField(String.valueOf(sleepTime),100);
        sleepTimeTextTield.setBounds(610,536,72,16);
        sleepTimeTextTield.addActionListener(new ActionListener() { // 슬립 텍스트의 액션리스너설정
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=sleepTimeTextTield.getText();
                long milisec=100;
                try {
                    if(milisec<100)
                        throw new Exception();
                    milisec = Long.parseLong(text);
                }
                catch (Exception ee){
                    Dialog dl = new Dialog(f,"잘못된 입력"); // 이 내부는 위의 동작과 동일하여 설명 생략
                    dl.setBounds(screenSize.width/2-140/2,screenSize.height/2-90/2,140,90);
                    dl.setLayout(new FlowLayout());

                    Label msg = new Label("잘못된 입력입니다. (재입력)");
                    dl.add(msg);
                    Button ok = new Button("ok");
                    dl.add(ok);
                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                }
                sleepTime=milisec;
            }
        });
        //Copyright2018.이상수(futureprogrammer@naver.com) all rights reserved.
        Label millicho = new Label("밀리초");
        millicho.setBounds(683,536,46,16);

        JPanel jp = null; // 해당 J패널은 GIF이미지를 넣기위해서 사용됨.
        try {
            jp = new MyPanel(); // 해당 J패널은 GIF이미지를 넣기위해서 사용됨.
            jp.setBounds(770,250,224,224);
        }
        catch (Exception e){

        }
        if(jp!=null)
            add(jp);

        //770,202 (게임로고)
        Label logo = new Label("The Game Of Life",Label.CENTER);
        logo.setBounds(770,202,224,37);
        Font font = new Font("Serif",Font.ITALIC,20);
        logo.setFont(font);
        add(logo);

        //770,476 (사진출처)
        Label source = new Label("사진출처:위키백과:Conway's GameOfLife",Label.CENTER);
        source.setBounds(770,476,224,37);
        Font font2 = new Font("SansSerif",Font.PLAIN,12);
        source.setFont(font2);
        add(source);

        //610,50 Help영역
        Button helpButton = new Button("Help");
        helpButton.setFont(font);
        helpButton.setBackground(new Color(255, 227, 1));
        helpButton.setBounds(610,90-20,120,40);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LifeGameHelp();
            }
        });
        //Copyright2018.이상수(futureprogrammer@naver.com) all rights reserved.
        //610,134 규칙버튼
        Button rules = new Button("규칙");
        rules.setForeground(new Color(6,69,173));//link로 보이도록 색설정(기본 링크색 참고함.)
        rules.setFont(font);
        rules.setBackground(new Color(255, 227, 1));
        rules.setBounds(610,134-20,120,40);
        // 규칙 버튼 링크로 연결하여 브라우저로 열기
        // 소스를 참고 한곳 : https://code.i-harness.com/ko-kr/q/a7599b
        rules.addActionListener(new ActionListener() { // 링크로 연결시키기 위해서 구현되었음
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://ko.wikipedia.org/wiki/%EB%9D%BC%EC%9D%B4%ED%94%84_%EA%B2%8C%EC%9E%84");
            }
            public void openWebPage(String url) {
                try {
                    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(new URI(url));
                    }// 소스를 참고 한곳 : https://code.i-harness.com/ko-kr/q/a7599b
                    throw new NullPointerException();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "라이프 게임의 규칙을 링크로 열었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        //카피라잇
        Label copyright = new Label("Copyright2018.이상수 all rights reserved.");
        copyright.setBounds(770,537,400,20);
        Label copyright2 = new Label("futureprogrammer@naver.com");
        copyright2.setBounds(770,537+20,400,35);

        // this.add() 등 최종 영역 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        setRandomArr(); // 시작할 때는 랜덤하게 세포에게 생명을 준다.
        cell_label.setText(liveCell+" 생존"); // 라벨에 생존수 세팅
        add(generateButton); // generate 버튼 추가
        add(autoGenerationButton); // auto generate 버튼 추가
        add(canvas); // canvas 추가
        add(gen_label); // 세대수 라벨 추가
        add(cell_label); // 세포수 라벨 추가
        add(clearButton); // clear 버튼 추가
        add(ClearLiveButton); //ClearLive 버튼 추가
        add(setRandomButton); // setRandom 버튼 추가
        add(setSleepTime); // 갱신시간 라벨 추가
        add(sleepTimeTextTield); // 갱신시간 텍스트 필드 추가
        add(millicho); // 밀리초 라벨 추가
        setVisible(true); // 창 가시화
        add(helpButton);//헬프버튼추가
        add(rules);//규칙버튼추가
        add(copyright);//카피라이트 추가
        add(copyright2);//카피라이트 추가
    }// end of LifeGame() 기본생성자///////////////////////////////////////////////////////////////////////////////////////////

    private void setRandomArr(){
        liveCell=0;
        for(int i=0;i<arr.length;++i){ // 세포배열 arr을 돌면서
            for(int j=0;j<arr.length;++j){
                if(generator.nextBoolean()){ // 랜덤하게 true가 나올시
                    arr[i][j]=true; // 해당 부분의 세포를 true(생존) 시킨다.
                    liveCell++;
                }
                else
                    arr[i][j]=false;
            }
        }
    }
    // 라이프 게임에 사용할 캔버스를 정의한다.
    public class LifeGameCanvas extends Canvas{
        final int blockSize=40; // 세포블록 사이즈
        public LifeGameCanvas() { // 라이프게임 전용 캔버스의 기본 생성자
            setBackground(new Color(174, 174, 174)); // 배경색 지정
            setBounds(50,50,521,521); // 위치 밑 크기 지정

            //마우스 클릭시 세포를 바꿀 수 있도록 마우스 리스너를 지정한다.
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) { // 마우스를 클릭하고 뗐을때
                    Point p = e.getPoint(); // 클릭한 곳의 포인트를 얻어온다.
                    int x=p.x/40;
                    int y=p.y/40; // 13x13 배열 상으로 계산하기 위해 40을 나눈다.
                    Graphics g = getGraphics(); // 그리기 위해서 그레픽을 얻어온다.

                    // 만약 이미 살아 있을 경우
                    if(arr[x][y]) {
                        liveCell--; // 살아있는 세포 개수 1감소
                        arr[x][y] = false; // 죽인다.
                        g.setColor(deadColor); // 지우기 색을 사용하여 지운다.
                        g.fillRect(x*blockSize+1,y*blockSize+1,blockSize-1,blockSize-1);
                    }
                    else { // 그렇지 않고 죽어있는 경우
                        liveCell++; // 살아있는 세포 개수 1증가
                        arr[x][y] = true; // 살린다.
                        g.setColor(liveColor); // 살리기 색을 사용하여 칠한다.
                        g.fillRect(x*blockSize+1,y*blockSize+1,blockSize-1,blockSize-1);
                    }
                    cell_label.setText(liveCell+" 생존");
                    invalidate(); // 다시 그려주기 위해서 invalidate를 호출한다.
                }
            });
        } // end of LifeGameCanvas() 기본생성자
        //Copyright2018.이상수(futureprogrammer@naver.com) all rights reserved.
        public void paint(Graphics g)
        {
            g.setColor(Color.BLACK); // 그리기 색 검은색으로 지정
            // 테두리 선 그리기
            for(int x=0;x<=520;x+=blockSize){
                g.drawLine(x,0,x,521);
                g.drawLine(0,x,521,x);
            }
            // arr 배열 전체를 돌면서 알맞은 위치의 캔버스에 사각형을 그린다.
            g.setColor(liveColor);
            liveCell=0; // 살아있는 세포 개수 초기화
            for(int i=0;i<arr.length;++i){
                for(int j=0;j<arr.length;++j){
                    if(arr[i][j]){
                        // 살아있는 세포 나타내기 위해 사각형 그리기
                        g.fillRect(i*blockSize+1,j*blockSize+1,blockSize-1,blockSize-1);
                        liveCell++; // 살아있는 세포 개수 카운트
                    }
                }
            }

        } // end of paint()
    }// end of class of LifeGameCanvas

    class MyPanel extends JPanel { // Gif를 열어주기위해서 J패널을 상속받아 구현
        Image image;
        MyPanel() throws MalformedURLException {
            image = Toolkit.getDefaultToolkit().createImage("LifeGame.gif");
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this); // 이미지가 제대로 열렸다면 그려준다.
            }
        }
    }

    // main (for test)
//    public static void main(String[] args){
//        new LifeGame();
//    }
}
//Copyright2018.이상수(futureprogrammer@naver.com) all rights reserved.
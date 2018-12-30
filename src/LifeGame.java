import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Random;

/*
[�ڹ�2 �⸻ ���� ������Ʈ]
�̸�  : �̻��
�й�  : 201455035
phone : 010-8454-5406
email : vviprogrammer@gmail.com , futureprogrammer@naver.com

[work log.]
2018-12-03 : ������Ʈ ��ȹ �� �⺻ ���̾ƿ� ���� �� �ڵ�
2018-12-04 : ��ǥ �׸���, ��� ��ǥ ���..
2018-12-08 : ���������� �꿡 ���� �����п� ���� �ڵ� �� ��ư �߰� +alpha
2018-12-15 : �����带 �̿��� �ڵ� ���� ��� ���� �� ��� �߰�,
             Sleep�ð� �ؽ�Ʈ�ʵ�� ��������,
             ������ ����, Gif �̹��� �ֱ� �߰�,
             ȸ������ â ����,
             +alpha �� ������ �ܰ� ����.
2018-12-16 : �Ϻ� �ּ� �߰�         
*/
//Copyright2018.�̻��(futureprogrammer@naver.com) all rights reserved.
public class LifeGame extends Frame {
    final int frameWidth=1050;
    final int frameHeight=600;
    int generation; // ���� ���� ī��Ʈ�Ѵ�.
    int liveCell; // ����ִ� ���� ���� ī��Ʈ �Ѵ�.
    boolean[][] arr = new boolean[13][13]; // ��ǥ�� ǥ�õ� ���� ���� ���� �迭
    boolean[][] arr_next = new boolean[13][13]; // ���� ���� ���ſ� ���� ���� ���� �迭
    Label cell_label; // ���� ���� ��
    Random generator = new Random(); // ������ ����ϱ����ؼ� ���� ��ü ����
    Color liveColor = new Color(82, 124, 255); // ���� ������ �� ����
    Color deadColor = new Color(174, 174, 174); // ���� ������ �� ����
    long sleepTime=1000; // �ڵ� ��ư ������ sleep�ð�
    TextField sleepTimeTextTield; // �ڵ� ���� �ð� �ؽ�Ʈ �ʵ�
    public LifeGame(){
        //[�⺻����]===============================================================================================
        super("Life Game"); // â Ÿ��Ʋ ����
        generation=0; // 0���� ���� ����
        liveCell=0; // ������ ���� ���� 0���� �ʱ�ȭ
        setLayout(null); // �⺻ ���̾ƿ� ������
        addWindowListener(new WindowAdapter() { // �ݱ��ư Ŭ���� �ش� â�� �ݵ��� ����
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();// �ݱ��ư Ŭ���� �ش� â�� �ݵ��� ����
            }
        });
        Toolkit tk = Toolkit.getDefaultToolkit(); // (å����) ������ Toolkit ��ü�� ��´�.
        Dimension screenSize = tk.getScreenSize(); // ȭ���� ũ�⸦ �����´�.
        setBounds(screenSize.width/2-frameWidth/2,screenSize.height/2-frameHeight/2,frameWidth,frameHeight); // ȭ�鿡 �߾ӿ� ������ �����Ѵ�.
        setResizable(false); // â ũ�� ���� �Ұ� ����
        setBackground(new Color(130, 130, 130)); // Frame ��� �� ����
        Frame f = this; // ���� �������� �Ѱ� �� �� ����� Frame ����
        Font defaultFont = new Font("Serif",Font.PLAIN,20);
        //========================================================================================================

        Canvas canvas = new LifeGameCanvas(); // ���� ȭ���� ǥ���� ĵ���� ����

        Label gen_label = new Label(); // ���� �� �߰�
        gen_label.setLocation(610,185);
        gen_label.setSize(120,20);
        gen_label.setBackground(Color.WHITE);
        gen_label.setText(generation+" ����");

        cell_label = new Label();	//������ �߰�
        cell_label.setLocation(610,210);
        cell_label.setSize(120,20);
        cell_label.setBackground(Color.WHITE);
        cell_label.setText(liveCell+" ����");

        Button generateButton = new Button("Generate"); // Generate ��ư (Ŭ���� 1����� ����)
        generateButton.setBounds(570+40,250,570-450,370-250); //  Generate ��ư ��ġ�� ũ�� ����
        generateButton.setBackground(new Color(148, 167, 255)); // ��ư ���� ����
        generateButton.setFont(defaultFont);
        generateButton.addActionListener(new ActionListener() {
            // generate ��ư�� �׼� �����ʸ� �����Ͽ�
            // Ŭ���� ������ ���밡 ���ŵǵ��� �����ʸ� �����Ѵ�.
            @Override
            public void actionPerformed(ActionEvent e) {
                // arr�迭 ��ü�� ���� �˻��Ѵ�.
                for (int i = 0; i < arr.length; i++)
                {
                    for (int j = 0; j < arr.length; j++)
                    {
                        int cnt = 0; // �̿� Ȯ�� ī��Ʈ
                        // Life Game ��Ģ ����. (�Ʒ�)
                        // �̿� Ȯ�� (������: ������ ����� ���� �˻����� �ʴ´�.)
                        //�� �̿� Ȯ��
                        if (i > 0 && j > 0 && arr[i - 1][ j - 1]) cnt++;
                        //�� �̿� Ȯ��
                        if (j > 0 && arr[i][j - 1]) cnt++;
                        //�� �̿� Ȯ��
                        if (i > 0 && j < 12 && arr[i-1][j+1]) cnt++;
                        //�� �̿� Ȯ��
                        if (i > 0 && arr[i-1][ j]) cnt++;
                        //�� �̿� Ȯ��
                        if (j<12 && arr[i][j+1]) cnt++;
                        //�� �̿� Ȯ��
                        if (i<12 && j >0 && arr[i+1][j-1]) cnt++;
                        //�� �̿� Ȯ��
                        if (i<12&& arr[i+1][j]) cnt++;
                        //�� �̿� Ȯ��
                        if (i<12&& j<12 && arr[i+1][j+1]) cnt++;
                        //�׾��ְ�, ����ִ� �̿��� 3�������� ���� ���뿡�� ��Ƴ�.
                        //Copyright2018.�̻��(futureprogrammer@naver.com) all rights reserved.
                        if (!arr[i][j] && cnt == 3)
                        {
                           arr_next[i][j] = true;
                        }//����ְ� �̿��� 2�� �Ǵ� 3�� ��������� ��� �������.
                        else if (arr[i][j] && (cnt == 2 || cnt == 3))
                        {
                            arr_next[i][j] = true;
                        }
                        else// �� ���׵��� ��ġ���� ������ �װԵ�.
                        {
                            arr_next[i][j] = false;
                        }
                    }
                }
                liveCell=0; // �������� ������ �ٽ� �������� 0���� �ʱ�ȭ�Ѵ�
                for(int i=0;i<arr.length;++i){ // ���� ���� �迭�� ������ �迭�� �����Ѵ�. (�����ϱ� ���ؼ�)
                    for(int j=0;j<arr.length;++j){
                        arr[i][j]=arr_next[i][j]; // ���� ���� �迭�� ������ �迭�� �����Ѵ�. (�����ϱ� ���ؼ�)
                        if(arr[i][j]) // ����ִ� ���
                            liveCell++; // ������ ī��Ʈ�� �����Ѵ�.
                    }
                }
                generation++; // ������� �����Ѵ�.
                gen_label.setText(generation+" ����"); // �󺧿� ������� ���� �������ش�.
                cell_label.setText(liveCell+" ����"); // �󺧿� ������ ����
                canvas.repaint(); // �������� �ٽ� �׷��ش�.
            }
        });

        Button autoGenerationButton = new Button("Auto Generate"); // �ڵ� ���� ��ư ����
        autoGenerationButton.setBounds(570+40,250+370-250+10,570-450,370-250); //  Generate ��ư ��ġ�� ũ�� ����
        autoGenerationButton.setBackground(new Color(148, 167, 255)); // ��ư ���� ����
        autoGenerationButton.setFont(defaultFont);
        class myActListener implements ActionListener, Runnable{ // �ڵ����� �ϱ����ؼ� �����带 ����ϱ� ������ Runnable�� �����Ѵ�.
            ActionEvent event; // Ŭ���� �̺�Ʈ�� ���⿡ �����Ѵ�.
            Thread t = new Thread(this); // ����� �����带 �����Ѵ�.
            boolean autoLoop=false; // �ڵ� ���� ���� ����
            @Override
            public void actionPerformed(ActionEvent e) { // �ڵ� ���� ��ư�� ������

                String text=sleepTimeTextTield.getText(); // ���� ������ �ؽ�Ʈ�� �����´�.
                long milisec=1000; // 1000�� 1��
                try {
                    milisec = Long.parseLong(text);
                    if(milisec<1000||milisec>5000) // 1000���� 5000���� �����Ѵ�. (100�� ������ ������ ������)
                        throw new Exception(); // �װ��� �ƴϸ� ���ܸ�������. �Ǵ� ��ȯ�� �ȵǾ ���ܰ� ��������.
                }
                catch (Exception ee){
                    Dialog dl = new Dialog(f,"�߸��� �Է�",true); // ������ ����ų�, ���ڰ� �ԷµǸ� ���ܸ� ó���Ѵ�.
                    dl.setBounds(screenSize.width/2-200/2,screenSize.height/2-100/2,200,100);
                    dl.setLayout(new FlowLayout());

                    dl.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            dl.setVisible(false); // ���̾�α��� �ݱ��ư �ݱ�� ó�� (dispose�ϸ� �� ������ �̰��� ���)
                        }
                    });

                    Label msg = new Label("�߸��� �Է��Դϴ�. (���Է�)");
                    sleepTime=1000; // �߸��� �Է��� ��� �ٽ� 1000(�⺻)�и��ʷ� ������.
                    sleepTimeTextTield.setText(String.valueOf(sleepTime)); // int�� String������ ��ȯ�ؼ� ����
                    dl.add(msg);
                    Button ok = new Button("ok");
                    dl.add(ok);
                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dl.setVisible(false); // ok ��ư�� ������ ������� ��.
                        }
                    });
                    dl.setVisible(true);
                    return;
                }
                sleepTime=milisec; // ��ȯ�� �ȴٸ� ����Ÿ�ӿ� ���Խ�Ŵ




                event = e;
                Button autoButton = (Button)e.getSource(); // �̺�Ʈ���� ��ư�ҽ��� ������
                if(autoLoop==false) { // �ȴ������־��ٸ�
                    Thread t = new Thread(this);//�����带 �����ϰ�
                    autoButton.setBackground(new Color(0, 255, 14)); 
                    autoLoop=true;//������ true�� �ٲ۴�.
                    sleepTimeTextTield.setEditable(false); // ����Ÿ�� ���� �ʵ带 ��Ȱ��ȭ (�������ϰ�)
                    generateButton.setEnabled(false); // ���Ź�ư�� �������� ��Ȱ��ȭ
                    generateButton.setBackground(new Color(130, 130, 130));
                    t.start(); // �����带 �����Ѵ�!
                }
                else{
                    autoLoop=false; // �����尡 ����ǰ����� �� �����ٸ� ����.
                    t.interrupt(); // ��Ʈ���̴� �������� �Ǿ��־ ���ͷ�Ʈ�� ���Ƿ� �߻����� �����ߴ�.
                    sleepTimeTextTield.setEditable(true); // �ٽ� Ȱ��ȭ
                    autoButton.setBackground(new Color(148, 167, 255));
                    generateButton.setBackground(new Color(148, 167, 255));
                    generateButton.setEnabled(true); // �ٽ� Ȱ��ȭ
                }
            }
            //Copyright2018.�̻��(futureprogrammer@naver.com) all rights reserved.
            @Override
            public void run() { // ������ start�� ����� �� ����
                //generateButton.notify();
                autoLoop=true;
                while(autoLoop&&!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(sleepTime); // ������ �и��ʸ�ŭ ������
                    } catch (InterruptedException e1) {
                        //e1.printStackTrace();
                    }
                    generateButton.dispatchEvent(event); 
                    generateButton.getActionListeners(); // ���Ź�ư�� ������ ó�� ó���Ѵ�.
                    canvas.repaint(); // �׸��� �ٽ� �׷��ֶ�� ��û�� ���־�� ����� �׷�����.
                }
            }
        }
        autoGenerationButton.addActionListener(new myActListener()); // �ش� �ڵ鷯�� �����ʿ� �޾��ش�.



        // ��� dead�� ���·� ����� ��ư
        Button clearButton = new Button("Clear (all dead)");
        clearButton.setBounds(770,50,224,40); // ��ư ��ġ, ũ�� ����
        clearButton.setFont(defaultFont);
        clearButton.addActionListener(new ActionListener() { // ��ư �׼� ������ ����
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<arr.length;++i){ // ���� �迭 ��ü�� ���鼭.
                    for(int j=0;j<arr.length;++j){
                        arr[i][j]=false; // Ŭ���� ��� false(dead)�ϰ� ����.
                    }
                }
                liveCell=0; // ���� ���� �ʱ�ȭ
                generation=0; // ����� �ʱ�ȭ
                gen_label.setText(generation+" ����"); // �󺧿� ������� ���� �������ش�.
                cell_label.setText(liveCell+" ����"); // �󺧿� ������ ����
                canvas.repaint(); // �������� �ٽ� �׷��ش�.
            }
        });

        // ��� true(����)���� �������ִ� ��ư
        Button ClearLiveButton = new Button("Clear (all live)");
        ClearLiveButton.setBounds(770,100,224,40);
        ClearLiveButton.setFont(defaultFont);
        ClearLiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<arr.length;++i){ // ���� �迭 ��ü�� ���鼭
                    for(int j=0;j<arr.length;++j){
                        arr[i][j]=true; // ��� true(����) ���·� ����
                    }
                }
                liveCell=13*13; // ���� ������ ��ü�̹Ƿ� 13*13
                generation=0; // ����� �ʱ�ȭ
                gen_label.setText(generation+" ����"); // �󺧿� ������� ���� �������ش�.
                cell_label.setText(liveCell+" ����"); // �󺧿� ������ ����
                canvas.repaint(); // �������� �ٽ� �׷��ش�.
            }
        });



        // �������� �������ִ� ��ư
        Button setRandomButton = new Button("Set RANDOM");
        setRandomButton.setBounds(770,150,224,40);
        setRandomButton.setFont(defaultFont);
        setRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRandomArr(); // �������� �����ϴ� �Լ� ȣ��
                generation=0; // ����� �ʱ�ȭ
                gen_label.setText(generation+" ����"); // �󺧿� ������� ���� �������ش�.
                cell_label.setText(liveCell+" ����"); // �󺧿� ������ ����
                canvas.repaint(); // �������� �ٽ� �׷��ش�.
            }
        });

        Label setSleepTime = new Label("���Žð�(1000�̻� 5000����)");
        setSleepTime.setBounds(610,510,170,16);

        sleepTimeTextTield = new TextField(String.valueOf(sleepTime),100);
        sleepTimeTextTield.setBounds(610,536,72,16);
        sleepTimeTextTield.addActionListener(new ActionListener() { // ���� �ؽ�Ʈ�� �׼Ǹ����ʼ���
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
                    Dialog dl = new Dialog(f,"�߸��� �Է�"); // �� ���δ� ���� ���۰� �����Ͽ� ���� ����
                    dl.setBounds(screenSize.width/2-140/2,screenSize.height/2-90/2,140,90);
                    dl.setLayout(new FlowLayout());

                    Label msg = new Label("�߸��� �Է��Դϴ�. (���Է�)");
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
        //Copyright2018.�̻��(futureprogrammer@naver.com) all rights reserved.
        Label millicho = new Label("�и���");
        millicho.setBounds(683,536,46,16);

        JPanel jp = null; // �ش� J�г��� GIF�̹����� �ֱ����ؼ� ����.
        try {
            jp = new MyPanel(); // �ش� J�г��� GIF�̹����� �ֱ����ؼ� ����.
            jp.setBounds(770,250,224,224);
        }
        catch (Exception e){

        }
        if(jp!=null)
            add(jp);

        //770,202 (���ӷΰ�)
        Label logo = new Label("The Game Of Life",Label.CENTER);
        logo.setBounds(770,202,224,37);
        Font font = new Font("Serif",Font.ITALIC,20);
        logo.setFont(font);
        add(logo);

        //770,476 (������ó)
        Label source = new Label("������ó:��Ű���:Conway's GameOfLife",Label.CENTER);
        source.setBounds(770,476,224,37);
        Font font2 = new Font("SansSerif",Font.PLAIN,12);
        source.setFont(font2);
        add(source);

        //610,50 Help����
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
        //Copyright2018.�̻��(futureprogrammer@naver.com) all rights reserved.
        //610,134 ��Ģ��ư
        Button rules = new Button("��Ģ");
        rules.setForeground(new Color(6,69,173));//link�� ���̵��� ������(�⺻ ��ũ�� ������.)
        rules.setFont(font);
        rules.setBackground(new Color(255, 227, 1));
        rules.setBounds(610,134-20,120,40);
        // ��Ģ ��ư ��ũ�� �����Ͽ� �������� ����
        // �ҽ��� ���� �Ѱ� : https://code.i-harness.com/ko-kr/q/a7599b
        rules.addActionListener(new ActionListener() { // ��ũ�� �����Ű�� ���ؼ� �����Ǿ���
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://ko.wikipedia.org/wiki/%EB%9D%BC%EC%9D%B4%ED%94%84_%EA%B2%8C%EC%9E%84");
            }
            public void openWebPage(String url) {
                try {
                    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(new URI(url));
                    }// �ҽ��� ���� �Ѱ� : https://code.i-harness.com/ko-kr/q/a7599b
                    throw new NullPointerException();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "������ ������ ��Ģ�� ��ũ�� �������ϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        //ī�Ƕ���
        Label copyright = new Label("Copyright2018.�̻�� all rights reserved.");
        copyright.setBounds(770,537,400,20);
        Label copyright2 = new Label("futureprogrammer@naver.com");
        copyright2.setBounds(770,537+20,400,35);

        // this.add() �� ���� ���� !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        setRandomArr(); // ������ ���� �����ϰ� �������� ������ �ش�.
        cell_label.setText(liveCell+" ����"); // �󺧿� ������ ����
        add(generateButton); // generate ��ư �߰�
        add(autoGenerationButton); // auto generate ��ư �߰�
        add(canvas); // canvas �߰�
        add(gen_label); // ����� �� �߰�
        add(cell_label); // ������ �� �߰�
        add(clearButton); // clear ��ư �߰�
        add(ClearLiveButton); //ClearLive ��ư �߰�
        add(setRandomButton); // setRandom ��ư �߰�
        add(setSleepTime); // ���Žð� �� �߰�
        add(sleepTimeTextTield); // ���Žð� �ؽ�Ʈ �ʵ� �߰�
        add(millicho); // �и��� �� �߰�
        setVisible(true); // â ����ȭ
        add(helpButton);//������ư�߰�
        add(rules);//��Ģ��ư�߰�
        add(copyright);//ī�Ƕ���Ʈ �߰�
        add(copyright2);//ī�Ƕ���Ʈ �߰�
    }// end of LifeGame() �⺻������///////////////////////////////////////////////////////////////////////////////////////////

    private void setRandomArr(){
        liveCell=0;
        for(int i=0;i<arr.length;++i){ // �����迭 arr�� ���鼭
            for(int j=0;j<arr.length;++j){
                if(generator.nextBoolean()){ // �����ϰ� true�� ���ý�
                    arr[i][j]=true; // �ش� �κ��� ������ true(����) ��Ų��.
                    liveCell++;
                }
                else
                    arr[i][j]=false;
            }
        }
    }
    // ������ ���ӿ� ����� ĵ������ �����Ѵ�.
    public class LifeGameCanvas extends Canvas{
        final int blockSize=40; // ������� ������
        public LifeGameCanvas() { // ���������� ���� ĵ������ �⺻ ������
            setBackground(new Color(174, 174, 174)); // ���� ����
            setBounds(50,50,521,521); // ��ġ �� ũ�� ����

            //���콺 Ŭ���� ������ �ٲ� �� �ֵ��� ���콺 �����ʸ� �����Ѵ�.
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) { // ���콺�� Ŭ���ϰ� ������
                    Point p = e.getPoint(); // Ŭ���� ���� ����Ʈ�� ���´�.
                    int x=p.x/40;
                    int y=p.y/40; // 13x13 �迭 ������ ����ϱ� ���� 40�� ������.
                    Graphics g = getGraphics(); // �׸��� ���ؼ� �׷����� ���´�.

                    // ���� �̹� ��� ���� ���
                    if(arr[x][y]) {
                        liveCell--; // ����ִ� ���� ���� 1����
                        arr[x][y] = false; // ���δ�.
                        g.setColor(deadColor); // ����� ���� ����Ͽ� �����.
                        g.fillRect(x*blockSize+1,y*blockSize+1,blockSize-1,blockSize-1);
                    }
                    else { // �׷��� �ʰ� �׾��ִ� ���
                        liveCell++; // ����ִ� ���� ���� 1����
                        arr[x][y] = true; // �츰��.
                        g.setColor(liveColor); // �츮�� ���� ����Ͽ� ĥ�Ѵ�.
                        g.fillRect(x*blockSize+1,y*blockSize+1,blockSize-1,blockSize-1);
                    }
                    cell_label.setText(liveCell+" ����");
                    invalidate(); // �ٽ� �׷��ֱ� ���ؼ� invalidate�� ȣ���Ѵ�.
                }
            });
        } // end of LifeGameCanvas() �⺻������
        //Copyright2018.�̻��(futureprogrammer@naver.com) all rights reserved.
        public void paint(Graphics g)
        {
            g.setColor(Color.BLACK); // �׸��� �� ���������� ����
            // �׵θ� �� �׸���
            for(int x=0;x<=520;x+=blockSize){
                g.drawLine(x,0,x,521);
                g.drawLine(0,x,521,x);
            }
            // arr �迭 ��ü�� ���鼭 �˸��� ��ġ�� ĵ������ �簢���� �׸���.
            g.setColor(liveColor);
            liveCell=0; // ����ִ� ���� ���� �ʱ�ȭ
            for(int i=0;i<arr.length;++i){
                for(int j=0;j<arr.length;++j){
                    if(arr[i][j]){
                        // ����ִ� ���� ��Ÿ���� ���� �簢�� �׸���
                        g.fillRect(i*blockSize+1,j*blockSize+1,blockSize-1,blockSize-1);
                        liveCell++; // ����ִ� ���� ���� ī��Ʈ
                    }
                }
            }

        } // end of paint()
    }// end of class of LifeGameCanvas

    class MyPanel extends JPanel { // Gif�� �����ֱ����ؼ� J�г��� ��ӹ޾� ����
        Image image;
        MyPanel() throws MalformedURLException {
            image = Toolkit.getDefaultToolkit().createImage("LifeGame.gif");
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this); // �̹����� ����� ���ȴٸ� �׷��ش�.
            }
        }
    }

    // main (for test)
//    public static void main(String[] args){
//        new LifeGame();
//    }
}
//Copyright2018.�̻��(futureprogrammer@naver.com) all rights reserved.
package windows;

import bean.keybean;
import bean.keylockbean;
import bean.lockbean;
import com.google.gson.Gson;
import core.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

class MainJframe extends JFrame implements ActionListener {
    HashSet<Key1> k1=null;
    HashSet<Lock1> l1=null;
    HashSet<KeyLock> kl1=null;
    JTextArea jtakey;
    JScrollPane jsp;       //key
    JTextArea jtalock;
    JScrollPane sp ;   //lock
    JTextField searchtextfield;
    JComboBox comboBox;
    static Main m;
    public static void main(String[] args) {

        MainJframe test=new MainJframe();

    }
    public MainJframe(){
        this.setTitle("Main Interface");
        this.setBounds(100, 100, 1500, 900);

        JPanel panel2 = new JPanel();
        // 添加面板
        this.add(panel2);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel2);
        initialdata();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         }

       public void placeComponents(JPanel panel)
       {
           panel.setLayout(null);
//           JButton InitialButton = new JButton("Initial Data");
//           InitialButton.setPreferredSize(new Dimension(100,30));
//           InitialButton.setFont(new   java.awt.Font("Dialog",   1,   20));
//           InitialButton.setBounds(130, 90, 160, 50);
//           panel.add(InitialButton);
//           InitialButton.addActionListener(this);

           JButton RefreshButton = new JButton("Refresh Data");
           RefreshButton.setPreferredSize(new Dimension(100,30));
           RefreshButton.setFont(new   java.awt.Font("Dialog",   1,   20));
           //RefreshButton.setBounds(320, 90, 160, 50);
           RefreshButton.setBounds(130, 90, 160, 50);
           panel.add(RefreshButton);
           RefreshButton.addActionListener(this);

           JButton ComboButton = new JButton("Add a combination");
           ComboButton.setPreferredSize(new Dimension(190,30));
           ComboButton.setFont(new   java.awt.Font("Dialog",   1,   20));
           ComboButton.setBounds(130, 195, 220, 50);
           panel.add(ComboButton);
           ComboButton.addActionListener(this);

           JButton KeyButton = new JButton("Add a new key");
           KeyButton.setPreferredSize(new Dimension(190,30));
           KeyButton.setFont(new   java.awt.Font("Dialog",   1,   20));
           KeyButton.setBounds(130, 300, 190, 50);
           panel.add(KeyButton);
           KeyButton.addActionListener(this);

//           JButton UpdateKeyButton = new JButton("Update a key");
//           UpdateKeyButton.setPreferredSize(new Dimension(190,30));
//           UpdateKeyButton.setFont(new   java.awt.Font("Dialog",   1,   20));
//           UpdateKeyButton.setBounds(350, 300, 180, 50);
//           panel.add(UpdateKeyButton);
//           UpdateKeyButton.addActionListener(this);

            jtakey=new JTextArea(100,100);
           //jtakey.setBounds(900,70,500,350);
           jtakey.setFont(new   java.awt.Font("Dialog",   0,   20));
           jsp = new JScrollPane(jtakey);
           jsp.setBounds(900,70,500,350);
           //jsp.setPreferredSize(new Dimension(100,100));
          //jsp.setViewportView(jtakey);
           panel.add(jsp);
          //panel.add(jtakey);


            jtalock=new JTextArea(100,100);
          // jtalock.setBounds(900,480,500,350);
           jtalock.setFont(new   java.awt.Font("Dialog",   0,   20));

           sp= new JScrollPane(jtalock);
           sp.setBounds(900,480,500,350);
           panel.add(sp);

            comboBox=new JComboBox();
           comboBox.addItem("Search a key");
           comboBox.addItem("Search a lock");
           comboBox.setBounds(130,450,120,30);
           panel.add(comboBox);

           searchtextfield = new JTextField();
           searchtextfield.setBounds(130,500,320,50);
           panel.add(searchtextfield);
           //String value = textfield.getText().trim();

           JButton SearchButton = new JButton("Search");
           SearchButton.setPreferredSize(new Dimension(100,30));
           SearchButton.setFont(new   java.awt.Font("Dialog",   1,   20));
           SearchButton.setBounds(470, 500, 100, 50);
           panel.add(SearchButton);
           SearchButton.addActionListener(this);

       }

    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();
        if(source=="Initial Data")
        {
           initialdata();
        }
       else if(source=="Search")
        {
           SearchButtonFunction();
       }
       else if(source=="Add a combination")
        {
            AddCombinationFunction();
        }
        else if(source=="Add a new key")
        {
              AddNewKeyFunction();
        }
        else if(source=="Update a key")
        {
               UpdateKeyFunction();
        }
        else if(source=="Refresh Data")
        {
            RefreshDataFunction();
        }

    }

    public static void UpdateKeyFunction() {
        new UpdateKeyJframe();
    }

    private void RefreshDataFunction() {
        ConvertJson cj=new ConvertJson();
        k1=m.getAllKeys();
        l1=m.getAllLocks();
        kl1=m.getAllCombos();

        String keys= cj.set2json(m.getAllKeys());
        String locks= cj.set2json(m.getAllLocks());
        String combos= cj.set2json(m.getAllCombos());
        keybean[] kb=new Gson().fromJson(keys,keybean[].class);
        lockbean[] lb=new Gson().fromJson(locks,lockbean[].class);
        keylockbean[] klb=new Gson().fromJson(combos,keylockbean[].class);
        jtakey.setText("");
        jtalock.setText("");
        System.out.println("keys infer"+keys);

        for(int x=0;x<kb.length;x++)
        {
            int id=Integer.parseInt(kb[x].getID());
            try {
                if(m.searchLocksOpenedByGivenKey(id).size()==0)
                {
                   continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            jtakey.append("Key [ ID: "+kb[x].getID()+" type: "+kb[x].isType()+"]"+"\r\n");
        }

        for(int x=0;x<lb.length;x++)
        {
            jtalock.append("Lock [ ID: "+lb[x].getID()+" roomNumber: "+lb[x].getRoomNumber()+"]"+"\r\n");
        }


    }

    private void AddNewKeyFunction() {
        new AddKeyJframe();

    }

    private void AddCombinationFunction() {

        new AddComboJframe();
    }

    private void SearchButtonFunction() {
        String value = searchtextfield.getText().trim();
        int v=Integer.parseInt(value);
        ConvertJson cj=new ConvertJson();
        if(comboBox.getSelectedItem().toString()=="Search a key")
        {
            lockbean[] lb;
            try {
                Key1 keyinfer=m.searchKey(v);

                if(m.searchLocksOpenedByGivenKey(v).size()!=0)
                {
                    String lock=cj.set2json(m.searchLocksOpenedByGivenKey(v));
                    lb=new Gson().fromJson(lock,lockbean[].class);
                }
                else{
                    throw new Exception();
                }
                //System.out.println("data01 = " + lb[0].getRoomNumber());
                // System.out.println("lb infer " +lb.length);
                // System.out.println("data02 = " + lb[0].getID());

                new KeyInferJframe(keyinfer,lb);


            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this,"There is no such key.", "Wrong", JOptionPane.ERROR_MESSAGE);
                //"There is no such key."
            }
        }
        else{

            try {
                Lock1 lockinfer=m.searchLock(v);
                String key=cj.set2json(m.searchKeysOpeningGivenLock(v));
                keybean[] kb=new Gson().fromJson(key,keybean[].class);
                new LockInferJframe(lockinfer,kb);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "There is no such lock.", "Wrong", JOptionPane.ERROR_MESSAGE);
            }

        }
    }



    public void initialdata()
    {
         m=new Main();
        ConvertJson cj=new ConvertJson();
        k1=m.getAllKeys();
        l1=m.getAllLocks();
        kl1=m.getAllCombos();

        String keys= cj.set2json(m.getAllKeys());
        String locks= cj.set2json(m.getAllLocks());
        String combos= cj.set2json(m.getAllCombos());
        keybean[] kb=new Gson().fromJson(keys,keybean[].class);
        lockbean[] lb=new Gson().fromJson(locks,lockbean[].class);
        keylockbean[] klb=new Gson().fromJson(combos,keylockbean[].class);
//        System.out.println("name01 = " + kb[0].getID());
//        System.out.println("name02 = " + kb[1].getID());
        for(int x=0;x<kb.length;x++)
        {
            jtakey.append("Key [ ID: "+kb[x].getID()+" type: "+kb[x].isType()+"]"+"\r\n");
        }

        for(int x=0;x<lb.length;x++)
        {
            jtalock.append("Lock [ ID: "+lb[x].getID()+" roomNumber: "+lb[x].getRoomNumber()+"]"+"\r\n");
        }

    }
}
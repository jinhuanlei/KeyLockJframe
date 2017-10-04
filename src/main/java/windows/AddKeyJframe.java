package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddKeyJframe extends JFrame implements ActionListener {
    JTextField jtf;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    boolean selected;
    public static void main(String args[])
    {
        AddKeyJframe akj=new AddKeyJframe();
    }

    public AddKeyJframe()
    {
        this.setTitle("Add");
        this.setBounds(720, 360, 550, 360);
        JPanel panel2 = new JPanel();
        this.add(panel2);
        placeComponents(panel2);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel jl=new JLabel("Lock ID :");
       jl.setBounds(50,50,100,30);
        jl.setFont(new   java.awt.Font("Dialog",   1,   20));
        panel.add(jl);

       jtf=new JTextField();
        jtf.setBounds(150,50,120,30);
        panel.add(jtf);

        JLabel j2=new JLabel("Key Type :");
        j2.setBounds(50,100,120,30);
        j2.setFont(new   java.awt.Font("Dialog",   1,   20));
        panel.add(j2);

        radioButton1=new JRadioButton("Swipe Card",true);
        radioButton2=new JRadioButton("Physical key");
        selected=  radioButton1.isSelected();
        System.out.println("..........................."+selected);
        radioButton1.setBounds(150,105,120,30);
        radioButton2.setBounds(270,105,120,30);
        panel.add(radioButton1);
        panel.add(radioButton2);
        ButtonGroup group=new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);

        JButton submitButton=new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100,30));
        submitButton.setFont(new   java.awt.Font("Dialog",   1,   20));
        submitButton.setBounds(200, 200, 100, 50);
        panel.add(submitButton);
        submitButton.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();
        if(source=="Submit")
        {
            System.out.println("..........................."+selected);
        }
    }
}

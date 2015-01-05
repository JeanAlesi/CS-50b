import java.awt.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class test extends JPanel implements ActionListener
{
     private JTextField display = new JTextField("0");
     private String buttonText = "789/456*123-0.=+";
     private double result = 0;
     private String operator = "=";
     private boolean calculating = true;
     public test()
     {
          setLayout(new BorderLayout());
          display.setEditable(false);
          add(display, "North");
          JPanel p = new JPanel();
          p.setLayout(new GridLayout(4, 4));
          for (int i = 0; i < buttonText.length(); i++) {
          JButton b = new JButton(buttonText.substring(i, i + 1));
          p.add(b);
          b.addActionListener(this);
     }
     add(p, "Center");
 }
 public void actionPerformed(ActionEvent e)
 {
      String cmd = e.getActionCommand();
      if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
      if (calculating)
        display.setText(cmd);
      else
        display.setText(display.getText() + cmd);
       calculating = false;
    }
    else
    {
        if (calculating)
        {
           if (cmd.equals("-"))
           {
               display.setText(cmd);
               calculating = false;
           }
           else
           operator = cmd;
       }
       else
       {
          double x = Double.parseDouble(display.getText());
          calculate(x);
          operator = cmd;
          calculating = true;
       }
    }
  }
  private void calculate(double n)
  {
     if (operator.equals("+"))
      result += n;
    else if (operator.equals("-"))
      result -= n;
    else if (operator.equals("*"))
      result *= n;
    else if (operator.equals("/"))
      result /= n;
    else if (operator.equals("="))
      result = n;
    display.setText("" + result);
   }
   public static void main(String[] args)
   {
       JFrame frame = new JFrame();
       frame.setTitle("Calculator");
       frame.setSize(200, 200);
       frame.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent e) {
       System.exit(0);
   }
});
    Container contentPane = frame.getContentPane();
    contentPane.add(new test());
    frame.show();
  }
}
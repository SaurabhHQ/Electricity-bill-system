package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class Payment_bill extends JFrame implements ActionListener {

    JButton back;
    String meter;

    Payment_bill(String meter) {
        this.meter = meter;

        setTitle("Pay Bill");
        setSize(400, 200);
        setLocation(500, 300);
        setLayout(null);

        JLabel msg = new JLabel("Opening payment gateway in browser...");
        msg.setBounds(50, 40, 300, 30);
        add(msg);

        back = new JButton("Back");
        back.setBounds(150, 100, 100, 30);
        back.addActionListener(this);
        add(back);

        // 🔥 OPEN PAYTM IN SYSTEM BROWSER
        try {
            Desktop.getDesktop().browse(
                    new URI("https://paytm.com/online-payments")
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open browser");
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Pay_bill(meter);
    }

    public static void main(String[] args) {
        new Payment_bill("101");
    }
}

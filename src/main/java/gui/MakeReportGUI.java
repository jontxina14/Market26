package gui;

import businessLogic.BLFacade;
import domain.Sale;
import domain.SaleContainer;
import enums.ReportReason;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.objectdb.o.CLN.s;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class MakeReportGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel errorLabel;
    private ButtonGroup radioGroup;

    public MakeReportGUI(String reporterMail, Sale s) {
        ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

        setTitle(bundle.getString("MakeReportGUI.Title"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 320);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPane.setLayout(new BorderLayout(10, 15));
        setContentPane(contentPane);

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        contentPane.add(errorLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel lblReason = new JLabel(bundle.getString("MakeReportGUI.Reason"));
        lblReason.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblReason.setAlignmentX(LEFT_ALIGNMENT);
        centerPanel.add(lblReason);
        centerPanel.add(Box.createVerticalStrut(8));

        radioGroup = new ButtonGroup();
        for (ReportReason reason : ReportReason.values()) {
            JRadioButton rb = new JRadioButton(bundle.getString("MakeReportGUI." + reason.name()));
            rb.setActionCommand(reason.name());
            rb.setAlignmentX(LEFT_ALIGNMENT);
            radioGroup.add(rb);
            centerPanel.add(rb);
        }

        contentPane.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        JButton btnSend = new JButton(bundle.getString("MakeReportGUI.SendButton"));
        JButton btnClose = new JButton(bundle.getString("Close"));
        btnSend.setPreferredSize(new Dimension(140, 28));
        btnClose.setPreferredSize(new Dimension(140, 28));

        buttonPanel.add(btnSend);
        buttonPanel.add(btnClose);
        btnClose.setBackground(new Color(231, 76, 60));
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> dispose());

        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButtonModel selected = radioGroup.getSelection();
                if (selected == null) {
                    errorLabel.setText(bundle.getString("MakeReportGUI.NoReasonSelected"));
                    return;
                }
                ReportReason reason = ReportReason.valueOf(selected.getActionCommand());
                BLFacade facade = MainGUInonReg.getBusinessLogic();
                facade.makeReport(reporterMail, s.getSaleNumber(), reason);
                dispose();
            }
        });
    }
}
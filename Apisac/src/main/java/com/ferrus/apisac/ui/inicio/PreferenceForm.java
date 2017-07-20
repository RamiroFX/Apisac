/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.util.AppUIConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

/**
 *
 * @author Ramiro Ferreira
 */
public class PreferenceForm extends JDialog implements ActionListener {

    private JButton selectButton;
    private JButton cancelButton;
    private JLabel studentName;
    private JComboBox lookAndFellComboBox;

    public PreferenceForm(JFrame parent) {
        super(parent, AppUIConstants.PREFERENCE_FORM_TITLE, false);
        initializeVariables();
        loadData();
        constructLayout();
        setWindows(parent);
    }

    private void loadData() {

        this.lookAndFellComboBox.removeAllItems();
        List<UIManager.LookAndFeelInfo> lafList = new ArrayList<>();

        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        lafList.addAll(Arrays.asList(lafInfo));
        for (UIManager.LookAndFeelInfo lookAndFeelInfo : lafInfo) {
            lookAndFellComboBox.addItem(lookAndFeelInfo);
        }
    }

    private void setWindows(JFrame parent) {
        this.setSize(AppUIConstants.PREFERENCE_WINDOWS_SIZE_WIDTH, AppUIConstants.PREFERENCE_WINDOWS_SIZE_HEIGHT);
        this.setLocationRelativeTo(parent);
    }

    private void initializeVariables() {
        /*this.removeStudentFormService = new RemoveStudentFormServiceImpl();*/
        this.lookAndFellComboBox = new JComboBox<>();
        this.lookAndFellComboBox.setRenderer(new ColorCellRenderer());
        this.selectButton = new JButton(AppUIConstants.PREFERENCE_SELECT_BTN);
        this.cancelButton = new JButton(AppUIConstants.PREFERENCE_CANCEL_BTN);
        this.studentName = new JLabel(AppUIConstants.PREFERENCE_LNF);

        this.selectButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
    }

    private void constructLayout() {
        JPanel studentInfoPanel = new JPanel();
        JPanel studentButtonPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder(AppUIConstants.PREFERENCE_FORM_TITLE);

        studentInfoPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        studentInfoPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;

        Insets rightPadding = new Insets(0, 0, 0, 15);
        Insets noPadding = new Insets(0, 0, 0, 0);

        ///// FIRST ROW /////
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        studentInfoPanel.add(studentName, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        studentInfoPanel.add(lookAndFellComboBox, gc);

        //// BUTTON PANEL /////
        studentButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        studentButtonPanel.add(selectButton);
        studentButtonPanel.add(cancelButton);

        ///// SET LAYOUT /////
        setLayout(new BorderLayout());
        add(studentInfoPanel, BorderLayout.CENTER);
        add(studentButtonPanel, BorderLayout.SOUTH);
    }

    private void selectLNF() {
        UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) lookAndFellComboBox.getSelectedItem();
        try {
            UIManager.setLookAndFeel(info.getClassName());
            for (Window window : JFrame.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PreferenceForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();
        if (src.equals(cancelButton)) {
            this.setVisible(false);
        } else if (src.equals(selectButton)) {
            selectLNF();
        }
    }
}

class ColorCellRenderer implements ListCellRenderer {

    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    private final static Dimension preferredSize = new Dimension(100, 20);

    public Component getListCellRendererComponent(JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
        UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) value;
        renderer.setText(info.getName());
        return renderer;
    }
}

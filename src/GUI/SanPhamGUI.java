package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import BUS.ToppingBUS;
import BUS.NguyenlieuBUS;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.ToppingDTO;
import DTO.cartProduct;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import GUI.Component.ConfirmDialog;

public class SanPhamGUI extends JPanel {
    private static final int PRODUCT_COLUMNS = 3;
    private static final int PRODUCT_CARD_WIDTH = 220;
    private static final int PRODUCT_CARD_HEIGHT = 270;
    private static final int PRODUCT_GAP = 16;
    private static final int CART_PANEL_WIDTH = 350;

    public SanPhamBUS spBUS = new SanPhamBUS();
    public ToppingBUS tpBUS = new ToppingBUS();
    public NguyenlieuBUS nlBUS = new NguyenlieuBUS();
    public KhachHangBUS khBUS = new KhachHangBUS();
    public NhanVienBUS nvBUS = new NhanVienBUS();
    public PhieuXuatBUS pxBUS = new PhieuXuatBUS();
    private ArrayList<JPanel> ListProductPanel = new ArrayList<>();
    public JScrollPane scrollPane;
    public ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    private final ArrayList<cartProduct> cartProducts = new ArrayList<>();
    private JPanel cartItemsPanel;
    private JLabel totalPriceLabel;
    private JTextField phoneField;
    private JComboBox<String> employeeCombo;
    private JLabel customerIdValueLabel;
    private JLabel customerNameValueLabel;
    private KhachHangDTO selectedCustomerFromEnter;
    private double totalPrice = 0;
    private final Color pageBackground = new Color(252, 246, 249);
    private final Color cardBackground = Color.WHITE;
    private final Color cardBorder = new Color(237, 216, 227);
    private final Color actionButtonColor = new Color(236, 90, 150);
    private final Color actionHoverColor = new Color(214, 72, 134);
    private final Color titleColor = new Color(110, 58, 88);
    private final Color nameColor = new Color(99, 68, 86);
    private final Color panelBorder = new Color(231, 208, 221);
    
    public SanPhamGUI() {
        setLayout(new BorderLayout());
        setBackground(pageBackground);

        JLabel header = new JLabel("Menu do uong");
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(titleColor);
        header.setBorder(BorderFactory.createEmptyBorder(12, 20, 8, 0));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(header, BorderLayout.WEST);

        JPanel mainPanel = new JPanel(new GridLayout(0, PRODUCT_COLUMNS, PRODUCT_GAP, PRODUCT_GAP));
        mainPanel.setBackground(pageBackground);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        for (int i = 0; i < listSP.size(); i++) {
            String img = spBUS.imgLink(i);
            String nameSp = spBUS.getName(i);
            int idSelectedSP = spBUS.getId(i);
            JPanel productPanel = createProductPanel(idSelectedSP, img, nameSp);
            ListProductPanel.add(productPanel);
            mainPanel.add(productPanel);
        }

        int rows = (listSP.size() + PRODUCT_COLUMNS - 1) / PRODUCT_COLUMNS;
        int preferredGridWidth = PRODUCT_COLUMNS * PRODUCT_CARD_WIDTH + (PRODUCT_COLUMNS - 1) * PRODUCT_GAP + 20;
        int preferredGridHeight = rows * PRODUCT_CARD_HEIGHT + Math.max(0, rows - 1) * PRODUCT_GAP + 30;
        mainPanel.setPreferredSize(new Dimension(preferredGridWidth, preferredGridHeight));

        JPanel gridWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        gridWrap.setBackground(pageBackground);
        gridWrap.add(mainPanel);

        scrollPane = new JScrollPane(gridWrap);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(pageBackground);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.add(topPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel rightPanel = buildCartPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        splitPane.setResizeWeight(1.0);
        splitPane.setDividerSize(7);
        splitPane.setContinuousLayout(true);
        SwingUtilities.invokeLater(() -> splitPane.setDividerLocation(Math.max(760, splitPane.getWidth() - CART_PANEL_WIDTH)));

        add(splitPane, BorderLayout.CENTER);
    }
   

    private JPanel createProductPanel(int idSelectedSP, String img, String nameSp) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setPreferredSize(new Dimension(PRODUCT_CARD_WIDTH, PRODUCT_CARD_HEIGHT));
        productPanel.setMinimumSize(new Dimension(PRODUCT_CARD_WIDTH, PRODUCT_CARD_HEIGHT));
        productPanel.setBackground(cardBackground);
        productPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(cardBorder, 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        try {
            InputStream inputStream = getClass().getResourceAsStream(img);
            if (inputStream != null) {
                BufferedImage image = ImageIO.read(inputStream);
                int newWidth = 145;
                int newHeight = 150;
                Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon imgProduct = new ImageIcon(scaledImage);
                JLabel imgLabel = new JLabel(imgProduct);
                imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(imgLabel);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        productPanel.add(Box.createVerticalStrut(10));

        JLabel labelProduct = new JLabel("", JLabel.CENTER);
        labelProduct.setText(nameSp);
        Font font = new Font("Segoe UI", Font.BOLD, 17);
        labelProduct.setFont(font);
        labelProduct.setForeground(nameColor);
        labelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelProduct.setHorizontalAlignment(SwingConstants.CENTER);
        labelProduct.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        productPanel.add(labelProduct);

        productPanel.add(Box.createVerticalGlue());

        JPanel actionPanel = new JPanel(new BorderLayout());
        actionPanel.setOpaque(false);
        actionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JButton btnChoose = new JButton("Chọn món");
        btnChoose.setFocusPainted(false);
        btnChoose.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnChoose.setForeground(Color.WHITE);
        btnChoose.setBackground(actionButtonColor);
        btnChoose.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        btnChoose.setCursor(new Cursor(Cursor.HAND_CURSOR));

        actionPanel.add(btnChoose, BorderLayout.CENTER);
        productPanel.add(Box.createVerticalStrut(10));
        productPanel.add(actionPanel);
        productPanel.setVisible(true);

        btnChoose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnChoose.setBackground(actionHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnChoose.setBackground(actionButtonColor);
            }
        });

        btnChoose.addActionListener(e -> openConfigDialog(idSelectedSP, nameSp, null, -1));

        productPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openConfigDialog(idSelectedSP, nameSp, null, -1);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                productPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(229, 157, 193), 2),
                        BorderFactory.createEmptyBorder(11, 11, 11, 11)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                productPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(cardBorder, 1),
                        BorderFactory.createEmptyBorder(12, 12, 12, 12)));
            }
        });

        return productPanel;
    }

    private JPanel buildCartPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout(0, 10));
        rightPanel.setPreferredSize(new Dimension(CART_PANEL_WIDTH, 0));
        rightPanel.setMinimumSize(new Dimension(320, 0));
        rightPanel.setBackground(new Color(250, 240, 247));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, panelBorder),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        JLabel cartTitle = new JLabel("Gio hang", SwingConstants.LEFT);
        cartTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cartTitle.setForeground(titleColor);

        JPanel titleWrap = new JPanel(new BorderLayout());
        titleWrap.setOpaque(false);
        titleWrap.add(cartTitle, BorderLayout.WEST);
        rightPanel.add(titleWrap, BorderLayout.NORTH);

        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE);
        cartItemsPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JScrollPane cartScroll = new JScrollPane(cartItemsPanel);
        cartScroll.setBorder(BorderFactory.createLineBorder(panelBorder));
        cartScroll.getViewport().setBackground(Color.WHITE);
        cartScroll.getVerticalScrollBar().setUnitIncrement(12);
        cartScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightPanel.add(cartScroll, BorderLayout.CENTER);

        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new BoxLayout(checkoutPanel, BoxLayout.Y_AXIS));
        checkoutPanel.setOpaque(false);

        JPanel employeePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        employeePanel.setOpaque(false);
        JLabel employeeLabel = new JLabel("ID nhan vien:");
        employeeLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        employeeLabel.setForeground(nameColor);
        employeeCombo = new JComboBox<>();
        employeeCombo.addItem("Chon ID NV");
        for (NhanVienDTO nv : nvBUS.layDanhSachNhanVien()) {
            employeeCombo.addItem(String.valueOf(nv.getIdNhanVien()));
        }
        employeeCombo.setEditable(true);
        employeePanel.add(employeeLabel);
        employeePanel.add(employeeCombo);

        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        phonePanel.setOpaque(false);
        JLabel phoneLabel = new JLabel("So dien thoai:");
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        phoneLabel.setForeground(nameColor);
        phoneField = new JTextField(14);
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        phoneField.addActionListener(e -> performCustomerLookupByPhone());
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        JPanel customerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        customerPanel.setOpaque(false);
        JLabel customerLabel = new JLabel("ID khach hang:");
        customerLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        customerLabel.setForeground(nameColor);
        customerIdValueLabel = new JLabel("null");
        customerIdValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        customerIdValueLabel.setForeground(titleColor);
        JLabel customerNameLabel = new JLabel("Ten:");
        customerNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        customerNameLabel.setForeground(nameColor);
        customerNameValueLabel = new JLabel("-");
        customerNameValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        customerNameValueLabel.setForeground(titleColor);
        customerPanel.add(customerLabel);
        customerPanel.add(customerIdValueLabel);
        customerPanel.add(Box.createHorizontalStrut(8));
        customerPanel.add(customerNameLabel);
        customerPanel.add(customerNameValueLabel);

        phoneField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                markCustomerLookupPending();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                markCustomerLookupPending();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                markCustomerLookupPending();
            }
        });
        markCustomerLookupPending();

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        totalPanel.setOpaque(false);
        JLabel totalText = new JLabel("Tong tien:");
        totalText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalText.setForeground(nameColor);
        totalPriceLabel = new JLabel("0 VNĐ");
        totalPriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPriceLabel.setForeground(titleColor);
        totalPanel.add(totalText);
        totalPanel.add(totalPriceLabel);

        JButton checkoutButton = new JButton("Thanh toan");
        checkoutButton.setFocusPainted(false);
        checkoutButton.setBackground(actionButtonColor);
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        checkoutButton.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        checkoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkoutButton.setBackground(actionHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                checkoutButton.setBackground(actionButtonColor);
            }
        });
        checkoutButton.addActionListener(e -> checkoutCart());

        checkoutPanel.add(employeePanel);
        checkoutPanel.add(Box.createVerticalStrut(8));
        checkoutPanel.add(phonePanel);
        checkoutPanel.add(Box.createVerticalStrut(6));
        checkoutPanel.add(customerPanel);
        checkoutPanel.add(Box.createVerticalStrut(8));
        checkoutPanel.add(totalPanel);
        checkoutPanel.add(Box.createVerticalStrut(8));
        checkoutPanel.add(checkoutButton);

        rightPanel.add(checkoutPanel, BorderLayout.SOUTH);
        renderCart();
        return rightPanel;
    }

    private void openConfigDialog(int productId, String productName, cartProduct editingItem, int editingIndex) {
        Window owner = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(owner instanceof Frame ? (Frame) owner : null, "Chon mon", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(pageBackground);

        JLabel title = new JLabel(productName, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(titleColor);
        title.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        dialog.add(title, BorderLayout.NORTH);

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(Color.WHITE);
        body.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(panelBorder),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        sizePanel.setOpaque(false);
        JLabel sizeLbl = new JLabel("Size:");
        sizeLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JRadioButton sizeM = new JRadioButton("M", true);
        JRadioButton sizeL = new JRadioButton("L");
        if (editingItem != null && "L".equalsIgnoreCase(editingItem.getSize())) {
            sizeL.setSelected(true);
            sizeM.setSelected(false);
        }
        sizeM.setOpaque(false);
        sizeL.setOpaque(false);
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(sizeM);
        sizeGroup.add(sizeL);
        sizePanel.add(sizeLbl);
        sizePanel.add(sizeM);
        sizePanel.add(sizeL);

        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        qtyPanel.setOpaque(false);
        JLabel qtyLbl = new JLabel("So luong:");
        qtyLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        int initialQty = editingItem != null ? editingItem.getQuant() : 1;
        JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(initialQty, 1, 20, 1));
        qtyPanel.add(qtyLbl);
        qtyPanel.add(qtySpinner);

        JPanel toppingHeader = new JPanel(new GridLayout(1, 3));
        toppingHeader.setOpaque(true);
        toppingHeader.setBackground(new Color(247, 231, 239));
        toppingHeader.setBorder(BorderFactory.createLineBorder(panelBorder));
        toppingHeader.add(createDialogHeader("Topping"));
        toppingHeader.add(createDialogHeader("Gia"));
        toppingHeader.add(createDialogHeader("SL"));

        JPanel toppingRows = new JPanel();
        toppingRows.setLayout(new BoxLayout(toppingRows, BoxLayout.Y_AXIS));
        toppingRows.setBackground(Color.WHITE);
        ArrayList<JSpinner> toppingQty = new ArrayList<>();
        Map<String, Integer> initialToppingMap = parseToppingQuantities(editingItem != null ? editingItem.getToppings() : "");

        for (ToppingDTO tp : tpBUS.getAll()) {
            JPanel row = new JPanel(new GridLayout(1, 3));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
            row.setBackground(Color.WHITE);
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 232, 237)));

            JLabel name = new JLabel(tp.getNameTopping());
            name.setFont(new Font("Segoe UI", Font.PLAIN, 13));

            JLabel price = new JLabel(formatVnd(tp.getPriceTopping()), SwingConstants.CENTER);
            price.setFont(new Font("Segoe UI", Font.BOLD, 12));

            JSpinner sp = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
            Integer initialTopQty = initialToppingMap.get(tp.getNameTopping());
            if (initialTopQty != null) {
                sp.setValue(initialTopQty);
            }
            sp.setPreferredSize(new Dimension(60, 26));
            toppingQty.add(sp);
            JPanel spWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
            spWrap.setOpaque(false);
            spWrap.add(sp);

            row.add(name);
            row.add(price);
            row.add(spWrap);
            toppingRows.add(row);
        }

        JScrollPane toppingScroll = new JScrollPane(toppingRows);
        toppingScroll.setPreferredSize(new Dimension(420, 260));
        toppingScroll.setBorder(BorderFactory.createLineBorder(panelBorder));
        toppingScroll.getViewport().setBackground(Color.WHITE);

        body.add(sizePanel);
        body.add(Box.createVerticalStrut(8));
        body.add(qtyPanel);
        body.add(Box.createVerticalStrut(10));
        body.add(toppingHeader);
        body.add(toppingScroll);

        dialog.add(body, BorderLayout.CENTER);

        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonBar.setOpaque(false);
        JButton cancel = new JButton("Huy");
        JButton ok = new JButton("OK");
        styleSecondaryButton(cancel);
        stylePrimaryButton(ok);

        cancel.addActionListener(e -> dialog.dispose());
        ok.addActionListener(e -> {
            String selectedSize = sizeL.isSelected() ? "L" : "M";
            int quantity = (Integer) qtySpinner.getValue();
            double basePrice = spBUS.getPriceSp(String.valueOf(productId), selectedSize);

            StringBuilder toppingText = new StringBuilder();
            double toppingUnitPrice = 0;
            ArrayList<ToppingDTO> toppingData = tpBUS.getAll();
            for (int i = 0; i < toppingData.size(); i++) {
                int tQty = (Integer) toppingQty.get(i).getValue();
                if (tQty > 0) {
                    ToppingDTO t = toppingData.get(i);
                    toppingText.append(t.getNameTopping()).append("(x").append(tQty).append(")\n");
                    toppingUnitPrice += t.getPriceTopping() * tQty;
                }
            }

            double linePrice = (basePrice + toppingUnitPrice) * quantity;
            cartProduct product = new cartProduct(
                    productId,
                    productName + "(" + selectedSize + ")",
                    toppingText.toString(),
                    linePrice,
                    quantity,
                    selectedSize
            );
            if (editingIndex >= 0 && editingIndex < cartProducts.size()) {
                cartProducts.set(editingIndex, product);
            } else {
                cartProducts.add(product);
            }
            renderCart();
            dialog.dispose();
        });

        buttonBar.add(cancel);
        buttonBar.add(ok);
        dialog.add(buttonBar, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JLabel createDialogHeader(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(titleColor);
        return label;
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(actionButtonColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }

    private void styleSecondaryButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(nameColor);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(panelBorder),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)));
    }

    private void renderCart() {
        cartItemsPanel.removeAll();
        totalPrice = 0;

        if (cartProducts.isEmpty()) {
            JLabel empty = new JLabel("Chua co mon nao trong gio", SwingConstants.CENTER);
            empty.setForeground(new Color(140, 120, 133));
            empty.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            empty.setBorder(BorderFactory.createEmptyBorder(18, 8, 18, 8));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            cartItemsPanel.add(empty);
        } else {
            for (int i = 0; i < cartProducts.size(); i++) {
                final int index = i;
                cartProduct item = cartProducts.get(i);
                totalPrice += item.getPrice();

                JPanel itemPanel = new JPanel(new BorderLayout(8, 4));
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(238, 225, 232)),
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)));
                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));

                JLabel name = new JLabel(item.getName());
                name.setFont(new Font("Segoe UI", Font.BOLD, 13));
                name.setForeground(titleColor);

                String toppingText = item.getToppings() == null || item.getToppings().trim().isEmpty()
                        ? "Khong topping"
                        : item.getToppings().trim().replace("\n", ", ");
                JLabel sub = new JLabel("<html><body style='width:160px'>" + toppingText + "</body></html>");
                sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                sub.setForeground(new Color(120, 100, 114));

                JLabel info = new JLabel("SL: " + item.getQuant() + "   |   " + formatVnd(item.getPrice()));
                info.setFont(new Font("Segoe UI", Font.BOLD, 12));
                info.setForeground(nameColor);

                JButton remove = new JButton("Xoa");
                remove.setFocusPainted(false);
                remove.setBackground(new Color(255, 236, 243));
                remove.setForeground(new Color(177, 68, 118));
                remove.setFont(new Font("Segoe UI", Font.BOLD, 12));
                remove.setBorder(BorderFactory.createLineBorder(new Color(236, 197, 218)));
                remove.setPreferredSize(new Dimension(56, 26));
                remove.setMaximumSize(new Dimension(56, 26));
                remove.addActionListener(e -> {
                    cartProducts.remove(index);
                    renderCart();
                });

                JButton edit = new JButton("Sua");
                edit.setFocusPainted(false);
                edit.setBackground(new Color(239, 245, 255));
                edit.setForeground(new Color(73, 103, 168));
                edit.setFont(new Font("Segoe UI", Font.BOLD, 12));
                edit.setBorder(BorderFactory.createLineBorder(new Color(196, 212, 244)));
                edit.setPreferredSize(new Dimension(56, 26));
                edit.setMaximumSize(new Dimension(56, 26));
                edit.addActionListener(e -> {
                    String baseName = getBaseNameFromCartItem(item);
                    openConfigDialog(item.getId(), baseName, item, index);
                });

                JPanel centerText = new JPanel();
                centerText.setOpaque(false);
                centerText.setLayout(new BoxLayout(centerText, BoxLayout.Y_AXIS));
                centerText.add(name);
                centerText.add(Box.createVerticalStrut(3));
                centerText.add(sub);
                centerText.add(Box.createVerticalStrut(3));
                centerText.add(info);

                JPanel actionPanel = new JPanel();
                actionPanel.setOpaque(false);
                actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
                actionPanel.setPreferredSize(new Dimension(60, 60));
                actionPanel.setMaximumSize(new Dimension(60, 60));
                actionPanel.add(edit);
                actionPanel.add(Box.createVerticalStrut(6));
                actionPanel.add(remove);

                itemPanel.add(centerText, BorderLayout.CENTER);
                itemPanel.add(actionPanel, BorderLayout.EAST);

                cartItemsPanel.add(itemPanel);
                cartItemsPanel.add(Box.createVerticalStrut(8));
            }
        }

        totalPriceLabel.setText(formatVnd(totalPrice));
        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
    }

    private void checkoutCart() {
        if (cartProducts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng đang trống!");
            return;
        }

        Integer employeeId = getSelectedEmployeeId();
        if (employeeId == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ID nhân viên trước khi thanh toán!");
            return;
        }

        Integer customerId = resolveCustomerIdForCheckout();
        if (customerId != null && customerId == Integer.MIN_VALUE) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng theo số điện thoại đã nhập!");
            return;
        }

        ConfirmDialog confirmDialog = new ConfirmDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Xác nhận thanh toán",
                "Bạn có chắc muốn thanh toán?"
        );
        confirmDialog.setVisible(true);

        if (confirmDialog.isConfirmed()) {
            int newInvoiceId = pxBUS.taoHoaDon(employeeId, customerId, totalPrice);
            if (newInvoiceId <= 0) {
                String detail = pxBUS.getLastError();
                if (detail == null || detail.trim().isEmpty()) {
                    detail = "Không xác định";
                }
                JOptionPane.showMessageDialog(this, "Lưu hóa đơn thất bại!\nChi tiết: " + detail);
                return;
            }

            saveInvoiceDetails(newInvoiceId);

            for (cartProduct product : cartProducts) {
                nlBUS.updateQuantity(product.getId(), product.getSize(), product.getQuant());
            }
            cartProducts.clear();
            renderCart();
            phoneField.setText("");
            employeeCombo.setSelectedIndex(0);
            markCustomerLookupPending();
            JOptionPane.showMessageDialog(this, "Đã thanh toán thành công!");
        }
    }

    private Integer getSelectedEmployeeId() {
        if (employeeCombo == null) {
            return null;
        }
        String selected = String.valueOf(employeeCombo.getSelectedItem()).trim();
        if (selected.isEmpty() || selected.equalsIgnoreCase("Chon ID NV")) {
            return null;
        }
        try {
            return Integer.parseInt(selected);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void markCustomerLookupPending() {
        if (customerIdValueLabel == null) {
            return;
        }

        String phone = phoneField != null ? phoneField.getText().trim() : "";
        if (phone.isEmpty()) {
            selectedCustomerFromEnter = null;
            customerIdValueLabel.setText("null");
            if (customerNameValueLabel != null) {
                customerNameValueLabel.setText("-");
            }
            customerIdValueLabel.setForeground(titleColor);
            return;
        }

        customerIdValueLabel.setText("Nhan Enter de tim");
        customerIdValueLabel.setForeground(new Color(104, 104, 140));
        if (customerNameValueLabel != null) {
            customerNameValueLabel.setText("-");
        }
        selectedCustomerFromEnter = null;
    }

    private void performCustomerLookupByPhone() {
        if (customerIdValueLabel == null) {
            return;
        }

        String phone = phoneField != null ? phoneField.getText().trim() : "";
        if (phone.isEmpty()) {
            markCustomerLookupPending();
            return;
        }

        KhachHangDTO kh = khBUS.timKhachHangTheoSDTNhapVao(phone);
        if (kh == null) {
            selectedCustomerFromEnter = null;
            customerIdValueLabel.setText("Khong tim thay");
            if (customerNameValueLabel != null) {
                customerNameValueLabel.setText("-");
            }
            customerIdValueLabel.setForeground(new Color(180, 60, 60));
        } else {
            selectedCustomerFromEnter = kh;
            customerIdValueLabel.setText(String.valueOf(kh.getMaKhachHang()));
            if (customerNameValueLabel != null) {
                customerNameValueLabel.setText(kh.getTenKhachHang());
            }
            customerIdValueLabel.setForeground(titleColor);
        }
    }

    private Integer resolveCustomerIdForCheckout() {
        String phone = phoneField != null ? phoneField.getText().trim() : "";
        if (phone.isEmpty()) {
            return null;
        }

        if (selectedCustomerFromEnter == null) {
            performCustomerLookupByPhone();
        }

        if (selectedCustomerFromEnter == null) {
            return Integer.MIN_VALUE;
        }
        return selectedCustomerFromEnter.getMaKhachHang();
    }

    private String formatVnd(double value) {
        return String.format(Locale.US, "%,.0f VNĐ", value).replace(',', '.');
    }

    private Map<String, Integer> parseToppingQuantities(String toppings) {
        Map<String, Integer> map = new HashMap<>();
        if (toppings == null || toppings.trim().isEmpty()) {
            return map;
        }

        String[] lines = toppings.split("\\n");
        for (String line : lines) {
            String value = line.trim();
            if (value.isEmpty()) {
                continue;
            }

            int marker = value.lastIndexOf("(x");
            int end = value.lastIndexOf(")");
            if (marker > 0 && end > marker + 2) {
                String name = value.substring(0, marker).trim();
                String qtyText = value.substring(marker + 2, end).trim();
                try {
                    map.put(name, Integer.parseInt(qtyText));
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return map;
    }

    private String getBaseNameFromCartItem(cartProduct item) {
        DTO.SanPhamDTO dto = spBUS.selectedById(String.valueOf(item.getId()));
        if (dto != null && dto.getTenSp() != null && !dto.getTenSp().trim().isEmpty()) {
            return dto.getTenSp();
        }

        String displayName = item.getName();
        int idx = displayName.lastIndexOf('(');
        if (idx > 0) {
            return displayName.substring(0, idx).trim();
        }
        return displayName;
    }

    private void saveInvoiceDetails(int invoiceId) {
        for (cartProduct item : cartProducts) {
            String productName = getBaseNameFromCartItem(item);
            String toppingsText = item.getToppings() == null || item.getToppings().trim().isEmpty() ? "Không" : item.getToppings().trim().replace("\n", ", ");
            pxBUS.themChiTietHoaDonDayDu(
                    invoiceId,
                    item.getId(),
                    productName,
                    item.getSize(),
                    toppingsText,
                    item.getQuant(),
                    item.getPrice()
            );
        }
    }

    private Integer findToppingIdByName(String toppingName) {
        if (toppingName == null) {
            return null;
        }
        for (ToppingDTO tp : tpBUS.getAll()) {
            if (toppingName.trim().equalsIgnoreCase(tp.getNameTopping().trim())) {
                return tp.getIdTopping();
            }
        }
        return null;
    }

   
//public static void main(String[] args)
//{
//  JFrame frame = new JFrame();
//        frame.setSize(800, 900);
//        SanPhamGUI sanPhamGUI = new SanPhamGUI();
//        frame.add(sanPhamGUI); // Add SanPhamGUI panel to the frame
//        scrollPane = new JScrollPane(sanPhamGUI); // Create JScrollPane with SanPhamGUI panel
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        frame.add(scrollPane); // Add JScrollPane to the frame (optional, only if the panel overflows)
//        frame.setVisible(true);
//
//        
//       
//}
}

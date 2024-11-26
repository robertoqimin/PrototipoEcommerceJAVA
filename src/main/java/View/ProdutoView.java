/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;




import Model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProdutoView extends JFrame {
    private JTextField txtPesquisa;
    private JButton btnPesquisar;
    private JList<String> listaCategorias;
    private JPanel painelProdutos;
    private JScrollPane scrollPane;
    private JButton btnVerCarrinho;
    private JMenuBar menuBar;
    private JMenu menuArquivo;
    private JMenuItem menuItemSair;
    private JMenu menuAjuda;
    private JMenuItem menuItemSobre;
    private JToggleButton toggleButtonPromo;
    private JCheckBox checkBoxPromo;
    private JRadioButton radioButtonGrid;
    private JRadioButton radioButtonList;
    private JDialog dialog;

    public ProdutoView() {
        setTitle("E-commerce");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPesquisa = new JPanel(new BorderLayout());
        txtPesquisa = new JTextField();
        btnPesquisar = new JButton("Pesquisar");
        painelPesquisa.add(txtPesquisa, BorderLayout.CENTER);
        painelPesquisa.add(btnPesquisar, BorderLayout.EAST);


        toggleButtonPromo = new JToggleButton("Filtrar promoções");
        painelPesquisa.add(toggleButtonPromo, BorderLayout.SOUTH);


        checkBoxPromo = new JCheckBox("Somente promoções");
        painelPesquisa.add(checkBoxPromo, BorderLayout.NORTH);

        String[] categorias = {"Eletrônicos", "Moda", "Casa e Jardim", "Esportes", "Automóveis"};
        listaCategorias = new JList<>(categorias);
        listaCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel painelCategorias = new JPanel(new BorderLayout());
        painelCategorias.setBorder(BorderFactory.createTitledBorder("Categorias"));
        painelCategorias.add(new JScrollPane(listaCategorias), BorderLayout.CENTER);

        painelProdutos = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        scrollPane = new JScrollPane(painelProdutos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        btnVerCarrinho = new JButton("Ver carrinho");
        painelPesquisa.add(btnVerCarrinho, BorderLayout.WEST);

        setLayout(new BorderLayout());
        add(painelPesquisa, BorderLayout.NORTH);
        add(painelCategorias, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);


        menuBar = new JMenuBar();
        

        menuArquivo = new JMenu("Arquivo");
        menuItemSair = new JMenuItem("Sair");
        menuArquivo.add(menuItemSair);
        
        menuAjuda = new JMenu("Ajuda");
        menuItemSobre = new JMenuItem("Sobre");
        menuAjuda.add(menuItemSobre);
        
        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);
        
        setJMenuBar(menuBar);

        radioButtonGrid = new JRadioButton("Grade");
        radioButtonList = new JRadioButton("Lista");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonGrid);
        group.add(radioButtonList);

        JPanel radioPanel = new JPanel();
        radioPanel.add(radioButtonGrid);
        radioPanel.add(radioButtonList);
        add(radioPanel, BorderLayout.SOUTH);
    }

    public String getPesquisaTexto() {
        return txtPesquisa.getText();
    }

    public String getCategoriaSelecionada() {
        return listaCategorias.getSelectedValue();
    }

    public boolean isPromoFiltrada() {
        return toggleButtonPromo.isSelected();
    }

    public boolean isOnlyPromo() {
        return checkBoxPromo.isSelected();
    }

    public boolean isGridMode() {
        return radioButtonGrid.isSelected();
    }

    public boolean isListMode() {
        return radioButtonList.isSelected();
    }

    public void adicionarProdutoPainel(Produto produto, ActionListener listener) {
        JPanel painelProduto = new JPanel();
        painelProduto.setLayout(new BoxLayout(painelProduto, BoxLayout.Y_AXIS));
        painelProduto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        painelProduto.setPreferredSize(new Dimension(200, 300));

        JLabel lblImagem = new JLabel(new ImageIcon(getClass().getResource(produto.getImagem())));
        JLabel lblNome = new JLabel(produto.getNome(), SwingConstants.CENTER);
        JLabel lblPreco = new JLabel("Preço: R$ " + produto.getPreco(), SwingConstants.CENTER);
        JButton btnAdicionar = new JButton("Adicionar ao carrinho");

        btnAdicionar.addActionListener(listener);

        painelProduto.add(lblImagem);
        painelProduto.add(lblNome);
        painelProduto.add(lblPreco);
        painelProduto.add(btnAdicionar);

        painelProdutos.add(painelProduto);
        painelProdutos.revalidate();
        painelProdutos.repaint();
    }

    public void adicionarListenerPesquisar(ActionListener listener) {
        btnPesquisar.addActionListener(listener);
    }

    public void adicionarListenerCategoria(ActionListener listener) {
        listaCategorias.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listener.actionPerformed(null);
            }
        });
    }

    public void adicionarListenerVerCarrinho(ActionListener listener) {
        btnVerCarrinho.addActionListener(listener);
    }

    public void adicionarListenerMenuSair(ActionListener listener) {
        menuItemSair.addActionListener(listener);
    }

    public void adicionarListenerMenuSobre(ActionListener listener) {
        menuItemSobre.addActionListener(listener);
    }

    public void adicionarListenerToggle(ActionListener listener) {
        toggleButtonPromo.addActionListener(listener);
    }

    public void adicionarListenerCheckBox(ActionListener listener) {
        checkBoxPromo.addActionListener(listener);
    }

    public void adicionarListenerRadioButtonGrid(ActionListener listener) {
        radioButtonGrid.addActionListener(listener);
    }

    public void adicionarListenerRadioButtonList(ActionListener listener) {
        radioButtonList.addActionListener(listener);
    }

    public JPanel getPainelProdutos() {
        return painelProdutos;
    }

    public void limparProdutos() {
        painelProdutos.removeAll();
        painelProdutos.revalidate();
        painelProdutos.repaint();
    }

    public void exibirCarrinho(String carrinhoTexto, ActionListener listenerFinalizarCompra) {
        JTextArea textArea = new JTextArea(carrinhoTexto);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JButton btnFinalizarCompra = new JButton("Finalizar compra");
        btnFinalizarCompra.addActionListener(listenerFinalizarCompra);

        JPanel painelCarrinho = new JPanel(new BorderLayout());
        painelCarrinho.add(scrollPane, BorderLayout.CENTER);
        painelCarrinho.add(btnFinalizarCompra, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, painelCarrinho, "Carrinho", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarSobre() {
        JOptionPane.showMessageDialog(this, "Projeto E-commerce", "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarDialogo() {
        dialog = new JDialog(this, "Confirmação de compra", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        JLabel label = new JLabel("Compra finalizada com sucesso.", SwingConstants.CENTER);
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dialog.dispose());

        dialog.add(label, BorderLayout.CENTER);
        dialog.add(btnOk, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}

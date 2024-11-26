/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author qimin
 */


import Model.Carrinho;
import Model.Produto;
import View.ProdutoView;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;

public class ProdutoControl {
    private ProdutoView view;
    private Carrinho carrinho;
    private List<Produto> produtos;

    public ProdutoControl(ProdutoView view, Carrinho carrinho) {
        this.view = view;
        this.carrinho = carrinho;

        String imagemCaminho = "/Imagens/";

        produtos = Arrays.asList(
            new Produto("Laptop", 3000.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Celular", 1500.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Tablet", 1200.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Camiseta", 50.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Tênis", 200.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Sofá", 1200.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Mesa", 500.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Bicicleta", 800.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Patins", 300.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Carro", 35000.00, imagemCaminho + "placeholder.jpg"),
            new Produto("Moto", 15000.00, imagemCaminho + "placeholder.jpg")
        );

        view.adicionarListenerPesquisar(e -> buscarProdutos());
        view.adicionarListenerCategoria(e -> buscarPorCategoria());
        view.adicionarListenerVerCarrinho(e -> verCarrinho());
        view.adicionarListenerMenuSair(e -> System.exit(0));
        view.adicionarListenerMenuSobre(e -> view.mostrarSobre());
        view.adicionarListenerToggle(e -> atualizarFiltro());
        view.adicionarListenerCheckBox(e -> atualizarFiltro());
        view.adicionarListenerRadioButtonGrid(e -> atualizarModoVisualizacao());
        view.adicionarListenerRadioButtonList(e -> atualizarModoVisualizacao());
    }

    private void buscarProdutos() {
        String pesquisa = view.getPesquisaTexto();
        view.limparProdutos();
        for (Produto produto : produtos) {
            if (produto.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                view.adicionarProdutoPainel(produto, e -> adicionarProdutoAoCarrinho(produto));
            }
        }
    }

    private void buscarPorCategoria() {
        String categoria = view.getCategoriaSelecionada();
        view.limparProdutos();
        for (Produto produto : produtos) {
            if ((categoria.equals("Eletrônicos") && (produto.getNome().equals("Laptop") || produto.getNome().equals("Celular") || produto.getNome().equals("Tablet"))) ||
                (categoria.equals("Moda") && (produto.getNome().equals("Camiseta") || produto.getNome().equals("Tênis"))) ||
                (categoria.equals("Casa e Jardim") && (produto.getNome().equals("Sofá") || produto.getNome().equals("Mesa"))) ||
                (categoria.equals("Esportes") && (produto.getNome().equals("Bicicleta") || produto.getNome().equals("Patins"))) ||
                (categoria.equals("Automóveis") && (produto.getNome().equals("Carro") || produto.getNome().equals("Moto")))) {
                view.adicionarProdutoPainel(produto, e -> adicionarProdutoAoCarrinho(produto));
            }
        }
    }

    private void atualizarFiltro() {
        boolean somentePromocoes = view.isOnlyPromo();
        boolean filtrarPromocoes = view.isPromoFiltrada();
        view.limparProdutos();
        for (Produto produto : produtos) {
            if (somentePromocoes && produto.isPromocao()) {
                view.adicionarProdutoPainel(produto, e -> adicionarProdutoAoCarrinho(produto));
            } else if (!somentePromocoes) {
                view.adicionarProdutoPainel(produto, e -> adicionarProdutoAoCarrinho(produto));
            }
        }
    }

    private void atualizarModoVisualizacao() {
        view.limparProdutos();
        if (view.isGridMode()) {
            view.getPainelProdutos().setLayout(new GridLayout(0, 3, 10, 10));
        } else {
            view.getPainelProdutos().setLayout(new BoxLayout(view.getPainelProdutos(), BoxLayout.Y_AXIS));
        }
        for (Produto produto : produtos) {
            view.adicionarProdutoPainel(produto, e -> adicionarProdutoAoCarrinho(produto));
        }
    }

    private void adicionarProdutoAoCarrinho(Produto produto) {
        if (produto != null) {
            carrinho.adicionarProduto(produto);
        }
    }

    private void verCarrinho() {
        StringBuilder carrinhoTexto = new StringBuilder("Produtos no carrinho:\n");
        for (Produto produto : carrinho.getProdutos()) {
            carrinhoTexto.append(produto.getNome())
                          .append(" - R$ ")
                          .append(produto.getPreco())
                          .append("\n");
        }
        view.exibirCarrinho(carrinhoTexto.toString(), e -> finalizarCompra());
    }

    private void finalizarCompra() {
        if (carrinho.getProdutos().isEmpty()) {
            view.mostrarMensagem("O carrinho está vazio.");
        } else {
            carrinho.getProdutos().clear();
            view.mostrarDialogo();
        }
    }
}

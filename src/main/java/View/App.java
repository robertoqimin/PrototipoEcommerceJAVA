/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author qimin
 */
import Control.ProdutoControl;
import Model.Carrinho;
import View.ProdutoView;

public class App {
    public static void main(String[] args) {
        ProdutoView view = new ProdutoView();
        Carrinho carrinho = new Carrinho();
        ProdutoControl control = new ProdutoControl(view, carrinho);

        view.setVisible(true);
    }
}











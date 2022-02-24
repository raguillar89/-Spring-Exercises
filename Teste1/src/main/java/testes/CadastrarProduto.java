package testes;

import dao.ProdutoDAO;
import models.Enum.StatusProduto;
import models.Produto;
import utils.JPAUtil;
import javax.persistence.EntityManager;
import java.time.LocalDate;

public class CadastrarProduto {
    public static void main(String[] args) {
        Produto produto = new Produto();

        produto.setNome("Curso Java");
        produto.setDescricao("Livro de Java do DevInHouse");
        produto.setPreco(160.00);
        produto.setQtdEstoque(50);
        produto.setDataCadastro(LocalDate.now());
        produto.setStatus(StatusProduto.PRATELEIRA);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO dao = new ProdutoDAO(em);

        em.getTransaction().begin();

        dao.cadastrar(produto);

        em.getTransaction().commit();
        em.close();
    }
}

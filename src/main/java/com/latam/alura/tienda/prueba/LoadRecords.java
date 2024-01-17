package com.latam.alura.tienda.prueba;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.ItemsPedido;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.utils.JPAUtils;

public class LoadRecords {
	public static void cargarRegistro() throws FileNotFoundException{
		EntityManager em = JPAUtils.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProductoDao productoDao = new ProductoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		em.getTransaction().begin();
		
		LoadCategoria("categoria", categoriaDao,em);
		
		LoadProducto("producto", productoDao, categoriaDao, em);
		
		LoadCliente("cliente", clienteDao,em);
		
		List<Cliente> clientesList = clienteDao.consultarTodos();
		List<Pedido> pedidoList = new ArrayList<>();
		
		for(Cliente cl:clientesList) {
			pedidoList.add(new Pedido(cl));
		}
		
		for(int i=0;i<pedidoList.size();i++) {
			pedidoList.get(i).agregarItems(new ItemsPedido(i+1,producto));
			pedidoDao.guardar(pedidoList.get(i));
		}
		
		em.getTransaction().commit();
		em.close();
		
		
		
	
	}

	private static void LoadCliente(String string, ClienteDao clienteDao, EntityManager em) {
	}

	private static void LoadProducto(String string, ProductoDao productoDao, CategoriaDao categoriaDao,
			EntityManager em) {
	}

	private static void LoadCategoria(String string, CategoriaDao categoriaDao, EntityManager em) {
		
	}


}

/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.app.api.ProductsApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.ProductsMapper;
import ar.com.plug.examen.domain.model.Products;
import ar.com.plug.examen.domain.service.ProductRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class ProductsServiceImpl {
	
	@Autowired
	private ProductRepository productoRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param productos
	 * @return
	 */
	@Transactional
	public ProductsApi saveProductos(ProductsApi productosApi) throws Exception{

		ProductsMapper mapper = new ProductsMapper();
		
		Products prod = mapper.map(productosApi, Products.class);
		
		Products prod2 = productoRepository.save(prod);
		
		return mapper.map(prod2, ProductsApi.class);
	}

	/**
	 * method that find all entity 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<ProductsApi> getProductos() {
		ProductsMapper mapper = new ProductsMapper();
		
		List<Products> clienteList =  productoRepository.findAll();
		
		return mapper.mapAsList(clienteList, ProductsApi.class);
	}

	/**
	 * method that find entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public ProductsApi getProductoById(Integer idProducto) {
		ProductsMapper mapper = new ProductsMapper();
		Products prod = productoRepository.findById(idProducto).orElse(null);
		return mapper.map(prod, ProductsApi.class);
	}

	/**
	 * method that update an entity 
	 * @autor CACP - 5/02/2021
	 * @param productos
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public ProductsApi updateProductos(ProductsApi productos) throws Exception {
		
		if(productos == null || productos.getIdproduct() == null) {
			throw new ExamenException("The productid field cannot be null.");
		}
		
		ProductsMapper mapper = new ProductsMapper();
		
		Products producto = productoRepository.findById(productos.getIdproduct()).orElse(null);
		
		if(producto != null && producto.getIdproduct() != null) {
			
			producto.setCode(productos.getCode());
			producto.setDescription(productos.getDescription());
			producto.setEnabled(productos.getEnabled());
			producto.setPrice(productos.getPrice());
		}
		
		return mapper.map(productoRepository.save(producto), ProductsApi.class);
	}
	
	/**
	 * method that delete entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	@Transactional
	public MessageApi deleteProductoById(Integer idProducto) throws Exception{
		
		if(idProducto == null) {
			throw new Exception("The productid field cannot be null.");
		}
		
		MessageApi api = new MessageApi();
		
		Products producto = productoRepository.findById(idProducto).orElse(null);
		
		if(producto != null && producto.getIdproduct() != null) {
			
			productoRepository.deleteById(idProducto);
			
			api.setMessage("product removed successfully.");
			
		}else {
			api.setMessage("Product with the id " + idProducto + " Not found in the system.");
		}
		
		return api;
	}
}

/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.entity.Productos;
import ar.com.plug.examen.domain.service.ProductoRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class ProductosServiceImpl {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param productos
	 * @return
	 */
	public Productos saveProductos(Productos productos) {
		return productoRepository.save(productos);
	}

	/**
	 * method that find all entity 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<Productos> getProductos() {
		return productoRepository.findAll();
	}

	/**
	 * method that find entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public Productos getProductoById(Integer idProducto) {
		
		return productoRepository.findById(idProducto).orElse(null);
	}

	/**
	 * method that update an entity 
	 * @autor CACP - 5/02/2021
	 * @param productos
	 * @return
	 * @throws Exception 
	 */
	public Productos updateProductos(Productos productos) throws Exception {
		
		if(productos == null || productos.getIdproducto() == null) {
			throw new Exception("El campo idproducto no puede ser nulo.");
		}
		
		Productos producto = getProductoById(productos.getIdproducto());
		
		if(producto != null && producto.getIdproducto() != null) {
			
			producto.setCodigo(productos.getCodigo());
			producto.setDescripcion(productos.getDescripcion());
			producto.setHabiltiado(productos.getHabiltiado());
			producto.setValor(productos.getValor());
			
			return productoRepository.save(producto);
		}
		
		return null;
	}
	
	
}

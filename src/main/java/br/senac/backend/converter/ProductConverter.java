package br.senac.backend.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import br.senac.backend.model.Product;
import br.senac.backend.request.ProductRequest;
import br.senac.backend.response.ProductResponse;

@Component
public class ProductConverter {

	public Product productSave(ProductRequest productRequest) {
		try {
			Product product = new Product();
			product.setGuid(UUID.randomUUID().toString());
			product.setCode(productRequest.getCode());
			product.setName(productRequest.getName());
			product.setDescription(productRequest.getDescription());
			product.setPrice(productRequest.getPrice());
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product productSave(ProductRequest productRequest, Product product) {
		try {
			product.setCode(productRequest.getCode());
			product.setName(productRequest.getName());
			product.setDescription(productRequest.getDescription());
			product.setPrice(productRequest.getPrice());
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProductResponse productToResponse(Product product) {
		try {
			ProductResponse productResponse = new ProductResponse();
			productResponse.setCode(product.getCode());
			productResponse.setGuid(product.getGuid());
			productResponse.setName(product.getName());
			productResponse.setDescription(product.getDescription());
			productResponse.setPrice(product.getPrice());
			return productResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductResponse> productToResponseList(List<Product> products) {
		try {
			List<ProductResponse> list = new ArrayList<ProductResponse>();
			for (Product product : products) {
				ProductResponse productResponse = new ProductResponse();
				productResponse.setCode(product.getCode());
				productResponse.setGuid(product.getGuid());
				productResponse.setName(product.getName());
				productResponse.setDescription(product.getDescription());
				productResponse.setPrice(product.getPrice());
				list.add(productResponse);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

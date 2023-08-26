package br.senac.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.senac.backend.converter.ProductConverter;
import br.senac.backend.handler.HandlerProduct;
import br.senac.backend.model.Product;
import br.senac.backend.request.ProductRequest;
import br.senac.backend.response.ResponseAPI;
import br.senac.backend.response.ProductResponse;
import br.senac.backend.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HandlerProduct handlerProduct;

	@Autowired
	private ProductConverter productConverter;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseAPI> add(@RequestBody ProductRequest productRequest) { // verificar os saves e nomes dos metodos
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			if (!productService.isExists(productRequest.getCode())) {
				ProductResponse productResponse = productConverter
						.productToResponse(productService.save(productConverter.productSave(productRequest)));
				if (productResponse != null)
					handlerProduct.handleAddMessages(responseAPI, 200, productResponse);
				else
					handlerProduct.handleAddMessages(responseAPI, 404, null);
			} else
				handlerProduct.handleAddMessages(responseAPI, 304, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerProduct.handleAddMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/update/guid/{guid}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseAPI> update(@PathVariable String guid, @RequestBody ProductRequest productRequest) {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			Product product = productService.detail(guid);
			if (product != null) {
				ProductResponse productResponse = productConverter
						.productToResponse(productService.save(productConverter.productSave(productRequest, product)));
				if (productResponse != null)
					handlerProduct.handleUpdateMessages(responseAPI, 200, productResponse);
				else
					handlerProduct.handleUpdateMessages(responseAPI, 404, null);
			} else
				handlerProduct.handleUpdateMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerProduct.handleUpdateMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/detail/guid/{guid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseAPI> getByGuid(@PathVariable String guid) {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			ProductResponse productResponse = productConverter.productToResponse(productService.detail(guid));
			if (productResponse != null)
				handlerProduct.handleDetailMessages(responseAPI, 200, productResponse);
			else
				handlerProduct.handleDetailMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerProduct.handleDetailMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/delete/guid/{guid}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseAPI> delete(@PathVariable String guid) {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			Product product = productService.detail(guid);
			if (product != null) {
				productService.delete(product.getId());
				handlerProduct.handleDeleteMessages(responseAPI, 200);
			} else
				handlerProduct.handleDeleteMessages(responseAPI, 404);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerProduct.handleDeleteMessages(responseAPI, 400);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/product/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseAPI> list() {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			List<ProductResponse> list = productConverter.productToResponseList(productService.listAll());
			if (!list.isEmpty())
				handlerProduct.handleListMessages(responseAPI, 200, list);
			else
				handlerProduct.handleListMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerProduct.handleListMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}
}

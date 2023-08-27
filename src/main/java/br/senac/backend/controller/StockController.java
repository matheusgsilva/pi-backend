package br.senac.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.senac.backend.converter.StockConverter;
import br.senac.backend.handler.HandlerStock;
import br.senac.backend.model.Stock;
import br.senac.backend.request.StockRequest;
import br.senac.backend.response.ResponseAPI;
import br.senac.backend.response.StockResponse;
import br.senac.backend.service.StockService;

@Controller
public class StockController {

	@Autowired
	private StockService stockService;

	@Autowired
	private HandlerStock handlerStock;

	@Autowired
	private StockConverter stockConverter;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/stock/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseAPI> add(@RequestBody StockRequest stockRequest) {

		ResponseAPI responseAPI = new ResponseAPI();

		try {

			if (!stockService.isExists(stockRequest.getProductGuid())) {
				StockResponse stockResponse = stockConverter
						.stockToResponse(stockService.save(stockConverter.stockSave(stockRequest)));
				if (stockResponse != null)
					handlerStock.handleAddMessages(responseAPI, 200, stockResponse);
				else
					handlerStock.handleAddMessages(responseAPI, 404, null);
			} else
				handlerStock.handleAddMessages(responseAPI, 304, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			handlerStock.handleAddMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/stock/update/guid/{guid}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseAPI> update(@PathVariable String guid, @RequestBody StockRequest stockRequest) {

		ResponseAPI responseAPI = new ResponseAPI();

		try {
			if (!stockService.isExists(stockRequest.getProductGuid(), guid)) {
				Stock stock = stockService.findByGuid(guid);
				if (stock != null) {
					StockResponse stockResponse = stockConverter
							.stockToResponse(stockService.save(stockConverter.stockSave(stockRequest, stock)));
					if (stockResponse != null)
						handlerStock.handleUpdateMessages(responseAPI, 200, stockResponse);
					else
						handlerStock.handleUpdateMessages(responseAPI, 404, null);
				} else
					handlerStock.handleUpdateMessages(responseAPI, 404, null);
			} else
				handlerStock.handleUpdateMessages(responseAPI, 304, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			handlerStock.handleUpdateMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/stock/detail/guid/{guid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseAPI> getByGuid(@PathVariable String guid) {

		ResponseAPI responseAPI = new ResponseAPI();

		try {
			StockResponse stockResponse = stockConverter.stockToResponse(stockService.findByGuid(guid));
			if (stockResponse != null)
				handlerStock.handleDetailMessages(responseAPI, 200, stockResponse);
			else
				handlerStock.handleDetailMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			handlerStock.handleDetailMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/stock/delete/guid/{guid}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseAPI> delete(@PathVariable String guid) {

		ResponseAPI responseAPI = new ResponseAPI();

		try {
			Stock stock = stockService.findByGuid(guid);
			if (stock != null) {
				stockService.delete(stock.getId());
				handlerStock.handleDeleteMessages(responseAPI, 200);
			} else
				handlerStock.handleDeleteMessages(responseAPI, 404);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			handlerStock.handleDeleteMessages(responseAPI, 400);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/stock/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseAPI> list() {

		ResponseAPI responseAPI = new ResponseAPI();

		try {
			List<StockResponse> list = stockConverter.stockToResponseList(stockService.listAll());
			if (!list.isEmpty())
				handlerStock.handleListMessages(responseAPI, 200, list);
			else
				handlerStock.handleListMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			handlerStock.handleListMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}
}

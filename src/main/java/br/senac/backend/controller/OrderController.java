package br.senac.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.senac.backend.converter.OrderConverter;
import br.senac.backend.handler.HandlerOrder;
import br.senac.backend.model.OrderDemand;
import br.senac.backend.request.OrderRequest;
import br.senac.backend.response.ResponseAPI;
import br.senac.backend.response.OrderResponse;
import br.senac.backend.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private HandlerOrder handlerOrder;

	@Autowired
	private OrderConverter orderConverter;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseAPI> add(@RequestBody OrderRequest orderRequest) {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			OrderResponse orderResponse = orderConverter
					.orderToResponse(orderService.save(orderConverter.orderSave(orderRequest)));
			if (orderResponse != null)
				handlerOrder.handleAddMessages(responseAPI, 200, orderResponse);
			else
				handlerOrder.handleAddMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerOrder.handleAddMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/order/update/guid/{guid}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseAPI> update(@PathVariable String guid, @RequestBody OrderRequest orderRequest) {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			OrderDemand order = orderService.detail(guid);
			if (order != null) {
				OrderResponse orderResponse = orderConverter
						.orderToResponse(orderService.save(orderConverter.orderSave(orderRequest, order)));
				if (orderResponse != null)
					handlerOrder.handleUpdateMessages(responseAPI, 200, orderResponse);
				else
					handlerOrder.handleUpdateMessages(responseAPI, 404, null);
			} else
				handlerOrder.handleUpdateMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerOrder.handleUpdateMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/order/detail/guid/{guid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseAPI> getByGuid(@PathVariable String guid) {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			OrderResponse orderResponse = orderConverter.orderToResponse(orderService.detail(guid));
			if (orderResponse != null)
				handlerOrder.handleDetailMessages(responseAPI, 200, orderResponse);
			else
				handlerOrder.handleDetailMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerOrder.handleDetailMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/order/delete/guid/{guid}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseAPI> delete(@PathVariable String guid) {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			OrderDemand order = orderService.detail(guid);
			if (order != null) {
				orderService.delete(order.getId());
				handlerOrder.handleDeleteMessages(responseAPI, 200);
			} else
				handlerOrder.handleDeleteMessages(responseAPI, 404);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerOrder.handleDeleteMessages(responseAPI, 400);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/order/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseAPI> list() {
		ResponseAPI responseAPI = new ResponseAPI();
		try {
			List<OrderResponse> list = orderConverter.orderToResponseList(orderService.listAll());
			if (!list.isEmpty())
				handlerOrder.handleListMessages(responseAPI, 200, list);
			else
				handlerOrder.handleListMessages(responseAPI, 404, null);

			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
		} catch (Exception ex) {
			handlerOrder.handleListMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
		}
	}
}

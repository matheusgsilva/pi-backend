package br.senac.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.senac.backend.converter.OrderItemConverter;
import br.senac.backend.handler.HandlerOrderItem;
import br.senac.backend.model.OrderItem;
import br.senac.backend.request.OrderItemRequest;
import br.senac.backend.response.ResponseAPI;
import br.senac.backend.response.OrderItemResponse;
import br.senac.backend.service.OrderItemService;

@Controller
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private HandlerOrderItem handlerOrderItem;

    @Autowired
    private OrderItemConverter orderItemConverter;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/orderitem/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseAPI> add(@RequestBody OrderItemRequest orderItemRequest) {
        ResponseAPI responseAPI = new ResponseAPI();
        try {
            OrderItemResponse orderItemResponse = orderItemConverter
                    .orderItemToResponse(orderItemService.save(orderItemConverter.orderItemSave(orderItemRequest)));
            if (orderItemResponse != null)
                handlerOrderItem.handleAddMessages(responseAPI, 200, orderItemResponse);
            else
                handlerOrderItem.handleAddMessages(responseAPI, 404, null);

            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
        } catch (Exception ex) {
            handlerOrderItem.handleAddMessages(responseAPI, 400, null);
            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/orderitem/update/guid/{guid}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseAPI> update(@PathVariable String guid, @RequestBody OrderItemRequest orderItemRequest) {
        ResponseAPI responseAPI = new ResponseAPI();
        try {
            OrderItem orderItem = orderItemService.detail(guid);
            if (orderItem != null) {
                OrderItemResponse orderItemResponse = orderItemConverter
                        .orderItemToResponse(orderItemService.save(orderItemConverter.orderItemSave(orderItemRequest, orderItem)));
                if (orderItemResponse != null)
                    handlerOrderItem.handleUpdateMessages(responseAPI, 200, orderItemResponse);
                else
                    handlerOrderItem.handleUpdateMessages(responseAPI, 404, null);
            } else
                handlerOrderItem.handleUpdateMessages(responseAPI, 404, null);

            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
        } catch (Exception ex) {
            handlerOrderItem.handleUpdateMessages(responseAPI, 400, null);
            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/orderitem/detail/guid/{guid}", method = RequestMethod.GET)
    public ResponseEntity<ResponseAPI> getByGuid(@PathVariable String guid) {
        ResponseAPI responseAPI = new ResponseAPI();
        try {
            OrderItemResponse orderItemResponse = orderItemConverter.orderItemToResponse(orderItemService.detail(guid));
            if (orderItemResponse != null)
                handlerOrderItem.handleDetailMessages(responseAPI, 200, orderItemResponse);
            else
                handlerOrderItem.handleDetailMessages(responseAPI, 404, null);

            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
        } catch (Exception ex) {
            handlerOrderItem.handleDetailMessages(responseAPI, 400, null);
            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/orderitem/delete/guid/{guid}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseAPI> delete(@PathVariable String guid) {
        ResponseAPI responseAPI = new ResponseAPI();
        try {
            OrderItem orderItem = orderItemService.detail(guid);
            if (orderItem != null) {
                orderItemService.delete(orderItem.getId());
                handlerOrderItem.handleDeleteMessages(responseAPI, 200);
            } else
                handlerOrderItem.handleDeleteMessages(responseAPI, 404);

            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
        } catch (Exception ex) {
            handlerOrderItem.handleDeleteMessages(responseAPI, 400);
            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
        }
    }

//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/orderitem/list", method = RequestMethod.GET)
//    public ResponseEntity<ResponseAPI> list() {
//        ResponseAPI responseAPI = new ResponseAPI();
//        try {
//            List<OrderItemResponse> list = orderItemConverter.orderItemToResponseList(orderItemService.());
//            if (!list.isEmpty())
//                handlerOrderItem.handleListMessages(responseAPI, 200, list);
//            else
//                handlerOrderItem.handleListMessages(responseAPI, 404, null);
//
//            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
//        } catch (Exception ex) {
//            handlerOrderItem.handleListMessages(responseAPI, 400, null);
//            return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.BAD_REQUEST);
//        }
//    }
}

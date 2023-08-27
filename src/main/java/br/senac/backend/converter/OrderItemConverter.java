package br.senac.backend.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.senac.backend.model.OrderItem;
import br.senac.backend.request.OrderItemRequest;
import br.senac.backend.response.OrderItemResponse;
import br.senac.backend.service.OrderService;
import br.senac.backend.service.ProductService;

@Component
public class OrderItemConverter {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;

    public OrderItem orderItemSave(OrderItemRequest orderItemRequest) {
        try {
            OrderItem orderItem = new OrderItem();
            orderItem.setGuid(UUID.randomUUID().toString());
            orderItem.setOrder(orderService.detail(orderItemRequest.getOrderGuid()));
            orderItem.setProduct(productService.detail(orderItemRequest.getProductGuid()));
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setWeight(orderItemRequest.getWeight());
            return orderItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderItem orderItemSave(OrderItemRequest orderItemRequest, OrderItem orderItem) {
        try {
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setWeight(orderItemRequest.getWeight());
            return orderItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderItemResponse orderItemToResponse(OrderItem orderItem) {
        try {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setGuid(orderItem.getGuid());
            orderItemResponse.setOrderGuid(orderItem.getOrder().getGuid());
            orderItemResponse.setProductGuid(orderItem.getProduct().getGuid());
            orderItemResponse.setProductName(orderItem.getProduct().getName());
            orderItemResponse.setProductCode(orderItem.getProduct().getCode());
            orderItemResponse.setProductType(orderItem.getProduct().getType());
            orderItemResponse.setQuantity(orderItem.getQuantity());
            orderItemResponse.setWeight(orderItem.getWeight());
            return orderItemResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderItemResponse> orderItemToResponseList(List<OrderItem> orderItems) {
        try {
            List<OrderItemResponse> list = new ArrayList<OrderItemResponse>();
            for (OrderItem orderItem : orderItems) {
                OrderItemResponse orderItemResponse = new OrderItemResponse();
                orderItemResponse.setGuid(orderItem.getGuid());
                orderItemResponse.setOrderGuid(orderItem.getOrder().getGuid());
                orderItemResponse.setProductGuid(orderItem.getProduct().getGuid());
                orderItemResponse.setProductName(orderItem.getProduct().getName());
                orderItemResponse.setProductCode(orderItem.getProduct().getCode());
                orderItemResponse.setProductType(orderItem.getProduct().getType());
                orderItemResponse.setQuantity(orderItem.getQuantity());
                orderItemResponse.setWeight(orderItem.getWeight());
                list.add(orderItemResponse);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package br.senac.backend.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.senac.backend.model.OrderDemand;
import br.senac.backend.request.OrderRequest;
import br.senac.backend.response.OrderResponse;
import br.senac.backend.service.UserService;

@Component
public class OrderConverter {
	
	@Autowired
	private UserService userService;

    public OrderDemand orderSave(OrderRequest orderRequest) {
        try {
            OrderDemand order = new OrderDemand();
            order.setNumber(UUID.randomUUID().toString());
            order.setUser(userService.getByGuid(orderRequest.getUserGuid()));
            order.setOrderDate(orderRequest.getOrderDate());
            order.setNumber(orderRequest.getNumber());
            order.setStatus(orderRequest.getStatus());
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderDemand orderSave(OrderRequest orderRequest, OrderDemand order) {
        try {
            order.setOrderDate(orderRequest.getOrderDate());
            order.setNumber(orderRequest.getNumber());
            order.setStatus(orderRequest.getStatus());
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderResponse orderToResponse(OrderDemand order) {
        try {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setUserGuid(order.getUser().getGuid());
            orderResponse.setUserName(order.getUser().getName());
            orderResponse.setOrderDate(order.getOrderDate());
            orderResponse.setNumber(order.getNumber());
            orderResponse.setStatus(order.getStatus());
            return orderResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderResponse> orderToResponseList(List<OrderDemand> orders) {
        try {
            List<OrderResponse> list = new ArrayList<OrderResponse>();
            for (OrderDemand order : orders) {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setUserGuid(order.getUser().getGuid());
                orderResponse.setUserName(order.getUser().getName());
                orderResponse.setOrderDate(order.getOrderDate());
                orderResponse.setNumber(order.getNumber());
                orderResponse.setStatus(order.getStatus());
                list.add(orderResponse);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

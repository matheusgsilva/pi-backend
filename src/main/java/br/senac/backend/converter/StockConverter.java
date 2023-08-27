package br.senac.backend.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.senac.backend.model.Stock;
import br.senac.backend.request.StockRequest;
import br.senac.backend.response.StockResponse;
import br.senac.backend.service.ProductService;

@Component
public class StockConverter {
	
	@Autowired
	private ProductService productService;

    public Stock stockSave(StockRequest stockRequest) {
        try {
            Stock stock = new Stock();
            stock.setGuid(UUID.randomUUID().toString());
            stock.setProduct(productService.detail(stockRequest.getProductGuid()));
            stock.setQuantity(stockRequest.getQuantityAvailable());
            stock.setWeight(stockRequest.getWeightAvailable());
            return stock;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Stock stockSave(StockRequest stockRequest, Stock stock) {
        try {
            stock.setQuantity(stockRequest.getQuantityAvailable());
            stock.setWeight(stockRequest.getWeightAvailable());
            return stock;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StockResponse stockToResponse(Stock stock) {
        try {
            StockResponse stockResponse = new StockResponse();
            stockResponse.setGuid(stock.getGuid());
            stockResponse.setProductGuid(stock.getProduct().getGuid());
            stockResponse.setProductName(stock.getProduct().getName());
            stockResponse.setProductCode(stock.getProduct().getCode());
            stockResponse.setStockType(stock.getProduct().getType());
            stockResponse.setQuantityAvailable(stock.getQuantity());
            stockResponse.setWeightAvailable(stock.getWeight());
            return stockResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StockResponse> stockToResponseList(List<Stock> stocks) {
        try {
            List<StockResponse> list = new ArrayList<StockResponse>();
            for (Stock stock : stocks) {
                StockResponse stockResponse = new StockResponse();
                stockResponse.setGuid(stock.getGuid());
                stockResponse.setProductGuid(stock.getProduct().getGuid());
                stockResponse.setProductName(stock.getProduct().getName());
                stockResponse.setProductCode(stock.getProduct().getCode());
                stockResponse.setStockType(stock.getProduct().getType());
                stockResponse.setQuantityAvailable(stock.getQuantity());
                stockResponse.setWeightAvailable(stock.getWeight());
                list.add(stockResponse);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

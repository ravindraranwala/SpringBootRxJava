package com.example.transformer;

import com.example.domain.dto.OrderDetailsDTO;
import com.example.domain.dto.ProductDetailsDTO;
import com.example.domain.dto.ShippingDetailsDTO;

public class ProductDetailsDTOTOOrderDetailsDTOTransformer implements Transformer<ProductDetailsDTO, OrderDetailsDTO> {
	private ShippingDetailsDTO shippingInformation;

	public ProductDetailsDTOTOOrderDetailsDTOTransformer(ShippingDetailsDTO shippingInformation) {
		super();
		this.shippingInformation = shippingInformation;
	}

	@Override
	public OrderDetailsDTO transform(ProductDetailsDTO source) {
		return new OrderDetailsDTO(source.getName(), source.getUnitPrice() + shippingInformation.getCost(),
				source.getUnitPrice(), source.getCurrency(), source.getSoldBy(),
				shippingInformation.getCourierService(), shippingInformation.getAddress(),
				shippingInformation.getCost());
	}

}

package kr.brian.study.core.order.service.extension;

import org.springframework.stereotype.Service;

import kr.brian.study.common.extension.ExtensionManager;

@Service("cartAddItemExtensionManager")
public class CartAddItemExtensionManager extends ExtensionManager<CartAddItemExtensionHandler>{

	public CartAddItemExtensionManager() {
		super(CartAddItemExtensionHandler.class);
	}
	
	public boolean continueOnHandled() {
        return true;
    }
}
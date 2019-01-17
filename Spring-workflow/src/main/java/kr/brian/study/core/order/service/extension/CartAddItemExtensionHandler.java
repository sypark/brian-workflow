package kr.brian.study.core.order.service.extension;

import kr.brian.study.common.extension.ExtensionHandler;
import kr.brian.study.common.extension.ExtensionResultHolder;
import kr.brian.study.common.extension.ExtensionResultStatusType;

public interface CartAddItemExtensionHandler extends ExtensionHandler  {

	public ExtensionResultStatusType add(ExtensionResultHolder<CartAddDto> result) throws Exception;
	
}

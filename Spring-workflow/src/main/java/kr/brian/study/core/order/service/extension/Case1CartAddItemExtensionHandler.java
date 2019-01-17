package kr.brian.study.core.order.service.extension;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.brian.study.common.extension.ExtensionResultHolder;
import kr.brian.study.common.extension.ExtensionResultStatusType;

@Service("case1CartAddItemExtensionHandler")
public class Case1CartAddItemExtensionHandler implements CartAddItemExtensionHandler {
	
	@Resource(name = "cartAddItemExtensionManager")
	protected CartAddItemExtensionManager extensionManager;

	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public ExtensionResultStatusType add(ExtensionResultHolder<CartAddDto> result) throws Exception {
		
		System.out.println("hey,hey,hey, this is add Extension Handler");
		
		return ExtensionResultStatusType.NOT_HANDLED;
	}

	@PostConstruct
	public void init() {
		extensionManager.registerHandler(this);
	}
}

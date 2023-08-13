package com.Koupag.services.services_implementations;

import com.Koupag.models.RequestItem;
import com.Koupag.repositories.RequestItemRepository;
import com.Koupag.services.RequestItemService;
import org.springframework.stereotype.Service;

@Service
public class RequestItemServiceImpl implements RequestItemService {
	
	private final RequestItemRepository requestItemRepository;
	
	public RequestItemServiceImpl(RequestItemRepository requestItemRepository) {
		this.requestItemRepository = requestItemRepository;
	}
	
	@Override
	public void createNewRequestItem(RequestItem requestItem) {
		requestItemRepository.save(requestItem);
		
	}
}

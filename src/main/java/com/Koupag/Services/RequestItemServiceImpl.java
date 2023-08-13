package com.Koupag.Services;

import com.Koupag.Model.RequestItem;
import com.Koupag.Repository.RequestItemRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestItemServiceImpl implements RequestItemService{
	
	private final RequestItemRepository requestItemRepository;
	
	public RequestItemServiceImpl(RequestItemRepository requestItemRepository) {
		this.requestItemRepository = requestItemRepository;
	}
	
	@Override
	public void createNewRequestItem(RequestItem requestItem) {
		requestItemRepository.save(requestItem);
		
	}
}

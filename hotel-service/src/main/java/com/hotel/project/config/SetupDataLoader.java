package com.hotel.project.config;

import com.hotel.project.model.entity.Category;
import com.hotel.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		createRoleIfNotFound("Khách sạn");
		createRoleIfNotFound("Rosort");
		createRoleIfNotFound("Homestay");
		createRoleIfNotFound("Căn hộ");
		createRoleIfNotFound("Biệt thự");
	}

	@Transactional
	private final Category createRoleIfNotFound(final String name) {
		Category category = null;
		category = categoryRepository.findByName(name).orElseGet(()->{
			return categoryRepository.save(new Category(name));
		});
		return category;
	}
}
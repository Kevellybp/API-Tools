package com.api.tools.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.tools.model.ToolsModel;
import com.api.tools.repository.ToolsRepository;

@Service
public class ToolsService {

	
	@Autowired
	private ToolsRepository repository;
	
	public List<ToolsModel> findAllTools() {
		return repository.findAll();
	}
	
	public Optional<ToolsModel> findByIdTools (Long id) {
		return repository.findById(id);
	}
	
	public Optional<ToolsModel> save (ToolsModel toolsModel){
	return Optional.ofNullable(repository.save(toolsModel));
}
//	public Optional<Boolean> titleExisting (String title) {
//		return repository.findAllByTitleIgnoreCase(title);
//	}
	public boolean titleExisting (String title) {
		return repository.existsByTitleIgnoreCase(title);
	}
	
	@Transactional
public void delete (ToolsModel toolsModel) {
	repository.delete(toolsModel);
}
}